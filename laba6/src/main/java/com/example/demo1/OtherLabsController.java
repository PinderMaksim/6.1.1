package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;

/**
 * Практична робота №6: Buttons + CheckBox/ChoiceBox/ComboBox/RadioButton + Menu/ContextMenu.
 * Логіка: перевірка відповідей і підрахунок кількості правильних.
 */
public class OtherLabsController {

    @FXML private CheckBox cbJavaFX;
    @FXML private CheckBox cbSwing;
    @FXML private CheckBox cbQt;

    @FXML private ChoiceBox<String> choiceOs;
    @FXML private ComboBox<String> comboProtocol;

    @FXML private RadioButton rbTcp;
    @FXML private RadioButton rbUdp;
    @FXML private RadioButton rbIcmp;

    @FXML private Label resultLabel;

    @FXML private Label headerLabel;

    private ToggleGroup transportGroup;

    @FXML
    private void initialize() {
        // ChoiceBox / ComboBox наповнення
        choiceOs.setItems(FXCollections.observableArrayList("Windows", "Linux", "macOS"));
        choiceOs.getSelectionModel().selectFirst();

        comboProtocol.setItems(FXCollections.observableArrayList("HTTP", "HTTPS", "FTP", "SSH"));
        comboProtocol.getSelectionModel().select("HTTPS");

        // RadioButton group
        transportGroup = new ToggleGroup();
        rbTcp.setToggleGroup(transportGroup);
        rbUdp.setToggleGroup(transportGroup);
        rbIcmp.setToggleGroup(transportGroup);
        rbTcp.setSelected(true);

        resultLabel.setText("Відповіді ще не перевірялися.");
    }

    @FXML
    private void onCheckAnswers(ActionEvent event) {
        int correct = 0;

        // Q1: Які UI-фреймворки Java? (правильні: JavaFX + Swing)
        boolean q1 = cbJavaFX.isSelected() && cbSwing.isSelected() && !cbQt.isSelected();
        if (q1) correct++;

        // Q2: Яка ОС найчастіше використовується в серверних середовищах? (умовно: Linux)
        boolean q2 = "Linux".equals(choiceOs.getValue());
        if (q2) correct++;

        // Q3: Який протокол шифрує веб-з'єднання? (HTTPS)
        boolean q3 = "HTTPS".equals(comboProtocol.getValue());
        if (q3) correct++;

        // Q4: Який протокол є транспортним та забезпечує надійну доставку? (TCP)
        Toggle selected = transportGroup.getSelectedToggle();
        boolean q4 = selected == rbTcp;
        if (q4) correct++;

        resultLabel.setText("Кількість правильних відповідей: " + correct + " з 4");
    }

    @FXML
    private void onReset(ActionEvent event) {
        cbJavaFX.setSelected(false);
        cbSwing.setSelected(false);
        cbQt.setSelected(false);

        choiceOs.getSelectionModel().selectFirst();
        comboProtocol.getSelectionModel().select("HTTPS");

        rbTcp.setSelected(true);

        resultLabel.setText("Скинуто. Відповіді ще не перевірялися.");
    }

    @FXML
    private void onClose(ActionEvent event) {
        // Закрити поточне вікно
        if (headerLabel != null) {
            headerLabel.getScene().getWindow().hide();
        }
    }

    // MenuBar actions
    @FXML private void onMenuCheck() { onCheckAnswers(new ActionEvent()); }
    @FXML private void onMenuReset() { onReset(new ActionEvent()); }
    @FXML private void onMenuAbout() {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Про роботу");
        a.setHeaderText("Практична робота №6");
        a.setContentText("Контроли: CheckBox, ChoiceBox, ComboBox, RadioButton. "
                + "Підрахунок правильних відповідей + меню та контекстне меню.");
        a.initOwner(headerLabel.getScene().getWindow());
        a.showAndWait();
    }

    // Context menu on header label (optional handler)
    @FXML
    private void onHeaderContextMenu(ContextMenuEvent event) {
        // ContextMenu already attached in FXML; this method can stay empty.
    }
}
