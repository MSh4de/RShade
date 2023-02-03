package eu.mshadeproduction.rshade.extension;

public enum ExtensionPriority {

    HIGH(1), NORMAL(2), LOW(3);

    private int priority;

    ExtensionPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
