package lam.fpoly.myrelaxstar.Activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;
import com.learnoset.material.ui.learnosetnavigationbar.CustomNavTheme;
import com.learnoset.material.ui.learnosetnavigationbar.LearnosetExceptions;
import com.learnoset.material.ui.learnosetnavigationbar.LearnosetNavItem;
import com.learnoset.material.ui.learnosetnavigationbar.LearnosetNavigationBar;
import com.learnoset.material.ui.learnosetnavigationbar.NavItemsGroup;
import com.learnoset.material.ui.learnosetnavigationbar.NavigationEventListener;

import java.io.File;
import java.util.List;

import lam.fpoly.myrelaxstar.Class.DataLocalManager;
import lam.fpoly.myrelaxstar.Fragment.Diff_Fragment.Account_Fragment;
import lam.fpoly.myrelaxstar.Fragment.Fragment_Music.Music_Fragment;
import lam.fpoly.myrelaxstar.Fragment.Fragment_Newspaper.News_Fragment;
import lam.fpoly.myrelaxstar.Object.User;
import lam.fpoly.myrelaxstar.R;

public class MainActivity extends AppCompatActivity {
    private LearnosetNavigationBar learnosetNavigationBar;
    public DrawerLayout drawerLayout;
    private AppBarLayout dynamic_island;
    private Boolean checkColor = false;
    public static boolean checkPermission = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        if (!DataLocalManager.getFirstInstaller()){
            DataLocalManager.setFirstInstaller(true);
        }


        dynamic_island = findViewById(R.id.dynamic_island);

        dynamic_island.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNav();
            }
        });

        drawerLayout = findViewById(R.id.drawerLayout);
        learnosetNavigationBar = findViewById(R.id.navigatioNabr);

        settingCustomIcons();
        learnosetNavigationBar.addNavItem(LearnosetNavItem.BuiltInItems.PROFILE);

        learnosetNavigationBar.setDrawerLayout(drawerLayout, LearnosetNavigationBar.DrawerGravity.LEFT);

        //settingCustomColorDark();
        settingCustomColorLight();

        NavItemsGroup navItemsGroup = new NavItemsGroup("Settings");
        navItemsGroup.addGroupItem(LearnosetNavItem.BuiltInItems.TOOLS);
        learnosetNavigationBar.addItemsGroup(navItemsGroup);

        learnosetNavigationBar.setEventListener(new NavigationEventListener() {
            @Override
            public void onItemSelected(int position, LearnosetNavItem selectedNavItem) {
                // TODO Your code goes here to perform action according to the selected Item
                switch (position){
                    case 0:
                        selectedNavItem.setFragment(new News_Fragment(),R.id.fragmentContainer);
                        break;
                    case 1:
                        selectedNavItem.setFragment(new Music_Fragment(),R.id.fragmentContainer);
                        break;
                    case 2:
                            selectedNavItem.setFragment(new Account_Fragment(), R.id.fragmentContainer);
                        break;
                    case 3:
                        if(checkColor == false){
                            settingCustomColorLight();
                        }else {
                            settingCustomColorDark();
                        }
                        break;
                }
            }
            @Override
            public void onLogOutBtnClick() {
                drawerLayout.setVisibility(View.GONE);
//                drawerLayout.closeDrawer(GravityCompat.START);
//                MainActivity.super.onDestroy();
            }
        });

//        Intent intent = new Intent(this,Playing_Now.class);
//        startActivity(intent);

        settingProfile();

    }

    private void settingCustomColorDark(){
        CustomNavTheme customNavTheme = new CustomNavTheme();
        customNavTheme.setIconsColor(Color.parseColor("#FF03DAC5"));
        customNavTheme.setNavigationBackground(Color.BLACK);
        customNavTheme.setSelectedItemBackgroundColor(Color.parseColor("#FF03DAC5"));
        customNavTheme.setSelectedItemIconColor(Color.WHITE);
        customNavTheme.setSelectedItemTextColor(Color.WHITE);
        customNavTheme.setTextColor(Color.WHITE);

        learnosetNavigationBar.setTheme(customNavTheme);

        learnosetNavigationBar.setSelectedItemBackground(LearnosetNavigationBar.NavColors.ORANGE);
        learnosetNavigationBar.setIconsColor(LearnosetNavigationBar.NavColors.ORANGE);
        checkColor = false;
    }

    private void settingCustomColorLight(){
        CustomNavTheme customNavTheme = new CustomNavTheme();
        customNavTheme.setIconsColor(Color.parseColor("#FF03DAC5"));
        customNavTheme.setNavigationBackground(Color.WHITE);
        customNavTheme.setSelectedItemBackgroundColor(Color.parseColor("#FF03DAC5"));
        customNavTheme.setSelectedItemIconColor(Color.WHITE);
        customNavTheme.setSelectedItemTextColor(Color.WHITE);
        customNavTheme.setTextColor(Color.GRAY);

        learnosetNavigationBar.setTheme(customNavTheme);

        learnosetNavigationBar.setSelectedItemBackground(LearnosetNavigationBar.NavColors.DARK_BLUE);
        learnosetNavigationBar.setIconsColor(LearnosetNavigationBar.NavColors.BLACK);
        checkColor = true;
    }

    private void settingCustomIcons(){
        LearnosetNavItem customItem1 = new LearnosetNavItem();
        customItem1.setTitle("Newspaper");
        customItem1.setIcon(R.drawable.newspaper_64);
        customItem1.setSelected(true);
        customItem1.setFragment(new News_Fragment(),R.id.fragmentContainer);

        LearnosetNavItem customItem2 = new LearnosetNavItem("Itunes Music", R.drawable.headphone);
        learnosetNavigationBar.addNavItem(customItem1);
        learnosetNavigationBar.addNavItem(customItem2);
    }

    private void settingProfile(){

        File file = new File("You Image File Path");
        try {
            learnosetNavigationBar.setHeaderData("Duy Lâm",file);
        } catch (LearnosetExceptions learnosetExceptions) {
            learnosetExceptions.printStackTrace();
        }
        learnosetNavigationBar.setHeaderData("Duy Lâm", "https://i1-vnexpress.vnecdn.net/2022/10/19/-2174-1666176633.jpg?w=1200&h=0&q=100&dpr=1&fit=crop&s=NFsKrAZnl5c1UZYMNak2DQ");
    }

    public void openNav(){
        drawerLayout.openDrawer(GravityCompat.START);
    }




    public void request() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                checkPermission = true;

            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(MainActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                checkPermission = false;
            }
        };
        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.WAKE_LOCK,
                        Manifest.permission.ACCESS_NETWORK_STATE)
                .check();
    }

    @Override
    public void onBackPressed() {
        drawerLayout.closeDrawer(learnosetNavigationBar);
        if(drawerLayout.isDrawerOpen(learnosetNavigationBar)){
            drawerLayout.closeDrawer(learnosetNavigationBar);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}