package com.example.wizytydomowe.HereApi;

import com.example.wizytydomowe.Appointment.Importance;

import java.util.Map;

public class ImportancePricing {
    private static final Map<Importance, Integer> IMPORTANCE_PRICE_MAP = Map.of(
            Importance.URGENT, 3,
            Importance.MEDIUM, 2,
            Importance.MINOR, 1
    );

    public static int getAdditionalCost(Importance importance) {
        return IMPORTANCE_PRICE_MAP.getOrDefault(importance, 1);
    }
}

