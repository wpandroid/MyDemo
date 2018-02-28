package com.example.stopgap.mydemo.module.team.Asn;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.stopgap.mydemo.R;
import com.example.stopgap.mydemo.adapter.ZhuangbiListAdapter;
import com.example.stopgap.mydemo.model.TeamNewsResult;
import com.example.stopgap.mydemo.network.Network;
import com.example.stopgap.mydemo.view.SinaRefreshFooter;
import com.example.stopgap.mydemo.view.SinaRefreshHeader;
import com.lcodecore.tkrefreshlayout.IBottomView;
import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.wang.avi.AVLoadingIndicatorView;

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
public abstract class AsnFragment extends Fragment {

    protected Disposable disposable;
    @BindView(R.id.gridRv)
    RecyclerView gridRv;

    Unbinder unbinder;
    ZhuangbiListAdapter adapter = new ZhuangbiListAdapter();
    protected boolean isViewInitiated;
    protected boolean isVisibleToUser;
    protected boolean isDataInitiated;
    String next = "";
    @BindView(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_asn, container, false);
        unbinder = ButterKnife.bind(this, view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        gridRv.setLayoutManager(layoutManager);
        gridRv.setAdapter(adapter);



        refreshLayout.setOverScrollTopShow(false);
        refreshLayout.setAutoLoadMore(true);

        IHeaderView headerView=new SinaRefreshHeader(getActivity());
        refreshLayout.setHeaderView(headerView);

        IBottomView footerView=new SinaRefreshFooter(getActivity());
        refreshLayout.setBottomView(footerView);


        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter(){
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                next = "";
                unsubscribe();
                search("加载");
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                search("加载更多");
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    void search(final String load) {
        disposable = Network.getTeamNewsApi()
                .getTeamNews(getTeamNum(), next)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TeamNewsResult>() {
                    @Override
                    public void accept(@NonNull TeamNewsResult images) throws Exception {
                        if (load.equals("加载")) {
                            adapter.setImages(images.beauties);
                            refreshLayout.finishRefreshing();
                        } else {
                            adapter.AddFooterItem(images.beauties);
                            refreshLayout.finishLoadmore();
                        }

                        String[] strs = images.next.split("next=");
                        next = strs[1].toString();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        refreshLayout.finishRefreshing();
                        refreshLayout.finishLoadmore();
                        Toast.makeText(getActivity(), "加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    protected void unsubscribe() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    //查看这个fragment的可见状态
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }

    public boolean prepareFetchData() {
        return prepareFetchData(false);
    }

    public boolean prepareFetchData(boolean forceUpdate) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            //网络请求
            if (adapter.images == null || adapter.images.size() == 0) {
                adapter.setImages(null);
                refreshLayout.startRefresh();
            }
            isDataInitiated = true;
            return true;
        }
        return false;
    }


    protected abstract int getTeamNum();

}
