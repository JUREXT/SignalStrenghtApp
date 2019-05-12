package com.example.jure_lokovsek.signalstrenghtapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import eo.view.signalstrength.SignalStrength;

public class MainActivity extends AppCompatActivity {

    private SignalStrength mSignal;
    private Switch mSwitch;
    private SeekBar mSeekBar;
    private TextView mTextViewSharp, mTextViewRound, mProgressTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSignal = (SignalStrength) findViewById(R.id.signal);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        mTextViewRound = (TextView) findViewById(R.id.textViewRound);
        mTextViewSharp = (TextView) findViewById(R.id.textViewSharp);
        mProgressTv = (TextView) findViewById(R.id.textView_p);

        mSwitch = (Switch) findViewById(R.id.switch1);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mSignal.setTheme(SignalStrength.Theme.ROUNDED);
                    mTextViewRound.setVisibility(View.VISIBLE);
                    mTextViewSharp.setVisibility(View.GONE);
                }else {
                    mSignal.setTheme(SignalStrength.Theme.SHARP);
                    mTextViewRound.setVisibility(View.GONE);
                    mTextViewSharp.setVisibility(View.VISIBLE);
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.reset_and_bye, Snackbar.LENGTH_LONG).setAction(getResources().getString(R.string.reset), listener).show();
            }
        });

        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mSignal.setSignalLevel(progress);
                mProgressTv.setText(progress + getResources().getString(R.string.percentages_sign));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        int val = 10;
        mSignal.setSignalLevel(val);
        mSeekBar.setProgress(val);
        mProgressTv.setText(val + getResources().getString(R.string.percentages_sign));
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int val = 0;
            mSignal.setSignalLevel(val);
            mSeekBar.setProgress(val);
            mProgressTv.setText(val + getResources().getString(R.string.percentages_sign));
            mSwitch.setChecked(false);
            mTextViewRound.setVisibility(View.VISIBLE);
            mTextViewSharp.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, getResources().getString(R.string.reset_done), Toast.LENGTH_SHORT).show();
        }};

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit_app) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
