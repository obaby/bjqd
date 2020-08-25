package com.bsit.zxing;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import com.bsit.zxing.config.Config;
import java.io.IOException;

final class BeepManager implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {
    private static final float BEEP_VOLUME = 0.1f;
    private static final String TAG = "BeepManager";
    private static final long VIBRATE_DURATION = 200;
    private final Activity activity;
    private MediaPlayer mediaPlayer = null;
    private boolean playBeep;
    private boolean vibrate;

    BeepManager(Activity activity2) {
        this.activity = activity2;
        updatePrefs();
    }

    /* access modifiers changed from: package-private */
    public synchronized void updatePrefs() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.activity);
        this.playBeep = shouldBeep(defaultSharedPreferences, this.activity);
        this.vibrate = defaultSharedPreferences.getBoolean(Config.KEY_VIBRATE, false);
        if (this.playBeep && this.mediaPlayer == null) {
            this.activity.setVolumeControlStream(3);
            this.mediaPlayer = buildMediaPlayer(this.activity);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void playBeepSoundAndVibrate() {
        if (this.playBeep && this.mediaPlayer != null) {
            this.mediaPlayer.start();
        }
        if (this.vibrate) {
            ((Vibrator) this.activity.getSystemService("vibrator")).vibrate(VIBRATE_DURATION);
        }
    }

    private static boolean shouldBeep(SharedPreferences sharedPreferences, Context context) {
        boolean z = sharedPreferences.getBoolean(Config.KEY_PLAY_BEEP, true);
        if (!z || ((AudioManager) context.getSystemService("audio")).getRingerMode() == 2) {
            return z;
        }
        return false;
    }

    private MediaPlayer buildMediaPlayer(Context context) {
        MediaPlayer mediaPlayer2 = new MediaPlayer();
        mediaPlayer2.setAudioStreamType(3);
        mediaPlayer2.setOnCompletionListener(this);
        mediaPlayer2.setOnErrorListener(this);
        AssetFileDescriptor openRawResourceFd = context.getResources().openRawResourceFd(R.raw.beep);
        try {
            mediaPlayer2.setDataSource(openRawResourceFd.getFileDescriptor(), openRawResourceFd.getStartOffset(), openRawResourceFd.getLength());
            openRawResourceFd.close();
            mediaPlayer2.setVolume(BEEP_VOLUME, BEEP_VOLUME);
            mediaPlayer2.prepare();
            return mediaPlayer2;
        } catch (IOException e) {
            Log.w(TAG, e);
            return null;
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer2) {
        mediaPlayer2.seekTo(0);
    }

    public synchronized boolean onError(MediaPlayer mediaPlayer2, int i, int i2) {
        if (i == 100) {
            try {
                this.activity.finish();
            } catch (Throwable th) {
                throw th;
            }
        } else {
            mediaPlayer2.release();
            this.mediaPlayer = null;
            updatePrefs();
        }
        return true;
    }

    public synchronized void close() {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.release();
            this.mediaPlayer = null;
        }
    }
}
