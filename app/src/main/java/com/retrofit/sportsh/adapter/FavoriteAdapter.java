package com.retrofit.sportsh.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.retrofit.sportsh.R;
import com.retrofit.sportsh.details.DetailsActivity;
import com.retrofit.sportsh.model.ProductInfo;
import com.squareup.picasso.Picasso;

import java.util.List;


public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder> {
    private Context context;
    private List<ProductInfo> productInfos;
    private int rowLayout;

    public FavoriteAdapter(Context context, List<ProductInfo> productInfos, int rowLayout) {
        this.context = context;
        this.productInfos = productInfos;
        this.rowLayout = rowLayout;
    }



    public class FavoriteHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView imageView;
        private TextView txtTitle;
        private TextView txtPrice;
        private CardView cardView;
        private String id;

        public FavoriteHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.rv_grid_item);
            imageView = (ImageView) itemView.findViewById(R.id.img_item);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_title_item);
            txtPrice = (TextView) itemView.findViewById(R.id.txt_price_item);

            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.rv_grid_item:
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("id",id);
                    context.startActivity(intent);
                    break;
            }
        }
    }

    @Override
    public FavoriteAdapter.FavoriteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new FavoriteHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteAdapter.FavoriteHolder holder, int position) {
        Picasso.with(context).load(productInfos.get(position).getImg1()).into(holder.imageView);
        holder.txtTitle.setText(productInfos.get(position).getName());
        holder.txtPrice.setText(productInfos.get(position).getPrice() + "тг");
        holder.id = productInfos.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return productInfos.size();
    }
}
