package eu.mshadeproduction.rshade.protocol.packets.login;

import eu.mshadeproduction.rshade.protocol.ByteMessage;
import eu.mshadeproduction.rshade.protocol.Packet;

import javax.crypto.SecretKey;
import java.security.PublicKey;

public class PacketLoginEncryptionResponse implements Packet {

    private byte[] secretKeyEncrypted = new byte[0];
    private byte[] verifyTokenEncrypted = new byte[0];

    private PacketLoginEncryptionResponse() {
    }

    public PacketLoginEncryptionResponse(SecretKey secretKey, PublicKey publicKey, byte[] verifyTokenEncrypted) {

    }

    @Override
    public void decode(ByteMessage msg) {
        this.secretKeyEncrypted = msg.readBytesArray();
        this.verifyTokenEncrypted = msg.readBytesArray();
    }

    @Override
    public void encode(ByteMessage msg) {
        msg.writeBytesArray(this.secretKeyEncrypted);
        msg.writeBytesArray(this.verifyTokenEncrypted);
    }

    public byte[] getSecretKeyEncrypted() {
        return secretKeyEncrypted;
    }

    public byte[] getVerifyTokenEncrypted() {
        return verifyTokenEncrypted;
    }
}
