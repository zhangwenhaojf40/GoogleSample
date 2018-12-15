package sample.google.databinding.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import sample.google.databinding.Product;

/***
 *  Create By ZWH  On  2018/12/15 0015
 **/
public class ProductDao {
    Dao<Product, Integer> dao;

    public ProductDao(Context context) {
        dao = DatabaseHelper.getInstance(context).getDao();
    }
    /*
    * 增加一条数据
    * */
    public void add(Product product) {
        try {
            dao.create(product);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    /*
     * 删除一条数据
     * */
    public void delete(Product product) {
        try {
            dao.delete(product);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    /*
    * 查询所有数据
    * */
    public  List<Product> queryAll() {
        List<Product> products=null;
        try {
             products= dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return products;
    }
    /*
    * 条件查询
    * */
    public List<Product> queryCondition(String value) {
        List<Product> products = null;
        try {
            products= dao.queryBuilder().where().like("name", "%"+value+"%").query();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return products;
    }
}
