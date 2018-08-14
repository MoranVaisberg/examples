package com.leadspace.annotations.model;

import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

public class IgnoranceIntrospector extends JacksonAnnotationIntrospector {
    public boolean hasIgnoreMarker(AnnotatedMember m) {
        return m.getDeclaringClass() == Vehicle.class && m.getName() == "modell"
                || m.getDeclaringClass() == Car.class
                || m.getName() == "towingCapacity"
                || super.hasIgnoreMarker(m);
    }
}
