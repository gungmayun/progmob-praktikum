package com.example.adiputra.sewain;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatLobbyAdapter extends RecyclerView.Adapter<ChatLobbyAdapter.ChatLobbyViewHolder> {
    private ArrayList<ChatLobby> dataList;

    public ChatLobbyAdapter(ArrayList<ChatLobby> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ChatLobbyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_chat_lobby, parent, false);
        return new ChatLobbyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatLobbyViewHolder holder, int position) {
        holder.imgProfile.setImageResource(dataList.get(position).getGambar());
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtPesan.setText(dataList.get(position).getPesan());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ChatLobbyViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama, txtPesan;
        private ImageView imgProfile;

        public ChatLobbyViewHolder(View itemView) {
            super(itemView);
            imgProfile = (ImageView) itemView.findViewById(R.id.img_chat_lobby);
            txtNama = (TextView) itemView.findViewById(R.id.tv_name_chat_lobby);
            txtPesan = (TextView) itemView.findViewById(R.id.tv_msg_chat_lobby);
        }
    }
}
