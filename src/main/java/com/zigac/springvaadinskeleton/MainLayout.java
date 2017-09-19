package com.zigac.springvaadinskeleton;

import com.vaadin.ui.HorizontalLayout;

public class MainLayout extends HorizontalLayout
{
    public MainLayout()
    {
        ApiLayout components = new ApiLayout();
        ModelLayout components1 = new ModelLayout();

        components1.addModelValueChangeListener(e -> components.setProperties(e));

        addComponents(components1, components);
    }
}
