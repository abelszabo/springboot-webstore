package org.example.webstore.base;

import java.util.UUID;

public final class SystemUsers {

    /**
     * Technical system user.
     * Used when no authenticated user is available
     * (scheduler, async job, startup, retry).
     */
    public static final UUID SYSTEM_UUID =
            //UUID.fromString("00000000-0000-0000-0000-000000000001");
            UUID.fromString("36c9b365-b879-4a97-ae8f-4cb8b1b37042");

    private SystemUsers() {
    }
}
