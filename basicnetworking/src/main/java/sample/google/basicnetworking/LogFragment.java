package sample.google.basicnetworking;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

/***
 *  Create By ZWH  On  2018/12/12 0012
 **/
public class LogFragment extends Fragment {

    private TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = initViews();
        return view;
    }

    private View initViews() {
        ScrollView scrollView = new ScrollView(getActivity());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollView.setLayoutParams(params);
        mTextView = new TextView(getActivity());
        ScrollView.LayoutParams tvParams = new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvParams.setMargins(50,50,50,50);
        mTextView.setTextSize(16);
        mTextView.setLayoutParams(tvParams);

        scrollView.addView(mTextView);
        return scrollView;
    }
    public void setMsg(String msg) {
        mTextView.append("The Activity connection is "+msg+"\n");
    }
    public void  setClear() {
        mTextView.setText("");

    }
}
