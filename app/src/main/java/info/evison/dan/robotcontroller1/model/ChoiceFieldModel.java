package info.evison.dan.robotcontroller1.model;

public class ChoiceFieldModel implements FieldModel {

    public String name;
    public int index;
    public String[] choices;

    public ChoiceFieldModel(String name, int index, String[] choices) {
        this.name = name;
        this.index = index;
        this.choices = choices;
    }
}
