package lam.fpoly.myrelaxstar.Fragment.Fragment_Music;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lam.fpoly.myrelaxstar.Adapter.MusicAdapter;
import lam.fpoly.myrelaxstar.MyDataBase.MyDataBase;
import lam.fpoly.myrelaxstar.MyDataBase.MyDataBase2;
import lam.fpoly.myrelaxstar.MyDataBase.MyDataBase3;
import lam.fpoly.myrelaxstar.Object.AudioObject;
import lam.fpoly.myrelaxstar.Object.InputUser;
import lam.fpoly.myrelaxstar.R;


public class Favourites_Fragment extends Fragment {
RecyclerView idRcv123;
    MusicAdapter musicAdapter;
    List<AudioObject> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourites_, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        list = new ArrayList<>();

        musicAdapter = new MusicAdapter(getActivity(), new MusicAdapter.InterClickItemData() {
            @Override
            public void clickItemData(AudioObject audioObject) {
                MyDataBase2.getInstance(getActivity()).musical_dao().delete(audioObject);
                loadData();
            }
        });

        loadData();

        idRcv123 = view.findViewById(R.id.idRcv123);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        idRcv123.addItemDecoration(itemDecoration);
        idRcv123.setLayoutManager(manager);
        idRcv123.setFocusable(false);
        idRcv123.setNestedScrollingEnabled(false);
        idRcv123.setAdapter(musicAdapter);

    }
    private void loadData(){
        InputUser inputUser =  MyDataBase3.getInstance(getActivity()).inputUser_dao().getUser();
        try {
            list = MyDataBase2.getInstance(getActivity()).musical_dao().checkDataTym(R.drawable.ic_baseline_favorite_24,inputUser.getName());
            musicAdapter.setData(list);
        }catch (Exception e){}
    }
}