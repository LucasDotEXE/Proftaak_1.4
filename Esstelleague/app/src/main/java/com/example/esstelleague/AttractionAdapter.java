package com.example.esstelleague;

import android.content.Context;
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

public class AttractionAdapter extends RecyclerView.Adapter<AttractionAdapter.AttractieViewHolder> {

    private ArrayList<Attraction> mDataset;
    private Context mContext;

    public AttractionAdapter(ArrayList<Attraction> mDataset, Context mContext) {
        this.mDataset = mDataset;
        this.mContext = mContext;
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
        Log.d(TAG, "onBindViewHolder: ISNULL? "+(holder.mAttractie ==null));
        int resid = holder.itemView.getResources().getIdentifier(mDataset.get(holder.getAdapterPosition()).getmImageUrl(), "drawable", holder.itemView.getContext().getPackageName());
        Attraction attractie = mDataset.get(position);
        holder.mAttractie.setText(attractie.getmName());
        holder.mImageView.setImageResource(resid);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class AttractieViewHolder extends RecyclerView.ViewHolder {

        public TextView mAttractie;
        public ImageView mImageView;

        public AttractieViewHolder(View itemView) {
            super(itemView);
            mAttractie =  itemView.findViewById(R.id.mName);
            mImageView =  itemView.findViewById(R.id.rv_item);
        }
    }
}
