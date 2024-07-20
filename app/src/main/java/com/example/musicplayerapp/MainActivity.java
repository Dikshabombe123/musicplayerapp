package com.example.musicplayerapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.musicplayerapp.R;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    ImageView playBtn, prevBtn, nextBtn,imageView;
    TextView songTitle, songDuration, songCurrentTime;
    SeekBar seekBar;
    MediaPlayer mediaPlayer;
    ArrayList<Integer> songs;
    int currentIndex = 0;
    boolean isPlaying = false;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        playBtn = findViewById(R.id.playBtn);
        prevBtn = findViewById(R.id.prevBtn);
        nextBtn = findViewById(R.id.nextBtn);
        songTitle = findViewById(R.id.songTitle);
        songDuration = findViewById(R.id.songDuration);
        songCurrentTime = findViewById(R.id.songCurrentTime);
        seekBar = findViewById(R.id.seekBar);
        imageView=findViewById(R.id.imageView);

        // Initialize MediaPlayer and songs list
        mediaPlayer = new MediaPlayer();
        songs = new ArrayList<>();
        songs.add(R.raw.song1);
        songs.add(R.raw.song2);
        songs.add(R.raw.song3);
        songs.add(R.raw.song4);
        songs.add(R.raw.song5);
        songs.add(R.raw.song6);
        songs.add(R.raw.song7);
        songs.add(R.raw.song8);
        songs.add(R.raw.song9);
        songs.add(R.raw.song10);
        songs.add(R.raw.song11);
        songs.add(R.raw.song12);
        songs.add(R.raw.song13);
        songs.add(R.raw.song14);
        songs.add(R.raw.song15);

        // Set initial song
        mediaPlayer = MediaPlayer.create(getApplicationContext(), songs.get(currentIndex));
        songTitle.setText("hassen me"); // Initial song title
        imageView.setImageResource(R.drawable.headphone);
        updateSeekBar();

        // Play button click listener
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isPlaying) {
                    mediaPlayer.start();
                    isPlaying = true;
                    playBtn.setImageResource(R.drawable.pause); // Change to pause icon
                    updateSeekBar();
                } else {
                    mediaPlayer.pause();
                    isPlaying = false;
                    playBtn.setImageResource(R.drawable.play); // Change to play icon
                }
            }
        });

        // Next button click listener
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex < songs.size() - 1) {
                    currentIndex++;
                } else {
                    currentIndex = 0;
                }
                playSong();
            }
        });

        // Previous button click listener
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex > 0) {
                    currentIndex--;
                } else {
                    currentIndex = songs.size() - 1;
                }
                playSong();
            }
        });

        // Seek bar change listener
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Remove updateSeekBar callbacks
                mHandler.removeCallbacks(mUpdateTimeTask);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Add updateSeekBar callbacks
                mHandler.post(mUpdateTimeTask);
            }
        });

        // Update seek bar and time
        updateSeekBar();
    }

    // Update seek bar and time
    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            long totalDuration = mediaPlayer.getDuration();
            long currentDuration = mediaPlayer.getCurrentPosition();

            // Displaying Total Duration time
            songDuration.setText("" + TimeUnit.MILLISECONDS.toMinutes(totalDuration) + ":" + TimeUnit.MILLISECONDS.toSeconds(totalDuration % 60000));

            // Displaying time completed playing
            songCurrentTime.setText("" + TimeUnit.MILLISECONDS.toMinutes(currentDuration) + ":" + TimeUnit.MILLISECONDS.toSeconds(currentDuration % 60000));

            // Updating progress bar
            int progress = (int) (TimeUnit.MILLISECONDS.toSeconds(currentDuration));
            seekBar.setProgress(progress);

            // Running this thread after 100 milliseconds
            mHandler.postDelayed(this, 100);
        }
    };

    // Play selected song
    private void playSong() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(getApplicationContext(), songs.get(currentIndex));
        if (currentIndex == 0) {
            songTitle.setText("thandi thandi bear piyenge");
            imageView.setImageResource(R.drawable.p9);
        }
        if (currentIndex == 1) {
            songTitle.setText("chan vi gavah");
            imageView.setImageResource(R.drawable.pic2);
        }
        if (currentIndex == 2) {
            songTitle.setText("dil diya gallan");
            imageView.setImageResource(R.drawable.pic3);
        }
        if (currentIndex == 3) {
            songTitle.setText("is baarish mein");
            imageView.setImageResource(R.drawable.pic4);
        }
        if (currentIndex == 4) {
            songTitle.setText("hawayein");
            imageView.setImageResource(R.drawable.pic5);
        }
        if (currentIndex == 5) {
            songTitle.setText("song6");
            imageView.setImageResource(R.drawable.pic6);
        }
        if (currentIndex == 6) {
            songTitle.setText("kabhi jo badal barase");
            imageView.setImageResource(R.drawable.p1);
        }
        if (currentIndex == 7) {
            songTitle.setText("tera yar hu mai");
            imageView.setImageResource(R.drawable.p2);
        }
        if (currentIndex == 8) {
            songTitle.setText("tera yar hu mai");
            imageView.setImageResource(R.drawable.p4);
        }
        if (currentIndex == 9) {
            songTitle.setText("finally finally");
            imageView.setImageResource(R.drawable.p5);
        }
        if (currentIndex == 10) {
            songTitle.setText("tenu burj khalifa");
            imageView.setImageResource(R.drawable.p3);
        }
        if (currentIndex == 11) {
            songTitle.setText("tu ki jane pyar mera");
            imageView.setImageResource(R.drawable.p6);
        }
        if (currentIndex == 12) {
            songTitle.setText("hum badala ne lage");
            imageView.setImageResource(R.drawable.p7);
        }
        if (currentIndex == 13) {
            songTitle.setText("DJ vale babu");
            imageView.setImageResource(R.drawable.p8);
        }


        mediaPlayer.start();
        playBtn.setImageResource(R.drawable.pause); // Change to pause icon
        isPlaying = true;
        updateSeekBar();
    }

    // Update seek bar
    private void updateSeekBar() {
        seekBar.setMax(mediaPlayer.getDuration() / 1000);
        mHandler.postDelayed(mUpdateTimeTask, 100);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        mHandler.removeCallbacks(mUpdateTimeTask);
    }
}
