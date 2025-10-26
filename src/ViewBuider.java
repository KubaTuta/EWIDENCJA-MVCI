import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

class ViewBuilder implements Builder<Region> {

    private final Model model;
    public ViewBuilder(Model model) {
        this.model = model;
    }

    @Override
    public Region build() {
        return new VBox();
    }
}