import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class Interactor {
    private final Model model;
    private Stage primaryStage;

    public Interactor(Model model) {
        this.model = model;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setLabel(TopButtonType topButtonType) {
        model.setPromptLabelText(topButtonType.getLabel());
        model.setActiveButton(topButtonType);
        confirmUserInput();
    }

    public void confirmUserInput() {
        List<Node> input = switchBetweenMethods(model.getCars(), model.getInputData());
        model.setOutputNodes(input);

        List<Node> regList = showReg(model.getCars(), model.getInputData());
        model.setOutputReg(regList);
    }

    private List<Node> switchBetweenMethods(List<Car> cars, String input) {
        switch (model.getActiveButton()) {
            case COMBO:
                return comboReader(cars, input);
            case REG:
                return showCarInfo(cars, input);
            case COMMENTS:
                return showDailyComments(cars, input);
            case INVOICES:
                return showDailyInvoiceNumbers(cars, input);
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
        gridLayout.getStyleClass().add("grid-layout");
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
            int row = grid.getChildren().size() / 4;

            Hyperlink fvNumber = new Hyperlink(car.getInvoiceNumber());
            fvNumber.setOnAction(event -> minimize());

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

    public void showOutput(FlowPane output) {
        output.getChildren().clear();
        output.getChildren().addAll(model.getOutputNodes());
    }

    public void showRegOutput(FlowPane output) {
        output.getChildren().clear();
        output.getChildren().addAll(model.getOutputReg());
    }

    public void minimize() {
        primaryStage.setIconified(true);
    }

    public List<Node> showReg(List<Car> cars, String longString) {
        return carGrid(cars, longString, (car, grid) -> {
            int row = grid.getChildren().size();
            grid.add(new Label(car.getRegNumber()), 1, row);
        });
    }
}