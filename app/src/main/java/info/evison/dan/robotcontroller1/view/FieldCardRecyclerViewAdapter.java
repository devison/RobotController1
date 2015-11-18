package info.evison.dan.robotcontroller1.view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import info.evison.dan.robotcontroller1.R;
import info.evison.dan.robotcontroller1.model.FieldGroupModel;

public class FieldCardRecyclerViewAdapter extends RecyclerView.Adapter<FieldCardViewHolder> {

    private static final String TAG = FieldCardRecyclerViewAdapter.class.getSimpleName();

    private List<FieldGroupModel> _fieldGroupModels;

    public FieldCardRecyclerViewAdapter(List<FieldGroupModel> fieldGroupModels) {
        _fieldGroupModels = fieldGroupModels;
    }

    @Override
    public int getItemCount() {
        return _fieldGroupModels.size();
    }

    @Override
    public FieldCardViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Log.v(TAG, "Here I am in FieldCarveRecyclerViewAdapter.onCreateViewHolder: " + this);
        FieldCardView fieldCardView = new FieldCardView(viewGroup.getContext(), viewGroup);
        return new FieldCardViewHolder(fieldCardView);
    }

    @Override
    public void onBindViewHolder(FieldCardViewHolder holder, int i) {
        Log.v(TAG, "Here I am in FieldCarveRecyclerViewAdapter.onBindViewHolder: " + this);
        FieldGroupModel fieldGroupModel = _fieldGroupModels.get(i);
        holder.bind(fieldGroupModel);
    }
}
