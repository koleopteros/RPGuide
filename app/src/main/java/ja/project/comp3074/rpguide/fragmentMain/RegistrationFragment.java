package ja.project.comp3074.rpguide.fragmentMain;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ja.project.comp3074.rpguide.DashboardActivity;
import ja.project.comp3074.rpguide.MainActivity;
import ja.project.comp3074.rpguide.R;
import ja.project.comp3074.rpguide.database.users.UserDBHelper;
import ja.project.comp3074.rpguide.database.users.User;

public class RegistrationFragment extends Fragment {
    TextView reg_fname,reg_lname,reg_email,reg_passwd;
    Button back, register;
    UserDBHelper dbHelper;
    public RegistrationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container,false);

        dbHelper = new UserDBHelper(getActivity());

        reg_fname = (TextView) view.findViewById(R.id.txtRegFirstName);
        reg_lname = (TextView) view.findViewById(R.id.txtRegLastName);
        reg_email = (TextView) view.findViewById(R.id.txtRegEmail);
        reg_passwd = (TextView) view.findViewById(R.id.txtRegPasswd);

        back = (Button) view.findViewById(R.id.btnBackToLogin);
        register = (Button) view.findViewById(R.id.btnRegister);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragman.beginTransaction().replace(R.id.mainFrame,new LoginFragment(),null).commit();
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
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        User newUser = new User(
                reg_fname.getText().toString(),
                reg_lname.getText().toString(),
                reg_email.getText().toString(),
                reg_passwd.getText().toString());
        dbHelper.addNewUser(db,newUser);

        //db = dbHelper.getReadableDatabase();
        //User user = dbHelper.authenticate(db,reg_email.getText().toString(),reg_passwd.getText().toString());
        getLoggedIn(newUser);
        moveToDash();
    }
    private void getLoggedIn(User user){
        SharedPreferences prefs = this.getActivity().getSharedPreferences("rpGuide",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("userID",user.getId());
        editor.putString("user",user.getName());
        editor.putString("userEmail",user.getEmail());
        editor.apply();
    }
    private void moveToDash(){
        Intent intent = new Intent(getActivity(), DashboardActivity.class);
        startActivity(intent);
    }
}
