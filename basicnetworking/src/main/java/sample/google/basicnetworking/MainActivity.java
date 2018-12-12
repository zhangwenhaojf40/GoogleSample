package sample.google.basicnetworking;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private LogFragment logFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SimpleTextFragment textFragment = (SimpleTextFragment) getSupportFragmentManager().findFragmentById(R.id.topFragment);
        logFragment = (LogFragment) getSupportFragmentManager().findFragmentById(R.id.bottomFragment);
        textFragment.setText(R.string.intro_message);
        textFragment.getTextView().setTextSize(16.f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.test_action:
                checkNetWork();
                break;
            case R.id.clear_action:
                logFragment.setClear();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkNetWork() {
        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            boolean wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            boolean mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            if(wifiConnected) {
                logFragment.setMsg("WIFI");
            } else if (mobileConnected){
                logFragment.setMsg("Mobile");
            }
        } else {
            logFragment.setMsg("NO NET");
        }
    }
}
