package com.example.demo1;

import javafx.collections.ObservableList;

/**
 * Інтерфейс доступу до колекції контактів.
 * Використовується в MainController, реалізується класом MemoryContactRepository.
 */
public interface ContactRepository {

    ObservableList<Contact> getAll();

    void add(Contact contact);

    void remove(Contact contact);
}
