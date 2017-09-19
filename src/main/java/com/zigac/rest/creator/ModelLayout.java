package com.zigac.rest.creator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.HasValue;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.zigac.model.Entity;
import com.zigac.model.Property;
import com.zigac.utils.MockEntity;

public class ModelLayout extends VerticalLayout
{
    private static final Logger log = LoggerFactory.getLogger(ModelLayout.class);

    private List<ModelValueChangeListener> listeners = new ArrayList<>();

    public ModelLayout()
    {
        addComponent(new Label("Response: "));
        MockEntity.createMockEntity().getEntities().forEach(entity -> addComponent(createList(entity)));
    }

    private Component createList(Entity entity)
    {
        CheckBoxGroup<Property> select = new CheckBoxGroup<>(entity.getName());
        select.setItems(entity.getProperties());

        select.addValueChangeListener(this::itemClicked);

        return select;
    }

    private void itemClicked(HasValue.ValueChangeEvent<Set<Property>> setValueChangeEvent)
    {
        notifyModelValueChangeListener(setValueChangeEvent.getValue());
    }

    public void addModelValueChangeListener(ModelValueChangeListener listener)
    {
        this.listeners.add(listener);
    }

    void notifyModelValueChangeListener(Set<Property> properties)
    {
        this.listeners.forEach(listener -> listener.modelPropertiesChanged(properties));
    }
}
