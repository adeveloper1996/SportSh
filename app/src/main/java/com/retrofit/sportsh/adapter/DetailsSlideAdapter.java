package com.retrofit.sportsh.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.retrofit.sportsh.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailsSlideAdapter extends PagerAdapter {

    private List<String> photo;
    private LayoutInflater inflater;
    private Context context;

    public DetailsSlideAdapter(Context context,List<String> photo) {
        this.context = context;
        this.photo = photo;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return photo.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slide_image_view, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image_slide);
        Picasso.with(context).load(photo.get(position)).into(imageView);

        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}