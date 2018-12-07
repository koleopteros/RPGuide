package ja.project.comp3074.rpguide.fragmentDash;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import ja.project.comp3074.rpguide.DashboardActivity;
import ja.project.comp3074.rpguide.R;
import ja.project.comp3074.rpguide.obj.users.User;

public class AboutFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private ArrayList<User> members;
    ListView lv;
    Button goBack;
    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        String[] members = {
                "Jerome Ching"
        };
        lv = (ListView)view.findViewById(R.id.lvAboutUs);
        ArrayAdapter<String> lvAdapter =
                new ArrayAdapter<String>(getActivity(),R.layout.row_layout,members);
        lv.setAdapter(lvAdapter);

        //setting up button
        goBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DashboardActivity.fragman.beginTransaction().replace(R.id.dashFrame, new DashFragment(),null).commit();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
