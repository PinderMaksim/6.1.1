package com.example.demo1;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Модель одного запису в адресній книзі.
 */
public class Contact {

    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty phone = new SimpleStringProperty();

    public Contact() {
    }

    public Contact(String name, String phone) {
        this.name.set(name);
        this.phone.set(phone);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String value) {
        phone.set(value);
    }

    public StringProperty phoneProperty() {
        return phone;
    }
}
