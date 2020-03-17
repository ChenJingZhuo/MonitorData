package com.cjz.monitordata;

import androidx.appcompat.app.AppCompatActivity;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Uri uri= Uri.parse("content://com.cjz.contentobserverdb/info");
        getContentResolver().registerContentObserver(uri,true,new MyObserver(new Handler()));
    }
    private class MyObserver extends ContentObserver{
        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public MyObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            Log.d("监测数据变化", "有人动了你的数据库！");
            super.onChange(selfChange);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(new MyObserver(new Handler()));
    }
}
