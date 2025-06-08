import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Essentials{

    static Separator divider = new Separator(Orientation.VERTICAL);
    public static void getInput(){
        Scene scene = Main.mainScene;
        if (scene != null) {
            scene.setOnKeyPressed(keyEvent -> {
                if (keyEvent.getCode() == KeyCode.SPACE) {
                    scene.setOnKeyPressed(null);
                    TypeWriter.promptLabel.setOpacity(0);
                    Main.getChapter().next();
                }
            });
        }
    }
    public static void openInventory(){
        Stage stage = (Stage) Main.root.getScene().getWindow();
        stage.setWidth(stage.getWidth() + 250);

        VBox inventoryPane = new VBox(10);
        inventoryPane.getChildren().addAll(
                new Label("Inventory"),
                new Label("Pills")
        );
        // Separator to visually divide the inventory from the main content
        divider.setPrefWidth(2);            // thickness
        divider.setStyle("-fx-background-color: #666;");  // color

        // Container holds the divider and the inventory panel
        HBox centerContent = new HBox(divider, inventoryPane);
        centerContent.setSpacing(10);
        centerContent.setAlignment(Pos.CENTER_LEFT);

        inventoryPane.getStyleClass().add("my-label");

        // Place the inventory with the divider on the right side
        Main.root.setRight(centerContent);
    }
    public static Button addButton(String buttonText){
        Button button = new Button(buttonText);
        button.getStyleClass().add("choice-button");
        return button;
    }
    public static void pause(int durationSeconds, Runnable run){
        PauseTransition pause = new PauseTransition(Duration.seconds(durationSeconds));
        pause.setOnFinished(pauseEvent->{
            //run runnables
            run.run();
        });
        pause.play();
    }
    public static Label choice(String text,Runnable onFinished){
        Label label = new Label();
        label.getStyleClass().add("my-label");
        Timeline typingTimeline = new Timeline();
        StringBuilder sb = new StringBuilder();

        // Typing animation
        for (int i = 0; i < text.length(); i++) {
            final int index = i;
            KeyFrame kf = new KeyFrame(Duration.millis(TypeWriter.delay * (index + 2)), e -> {
                sb.append(text.charAt(index));
                label.setText(sb.toString());
            });
            typingTimeline.getKeyFrames().add(kf);
            typingTimeline.setOnFinished(event -> {
                onFinished.run();
            });
        }
        typingTimeline.play();
        return label;
    }
    public static void buttonOutput(String output){
        Chapter.choices.getChildren().clear();
        Label label = new Label();
        label.getStyleClass().add("my-label");
        Timeline typingTimeline = new Timeline();
        StringBuilder sb = new StringBuilder();

        TypeWriter.promptLabel.getStyleClass().add("press-continue");

        // Typing animation
        for (int i = 0; i < output.length(); i++) {
            final int index = i;
            KeyFrame kf = new KeyFrame(Duration.millis(TypeWriter.delay * (index + 1)), e -> {
                sb.append(output.charAt(index));
                label.setText(sb.toString());
            });
            typingTimeline.getKeyFrames().add(kf);
        }
        Chapter.box.getChildren().add(label);
        typingTimeline.setOnFinished(ev -> {
            // Fade in prompt when dots are done
            Chapter.box.getChildren().addLast(TypeWriter.promptLabel);
            TypeWriter.promptLabel.setOpacity(0);
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), TypeWriter.promptLabel);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.setOnFinished(eve -> {
                Essentials.getInput();
            });
            fadeIn.play();
        });
        typingTimeline.play();
    }
    public static void choiceProgress(){
        Main.getChapter().next();
    }

}