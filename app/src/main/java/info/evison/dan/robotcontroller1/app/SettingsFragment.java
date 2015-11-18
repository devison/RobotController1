package info.evison.dan.robotcontroller1.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.evison.dan.robotcontroller1.R;

public class SettingsFragment extends Fragment {

    private static final String TAG = FieldsFragment.class.getSimpleName();

    public SettingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.settings_fragment, container, false);
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
