package com.zigac.rest.creator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

@SpringBootApplication
public class RestServerCreatorApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RestServerCreatorApplication.class, args);
    }

    @Theme("valo")
    @SpringUI(path = "")
    public static class VaadinUI extends UI
    {
        @Override
        protected void init(VaadinRequest request)
        {
            setContent(new MainLayout());
        }
    }
}