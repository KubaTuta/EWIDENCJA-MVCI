import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Builder;

public class Controller {

    private final Builder<Region> viewBuilder;
    private final Interactor interactor;
    private final Stage primaryStage;

    public Controller(Stage primaryStage) {
        this.primaryStage = primaryStage;

        Model model = new Model();
        interactor = new Interactor(model);
        interactor.setPrimaryStage(primaryStage);
        viewBuilder = new ViewBuilder(model,
                interactor::setLabel,
                interactor::confirmUserInput,
                interactor::minimize,
                interactor::showOutput,
                interactor::showRegOutput);

        model.setCars(interactor.loadCarsFromCsv());
    }

    public Region getView() {
        return viewBuilder.build();
    }
}