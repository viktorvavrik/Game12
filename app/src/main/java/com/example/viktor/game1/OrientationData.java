package com.example.viktor.game1;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


public class OrientationData implements SensorEventListener {
    private SensorManager manager;
    //private Sensor accelerometer;
    //private Sensor magnometer;
    private Sensor gravitymeter;

    //private float[] accelOutput;
    //private float[] magOutput;
    private float[] gravOutput;

    private float[] orientation = new float[3];
    public float[] getOrientation() {
        return orientation;
    }

    private float[] startOrientation = null;
    public float[] getStartOrientation() {
        return startOrientation;
    }
    public void newGame() {
        startOrientation = null;
    }

    public OrientationData(Context context) {
        manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        //accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //magnometer = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        gravitymeter = manager.getDefaultSensor(Sensor.TYPE_GRAVITY);
    }

    public void register() {
        //manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        //manager.registerListener(this, magnometer, SensorManager.SENSOR_DELAY_GAME);
        manager.registerListener(this, gravitymeter, SensorManager.SENSOR_DELAY_GAME);
    }

    public void pause() {
        manager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_GRAVITY) {
            gravOutput = event.values;
            //System.out.println("gravity"+ gravOutput[0] + " " + gravOutput[1] + " " + gravOutput[2]);
            orientation[0] = gravOutput[0];
            orientation[1] = gravOutput[1];
            orientation[2] = gravOutput[2];
        }
        /*if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            accelOutput = event.values;
            System.out.println("accel output" + accelOutput[1]);
        }
        else if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            magOutput = event.values;
            System.out.println("magnet output" + magOutput[1]);
        }

        if(accelOutput != null && magOutput != null) {
            float[] R = new float[9];
            float[] I = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, accelOutput, magOutput);
            if(success) {
                SensorManager.getOrientation(R, orientation);
                if(startOrientation == null) {
                    startOrientation = new float[orientation.length];
                    System.arraycopy(orientation, 0, startOrientation, 0, orientation.length);
                }
            }
        }*/
    }
}
