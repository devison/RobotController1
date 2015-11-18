package info.evison.dan.robotcontroller1.app;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
//        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
//        recyclerView.setLayoutManager(layoutManager);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        String[] choices = new String[]{"Choice 1", "Choice 2", "Choice 3"};
        String[] choices2 = new String[]{"Choice 4", "Choice 5", "Choice 6", "Choice 7", "Choice 8", "Choice 9", "Choice 10"};

        List<FieldGroupModel> models = Arrays.asList(
                new FieldGroupModel("First Group", Arrays.<FieldModel>asList(
                        new BooleanFieldModel("My Boolean 1", false),
                        new BooleanFieldModel("My Boolean 2", true)
                )),
                new FieldGroupModel("Second Group", Arrays.<FieldModel>asList(
                        new RangeFieldModel("My Range 1", 5, 100),
                        new RangeFieldModel("My Range 2", 50, 100),
                        new RangeFieldModel("My Range 3", 100, 100),
                        new RangeFieldModel("My Range 4", 100, 1000),
                        new BooleanFieldModel("My Boolean 2", true)
                )),
                new FieldGroupModel("Third Group", Arrays.<FieldModel>asList(
                        new ChoiceFieldModel("My Choice 1", 0, choices),
                        new ChoiceFieldModel("My Choice 2", 1, choices),
                        new ChoiceFieldModel("My Choice 3", 2, choices),
                        new ChoiceFieldModel("My Choice 4", 0, choices2)))
        );

        List<FieldGroupModel> models2 = new ArrayList<>();
        models2.addAll(models);
        models2.addAll(models);
        models2.addAll(models);
        models2.addAll(models);
        FieldCardRecyclerViewAdapter adapter = new FieldCardRecyclerViewAdapter(models2);
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
