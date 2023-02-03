package eu.mshadeproduction.rshade.protocol.packets.status;

import eu.mshadeproduction.rshade.RShadeServer;
import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.PacketOut;
import eu.mshadeproduction.rshade.protocol.registry.Version;

public class PacketStatusOutStatusResponse implements PacketOut {

    private RShadeServer server;

    private PacketStatusOutStatusResponse() {
    }

    public PacketStatusOutStatusResponse(RShadeServer server) {
        this.server = server;
    }

    @Override
    public void encode(ByteMessage msg) {
        try {
            msg.writeString(server.buildServerListResponseJson("1.8.8", Version.getCurrentSupported().getProtocolNumber(), "RShade-1.8 made by MShadeProduction", 5, 0, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}