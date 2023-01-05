package lam.fpoly.myrelaxstar.Adapter;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import lam.fpoly.myrelaxstar.Fragment.Fragment_news_type.Business_Fragment;
import lam.fpoly.myrelaxstar.Fragment.Fragment_news_type.Current_Affairs_Fragment;
import lam.fpoly.myrelaxstar.Fragment.Fragment_news_type.Digitization_Fragment;
import lam.fpoly.myrelaxstar.Fragment.Fragment_news_type.Education_Fragment;
import lam.fpoly.myrelaxstar.Fragment.Fragment_news_type.Entertainment_Fragment;
import lam.fpoly.myrelaxstar.Fragment.Fragment_news_type.Featured_News_Fragment;
import lam.fpoly.myrelaxstar.Fragment.Fragment_news_type.Health_Fragment;
import lam.fpoly.myrelaxstar.Fragment.Fragment_news_type.Newest_Fragment;
import lam.fpoly.myrelaxstar.Fragment.Fragment_news_type.Laugh_Fragment;
import lam.fpoly.myrelaxstar.Fragment.Fragment_news_type.Law_Fragment;
import lam.fpoly.myrelaxstar.Fragment.Fragment_news_type.Life_Fragment;
import lam.fpoly.myrelaxstar.Fragment.Fragment_news_type.Science_Fragment;
import lam.fpoly.myrelaxstar.Fragment.Fragment_news_type.Sport_Fragment;
import lam.fpoly.myrelaxstar.Fragment.Fragment_news_type.Travel_Fragment;
import lam.fpoly.myrelaxstar.Fragment.Fragment_news_type.Vehicle_Fragment;
import lam.fpoly.myrelaxstar.Fragment.Fragment_news_type.World_Fragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    String [] title = {"Mới nhất","Nổi bật","Thế giới","Thời sự","Kinh doanh",
            "Giải trí","Thể thao","Pháp luật","Giáo dục","Sức khỏe","Đời sống","Du lịch",
            "Khoa học","Số hóa","Xe","Cười"};

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {

        Log.d("dcmmmmmmmm : ",String.valueOf(position));

        switch (position){
            case 0:
                return new Newest_Fragment();
            case 1:
                return new Featured_News_Fragment();
            case 2:
                return new World_Fragment();
            case 3:
                return new Current_Affairs_Fragment();
            case 4:
                return new Business_Fragment();
            case 5:
                return new Entertainment_Fragment();
            case 6:
                return new Sport_Fragment();
            case 7:
                return new Law_Fragment();
            case 8:
                return new Education_Fragment();
            case 9:
                return new Health_Fragment();
            case 10:
                return new Life_Fragment();
            case 11:
                return new Travel_Fragment();
            case 12:
                return new Science_Fragment();
            case 13:
                return new Digitization_Fragment();
            case 14:
                return new Vehicle_Fragment();
            case 15:
                return new Laugh_Fragment();
        }
        return new Newest_Fragment();
    }

    @Override
    public int getItemCount() {
        return title.length ;
    }
}
