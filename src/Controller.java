import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Builder;

public class Controller {

    private Builder<Region> viewBuilder;
    private Interactor interactor;
    private Stage primaryStage;

    public Controller(Stage primaryStage) {
        this.primaryStage = primaryStage;

        Model model = new Model();
        interactor = new Interactor(model);
        interactor.setPrimaryStage(primaryStage);
        viewBuilder = new ViewBuilder(model,
                (TopButtonType topButtonType) -> interactor.setLabel(topButtonType),
                () -> interactor.confirmUserInput(),
                interactor::minimize,
                (textFlow)->interactor.showOutput(textFlow));

        model.setCars(interactor.loadCarsFromCsv());
    }

    public Region getView() {
        return viewBuilder.build();
    }
}