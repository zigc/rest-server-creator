package com.zigac.model;

import java.util.ArrayList;
import java.util.List;

public class Entity
{
    private String name;
    private List<Property> properties = new ArrayList<>();
    private List<Entity> referenceEntities = new ArrayList<>();

    public String getName()
    {
        return name;
    }

    public Entity(String name)
    {
        this.name = name;
    }

    public void addProperty(Property propertiy)
    {
        propertiy.setEntity(this);
        properties.add(propertiy);
    }

    public List<Property> getProperties()
    {
        return properties;
    }

    public void addRefrenceEntity(Entity entity)
    {
        referenceEntities.add(entity);
    }
}
