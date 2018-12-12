package ja.project.comp3074.rpguide.fragmentMain;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ja.project.comp3074.rpguide.DashboardActivity;
import ja.project.comp3074.rpguide.MainActivity;
import ja.project.comp3074.rpguide.R;
import ja.project.comp3074.rpguide.database.users.UserDBHelper;
import ja.project.comp3074.rpguide.obj.users.User;

public class LoginFragment extends Fragment {
    private TextView txtUser, txtPasswd;
    private Button login, registration;
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        UserDBHelper dbHelper = new UserDBHelper(getActivity());

        User user = dbHelper.authenticate(username,passwd);
        if(user==null){
            return false;
        }
        else{
            SharedPreferences prefs = this.getActivity().getSharedPreferences("rpGuide",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putLong("userID",user.getId());
            editor.putString("user",user.getName());
            editor.putString("userEmail",user.getEmail());
            editor.apply();
        }
        return true;
    }
}
