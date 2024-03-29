package lam.fpoly.myrelaxstar.Class;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class MyApplicationChannel extends Application {
    public static final String CHANNEL_ID = "CHANNEL_MUSIC_APP";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        DataLocalManager.init(getApplicationContext());
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"Channel Music", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            if(manager != null){
                manager.createNotificationChannel(channel);
            }
        }
    }
}
