package com.zigac.rest.creator;

import com.vaadin.ui.HorizontalLayout;

public class MainLayout extends HorizontalLayout
{
    public MainLayout()
    {
        ApiLayout components = new ApiLayout();
        ModelLayout components1 = new ModelLayout();
        GeneratedLayout generatedLayout = new GeneratedLayout();

        components1.addModelValueChangeListener(e -> components.setProperties(e));

        addComponents(components1, components, generatedLayout);
    }
}
