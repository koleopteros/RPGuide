package ja.project.comp3074.rpguide.fragmentDash;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import ja.project.comp3074.rpguide.R;
import ja.project.comp3074.rpguide.database.shops.Shops;
import ja.project.comp3074.rpguide.database.shops.ShopsAdapter;

public class ShopsFragment extends Fragment {
    private ListView lv;
    private SearchView sv;
    private ArrayList<Shops> data;

    private ShopsAdapter adapter;

    public ShopsFragment() {
        // Required empty public constructor
    }
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shops, container, false);

        lv = view.findViewById(R.id.lvShops);
        populateData();

        adapter = new ShopsAdapter(getActivity(),data);

        lv.setAdapter(adapter);

        setListClickers();

        // Inflate the layout for this fragment
        return view;
    }

    private void populateData(){
        data = new ArrayList<>();
        data.add(new Shops("The Sword & Board", "1193 Bloor St W Toronto ON", "647-350-7529", "Open Public Playspace"));
        data.add(new Shops("401 Games Downtown","518 Yonge St Toronto ON","416-599-6447", "Open Public Playspace"));
        data.add(new Shops("401 Games Vaughan","7700 Keele St Concord ON","416-599-6447", "Open Public Playspace"));
    }
    private void setListClickers(){
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog ad = buildListAlert(data.get(position));
                ad.show();
            }
        });
    }
    public AlertDialog buildListAlert(final Shops item){
        AlertDialog.Builder bobTheBuilder = new AlertDialog.Builder(getActivity());
        bobTheBuilder.setTitle("Maps!");
        bobTheBuilder.setMessage("View on map, or navigate on map?");
        bobTheBuilder.setPositiveButton("Navigate", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent toMapsWeGo = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q="+Uri.encode(item.getAddress())));
                startActivity(toMapsWeGo);
                dialog.dismiss();
            }
        });
        bobTheBuilder.setNeutralButton("View on Maps", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent toMapsWeGo = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q="+Uri.encode(item.getAddress())));
                startActivity(toMapsWeGo);
                dialog.dismiss();
            }
        });

        return bobTheBuilder.create();
    }

}
