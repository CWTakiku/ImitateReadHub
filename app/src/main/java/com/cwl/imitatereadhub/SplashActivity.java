package com.cwl.imitatereadhub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//	  我们需要判断:该意图是打开一个新的任务,还是将后台的应用给提到前台来.
//	  若是要将应用提到前台来直接将这个Activity结束掉,然后显示出来的Activity就是之前被最小化的Activity.
//	  因为点击图标的意图会将新启动的Activity置于顶端,而顶端的下面的Activity就是之前被最小化的Activity.
//	  此时结束掉新启动的Activity,就可以让之前被最小化的Activity 显示出来了.
 if ((getIntent().getFlags()&Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT)!=0){
     finish();
     return;
 }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        },2000);
    }
}
