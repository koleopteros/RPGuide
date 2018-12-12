package ja.project.comp3074.rpguide.database.shops;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ja.project.comp3074.rpguide.R;
import ja.project.comp3074.rpguide.obj.shops.Shops;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopHolder> {
    private List<Shops> shops = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public ShopHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_cards,viewGroup,false);
        return new ShopHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopHolder holder, int i) {
        Shops shop = shops.get(i);

        holder.textName.setText(shop.getName());
        holder.textDetails.setText(shop.getSubDetail());

        holder.btnNavigate.setVisibility(View.INVISIBLE);
        holder.btnViewOnMap.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() { return shops.size(); }

    public void setShops(List<Shops> shops){
        this.shops = shops;
        notifyDataSetChanged();
    }

    public Shops shopAt(int position){ return shops.get(position); }

    class ShopHolder extends RecyclerView.ViewHolder{
        private TextView textName;
        private TextView textDetails;
        private Button btnNavigate;
        private Button btnViewOnMap;

        public ShopHolder(View itemView){
            super(itemView);
            textName = itemView.findViewById(R.id.tvRVName);
            textDetails = itemView.findViewById(R.id.tvRVSubDetail);

            btnNavigate = (Button) itemView.findViewById(R.id.btnRVone);
            btnNavigate.setText("Navigate");
            btnViewOnMap = (Button) itemView.findViewById(R.id.btnRVtwo);
            btnViewOnMap.setText("Map it");
        }
    }
    public interface OnItemClickListener {
        void onItemClick(Shops s);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }
}
