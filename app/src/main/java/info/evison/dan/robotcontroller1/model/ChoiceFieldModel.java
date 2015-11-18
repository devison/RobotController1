package info.evison.dan.robotcontroller1.model;

public class ChoiceFieldModel implements FieldModel {

    public String name;
    public int selection;
    public String[] choices;

    public ChoiceFieldModel(String name, int selection, String[] choices) {
        this.name = name;
        this.selection = selection;
        this.choices = choices;
    }
}
