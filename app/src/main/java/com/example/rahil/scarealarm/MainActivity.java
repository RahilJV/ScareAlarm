package com.example.rahil.scarealarm;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView (R.id.alarmTimePicker)  TimePicker alarmTimePicker;
    @BindView (R.id.alarmToggle) ToggleButton alarmToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        alarmToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (isChecked)
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        NotificationHelper.scheduleRepeatingRTCNotification(MainActivity.this,
                                Integer.toString(alarmTimePicker.getHour()), Integer.toString(alarmTimePicker.getMinute()));
                    }
                    else
                    {
                        NotificationHelper.scheduleRepeatingRTCNotification(MainActivity.this,
                                Integer.toString(alarmTimePicker.getCurrentHour()), Integer.toString(alarmTimePicker.getCurrentMinute()));
                    }
                }
                else
                {
                    NotificationHelper.cancelAlarmRTC();
                    Log.d(TAG, "Alarm Off");
                }

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
