package info.evison.dan.robotcontroller1.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import info.evison.dan.robotcontroller1.model.FieldGroupModel;

public class FieldCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected FieldCardView _fieldCardView;

    public FieldCardViewHolder(FieldCardView fieldCardView) {
        super(fieldCardView);
        _fieldCardView = fieldCardView;
        fieldCardView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked!", Toast.LENGTH_SHORT);
    }

    public void bind(FieldGroupModel model) {
        _fieldCardView.bind(model);
    }
}
