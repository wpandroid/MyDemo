package com.example.stopgap.mydemo.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.stopgap.mydemo.R;
import com.example.stopgap.mydemo.model.LiveResult;
import com.example.stopgap.mydemo.view.LiveActivity;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LiveAdapter extends RecyclerView.Adapter {
    public List<LiveResult.Livelist> livelists;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_live, parent, false);
        return new DebounceViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        DebounceViewHolder debounceViewHolder = (DebounceViewHolder) holder;
        final LiveResult.Livelist list = livelists.get(position);
        debounceViewHolder.livetitleTv.setText(list.program_summary);
        debounceViewHolder.usernameTv.setText(list.user.name);
        debounceViewHolder.usernumsTv.setText(list.online_num.replace(",", "") + "人");

        Glide.with(holder.itemView.getContext())
                .load(list.live_cover)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(debounceViewHolder.liveIv);


        Glide.with(holder.itemView.getContext())
                .load(list.user.url)
                .into(debounceViewHolder.userimageIv);

        debounceViewHolder.liveLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), LiveActivity.class);
                intent.putExtra("url", list.relate_id);
                intent.putExtra("title", list.program_summary);
                intent.putExtra("bgurl", list.live_cover);
                ActivityOptionsCompat compat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) holder.itemView.getContext(),
                                holder.itemView, "image");
                holder.itemView.getContext().startActivity(intent,compat.toBundle());
            }
        });

    }

    @Override
    public int getItemCount() {
        return livelists == null ? 0 : livelists.size();
    }


    static class DebounceViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.live_iv)
        ImageView liveIv;
        @BindView(R.id.livetitle_tv)
        TextView livetitleTv;
        @BindView(R.id.userimage_iv)
        RoundedImageView userimageIv;
        @BindView(R.id.username_tv)
        TextView usernameTv;
        @BindView(R.id.usernums_tv)
        TextView usernumsTv;
        @BindView(R.id.live_ln)
        LinearLayout liveLn;



        public DebounceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public void setImages(List<LiveResult.Livelist> livelists) {
        this.livelists = livelists;
        notifyDataSetChanged();
    }


}
