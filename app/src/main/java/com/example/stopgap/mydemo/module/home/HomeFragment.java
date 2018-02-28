package com.example.stopgap.mydemo.module.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.stopgap.mydemo.R;
import com.example.stopgap.mydemo.adapter.LiveAdapter;
import com.example.stopgap.mydemo.adapter.RankAdapter;
import com.example.stopgap.mydemo.gallary.Item;
import com.example.stopgap.mydemo.gallary.Shop;
import com.example.stopgap.mydemo.gallary.ShopAdapter;
import com.example.stopgap.mydemo.model.LiveResult;
import com.example.stopgap.mydemo.model.RankResult;
import com.example.stopgap.mydemo.network.Network;
import com.example.stopgap.mydemo.util.DiscreteScrollViewOptions;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.Orientation;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.List;

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
public class HomeFragment extends Fragment {
    protected Disposable disposable;
    Unbinder unbinder;
    @BindView(R.id.scrollview)
    DiscreteScrollView scrollview;
    @BindView(R.id.rv_live)
    RecyclerView rvLive;
    @BindView(R.id.srl_home)
    SwipeRefreshLayout srlHome;

    private List<Item> data;
    private Shop shop;
    private InfiniteScrollAdapter infiniteAdapter;
    LiveAdapter liveAdapter = new LiveAdapter();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        //滑动广告栏
        shop = Shop.get();
        data = shop.getData();
        scrollview.setOrientation(Orientation.HORIZONTAL);
        infiniteAdapter = InfiniteScrollAdapter.wrap(new ShopAdapter(data));
        scrollview.setAdapter(infiniteAdapter);
        scrollview.setItemTransitionTimeMillis(DiscreteScrollViewOptions.getTransitionTime());
        scrollview.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 2);
        rvLive.setLayoutManager(gridLayoutManager);
        rvLive.setAdapter(liveAdapter);
        rvLive.setNestedScrollingEnabled(false);

        srlHome.setColorSchemeResources(R.color.colorPrimary);
        srlHome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                searchLive();
            }
        });
        srlHome.setRefreshing(true);
        searchLive();
        return view;
    }


    void searchLive() {
        disposable = Network.getLiveApi()
                .getRank("android", "134")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LiveResult>() {
                    @Override
                    public void accept(@NonNull LiveResult liveResult) throws Exception {
                        srlHome.setRefreshing(false);
                        liveAdapter.setImages(liveResult.list);
                       /* rankAdapter=new RankAdapter(getContext(), R.layout.item_rank, rank.content.rounds.get(0).content.data);
                        rvLive.setAdapter(rankAdapter);*/
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        srlHome.setRefreshing(false);
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
