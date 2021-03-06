package com.example.mnoer.filmku;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ExampleViewHolder> {
    private Context mContext;
    private ArrayList<Item> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void OnIntemClick (int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;

    }

    public Adapter(Context context, ArrayList<Item> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.movie_item, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        Item currentItem = mExampleList.get(position);

        String imageUrl = currentItem.getmImageUrl();
        String creatorName = currentItem.getmTitle();
        String overview = currentItem.getmOverview();


        holder.mTVCreator.setText(creatorName);
        holder.mTVOverview.setText(overview);
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public Button mId;
        public ImageView mImageView;
        public TextView mTVCreator;
        public TextView mTVOverview;
        public ExampleViewHolder(View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.image_view);
            mTVCreator = itemView.findViewById(R.id.tv_title);
            mTVOverview = itemView.findViewById(R.id.tv_overview);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (mListener !=null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.OnIntemClick(position);
                        }
                    }
                }
            });
        }
    }

}
