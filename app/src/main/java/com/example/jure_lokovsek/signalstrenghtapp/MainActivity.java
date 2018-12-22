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
    private TextView textViewSpica, textViewOkroglo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        signal = (SignalStrength) findViewById(R.id.signal);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        textViewOkroglo = (TextView) findViewById(R.id.textViewOkroglo);
        textViewSpica = (TextView) findViewById(R.id.textViewSpica);

        aSwitch = (Switch) findViewById(R.id.switch1);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    signal.setTheme(SignalStrength.Theme.ROUNDED);
                    textViewOkroglo.setVisibility(View.VISIBLE);
                    textViewSpica.setVisibility(View.GONE);
                }else {
                    signal.setTheme(SignalStrength.Theme.SHARP);
                    textViewOkroglo.setVisibility(View.GONE);
                    textViewSpica.setVisibility(View.VISIBLE);
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
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        signal.setSignalLevel(10);
        seekBar.setProgress(10);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            signal.setSignalLevel(0);
            seekBar.setProgress(0);
            aSwitch.setChecked(false);
            textViewOkroglo.setVisibility(View.VISIBLE);
            textViewSpica.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, "Bye", Toast.LENGTH_SHORT).show();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
