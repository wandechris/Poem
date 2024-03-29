package com.wande.poemmind.poem;

/**
 * This class handle RSS Item <item> node in rss xml
 * */
public class RSSItem {
	
	// All <item> node name
	String _title;
	String _link;
	String _description;
	String _pubdate;
	String _guid;
	String _content;
	String _author;
	
	// constructor
	public RSSItem(){
		
	}
	
	// constructor with parameters
	public RSSItem(String title, String link, String description, String pubdate, String guid, String content, String author){
		this._title = title;
		this._link = link;
		this._description = description;
		this._pubdate = pubdate;
		this._guid = guid;
		this._content = content;
		this._author = author;
	}
	
	/**
	 * All SET methods
	 * */
	public void setTitle(String title){
		this._title = title;
	}
	
	public void setLink(String link){
		this._link = link;
	}
	
	public void setDescription(String description){
		this._description = description;
	}
	
	public void setPubdate(String pubDate){
		this._pubdate = pubDate;
	}
	
	
	public void setGuid(String guid){
		this._guid = guid;
	}
	
	public void setContent(String content){
		this._content = content;
	}
	
	public void setAuthor(String author){
		this._author = author;
	}
	
	/**
	 * All GET methods
	 * */
	public String getTitle(){
		return this._title;
	}
	
	public String getLink(){
		return this._link;
	}
	
	public String getDescription(){
		return this._description;
	}
	
	public String getPubdate(){
		return this._pubdate;
	}
	
	public String getGuid(){
		return this._guid;
	}
	
	public String getContent(){
		return this._content;
	}
	
	public String getAuthor(){
		return this._author;
	}
}
