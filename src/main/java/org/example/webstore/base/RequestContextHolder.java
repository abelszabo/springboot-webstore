package org.example.webstore.base;

import java.util.UUID;

public final class RequestContextHolder {

    private static final ThreadLocal<RequestContext> CONTEXT =
            new ThreadLocal<>();

    private RequestContextHolder() {
    }

    public static void set(RequestContext context) {
        CONTEXT.set(context);
    }

    public static RequestContext get() {
        return CONTEXT.get();
    }

    public static UUID getUserUuid() {
        RequestContext ctx = CONTEXT.get();
        return ctx != null ? ctx.getUserUuid() : null;
    }

    public static UUID getUserUuidOrSystem() {
        UUID actor = getUserUuid();
        return actor != null ? actor : SystemUsers.SYSTEM_UUID;
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
