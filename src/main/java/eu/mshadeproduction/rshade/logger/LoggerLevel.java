package eu.mshadeproduction.rshade.logger;

public enum LoggerLevel {

    TRACE("", 0),
    DEBUG(ChatColor.GREEN, 10),
    INFO(ChatColor.BLUE, 20),
    WARN(ChatColor.YELLOW, 30),
    ERROR(ChatColor.RED, 40);

    private final String color;
    private final int level;

    LoggerLevel(String color, int level) {
        this.color = color;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public String getColor() {
        return color;
    }
}
