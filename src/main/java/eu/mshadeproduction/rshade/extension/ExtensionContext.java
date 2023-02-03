package eu.mshadeproduction.rshade.extension;

import java.net.URLClassLoader;

public class ExtensionContext {

    private Class<? extends RShadeExtension> rShadeExtensionClazz;
    private URLClassLoader urlClassLoader;

    public ExtensionContext(Class<? extends RShadeExtension> rShadeExtensionClazz, URLClassLoader urlClassLoader) {
        this.rShadeExtensionClazz = rShadeExtensionClazz;
        this.urlClassLoader = urlClassLoader;

    }

    public Class<? extends RShadeExtension> getRShadeExtensionClazz() {
        return rShadeExtensionClazz;
    }

    public URLClassLoader getUrlClassLoader() {
        return urlClassLoader;
    }
}
