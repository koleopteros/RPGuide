package ja.project.comp3074.rpguide.database.shops;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ja.project.comp3074.rpguide.R;

public class ShopsAdapter extends ArrayAdapter<Shops> {
    private ArrayList<Shops> dataset;
    Context context;
    public ShopsAdapter(Context context, ArrayList<Shops> shops){
        super(context,R.layout.row_layout,shops);
        this.context = context;
        this.dataset = shops;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView ==null){
            LayoutInflater inf = LayoutInflater.from(context);
            convertView=inf.inflate(R.layout.row_layout,parent,false);
        }
        TextView name = convertView.findViewById(R.id.txtRowName);
        TextView detail = convertView.findViewById(R.id.txtRowDetail);

        Shops shop = dataset.get(position);
        name.setText(shop.getName());
        detail.setText(shop.getSubDetail());

        return convertView;

    }
}