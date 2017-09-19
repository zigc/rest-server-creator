package com.zigac.springvaadinskeleton;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.HasValue;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.zigac.model.Property;

public class ApiLayout extends VerticalLayout
{
    private static final Logger log = LoggerFactory.getLogger(ApiLayout.class);

    private final Label generatedURL;
    private final Label json;
    private final TextArea sqlTextArea;

    public ApiLayout()
    {
        Button get = new Button("GET");
        Button post = new Button("POST");
        Button put = new Button("PUT");
        Button delete = new Button("DELETE");

        addComponent(new HorizontalLayout(get, post, put, delete));
        generatedURL = new Label();
        TextField methodTextField = new TextField();
        methodTextField.setValue("/");
        methodTextField.addValueChangeListener(this::generateURL);

        addComponent(new HorizontalLayout(new Label("GET:"), methodTextField, generatedURL));
        sqlTextArea = new TextArea("SQL");
        sqlTextArea.setWidth("400px");
        addComponent(sqlTextArea);

        json = new Label();
        json.setContentMode(ContentMode.PREFORMATTED);
        addComponent(json);
    }

    private String createJSONModel(Set<Property> properties)
    {
        if (properties == null)
        {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{\n");
        properties.forEach(property -> {
            stringBuilder.append("  \"" + property.getName() + "\": " + property.getType().getSimpleName());
            stringBuilder.append(",\n");
        });
        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    private void generateURL(HasValue.ValueChangeEvent<String> stringValueChangeEvent)
    {
        generatedURL.setValue(stringValueChangeEvent.getValue());
    }

    public void setProperties(Set<Property> properties)
    {
        log.info("setProperties: " + properties);

        refreshSQL(properties);
        refreshJson(properties);
    }

    private void refreshSQL(Set<Property> properties)
    {
        sqlTextArea.setValue(createSQLModel(properties));
    }

    private String createSQLModel(Set<Property> properties)
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("SELECT ");

        for (Property property : properties)
        {
            stringBuilder.append(property.getEntity().getName() + "." + property.getName() + ", ");
        }
        stringBuilder.append(" FROM " + properties.iterator().next().getEntity().getName());

        return stringBuilder.toString();
    }

    private void refreshJson(Set<Property> properties)
    {
        json.setValue(createJSONModel(properties));
    }
}
