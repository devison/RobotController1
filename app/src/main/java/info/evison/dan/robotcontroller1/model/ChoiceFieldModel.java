package info.evison.dan.robotcontroller1.model;

public class ChoiceFieldModel implements FieldModel {

    public BindableString name;
    public BindableInt selection;
    public String[] choices;  // not bindable

    public ChoiceFieldModel(String name, int selection, String[] choices) {
        this.name = new BindableString(name);
        this.selection = new BindableInt(selection);
        this.choices = choices;
    }
}
