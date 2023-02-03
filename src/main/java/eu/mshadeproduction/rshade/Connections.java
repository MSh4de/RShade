package eu.mshadeproduction.rshade;

import eu.mshadeproduction.rshade.protocol.ClientBridge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class Connections {

    private static final Logger logger = LoggerFactory.getLogger("");
    private final Map<UUID, ClientBridge> connections;

    public Connections() {
        connections = new ConcurrentHashMap<>();
    }

    public Collection<ClientBridge> getAllConnections() {
        return Collections.unmodifiableCollection(connections.values());
    }

    public int getCount() {
        return connections.size();
    }

    public void addConnection(ClientBridge connection) {
        connections.put(connection.getGameProfile().getUuid(), connection);
        logger.info(String.format("Player %s connected ", connection.getGameProfile().getUsername(), connection.getAddress()));
    }

    public void removeConnection(ClientBridge connection) {
        connections.remove(connection.getGameProfile().getUuid());
        logger.info(String.format("Player %s disconnected", connection.getGameProfile().getUsername()));
    }
}