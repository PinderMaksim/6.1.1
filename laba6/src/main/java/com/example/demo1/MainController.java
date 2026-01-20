package com.example.demo1;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * Головний контролер: робота з колекцією контактів та вивід у TableView.
 */
public class MainController {

    @FXML
    private TableView<Contact> tableView;

    @FXML
    private TableColumn<Contact, String> nameColumn;

    @FXML
    private TableColumn<Contact, String> phoneColumn;

    @FXML
    private TextField searchField;

    @FXML
    private Label countLabel;

    private final ContactRepository repository = new MemoryContactRepository();
    private FilteredList<Contact> filteredList;

    @FXML
    private void initialize() {
        // Колонки таблиці
        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
        phoneColumn.setCellValueFactory(data -> data.getValue().phoneProperty());

        // Колекція + обгортка для фільтрації
        filteredList = new FilteredList<>(repository.getAll(), c -> true);
        SortedList<Contact> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);

        // Пошук по ПІП та телефону
        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            String query = newVal == null ? "" : newVal.trim().toLowerCase();
            filteredList.setPredicate(contact -> {
                if (query.isEmpty()) return true;
                return contact.getName().toLowerCase().contains(query)
                        || contact.getPhone().toLowerCase().contains(query);
            });
            updateCount();
        });

        updateCount();
    }

    @FXML
    private void onAdd() {
        Contact contact = new Contact();
        boolean ok = showContactDialog(contact, "Додати запис");
        if (ok) {
            repository.add(contact);
            updateCount();
        }
    }

    @FXML
    private void onEdit() {
        Contact selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showInfo("Редагування", "Оберіть запис у таблиці для редагування.");
            return;
        }

        // Копія, щоб можна було скасувати зміни
        Contact temp = new Contact(selected.getName(), selected.getPhone());
        boolean ok = showContactDialog(temp, "Редагувати запис");
        if (ok) {
            selected.setName(temp.getName());
            selected.setPhone(temp.getPhone());
            tableView.refresh();
        }
    }

    @FXML
    private void onDelete() {
        Contact selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showInfo("Видалення", "Оберіть запис у таблиці для видалення.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Видалення");
        confirm.setHeaderText("Видалити вибраний запис?");
        confirm.setContentText(selected.getName() + " (" + selected.getPhone() + ")");
        confirm.initOwner(HelloApplication.getPrimaryStage());

        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            repository.remove(selected);
            updateCount();
        }
    }

    @FXML
    private void onSearch() {
        // Пошук вже працює автоматично при введенні тексту.
        showInfo("Пошук", "Пошук працює автоматично під час введення тексту в поле.");
    }

    @FXML
private void onOtherLabs() {
    try {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("other-labs.fxml"));
        Scene scene = new Scene(loader.load());

        Stage s = new Stage();
        s.setTitle("Other Labs — Практична робота №6");
        s.initOwner(HelloApplication.getPrimaryStage());
        s.initModality(Modality.WINDOW_MODAL);
        s.setResizable(false);
        s.setScene(scene);
        s.showAndWait();
    } catch (IOException e) {
        showError("Не вдалося відкрити Other Labs: " + e.getMessage());
    }
}

@FXML

    private void onExit() {
        Stage stage = HelloApplication.getPrimaryStage();
        if (stage != null) {
            stage.close();
        }
    }

    /**
     * Відкриває модальне вікно редагування контакту.
     * Передає туди об'єкт Contact та отримує назад змінені дані.
     */
    private boolean showContactDialog(Contact contact, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("e1.fxml"));
            Scene scene = new Scene(loader.load());

            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initOwner(HelloApplication.getPrimaryStage());
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setResizable(false);
            dialogStage.setScene(scene);

            ModalController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setContact(contact);

            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            showError("Не вдалося відкрити вікно редагування: " + e.getMessage());
            return false;
        }
    }

    private void updateCount() {
        if (countLabel != null && filteredList != null) {
            countLabel.setText("Кількість записів: " + filteredList.size());
        }
    }

    private void showInfo(String title, String text) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(text);
        a.initOwner(HelloApplication.getPrimaryStage());
        a.showAndWait();
    }

    private void showError(String text) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Помилка");
        a.setHeaderText(null);
        a.setContentText(text);
        a.initOwner(HelloApplication.getPrimaryStage());
        a.showAndWait();
    }
}
