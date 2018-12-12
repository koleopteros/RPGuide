package ja.project.comp3074.rpguide.fragmentDash;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ja.project.comp3074.rpguide.R;

public class CharacterFragment extends Fragment {

    public CharacterFragment() {
        // Required empty public constructor
    }
    public static CharacterFragment newInstance(String param1, String param2) {
        CharacterFragment fragment = new CharacterFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character, container, false);
    }
}