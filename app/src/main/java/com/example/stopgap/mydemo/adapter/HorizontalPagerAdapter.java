package com.example.stopgap.mydemo.adapter;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stopgap.mydemo.R;
import com.example.stopgap.mydemo.util.Utils;

import static com.example.stopgap.mydemo.util.Utils.setupItem;


/**
 * Created by wp on 2017/12/23.
 */




public class HorizontalPagerAdapter extends PagerAdapter {

    private final Utils.LibraryObject[] LIBRARIES = new Utils.LibraryObject[]{
            new Utils.LibraryObject(
                    R.drawable.ic_add_white_24px,
                    "Strategy"
            ),
            new Utils.LibraryObject(
                    R.drawable.ic_arrow,
                    "Design"
            ),
            new Utils.LibraryObject(
                    R.drawable.ic_copy_white_24dp,
                    "Development"
            ),
            new Utils.LibraryObject(
                    R.drawable.ic_dashboard_black_24dp,
                    "Quality Assurance"
            )
    };

    private Context mContext;
    private LayoutInflater mLayoutInflater;


    public HorizontalPagerAdapter(final Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return  LIBRARIES.length;
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view;
        view = mLayoutInflater.inflate(R.layout.item, container, false);
        setupItem(view, LIBRARIES[position]);
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }
}