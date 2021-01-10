package com.pixcat.jellymonsters.time;

public final class Timer {

    private static final long NANOS_IN_SECOND = 1_000_000_000;

    private boolean started = false;
    private long lastNanos;

    public Seconds getElapsedSeconds() {
        if (started) {
            final var currentNanos = System.nanoTime();
            final var deltaNanos = currentNanos - lastNanos;
            lastNanos = currentNanos;
            return Seconds.of((float) deltaNanos / NANOS_IN_SECOND);
        } else {
            started = true;
            lastNanos = System.nanoTime();
            return Seconds.zero();
        }
    }
}
