package com.zigac.utils;

import com.zigac.model.Entity;
import com.zigac.model.Model;
import com.zigac.model.Property;

public class MockEntity
{
    public static Model createMockEntity()
    {
        Model model = new Model();

        Entity oglasEntity = new Entity("oglas");
        oglasEntity.addProperty(new Property("title", String.class));
        oglasEntity.addProperty(new Property("phone_number", Integer.class));
        oglasEntity.addProperty(new Property("url", String.class));
        oglasEntity.addProperty(new Property("html", String.class));

        model.addEntity(oglasEntity);

        return model;
    }
}
