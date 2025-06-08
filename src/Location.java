import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private List<String> labels = new ArrayList<>();
    private List<Runnable> actions = new ArrayList<>();

    private static VBox locationBox = new VBox(10);
    private static Button button;

    public static void setLocationBox() {
        locationBox.getStyleClass().add("my-label");
        Main.root.setCenter(locationBox);
    }
    public static VBox getLocationBox(){
        return locationBox;
    }

    public Button setLocationButtonBox(String buttonText){
        button = Essentials.addButton(buttonText);
        return button;

    }
//    public void setOptions(Map<String, Runnable> options) {
//        labels.clear();
//        actions.clear();
//        for (Map.Entry<String, Runnable> entry : options.entrySet()) {
//            labels.add(entry.getKey());
//            actions.add(entry.getValue());
//        }
//    }

    public void prompt() {
        for (int i = 0; i < labels.size(); i++) {
            System.out.println("[ " + (i + 1) + " ] " + labels.get(i));
        }

        //String input = Essentials.getInput(generateExpectedRange());
        String input = "a";
        assert input != null;
        int index = Integer.parseInt(input) - 1;

        if (index >= 0 && index < actions.size()) {
            actions.get(index).run();
        } else {
            System.out.println("Invalid choice.");
            prompt(); // re-loop
        }
    }

    private String generateExpectedRange() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= actions.size(); i++) {
            sb.append(i);
        }
        return sb.toString(); // e.g., "123"
    }
}
