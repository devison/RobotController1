package info.evison.dan.robotcontroller1.app;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import info.evison.dan.robotcontroller1.R;
import info.evison.dan.robotcontroller1.model.BooleanFieldModel;
import info.evison.dan.robotcontroller1.model.ChoiceFieldModel;
import info.evison.dan.robotcontroller1.model.FieldGroupModel;
import info.evison.dan.robotcontroller1.model.FieldModel;
import info.evison.dan.robotcontroller1.model.RangeFieldModel;
import info.evison.dan.robotcontroller1.util.LayoutUtil;
import info.evison.dan.robotcontroller1.view.FieldGroupRecyclerViewAdapter;
import info.evison.dan.robotcontroller1.view.MarginItemDecoration;

public class FieldsFragment extends Fragment {

    private static final String TAG = FieldsFragment.class.getSimpleName();

    public static final int MARGIN_DP = 5;

    BooleanFieldModel _booleanFieldModel;
    ChoiceFieldModel _choiceFieldModel;
    RangeFieldModel _rangeFieldModel;
    FieldGroupModel _fieldGroupModel;

    public FieldsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fields_fragment, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fields_recycler_view);
        recyclerView.setHasFixedSize(false);
        //  Hack to avoid animations being destroyed when scrolling and views being recycled.  TODO:  better way?
        recyclerView.setItemViewCacheSize(200);
        final int marginPx = LayoutUtil.convertDpToPixel(MARGIN_DP, view.getContext());
        recyclerView.addItemDecoration(new MarginItemDecoration(marginPx));

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(layoutManager);

        String[] choices = new String[]{"Choice 1", "Choice 2", "Choice 3"};
        String[] choices2 = new String[]{"Choice 4", "Choice 5", "Choice 6", "Choice 7", "Choice 8", "Choice 9", "Choice 10"};

        _booleanFieldModel = new BooleanFieldModel("My Boolean 1", false);
        _choiceFieldModel = new ChoiceFieldModel("My Choice 1", 0, choices);
        _rangeFieldModel = new RangeFieldModel("My Range 1", 5, 100);

        _fieldGroupModel = new FieldGroupModel("First Group", Arrays.<FieldModel>asList(
                _booleanFieldModel,
                new BooleanFieldModel("My Boolean 2", true)
        ));

        List<FieldGroupModel> models = Arrays.asList(
                _fieldGroupModel,
                new FieldGroupModel("Second Group", Arrays.<FieldModel>asList(
                        _rangeFieldModel,
                        new RangeFieldModel("My Range 2", 50, 100),
                        new RangeFieldModel("My Range 3", 100, 100),
                        new RangeFieldModel("My Range 4", 100, 1000),
                        new BooleanFieldModel("My Boolean 2", true)
                )),
                new FieldGroupModel("Third Group", Arrays.<FieldModel>asList(
                        _choiceFieldModel,
                        new ChoiceFieldModel("My Choice 2", 1, choices),
                        new ChoiceFieldModel("My Choice 3", 2, choices),
                        new ChoiceFieldModel("My Choice 4", 0, choices2)))
        );

        List<FieldGroupModel> models2 = new ArrayList<>();
        models2.addAll(models);
        models2.addAll(models);
        models2.addAll(models);
        models2.addAll(models);
        FieldGroupRecyclerViewAdapter adapter = new FieldGroupRecyclerViewAdapter(models2);
        recyclerView.setAdapter(adapter);

        super.onCreate(savedInstanceState);
        runThread();
        return view;
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    private void runThread() {
        new Thread() {

            public void run() {

                Runnable toggle = new Runnable() {
                    @Override
                    public void run() {
                        boolean newValue = !_booleanFieldModel.value.get();
                        _booleanFieldModel.value.set(newValue);
                        _booleanFieldModel.name.set(newValue ? "Heading - true" : "Heading - false");
                        _fieldGroupModel.groupName.set(newValue ? "First Group - true" : "First Group - false");
                        int newSelection = (_choiceFieldModel.selection.get() + 1) % _choiceFieldModel.choices.length;
                        _choiceFieldModel.selection.set(newSelection);

                        int newRange = (_rangeFieldModel.value.get() + _rangeFieldModel.max.get() / 10) % _rangeFieldModel.max.get();
                        _rangeFieldModel.value.set(newRange);
                    }
                };

                try {
                    while (true) {
                        int seconds = ThreadLocalRandom.current().nextInt(2, 6);
                        sleep(seconds * 1000);
                        getActivity().runOnUiThread(toggle);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
