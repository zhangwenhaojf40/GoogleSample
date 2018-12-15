package sample.google.databinding.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import sample.google.databinding.Product;

/***
 *  Create By ZWH  On  2018/12/15 0015
 **/
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static DatabaseHelper instance;
    private static final String TABLE_NAME = "google.db";
    private Dao<Product, Integer> dao;


    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    public static DatabaseHelper getInstance(Context context) {
        if (instance == null) {

            synchronized (DatabaseHelper.class) {
                if (instance == null) {
                    instance = new DatabaseHelper(context);
                }
            }

        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Product.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Product.class, true);// 服务器配置
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Product, Integer> getDao() {
        if (dao == null) {
            try {
                dao = getDao(Product.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dao;
    }

    @Override
    public void close() {
        super.close();
        dao = null;
    }
}
