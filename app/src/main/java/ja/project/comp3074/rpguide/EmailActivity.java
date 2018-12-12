package ja.project.comp3074.rpguide;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EmailActivity extends AppCompatActivity {
    EditText etfname, etlname, etemail, etmessage;
    Button send,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        etfname = findViewById(R.id.etMailFirstName);
        etlname = findViewById(R.id.etMailLastName);
        etemail = findViewById(R.id.etRecipient);
        etmessage = findViewById(R.id.etEmailMessage);

        send = (Button) findViewById(R.id.btnSendEmail);
        back = (Button) findViewById(R.id.btnEmailCancel);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                awayWeGo();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
    public void awayWeGo(){
        Intent data = new Intent();

        String fname = etfname.getText().toString();
        String lname = etlname.getText().toString();
        String email = etemail.getText().toString();
        String message = etmessage.getText().toString();
        if(fname.trim().isEmpty() || email.trim().isEmpty() || message.trim().isEmpty()){
            Toast.makeText(this,"Recipient NAME, EMAIL and MESSAGE are all required!!",Toast.LENGTH_SHORT).show();
            return;
        }

        data.putExtra("recip_fname",fname);
        data.putExtra("recip_lname",lname);
        data.putExtra("recip_email",email);
        data.putExtra("recip_msg",message);

        setResult(RESULT_OK,data);
        finish();
    }
}
