package com.example.wizytydomowe.HereApi;


import com.fasterxml.jackson.annotation.JsonProperty;

// Class to represent the entire API response
public class RouteResponse {
    private Route[] routes;

    // Getters and setters
    public Route[] getRoutes() {
        return routes;
    }

    public void setRoutes(Route[] routes) {
        this.routes = routes;
    }
}

// Class to represent a route
class Route {
    private Section[] sections;

    // Getters and setters
    public Section[] getSections() {
        return sections;
    }

    public void setSections(Section[] sections) {
        this.sections = sections;
    }
}

// Class to represent a section of a route
class Section {
    @JsonProperty("summary")
    private TravelSummary travelSummary;

    // Getters and setters
    public TravelSummary getTravelSummary() {
        return travelSummary;
    }

    public void setTravelSummary(TravelSummary travelSummary) {
        this.travelSummary = travelSummary;
    }
}

// Class to represent the travel summary
class TravelSummary {
    private int duration;
    private int length;
    private int baseDuration;

    // Getters and setters
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getBaseDuration() {
        return baseDuration;
    }

    public void setBaseDuration(int baseDuration) {
        this.baseDuration = baseDuration;
    }
}

