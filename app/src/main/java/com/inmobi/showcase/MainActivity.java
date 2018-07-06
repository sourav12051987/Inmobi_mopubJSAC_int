package com.inmobi.showcase;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.inmobi.ads.InMobiInterstitial;
import com.mopub.common.MoPub;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.SdkInitializationListener;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;

public class MainActivity extends AppCompatActivity implements MoPubInterstitial.InterstitialAdListener {
    Button Load;
    MoPubInterstitial mInterstitial;
    private static final String TAG = "Interstitial";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Load=(Button) findViewById(R.id.load);
        SdkConfiguration sdkConfiguration= new  SdkConfiguration.Builder("21e005af2366472782920bbf705d7635").build();

        MoPub.initializeSdk(this, sdkConfiguration, initSdkListener());

    }



    private SdkInitializationListener initSdkListener() {
        return new SdkInitializationListener() {

            @Override
            public void onInitializationFinished() {
                Log.d(TAG, "MopubInitializationFinished:");

            }
        };
    }

    public  void loadAd(View v){

        /****** mopub ******/

        mInterstitial = new MoPubInterstitial(this, "21e005af2366472782920bbf705d7635");
        mInterstitial.setInterstitialAdListener(this);
        mInterstitial.load();
    }



    @Override
    public void onInterstitialLoaded(MoPubInterstitial interstitial) {
        // The interstitial has been cached and is ready to be shown.
        mInterstitial.show();
    }

    @Override
    public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
        // The interstitial has failed to load. Inspect errorCode for additional information.
        Log.d(TAG, "onInterstitialFailed:"+errorCode);
    }

    @Override
    public void onInterstitialShown(MoPubInterstitial interstitial) {
        // The interstitial has been shown. Pause / save state accordingly.

    }

    @Override
    public void onInterstitialClicked(MoPubInterstitial interstitial) {}

    @Override
    public void onInterstitialDismissed(MoPubInterstitial interstitial) {
        // The interstitial has being dismissed. Resume / load state accordingly.
    }

    @Override
    protected void onPause() {
        super.onPause();
        MoPub.onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MoPub.onStop(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MoPub.onResume(this);
    }

    @Override
    protected void onDestroy() {
        mInterstitial.destroy();
        super.onDestroy();
    }

}
