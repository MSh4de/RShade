package eu.mshadeproduction.rshade.protocol.packets.play;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.PacketIn;

public class PacketPlayInSettings implements PacketIn {

    private String locale;
    private byte viewDistance;
    private byte chatMode;
    private boolean chatColors;
    private short displayedSkinParts;


    @Override
    public void decode(ByteMessage msg) {
        this.locale = msg.readString();
        this.viewDistance = msg.readByte();
        this.chatMode = msg.readByte();
        this.chatColors = msg.readBoolean();
        this.displayedSkinParts = msg.readUnsignedByte();
    }

    public String getLocale() {
        return locale;
    }

    public byte getViewDistance() {
        return viewDistance;
    }

    public byte getChatMode() {
        return chatMode;
    }

    public boolean isChatColors() {
        return chatColors;
    }

    public short getDisplayedSkinParts() {
        return displayedSkinParts;
    }

    @Override
    public String toString() {
        return "PacketPlayInSettings{" +
                "locale='" + locale + '\'' +
                ", viewDistance=" + viewDistance +
                ", chatMode=" + chatMode +
                ", chatColors=" + chatColors +
                ", displayedSkinParts=" + displayedSkinParts +
                '}';
    }
}
