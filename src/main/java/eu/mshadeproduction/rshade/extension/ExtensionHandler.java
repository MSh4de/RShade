package eu.mshadeproduction.rshade.extension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtensionHandler {

    String name();

    String version();

    String[] authors();

    Class<? extends RShadeExtension>[] dependencies() default {};

    ExtensionPriority priority() default ExtensionPriority.NORMAL;


}
