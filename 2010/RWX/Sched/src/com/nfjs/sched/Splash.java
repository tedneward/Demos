package com.nfjs.sched;

import android.app.*;
import android.content.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import java.util.*;

/**
 * Activity to show a splash screen before automatically moving
 * on to the next Activity (the Main activity).
 *
 * Created by Ted at 9:31:27 PM on Nov 24, 2010
 */
public class Splash extends Activity
{
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new CountDownTimer(2 * 1000, 1000)
        {
            public void onTick(long millisUntilFinished)
            {
            }

            public void onFinish()
            {
                startActivity(new Intent(Splash.this, Main.class));
                finish();
            }
        }.start();

        /*
        String apkFileName = "/data/app/" + this.getClass().getPackage().getName() + ".apk";
		File appFile = new File(apkFileName);
		Log.i("Splash", "Date/time on our .apk is " + new Date(appFile.lastModified()));
		*/
    }
}
