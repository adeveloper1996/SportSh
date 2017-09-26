package com.retrofit.sportsh.adapter;


import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.retrofit.sportsh.R;
import com.retrofit.sportsh.model.DataAddProduct;
import com.retrofit.sportsh.model.ProductInfo;
import com.retrofit.sportsh.response.AddFavoriteResponse;
import com.retrofit.sportsh.rest.ApiClient;
import com.retrofit.sportsh.rest.ApiInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

import pl.polak.clicknumberpicker.ClickNumberPickerListener;
import pl.polak.clicknumberpicker.ClickNumberPickerView;
import pl.polak.clicknumberpicker.PickerClickType;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.BasketHolder> {
    private Context context;
    private List<ProductInfo> productInfos;
    private int row;
    private TextView txtAllPrice;
    private TextView txtDeliver;
    private TextView txtPriceParent;
    private int currentPrice = 0;
    private String taId;

    public BasketAdapter(Context context, List<ProductInfo> productInfos, int row, TextView txtPriceParent, TextView txtAllPrice, TextView txtDeliver, String taId) {
        this.context = context;
        this.productInfos = productInfos;
        this.row = row;
        this.txtAllPrice = txtAllPrice;
        this.txtDeliver = txtDeliver;
        this.txtPriceParent = txtPriceParent;
        this.taId = taId;
    }

    public class BasketHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener{
        private TextView txtTitle;
        private TextView txtPrice;
        private ImageView imageView;
        private ImageButton iBtn;
        private ClickNumberPickerView pickerView;
        private String id;

        public BasketHolder(View itemView) {
            super(itemView);
            txtPrice = (TextView) itemView.findViewById(R.id.txt_price_basket);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_title_basket);
            imageView = (ImageView) itemView.findViewById(R.id.img_basket);
            iBtn = (ImageButton) itemView.findViewById(R.id.i_btn_popup);
            iBtn.setOnClickListener(this);
            pickerView = (ClickNumberPickerView) itemView.findViewById(R.id.picker_number);
            pickerView.setPickerValue(1);
            pickerView.setClickNumberPickerListener(new ClickNumberPickerListener() {
                @Override
                public void onValueChange(float previousValue, float currentValue, PickerClickType pickerClickType) {
                    Log.d("ssss","" + getAdapterPosition());
                    Log.d("ssss","price" + productInfos.get(getAdapterPosition()).getPrice());
                    if (previousValue > currentValue){
                        currentPrice = currentPrice - Integer.parseInt(productInfos.get(getAdapterPosition()).getPrice());
                        txtPriceParent.setText(String.valueOf (currentPrice));
                    }else {
                        currentPrice = currentPrice + Integer.parseInt(productInfos.get(getAdapterPosition()).getPrice());
                        txtPriceParent.setText(String.valueOf (currentPrice));
                    }
                    txtAllPrice.setText(String.valueOf(Integer.parseInt(txtPriceParent.getText().toString()) + Integer.parseInt(txtDeliver.getText().toString())));
                }
            });
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.menu_delete:
                    deleteProduct(getAdapterPosition());
                    return true;
            }
            return false;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.i_btn_popup:
                    PopupMenu popupMenu = new PopupMenu(itemView.getContext(),v);
                    popupMenu.setOnMenuItemClickListener(this);
                    popupMenu.inflate(R.menu.menu_popup);
                    popupMenu.show();
                    break;
            }
        }

        private void deleteProduct(final int adapterPosition) {
            ApiInterface apiInterface = ApiClient.getApiInterface();
            Call<AddFavoriteResponse> call = apiInterface.deleteFromBasket(id,taId);
            call.enqueue(new Callback<AddFavoriteResponse>() {
                @Override
                public void onResponse(Call<AddFavoriteResponse> call, Response<AddFavoriteResponse> response) {
                    AddFavoriteResponse favoriteResponse = response.body();
                    List<DataAddProduct> addProduct = favoriteResponse.getData();
                    boolean success = addProduct.get(0).isSuccess();

                    if (success){
                        txtPriceParent.setText(String.valueOf (currentPrice - Integer.parseInt(productInfos.get(getAdapterPosition()).getPrice())));
                        txtAllPrice.setText(String.valueOf(Integer.parseInt(txtPriceParent.getText().toString()) + Integer.parseInt(txtDeliver.getText().toString())));
                        Toast.makeText(context,"Delete",Toast.LENGTH_SHORT).show();
                        productInfos.remove(adapterPosition);
                        notifyItemRangeChanged(adapterPosition,productInfos.size());
                    }
                }

                @Override
                public void onFailure(Call<AddFavoriteResponse> call, Throwable t) {
                    Toast.makeText(context,t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public BasketHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(row,parent,false);
        return new BasketHolder(view);
    }

    @Override
    public void onBindViewHolder(BasketHolder holder, int position) {
        Picasso.with(context).load(productInfos.get(position).getImg1()).centerInside().fit().into(holder.imageView);
        holder.txtTitle.setText(productInfos.get(position).getName());
        holder.txtPrice.setText(productInfos.get(position).getPrice() + "тг");
        holder.id = productInfos.get(position).getId();

        currentPrice += Integer.parseInt(productInfos.get(position).getPrice());

        txtPriceParent.setText(String.valueOf(currentPrice));
        txtAllPrice.setText(String.valueOf(Integer.parseInt(txtPriceParent.getText().toString()) + Integer.parseInt(txtDeliver.getText().toString())));
    }

    @Override
    public int getItemCount() {
        return productInfos.size();
    }
}


