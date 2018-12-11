package ja.project.comp3074.rpguide;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import ja.project.comp3074.rpguide.database.characters.CharacterViewModel;
import ja.project.comp3074.rpguide.obj.characters.Characters;

public class AddNewCharacterActivity extends AppCompatActivity {
    private ArrayList<EditText> etValues;
    private Button btnNewCharToDash, btnAddNewChar;
    private CharacterViewModel characterViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_character);
        etValues = new ArrayList<>();
        //Just storing everything into an arrayList to loop thru
        etValues.add((EditText) findViewById(R.id.etNewName));//0
        etValues.add((EditText) findViewById(R.id.etNewRace));//1
        etValues.add((EditText) findViewById(R.id.etNewJob));//2
        etValues.add((EditText) findViewById(R.id.etNewLevel));//3
        etValues.add((EditText) findViewById(R.id.etNewStr));//4
        etValues.add((EditText) findViewById(R.id.etNewDex));//5
        etValues.add((EditText) findViewById(R.id.etNewCon));//6
        etValues.add((EditText) findViewById(R.id.etNewInt));//7
        etValues.add((EditText) findViewById(R.id.etNewWis));//8
        etValues.add((EditText) findViewById(R.id.etNewCha));//9
        etValues.add((EditText) findViewById(R.id.etNewDesc));//10

        btnNewCharToDash = (Button) findViewById(R.id.btnNewCharToDash);
        btnAddNewChar = (Button) findViewById(R.id.btnAddNewChar);

        btnNewCharToDash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        btnAddNewChar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTheDude();
            }
        });

    }
    public void goBack(){
        Intent dashboard = new Intent(getApplicationContext(),DashboardActivity.class);
        startActivity(dashboard);
        finish();
    }
    public void createTheDude(){
        SharedPreferences sp = getSharedPreferences("mrUser", Context.MODE_PRIVATE);
        long userID = sp.getLong("userID",-1);
        String name,race,job,desc;
        int lv,str,dex,con,intel,wis,cha;
        name = (String) etValues.get(0).getText().toString();
        race = (String) etValues.get(1).getText().toString();
        job = (String) etValues.get(2).getText().toString();
        lv = Integer.parseInt(etValues.get(3).getText().toString());
        str =Integer.parseInt(etValues.get(4).getText().toString());
        dex = Integer.parseInt(etValues.get(5).getText().toString());
        con = Integer.parseInt(etValues.get(6).getText().toString());
        intel = Integer.parseInt(etValues.get(7).getText().toString());
        wis = Integer.parseInt(etValues.get(8).getText().toString());
        cha = Integer.parseInt(etValues.get(9).getText().toString());
        desc = (String) etValues.get(10).getText().toString();

        Characters newGuy = new Characters(userID,name,race,job,lv,str,dex,con,intel,wis,cha,desc);

        characterViewModel.insert(newGuy);
        AlertDialog ad = constructiconsFormDevastator(newGuy);
        ad.show();
    }

    public AlertDialog constructiconsFormDevastator (final Characters c){
        android.support.v7.app.AlertDialog.Builder bobTheBuilder = new android.support.v7.app.AlertDialog.Builder(this);
        bobTheBuilder.setTitle("You've created "+c.getName()+"!");
        bobTheBuilder.setMessage("Share to:");
        bobTheBuilder.setPositiveButton("Twitter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = "I just created my newest character, "+c.getName();
                Intent tweet = new Intent(Intent.ACTION_VIEW);
                tweet.setData(Uri.parse("http://twitter.com/?status=" + Uri.encode(message)));
                startActivity(tweet);
                dialog.dismiss();
            }
        });
        bobTheBuilder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return bobTheBuilder.create();
    }
}
