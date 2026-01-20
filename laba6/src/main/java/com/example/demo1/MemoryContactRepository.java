package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Сховище контактів у пам'яті (колекція ObservableList).
 * Без початкових демо-даних.
 */
public class MemoryContactRepository implements ContactRepository {

    private final ObservableList<Contact> contacts = FXCollections.observableArrayList();

    public MemoryContactRepository() {
        // Порожній список при запуску
    }

    @Override
    public ObservableList<Contact> getAll() {
        return contacts;
    }

    @Override
    public void add(Contact contact) {
        contacts.add(contact);
    }

    @Override
    public void remove(Contact contact) {
        contacts.remove(contact);
    }
}
