package com.example.paras.bottle;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView iv;
    Button b;
    Random r;
    int angle;
    boolean restart = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r = new Random();
        iv = (ImageView)findViewById(R.id.ivBottle);
        b = (Button)findViewById(R.id.button);
        b.setBackgroundColor(Color.TRANSPARENT);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread = new Thread() {
                    public void run() {
                        try {
                            if (restart)

                            {
                                angle = angle % 360;
                                RotateAnimation rotate = new RotateAnimation(angle, 360, RotateAnimation.RELATIVE_TO_SELF,
                                        0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                                rotate.setFillAfter(true);
                                rotate.setDuration(1000);
                                rotate.setInterpolator(new AccelerateDecelerateInterpolator());

                                iv.startAnimation(rotate);

                                b.setText("SPIN!");
                                restart = false;
                            } else

                            {
                                angle = r.nextInt(3600) + 360;
                                RotateAnimation rotate = new RotateAnimation(0, angle, RotateAnimation.RELATIVE_TO_SELF,
                                        0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                                rotate.setFillAfter(true);
                                rotate.setDuration(3600);
                                rotate.setInterpolator(new AccelerateDecelerateInterpolator());

                                iv.startAnimation(rotate);

                                restart = true;
                                b.setText("RESET");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
                Thread thread1 = new Thread() {
                    public void run() {
                        try {
                            AlertDialog.Builder b1 = new AlertDialog.Builder(getApplicationContext());
                            b1.setTitle("Truth or Dare?");
                            b1.setPositiveButton("Truth", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(getApplicationContext(), "Truth", Toast.LENGTH_LONG).show();
                                }
                            });
                            b1.setNegativeButton("Dare", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(getApplicationContext(), "Dare", Toast.LENGTH_LONG).show();
                                }
                            });
                            b1.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.interrupt();
                try {
                    thread.join(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                thread1.start();
                try {
                    thread1.join(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view == b){
            AlertDialog.Builder b1 = new AlertDialog.Builder(getApplicationContext());
            b1.setTitle("Truth or Dare?");
            b1.setPositiveButton("Truth", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getApplicationContext(), "Truth", Toast.LENGTH_LONG).show();
                }
            });
            b1.setNegativeButton("Dare", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getApplicationContext(), "Dare", Toast.LENGTH_LONG).show();
                }
            });
            b1.show();
            b1.create();
        }
    }
}