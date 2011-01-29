package com.nfjs.sched;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

/**
 * Created by Ted at 12:31:21 PM on Dec 3, 2010
 */
public class ShowAct extends Activity
{
    private static int instanceCt = 0;
    public ShowAct()
    {
        instanceCt++;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);


        Intent launchingIntent = this.getIntent();
        Show show = (Show) launchingIntent.getSerializableExtra("show");

        Button btn = (Button) findViewById(R.id.btnHotel);
        String hotelText = show.location.name + "\n" +
                show.location.address1 + "\n" +
                (!show.location.address2.equals("null") ? (show.location.address2 + "\n") : "") +
                show.location.city + " " + show.location.stateCode;
        btn.setText(instanceCt + " " + hotelText);
    }
}
