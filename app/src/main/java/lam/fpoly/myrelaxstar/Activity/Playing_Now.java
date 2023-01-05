package lam.fpoly.myrelaxstar.Activity;

import static lam.fpoly.myrelaxstar.Class.MyApplicationChannel.CHANNEL_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Activity;
import android.app.Notification;
import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.FileDescriptor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lam.fpoly.myrelaxstar.Class.MyMediaPlayer;

import lam.fpoly.myrelaxstar.Fragment.Fragment_Music.Favourites_Fragment;
import lam.fpoly.myrelaxstar.Object.AudioModel;
import lam.fpoly.myrelaxstar.R;
import lam.fpoly.myrelaxstar.Service.PlayMusic_Service;

public class Playing_Now extends AppCompatActivity {
    TextView titleTv,currentTimeTv,totalTimeTv,Artist;
    SeekBar seekBar;
    ImageView pausePlay,nextBtn,previousBtn,musicIcon,idBackActivity,idTymPlay;
    List<AudioModel> songsList;
    AudioModel currentSong;
    MediaPlayer mediaPlayer = MyMediaPlayer.getInstance();
    int x=0;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_now);
        getSupportActionBar().hide();
        titleTv = findViewById(R.id.TitlePlay);
        Artist = findViewById(R.id.ArtistPlay);
        currentTimeTv = findViewById(R.id.current_time);
        totalTimeTv = findViewById(R.id.total_time);
        seekBar = findViewById(R.id.seek_bar);
        pausePlay = findViewById(R.id.pause_play);
        nextBtn = findViewById(R.id.next);
        previousBtn = findViewById(R.id.previous);
        musicIcon = findViewById(R.id.imgAvtPlay);
        idTymPlay = findViewById(R.id.idTymPlay);
        idBackActivity = findViewById(R.id.idBackActivity);

        idBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Playing_Now.super.onBackPressed();
            }
        });

        titleTv.setSelected(true);
        Artist.setSelected(true);

        songsList = (ArrayList<AudioModel>) getIntent().getSerializableExtra("LIST");

        idTymPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = getIntent().getExtras().getInt("position");
                Intent intent = new Intent(Playing_Now.this, Favourites_Fragment.class);
                intent.putExtra("array", (Serializable) songsList);
                intent.putExtra("position",i);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        

        PlayMusic_Service service = new PlayMusic_Service();
        Intent intent = new Intent(Playing_Now.this, PlayMusic_Service.class);
        bindService(intent,sv_conn, Context.BIND_AUTO_CREATE);
        service.playMusicService(Playing_Now.this,mediaPlayer,seekBar,currentTimeTv,pausePlay,musicIcon
                ,currentSong,songsList,titleTv,totalTimeTv,nextBtn,previousBtn,Artist);
    }

    ServiceConnection sv_conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            PlayMusic_Service.LocalBinder1 iBinder1 = (PlayMusic_Service.LocalBinder1) iBinder;
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };


}