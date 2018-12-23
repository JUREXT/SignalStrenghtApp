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

    private SignalStrength signal;
    private Switch aSwitch;
    private SeekBar seekBar;
    private TextView textViewSharp, textViewRound, progressTv;
    private static final String PRO = " %";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        signal = (SignalStrength) findViewById(R.id.signal);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        textViewRound = (TextView) findViewById(R.id.textViewRound);
        textViewSharp = (TextView) findViewById(R.id.textViewSharp);
        progressTv = (TextView) findViewById(R.id.textView_p);

        aSwitch = (Switch) findViewById(R.id.switch1);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    signal.setTheme(SignalStrength.Theme.ROUNDED);
                    textViewRound.setVisibility(View.VISIBLE);
                    textViewSharp.setVisibility(View.GONE);
                }else {
                    signal.setTheme(SignalStrength.Theme.SHARP);
                    textViewRound.setVisibility(View.GONE);
                    textViewSharp.setVisibility(View.VISIBLE);
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.reset_and_bye, Snackbar.LENGTH_LONG).setAction("Reset", listener).show();
            }
        });

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                signal.setSignalLevel(progress);
                progressTv.setText(Integer.toString(progress)+PRO);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        int val = 10;
        signal.setSignalLevel(val);
        seekBar.setProgress(val);
        progressTv.setText(Integer.toString(val)+PRO);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int val = 0;
            signal.setSignalLevel(val);
            seekBar.setProgress(val);
            progressTv.setText(Integer.toString(val)+PRO);
            aSwitch.setChecked(false);
            textViewRound.setVisibility(View.VISIBLE);
            textViewSharp.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, "Reset Done!", Toast.LENGTH_SHORT).show();
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
