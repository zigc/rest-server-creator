package com.zigac.model;

import java.util.List;

public class Property
{
    private String name;
    private Class type;

    private Entity entity;

    private List<String> meta;

    private String referenceId;

    public Property(String name, Class type)
    {
        this.name = name;
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public Class getType()
    {
        return type;
    }

    public Entity getEntity()
    {
        return entity;
    }

    public void setEntity(Entity entity)
    {
        this.entity = entity;
    }

    @Override public String toString()
    {
        return  type.getSimpleName() + " " + name + "";
    }
}
