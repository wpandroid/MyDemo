package com.example.stopgap.mydemo.module.team;


import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stopgap.mydemo.R;
import com.example.stopgap.mydemo.module.team.Asn.AsnFragment;
import com.example.stopgap.mydemo.module.team.Asn.FourFragment;
import com.example.stopgap.mydemo.module.team.Asn.OneFragment;
import com.example.stopgap.mydemo.module.team.Asn.ThreeFragment;
import com.example.stopgap.mydemo.module.team.Asn.TwoFragment;
import com.example.stopgap.mydemo.view.CompatViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamFragment extends Fragment {

    @BindView(android.R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewPager)
    CompatViewPager viewPager;
    Unbinder unbinder;

    FragmentTransaction transaction;

    public TeamFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_team, container, false);
        unbinder = ButterKnife.bind(this, view);



        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new OneFragment();
                    case 1:
                        return new TwoFragment();
                    case 2:
                        return new ThreeFragment();
                    case 3:
                        return new FourFragment();
                    default:
                        return new OneFragment();
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return getString(R.string.title_asn);
                    case 1:
                        return getString(R.string.title_mc);
                    case 2:
                        return getString(R.string.title_ml);
                    case 3:
                        return getString(R.string.title_hot);
                    default:
                        return getString(R.string.title_asn);
                }
            }
        });
        tabs.setupWithViewPager(viewPager);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }




}
