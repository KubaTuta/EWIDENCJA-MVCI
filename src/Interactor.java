import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.CsvReader;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class Interactor {
    private final Model model;
    private static final String FILE_PATH = "C:/JAVA/EWIDENCJA MVCI/src/resources/csv/csv.csv";
    private Stage primaryStage;

    public Interactor(Model model) {
        this.model = model;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
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
        confirmUserInput();
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

    private List<Node> carGrid(List<Car> cars, String longString, BiConsumer<Car, GridPane> rowBuilder) {
        List<Node> carOfInterest = new ArrayList<>();
        Text notFoundedCars = new Text(" Nie znaleziono takiego pojazdu");
        boolean foundedCars = false;

        String[] parts = Hooks.stringSorter(longString);
        GridPane gridLayout = new GridPane();
        int[] rowIndex = {0};

        for (String part : parts) {
            for (Car car : cars) {
                if (car.getRegNumber().equals(part) || car.getVin().equals(part)) {
                    rowBuilder.accept(car, gridLayout);
                    rowIndex[0]++;
                    foundedCars = true;
                }
            }
        }
        if (!foundedCars) {
            carOfInterest.add(notFoundedCars);

        }

        carOfInterest.add(gridLayout);
        return carOfInterest;
    }

    public List<Node> comboReader(List<Car> cars, String input) {
        return carGrid(cars, input, (car, grid) -> {
            int row = grid.getChildren().size() / 5;

            Hyperlink fvNumber = new Hyperlink(car.getInvoiceNumber());
            fvNumber.setOnAction(event -> minimize());

            grid.add(new Label(car.getRegNumber()), 0, row);
            grid.add(fvNumber, 1, row);
            grid.add(new Label(car.getDateOfInvoiceIssue()), 2, row);
            grid.add(new Label(car.getInsurer()), 3, row);
            grid.add(new Label(car.getExpirationDate()), 4, row);
        });
    }

    public List<Node> showCarInfo(List<Car> cars, String longString) {
        return carGrid(cars, longString, (car, grid) -> {
            int row = grid.getChildren().size() / 11;

            grid.add(new Label(car.getVin()), 1, row);
            grid.add(new Label(car.getUserCompanyName()), 2, row);
            grid.add(new Label(car.getDateOfCollection()), 3, row);
            grid.add(new Label(car.getBrand()), 4, row);
            grid.add(new Label(car.getModel()), 5, row);
            grid.add(new Label(car.getBodyType()), 6, row);
            grid.add(new Label(car.getFuelType()), 7, row);
            grid.add(new Label(car.getModel()), 8, row);
            grid.add(new Label(car.getMileage()), 9, row);
            grid.add(new Label(car.getProductionYear()), 10, row);
            grid.add(new Label(car.getFirstRegDate()), 11, row);
        });
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

        GridPane gridLayout = new GridPane();
        gridLayout.getStyleClass().add("gridLayout");
        int rowIndex = 0;

        for (String part : parts) {
            for (Car car : cars) {
                if (car.getRegNumber().equals(part) || car.getVin().equals(part)) {
                    Hyperlink fvNumber = new Hyperlink(car.getInvoiceNumber());
                    fvNumber.setOnAction(event -> minimize());
                    gridLayout.add(new Label(car.getRegNumber()), 0, rowIndex);
                    gridLayout.add(fvNumber, 1, rowIndex);
                    gridLayout.add(new Label(car.getDateOfInvoiceIssue()), 2, rowIndex);
                    gridLayout.add(new Label(car.getInsurer()), 3, rowIndex);
                    gridLayout.add(new Label(car.getExpirationDate()), 4, rowIndex);
                    rowIndex++;
                    foundedCars = true;
                }
            }
        }

        if (!foundedCars) {
            carOfInterest.add(notFoundedCars);

        }
        carOfInterest.add(gridLayout);
        return carOfInterest;
    }

    public void showOutput(FlowPane output) {
        output.getChildren().clear();
        output.getChildren().addAll(model.getOutputNodes());
    }

    public void minimize() {
        primaryStage.setIconified(true);
    }

    public List<Node> showReg() {
        List<Node> regNumber = new ArrayList<>();


        return regNumber;

    }
}