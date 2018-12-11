package ja.project.comp3074.rpguide;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ja.project.comp3074.rpguide.fragmentDash.AboutFragment;
import ja.project.comp3074.rpguide.fragmentDash.CharacterFragment;
import ja.project.comp3074.rpguide.fragmentDash.ShopsFragment;


public class DashboardActivity extends AppCompatActivity {
    private final String TAG = "DashboardActivity";
    public static FragmentManager fragman;
    Button toShops, toCharacters, toAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        toShops = (Button) findViewById(R.id.btnToShops);
        toCharacters = (Button) findViewById(R.id.btnToCharacters);
        toAbout = (Button) findViewById(R.id.btnToAbout);

        setButtonListeners();

        //settin' up fragment manager
        fragman = getSupportFragmentManager();

        if(findViewById(R.id.dashFrame)!=null){
            if(savedInstanceState!=null) return;
            CharacterFragment charFrag= new CharacterFragment();
            FragmentTransaction fragTrans = fragman.beginTransaction();
            fragTrans.add(R.id.dashFrame, charFrag,null);
            fragTrans.commit();
        }
    }

    public void setButtonListeners(){
        toShops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopsFragment shopFrag = new ShopsFragment();
                FragmentTransaction fragTrans = fragman.beginTransaction();
                fragTrans.add(R.id.dashFrame, shopFrag,null);
                fragTrans.commit();
            }
        });
        toCharacters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharacterFragment charFrag= new CharacterFragment();
                FragmentTransaction fragTrans = fragman.beginTransaction();
                fragTrans.add(R.id.dashFrame, charFrag,null);
                fragTrans.commit();
            }
        });
        toAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutFragment abootFrag = new AboutFragment();
                FragmentTransaction fragTrans = fragman.beginTransaction();
                fragTrans.add(R.id.dashFrame, abootFrag,null);
                fragTrans.commit();
            }
        });
    }
}
