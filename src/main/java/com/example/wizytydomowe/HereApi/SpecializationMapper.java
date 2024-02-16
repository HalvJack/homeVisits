package com.example.wizytydomowe.HereApi;

import java.util.HashMap;
import java.util.Map;

public class SpecializationMapper {
    private static final Map<String, SpecializationGroup> SPECIALIZATION_GROUP_MAP = new HashMap<>();

    static {
        SPECIALIZATION_GROUP_MAP.put("Masaż mobilny", SpecializationGroup.COMMON);
        SPECIALIZATION_GROUP_MAP.put("Medycyna rodzinna", SpecializationGroup.COMMON);
        SPECIALIZATION_GROUP_MAP.put("Dermatologia", SpecializationGroup.COMMON);
        SPECIALIZATION_GROUP_MAP.put("Fizjoterapie", SpecializationGroup.COMMON);
        SPECIALIZATION_GROUP_MAP.put("Internista", SpecializationGroup.MEDIUM);
        SPECIALIZATION_GROUP_MAP.put("Stomatolog", SpecializationGroup.MEDIUM);
        SPECIALIZATION_GROUP_MAP.put("Pediatria", SpecializationGroup.MEDIUM);
        SPECIALIZATION_GROUP_MAP.put("Diabetologia", SpecializationGroup.MEDIUM);
        SPECIALIZATION_GROUP_MAP.put("Anestezjologia", SpecializationGroup.RARE);
        SPECIALIZATION_GROUP_MAP.put("Psychiatria", SpecializationGroup.RARE);
        SPECIALIZATION_GROUP_MAP.put("Radiologia i diagnostyka obrazowa", SpecializationGroup.RARE);
    }

    public static SpecializationGroup getSpecializationGroup(String specialization) {
        return SPECIALIZATION_GROUP_MAP.getOrDefault(specialization, SpecializationGroup.COMMON);
    }
}
