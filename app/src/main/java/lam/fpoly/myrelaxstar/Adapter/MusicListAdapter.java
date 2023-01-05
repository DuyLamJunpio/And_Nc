package lam.fpoly.myrelaxstar.Adapter;

import static lam.fpoly.myrelaxstar.Class.MyApplicationChannel.CHANNEL_ID;

import android.app.Activity;
import android.app.Notification;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileDescriptor;
import java.util.ArrayList;
import java.util.List;

import lam.fpoly.myrelaxstar.Activity.ActivityLogin;
import lam.fpoly.myrelaxstar.Activity.Playing_Now;
import lam.fpoly.myrelaxstar.Class.MyMediaPlayer;
import lam.fpoly.myrelaxstar.Fragment.Diff_Fragment.Account_Fragment;
import lam.fpoly.myrelaxstar.MyDataBase.MyDataBase2;
import lam.fpoly.myrelaxstar.MyDataBase.MyDataBase3;
import lam.fpoly.myrelaxstar.Object.AudioModel;
import lam.fpoly.myrelaxstar.Object.AudioObject;
import lam.fpoly.myrelaxstar.Object.InputUser;
import lam.fpoly.myrelaxstar.R;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder>{

    public static ArrayList<AudioModel> songsList;

    Context context;

    AudioObject audioObject;

    MediaPlayer mediaPlayer = new MediaPlayer();

    public void setData(ArrayList<AudioModel> data){
        this.songsList = data;
        notifyDataSetChanged();
    }

    List<AudioObject> listcheck;


    public MusicListAdapter( Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layoutitem_music,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final AudioModel songData = songsList.get(position);
        int i = position;
        holder.titleTextView.setText(songData.getTitle());
        holder.tvArtist.setText(songData.getArtist());
        holder.iconImageView.setImageBitmap(getAlbumart(songData.getIdImg()));

        holder.imgTym.setImageResource(songData.getImgAvt());

        //sendNotification(songData,songsList,context,mediaPlayer,i);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyMediaPlayer.getInstance().reset();
                MyMediaPlayer.currentIndex = i;
                Intent intent = new Intent(context, Playing_Now.class);
                intent.putExtra("LIST",songsList);
                intent.putExtra("position",i);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.imgTym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputUser inputUser = MyDataBase3.getInstance(context).inputUser_dao().getUser();
                try{
                audioObject = new AudioObject(inputUser.getName(),songData.getPath(),
                        songData.getTitle(), songData.getDuration(),
                        songData.getArtist(), songData.getIdImg(),R.drawable.ic_baseline_favorite_24);}
                catch (Exception e){}
                if(!Account_Fragment.checkLogin){
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Thông báo!");
                    builder.setMessage("Yêu cầu đang nhập");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(context, ActivityLogin.class);
                            context.startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("No",null);
                    builder.show();
                    return;
                }
                listcheck = new ArrayList<>();
                listcheck = MyDataBase2.getInstance(context).musical_dao().getList(inputUser.getName());
                for(int f = 0 ; f < listcheck.size() ; f++) {
                    if(songData.getTitle().equals(listcheck.get(f).getTitle())){
                        holder.imgTym.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                        MyDataBase2.getInstance(context).musical_dao().deleteTitle(listcheck.get(f).getTitle());
                        return;
                        }
                }
                MyDataBase2.getInstance(context).musical_dao().insertData(audioObject);
                holder.imgTym.setImageResource(R.drawable.ic_baseline_favorite_24);
            }
        });
    }


    @Override
    public int getItemCount() {
        return songsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleTextView, tvArtist;
        ImageView iconImageView,imgTym;
        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.song_title);
            iconImageView = itemView.findViewById(R.id.imgAvtSong);
            tvArtist = itemView.findViewById(R.id.song_artist);
            imgTym = itemView.findViewById(R.id.imgTym);
        }
    }
    public Bitmap getAlbumart(Long album_id) {
        Bitmap bm = null;
        try {
            final Uri sArtworkUri = Uri
                    .parse("content://media/external/audio/albumart");

            Uri uri = ContentUris.withAppendedId(sArtworkUri, album_id);

            ParcelFileDescriptor pfd = context.getContentResolver()
                    .openFileDescriptor(uri, "r");

            if (pfd != null) {
                FileDescriptor fd = pfd.getFileDescriptor();
                bm = BitmapFactory.decodeFileDescriptor(fd);
            }
        } catch (Exception e) {
        }
        return bm;
    }

    private void sendNotification(AudioModel currentSong , List<AudioModel> songsList, Context context, MediaPlayer mediaPlayer, int position){

        currentSong = songsList.get(mediaPlayer.getCurrentPosition());

        MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(context,"tag");


        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                // Show controls on lock screen even when user hides sensitive content.
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSmallIcon(R.drawable.ic_baseline_music_note_24)
                .setSubText("DuyLamJunpio")
                .setContentTitle(currentSong.getTitle())
                .setContentInfo(currentSong.getArtist())
                .setLargeIcon(getAlbumart(currentSong.getIdImg()))
                // Add media control buttons that invoke intents in your media service
                .addAction(R.drawable.ic_baseline_skip_previous_24, "Previous", null) // #0
                .addAction(R.drawable.ic_baseline_pause_circle_filled_24, "Pause", null)  // #1
                .addAction(R.drawable.ic_baseline_skip_next_24, "Next", null)     // #2
                // Apply the media style template
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1 /* #1: pause button */)
                        .setMediaSession(mediaSessionCompat.getSessionToken()))
                .build();

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        managerCompat.notify(0,notification);
    }
}
