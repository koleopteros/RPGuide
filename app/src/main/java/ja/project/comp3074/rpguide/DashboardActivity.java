package ja.project.comp3074.rpguide;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ja.project.comp3074.rpguide.database.characters.CharacterViewModel;
import ja.project.comp3074.rpguide.fragmentDash.AboutFragment;
import ja.project.comp3074.rpguide.fragmentDash.CharacterFragment;
import ja.project.comp3074.rpguide.fragmentDash.ShopsFragment;


public class DashboardActivity extends AppCompatActivity {
    private final String TAG = "DashboardActivity";
    public static FragmentManager fragman;
    Button toShops, toCharacters, toAbout;
    TextView title;
    Fragment frag;
    private CharacterViewModel cvm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cvm = ViewModelProviders.of(this).get(CharacterViewModel.class);

        setContentView(R.layout.activity_dashboard);

        title = (TextView) findViewById(R.id.tvTitle);

        toShops = (Button) findViewById(R.id.btnToShops);
        toCharacters = (Button) findViewById(R.id.btnToCharacters);
        toAbout = (Button) findViewById(R.id.btnToAbout);

        setButtonListeners();

        //settin' up fragment manager
        fragman = getSupportFragmentManager();

        if(findViewById(R.id.dashFrame)!=null){
            if(savedInstanceState!=null) return;
            frag= new CharacterFragment();
            FragmentTransaction fragTrans = fragman.beginTransaction();
            fragTrans.add(R.id.dashFrame, frag,null);
            fragTrans.commit();
        }
    }

    public void setButtonListeners(){
        toShops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag = new ShopsFragment();
                FragmentTransaction fragTrans = fragman.beginTransaction();
                fragTrans.replace(R.id.dashFrame, frag,"shop");
                title.setText("Shops List");
                fragTrans.commit();
            }
        });
        toCharacters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag= new CharacterFragment();
                FragmentTransaction fragTrans = fragman.beginTransaction();
                fragTrans.replace(R.id.dashFrame, frag,"character");
                title.setText("Characters List");
                fragTrans.commit();
            }
        });
        toAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag = new AboutFragment();
                FragmentTransaction fragTrans = fragman.beginTransaction();
                fragTrans.replace(R.id.dashFrame, frag,"about");
                title.setText("About Page");
                fragTrans.commit();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Fragment frag = fragman.findFragmentById(R.id.dashFrame);
        if(frag!=null){
            FragmentTransaction fragTrans = fragman.beginTransaction();
            frag = new CharacterFragment();
            fragTrans.replace(R.id.dashFrame,frag,null);
            fragTrans.commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
