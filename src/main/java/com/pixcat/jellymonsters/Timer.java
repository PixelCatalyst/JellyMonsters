package com.pixcat.jellymonsters;

public final class Timer {

    private static final long NANOS_IN_SECOND = 1_000_000_000;

    private boolean started = false;
    private long lastNanos;

    public float elapsedSeconds() {
        if (started) {
            final var currentNanos = System.nanoTime();
            final var deltaNanos = currentNanos - lastNanos;
            lastNanos = currentNanos;
            return (float) deltaNanos / NANOS_IN_SECOND;
        } else {
            started = true;
            lastNanos = System.nanoTime();
            return 0.0f;
        }
    }
}
