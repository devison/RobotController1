package info.evison.dan.robotcontroller1.model;

public class RangeFieldModel implements FieldModel {

    public String name;
    public int value;
    public int max;

    public RangeFieldModel(String name, int value, int max) {
        this.name = name;
        this.value = value;
        this.max = max;
    }
}
