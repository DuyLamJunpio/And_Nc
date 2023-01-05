package lam.fpoly.myrelaxstar.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lam.fpoly.myrelaxstar.Class.LoginPresenter;
import lam.fpoly.myrelaxstar.Fragment.Diff_Fragment.Account_Fragment;
import lam.fpoly.myrelaxstar.Interface.Login_Interface;
import lam.fpoly.myrelaxstar.MyDataBase.MyDataBase;
import lam.fpoly.myrelaxstar.MyDataBase.MyDataBase1;
import lam.fpoly.myrelaxstar.MyDataBase.MyDataBase2;
import lam.fpoly.myrelaxstar.MyDataBase.MyDataBase3;
import lam.fpoly.myrelaxstar.Object.User;
import lam.fpoly.myrelaxstar.R;
import lam.fpoly.myrelaxstar.Service.DangKy;
import lam.fpoly.myrelaxstar.Service.DangNhap;

public class ActivityLogin extends AppCompatActivity implements Login_Interface, View.OnClickListener{
    private TextView idSign_Up;
    private TextView idLogin;
    private EditText UserName,UserPass;
    private DangNhap dangNhap;
    private ImageView showPassWord,idBackMain;
    private ProgressBar idProgressBar;
    private boolean check = false;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        idLogin = findViewById(R.id.idLogin);
        idSign_Up = findViewById(R.id.idSign_Up);
        UserName = findViewById(R.id.UserName);
        UserPass = findViewById(R.id.UserPass);
        showPassWord = findViewById(R.id.showPassWord);
        idProgressBar = findViewById(R.id.idProgressBar);
        idBackMain = findViewById(R.id.idBackMain);
        dangNhap = new DangNhap();
        Intent intent = new Intent(this, DangNhap.class);
        bindService(intent,sv_conn, Context.BIND_AUTO_CREATE);

        loginPresenter = new LoginPresenter(this);

        idLogin.setOnClickListener(this);

        idSign_Up.setOnClickListener(this);

        idBackMain.setOnClickListener(this);

        showPassWord.setOnClickListener(this);
    }

    private void clickLogin() {
        idProgressBar.setVisibility(View.VISIBLE);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                idProgressBar.setVisibility(View.GONE);
                dangNhap.dangnhapUser(UserName,UserPass,loginPresenter,ActivityLogin.this);
            }
        },3000);
    }

    ServiceConnection sv_conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            DangNhap.LocalBinder localBinder = (DangNhap.LocalBinder) iBinder;
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    public void loginSuccess() {
        Account_Fragment.checkLogin = true;
        dangNhap.dangnhapThanhCong(ActivityLogin.this);
       Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
       startActivity(intent);
    }

    @Override
    public void loginError() {
        dangNhap.dangnhapFail(UserName,UserPass,ActivityLogin.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.idLogin:
                clickLogin();
                break;
            case R.id.idSign_Up:
                Intent intent = new Intent(getApplicationContext(), SignUp_Activity.class);
                startActivity(intent);
                break;
            case R.id.idBackMain:
                Intent intent1 = new Intent(ActivityLogin.this,MainActivity.class);
                startActivity(intent1);
                break;
            case R.id.showPassWord:
                if (check == false) {
                    showPassWord.setImageResource(R.drawable.view);
                    UserPass.setTransformationMethod(new HideReturnsTransformationMethod());
                    check = true;
                }else{
                    showPassWord.setImageResource(R.drawable.invisible);
                    UserPass.setTransformationMethod(new PasswordTransformationMethod());
                    check = false;
                }
                break;
        }
    }
}