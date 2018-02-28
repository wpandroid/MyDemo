package com.example.stopgap.mydemo.adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.stopgap.mydemo.R;
import com.example.stopgap.mydemo.VideoActivity;
import com.example.stopgap.mydemo.model.TeamNews;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhuangbiListAdapter extends RecyclerView.Adapter {
    public List<TeamNews> images;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new DebounceViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        DebounceViewHolder debounceViewHolder = (DebounceViewHolder) holder;
        final TeamNews image = images.get(position);
        debounceViewHolder.descriptionTv.setText(image.title);
        debounceViewHolder.comment.setText(image.comments_total);
        Glide.with(holder.itemView.getContext())
                .load(image.litpic)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(debounceViewHolder.image);

        debounceViewHolder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.itemView.getContext(), VideoActivity.class);
                intent.putExtra("url",image.aid);
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return images == null ? 0 : images.size();
    }

    public void setImages(List<TeamNews> images) {
        this.images = images;
        notifyDataSetChanged();
    }

    static class DebounceViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.descriptionTv)
        TextView descriptionTv;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.comment)
        TextView comment;
        @BindView(R.id.cardview)
        CardView cardview;

        public DebounceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public void AddFooterItem(List<TeamNews> items) {
        images.addAll(items);
        notifyDataSetChanged();
    }


}
