package com.pixcat.jellymonsters.time;

import lombok.Value;

@Value(staticConstructor = "of")
public class Seconds {

    float amount;

    public static Seconds zero() {
        return new Seconds(0.0f);
    }
}
