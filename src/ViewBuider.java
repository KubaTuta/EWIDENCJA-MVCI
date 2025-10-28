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

import java.util.function.BiConsumer;
import java.util.function.Consumer;

class ViewBuilder implements Builder<Region> {

    private final Model model;
    private final Consumer<TopButtonType> promptLabelHandler;
    private final Runnable confirmHandler;
    private final Consumer<TextFlow> outputHandler;

    public ViewBuilder(Model model, Consumer<TopButtonType> promptLabelHandler, Runnable confirmHandler, Consumer<TextFlow> outputHandler) {
        this.model = model;
        this.promptLabelHandler = promptLabelHandler;
        this.confirmHandler = confirmHandler;
        this.outputHandler = outputHandler;
    }

    @Override
    public Region build() {
        VBox wholeView = new VBox(createTopButtons(), createPromptLabel(), createInputHorizontalArea(), createFeedbackLabel());
        wholeView.getStylesheets().add(this.getClass().getResource("/resources/css/styled.css").toExternalForm());
        return wholeView;
    }

    private Node createTopButtons() {
        HBox topButtons = new HBox(createTopUniversalButton(TopButtonType.REG),
                createTopUniversalButton(TopButtonType.COMMENTS),
                createTopUniversalButton(TopButtonType.INVOICES));
        topButtons.setSpacing(10);
        topButtons.setPadding(new Insets(10));
        topButtons.setAlignment(Pos.BASELINE_CENTER);
        return topButtons;
    }

    private Node createTopUniversalButton(TopButtonType topButtonType) {
        Button button = new Button(topButtonType.getTitle());
        button.getStyleClass().add("top-button");
        button.setOnAction(event -> promptLabelHandler.accept(topButtonType));
        return button;
    }

    private Node createPromptLabel() {
        Label promptLabel = new Label("");
        promptLabel.getStyleClass().add("prompt-label");
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

        confirmationButton.setOnAction(event -> confirmHandler.run());
        return wrapper;
    }

    private Node createFeedbackLabel() {
        TextFlow feedbackTextFlow = new TextFlow();
        feedbackTextFlow.setPadding(new Insets(10));
        feedbackTextFlow.getStyleClass().add("feedback-label");
        VBox.setVgrow(feedbackTextFlow, Priority.ALWAYS);
        model.getOutputNodes().addListener((ListChangeListener<Node>) change -> outputHandler.accept(feedbackTextFlow));
        return feedbackTextFlow;
    }
}