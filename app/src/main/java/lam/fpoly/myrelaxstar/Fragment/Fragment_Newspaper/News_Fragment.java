package lam.fpoly.myrelaxstar.Fragment.Fragment_Newspaper;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.learnoset.material.ui.learnosetbottombar.BottomBarEventListener;
import com.learnoset.material.ui.learnosetbottombar.BottomBarItem;
import com.learnoset.material.ui.learnosetbottombar.CustomBottomBarTheme;
import com.learnoset.material.ui.learnosetbottombar.LearnosetBottomBar;

import lam.fpoly.myrelaxstar.R;


public class News_Fragment extends Fragment {
    private LearnosetBottomBar bottomBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomBar = view.findViewById(R.id.bottomBar2);

        bottomBar.addItem(BottomBarItem.BuiltInItems.HOME,new Home_Newspaper_Fragment(),R.id.fragmentContainer3);
        bottomBar.addItem(BottomBarItem.BuiltInItems.FAVOURITES);

        settingColorBottomBar();
        settingAnimation();

        bottomBar.setEventListener(new BottomBarEventListener() {
            @Override
            public void onItemSelected(BottomBarItem bottomBarItem, int selectedItemPosition) {
                switch (selectedItemPosition){
                    case 0:
                        bottomBarItem.setFragment(new Home_Newspaper_Fragment(),R.id.fragmentContainer3);
                        break;
                    case 1:
                        bottomBarItem.setFragment(new Favourites_News_Fragment(),R.id.fragmentContainer3);
                        break;
                }
            }
        });


    }

    private void settingColorBottomBar(){

        CustomBottomBarTheme customBottomBarTheme = new CustomBottomBarTheme();
        customBottomBarTheme.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        customBottomBarTheme.setSelectedItemBackgroundColor(LearnosetBottomBar.LearnosetColors.ORANGE);
        customBottomBarTheme.setIconsColor(LearnosetBottomBar.LearnosetColors.GRAY);
        bottomBar.setTheme(customBottomBarTheme);

    }

    private void settingAnimation(){

        bottomBar.enableAnimation(true);

        bottomBar.enableAnimation(false);

    }

}