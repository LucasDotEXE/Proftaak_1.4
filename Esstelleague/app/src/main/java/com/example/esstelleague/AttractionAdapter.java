package com.example.esstelleague;

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

/**
 * @author Daphne
 * @author Lucas
 * With the AttractionAdapter a recyclerView can be made.
 */

public class AttractionAdapter extends RecyclerView.Adapter<AttractionAdapter.AttractionViewHolder> {

    private ArrayList<Attraction> mDataset;
    private Context mContext;

    /**
     * @param mDataset List of Attraction instances.
     * @param mContext Application context.
     */

    AttractionAdapter(ArrayList<Attraction> mDataset, Context mContext) {
        this.mDataset = mDataset;
        this.mContext = mContext;
    }

    /**
     * ViewHolder for in the RecyclerView
     * @param parent Parent obj.
     * @param viewType ViewType obj.
     * @return new AttractionViewHolder which contains the attraction.
     */

    @NonNull
    @Override
    public AttractionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_item, parent, false);
        return new AttractionViewHolder(view);
    }

    /**
     * Bind the viewHolder.
     * @param holder AttractionViewHolder object.
     * @param position Position of the adapter.
     */

    @Override
    public void onBindViewHolder(@NonNull AttractionViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ISNULL? " + (holder.mAttraction == null));
        int resid = holder.itemView.getResources().getIdentifier(mDataset.get(holder.getAdapterPosition()).getmImageUrl(), "drawable", holder.itemView.getContext().getPackageName());
        Attraction attractie = mDataset.get(position);
        holder.mAttraction.setText(attractie.getmName());
        holder.mImageView.setImageResource(resid);
    }

    /**
     * @return Size of the dataset.
     */

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    /**
     * AttractionViewHolder for in the RecyclerView.
     */

    class AttractionViewHolder extends RecyclerView.ViewHolder {

        TextView mAttraction;
        ImageView mImageView;

        AttractionViewHolder(View itemView) {
            super(itemView);
            mAttraction = itemView.findViewById(R.id.mName);
            mImageView = itemView.findViewById(R.id.rv_item);

            itemView.setOnClickListener(view -> {
                itemView.startAnimation(Animation.getFadeOut(mContext));
                itemView.postOnAnimationDelayed(() -> {
                    Intent intent = new Intent(
                            view.getContext(),
                            DetailedAttractionActivity.class);
                    Log.i("POSITION", "" + AttractionViewHolder.super.getAdapterPosition());

                Attraction attraction = mDataset.get(AttractionViewHolder.super.getAdapterPosition());
                intent.putExtra("ATTRACTION_OBJECT", attraction);
                if (MainActivity.DARKTHEME) {
                    intent.putExtra("theme", "dark");
                } else {
                    intent.putExtra("theme", "light");
                }
                    // Start de nieuwe activity
                    view.getContext().startActivity(intent);
                }, 1000);
            });
        }
    }
}
