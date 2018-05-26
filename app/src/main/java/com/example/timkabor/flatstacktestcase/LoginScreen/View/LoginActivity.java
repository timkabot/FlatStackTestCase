package com.example.timkabor.flatstacktestcase.LoginScreen.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.timkabor.flatstacktestcase.LoginScreen.Presenter.LoginActivityPresenter;
import com.example.timkabor.flatstacktestcase.R;
import com.vk.sdk.util.VKUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.btn_login) Button loginButton;
    private LoginActivityPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginActivityPresenter(this);
        presenter.setLoginBehaviour();
    }

    public Button getLoginButton() {
        return loginButton;
    }
}
