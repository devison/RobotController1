package info.evison.dan.robotcontroller1.model;

import java.util.List;


public class FieldGroupModel {

    protected String _groupName;
    protected List<FieldModel> _fieldModels;

    public FieldGroupModel(String groupName, List<FieldModel> fieldModels) {
        _groupName = groupName;
        _fieldModels = fieldModels;
    }

    public String getGroupName() {
        return _groupName;
    }

    public List<FieldModel> getFieldModels() {
        return _fieldModels;
    }
}
