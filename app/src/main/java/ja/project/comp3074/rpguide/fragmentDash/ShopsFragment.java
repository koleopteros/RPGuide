package ja.project.comp3074.rpguide.fragmentDash;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ja.project.comp3074.rpguide.R;
import ja.project.comp3074.rpguide.database.shops.ShopAdapter;
import ja.project.comp3074.rpguide.database.shops.ShopViewModel;
import ja.project.comp3074.rpguide.database.shops.Shops;

public class ShopsFragment extends Fragment {

    private ShopViewModel svm;
    private RecyclerView rv;
    private ShopAdapter adapter;
    private List<Shops> data;

    public ShopsFragment() {
        // Required empty public constructor
    }
    public void onCreate(Bundle savedInstanceState){ super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shops, container, false);

        rv = (RecyclerView) view.findViewById(R.id.rvCharacterView);
        svm = ViewModelProviders.of(getActivity()).get(ShopViewModel.class);
        data = svm.getAllData();
        setRecyclerView();

        // Inflate the layout for this fragment
        return view;
    }
    private void setRecyclerView(){
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);

        adapter = new ShopAdapter(data);
        rv.setAdapter(adapter);
    }
}
