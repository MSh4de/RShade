package eu.mshadeproduction.rshade.extension;

import eu.mshadeproduction.mwork.MOptional;
import eu.mshadeproduction.rshade.RShade;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class DefaultExtensionDriver implements ExtensionDriver {

    private File folder;
    private ConcurrentMap<Class<? extends RShadeExtension>, RShadeExtension> rShadeExtensions = new ConcurrentHashMap<>();

    public DefaultExtensionDriver(File folder) {
        this.folder = folder;
        folder.mkdir();
    }


    private Map<Class<? extends RShadeExtension>, ExtensionContext> load() {
        final Map<Class<? extends RShadeExtension>, ExtensionContext> extensionContexts = new HashMap<>();
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if (file.isFile() && file.getName().endsWith(".jar")) {
                try {
                    URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{file.toURI().toURL()}, this.getClass().getClassLoader());
                    getClassExtension(urlClassLoader).ifPresent(aClass -> extensionContexts.put(aClass, new ExtensionContext(aClass, urlClassLoader)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return extensionContexts;
    }

    @Override
    public void enable() {
        final Map<Class<? extends RShadeExtension>, ExtensionContext> extensionContexts = load();
        final Map<Integer, ExtensionContext> map = new HashMap<>();

        extensionContexts.forEach((aClass, extensionContext) -> {
            ExtensionHandler extensionHandler = extensionContext.getRShadeExtensionClazz().getAnnotation(ExtensionHandler.class);
            map.put(extensionHandler.priority().getPriority(), extensionContext);
        });

        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new)).forEach((integer, extensionContext) -> {
            if (!rShadeExtensions.containsKey(extensionContext.getRShadeExtensionClazz())) {
                ExtensionHandler extensionHandler = extensionContext.getRShadeExtensionClazz().getAnnotation(ExtensionHandler.class);
                for (Class<? extends RShadeExtension> dependency : extensionHandler.dependencies()) {
                    enable(dependency);
                }
                enable(extensionContext.getRShadeExtensionClazz());
            }
        });
    }

    @Override
    public void enable(Class<? extends RShadeExtension> aClass) {
        try {
            //create after DefaultRShade
            RShadeExtension rShadeExtension = aClass.getDeclaredConstructor(RShade.class).newInstance(null);
            rShadeExtensions.put(aClass, rShadeExtension);
            rShadeExtension.onEnable();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void unload() {
        rShadeExtensions.forEach((aClass, rShadeExtension) -> {
            ExtensionHandler extensionHandler = aClass.getAnnotation(ExtensionHandler.class);
            for (Class<? extends RShadeExtension> dependency : extensionHandler.dependencies()) {
                unload(rShadeExtensions.get(dependency));
            }
            unload(rShadeExtension);
        });
    }

    @Override
    public void unload(RShadeExtension rShadeExtension) {
        rShadeExtension.onDisable();
        rShadeExtensions.remove(rShadeExtension.getClass());
    }

    @Override
    public <T extends RShadeExtension> MOptional<T> getExtension(Class<T> aClass) {
        return MOptional.of(aClass.cast(rShadeExtensions.get(aClass)));
    }

    @Override
    public Stream<RShadeExtension> getExtensions() {
        return rShadeExtensions.values().stream();
    }

    private MOptional<Class<? extends RShadeExtension>> getClassExtension(URLClassLoader urlClassLoader) throws Exception {
        JarFile jarFile = new JarFile(urlClassLoader.getURLs()[0].getFile());
        Attributes attributes = jarFile.getManifest().getMainAttributes();
        if (attributes.containsKey("RShadeExtension")) {
            String value = attributes.getValue("RShadeExtension");
            Class<?> aClass = Class.forName(value, true, urlClassLoader);
            if (aClass.isAssignableFrom(RShadeExtension.class) && aClass.isAnnotationPresent(ExtensionHandler.class)) {
                return MOptional.of(aClass).map(clazz -> RShadeExtension.class);
            }
        }
        return MOptional.empty();
    }
}
