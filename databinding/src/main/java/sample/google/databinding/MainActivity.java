package sample.google.databinding;

import android.appwidget.AppWidgetManager;
import android.content.SharedPreferences;
import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import sample.google.databinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding=DataBindingUtil.setContentView(this, R.layout.activity_main);
        MyAdapter myAdapter = new MyAdapter();
        binding.recyclerView.setAdapter(myAdapter);
        ProductViewModel viewModel = new ProductViewModel(this);
        myAdapter.setList(viewModel.getProducts());
      /*  binding.search.setOnClickListener(v -> {
            List<Product> productsCondition = viewModel.getProductsCondition(binding.condition.getText().toString());
            myAdapter.setList(productsCondition);
        });*/
        //布局随数据动态改变
        binding.test.setOnClickListener(v -> {
            myAdapter.getList().get(0).setName(binding.testTV.getText().toString());

        });
        binding.condition.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<Product> productsCondition = viewModel.getProductsCondition(s.toString());
                myAdapter.setList(productsCondition);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}
