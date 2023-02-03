package eu.mshadeproduction.rshade.extension;

import eu.mshadeproduction.rshade.RShade;

public abstract class RShadeExtension {

    private final RShade rShade;

    public RShadeExtension(RShade rShade) {
        this.rShade = rShade;
    }

    protected abstract void onEnable();

    protected abstract void onDisable();

}
