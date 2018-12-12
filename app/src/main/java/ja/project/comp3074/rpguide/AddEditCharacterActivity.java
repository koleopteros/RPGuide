package ja.project.comp3074.rpguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddEditCharacterActivity extends AppCompatActivity {
    public static final String EXTRA_NAME="name";
    public static final String EXTRA_RACE="race";
    public static final String EXTRA_JOB="job";
    public static final String EXTRA_DESC="desc";
    public static final String EXTRA_RATE="rate";
    public static final String EXTRA_RCNT="count";

    private ArrayList<EditText> etValues;
    private Button btnNewCharToDash, btnAddNewChar;
    private int rate, ratingCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_character);
        etValues = new ArrayList<>();
        rate = 0;
        ratingCount = 0;
        //Just storing everything into an arrayList to loop thru
        etValues.add((EditText) findViewById(R.id.etNewName));//0
        etValues.add((EditText) findViewById(R.id.etNewRace));//1
        etValues.add((EditText) findViewById(R.id.etNewJob));//2
        etValues.add((EditText) findViewById(R.id.etNewDesc));//3

        etValues.get(0).setEnabled(true);
        etValues.get(0).setFocusable(true);

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
        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_NAME)){
            TextView title = findViewById(R.id.tvNewCharTitle);
            etValues.get(0).setText(intent.getStringExtra(EXTRA_NAME));
            etValues.get(0).setEnabled(false);
            etValues.get(0).setFocusable(false);
            title.setText(getString(R.string.editCharacterTitle));
            etValues.get(1).setText(intent.getStringExtra(EXTRA_RACE));
            etValues.get(2).setText(intent.getStringExtra(EXTRA_JOB));
            etValues.get(3).setText(intent.getStringExtra(EXTRA_DESC));
            rate = intent.getIntExtra(EXTRA_RATE,0);
            ratingCount = intent.getIntExtra(EXTRA_RCNT,0);
            btnAddNewChar.setText(getString(R.string.editCharacterButton));
        }
    }
    public void goBack(){
        setResult(RESULT_CANCELED);
        finish();
    }
    public void createTheDude(){
        Intent data = new Intent();
        String name = etValues.get(0).getText().toString();
        String race = etValues.get(1).getText().toString();
        String job = etValues.get(2).getText().toString();
        String desc = etValues.get(3).getText().toString();

        if(name.trim().isEmpty()||race.trim().isEmpty()||job.trim().isEmpty()){
            Toast.makeText(this, "You need at least a NAME, RACE, and JOB!",Toast.LENGTH_SHORT).show();
            return;
        }
        data.putExtra(EXTRA_NAME,name);
        data.putExtra(EXTRA_RACE,race);
        data.putExtra(EXTRA_JOB,job);
        data.putExtra(EXTRA_DESC,desc);
        data.putExtra(EXTRA_RATE,rate);
        data.putExtra(EXTRA_RCNT,ratingCount);

        setResult(RESULT_OK,data);
        finish();
    }
}
