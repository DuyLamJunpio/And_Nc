package lam.fpoly.myrelaxstar.Class;

import android.content.Context;

import lam.fpoly.myrelaxstar.Interface.Login_Interface;
import lam.fpoly.myrelaxstar.Object.User;

public class LoginPresenter {

    private Login_Interface login_interface;

    public LoginPresenter(Login_Interface login_interface) {
        this.login_interface = login_interface;
    }

    public void login(User user , Context context){
        if (user.isCheckUserName(context) && user.isCheckPassWord(context)){
            login_interface.loginSuccess();
        }else{
            login_interface.loginError();
        }
    }
}
