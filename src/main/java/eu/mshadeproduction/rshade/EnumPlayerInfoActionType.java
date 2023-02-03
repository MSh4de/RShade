package eu.mshadeproduction.rshade;

import java.util.HashMap;
import java.util.Map;

public enum EnumPlayerInfoActionType {

    ADD_PLAYER(1), UPDATE_GAME_MODE(2), UPDATE_LATENCY(3), UPDATE_DISPLAY_NAME(4), REMOVE_PLAYER(5);

    private int id;
    private static Map<Integer, EnumPlayerInfoActionType> MAP = new HashMap<>();

    EnumPlayerInfoActionType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EnumPlayerInfoActionType getActionById(int id) {
        return MAP.get(id);
    }

    static {
        for (EnumPlayerInfoActionType value : EnumPlayerInfoActionType.values()) {
            MAP.put(value.getId(), value);
        }
    }

}
