package sample.google.databinding;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import sample.google.databinding.databinding.ProductItemBinding;

public  class MyAdapter extends RecyclerView.Adapter {
        List< Product> mProductList;
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
           ProductItemBinding binding= DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.product_item,
                    viewGroup,false);
            return  new ProductHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            ProductHolder holder = (ProductHolder) viewHolder;
            holder.binding.setProduct(mProductList.get(i));
        }

        public void setList(List<Product> mProductList) {
            this.mProductList = mProductList;
            notifyDataSetChanged();
        }
        public List<Product>  getList() {
            return mProductList;
        }
        @Override
        public int getItemCount() {
            return mProductList==null?0:mProductList.size();
        }

    class ProductHolder extends RecyclerView.ViewHolder {
        ProductItemBinding binding;
        public ProductHolder(ProductItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }


    }