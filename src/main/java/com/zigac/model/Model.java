package com.zigac.model;

import java.util.ArrayList;
import java.util.List;

public class Model
{
    private List<Entity> entities = new ArrayList<>();

    public void addEntity(Entity entity)
    {
        entities.add(entity);
    }

    public List<Entity> getEntities()
    {
        return entities;
    }
}
