import javafx.scene.layout.Region;
import javafx.util.Builder;

public class Controller {

    private Builder<Region> viewBuilder;
    private Interactor interactor;

    public Controller() {
        Model model = new Model();
        interactor = new Interactor(model);
        viewBuilder = new ViewBuilder(model,
                (TopButtonType topButtonType) -> interactor.setLabel(topButtonType),
                () -> interactor.confirmUserInput(),
                (textFlow)->interactor.showOutput(textFlow));

        model.setCars(interactor.loadCarsFromCsv());
    }

    public Region getView() {
        return viewBuilder.build();
    }
}