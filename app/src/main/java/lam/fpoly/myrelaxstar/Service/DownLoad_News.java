package lam.fpoly.myrelaxstar.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import lam.fpoly.myrelaxstar.AsyncTask.LoadTinTuc.DownloadTinTuc;

public class DownLoad_News extends Service {
    public DownLoad_News() {
    }

    IBinder iBinder = new DownLoad_News.LocalBinder1();
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
    public void dowLoad(String urlRss, FragmentActivity fragment , RecyclerView view) {
        DownloadTinTuc downloadTinTuc = new DownloadTinTuc(fragment, view);
        downloadTinTuc.execute(urlRss);
    }
}
