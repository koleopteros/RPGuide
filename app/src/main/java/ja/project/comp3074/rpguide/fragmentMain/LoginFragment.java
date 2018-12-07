package ja.project.comp3074.rpguide.fragmentMain;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ja.project.comp3074.rpguide.DashboardActivity;
import ja.project.comp3074.rpguide.MainActivity;
import ja.project.comp3074.rpguide.R;
import ja.project.comp3074.rpguide.database.users.UserViewModel;
import ja.project.comp3074.rpguide.obj.users.User;

public class LoginFragment extends Fragment {

    private TextView txtUser, txtPasswd;
    private Button login, registration;
    private OnFragmentInteractionListener mListener;
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        txtUser = (TextView) view.findViewById(R.id.loginUser);
        txtPasswd = (TextView) view.findViewById(R.id.loginPasswd);
        login = (Button) view.findViewById(R.id.btnLogin);
        registration = (Button) view.findViewById(R.id.btnRegistration);

        registration.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MainActivity.fragman.beginTransaction().replace(R.id.mainFrame, new RegistrationFragment(),null).commit();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            String userAttempt = txtUser.getText().toString();
            String passwdAttempt = txtPasswd.getText().toString();

            @Override
            public void onClick(View v) {
                if(authenticate(userAttempt,passwdAttempt)){
                    Intent intent = new Intent(getActivity(), DashboardActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getActivity(),"Login Failed!\nIncorrect username or password.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
    private boolean authenticate(String username, String passwd){
        boolean result =false;
        UserViewModel uvm = ViewModelProviders.of(this).get(UserViewModel.class);
        List<User> userList = uvm.getAllUsers();
        User foundUser = new User("false+","negative-","","");

        for(User user : userList){
            if(!result){
                result = user.getEmail().equals(username) && user.getPasswd().equals(passwd);
                foundUser = user;
            }
            else {
                SharedPreferences prefs = getActivity().getSharedPreferences("mrUser", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("name", foundUser.getFirstName());
                editor.apply();
                break;
            }
        }
        return result;
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
