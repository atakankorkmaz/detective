import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class Conclusion {
    int picks = 0;
    ArrayList<Button> buttons = new ArrayList<>();
    public Conclusion(ArrayList<Button> buttonList){
        this.picks = 0;
        this.buttons = buttonList;
    }
    public void chooseConclusion(ArrayList<Integer> disableIndexList){
        for (int i = 0; i < disableIndexList.size(); i++) {
            this.buttons.get(disableIndexList.get(i)).setDisable(true);
            this.buttons.get(disableIndexList.get(i)).setOpacity(0.4);
        }
        picks++;
    }
    public void clickConclusion(String message, String outputMessage,ArrayList<Integer> disableIndexList){
        Chapter.clearBox();
        Label check = TypeWriter.simpleType(message, ()->{


            Button yes = Essentials.addButton("Yes");
            yes.setOnAction(e->{
                Label out = TypeWriter.simpleType(outputMessage,()->{
                    Essentials.pause(3,()->{
                        Chapter.box.getChildren().clear();
                        Chapter.choices.getChildren().addAll(this.buttons);
                        Main.root.setCenter(Chapter.choices);
                    });
                    chooseConclusion(disableIndexList);

                });
                Chapter.clearBox();
                Chapter.box.getChildren().add(out);
                Main.root.setLeft(Chapter.box);
            });
            Button no = Essentials.addButton("No");
            no.setOnAction(e->{
                Chapter.box.getChildren().clear();
                Chapter.choices.getChildren().clear();
                Chapter.choices.getChildren().addAll(this.buttons);
            });

            Chapter.choices.getChildren().addAll(yes,no);
            Main.root.setBottom(Chapter.choices);
        });
        Chapter.box.getChildren().add(check);
        Main.root.setLeft(Chapter.box);
    }
    public int getPickCount(){
        return this.picks;
    }
    public ArrayList<Button> getList(){
        return this.buttons;
    }
}
