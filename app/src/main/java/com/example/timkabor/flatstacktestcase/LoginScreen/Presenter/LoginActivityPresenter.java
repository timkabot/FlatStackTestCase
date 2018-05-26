package com.example.timkabor.flatstacktestcase.LoginScreen.Presenter;

import android.content.Intent;
import android.view.View;

import com.example.timkabor.flatstacktestcase.LoginScreen.View.LoginActivity;
import com.example.timkabor.flatstacktestcase.LoginScreen.View.VkLogin;

import butterknife.ButterKnife;

public class LoginActivityPresenter implements ILoginActivityPresenter{

    private LoginActivity activity;

    public LoginActivityPresenter(LoginActivity activity){
        this.activity = activity;
        ButterKnife.bind(activity);

    }
    public void setLoginBehaviour()
    {
        activity.getLoginButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, VkLogin.class);
                activity.startActivity(intent);
            }
        });
    }

}
