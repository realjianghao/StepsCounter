package com.hao.stepscounter;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class AccelerometerMonitor implements SensorEventListener {
    public AccelerometerMonitor(TextView view){
        this.view = view;
    }


    private TextView view;
    private final String SAVE_PATH = "acc.txt";
    @Override
    public void onSensorChanged(SensorEvent event) {

        StringBuilder msg = new StringBuilder();
        msg.append("x: " + event.values[0] + "\n");
        msg.append("y: " + event.values[1] + "\n");
        msg.append("z: " + event.values[2] + "\n");

        float sumAcc = 0;
        for (int i = 0; i < 3; i++) {
            sumAcc += event.values[i] * event.values[i];
        }
        sumAcc = (float) Math.sqrt(sumAcc);
        msg.append("合加速度: " + sumAcc + "\n");
        view.setText(msg);

        writeToFile(msg + "\n");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void writeToFile(String msg){
        //String filename = Config.SAVE_PATH;
        //Common.verifyStoragePermissions();
        File file = new File(SAVE_PATH);
        if (!file.exists()){
            try {
                file.createNewFile();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        BufferedWriter writer = null;
        try {

            FileOutputStream output = new FileOutputStream(file, true);
            //var i = new OutputStreamWriter(output);

            writer = new BufferedWriter(new OutputStreamWriter(output));

            writer.write(msg);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null){
                    writer.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
