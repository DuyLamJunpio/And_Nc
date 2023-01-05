package lam.fpoly.myrelaxstar.Fragment.Fragment_news_type;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lam.fpoly.myrelaxstar.AsyncTask.LoadTinTuc.DownloadTinTuc;
import lam.fpoly.myrelaxstar.R;
import lam.fpoly.myrelaxstar.Service.DownLoad_News;


public class Vehicle_Fragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vehicle_, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView idRcv_Vehicle = view.findViewById(R.id.idRcv_Vehicle);
        String urlRss = "https://vnexpress.net/rss/oto-xe-may.rss";
        DownLoad_News downLoad_news = new DownLoad_News();

        Intent intentDowloadBao = new Intent(getActivity(),DownLoad_News.class);

        getActivity().bindService(intentDowloadBao, sv_conn, Context.BIND_AUTO_CREATE);

        downLoad_news.dowLoad(urlRss,getActivity(),idRcv_Vehicle);
    }

    ServiceConnection sv_conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            DownLoad_News.LocalBinder1 localBinder1 = (DownLoad_News.LocalBinder1) iBinder;
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}