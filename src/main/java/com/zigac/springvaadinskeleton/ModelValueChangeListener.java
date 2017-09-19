package com.zigac.springvaadinskeleton;

import java.util.Set;

import com.zigac.model.Property;

@FunctionalInterface
public interface ModelValueChangeListener
{
    void modelPropertiesChanged(Set<Property> properties);
}
