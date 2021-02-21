package com.aice.pausemusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;

import com.aice.pausemusic.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    private MediaPlayer mPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initPlayer();
        mBinding.btnStopOther.setOnClickListener(v -> stopOtherMusic());
        mBinding.btnStart.setOnClickListener(v -> resumeMusic());
        mBinding.btnPause.setOnClickListener(v -> pauseMusic());
    }

    private void initPlayer() {
        try {
            mPlayer = new MediaPlayer();
            AssetFileDescriptor fd = getAssets().openFd("test.mp3");
            mPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());//指定音频文件路径
            mPlayer.setLooping(true);//设置为循环播放
            mPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void resumeMusic() {
        if (!mPlayer.isPlaying()) {
            mPlayer.start();
        }
    }

    private void pauseMusic() {
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
        }
    }

    private void stopOtherMusic() {
        AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        if(audioManager.isMusicActive()){
            audioManager.requestAudioFocus(null, AudioManager.STREAM_MUSIC,
                    AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        }
        Toast.makeText(this, "暂停", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        resumeMusic();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        pauseMusic();
    }
}