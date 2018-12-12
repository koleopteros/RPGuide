package ja.project.comp3074.rpguide.database.characters;

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

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterHolder> {
    private List<Characters> characters = new ArrayList<>();
    private OnItemClickListener listener;
    @NonNull
    @Override
    public CharacterHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_cards, parent, false);
        return new CharacterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final CharacterHolder holder, final int i) {
        final Characters currentCharacter = characters.get(i);
        holder.textName.setText(currentCharacter.getName());
        holder.textDesc.setText(currentCharacter.getSubDetail());
        holder.btnOne.setText("Upvote");
        holder.btnTwo.setText("Downvote");
        holder.btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                characters.get(i).upvote();
                holder.textDesc.setText(currentCharacter.getSubDetail());
                notifyDataSetChanged();
            }
        });
        holder.btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                characters.get(i).downvote();
                holder.textDesc.setText(currentCharacter.getSubDetail());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public void setCharacters(List<Characters> c) {
        this.characters = c;
        notifyDataSetChanged();
    }

    public Characters getCharacterAt(int position) {
        return characters.get(position);
    }

    class CharacterHolder extends RecyclerView.ViewHolder {
        private TextView textName, textDesc;
        private Button btnOne, btnTwo;

        public CharacterHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.tvRVName);
            textDesc = itemView.findViewById(R.id.tvRVSubDetail);
            btnOne = itemView.findViewById(R.id.btnRVone);
            btnTwo = itemView.findViewById(R.id.btnRVtwo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(characters.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Characters c);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }
}
