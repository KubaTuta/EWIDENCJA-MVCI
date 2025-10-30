import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import utils.CsvReader;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Interactor {
    private final Model model;
    private static final String FILE_PATH = "C:/JAVA/EWIDENCJA MVCI/src/resources/csv/csv.csv";

    public Interactor(Model model) {
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

    public void setLabel(TopButtonType topButtonType) {
        model.setPromptLabelText(topButtonType.getLabel());
        model.setActiveButton(topButtonType);
    }

    public void confirmUserInput() {
        List<Node> input = switchBetweenMethods(model.getCars(), model.getInputData());
        model.setOutputNodes(input);
    }

    private List<Node> switchBetweenMethods(List<Car> cars, String input) {
        switch (model.getActiveButton()) {
            case REG:
                return showCarOfInterest(cars, input);
            case COMMENTS:
                return showDailyComments(cars, input);
            case INVOICES:
                return showDailyInvoiceNumbers(cars, input);
            case COMBO:
                return comboReader(cars, input);
            default:
                return new ArrayList<>();
        }
    }

    public static List<Node> showCarOfInterest(List<Car> cars, String reg) {
        List<Node> carOfInterest = new ArrayList<>();
        Text foundedCar = new Text(" Nie znaleziono takiego pojazdu");
        for (Car car : cars) {
            if (car.getRegNumber().equals(reg.toUpperCase().trim()) || car.getVin().equals(reg.toUpperCase().trim())) {
                foundedCar.setText(car.allAttributeNames());
                carOfInterest.add(foundedCar);
                return carOfInterest;
            }
        }
        carOfInterest.add(foundedCar);
        return carOfInterest;
    }

    public static List<Node> showDailyComments(List<Car> cars, String date) {
        List<Node> dailyComments = new ArrayList<>();
        Text dailyCommentsText = new Text("");
        for (Car car : cars) {
            if (car.getDateOfInvoiceIssue().equals(date) && !car.getComment().isEmpty()) {
                dailyCommentsText.setText(dailyCommentsText.getText() + car.getRegNumber() + ": " + car.getComment() + "\n");
            }
        }
        dailyComments.add(dailyCommentsText);
        return dailyComments;
    }

    private static List<Node> showDailyInvoiceNumbers(List<Car> cars, String date) {
        List<Node> listOfInvoiceNumbers = new ArrayList<>();
        Text listOfInvoiceNumbersText = new Text("Numery faktur z dnia " + date + ":\n");
        for (Car car : cars) {
            if (car.getDateOfInvoiceIssue().equals(date)) {
                listOfInvoiceNumbersText.setText(listOfInvoiceNumbersText.getText() + car.getInvoiceNumber() + ",");
            }
        }
        listOfInvoiceNumbers.add(listOfInvoiceNumbersText);
        return listOfInvoiceNumbers;
    }

    public static List<Node> comboReader(List<Car> cars, String longString) {
        List<Node> carOfInterest = new ArrayList<>();
        Text notFoundedCars = new Text(" Nie znaleziono żadnych pojazdów");
        boolean foundedCars = false;

        String[] parts = Hooks.stringSorter(longString);

        for (String part : parts) {
            for (Car car : cars) {
                if (car.getRegNumber().equals(part) || car.getVin().equals(part)) {
                    carOfInterest.add(new Text(car.getRegNumber() + "\n"));
                    foundedCars = true;
                }
            }
        }

        if (!foundedCars) {
            carOfInterest.add(notFoundedCars);

        }
        return carOfInterest;
    }

    public void showOutput(TextFlow output) {
        output.getChildren().clear();
        output.getChildren().addAll(model.getOutputNodes());
    }
}