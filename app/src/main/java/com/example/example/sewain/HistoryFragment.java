package com.example.example.sewain;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private ArrayList<History> historyArrayList;

    public HistoryFragment() {
        // Required empty public constructor
    }

    void addData(){
        historyArrayList = new ArrayList<>();
        historyArrayList.add(new History(R.drawable.motor1, "Honda New CBR","Perumahan Andika Graha, Kediri, Tabanan","Sukses"));
        historyArrayList.add(new History(R.drawable.motor2, "Honda Scoopy","Bukit Jimbaran, Badung","Sukses"));
        historyArrayList.add(new History(R.drawable.motor3, "Yamaha Nmax","Perumahan Dalung Permai","Pending"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        addData();

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        adapter = new HistoryAdapter(historyArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

}
