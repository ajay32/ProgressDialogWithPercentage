package com.hackingbuzz.progressdialogwithpercentage;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    Handler handler = new Handler();
    int status = 0;
    Button button;
    ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CreateProgressDialog();

                ShowProgressDialog();

            }
        });
    }

    public void CreateProgressDialog()
    {

        progressdialog = new ProgressDialog(MainActivity.this);

        progressdialog.setIndeterminate(false);

        progressdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        progressdialog.setCancelable(true);

        progressdialog.setMax(100);

        progressdialog.show();

    }

    public void ShowProgressDialog()
    {
        status = 0;

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(status < 100){    // we got one more thread in a while loop..so we took one thread to give a little mili sleep to our incrementation ..n inside that thread (in while loop) we taking another thread (handler) that is setting our output to progress dialog..

                    status +=1;

                    try{
                        Thread.sleep(200);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            progressdialog.setProgress(status);

                            if(status == 100){

                                progressdialog.dismiss();
                            }
                        }
                    });
                }
            }
        }).start();

    }  // end of show dialog...




    // see we can do all of this using single thread too....

/*
    public void ShowProgressDialog()
    {
        status = 0;

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(status < 100){    // we got one more thread in a while loop..so we took one thread to give a little mili sleep to our incrementation ..n inside that thread (in while loop) we taking another thread (handler) that is setting our output to progress dialog..

                    status +=1;

                    try{
                        Thread.sleep(200);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }


                    progressdialog.setProgress(status);

                    if(status == 100){

                        progressdialog.dismiss(); }

                }
            }
        }).start();*/

    }




