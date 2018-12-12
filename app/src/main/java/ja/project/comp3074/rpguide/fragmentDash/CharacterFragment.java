package ja.project.comp3074.rpguide.fragmentDash;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import ja.project.comp3074.rpguide.AddEditCharacterActivity;
import ja.project.comp3074.rpguide.EmailActivity;
import ja.project.comp3074.rpguide.R;
import ja.project.comp3074.rpguide.database.characters.CharacterAdapter;
import ja.project.comp3074.rpguide.database.characters.CharacterViewModel;
import ja.project.comp3074.rpguide.database.characters.Characters;
import ja.project.comp3074.rpguide.utils.EmailHandler;

import static android.app.Activity.RESULT_OK;
import static ja.project.comp3074.rpguide.AddEditCharacterActivity.EXTRA_RATE;
import static ja.project.comp3074.rpguide.AddEditCharacterActivity.EXTRA_RCNT;

public class CharacterFragment extends Fragment {
    public static final int ADD_CHARACTER_REQUEST = 1;
    public static final int EDIT_CHARACTER_REQUEST = 2;
    public static final int SEND_EMAIL_REQUEST = 3;
    private CharacterViewModel cvm;
    private RecyclerView rv;
    private Button btnNewChar;
    private CharacterAdapter adapter;

    public CharacterFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character, container, false);

        rv = (RecyclerView) view.findViewById(R.id.rvCharacterView);
        btnNewChar = (Button) view.findViewById(R.id.btnGoToNewchar);

        setRecyclerView();
        setButtonListeners();

        cvm = ViewModelProviders.of(getActivity()).get(CharacterViewModel.class);
        cvm.getAllCharacters().observe(this, new Observer<List<Characters>>() {
            @Override
            public void onChanged(@Nullable List<Characters> characters) {
                adapter.setCharacters(characters);
                //Toast.makeText(getActivity(), "List size: " + characters.size(), Toast.LENGTH_SHORT).show();
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder holder, int i) {
                cvm.delete(adapter.getCharacterAt(holder.getAdapterPosition()));
                Toast.makeText(getActivity(), "Character Deleted!", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(rv);

        adapter.setOnItemClickListener(new CharacterAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Characters c) {
                Intent intent = new Intent(getActivity(),AddEditCharacterActivity.class);
                intent.putExtra(AddEditCharacterActivity.EXTRA_NAME, c.getName());
                intent.putExtra(AddEditCharacterActivity.EXTRA_RACE, c.getRace());
                intent.putExtra(AddEditCharacterActivity.EXTRA_JOB, c.getJob());
                intent.putExtra(AddEditCharacterActivity.EXTRA_DESC,c.getDesc());
                intent.putExtra(EXTRA_RATE,c.getRating());
                intent.putExtra(AddEditCharacterActivity.EXTRA_RCNT,c.getRatingCount());
                // cvm.update(c);
                startActivityForResult(intent,EDIT_CHARACTER_REQUEST);
            }
        });
        return view;
    }

    private void setRecyclerView() {
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);

        adapter = new CharacterAdapter();
        rv.setAdapter(adapter);
    }

    private void setButtonListeners() {
        btnNewChar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddEditCharacterActivity.class);
                startActivityForResult(intent, ADD_CHARACTER_REQUEST);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CharacterFragment.ADD_CHARACTER_REQUEST && resultCode == RESULT_OK) {
            String name = data.getStringExtra(AddEditCharacterActivity.EXTRA_NAME);
            String race = data.getStringExtra(AddEditCharacterActivity.EXTRA_RACE);
            String job = data.getStringExtra(AddEditCharacterActivity.EXTRA_JOB);
            String desc = data.getStringExtra(AddEditCharacterActivity.EXTRA_DESC);
            int rate = data.getIntExtra(EXTRA_RATE,0);
            int ratingCount = data.getIntExtra(AddEditCharacterActivity.EXTRA_RCNT,0);

            Characters c = new Characters(name, race, job, desc,rate,ratingCount);

            cvm.insert(c);

            Toast.makeText(getActivity(), "Character has been created!", Toast.LENGTH_SHORT).show();

            AlertDialog ad = constructiconsFormDevastator(c);
            ad.show();
        }
        else if (requestCode == CharacterFragment.EDIT_CHARACTER_REQUEST&& resultCode == RESULT_OK){
            String name = data.getStringExtra(AddEditCharacterActivity.EXTRA_NAME);
            String race = data.getStringExtra(AddEditCharacterActivity.EXTRA_RACE);
            String job = data.getStringExtra(AddEditCharacterActivity.EXTRA_JOB);
            String desc = data.getStringExtra(AddEditCharacterActivity.EXTRA_DESC);
            int rate = data.getIntExtra(EXTRA_RATE,0);
            int ratingCount = data.getIntExtra(AddEditCharacterActivity.EXTRA_RCNT,0);
            Characters c = new Characters(name, race, job, desc, rate, ratingCount);
            Toast.makeText(getActivity(),c.getRating()+"",Toast.LENGTH_LONG).show();

            cvm.update(c);

            Toast.makeText(getActivity(),"Character has been updated!",Toast.LENGTH_SHORT).show();
        }
        else if(requestCode == SEND_EMAIL_REQUEST && resultCode == RESULT_OK){
            String fname = data.getStringExtra("recip_fname");
            String lname = data.getStringExtra("recip_lname");
            String email = data.getStringExtra("recip_email");
            String message = data.getStringExtra("recip_msg");
            EmailHandler.sendEmail(fname,lname,email,message);

            Toast.makeText(getActivity(),"E-mail has been sent!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getActivity(),"Request failed",Toast.LENGTH_SHORT).show();
        }
    }

    public AlertDialog constructiconsFormDevastator(final Characters c) {
        android.support.v7.app.AlertDialog.Builder bobTheBuilder = new android.support.v7.app.AlertDialog.Builder(getActivity());
        bobTheBuilder.setTitle("You've created " + c.getName() + "!");
        bobTheBuilder.setMessage("Share to:");
        bobTheBuilder.setPositiveButton("Twitter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = "I just created my newest character, " + c.getName();
                Intent tweet = new Intent(Intent.ACTION_VIEW);
                tweet.setData(Uri.parse("http://twitter.com/?status=" + Uri.encode(message)));
                startActivity(tweet);
                dialog.dismiss();
            }
        });
        bobTheBuilder.setNeutralButton("Email", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(),EmailActivity.class);
                startActivityForResult(intent,SEND_EMAIL_REQUEST);
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