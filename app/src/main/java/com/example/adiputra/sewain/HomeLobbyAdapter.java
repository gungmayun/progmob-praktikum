//package com.example.adiputra.sewain;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//public class HomeLobbyAdapter extends RecyclerView.Adapter<HomeLobbyAdapter.HomeLobbyViewHolder> {
//    private ArrayList<HomeLobby> dataList;
//
//    public HomeLobbyAdapter(ArrayList<HomeLobby> dataList) {
//        this.dataList = dataList;
//    }
//
//    @Override
//    public HomeLobbyAdapter.HomeLobbyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View view = layoutInflater.inflate(R.layout.list_chat_lobby, parent, false);
//        return new HomeLobbyAdapter.HomeLobbyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(HomeLobbyAdapter.HomeLobbyViewHolder holder, int position) {
//        holder.imgProfile.setImageResource(dataList.get(position).getGambar());
//        holder.txtJenis.setText(dataList.get(position).getJenis());
//        holder.txtCC.setText(dataList.get(position).getCC());
//        holder.txtHarga.setText(dataList.get(position).getHarga());
//        holder.txtAlamat.setText(dataList.get(position).getAlamat());
//    }
//
//    @Override
//    public int getItemCount() {
//        return (dataList != null) ? dataList.size() : 0;
//    }
//
//    public class HomeLobbyViewHolder extends RecyclerView.ViewHolder{
//        private TextView txtJenis, txtCC, txtHarga, txtAlamat;
//        private ImageView imgProfile;
//
//        public HomeLobbyViewHolder(View itemView) {
//            super(itemView);
//            imgProfile = (ImageView) itemView.findViewById(R.id.img_chat_lobby);
//            txtJenis = (TextView) itemView.findViewById(R.id.tv_name_chat_lobby);
//            txtCC = (TextView) itemView.findViewById(R.id.tv_msg_chat_lobby);
//            txtHarga = (TextView) itemView.findViewById(R.id.tv_msg_chat_lobby);
//            txtAlamat = (TextView) itemView.findViewById(R.id.tv_msg_chat_lobby);
//        }
//    }
//}
