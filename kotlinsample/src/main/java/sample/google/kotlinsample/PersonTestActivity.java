package sample.google.kotlinsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/***
 *  Create By ZWH  On  2018/12/24 0024
 **/
public class CreateClassTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Person p = new Person("Tom", 23);
        Toast.makeText(this, "Create Kotlin Class"+, Toast.LENGTH_SHORT).show();
    }
}
