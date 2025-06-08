public class Option {
    String label;
    Runnable action;

    public Option(String label, Runnable action) {
        this.label = label;
        this.action = action;
    }

    public void select() {
        action.run();
    }
}
