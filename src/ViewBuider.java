import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.util.Builder;

import java.util.function.Consumer;

class ViewBuilder implements Builder<Region> {

    private final Model model;
    private final Consumer<TopButtonType> promptLabelHandler;
    private final Runnable confirmHandler;
    private final Runnable minimize;
    private final Consumer<VBox> outputHandler;

    public ViewBuilder(Model model, Consumer<TopButtonType> promptLabelHandler, Runnable confirmHandler, Runnable minimize, Consumer<VBox> outputHandler) {
        this.model = model;
        this.promptLabelHandler = promptLabelHandler;
        this.confirmHandler = confirmHandler;
        this.outputHandler = outputHandler;
    }

    @Override
    public Region build() {
        VBox layout = new VBox(createTopButtons(), createPromptLabel(), createInputHorizontalArea(), createFeedbackLabel());
        layout.setSpacing(10);
        layout.getStylesheets().add(this.getClass().getResource("/resources/css/styled.css").toExternalForm());
        return layout;
    }

    private Node createTopButtons() {
        HBox topButtonsBox = new HBox();

        for (TopButtonType type : TopButtonType.values()) {
            topButtonsBox.getChildren().add(createTopUniversalButton(type)) ;
        }

        topButtonsBox.setSpacing(10);
        topButtonsBox.setPadding(new Insets(10));
        topButtonsBox.setAlignment(Pos.BASELINE_CENTER);
        return topButtonsBox;
    }

    private Node createTopUniversalButton(TopButtonType topButtonType) {
        Button button = new Button(topButtonType.getTitle());
        button.getStyleClass().add("top-button");
        button.setOnAction(event -> promptLabelHandler.accept(topButtonType));
        return button;
    }

    private Node createPromptLabel() {
        Label promptLabel = new Label("");
        VBox promptLabelBox = new VBox(promptLabel);
        promptLabel.getStyleClass().add("prompt-label");
        promptLabelBox.setAlignment(Pos.CENTER);
        promptLabel.textProperty().bind(model.getPromptLabelTextProperty());
        return promptLabelBox;
    }

    private Node createInputHorizontalArea() {
        HBox hBox = new HBox(createInputField(), createConfirmationButton());
        hBox.getStyleClass().add("input-horizontal-area");
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        return hBox;
    }

    private Node createInputField() {
        TextArea inputFieldLabel = new TextArea("");
        inputFieldLabel.getStyleClass().add("input-field");
        inputFieldLabel.textProperty().bindBidirectional(model.getInputDataProperty());
        return inputFieldLabel;
    }

    private Node createConfirmationButton() {
        Button confirmationButton = new Button("OK");
        confirmationButton.getStyleClass().add("confirmation-button");
        HBox wrapper = new HBox(confirmationButton);
        wrapper.setAlignment(Pos.CENTER);

        confirmationButton.setOnAction(event -> confirmHandler.run());
        return wrapper;
    }

    private Node createFeedbackLabel() {
        VBox feedbackTextFlow = new VBox();
        feedbackTextFlow.setPadding(new Insets(10));
        feedbackTextFlow.getStyleClass().add("feedback-label");
        VBox.setVgrow(feedbackTextFlow, Priority.ALWAYS);
        model.getOutputNodes().addListener((ListChangeListener<Node>) change -> outputHandler.accept(feedbackTextFlow));
        return feedbackTextFlow;
    }

    private Node createMinizeButton() {
        Button minizeButton = new Button("Minize");
        minizeButton.getStyleClass().add("minize-button");
        minizeButton.setOnAction(event -> minimize.run());
        return minizeButton;
    }
}