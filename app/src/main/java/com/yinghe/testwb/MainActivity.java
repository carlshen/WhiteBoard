package com.yinghe.testwb;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.yinghe.whiteboardlib.fragment.WhiteBoardFragment;

import java.util.ArrayList;

/**
 * Created by carl on 19-2-22.
 */
public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE = 2;
    private ArrayList<String> mSelectPath;
    private WhiteBoardFragment whiteBoardFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermissions();

        FragmentTransaction ts = getSupportFragmentManager().beginTransaction();
        whiteBoardFragment = WhiteBoardFragment.newInstance();
        ts.add(R.id.fl_main, whiteBoardFragment, "wb").commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
//        whiteBoardFragment.setCurBackgroundByPath("/storage/emulated/0/YingHe/sketchPhoto/2016-06-21_035725.png");
//        whiteBoardFragment.setNewBackgroundByPath("/storage/emulated/0/YingHe/sketchPhoto/2016-06-21_035725.png");
//        whiteBoardFragment.setNewBackgroundByPath("/storage/emulated/0/YingHe/sketchPhoto/2016-06-21_041513.png");
//         File f= whiteBoardFragment.saveInOI(WhiteBoardFragment.FILE_PATH, "ss");
//
//        whiteBoardFragment.addPhotoByPath(f.toString());
//        whiteBoardFragment.setCurBackgroundByPath("/storage/emulated/0/YingHe/sketchPhoto/2016-06-21_04151  3.png");
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    private void checkPermissions() {
        boolean cameraEnable = PermissionUtils.permissionChecking(this, Manifest.permission.CAMERA);
        boolean storageWriteEnable = PermissionUtils.permissionChecking(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        boolean recordAudio = PermissionUtils.permissionChecking(this, Manifest.permission.RECORD_AUDIO);
        if (!cameraEnable || !storageWriteEnable || !recordAudio) {
            ActivityCompat.requestPermissions(this,
                    new String[] {
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.RECORD_AUDIO
                    }, PermissionUtils.REQUEST_CAMERA_PERMISSION);
        }
    }

}
