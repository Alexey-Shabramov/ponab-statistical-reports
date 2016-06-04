package com.uz.laboratory.statistical.service;


import javafx.fxml.FXMLLoader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.io.InputStream;

public class SpringFXMLLoader {
    @Autowired
    private ApplicationContext context;

    public Object load(final String resource) throws IOException {
        try (InputStream fxmlStream = getClass().getResourceAsStream(resource)) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(resource));
            loader.setControllerFactory(context::getBean);
            return loader.load(fxmlStream);
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
    }
}
