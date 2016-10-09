package com.nfjs.sched;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;

public class Main extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ListView lv = (ListView)findViewById(R.id.lstShows);
        
        ArrayAdapter<Show> mockShows = 
            new ArrayAdapter<Show>(this, android.R.layout.simple_list_item_1);
        mockShows.add(new Show("Rich Web Experience", "Hollywood, FL", "Nov 30 - Dec 3"));
        mockShows.add(new Show("Project Automation Experience", "Hollywood, FL", "Nov 30 - Dec 3"));

        lv.setAdapter(mockShows);
        
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(Main.this, "Enjoy COBOL!", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                    case 2:
                        Toast.makeText(Main.this, "You suck!", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        throw new RuntimeException("Unrecognized position: " + position);
                }
            }
        });
    }
}




