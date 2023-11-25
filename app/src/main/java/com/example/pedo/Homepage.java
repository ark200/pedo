package com.example.pedo;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Homepage extends AppCompatActivity implements SensorEventListener {

    private TextView stepcount_view;
    private TextView distance_view;
    private TextView calories_view;
    private Button start;
    private Button stop;

    private SensorManager sensorManager;
    private Sensor stepsensor;
    private Sensor accelerometer;

    private boolean step_counting = false;
    int step_count=0;
    float total_distance = 0.0F;
    float total_calories = 0.0F;

    float[] gravity = new float[3];
    float[] linearAcc = new float[3];
    float[] velocity = new float[3];
    static final float walking_met = 4.0F;
    long lastStepTimestamp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        start           =   findViewById(R.id.start_button);
        stop            =   findViewById(R.id.stop_button);
        stepcount_view  =   findViewById(R.id.step_count_view);
        distance_view   =   findViewById(R.id.distance_view);
        calories_view   =   findViewById(R.id.calories_view);

        sensorManager   = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepsensor      =   sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        accelerometer   =   sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if(stepsensor == null || accelerometer == null){
            Toast.makeText(Homepage.this,"The Device doesn't have the required sensors",Toast.LENGTH_SHORT).show();
        }
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start_calculation();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop_calculation();
            }
        });
    }

    private void start_calculation(){
        step_counting = true;
        step_count =0;
        sensorManager.registerListener(Homepage.this,stepsensor, SensorManager.SENSOR_DELAY_NORMAL);

//        step_counting = true;
//        step_count =0;
//        total_calories = 0.0F;
//        total_distance = 0.0F;
//        lastStepTimestamp =0;
//
//        sensorManager.registerListener((SensorEventListener) Homepage.this, stepsensor, SensorManager.SENSOR_DELAY_NORMAL);
//        sensorManager.registerListener((SensorEventListener) Homepage.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }

    private void stop_calculation(){
        step_counting = false;
        sensorManager.unregisterListener((SensorEventListener) Homepage.this, stepsensor);
        sensorManager.unregisterListener((SensorEventListener) Homepage.this, accelerometer);
        updateUI();
    }

    private void updateUI(){
        distance_view.setText("DISTANCE: "+total_distance+"m");
        calories_view.setText("CALORIES: "+total_calories+"kcal");
        stepcount_view.setText("STEPS: "+step_count);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(step_counting){
            if(sensorEvent.sensor.getType() == Sensor.TYPE_STEP_DETECTOR){
                handleStep();
            }
//            if(sensorEvent.sensor.getType() == Sensor.TYPE_STEP_COUNTER){
//                step_count = (int) sensorEvent.values[0];
//                stepcount_view.setText("STEPS: "+step_count);
//            }
            else if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
                calculateDistance(sensorEvent);
                calculateCalories();
                distance_view.setText("DISTANCE: "+total_distance+"m");
                calories_view.setText("CALORIES: "+total_calories+"kcal");
            }
        }
    }

    private void handleStep(){

        step_count++;
        updateUI();
//        long currentTime = System.currentTimeMillis();
//        if(lastStepTimestamp == 0){
//            lastStepTimestamp = currentTime;
//        }else{
//            long timeDiff = currentTime - lastStepTimestamp;
//            if(timeDiff > 500){
//                step_count++;
//                lastStepTimestamp = currentTime;
//            }
//        }
    }
    private void calculateDistance(SensorEvent event){
        final float alpha = 0.8F;
        final float gravityCon = 9.81F;
        float timeInterval = 0.1F;
        float velocityThres = 0.1F;

        // filter to isolate the force of gravity
        gravity[0] = alpha * gravity[0] + (1-alpha) * event.values[0];
        gravity[1] = alpha * gravity[1] + (1-alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1-alpha) * event.values[2];

        // remove gravity contribution from the accelerometer valies
        linearAcc[0] = event.values[0] - gravity [0];
        linearAcc[1] = event.values[1] - gravity [1];
        linearAcc[2] = event.values[2] - gravity [2];

        velocity[0] += linearAcc[0] * timeInterval;
        velocity[1] += linearAcc[1] * timeInterval;
        velocity[2] += linearAcc[2] * timeInterval;

        if(Math.abs(velocity[0]) < velocityThres &&
        Math.abs(velocity[1]) < velocityThres &&
        Math.abs(velocity[2]) < velocityThres){
            return;
        }


//        float accelaration = (float) Math.sqrt(
//                Math.pow(linearAcc[0], 2) +
//                        Math.pow(linearAcc[1], 2) +
//                        Math.pow(linearAcc[2], 2)
//        );

//        total_distance += accelaration/(2*gravityCon);

        total_distance += velocity[0] * timeInterval;
    }

    private void calculateCalories(){
        float time = (float) (step_count/1200.0F);
        total_calories = time * walking_met*60.0F;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        sensorManager.unregisterListener(Homepage.this, stepsensor);
        sensorManager.unregisterListener(this,accelerometer);
    }
}