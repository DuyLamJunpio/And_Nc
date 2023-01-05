package lam.fpoly.myrelaxstar.Service;

import static lam.fpoly.myrelaxstar.Class.MyApplicationChannel.CHANNEL_ID;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.media.session.MediaButtonReceiver;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import lam.fpoly.myrelaxstar.Activity.Playing_Now;
import lam.fpoly.myrelaxstar.AsyncTask.LoadTinTuc.DownloadTinTuc;
import lam.fpoly.myrelaxstar.Class.MyMediaPlayer;
import lam.fpoly.myrelaxstar.Object.AudioModel;
import lam.fpoly.myrelaxstar.R;

public class PlayMusic_Service extends Service {
    int x = 0;
    public PlayMusic_Service() {
    }

    IBinder iBinder = new PlayMusic_Service.LocalBinder1();
    public class LocalBinder1 extends Binder {

        LocalBinder1 getLocalBinder(){
            return LocalBinder1.this; // phương thức khởi tạo khi client gọi tới các phương thức của service

        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void playMusicService(Activity activity, MediaPlayer mediaPlayer , SeekBar seekBar,
                                 TextView currentTimeTv, ImageView pausePlay , ImageView musicIcon ,
                                 AudioModel currentSong , List<AudioModel> songsList, TextView titleTv
            , TextView totalTimeTv , ImageView nextBtn, ImageView previousBtn, TextView Aritis) {
        setResourcesWithMusic(currentSong,songsList,titleTv,totalTimeTv,pausePlay,nextBtn,previousBtn,mediaPlayer,seekBar,Aritis,musicIcon,activity);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer!=null){
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    currentTimeTv.setText(convertToMMSS(mediaPlayer.getCurrentPosition()+""));

                    if(mediaPlayer.isPlaying()){
                        pausePlay.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24);
                        musicIcon.setRotation(x++);
                        //sendNotification(currentSong,songsList,activity,mediaPlayer);
                    }else{
                        pausePlay.setImageResource(R.drawable.ic_baseline_play_circle_outline_24);
                        musicIcon.setRotation(0);
                    }

                }
                new Handler().postDelayed(this,100);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mediaPlayer!=null && fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    void setResourcesWithMusic(AudioModel currentSong , List<AudioModel> songsList
            , TextView titleTv , TextView totalTimeTv , ImageView pausePlay , ImageView nextBtn
            , ImageView previousBtn, MediaPlayer mediaPlayer,SeekBar seekBar,TextView Aritis ,ImageView musicIcon,Activity activity){
        currentSong = songsList.get(MyMediaPlayer.currentIndex);

        titleTv.setText(currentSong.getTitle());
        Aritis.setText(currentSong.getArtist());
        musicIcon.setImageBitmap(getAlbumart(currentSong.getIdImg(),activity));
        totalTimeTv.setText(convertToMMSS(currentSong.getDuration()));

        pausePlay.setOnClickListener(v-> pausePlay(mediaPlayer));
        AudioModel finalCurrentSong = currentSong;
        nextBtn.setOnClickListener(v-> playNextSong(finalCurrentSong,songsList,titleTv,totalTimeTv
                ,pausePlay,nextBtn,previousBtn,mediaPlayer,seekBar,Aritis,musicIcon,activity));
        previousBtn.setOnClickListener(v-> playPreviousSong(finalCurrentSong,songsList
                ,titleTv,totalTimeTv,pausePlay,nextBtn,previousBtn,mediaPlayer,seekBar,Aritis,musicIcon,activity));

        playMusic(mediaPlayer,finalCurrentSong,seekBar);


    }


    private void playMusic(MediaPlayer mediaPlayer , AudioModel currentSong , SeekBar seekBar){

        mediaPlayer.reset();

        try {
            mediaPlayer.setDataSource(currentSong.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
            seekBar.setProgress(0);
            seekBar.setMax(mediaPlayer.getDuration());



    }

    private void playNextSong(AudioModel currentSong , List<AudioModel> songsList
            , TextView titleTv , TextView totalTimeTv , ImageView pausePlay , ImageView nextBtn
            , ImageView previousBtn, MediaPlayer mediaPlayer,SeekBar seekBar,TextView Aritis ,ImageView musicIcon,Activity activity){

        if(MyMediaPlayer.currentIndex== songsList.size()-1)
            return;
        MyMediaPlayer.currentIndex +=1;
        mediaPlayer.reset();
        setResourcesWithMusic(currentSong,songsList,titleTv,totalTimeTv,pausePlay,nextBtn,previousBtn,mediaPlayer,seekBar,Aritis,musicIcon,activity);

    }

    private void playPreviousSong(AudioModel currentSong , List<AudioModel> songsList
            , TextView titleTv , TextView totalTimeTv , ImageView pausePlay , ImageView nextBtn
            , ImageView previousBtn, MediaPlayer mediaPlayer,SeekBar seekBar,TextView Aritis ,ImageView musicIcon,Activity activity){
        if(MyMediaPlayer.currentIndex== 0)
            return;
        MyMediaPlayer.currentIndex -=1;
        mediaPlayer.reset();
        setResourcesWithMusic(currentSong,songsList,titleTv,totalTimeTv,pausePlay,nextBtn,previousBtn,mediaPlayer,seekBar,Aritis,musicIcon,activity);
    }

    private void pausePlay(MediaPlayer mediaPlayer){
        if(mediaPlayer.isPlaying())
            mediaPlayer.pause();
        else
            mediaPlayer.start();
    }


    public static String convertToMMSS(String duration){
        Long millis = Long.parseLong(duration);
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
    }

    public Bitmap getAlbumart(Long album_id,Activity activity) {
        Bitmap bm = null;
        try {
            final Uri sArtworkUri = Uri
                    .parse("content://media/external/audio/albumart");

            Uri uri = ContentUris.withAppendedId(sArtworkUri, album_id);

            ParcelFileDescriptor pfd = activity.getContentResolver()
                    .openFileDescriptor(uri, "r");

            if (pfd != null) {
                FileDescriptor fd = pfd.getFileDescriptor();
                bm = BitmapFactory.decodeFileDescriptor(fd);
            }
        } catch (Exception e) {
        }
        return bm;
    }



}
