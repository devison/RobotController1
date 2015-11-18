package info.evison.dan.robotcontroller1.app;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import info.evison.dan.robotcontroller1.R;
import info.evison.dan.robotcontroller1.model.BooleanFieldModel;
import info.evison.dan.robotcontroller1.model.ChoiceFieldModel;
import info.evison.dan.robotcontroller1.model.FieldGroupModel;
import info.evison.dan.robotcontroller1.model.FieldModel;
import info.evison.dan.robotcontroller1.model.RangeFieldModel;
import info.evison.dan.robotcontroller1.view.FieldCardRecyclerViewAdapter;

public class FieldsFragment extends Fragment {

    private static final String TAG = FieldsFragment.class.getSimpleName();

    public FieldsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fields_fragment, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fields_recycler_view);
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        String[] choices = new String[]{ "Choice 1", "Choice 2", "Choice 3"};
        List<FieldGroupModel> models = Arrays.asList(
                new FieldGroupModel("First Group", Arrays.<FieldModel>asList(new BooleanFieldModel("My Boolean", false))),
                new FieldGroupModel("Second Group", Arrays.<FieldModel>asList(new RangeFieldModel("My Range", 5, 100))),
                new FieldGroupModel("Third Group", Arrays.<FieldModel>asList(new ChoiceFieldModel("My Choice", 0, choices)))
        );

        FieldCardRecyclerViewAdapter adapter = new FieldCardRecyclerViewAdapter(models);
        recyclerView.setAdapter(adapter);

        super.onCreate(savedInstanceState);
        return view;
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }
}
