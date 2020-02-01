package com.erakarta.myegg;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ImageView qImageView = findViewById(R.id.imageView);
        qImageView.setTag(R.integer.Telur,0);
    }


    public void click_egg(android.view.View id)
    {
        ImageView qImageView = findViewById(R.id.imageView);
        int counter = (int) qImageView.getTag(R.integer.Telur);

        Random rnd = new Random();

        counter++;
        if(counter > 5)
        {
            counter = 1;
        }
        if(counter == 3)
        {
            if(rnd.nextBoolean()) {
                counter++;
            }
        }

        switch(counter)
        {
            case 5:
                qImageView.setBackgroundResource(R.drawable.egg1);
                break;
            case 4:
                qImageView.setBackgroundResource(R.drawable.chick1);
                break;
            case 1:
                qImageView.setBackgroundResource(R.drawable.egg2);
                break;
            case 2:
                qImageView.setBackgroundResource(R.drawable.egg3);
                break;
            case 3:
                qImageView.setBackgroundResource(R.drawable.egg5);
                break;
        }
        //mp = MediaPlayer.create(this, R.raw.eggcrack24);
        //mp.release();
        if(counter ==3){
            counter++;
        }
        qImageView.setTag(R.integer.Telur,counter);

        try {
            if (mp.isPlaying()) {
                mp.stop();
                if(counter < 4) {
                    mp = MediaPlayer.create(this, R.raw.eggcrack24);
                    mp.prepare();
                }

                if(counter==4)
                {
                    mp = MediaPlayer.create(this, R.raw.chiks);
                    mp.prepare();
                }
            } mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mp.release();
                }
            });
        } catch(Exception e) { e.printStackTrace(); }



        //createAlertDialog("Judul", "Pesan 1");
    }



    public void createAlertDialog(String Judul, String Pesan) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(Judul);
        alertDialog.setMessage(Pesan);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


}
