package com.rachio.test.api.qatest.util;

import java.util.UUID;

import static com.rachio.test.api.qatest.util.ARandom.aRandom;

public class RandomBuilderSupport {

    public UUID uuid() {
        return UUID.randomUUID();
    }

    public int rainDelayDuration() {
        return aRandom.getNumberBetween(0, 604800);
    }

    public int wateringDuration() {
        return aRandom.getNumberBetween(0, 10800);
    }

    public double seasonalAdjustment() {
        double randomDouble = aRandom.nextDouble();
        randomDouble = randomDouble * 100;
        randomDouble = Math.round(randomDouble);
        return randomDouble / 100;
    }
}
