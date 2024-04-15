package com.vaadin.training.intro.exercises;


import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route
public class MainView extends VerticalLayout {
    public MainView() {

        add(new ProductForm());
    }
}


