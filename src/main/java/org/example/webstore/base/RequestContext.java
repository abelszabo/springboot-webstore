package org.example.webstore.base;

import java.util.UUID;

public final class RequestContext {

    private final UUID userUuid;
    private final String username;

    public RequestContext(UUID userUuid, String username) {
        this.userUuid = userUuid;
        this.username = username;
    }

    public UUID getUserUuid() {
        return userUuid;
    }

    public String getUsername() {
        return username;
    }
}
