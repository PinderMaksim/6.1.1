package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Контролер модального вікна додавання/редагування контакту.
 * Тут ми працюємо з окремим Contact, який передається з головного контролера.
 */
public class ModalController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    private Stage dialogStage;
    private Contact contact;
    private boolean okClicked = false;

    public void setDialogStage(Stage stage) {
        this.dialogStage = stage;
    }

    /**
     * Передача даних з головного контролера в модальний.
     */
    public void setContact(Contact contact) {
        this.contact = contact;
        if (contact != null) {
            nameField.setText(contact.getName());
            phoneField.setText(contact.getPhone());
        }
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void onOk(ActionEvent event) {
        if (contact != null) {
            contact.setName(nameField.getText());
            contact.setPhone(phoneField.getText());
        }
        okClicked = true;
        dialogStage.close();
    }

    @FXML
    private void onCancel(ActionEvent event) {
        dialogStage.close();
    }
}
