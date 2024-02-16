package com.example.wizytydomowe.HereApi;

import lombok.Getter;

public enum TimeOfDayPricing {
    NIGHT(0, 6, 50),
    MORNING(6, 15, 20),
    AFTERNOON(15, 18, 30),
    EVENING(18, 24, 40);

    private final int startHour;
    private final int endHour;
    @Getter
    private final int priceIncrement;

    TimeOfDayPricing(int startHour, int endHour, int priceIncrement) {
        this.startHour = startHour;
        this.endHour = endHour;
        this.priceIncrement = priceIncrement;
    }

    public static TimeOfDayPricing fromHour(int hour) {
        for (TimeOfDayPricing period : values()) {
            if (hour >= period.startHour && hour < period.endHour) {
                return period;
            }
        }
        throw new IllegalArgumentException("Hour out of range: " + hour);
    }
}
