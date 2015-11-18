package info.evison.dan.robotcontroller1.model;

public class BooleanFieldModel implements FieldModel {

    public String name;
    public boolean value;

    public BooleanFieldModel(String name, boolean value) {
        this.name = name;
        this.value = value;
    }
}
