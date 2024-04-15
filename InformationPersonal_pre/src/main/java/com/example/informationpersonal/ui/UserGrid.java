package com.example.informationpersonal.ui;

import com.example.informationpersonal.backend.User;
import com.example.informationpersonal.backend.UserService;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.grid.Grid;
import com.vaadin.ui.*;

public class UserGrid extends VerticalLayout {

    private final UserService userService;
    private final ListDataProvider<User> dataProvider;
    private final Grid<User> grid;
    private final Button addUserButton;

    public UserGrid(UserService userService) {
        this.userService = userService;
        this.dataProvider = new ListDataProvider<>(userService.findAll());
        this.grid = new Grid<>();
        this.addUserButton = new Button("Add User");

        // Configure the grid
        grid.setDataProvider(dataProvider);
        grid.addColumn(User::getUsername).setHeaderCaption("Username");
        grid.addColumn(User::getEmail).setHeaderCaption("Email");
        grid.addColumn(User::getFullName).setHeaderCaption("Full Name");
        // Add more columns as needed

        // Add components using setComponents()
        setComponents(grid, addUserButton);

        // Handle add user button click
        addUserButton.addClickListener(click -> {
            User newUser = new User();
            UserForm form = new UserForm(newUser);
            form.addCloseListener(close -> {
                if (form.isSaved()) {
                    dataProvider.getItems().add(newUser);
                    dataProvider.refreshAll();
                }
            });
            form.openNewWindow();
        });
    }
}
