package com.example.stopgap.mydemo.module.rank;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.stopgap.mydemo.R;
import com.example.stopgap.mydemo.adapter.RankAdapter;
import com.example.stopgap.mydemo.model.RankResult;
import com.example.stopgap.mydemo.network.Network;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankFragment extends Fragment {
    protected Disposable disposable;
    @BindView(R.id.lv_rank)
    ListView lvRank;
    Unbinder unbinder;
    @BindView(R.id.srl_rank)
    SwipeRefreshLayout srlRank;
    RankAdapter rankAdapter;

    public RankFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_rank, container, false);
        unbinder = ButterKnife.bind(this, view);
        srlRank.setColorSchemeResources(R.color.colorPrimary);
        srlRank.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                search();
            }
        });
        srlRank.setRefreshing(true);
        search();
        return view;
    }

    void search() {
        disposable = Network.getRankApi()
                .getRank("9235", "133", "data_tab", "total_ranking")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RankResult>() {
                    @Override
                    public void accept(@NonNull RankResult rank) throws Exception {
                        srlRank.setRefreshing(false);
                        rankAdapter=new RankAdapter(getContext(), R.layout.item_rank, rank.content.rounds.get(0).content.data);
                        lvRank.setAdapter(rankAdapter);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        srlRank.setRefreshing(false);
                        System.out.println("加载失败" + throwable);
                        Toast.makeText(getActivity(), "加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
