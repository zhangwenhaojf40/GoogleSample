package sample.google.livedata_mvvm

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_mutable_livedata.*

/***
 *  Create By ZWH  On  2018/12/21 0021
 **/
class MutableLiveDataActivity : AppCompatActivity() {
    val liveData= MutableLiveData<String>()
    val observer=Observer<String>{value->
        value?.let {
            tvChang.text=it

        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mutable_livedata)
        liveData.observe(this,observer)
        generate.setOnClickListener {
            liveData.postValue((1..9999).random().toString())
        }
        supportFragmentManager.addOnBackStackChangedListener {
            setBtnText()
        }
        add.setOnClickListener {
            if (supportFragmentManager.backStackEntryCount == 0) {

                supportFragmentManager.beginTransaction().replace(R.id.container, MutableLiveDataFragment())
                        .addToBackStack("")
                        .commit()
            } else{
                supportFragmentManager.popBackStack()
            }


        }
    }

    private fun setBtnText() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            add.text = "add"
        } else {
            add.text = "remove"

        }
    }

}