package com.example.adiputra.sewain;


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
public class ChatFragment extends Fragment {
    private RecyclerView recyclerView;
    private ChatLobbyAdapter adapter;
    private ArrayList<ChatLobby> chatLobbyArrayList;

    public ChatFragment() {
        // recycle view chat fragment
    }

    void addData(){
        chatLobbyArrayList = new ArrayList<>();
        chatLobbyArrayList.add(new ChatLobby(R.drawable.profile, "Adi Putra","Halo, posisi dimana ya?"));
        chatLobbyArrayList.add(new ChatLobby(R.drawable.profile2, "Putu Balik","Mas, saya tungguin di depan rumah ya, pas di pertigaan pertama belok kanan aja langsung"));
        chatLobbyArrayList.add(new ChatLobby(R.drawable.profile3, "Komang Bagus","Motornya sudah dicuci belum pak?"));

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        addData();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        adapter = new ChatLobbyAdapter(chatLobbyArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

}
