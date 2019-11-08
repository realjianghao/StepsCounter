package com.hao.stepscounter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.widget.TextView;


public class AccelerationShower {
    private TextView view;
    private SensorManager sensorManager;
    private Sensor sensor;

    public AccelerationShower(TextView view) {
        this.view = view;
        sensorManager = (SensorManager) view.getContext().getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //sensorManager.registerListener(onSensorChanged,)
    }

    public void onSensorChanged(SensorEvent event){

    }
}
