package info.evison.dan.robotcontroller1.view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import java.util.List;

import info.evison.dan.robotcontroller1.model.FieldGroupModel;

public class FieldGroupRecyclerViewAdapter extends RecyclerView.Adapter<FieldGroupViewHolder> {

    private static final String TAG = FieldGroupRecyclerViewAdapter.class.getSimpleName();

    private List<FieldGroupModel> _fieldGroupModels;

    public FieldGroupRecyclerViewAdapter(List<FieldGroupModel> fieldGroupModels) {
        _fieldGroupModels = fieldGroupModels;
    }

    @Override
    public int getItemCount() {
        return _fieldGroupModels.size();
    }

    @Override
    public FieldGroupViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Log.v(TAG, "Here I am in FieldCarveRecyclerViewAdapter.onCreateViewHolder: " + this);
        FieldGroupView fieldGroupView = new FieldGroupView(viewGroup.getContext(), viewGroup);
        return new FieldGroupViewHolder(fieldGroupView);
    }

    @Override
    public void onBindViewHolder(FieldGroupViewHolder holder, int i) {
        Log.v(TAG, "Here I am in FieldCarveRecyclerViewAdapter.onBindViewHolder: " + this);
        FieldGroupModel fieldGroupModel = _fieldGroupModels.get(i);
        holder.bind(fieldGroupModel);
    }
}
