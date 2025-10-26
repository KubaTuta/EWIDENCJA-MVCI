import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.util.Builder;

import java.util.List;

class ViewBuilder implements Builder<Region> {

    private final Model model;
    public ViewBuilder(Model model) {
        this.model = model;
    }

    @Override
    public Region build() {
        VBox wholeView = new VBox(createTopButtons(), createPromptLabel(), createInputHorizontalArea(), createFeedbackLabel());
        wholeView.getStylesheets().add(this.getClass().getResource("/resources/css/styled.css").toExternalForm());
        return wholeView;
    }

    private Node createTopButtons() {
        HBox topButtons = new HBox(createTopUniversalButton(model.getReg(), model.getRegContent()),
                createTopUniversalButton(model.getComments(), model.getCommentsContent()),
                createTopUniversalButton(model.getInvoiceNumbers(), model.getInvoiceNumbersContent()));
        topButtons.setSpacing(10);
        topButtons.setPadding(new Insets(10));
        topButtons.setAlignment(Pos.BASELINE_CENTER);
        return topButtons;
    }

    private Node createTopUniversalButton(String title, String labelSetter) {
        Button button = new Button(title);
        button.getStyleClass().add("top-button");
//        button.setOnAction(e -> {
//            promptLabelText.set(labelSetter);
//            activeButtonType = title;
//        });
        return button;
    }

    private Node createPromptLabel() {
        Label promptLabel = new Label("");
//        promptLabel.getStyleClass().add("prompt-label");
        promptLabel.textProperty().bind(model.getPromptLabelTextProperty());
        return promptLabel;
    }

    private Node createInputHorizontalArea() {
        HBox hBox = new HBox(createInputField(), createConfirmationButton());
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        return hBox;
    }

    private Node createInputField() {
        TextField inputFieldLabel = new TextField("");
        inputFieldLabel.textProperty().bindBidirectional(model.getInputDataProperty());
        return inputFieldLabel;
    }

    private Node createConfirmationButton() {
        Button confirmationButton = new Button("OK");
        confirmationButton.getStyleClass().add("confirmation-button");
        HBox wrapper = new HBox(confirmationButton);
        wrapper.setAlignment(Pos.CENTER);

//        confirmationButton.setOnAction(e -> {
//            List<Node> input = switchBetweenMethods(cars, inputData.get());
//            outputNodes.setAll(input);
//        });
        return wrapper;
    }

    private Node createFeedbackLabel() {
        TextFlow feedbackTextFlow = new TextFlow();
        feedbackTextFlow.setPadding(new Insets(10));
        feedbackTextFlow.getStyleClass().add("feedback-label");
        VBox.setVgrow(feedbackTextFlow, Priority.ALWAYS);

//        outputNodes.addListener((ListChangeListener<Node>) change -> {
//            feedbackTextFlow.getChildren().clear();
//            feedbackTextFlow.getChildren().addAll(outputNodes);
//        });

        return feedbackTextFlow;
    }



}