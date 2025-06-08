import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class TypeWriter {
    protected static int delay = 60;
    protected static int stopDelay = 500;
    public static Label promptLabel = new Label("Press SPACE to continue...");
    public static Label simpleType(String text, Runnable run){
        Label label = new Label();
        label.getStyleClass().add("my-label");
        Timeline typingTimeline = new Timeline();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            final int index = i;
            KeyFrame kf = new KeyFrame(Duration.millis(1 * (index + 1)), e -> {
                sb.append(text.charAt(index));
                label.setText(sb.toString());
            });
            typingTimeline.getKeyFrames().add(kf);
        }
        typingTimeline.setOnFinished(e -> run.run());

        typingTimeline.setDelay(Duration.millis(600));
        typingTimeline.play();
        return label;
    }
    public static Label type(String text, boolean willStop) {
        Label label = new Label();
        label.getStyleClass().add("my-label");
        Timeline typingTimeline = new Timeline();
        StringBuilder sb = new StringBuilder();
        promptLabel.getStyleClass().add("press-continue");

        // Typing animation
        for (int i = 0; i < text.length(); i++) {
            final int index = i;
            KeyFrame kf = new KeyFrame(Duration.millis(1 * (index + 1)), e -> {
                sb.append(text.charAt(index));
                label.setText(sb.toString());
            });
            typingTimeline.getKeyFrames().add(kf);
        }

        typingTimeline.setDelay(Duration.millis(2200));

        typingTimeline.setOnFinished(e -> {
            if (willStop) {
                // Dot animation is in a separate timeline
                Timeline dotsTimeline = new Timeline();
                String dots = "\n.\n.\n.";
                for (int i = 0; i < dots.length(); i++) {
                    final int index = i;
                    KeyFrame kf = new KeyFrame(Duration.millis(stopDelay * (index + 0.3)), ev -> {
                        sb.append(dots.charAt(index));
                        label.setText(sb.toString());
                    });
                    dotsTimeline.getKeyFrames().add(kf);
                }
                dotsTimeline.setOnFinished(ev -> {
                    // Fade in prompt when dots are done
                    Chapter.box.getChildren().addLast(promptLabel);
                    promptLabel.setOpacity(0);
                    FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), promptLabel);
                    fadeIn.setFromValue(0.0);
                    fadeIn.setToValue(1.0);
                    fadeIn.setOnFinished(eve -> {
                        Essentials.getInput();
                    });
                    fadeIn.play();
                });
                dotsTimeline.play();
            }
            else {
                FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), promptLabel);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.setOnFinished(eve -> {
                    Essentials.getInput();
                });
                fadeIn.play();
            }
        });
        typingTimeline.play();
        return label;
    }
    public static void input(){
        Chapter.box.getChildren().add(promptLabel);
        promptLabel.getStyleClass().add("press-continue");
        // will get input here
        BorderPane borderPane = new BorderPane();
        borderPane.setBottom(promptLabel);
    }
}
