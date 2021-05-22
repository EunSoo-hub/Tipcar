package com.mit.tipcar;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RecyclerViewCustomAdapter extends RecyclerView.Adapter<RecyclerViewCustomAdapter.CustomViewHolder> {

    private ArrayList<RecyclerView_Dictionary> mList;
    private byte[] byteArray;
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected android.widget.TextView title;
        protected ImageView image;
        protected android.widget.TextView content;


        public CustomViewHolder(android.view.View view) {
            super(view);
            this.title = (android.widget.TextView) view.findViewById(R.id.Content_user);
            this.image = (ImageView) view.findViewById(R.id.Content_image);
            this.content = (android.widget.TextView) view.findViewById(R.id.Content_title);
        }
    }


    public RecyclerViewCustomAdapter(ArrayList<RecyclerView_Dictionary> list) {
        this.mList = list;
    }



    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        android.view.View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull CustomViewHolder viewholder, int position) {

        if(viewholder.image == null){
            viewholder.image.setVisibility(android.view.View.GONE);
        }else{
            viewholder.image.setVisibility(android.view.View.VISIBLE);
        }

        viewholder.title.setText(mList.get(position).getTitle());
        viewholder.content.setText(mList.get(position).getContent());
        viewholder.image.setImageDrawable(mList.get(position).getImage());


    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}