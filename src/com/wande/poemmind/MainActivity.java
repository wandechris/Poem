package com.wande.poemmind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.haarman.listviewanimations.swinginadapters.AnimationAdapter;
import com.haarman.listviewanimations.swinginadapters.prepared.ScaleInAnimationAdapter;
import com.wande.poemmind.poem.RSSFeed;
import com.wande.poemmind.poem.RSSItem;
import com.wande.poemmind.read.RSSParser;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.Menu;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends ListActivity {
	
	// Progress Dialog
		private ProgressDialog pDialog;

		// Array list for list view
		ArrayList<HashMap<String, String>> rssItemList = new ArrayList<HashMap<String,String>>();

		RSSParser rssParser = new RSSParser();
		
		// button add new website
		ImageButton btnAddSite;
		
		List<RSSItem> rssItems = new ArrayList<RSSItem>();

		RSSFeed rssFeed;
		
		private static String TAG_TITLE = "title";
		private static String TAG_LINK = "link";
		private static String TAG_DESRIPTION = "description";
		private static String TAG_PUB_DATE = "pubDate";
		private static String TAG_CONTENT = "content:encoded";
		private static String TAG_AUTHOR = "dc:creator";
		//private static String TAG_GUID = "guid"; // not used
		
		BaseAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new loadRSSFeedItems().execute("http://mariechidi.wordpress.com/feed/");
	}
	
	private void setScaleAdapter() {
		AnimationAdapter animAdapter = new ScaleInAnimationAdapter(adapter);
		animAdapter.setAbsListView(getListView());
		getListView().setAdapter(animAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	/**
	 * Background Async Task to get RSS Feed Items data from URL
	 * */
	class loadRSSFeedItems extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(
					MainActivity.this);
			pDialog.setMessage("Loading recent articles...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting all recent articles and showing them in listview
		 * */
		@Override
		protected String doInBackground(String... args) {
			// rss link url
			String rss_url = args[0];
			// list of rss items
			rssItems = rssParser.getRSSFeedItems(rss_url);
			
			// looping through each item
			for(RSSItem item : rssItems){
				// creating new HashMap
				HashMap<String, String> map = new HashMap<String, String>();
				
				// adding each child node to HashMap key => value
				map.put(TAG_TITLE, item.getTitle());
				map.put(TAG_LINK, item.getLink());
				map.put(TAG_PUB_DATE, item.getPubdate()); // If you want parse the date
				String description = item.getDescription();
				Spanned g;
				g = Html.fromHtml(description);//description;
				map.put(TAG_DESRIPTION, g.toString());
				map.put(TAG_CONTENT, item.getContent());
				map.put(TAG_AUTHOR, item.getAuthor());

				// adding HashList to ArrayList
				rssItemList.add(map);
			}
			
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					/**
					 * Updating parsed items into listview
					 * */
					 adapter = new SimpleAdapter(
							MainActivity.this,
							rssItemList, R.layout.rss_item_list_row,
							new String[] { TAG_LINK, TAG_TITLE, TAG_PUB_DATE, TAG_DESRIPTION, TAG_AUTHOR },
							new int[] { R.id.page_url, R.id.title, R.id.pub_date, R.id.link, R.id.page_url });
					 setScaleAdapter();
				}
			});
			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String args) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
		}
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent in = new Intent(getApplicationContext(), ArticleActivity.class);
        in.putExtra("title", rssItemList.get(position).get(TAG_TITLE));
        in.putExtra("time", rssItemList.get(position).get(TAG_PUB_DATE));
        in.putExtra("desc", rssItemList.get(position).get(TAG_CONTENT));
        startActivity(in);
	}
	
	
}
