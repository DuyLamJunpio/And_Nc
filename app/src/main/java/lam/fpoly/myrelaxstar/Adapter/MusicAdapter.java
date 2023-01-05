package lam.fpoly.myrelaxstar.Adapter;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileDescriptor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lam.fpoly.myrelaxstar.Activity.Playing_Now;
import lam.fpoly.myrelaxstar.Class.MyMediaPlayer;
import lam.fpoly.myrelaxstar.MyDataBase.MyDataBase2;
import lam.fpoly.myrelaxstar.MyDataBase.MyDataBase3;
import lam.fpoly.myrelaxstar.Object.AudioModel;
import lam.fpoly.myrelaxstar.Object.AudioObject;
import lam.fpoly.myrelaxstar.Object.InputUser;
import lam.fpoly.myrelaxstar.R;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder>{

    List<AudioObject> songsList;

    Context context;

    ArrayList<AudioModel> songs;

    public void setData(List<AudioObject> data){
        this.songsList = data;
        notifyDataSetChanged();
    }

    private InterClickItemData interClickItemData;

    public interface InterClickItemData {
        void clickItemData(AudioObject audioObject);
    }


    public MusicAdapter(Context context,  InterClickItemData interClickItemData) {
        this.context = context;
        this.interClickItemData = interClickItemData;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layoutitem_music,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final AudioObject songData = songsList.get(position);
        int i = position;
        holder.titleTextView.setText(songData.getTitle());
        holder.tvArtist.setText(songData.getArtist());
        holder.iconImageView.setImageBitmap(getAlbumart(songData.getIdImg()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MyMediaPlayer.getInstance().reset();
                MyMediaPlayer.currentIndex = i;
                Intent intent = new Intent(context, Playing_Now.class);
                intent.putExtra("LIST",MusicListAdapter.songsList);
                intent.putExtra("position",i);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


        holder.imgTym.setImageResource(songData.getImgTym());


        holder.imgTym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interClickItemData.clickItemData(songData);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (songsList != null) {
            return songsList.size();
        }
        return 0;
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
}
