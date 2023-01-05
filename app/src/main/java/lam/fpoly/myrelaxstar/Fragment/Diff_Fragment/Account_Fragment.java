package lam.fpoly.myrelaxstar.Fragment.Diff_Fragment;

import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.FileDescriptor;
import java.util.ArrayList;
import java.util.List;

import lam.fpoly.myrelaxstar.Activity.ActivityLogin;
import lam.fpoly.myrelaxstar.Activity.CapNhatAvatar;
import lam.fpoly.myrelaxstar.MyDataBase.MyDataBase1;
import lam.fpoly.myrelaxstar.MyDataBase.MyDataBase3;
import lam.fpoly.myrelaxstar.Object.InputUser;
import lam.fpoly.myrelaxstar.Object.TinTuc;
import lam.fpoly.myrelaxstar.Object.User;
import lam.fpoly.myrelaxstar.R;
import lam.fpoly.myrelaxstar.Service.DangNhap;


public class Account_Fragment extends Fragment {
public static ImageView imgAvtUser;
private TextView userNameAcc,userPassAcc,tvLogOut;
public static boolean checkLogin = false;
private List<InputUser> list;
private ProgressBar idProgressBarAcc;

    public static final String TAG = Account_Fragment.class.getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgAvtUser = view.findViewById(R.id.imgAvtUser);
        userNameAcc = view.findViewById(R.id.userNameAcc);
        userPassAcc = view.findViewById(R.id.userPassAcc);
        tvLogOut = view.findViewById(R.id.tvLogOut);
        idProgressBarAcc = view.findViewById(R.id.idProgressBarAcc);

        list = new ArrayList<>();

        if (checkLogin == true){
            list = MyDataBase3.getInstance(getActivity()).inputUser_dao().getListInputUser();
            userNameAcc.setText(list.get(0).getName());
            userPassAcc.setText(list.get(0).getPass());
            if(list.get(0).getAvt() == null){
                imgAvtUser.setImageResource(R.mipmap.ic_launcher_round);
            }else {
                imgAvtUser.setImageBitmap(getAlbumart(list.get(0).getAvt()));
            }
            tvLogOut.setVisibility(View.VISIBLE);
        }else{
            imgAvtUser.setImageResource(R.mipmap.ic_launcher_round);
            userNameAcc.setText("");
            userPassAcc.setText("");
            MyDataBase3.getInstance(getActivity()).inputUser_dao().deleteData();
        }



        imgAvtUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Dialog dialog = new Dialog(getActivity());
               dialog.setContentView(R.layout.layoutaccount);
               ImageView imgCamera = dialog.findViewById(R.id.imgCamera);
               ImageView imgPicture = dialog.findViewById(R.id.imgPicture);
               imgCamera.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Intent intent = new Intent(dialog.getContext(),CapNhatAvatar.class);
                       dialog.getContext().startActivity(intent);
                       dialog.dismiss();
                   }
               });
               imgPicture.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       openGallery();
                       dialog.dismiss();
                   }
               });
               dialog.show();
            }
        });

        tvLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDataBase3.getInstance(getActivity()).inputUser_dao().deleteData();
                imgAvtUser.setImageResource(R.mipmap.ic_launcher_round);
                userNameAcc.setText("");
                userPassAcc.setText("");
                checkLogin = false;
                tvLogOut.setVisibility(View.GONE);
                idProgressBarAcc.setVisibility(View.VISIBLE);
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        idProgressBarAcc.setVisibility(View.GONE);
                        Intent intent = new Intent(getActivity(), ActivityLogin.class);
                        startActivity(intent);
                    }
                },3000);
            }
        });
    }

    private void openGallery() {

    }

    public Bitmap getAlbumart(Long album_id) {
        Bitmap bm = null;
        try {
            final Uri sArtworkUri = Uri
                    .parse("content://media/external/audio/albumart");

            Uri uri = ContentUris.withAppendedId(sArtworkUri, album_id);

            ParcelFileDescriptor pfd = getActivity().getContentResolver()
                    .openFileDescriptor(uri, "r");

            if (pfd != null) {
                FileDescriptor fd = pfd.getFileDescriptor();
                bm = BitmapFactory.decodeFileDescriptor(fd);
            }
        } catch (Exception e) {
        }
        return bm;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (checkLogin == false){
            tvLogOut.setVisibility(View.GONE);
            idProgressBarAcc.setVisibility(View.VISIBLE);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    idProgressBarAcc.setVisibility(View.GONE);
                    Intent intent = new Intent(getActivity(), ActivityLogin.class);
                    startActivity(intent);
                }
            },3000);
        }
    }
}