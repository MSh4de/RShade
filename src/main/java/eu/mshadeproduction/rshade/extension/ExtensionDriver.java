package eu.mshadeproduction.rshade.extension;

import eu.mshadeproduction.mwork.MOptional;

import java.util.stream.Stream;

public interface ExtensionDriver {

    void enable();

    void enable(Class<? extends RShadeExtension> aClass);

    void unload();

    void unload(RShadeExtension rShadeExtension);

    <T extends RShadeExtension> MOptional<T> getExtension(Class<T> aClass);

    Stream<RShadeExtension> getExtensions();

}
