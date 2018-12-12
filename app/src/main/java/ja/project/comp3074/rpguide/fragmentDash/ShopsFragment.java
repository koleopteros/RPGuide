package ja.project.comp3074.rpguide.fragmentDash;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ja.project.comp3074.rpguide.R;
import ja.project.comp3074.rpguide.database.shops.Shops;

public class ShopsFragment extends Fragment {

    private ShopViewModel svm;
    private RecyclerView rv;
    private ShopAdapter adapter;
    private List<Shops> data;
    private View view;
    public ShopsFragment() {
        // Required empty public constructor
    }
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        svm = ViewModelProviders.of(getActivity()).get(ShopViewModel.class);

        data = svm.getAllData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shops, container, false);

        rv = (RecyclerView) view.findViewById(R.id.rvShopsView);
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
