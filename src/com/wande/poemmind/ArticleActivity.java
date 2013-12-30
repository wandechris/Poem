package com.wande.poemmind;

import com.wande.poemmind.managers.ConnectionDetector;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ArticleActivity extends Activity{
	Intent i;
	TextView name;
	TextView artist;
	EditText lyric;
	ImageView img;
	
	//AlertDialogManager alert = new AlertDialogManager();

	Boolean isInternetPresent = false;
	
	ConnectionDetector cd;
	
	ProgressDialog pDialog;
	
	String tName;
	String tArtist;
	String image;
	String phone;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.track_details);
		
		Intent i = ArticleActivity.this.getIntent();
		
		name = (TextView)findViewById(R.id.list_name);
		artist = (TextView)findViewById(R.id.list_vic);
		lyric = (EditText)findViewById(R.id.lyricview);
		
		
		tName = i.getExtras().getString("title");
		tArtist = i.getExtras().getString("time");
		phone = i.getExtras().getString("desc");
		
		name.setText(tName);
		artist.setText(tArtist);
		lyric.setText(Html.fromHtml(phone));
		
	}
	
	
	
	
@Override
public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {

    case android.R.id.home:
         finish();
         break;

    default:
        return super.onOptionsItemSelected(item);
    }
    return false;
}

}
