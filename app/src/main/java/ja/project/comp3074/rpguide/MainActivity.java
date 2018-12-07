package ja.project.comp3074.rpguide;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ja.project.comp3074.rpguide.fragmentMain.LoginFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static FragmentManager fragman;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragman = getSupportFragmentManager();
        if(findViewById(R.id.mainFrame)!=null){
            if(savedInstanceState!=null) return;
            LoginFragment loginFrag = new LoginFragment();
            FragmentTransaction fragTrans = fragman.beginTransaction();
            fragTrans.add(R.id.mainFrame, loginFrag,null);
            fragTrans.commit();
        }
    }
}
