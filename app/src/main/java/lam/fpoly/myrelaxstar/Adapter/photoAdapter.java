package lam.fpoly.myrelaxstar.Adapter;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import java.util.List;

import lam.fpoly.myrelaxstar.Fragment.Diff_Fragment.Slide_Show_Fragment;
import lam.fpoly.myrelaxstar.Object.Object_Photo;

public class photoAdapter extends FragmentStateAdapter {

    private List<Object_Photo> photoList;

    public photoAdapter(@NonNull FragmentActivity fragmentActivity, List<Object_Photo> list) {
        super(fragmentActivity);
        this.photoList = list;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Object_Photo photo = photoList.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Ob_Photo",photo);
        Slide_Show_Fragment slide_show_fragment = new Slide_Show_Fragment();
        slide_show_fragment.setArguments(bundle);
        return slide_show_fragment;
    }

    @Override
    public int getItemCount() {
        if(photoList != null){
            return photoList.size();
        }
        return 0;
    }
}
