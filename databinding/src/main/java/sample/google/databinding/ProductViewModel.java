package sample.google.databinding;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MediatorLiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.TextureView;

import java.util.ArrayList;
import java.util.List;

import sample.google.databinding.db.DatabaseHelper;
import sample.google.databinding.db.ProductDao;

/***
 *  Create By ZWH  On  2018/12/15 0015
 **/
public class ProductViewModel  {

    public static String FIRST = "first";
    private static final String[] names = new String[]{
            "Special edition", "New", "Cheap", "Quality", "Used",
            "Three-headed Monkey", "Rubber Chicken", "Pint of Grog", "Monocle"};

    private static final int [] prices = new int []{
           100,33,22,55,999,234,222,666,777,888,33,23,23,43};
    private static final String[] DESCRIPTION = new String[]{
            "is finally here", "is recommended by Stan S. Stanman",
            "is the best sold product on Mêlée Island", "is \uD83D\uDCAF", "is ❤️", "is fine",
            "is finally here", "is recommended by Stan S. Stanman",
            "is finally here", "is recommended by Stan S. Stanman",
            "is finally here", "is recommended by Stan S. Stanman",
    };
    private final ProductDao productDao;


    public ProductViewModel(Context context) {

        productDao = new ProductDao(context);
        //只有安装第一次，往数据库添加数据
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        if (sp.getInt(FIRST,0) == 0) {//第一次安装
            for (int i = 0; i <names.length ; i++) {
                productDao.add(new Product(i,names[i],DESCRIPTION[i],prices[i]));
            }
            sp.edit().putInt(FIRST, 1).apply();
        }



    }

    public List<Product> getProducts() {
        return productDao.queryAll();
    }

    public List<Product> getProductsCondition(String value) {
        if (TextUtils.isEmpty(value)) {
            return  getProducts();

        }
        return productDao.queryCondition(value);
    }


}
