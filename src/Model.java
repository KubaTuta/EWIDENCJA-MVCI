import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import java.util.List;

public class Model {
    private final ObservableList<Car> cars = FXCollections.observableArrayList();
    private final StringProperty promptLabelText = new SimpleStringProperty("");
    private final StringProperty inputData = new SimpleStringProperty("");
    private final ListProperty<Node> outputNodes = new SimpleListProperty<>(FXCollections.observableArrayList());
    private String activeButton = "";

    private final String reg = "Nr rej. / VIN";
    private final String regContent = "Wpisz nr rej. lub VIN, aby wyświetlić parametry pojazdu";
    private final String comments = "Komentarze";
    private final String commentsContent = "Wpisz datę, aby wyświetlić pojazdy z komentarzami z tego dnia (dd.mm.rrrr)";
    private final String invoiceNumbers = "Faktury danego dnia";
    private final String invoiceNumbersContent = "Wpisz datę, aby wyświetlić listę faktur z tego dnia (dd.mm.rrrr)";

    public ObservableList<Car> getCars() {
        return cars;
    }
    public void setCars(List<Car> newCars) {
        this.cars.addAll(newCars);
    }
    public String getActiveButton() {
        return activeButton;
    }
    public void setActiveButton(String activeButton) {
        this.activeButton = activeButton;
    }
    public String getPromptLabelText() {
        return promptLabelText.get();
    }
    public void setPromptLabelText(String promptLabelText) {
        this.promptLabelText.set(promptLabelText);
    }
    public StringProperty getPromptLabelTextProperty() {
        return promptLabelText;
    }
    public String getInputData() {
        return inputData.get();
    }
    public StringProperty getInputDataProperty() {
        return inputData;
    }
    public ObservableList<Node> getOutputNodes() {
        return outputNodes.get();
    }
    public void setOutputNodes(List<Node> newOutputNodes) {
        this.outputNodes.setAll(newOutputNodes);
    }
    public String getReg() {
        return reg;
    }
    public String getRegContent() {
        return regContent;
    }
    public String getComments() {
        return comments;
    }
    public String getCommentsContent() {
        return commentsContent;
    }
    public String getInvoiceNumbers() {
        return invoiceNumbers;
    }
    public String getInvoiceNumbersContent() {
        return invoiceNumbersContent;
    }
}