package com.example.adiputra.sewain;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private ArrayList<History> dataList;

    public HistoryAdapter(ArrayList<History> dataList) {
        this.dataList = dataList;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        holder.imgMotor.setImageResource(dataList.get(position).getGambar());
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtAlamat.setText(dataList.get(position).getAlamat());
        holder.txtStatus.setText(dataList.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama, txtAlamat, txtStatus;
        private ImageView imgMotor;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            imgMotor = (ImageView) itemView.findViewById(R.id.img_history_motor);
            txtNama = (TextView) itemView.findViewById(R.id.tv_history_nama);
            txtAlamat = (TextView) itemView.findViewById(R.id.tv_history_alamat);
            txtStatus = (TextView) itemView.findViewById(R.id.tv_history_status);
        }
    }
}
