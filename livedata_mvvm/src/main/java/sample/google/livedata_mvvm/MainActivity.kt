package sample.google.livedata_mvvm

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnMutableLive.setOnClickListener {
            startActivity(Intent(this,MutableLiveDataActivity::class.java))
        }

    }
}
