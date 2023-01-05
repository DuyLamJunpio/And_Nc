package lam.fpoly.myrelaxstar.Fragment.Fragment_Music.DownLoad;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.IBinder;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.Toolbar;

import lam.fpoly.myrelaxstar.Activity.MainActivity;
import lam.fpoly.myrelaxstar.Activity.MainActivity2;
import lam.fpoly.myrelaxstar.R;
import lam.fpoly.myrelaxstar.Service.WebView_News;


public class Web_Fragment extends Fragment implements View.OnClickListener {
    WebView idWebFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idWebFragment = view.findViewById(R.id.idWebFragment);
        WebView_News webView_news = new WebView_News();
        Intent intentweb = new Intent(getActivity(), WebView_News.class);
        getActivity().bindService(intentweb,sv_conn, Context.BIND_AUTO_CREATE);
        webView_news.loadWebNews("https://chiasenhac.vn/",idWebFragment,getContext());

        ImageView back = view.findViewById(R.id.back);
        ImageView next = view.findViewById(R.id.next);
        ImageView zoomIn = view.findViewById(R.id.zoomIn);
        ImageView zoomOut = view.findViewById(R.id.zoomOut);

        idWebFragment.requestFocus();
        idWebFragment.getSettings().setBuiltInZoomControls(true);

        back.setOnClickListener(this);
        next.setOnClickListener(this);
        zoomIn.setOnClickListener(this);
        zoomOut.setOnClickListener(this);


    }

    ServiceConnection sv_conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            WebView_News.LocalBinder2 localBinder2 = (WebView_News.LocalBinder2) iBinder;
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                idWebFragment.goBack();
                break;
            case R.id.next:
                idWebFragment.goForward();
                break;
            case R.id.zoomIn:
                idWebFragment.zoomBy(2);
                break;
            case R.id.zoomOut:
                idWebFragment.zoomBy(1);
                break;
        }
    }
}