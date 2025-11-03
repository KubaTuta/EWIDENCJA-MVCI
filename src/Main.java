import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Font.loadFont(getClass().getResourceAsStream("/resources/font/Genos-Regular.ttf"), 10);
        Scene scene = new Scene(new Controller(primaryStage).getView());
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("EWIDENCJA Remarketing");
        primaryStage.show();
        primaryStage.setAlwaysOnTop(true);
    }
}