package sample.google.livedata_mvvm

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_mutable_livedata.*
import kotlinx.android.synthetic.main.activity_mutable_livedata.view.*

/***
 *  Create By ZWH  On  2018/12/21 0021
 **/
class MutableLiveDataFragment : Fragment() {
    val observer=Observer<String>{value->
        value?.let {
            tvChang.text=it
        }

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=View.inflate(activity,R.layout.fragment_mutable_live_data,null)
        return view

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        (activity as MutableLiveDataActivity).liveData.observe(this,observer)
    }
}