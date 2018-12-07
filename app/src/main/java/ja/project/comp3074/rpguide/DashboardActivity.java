package ja.project.comp3074.rpguide;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ja.project.comp3074.rpguide.fragmentDash.DashFragment;


public class DashboardActivity extends AppCompatActivity {
    private final String TAG = "DashboardActivity";
    public static FragmentManager fragman;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        fragman = getSupportFragmentManager();
        if(findViewById(R.id.dashFrame)!=null){
            if(savedInstanceState!=null) return;
            DashFragment dashFrag= new DashFragment();
            FragmentTransaction fragTrans = fragman.beginTransaction();
            fragTrans.add(R.id.mainFrame, dashFrag,null);
            fragTrans.commit();
        }
    }
}
