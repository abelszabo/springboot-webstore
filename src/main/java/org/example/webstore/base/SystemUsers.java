package org.example.webstore.base;

import java.util.UUID;

public final class SystemUsers {

    /**
     * Technical system user.
     * Used when no authenticated user is available
     * (scheduler, async job, startup, retry).
     */
    public static final UUID SYSTEM_UUID =
            UUID.fromString("00000000-0000-0000-0000-000000000001");

    private SystemUsers() {
    }
}
