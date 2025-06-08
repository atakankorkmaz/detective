public class Character {
    // Stats
    private int charisma;
    private int speed;
    private int coordination;
    private int perception;

    // Data
    public static boolean pillsPicked;
    public static boolean noteChecked;


    public int getSpeed() {
        return Integer.parseInt(PreferencesManager.get("speed"));
    }
    public void setSpeed(int value){
        int temp = this.getSpeed();
        temp += value;
        PreferencesManager.set("speed", String.valueOf(temp));
        this.speed = temp;
    }
    public int getCoordination(){
        return Integer.parseInt(PreferencesManager.get("coordination"));
    }

    public void setCoordination(int value) {
        int temp = this.getCoordination();
        temp += value;
        PreferencesManager.set("coordination", String.valueOf(temp));
        this.speed = temp;
    }

    public int getCharisma() {
        return Integer.parseInt(PreferencesManager.get("charisma"));
    }

    public void setCharisma(int value) {
        int temp = this.getCharisma();
        temp += value;
        PreferencesManager.set("charisma", String.valueOf(temp));
        this.speed = temp;
    }

    public int getPerception(){
        return Integer.parseInt(PreferencesManager.get("perception"));
    }

    public void setPerception(int value) {
        int temp = this.getPerception();
        temp += value;
        PreferencesManager.set("perception", String.valueOf(temp));
        this.speed = temp;
    }
}
