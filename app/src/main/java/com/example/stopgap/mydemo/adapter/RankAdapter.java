package com.example.stopgap.mydemo.adapter;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.stopgap.mydemo.R;
import com.example.stopgap.mydemo.model.RankResult;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by wp on 2017/12/27.
 */

public class RankAdapter extends  CommonAdapter <RankResult.Data>{


    public RankAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
    }


    @Override
    protected void convert(ViewHolder viewHolder, RankResult.Data item, int position) {
        viewHolder.setText(R.id.tv_rank, position+1+"");
        viewHolder.setText(R.id.tv_name, item.team_name);
        viewHolder.setText(R.id.tv_matches_total, item.matches_total);
        viewHolder.setText(R.id.tv_matches_won, item.matches_won);
        viewHolder.setText(R.id.tv_matches_draw, item.matches_draw);
        viewHolder.setText(R.id.tv_matches_lost, item.matches_lost);
        viewHolder.setText(R.id.tv_goals, item.goals_pro+"/"+item.goals_against);
        viewHolder.setText(R.id.tv_points, item.points);
        if(position<=3){
            viewHolder.setBackgroundRes(R.id.ln_rank,R.color.colorPrimary);
        }else{
            viewHolder.setBackgroundRes(R.id.ln_rank,R.color.rankback);
        }

        ImageView imageView= viewHolder.getView(R.id.tv_teamlogo);
        Glide.with(viewHolder.getConvertView().getContext())
                .load(item.team_logo)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);

    }
}
