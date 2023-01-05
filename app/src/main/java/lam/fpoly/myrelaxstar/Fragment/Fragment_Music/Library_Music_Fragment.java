package lam.fpoly.myrelaxstar.Fragment.Fragment_Music;


import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileDescriptor;
import java.util.ArrayList;
import java.util.List;

import lam.fpoly.myrelaxstar.Activity.MainActivity2;
import lam.fpoly.myrelaxstar.Activity.Playing_Now;
import lam.fpoly.myrelaxstar.Adapter.MusicListAdapter;
import lam.fpoly.myrelaxstar.MyDataBase.MyDataBase2;
import lam.fpoly.myrelaxstar.MyDataBase.MyDataBase3;
import lam.fpoly.myrelaxstar.Object.AudioModel;
import lam.fpoly.myrelaxstar.Object.AudioObject;
import lam.fpoly.myrelaxstar.Object.InputUser;
import lam.fpoly.myrelaxstar.R;



public class Library_Music_Fragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<AudioModel> songsList = new ArrayList<>();
    MusicListAdapter musicListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library__music_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.idRcvMusic);

        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.ALBUM_ID
        };

        String selection = MediaStore.Audio.Media.IS_MUSIC +" != 0";

        Cursor cursor = getActivity().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,projection,selection,null,null);
        while(cursor.moveToNext()){
            AudioModel songData = new AudioModel(cursor.getString(1),
                    cursor.getString(0),cursor.getString(2)
                    ,cursor.getString(3),cursor.getLong(4),setAvt(cursor.getString(0)));
            if(new File(songData.getPath()).exists())
                songsList.add(songData);
        }


            //recyclerview
        musicListAdapter = new MusicListAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(musicListAdapter);
        musicListAdapter.setData(songsList);
    }

    private int setAvt(String title){
        List<AudioObject> list = new ArrayList<>();
        InputUser inputUser = MyDataBase3.getInstance(getActivity()).inputUser_dao().getUser();
        try{
            list = MyDataBase2.getInstance(getActivity()).musical_dao().getList(inputUser.getName());
        }catch (Exception e){}
            for (int k = 0 ; k < list.size() ; k++) {
                if (title.equals(list.get(k).getTitle())) {
                    return R.drawable.ic_baseline_favorite_24;
                }
            }
            return R.drawable.ic_baseline_favorite_border_24;
    }

    @Override
    public void onResume() {
        super.onResume();
        musicListAdapter.setData(songsList);
    }
}