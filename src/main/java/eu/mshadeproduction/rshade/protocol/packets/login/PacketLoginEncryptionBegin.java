package eu.mshadeproduction.rshade.protocol.packets.login;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

import java.security.PublicKey;

public class PacketLoginEncryptionBegin implements Packet {

    private String hashedServerId;
    private PublicKey publicKey;
    private byte[] verifyToken;

    private PacketLoginEncryptionBegin() {
    }

    public PacketLoginEncryptionBegin(String hashedServerId, PublicKey publicKey, byte[] verifyToken) {
        this.hashedServerId = hashedServerId;
        this.publicKey = publicKey;
        this.verifyToken = verifyToken;
    }

    @Override
    public void decode(ByteMessage msg) {
        this.hashedServerId = msg.readString(20);
        //this.publicKey = EncryptionManager.decodePublicKey(msg.readBytesArray());
        this.verifyToken = msg.readBytesArray();
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeString(this.hashedServerId);
        msg.writeBytesArray(this.publicKey.getEncoded());
        msg.writeBytesArray(this.verifyToken);
    }

    public String getHashedServerId() {
        return hashedServerId;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public byte[] getVerifyToken() {
        return verifyToken;
    }
}
