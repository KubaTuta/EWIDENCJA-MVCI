import utils.CsvReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Interactor {
    private Model model;
    private static final String FILE_PATH = "C:/JAVA/EWIDENCJA MVCI/src/resources/csv/csv.csv";

    public Interactor(Model model)  {
        this.model = model;
    }

    public List<Car> loadCarsFromCsv() {
        Path path = Paths.get(FILE_PATH);
        List<String[]> rows = CsvReader.readCsvFile(path);
        List<Car> cars = new ArrayList<>();

        for (String[] row : rows) {
            Car car = new Car(row);
            cars.add(car);
        }
        return cars;
    }

    public void setLabel(String title, String label) {
        model.setPromptLabelText(label);
        model.setActiveButton(title);
    }

    public void confirmUserInput() {
        List<Node> input = switchBetweenMethods(model.getCars(), model.getInputData());
        model.setOutputNodes(input);
    }

    private List<Node> switchBetweenMethods(List<Car> cars, String input) {
        switch (model.getActiveButton()) {
//            case reg:
//                return CarMethods.showCarOfInterest(cars, input);
//            case comments:
//                return CarMethods.showDailyComments(cars, input);
            case "Faktury danego dnia":
                return showDailyInvoiceNumbers(cars, input);
            default:
                return new ArrayList<>();
        }
    }
    private static List<Node> showDailyInvoiceNumbers(List<Car> cars, String date) {
        List<Node> listOfInvoiceNumbers = new ArrayList<>();
        Text sb = new Text("Numery faktur z dnia " + date + ":\n");
        for (Car car : cars) {
            if (car.dateOfInvoiceIssue.equals(date)) {
                sb.setText(sb.getText() + car.invoiceNumber + ",");
            }
        }
        listOfInvoiceNumbers.add(sb);
        return listOfInvoiceNumbers;
    }

    public void showOutput(TextFlow output) {

        output.getChildren().clear();
        output.getChildren().addAll(model.getOutputNodes());
    }
}