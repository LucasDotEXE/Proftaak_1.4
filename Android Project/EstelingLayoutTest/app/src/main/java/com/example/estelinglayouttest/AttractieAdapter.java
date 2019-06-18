package com.example.estelinglayouttest;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class AttractieAdapter extends RecyclerView.Adapter<AttractieAdapter.AttractieViewHolder> {

    private ArrayList<Attractie> dataset;
    private Context context;

    public AttractieAdapter(ArrayList<Attractie> dataset, Context context) {
        this.dataset = dataset;
        this.context = context;
    }

    @NonNull
    @Override
    public AttractieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_item, parent, false);
        return new AttractieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttractieViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ISNULL? "+(holder.attractie==null));
        int resid = holder.itemView.getResources().getIdentifier(dataset.get(holder.getAdapterPosition()).getImageUrl(), "drawable", holder.itemView.getContext().getPackageName());
        Attractie attractie = dataset.get(position);
        holder.attractie.setText(attractie.getName());
        holder.imageView.setImageResource(resid);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class AttractieViewHolder extends RecyclerView.ViewHolder {

        public TextView attractie;
        public ImageView imageView;

        public AttractieViewHolder(View itemView) {
            super(itemView);
            attractie =  itemView.findViewById(R.id.name);
            imageView =  itemView.findViewById(R.id.gwb_avatar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(
                            view.getContext(),
                            DetailedAtractieActivity.class);
                    Log.i("POSITION", "" + AttractieViewHolder.super.getAdapterPosition());

                    // Get GWB object waarop is geclicked
                    Attractie gwb = dataset.get(AttractieViewHolder.super.getAdapterPosition());
                    intent.putExtra("GWB_OBJECT", gwb);

                    // Start de nieuwe activity
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}
