package sample.google.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/***
 *  Create By ZWH  On  2018/12/14 0014
 **/
@DatabaseTable(tableName = "product")
public class Product extends BaseObservable{
    @DatabaseField(columnName = "name")
    private String name;
    @DatabaseField(columnName = "desscription")
    private String description;
    @DatabaseField(columnName = "price")
    private int price;
    /*
    * 要有无参构造
    * 否则报错
    *
    * */
    public Product(){}
    @DatabaseField(columnName = "id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public Product( int id,String name, String description, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }


    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
        notifyPropertyChanged(BR.name);
    }
    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }
    @Bindable
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
        notifyPropertyChanged(BR.price);
    }
}
