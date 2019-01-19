package com.example.yanaachrianti.nebengkuy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yanaachrianti.nebengkuy.Model.Buat;

import java.util.ArrayList;

public class listAdapter extends RecyclerView.Adapter<listAdapter.ViewHolder> {

    private Context xContext;
    private ArrayList<Buat> xList;

    listAdapter (Context context, ArrayList<Buat> list){
        xContext = context;
        xList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(xContext);

        View view = layoutInflater.inflate(R.layout.tebengan, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageView img_user = holder.img;
        TextView mKursi, mKet, mJam;

        mKursi = holder.xKursi;
        mKet = holder.xKet;
        mJam = holder.xJam;

    }

    @Override
    public int getItemCount() {
        return xList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView xKursi, xKet, xJam;

        public ViewHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img_user);
            xKursi = itemView.findViewById(R.id.jml_kursi);
            xKet = itemView.findViewById(R.id.keterangan);
            xJam = itemView.findViewById(R.id.jam);
        }
    }
}
