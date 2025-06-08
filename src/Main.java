import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static boolean firstRun = true;
    public static Chapter currentChapter = getChapter();
    public static Scene mainScene;
    public static BorderPane root = new BorderPane();


    public static void main(String[] args) throws IOException {
        if (firstRun) {
            firstTimeRun();
        }
        TypeWriter.promptLabel.setOpacity(0);

        launch(args);
        Exit();
    }
    public static void firstTimeRun(){
        setChapter(Chapter.CHAPTER0);
        System.out.println(Main.getChapter());
        PreferencesManager.set("chapter.no", String.valueOf(Chapter.CHAPTER0));
        PreferencesManager.set("charisma", "0");
        PreferencesManager.set("speed", "0");
        PreferencesManager.set("coordination", "0");
        PreferencesManager.set("perception", "0");
        PreferencesManager.save();
    }
    public static void setChapter(Chapter ch){
        currentChapter = Chapter.valueOf(String.valueOf(ch));
    }
    public static Chapter getChapter(){
        return currentChapter;
    }
    public static void Exit(){
        System.out.println(Character.pillsPicked);
        System.out.println("step is: " + Chapter.step);
    }
    @Override
    public void start(Stage stage) throws Exception {
        //scene
        mainScene = new Scene(root,640,400);
        mainScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(mainScene);



        // window settings
        stage.setTitle("The Hollow Trace");
        stage.setResizable(false);
        stage.show();
        Main.getChapter().play();
    }
}
