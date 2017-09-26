package com.retrofit.sportsh.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.retrofit.sportsh.R;
import com.retrofit.sportsh.details.DetailsActivity;
import com.retrofit.sportsh.model.DataAddProduct;
import com.retrofit.sportsh.model.InfoTemp;
import com.retrofit.sportsh.model.ProductInfo;
import com.retrofit.sportsh.response.AddFavoriteResponse;
import com.retrofit.sportsh.rest.ApiClient;
import com.retrofit.sportsh.rest.ApiInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ApparelsHolder> {

    private Context context;
    private List<ProductInfo> productInfos;
    private int rowLayout;
    private String taId;

    public MainAdapter(Context context, List<ProductInfo> productInfos, int rowLayout) {
        this.context = context;
        this.productInfos = productInfos;
        this.rowLayout = rowLayout;

        Realm realm = Realm.getDefaultInstance();
        InfoTemp infoTemp = realm.where(InfoTemp.class).findFirst();
        taId = String.valueOf(infoTemp.getId());
    }

    public class ApparelsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView txtTitle;
        private TextView txtPrice;
        private CardView cardView;
        private ImageButton iBtnFavorite;
        private String id;

        public ApparelsHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.rv_grid_item);
            imageView = (ImageView) itemView.findViewById(R.id.img_item);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_title_item);
            txtPrice = (TextView) itemView.findViewById(R.id.txt_price_item);
            iBtnFavorite = (ImageButton) itemView.findViewById(R.id.favorite);

            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rv_grid_item:
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("id", id);
                    context.startActivity(intent);
                    break;
            }
        }
    }

    @Override
    public ApparelsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ApparelsHolder(view);
    }

    @Override
    public void onBindViewHolder(final ApparelsHolder holder, final int position) {
        Picasso.with(context).load(productInfos.get(position).getImg1()).into(holder.imageView);
        holder.txtTitle.setText(productInfos.get(position).getName());
        holder.txtPrice.setText(productInfos.get(position).getPrice() + "тг");
        holder.id = productInfos.get(position).getId();
        Log.d("dddd", "isFavorite" + productInfos.get(position).getIsFavorite());
        if (productInfos.get(position).getIsFavorite() == 1) {
              holder.iBtnFavorite.setBackgroundResource(R.drawable.ic_favorite_blue_grey_600_24dp);
              productInfos.get(position).setPress(true);
        } else {
              holder.iBtnFavorite.setBackgroundResource(R.drawable.ic_favorite_border_blue_grey_600_24dp);
            productInfos.get(position).setPress(false);
        }

        holder.iBtnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productInfos.get(position).isPress()) {
                    Log.d("ssss", "click" + 123);
                       holder.iBtnFavorite.setBackgroundResource(R.drawable.ic_favorite_border_blue_grey_600_24dp);
                    productInfos.get(position).setPress(false);
                    delFavorite(productInfos.get(position).getId());
                } else {
                    Log.d("ssss", "click" + 123456);
                       holder.iBtnFavorite.setBackgroundResource(R.drawable.ic_favorite_blue_grey_600_24dp);
                    productInfos.get(position).setPress(true);
                    addFavorite(productInfos.get(position).getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productInfos.size();
    }

    private void addFavorite(String id) {
        ApiInterface apiInterface = ApiClient.getApiInterface();
        Call<AddFavoriteResponse> call = apiInterface.addToFavorite(id, taId);
        call.enqueue(new Callback<AddFavoriteResponse>() {
            @Override
            public void onResponse(Call<AddFavoriteResponse> call, Response<AddFavoriteResponse> response) {
                AddFavoriteResponse addFavoriteResponse = response.body();
                List<DataAddProduct> dataAddProduct = addFavoriteResponse.getData();
                boolean success = dataAddProduct.get(0).isSuccess();

                if (success) {
                    Toast.makeText(context, "add to favorite", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddFavoriteResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void delFavorite(String id) {
        ApiInterface apiInterface = ApiClient.getApiInterface();
        Call<AddFavoriteResponse> call = apiInterface.deleteFromFavorite(id, taId);
        call.enqueue(new Callback<AddFavoriteResponse>() {
            @Override
            public void onResponse(Call<AddFavoriteResponse> call, Response<AddFavoriteResponse> response) {
                AddFavoriteResponse addFavoriteResponse = response.body();
                List<DataAddProduct> dataAddProduct = addFavoriteResponse.getData();
                boolean success = dataAddProduct.get(0).isSuccess();

                if (success) {
                    Toast.makeText(context, "delete from favorite", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddFavoriteResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

