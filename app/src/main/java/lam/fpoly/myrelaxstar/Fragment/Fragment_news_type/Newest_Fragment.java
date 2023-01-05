package lam.fpoly.myrelaxstar.Fragment.Fragment_news_type;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

import lam.fpoly.myrelaxstar.Adapter.photoAdapter;
import lam.fpoly.myrelaxstar.AsyncTask.LoadTinTuc.DownloadTinTuc;
import lam.fpoly.myrelaxstar.Object.Object_Photo;
import lam.fpoly.myrelaxstar.R;
import lam.fpoly.myrelaxstar.Service.DownLoad_News;
import me.relex.circleindicator.CircleIndicator3;


public class Newest_Fragment extends Fragment {
    private ViewPager2 viewPager2;
    private CircleIndicator3 circleIndicator3;
    private lam.fpoly.myrelaxstar.Adapter.photoAdapter photoAdapter;
    private Timer timer;
    private List<Object_Photo> photoList;
    private RecyclerView idRcv_Newest;

    List<String> strings = new ArrayList<>();


    public static Newest_Fragment newInstance() {
        Newest_Fragment fragment = new Newest_Fragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_newest_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idRcv_Newest = view.findViewById(R.id.idRcv_Newest);
        String urlRss = "https://vnexpress.net/rss/tin-moi-nhat.rss";
        DownloadTinTuc downloadTinTuc = new DownloadTinTuc(getActivity(),idRcv_Newest);
        downloadTinTuc.execute(urlRss);

        try {
            strings.add(downloadTinTuc.get().get(0).getImg());
            strings.add(downloadTinTuc.get().get(1).getImg());
            strings.add(downloadTinTuc.get().get(2).getImg());
            strings.add(downloadTinTuc.get().get(0).getLink());
            strings.add(downloadTinTuc.get().get(1).getLink());
            strings.add(downloadTinTuc.get().get(2).getLink());
            strings.add(downloadTinTuc.get().get(0).getTitle());
            strings.add(downloadTinTuc.get().get(1).getTitle());
            strings.add(downloadTinTuc.get().get(2).getTitle());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        slideShow(view);
    }

    private void slideShow(View view){
        photoList = getListPhoto();
        viewPager2 = view.findViewById(R.id.id_viewpager2);
        circleIndicator3 = view.findViewById(R.id.id_Cirle_Indicator3);
        photoAdapter = new photoAdapter(getActivity(),getListPhoto());
        viewPager2.setAdapter(photoAdapter);
        circleIndicator3.setViewPager(viewPager2);
        autoSlideImage();
    }
    private void autoSlideImage() {
        if(photoList == null || photoList.isEmpty() || viewPager2 == null){
            return;
        }

        if(timer == null){
            timer = new Timer();
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager2.getCurrentItem();
                        int totalItem = photoList.size() - 1;
                        if(currentItem < totalItem){
                            currentItem++;
                            viewPager2.setCurrentItem(currentItem);
                        }else{
                            viewPager2.setCurrentItem(0);
                        }
                    }
                });
            }
        },500 ,3000);
    }

    private List<Object_Photo> getListPhoto() {
        List<Object_Photo> list = new ArrayList<>();
        list.add(new Object_Photo(strings.get(0), strings.get(3),strings.get(6)));
        list.add(new Object_Photo(strings.get(1),strings.get(4),strings.get(7)));
        list.add(new Object_Photo(strings.get(2),strings.get(5),strings.get(8)));
        return list;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(timer != null){
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}