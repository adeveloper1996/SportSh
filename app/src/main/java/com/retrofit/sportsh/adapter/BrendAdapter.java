package com.retrofit.sportsh.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.retrofit.sportsh.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BrendAdapter extends RecyclerView.Adapter<BrendAdapter.BrandHolder>{

    private List<Integer> drawables;
    private Context context;

    public BrendAdapter(Context context) {
        this.context = context;

        drawables = new ArrayList<>();
        drawables.add(R.drawable.reebok);
        drawables.add(R.drawable.nike);
        drawables.add(R.drawable.adidas);
        drawables.add(R.drawable.kappa);
        drawables.add(R.drawable.puma);
    }

    public class BrandHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public BrandHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img_home_brand);
        }
    }

    @Override
    public BrandHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brend,parent,false);
        return new BrandHolder(view);
    }

    @Override
    public void onBindViewHolder(BrandHolder holder, int position) {
        Picasso.with(context).load(drawables.get(position)).centerInside().fit().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return drawables.size();
    }
}
