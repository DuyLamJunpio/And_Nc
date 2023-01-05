package lam.fpoly.myrelaxstar.Activity;

import androidx.appcompat.app.AppCompatActivity;

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
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import lam.fpoly.myrelaxstar.R;
import lam.fpoly.myrelaxstar.Service.DangKy;

public class SignUp_Activity extends AppCompatActivity {
private TextView idSign_In,idSignUpAcount;
private EditText UserNameSignUp,UserPassSignUp,RePassSignUp;
private lam.fpoly.myrelaxstar.Service.DangKy DangKy;
private ImageView showPass,showRePass;
private boolean checkPass = false;
private boolean checkRePass = false;
private ProgressBar idProgressBarSignUp;
private LinearLayout idLayout1,idLayout2,idLayout3;
private View idView1,idView2,idView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        idSign_In = findViewById(R.id.idSign_In);
        idSignUpAcount = findViewById(R.id.idSignUpAcount);
        UserNameSignUp = findViewById(R.id.UserNameSignUp);
        UserPassSignUp = findViewById(R.id.UserPassSignUp);
        RePassSignUp = findViewById(R.id.RePassSignUp);
        showPass = findViewById(R.id.showPass);
        showRePass = findViewById(R.id.showRePass);
        idProgressBarSignUp = findViewById(R.id.idProgressBarSignUp);
        idLayout1 = findViewById(R.id.idLayout1);
        idLayout2 = findViewById(R.id.idLayout2);
        idLayout3 = findViewById(R.id.idLayout3);
        idView1 = findViewById(R.id.idView1);
        idView2 = findViewById(R.id.idView2);
        idView3 = findViewById(R.id.idView3);

        showPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPass == false) {
                    showPass.setImageResource(R.drawable.view);
                    UserPassSignUp.setTransformationMethod(new HideReturnsTransformationMethod());
                    checkPass = true;
                }else{
                    showPass.setImageResource(R.drawable.invisible);
                    UserPassSignUp.setTransformationMethod(new PasswordTransformationMethod());
                    checkPass = false;
                }
            }
        });

        showRePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkRePass == false) {
                    showRePass.setImageResource(R.drawable.view);
                    RePassSignUp.setTransformationMethod(new HideReturnsTransformationMethod());
                    checkRePass = true;
                }else{
                    showRePass.setImageResource(R.drawable.invisible);
                    RePassSignUp.setTransformationMethod(new PasswordTransformationMethod());
                    checkRePass = false;
                }
            }
        });

        DangKy = new DangKy();
        Intent intent = new Intent(SignUp_Activity.this, DangKy.class);
        bindService(intent,sv_conn, Context.BIND_AUTO_CREATE);


        idSignUpAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKy.dangKyUser(UserNameSignUp,UserPassSignUp,RePassSignUp,
                        SignUp_Activity.this,idProgressBarSignUp
                        ,idSignUpAcount,idSign_In,SignUp_Activity.this,
                        idLayout1,idLayout2,idLayout3,idView1,idView2,idView3);
            }
        });

        idSign_In.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp_Activity.super.onBackPressed();
            }
        });
    }

    ServiceConnection sv_conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            DangKy.LocalBinder localBinder = (DangKy.LocalBinder) iBinder;
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}