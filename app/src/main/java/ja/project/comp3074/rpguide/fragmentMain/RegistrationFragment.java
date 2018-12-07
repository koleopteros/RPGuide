package ja.project.comp3074.rpguide.fragmentMain;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import ja.project.comp3074.rpguide.DashboardActivity;
import ja.project.comp3074.rpguide.R;
import ja.project.comp3074.rpguide.database.users.UserViewModel;
import ja.project.comp3074.rpguide.obj.users.User;

public class RegistrationFragment extends Fragment {
    TextView reg_fname,reg_lname,reg_email,reg_passwd;
    Button back, register;
    private UserViewModel uvm;
    private OnFragmentInteractionListener mListener;

    public RegistrationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container,false);
        reg_fname = (TextView) view.findViewById(R.id.txtRegFirstName);
        reg_lname = (TextView) view.findViewById(R.id.txtRegLastName);
        reg_email = (TextView) view.findViewById(R.id.txtRegEmail);
        reg_passwd = (TextView) view.findViewById(R.id.txtRegPasswd);

        back = (Button) view.findViewById(R.id.btnBackToLogin);
        register = (Button) view.findViewById(R.id.btnRegister);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashboardActivity.fragman.beginTransaction().replace(R.id.dashFrame,new LoginFragment(),null).commit();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeRegistration();
                Toast.makeText(getActivity(),"Registration complete!",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    private void completeRegistration(){
        User newUser = new User(
                reg_fname.getText().toString(),
                reg_lname.getText().toString(),
                reg_email.getText().toString(),
                reg_passwd.getText().toString());
        uvm = ViewModelProviders.of(this).get(UserViewModel.class);
        uvm.insert(newUser);
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
