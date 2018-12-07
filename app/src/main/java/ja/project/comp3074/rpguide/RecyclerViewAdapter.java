package ja.project.comp3074.rpguide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ja.project.comp3074.rpguide.obj.ListInterface;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<ListInterface> items;
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<ListInterface> items, Context mContext) {
        this.items = items;
        this.mContext = mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView rowName;
        TextView rowDetail;
        ConstraintLayout parentLayout;
        //holds widgets in memory
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowName = itemView.findViewById(R.id.txtRowName);
            rowDetail = itemView.findViewById(R.id.txtRowDetail);
            parentLayout = itemView.findViewById(R.id.rowLayout);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG,"onBindViewHolder called");
        holder.rowName.setText(items.get(position).getName());
        holder.rowDetail.setText(items.get(position).getSubDetail());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: clicked on "+items.get(position).getName());

            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
