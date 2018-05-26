package com.example.timkabor.flatstacktestcase.LoginScreen.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.timkabor.flatstacktestcase.LoginScreen.Presenter.VkLoginPresenter;
import com.example.timkabor.flatstacktestcase.R;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

public class VkLogin extends Activity {
    private VkLoginPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vk_layout);
        presenter = new VkLoginPresenter(this);
        presenter.vkLogin();
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                presenter.changeScreen();
            }
            @Override
            public void onError(VKError error) {
                presenter.showError();
                }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
