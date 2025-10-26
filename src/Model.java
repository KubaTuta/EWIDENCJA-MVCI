import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.scene.Node;

public class Model {
    public final StringProperty promptLabelText = new SimpleStringProperty("");
    public final StringProperty inputData = new SimpleStringProperty("");
    public final ListProperty<Node> outputNodes = new SimpleListProperty<>(FXCollections.observableArrayList());



    public final String reg = "Nr rej. / VIN";
    public final String regContent = "Wpisz nr rej. lub VIN, aby wyświetlić parametry pojazdu";
    public final String comments = "Komentarze";
    public final String commentsContent = "Wpisz datę, aby wyświetlić pojazdy z komentarzami z tego dnia (dd.mm.rrrr)";
    public final String invoiceNumbers = "Faktury danego dnia";
    public final String invoiceNumbersContent = "Wpisz datę, aby wyświetlić listę faktur z tego dnia (dd.mm.rrrr)";
}