package com.nfjs.sched;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ted at 9:42:00 AM on Dec 3, 2010
 */
public class Main extends Activity
{
    private class ShowListAdapter extends BaseAdapter {
        public List<Show> shows;
        public List<ShowView> showViews;

        private List<ShowView> makeShowViews(Context context, List<Show> ss) {
            List<ShowView> results = new ArrayList<ShowView>(ss.size());
            for (Show s : ss)
                results.add(new ShowView(context, s));
            return results;
        }

        public ShowListAdapter(Context ctx, List<Show> shows) {
            context = ctx;
            this.shows = shows;
            showViews = makeShowViews(context, shows);
        }

        public int getCount() { return shows.size(); }
        public Object getItem(int position) { return shows.get(position); }
        public long getItemId(int position) { return position; }
        public View getView(int position, View convertView, ViewGroup parent) {
            return showViews.get(position);
        }
        private Context context;
    }

    /**
     * We will use a ShowListView to display each speech. It's just a LinearLayout
     * with two text fields.
     */
    private class ShowView extends LinearLayout {
        public ShowView(Context context, Show show) {
            super(context);

            this.setOrientation(VERTICAL);
            this.setPadding(2, 2, 7, 2);  // we need to accomodate the scrollbar on the right

            TextView tvTitle = new TextView(context);
            tvTitle.setText(show.name);
            tvTitle.setTextSize(16f);
            addView(tvTitle, new LinearLayout.LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

            RelativeLayout rl = new RelativeLayout(context);

            TextView tvDates = new TextView(context);
            tvDates.setText(show.dates);
            tvDates.setTextSize(13f);
            RelativeLayout.LayoutParams left =
                    new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            left.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            rl.addView(tvDates, left);

            TextView tvState = new TextView(context);
            tvState.setText(show.location.city + ", " + show.location.stateCode);
            tvState.setTextSize(13f);
            RelativeLayout.LayoutParams right =
                    new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            right.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            rl.addView(tvState, right);

            addView(rl);
        }
    }


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // send GET to http://www.nofluffjuststuff.com/m/data/show_list.json
        List<Show> shows = new ArrayList<Show>();
        try
        {
            URL url = new URL("http://www.nofluffjuststuff.com/m/data/show_list.json");
            URLConnection conn = url.openConnection();
            InputStream in = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String contents = br.readLine();
            Log.i("Main", "HTTP GET returns: " + contents);

            JSONArray jsonArray = new JSONArray(contents);
            Log.i("Main", "JSONArray holds: " + jsonArray.length() + " items");
            for (int i=0; i<jsonArray.length(); i++)
            {
                JSONObject obj = jsonArray.getJSONObject(i);
                Show s = new Show(obj.getString("name"),
                        obj.getJSONObject("location"),
                        obj.getString("shortDates"));
                shows.add(s);
            }
        }
        catch (Exception ex)
        {
            Log.e("Main", "URL failed", ex);
        }


        // Populate the list with the newly-fetched data
        final ListView lv = (ListView)findViewById(R.id.lstShows);
        lv.setAdapter(new ShowListAdapter(this, shows));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(Main.this, lv.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                Intent childIntent = new Intent(Main.this, ShowAct.class);
                childIntent.putExtra("show", (Serializable)lv.getItemAtPosition(position));
                startActivity(childIntent);
            }
        });
    }
}
