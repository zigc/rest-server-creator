package com.zigac.rest.creator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.HasValue;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.zigac.model.Entity;
import com.zigac.model.Property;
import com.zigac.utils.MockEntity;

public class ModelLayout extends VerticalLayout
{
    private static final Logger log = LoggerFactory.getLogger(ModelLayout.class);

    private List<ModelValueChangeListener> listeners = new ArrayList<>();

    public ModelLayout()
    {

        addComponent(new Label("DB: "));
        addComponent(createDBUI());

        addComponent(new Label("Response: "));
        MockEntity.createMockEntity().getEntities().forEach(entity -> addComponent(createList(entity)));
    }

    private Layout createDBUI()
    {
        TextField dbUrl = new TextField();
        dbUrl.setIcon(VaadinIcons.LINK);
        dbUrl.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        TextField dbUser = new TextField();
        dbUser.setIcon(VaadinIcons.USER);
        dbUser.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        TextField dbPass = new TextField();
        dbPass.setIcon(VaadinIcons.LOCK);
        dbPass.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        return new VerticalLayout(dbUrl, dbUser, dbPass);
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
