package lam.fpoly.myrelaxstar.Service;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.TextView;

import androidx.annotation.Nullable;

import lam.fpoly.myrelaxstar.Class.LoginPresenter;
import lam.fpoly.myrelaxstar.Fragment.Diff_Fragment.Account_Fragment;
import lam.fpoly.myrelaxstar.MyDataBase.MyDataBase3;
import lam.fpoly.myrelaxstar.Object.InputUser;
import lam.fpoly.myrelaxstar.Object.User;

public class DangNhap extends Service {

    public DangNhap() {
    }
    IBinder iBinder = new LocalBinder();
    public class LocalBinder extends Binder {

        DangNhap.LocalBinder getLocalBinder(){
            return DangNhap.LocalBinder.this; // phương thức khởi tạo khi client gọi tới các phương thức của service

        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public User user;
    public InputUser inputUser;
    public void dangnhapUser(TextView username, TextView userpass, LoginPresenter loginPresenter, Context context){
        String name = username.getText().toString().trim();
        String pass = userpass.getText().toString().trim();
        user = new User(name,pass,null);
        inputUser = new InputUser(name,pass,null);
        loginPresenter.login(user,context);
    }

    public void dangnhapFail(TextView username, TextView userpass, Context context){
        String name = username.getText().toString().trim();
        String pass = userpass.getText().toString().trim();
        if (name.isEmpty() || pass.isEmpty()) {
            AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
            builder.setTitle("Thông báo!");
            builder.setMessage("Không được để trống");
            builder.setPositiveButton("OK", null);
            builder.show();
        }else if(name.length() < 6 && pass.length() < 6){
            AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
            builder.setTitle("Thông báo!");
            builder.setMessage("Tài khoản và mật khẩu phải từ 6 ký tự trở lên");
            builder.setPositiveButton("OK", null);
            builder.show();
        } else{
            android.app.AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Thông báo!");
            builder.setMessage("Sai tài khoản hoặc mật khẩu");
            builder.setPositiveButton("OK",null);
            builder.show();
        }
    }
    public void dangnhapThanhCong(Context context){
        MyDataBase3.getInstance(context).inputUser_dao().insertData(inputUser);
    }
}
