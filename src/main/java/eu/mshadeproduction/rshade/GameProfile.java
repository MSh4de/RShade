package eu.mshadeproduction.rshade;

import spark.utils.StringUtils;

import java.util.UUID;

public class GameProfile {

    private final UUID uuid;
    private final String username;

    public GameProfile(final UUID uuid, final String username) {
        super();
        if (uuid == null && StringUtils.isBlank((CharSequence) username)) {
            throw new IllegalArgumentException("Name and ID cannot both be blank");
        }
        this.uuid = uuid;
        this.username = username;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isComplete() {
        return this.uuid != null && StringUtils.isNotBlank((CharSequence) this.getUsername());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final GameProfile that = (GameProfile) o;
        Label_0062:
        {
            if (this.uuid != null) {
                if (this.uuid.equals(that.uuid)) {
                    break Label_0062;
                }
            } else if (that.uuid == null) {
                break Label_0062;
            }
            return false;
        }
        if (this.username != null) {
            if (this.username.equals(that.username)) {
                return true;
            }
        } else if (that.username == null) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = (this.uuid != null) ? this.uuid.hashCode() : 0;
        result = 31 * result + ((this.username != null) ? this.username.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{{ uuid: " + this.uuid + " },  { username: " + this.username + " }}";
    }

}