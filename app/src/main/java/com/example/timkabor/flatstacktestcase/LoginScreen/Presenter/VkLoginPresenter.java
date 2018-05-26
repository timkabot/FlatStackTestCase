package com.example.timkabor.flatstacktestcase.LoginScreen.Presenter;

import android.content.Intent;
import android.widget.Toast;

import com.example.timkabor.flatstacktestcase.LoginScreen.View.VkLogin;
import com.example.timkabor.flatstacktestcase.NewsScreen.View.NewsActivity;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;

import static com.vk.sdk.VKUIHelper.getApplicationContext;

public class VkLoginPresenter implements IVkLoginPresenter {

        private VkLogin activity;
        private String[] scope = new String[]{VKScope.WALL, VKScope.PAGES, VKScope.FRIENDS};
        public VkLoginPresenter(VkLogin activity) {
            this.activity = activity;
        }
        public void showError() {
            Toast.makeText(getApplicationContext(), "Error authorizing", Toast.LENGTH_LONG).show();
        }
        public void changeScreen(){
            Intent new_screen = new Intent(activity, NewsActivity.class);
            activity.startActivity(new_screen);
        }
        public void vkLogin() {
            VKSdk.login(activity, scope);
        }
}
