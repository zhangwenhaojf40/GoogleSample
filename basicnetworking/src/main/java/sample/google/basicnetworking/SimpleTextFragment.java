package sample.google.basicnetworking;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/***
 *  Create By ZWH  On  2018/12/12 0012
 **/
public class SimpleTextFragment extends Fragment {

    private TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mTextView = new TextView(getActivity());
        mTextView.setGravity(Gravity.CENTER);
        return mTextView;
    }

    public void setText(int stringId) {
        getTextView().setText(stringId);
    }
    public TextView getTextView() {
        return mTextView;

    }
}
