package lam.fpoly.myrelaxstar.Service;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.List;

import lam.fpoly.myrelaxstar.Activity.ActivityLogin;
import lam.fpoly.myrelaxstar.Activity.SignUp_Activity;
import lam.fpoly.myrelaxstar.MyDataBase.MyDataBase1;
import lam.fpoly.myrelaxstar.Object.User;


public class DangKy extends Service {
    public DangKy() {
    }
    IBinder iBinder = new DangKy.LocalBinder();
    public class LocalBinder extends Binder {

        DangKy.LocalBinder getLocalBinder(){
            return DangKy.LocalBinder.this; // phương thức khởi tạo khi client gọi tới các phương thức của service

        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void dangKyUser(EditText UserNameSignUp, EditText UserPassSignUp,
                           EditText RePassSignUp, Context context, ProgressBar progressBar
            , TextView SignUp, TextView SignIn, Activity activity, LinearLayout layout1
            ,LinearLayout layout2,LinearLayout layout3,View view1,View view2,View view3){

        String name = UserNameSignUp.getText().toString().trim();
        String pass = UserPassSignUp.getText().toString().trim();
        String repass = RePassSignUp.getText().toString().trim();

        if(TextUtils.isEmpty(name) && TextUtils.isEmpty(pass) && TextUtils.isEmpty(repass)){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Thông báo!");
            builder.setMessage("Không được để trống");
            builder.setPositiveButton("OK",null);
            builder.show();
            return;
        }

        if(name.length() < 6 && pass.length() < 6){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Thông báo!");
            builder.setMessage("Tài khoản và mật khẩu phải từ 6 ký tự trở lên");
            builder.setPositiveButton("OK",null);
            builder.show();
            return;
        }

        if(!pass.equals(repass)){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Thông báo!");
            builder.setMessage("Mật khẩu không trùng khớp");
            builder.setPositiveButton("OK",null);
            builder.show();
            return;
        }

        User user = new User(name,pass,null);
        if(isDataExist(user,context)){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Thông báo!");
            builder.setMessage("Tài khoản đã tồn tại");
            builder.setPositiveButton("OK",null);
            builder.show();
            return;
        }


        MyDataBase1.getInstance(context).userDAO().insertDataUser(user);



        UserNameSignUp.setText("");
        UserPassSignUp.setText("");
        RePassSignUp.setText("");

        ObjectAnimator anim = ObjectAnimator.ofFloat(layout1,"translationX",0f,1500f);
        anim.setDuration(100);
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(view1,"translationX",0f,1500f);
        anim1.setDuration(500);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(layout2,"translationX",0f,1500f);
        anim2.setDuration(1000);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(view2,"translationX",0f,1500f);
        anim3.setDuration(1500);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(layout3,"translationX",0f,1500f);
        anim4.setDuration(2000);
        ObjectAnimator anim5 = ObjectAnimator.ofFloat(view3,"translationX",0f,1500f);
        anim5.setDuration(2500);
        AnimatorSet ans = new AnimatorSet();
        ans.play(anim5).after(anim4).after(anim3).after(anim2).after(anim1).after(anim);
        ans.start();
        SignIn.setVisibility(View.GONE);
        SignUp.setText("Successfully");
        SignUp.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                activity.finish();
            }
        },3000);
    }
    private boolean isDataExist(User user,Context context){
        List<User> list = MyDataBase1.getInstance(context).userDAO().checkDataUser(user.getName());
        return list != null && !list.isEmpty();
    }
}
