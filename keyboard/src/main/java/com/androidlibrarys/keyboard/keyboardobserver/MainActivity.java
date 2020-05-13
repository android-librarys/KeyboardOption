package com.androidlibrarys.keyboard.keyboardobserver;

import android.os.Bundle;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

//import androidx.appcompat.app.AppCompatActivity;
import android.support.v7.app.AppCompatActivity;

/**
 * 使用方法
 * */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // KeyboardVisibilityObserver.getInstance().init(context); 首先要再application中添加这个
        // 再在每一要用的activity中  onCreate和onDestroy 中添加监听  和重写 onEventMainThread
        EventBus.getDefault().register(this);
//        setContentView(R.layout.activity_main);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(KeyboardVisibleEvent event){
        Toast.makeText(this, event.isVisible ? "键盘弹起" : "键盘收起", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
