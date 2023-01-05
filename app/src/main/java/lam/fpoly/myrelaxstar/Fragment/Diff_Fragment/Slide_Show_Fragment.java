package lam.fpoly.myrelaxstar.Fragment.Diff_Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import lam.fpoly.myrelaxstar.Activity.MainActivity2;
import lam.fpoly.myrelaxstar.Object.Object_Photo;
import lam.fpoly.myrelaxstar.R;

public class Slide_Show_Fragment extends Fragment {
private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_slide__show_, container, false);
        // Inflate the layout for this fragment

        Bundle bundle = getArguments();
        Object_Photo photo = (Object_Photo) bundle.get("Ob_Photo");

        ImageView imageView = view.findViewById(R.id.id_Img1);
        Picasso.get().load(photo.getResourceId()).into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("photo",photo);
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }
}