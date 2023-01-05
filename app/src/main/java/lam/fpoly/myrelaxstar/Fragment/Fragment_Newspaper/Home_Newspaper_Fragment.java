package lam.fpoly.myrelaxstar.Fragment.Fragment_Newspaper;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import lam.fpoly.myrelaxstar.Adapter.ViewPagerAdapter;
import lam.fpoly.myrelaxstar.AsyncTask.LoadTinTuc.DownloadTinTuc;
import lam.fpoly.myrelaxstar.Object.Object_Photo;
import lam.fpoly.myrelaxstar.R;
import lam.fpoly.myrelaxstar.Adapter.photoAdapter;
import me.relex.circleindicator.CircleIndicator3;


public class Home_Newspaper_Fragment extends Fragment {
    private TabLayout idTabLayOut;
    private ViewPager2 idViewPager2;

    String [] title = {"Mới nhất","Nổi bật","Thế giới","Thời sự","Kinh doanh",
            "Giải trí","Thể thao","Pháp luật","Giáo dục","Sức khỏe","Đời sống","Du lịch",
            "Khoa học","Số hóa","Xe","Cười"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home__newspaper_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ScrollTab(view);
    }

    public void ScrollTab(View view){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity());
        idViewPager2 = view.findViewById(R.id.idViewPager2);
        idTabLayOut = view.findViewById(R.id.idTabLayOut);
        idViewPager2.setAdapter(adapter);
        new TabLayoutMediator(idTabLayOut, idViewPager2,((tab, position) -> tab.setText(title[position]))).attach();
        idTabLayOut.setTabGravity(TabLayout.GRAVITY_CENTER);
        idTabLayOut.setTabMode(TabLayout.MODE_SCROLLABLE);
    }
}