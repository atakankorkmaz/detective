import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Chapter {

    CHAPTER0{

        // every chapter has steps controlling our current text and state
        public void play() {
            Main.root.setLeft(box);
            if (step == 0) {
                System.out.println("Step " + step +" is running...");
                Label introText = TypeWriter.type("You wake up in a dim motel room. Sunlight seeps weakly through half-closed blinds, " +
                        "painting faint stripes across the stained ceiling. Your vision is blurry. Your head... worse", true);
                introText.getStyleClass().add("my-label");
                box.getChildren().add(introText);

                // Dirty implementation of SHIT!
                PauseTransition wait = new PauseTransition(Duration.seconds(15.3));
                wait.setOnFinished(e-> {
                    Label shit = new Label("SHIT!\n");
                    shit.getStyleClass().add("my-label");
                    box.getChildren().add(shit);
                });
                wait.play();
                } else if (step==1) {
                System.out.println("Step " + step +" is running...");
                Label introText = TypeWriter.type("Your skull feels like it’s splitting open. Instinctively, " +
//"your hand goes to your temple—and comes back damp. There's a bit of blood, just enough to sting. " +
//"You stay still for a moment, breathing, swallowing down nausea. Then you stumble upright and rush to the " +
//"bathroom.You vomit. Nothing but bile. You rinse your mouth, grip the sink. The mirror stares back—and what " +
//"you see barely registers... \nA pale woman in her early 30s. Long black hair. Hollow eyes. But it’s not you. " +
//"It can’t be. You don’t recognize her face. \nThe pain surges again, deeper now—not just in your skull, " +
//"but behind your eyes. Worse than that... you realize you don’t remember who you are.You splash your " +
"face again, shut your eyes tight, and try to claw your way back to a memory. Anything... Something...",
false);
                Main.root.setLeft(box);
                box.getChildren().add(introText);
                box.getChildren().add(TypeWriter.promptLabel);
            }
            else if (step == 2) {
                Label tut = TypeWriter.type("\n __________Tutorial__________\n" +
"Choices in this game will affect the story, you will have to make choices in order to progress in story. " +
"\n(?) sign will appear when you have to make a choice" +
"\n(?!) when you have to make a choice quickly. " +
"\n(L) when you are in an intractable location." +
"\n__________________________", false);
            Essentials.getInput();
            Main.root.setLeft(box);
            box.getChildren().add(tut);
            } else if (step==3) {
                // Choice question
                Label choicePrompt = Essentials.choice("You try to come up with a memory of (?): ",()->{
                    choices.setPadding(new Insets(5, 15, 40, 0));

                    Button button1 = Essentials.addButton("You remember a childhood memory, running around a garden. \nYou can almost hear a dog barking in a distance, happily.");
                    button1.setOnAction(e->{
                        Essentials.buttonOutput("\n*SPEED INCREASED BY ONE*\nYou recall sunlight on your skin, laughter, and your bare feet pounding against fresh grass. It's beautiful, carefree, but the memory fades as quickly as it came, leaving only the sense of wanting to run.");
                        character.setSpeed(1);

                    });
                    Button button2 = Essentials.addButton("You remember playing with your toys, matching and disassembling shapes. This is... Fun.");
                    button2.setOnAction(e->{
                        Essentials.buttonOutput("\n*COORDINATION INCREASED BY ONE*\nYour small hands carefully assembling toy pieces, turning shapes, matching colors. Calm, precise. Yet, as your vision clears, the details slip away, leaving behind only the feeling of delicate control.");
                        character.setCoordination(1);
                    });
                    Button button3 = Essentials.addButton("You remember making people laugh. They are older than you, much older... Are they your parents? ");
                    button3.setOnAction(e->{
                        Essentials.buttonOutput("\n*CHARISMA INCREASED BY ONE*\nYou can almost hear their laughter ringing out, bright and warm. Your words, confident even as a child, made them smile. The faces blur, leaving you with just the echo of warmth and approval.");
                        character.setCoordination(1);
                    });
                    Button button4 = Essentials.addButton("Fuck this, something is wrong.");
                    button4.setOnAction(e->{
                        Essentials.buttonOutput("\n*PERCEPTION INCREASED BY ONE*\nA sudden chill. You sense something's off—unseen eyes, a hidden whisper. The memory shatters before forming fully, but the lingering unease sharpens your awareness of the present moment.");
                        character.setPerception(1);
                    });
                    //make buttons visible
                    choices.getChildren().addAll(button1, button2, button3, button4);
                    choices.setAlignment(Pos.CENTER);
                    Main.root.setBottom(choices);
                });
                choicePrompt.getStyleClass().add("my-label");
                Chapter.box.getChildren().add(choicePrompt);
            } else if (step==4) {
                Label choicePrompt = Essentials.choice("You dry your face with a towel. The wound’s not deep, but it looks nasty — might get worse if you leave it alone, you might also need a painkiller. You decide to: (?)",
                        ()->{
                            choices.setPadding(new Insets(5, 15, 40, 0));

                            Button button1 = Essentials.addButton("Look for a medicine cabinet in the bathroom.");
                            button1.setOnAction(e->{
                                Essentials.buttonOutput("The mirror isn’t fixed. You open it—cabinet inside. " +
                                        "\n\nInside: old gauze, a sealed bandage, an outdated painkiller, and a half-used tube of antiseptic. You take the painkiller, clean the wound and cover it. " +
                                        "Crude, but it’ll do.\n\n*YOUR WOUND DOESN'T LOOK OPEN NOW, AND YOUR PAIN WILL BE GONE IN MINUTES*\n");
                            });
                            Button button2 = Essentials.addButton("Ignore it for now and return to the bedroom.");
                            button2.setOnAction(e->{
                                Essentials.buttonOutput("You step back into the bedroom, the ache in your skull now dulled — slightly.\n\n*YOU LOOK INTIMIDATING WITH THE SCAR*\n");
                            });
                            choices.getChildren().addAll(button1, button2);
                            choices.setAlignment(Pos.CENTER);
                            Main.root.setBottom(choices);
                        });
                choicePrompt.getStyleClass().add("my-label");
                Chapter.box.getChildren().add(choicePrompt);

            } else if (step==5) {
                Label tut = TypeWriter.type("\n __________Tutorial__________\n" +
"1. Recognizing a Location\n" +
"Whenever an (L) icon appears, you’ve entered a new Location. " +
"That means clues are nearby—inspect objects, note unusual details, and gather every hint.\n" +
"2. Collecting Clues\n" +
"Feel free to explore and revisit any clue until you’re confident you’ve seen everything relevant. " +
"You can return to clues as often as needed before making your move.\n" +
"3. Drawing Conclusions\n" +
"Once you’ve gathered clues, you must pick exactly two conclusions. Each button you click counts as one pick. " +
"Some conclusions directly conflict—choosing one will disable others that don’t fit together. You cannot remove " +
"a choice once made, so review your clues carefully. Only after you’ve chosen two conclusions will the scene " +
"wrap up and the story advance.\n" +
"4. Impact on Your Character\n" +
"Chosen conclusions resolve the current scene and change your character’s mindset. This affects which dialogue options, investigation tools, and story paths become available next. Choose wisely—your decisions shape your character's journey.",
false);
                Essentials.getInput();
                Main.root.setLeft(box);
                box.getChildren().add(tut);
            } else if (step==6) {
                Label text = TypeWriter.type("You step slowly from the cramped bathroom into the main motel room, pausing as the scene registers with a sense of weary confusion. ",false);
                Essentials.getInput();
                Main.root.setLeft(box);
                box.getChildren().add(text);
                box.getChildren().add(TypeWriter.promptLabel);

            } else if (step==7) {
                            Label text= TypeWriter.type("The bed, sitting low and uninviting to your left, is tangled" +
    " with cheap, coarse sheets you woke up on—still bearing faint traces of your own blood from your head" +
    " wound.\n" +
    "\n" +
    "On your right, an old wooden TV unit sags slightly under the weight of a small, outdated television. " +
    "Near it, a small amber prescription bottle rests ominously, label clearly marked yet somehow alien to you.\n" +
    "\n" +
    "Ahead, daylight struggles weakly through grimy blinds, casting faint lines across a worn coffee table " +
    "and a couple of mismatched chairs, positioned awkwardly.\n\n" +
    "The door, set to the far right, is chipped and faded. It promises escape, yet simultaneously threatens the unknown beyond this room.\n\n" +
    "A quiet dread gnaws at you as you look around this bleak, unfamiliar place, unsure how you arrived, " +
    "or why you chose somewhere as miserable as this to end up.\n",false);
                Essentials.getInput();
                Main.root.setCenter(box);
                box.getChildren().add(text);

            } else if (step==8) {

                choices.setPadding(new Insets(10,10,10,10));

                //Main Menu
                Label choicePrompt = Essentials.choice("What do you do? (L)",()->{
                    // Option TV Unit
                    Button button1 = Essentials.addButton("Check the TV Unit");
                    button1.setOnAction(e->{
                    //clear everything first
                    box.getChildren().clear();
                    choices.getChildren().clear();
                    //new simpleText
                    Label tvText = TypeWriter.simpleType("You step over to the TV unit — a tired, dusty thing, " +
                            "more furniture than technology at this point. Beside the silent TV, a crooked-handled " +
                            "drawer clings halfway shut. An amber pill bottle sits nearby, nearly empty.", ()->{
                        // Instantiating Options
                        Button tvOption1 = Essentials.addButton("Pick the pills.");
                        tvOption1.setOnAction(event -> {
                            Label pick = TypeWriter.simpleType("""
                                You pick up the amber bottle on the counter. The label reads:
                                                 *PAROXETINE — 20mg — Take once daily* 
                                                *Prescribed to: V. Harper*. 
                                 The name sits uncomfortably in your chest. Familiar. Yet distant. Like a coat you recognize, but aren’t sure ever really fit you. You speak aloud, quietly: “Is this who I am?”""",()->{
                                choices.setVisible(true);
                            });
                            Essentials.openInventory();
                            //relocate choices again for insets for inv
                            choices.setPadding(new Insets(10,260,10,10));
                            Character.pillsPicked = true;
                            box.getChildren().clear();
                            box.getChildren().add(pick);
                            choices.setVisible(false);
                            choices.getChildren().removeFirst();
                            Main.root.setLeft(box);
                        });
                        Button tvOption2 = Essentials.addButton("Open the drawer.");
                        tvOption2.setOnAction(event2->{
                            Label drawer = TypeWriter.simpleType("You tug the drawer open. It sticks for a moment, then slides open."+
                                    "Inside: a dead pen, a motel notepad, and a torn city map — the usual hotel junk.", ()-> {
                            });
                            box.getChildren().clear();
                            box.getChildren().add(drawer);
                            Main.root.setLeft(box);

                        });
                        Button tvOption3 = Essentials.addButton("Go back.");
                        tvOption3.setOnAction(event -> {
                            clearBox();
                            step=7;
                            next();
                        });
                        // pills picked check and show buttons
                        if (!Character.pillsPicked) {
                            choices.getChildren().add(tvOption1);
                        }
                        choices.getChildren().addAll(tvOption2,tvOption3);
                        Main.root.setBottom(choices);

                    });
                    choices.setAlignment(Pos.CENTER);
                    tvText.getStyleClass().add("my-label");
                    Main.root.setLeft(tvText);
                    });

                    // Option Coffee Table
                    Button button2 = Essentials.addButton("Check the Coffee Table");
                    button2.setOnAction(e->{
                        clearBox();
                        Label coffeeTableText = TypeWriter.simpleType("Two stained mugs sit on the table — cheap " +
                                "motel ceramic. No steam. No smell. Just faint rings at the bottom. You can't recall if " +
                                "you drank from one… or both.", ()->{
                           //Instantiating Options
                            Button cTableOption1 = Essentials.addButton("Examine the note.");
                            cTableOption1.setOnAction(event->{
                                Label pick = TypeWriter.simpleType("""
                                You open the crumbled note, it reads:
                                
                                        *Don't forget Room 6. Midnight.* 
                                                
                                You speak silently: "Don't forget huh? Great.”""",()->{
                                    choices.setVisible(true);


                                });
                                box.getChildren().clear();
                                Character.noteChecked = true;
                                box.getChildren().add(pick);
                                choices.setVisible(false);
                                Main.root.setLeft(box);
                            });
                            Button cTableOption2 = Essentials.addButton("Go back.");
                            cTableOption2.setOnAction(event->{
                                clearBox();
                                step=7;
                                next();
                            });
                            choices.getChildren().addAll(cTableOption1,cTableOption2);
                            Main.root.setBottom(choices);
                        });
                        choices.setAlignment(Pos.CENTER);
                        coffeeTableText.getStyleClass().add("my-label");
                        Main.root.setLeft(coffeeTableText);
                    });

                    Button button3 = Essentials.addButton("Check the Entrance");
                    button3.setOnAction(e->{
                        Label entranceText = TypeWriter.simpleType("You are in front of the door, you feel like you should conclude this scene before leaving.",()-> {
                            // entrance buttons
                            Button entranceOption1 = Essentials.addButton("Check the mud.");
                            Button entranceOption2 = Essentials.addButton("Check the coat rack.");
                            Button entranceOption3 = Essentials.addButton("Go back.");

                            entranceOption1.setOnAction(event-> {
                                Label checkMud = TypeWriter.simpleType("Half-dried mud is tracked near the entrance. " +
                                        "Not old, but not fresh either. It matches your shoes—but something feels off. " +
                                        "If it was raining, where’s your coat?", ()->{
                                    choices.setVisible(true);

                                });
                                mudChecked = true;
                                choices.setVisible(false);
                                entranceOption2.setText("Check the coat rack. *NEW INSIGHT*");
                                box.getChildren().clear();
                                box.getChildren().add(checkMud);
                                Main.root.setLeft(box);
                            });
                            entranceOption2.setOnAction(event-> {
                                Label checkCoatRack;
                                if (mudChecked) {
                                    checkCoatRack = TypeWriter.simpleType("You eye the coat rack. " +
                                            "There's mud on your shoes, but no coat to match it. Did you come here without one? " +
                                            "Or did it go missing after?" +
                                            "You should probably ask someone. The front desk might’ve seen something.\n\n" +
                                            "**NEW OBJECTIVE: Ask someone about your missing coat.**", ()->{
                                        choices.getChildren().remove(1);
                                        choices.setVisible(true);

                                    });
                                } else {
                                    checkCoatRack = TypeWriter.simpleType("An empty rack. Just a bent wire hook and dust. It tells you nothing.", ()->{
                                        choices.setVisible(true);
                                    });
                                }
                                choices.setVisible(false);
                                box.getChildren().clear();
                                box.getChildren().add(checkCoatRack);

                            });
                            entranceOption3.setOnAction(event->{
                                clearBox();
                                step=7;
                                next();
                            });

                            choices.getChildren().addAll(entranceOption1, entranceOption2, entranceOption3);
                            Main.root.setBottom(choices);
                        });

                        clearBox();
                        box.getChildren().add(entranceText);
                        Main.root.setLeft(box);
                    });
                    Button button4 = Essentials.addButton("Conclude the scene.");
                    button4.setOnAction(e->{
                        Label confirm = TypeWriter.simpleType("Are you sure you want to conclude this scene?",()->{
                            Button yes = Essentials.addButton("Yes");
                            yes.setOnAction(yesEvent->{
                                clearBox();
                                Button conclusion1 = Essentials.addButton("I was robbed, maybe resisted.");
                                Button conclusion2 = Essentials.addButton("Affair gone bad.");
                                Button conclusion3 = Essentials.addButton("I need a doctor.");
                                Button conclusion4 = Essentials.addButton("Am I a detective.");
                                Button conclusion5 = Essentials.addButton("A bad hangover apparently.");
                                Button conclusion6 = Essentials.addButton("Was I experimented on?");
                                Button conclusion7 = Essentials.addButton("I need to find my belongings.");
                                Button conclusion8 = Essentials.addButton("I need no theories, but actions.");

                                // Customization
                                ArrayList<Button> allConclusions = new ArrayList<>(Arrays.asList(
                                        conclusion1,
                                        conclusion2,
                                        conclusion3,
                                        conclusion4,
                                        conclusion5,
                                        conclusion6,
                                        conclusion7,
                                        conclusion8
                                ));

                                for (Button b : allConclusions) {
                                    b.getStyleClass().add("conclusion-button");
                                }
                                Conclusion c = new Conclusion(allConclusions);

                                // Effects
                                ArrayList<Button> conclusions = new ArrayList<>();
                                conclusion1.setOnAction(event->{
                                    // Attacked
                                    //itself
                                    c.clickConclusion("I wsarobbed considering I don't have my belongings. I must’ve resisted and got injured. But how did I end up here?",
                                            "Adrenaline surges: “Someone wanted me down.”.",new ArrayList<>(Arrays.asList(0,1,4,5)));
                                });
                                conclusion2.setOnAction(event->{
                                    // Affair
                                    c.clickConclusion("Was I having some sort of an affair? Is that what the note was about? Then why this dump of a motel? For secrecy I presume. Seems like it's not a secret anymore since I had a fight.",
                                            "A tangled web of desire and betrayal.",new ArrayList<>(Arrays.asList(0,1,3,5)));
                                });
                                conclusion3.setOnAction(event->{
                                    // Need doctor
                                    c.clickConclusion("Whatever this is, I need a doctor. Why haven’t I thought of that sooner? Something’s wrong with my head.",
                                            "“Blood trails aren’t a joke. I need aid.”\n",new ArrayList<>(Arrays.asList(4)));
                                });
                                conclusion4.setOnAction(event->{
                                    // Find belongings
                                    //itself
                                    c.clickConclusion("The scene is a mess, but your reaction to it isn’t. Every move—checking the wound, scanning the floor, noticing the note—it feels procedural. Like muscle memory. Like you’ve done this before. Like you were… investigating.",
                                            "“I’ve been here before. Time to finish the job.”",new ArrayList<>(Arrays.asList(1,3,5)));
                                });
                                conclusion5.setOnAction(event->{
                                    // Hangover
                                    //itself
                                    c.clickConclusion("Okay, maybe this is just a hangover. A really dramatic, head-wound-level hangover.",
                                            "Brain fog thickens. Everything hurts.", new ArrayList<>(Arrays.asList(2,4)));
                                });
                                conclusion6.setOnAction(event->{
                                    // Experiment
                                    //itself
                                    c.clickConclusion("What if I was experimented on? Wiped. Reprogrammed. This room… maybe it’s not even real.",
                                            "Mind games, chemicals, or worse.",new ArrayList<>(Arrays.asList(0,1,3,5)));
                                });
                                conclusion7.setOnAction(event->{
                                    //itself
                                    c.clickConclusion("Something isn’t right. I need to find my belongings—and whoever left that note. Someone out there knows who I am.",
                                            "Find belongings",new ArrayList<>(Arrays.asList(7)));
                                });
                                conclusion8.setOnAction(event->{
                                    c.clickConclusion("None of this makes sense. No wild theories. I just need to move forward, see what this is all about.",
                                            "Clear head, steady feet.",new ArrayList<>(Arrays.asList(8)));
                                    //itself

                                });
                                choices.getChildren().addAll(conclusion1,conclusion2,conclusion3,conclusion4,conclusion5,conclusion6,conclusion7,conclusion8);
                                Main.root.setCenter(choices);
                            });
                            Button no = Essentials.addButton("No");
                            no.setOnAction(noEvent->{
                                clearBox();
                                step=7;
                                next();
                            });
                            choices.getChildren().addAll(yes,no);
                        });
                        choices.getChildren().clear();
                        box.getChildren().add(confirm);
                    });

                    choices.getChildren().addAll(button1, button2, button3);
                    if (mudChecked) {
                        choices.getChildren().add(button4);
                    }
                    choices.setAlignment(Pos.CENTER);
                    Main.root.setBottom(choices);
                });
                choicePrompt.getStyleClass().add("my-label");
                Chapter.box.getChildren().add(choicePrompt);
            }
        }

        public void next() {
            box.getChildren().clear();
            step++;
            Main.getChapter().play();
        }
        boolean mudChecked;

    };
    public static String currentText;
    public static void conclude(List<Button> conclusions){
        picks++;
        for (int i = 0; i < conclusions.size(); i++) {
            conclusions.get(i).setDisable(true);
            conclusions.get(i).setOpacity(0.4);
        }
    }

    public abstract void play();
    public String getCurrentText(){
        return currentText;
    }
    public abstract void next();
    public static void clearBox(){
        Main.root.setBottom(null);
        Main.root.setLeft(null);
        Main.root.setCenter(null);
        box.getChildren().clear();
        choices.getChildren().clear();
    }
    public static int step = 8;
    public static int picks = 0;


    Character character = new Character();
    public static VBox box = new VBox(2);
    public static VBox choices = new VBox(10);


}
