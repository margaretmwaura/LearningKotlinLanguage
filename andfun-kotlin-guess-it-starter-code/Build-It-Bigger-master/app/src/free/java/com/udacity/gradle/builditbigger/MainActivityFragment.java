package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.example.com.jokelibrary.DisplayJokeActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.mCallback{

    private InterstitialAd mInterstitialAd;
    private ProgressBar mProgressBar;
    private Button jokeButton;

    public MainActivityFragment() {
    }

    public void getJokeFromTask() {
        Context context = getActivity();
        new EndpointsAsyncTask(this).execute(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressBar = null;
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_testad_id));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitialAd();
                jokeButton.setEnabled(false);
                mProgressBar.setVisibility(View.VISIBLE);
                getJokeFromTask();
            }
        });

        requestNewInterstitialAd();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mProgressBar = root.findViewById(R.id.spinner);
        jokeButton = root.findViewById(R.id.joke_button);
        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    //start AsyncTask to get the Joke
                    jokeButton.setEnabled(false);
                    mProgressBar.setVisibility(View.VISIBLE);
                    getJokeFromTask();
                }
            }
        });

        AdView mAdView = root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    public void onCallbackResult(String result) {
        Intent sendIntent = new Intent(getActivity(), DisplayJokeActivity.class);
        sendIntent.putExtra("jokes", result);

        mProgressBar.setVisibility(View.GONE);
        jokeButton.setEnabled(true);
        startActivity(sendIntent);
    }

    private void requestNewInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        if(mInterstitialAd != null) {
            mInterstitialAd.loadAd(adRequest);
        }
    }
}
