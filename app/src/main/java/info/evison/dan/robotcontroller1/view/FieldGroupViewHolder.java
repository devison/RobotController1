package info.evison.dan.robotcontroller1.view;

import android.support.v7.widget.RecyclerView;

import info.evison.dan.robotcontroller1.model.FieldGroupModel;

public class FieldGroupViewHolder extends RecyclerView.ViewHolder {

    protected FieldGroupView _fieldGroupView;

    public FieldGroupViewHolder(FieldGroupView fieldGroupView) {
        super(fieldGroupView);
        _fieldGroupView = fieldGroupView;
    }

    public void bind(FieldGroupModel model) {
        _fieldGroupView.bind(model);
    }
}
