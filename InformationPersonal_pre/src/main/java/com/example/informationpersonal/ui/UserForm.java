package com.example.informationpersonal.ui;

import com.example.informationpersonal.backend.User;
import com.vaadin.data.util.BeanValidationHelper;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.ui.*;

public class UserForm extends VerticalLayout {

    private final User user;
    private final TextField usernameField;
    private final TextField emailField;
    private final PasswordField passwordField;
    private final TextField fullNameField;
    private final DateField dateOfBirthField;
    private final ComboBox<String> genderComboBox;
    private final TextField phoneNumberField;
    private final TextField addressField;
    private final Button saveButton;
    private final Button cancelButton;

    public UserForm(User user) {
        this.user = user;

        // Initialize form components
        usernameField = new TextField("Username");
        emailField = new TextField("Email");
        passwordField = new PasswordField("Password");
        fullNameField = new TextField("Full Name");
        dateOfBirthField = new DateField("Date of Birth");
        genderComboBox = new ComboBox<>("Gender");
        genderComboBox.setItems("Male", "Female", "Other");
        phoneNumberField = new TextField("Phone Number");
        addressField = new TextField("Address");
        saveButton = new Button("Save");
        cancelButton = new Button("Cancel");

        // Set initial values for form fields
        usernameField.setValue(user.getUsername());
        emailField.setValue(user.getEmail());
        passwordField.setValue(user.getPassword());
        fullNameField.setValue(user.getFullName());
        dateOfBirthField.setValue(user.getDateOfBirth());
        genderComboBox.setValue(user.getGender());
        phoneNumberField.setValue(user.getPhoneNumber());
        addressField.setValue(user.getAddress());

        // Add components to the layout
        setComponents(usernameField, emailField, passwordField, fullNameField,
                dateOfBirthField, genderComboBox, phoneNumberField, addressField,
                createButtonLayout());
    }

    private HorizontalLayout createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setSpacing(true);

        saveButton.addClickListener(click -> {
            // Manually validate form fields (e.g., using BeanValidationHelper)
            BeanValidationHelper helper = new BeanValidationHelper(User.class);
            if (helper.validateBean(user).isOk()) {
                // Update the user object with values from form fields
                user.setUsername(usernameField.getValue());
                user.setEmail(emailField.getValue());
                user.setPassword(passwordField.getValue());
                user.setFullName(fullNameField.getValue());
                user.setDateOfBirth(dateOfBirthField.getValue());
                user.setGender(genderComboBox.getValue());
                user.setPhoneNumber(phoneNumberField.getValue());
                user.setAddress(addressField.getValue());

                // Call a method to save the user (explained later)
                saveUser();
            } else {
                // Handle validation errors
                // show error messages or apply styling to indicate errors
            }
        });

        cancelButton.addClickListener(click -> {
            // Handle cancel action (e.g., close the form)
            getWindow().close();
        });

        buttonLayout.addComponents(saveButton, cancelButton);
        return buttonLayout;
    }

    // Method to save the user data (implementation depends on your logic)
    private void saveUser() {
        // Call your service to save the user (e.g., userService.save(user))
        // Handle success or failure scenarios
    }
}
