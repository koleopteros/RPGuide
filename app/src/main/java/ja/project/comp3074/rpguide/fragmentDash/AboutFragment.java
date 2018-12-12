package ja.project.comp3074.rpguide.fragmentDash;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ja.project.comp3074.rpguide.R;
import ja.project.comp3074.rpguide.database.users.User;

public class AboutFragment extends Fragment {
    private ArrayList<User> members;
    private RecyclerView rv;
    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);


        return view;
    }
}
