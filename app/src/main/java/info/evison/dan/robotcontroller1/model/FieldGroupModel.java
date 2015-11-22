package info.evison.dan.robotcontroller1.model;

import java.util.List;


public class FieldGroupModel {

    public BindableString groupName;
    public List<FieldModel> _fieldModels;

    public FieldGroupModel(String groupName, List<FieldModel> fieldModels) {
        this.groupName = new BindableString(groupName);
        _fieldModels = fieldModels;
    }

    public List<FieldModel> getFieldModels() {
        return _fieldModels;
    }
}
