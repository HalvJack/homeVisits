package com.example.wizytydomowe.HereApi;

public enum SpecializationGroup {
    RARE(50),
    MEDIUM(20),
    COMMON(0);

    private final int priceIncrement;

    SpecializationGroup(int priceIncrement) {
        this.priceIncrement = priceIncrement;
    }

    public int getPriceIncrement() {
        return priceIncrement;
    }
}
