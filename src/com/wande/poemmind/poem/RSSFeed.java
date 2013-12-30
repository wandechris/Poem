package com.wande.poemmind.poem;

import java.util.List;

/**
 * This class handle rss xml 
 * */
public class RSSFeed {
	// xml nodes
	String _title;
	String _description;
	String _link;
	String _rss_link;
	String _language;
	String _content;
	String _author;
	List<RSSItem> _items;

	// constructor
	public RSSFeed(String title, String description, String link, String rss_link, String language, String content, String author) {
		this._title = title;
		this._description = description;
		this._link = link;
		this._rss_link = rss_link;
		this._language = language;
		this._content = content;
		this._author = author;
	}

	/**
	 * All set methods
	 * */
	public void setItems(List<RSSItem> items) {
		this._items = items;
	}

	/**
	 * All get methods
	 * */
	public List<RSSItem> getItems() {
		return this._items;
	}

	public String getTitle() {
		return this._title;
	}

	public String getDescription() {
		return this._description;
	}

	public String getLink() {
		return this._link;
	}

	public String getRSSLink() {
		return this._rss_link;
	}

	public String getLanguage() {
		return this._language;
	}

	public String getContent() {
		return this._content;
	}
	
	public String getAuthor() {
		return this._author;
	}

	
}
