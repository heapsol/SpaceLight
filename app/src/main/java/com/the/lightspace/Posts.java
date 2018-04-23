package com.the.lightspace;

import android.os.Parcel;
import android.os.Parcelable;

public class Posts implements Parcelable{
	
	private String img;
	private String name;
	private String title;
	private String content;
	private String post_time;
	private String video_link;
	
	
	public Posts(){
		
	}
	public Posts(Parcel source){
		img = source.readString();
		name = source.readString();
		title = source.readString();
		content = source.readString();
		video_link = source.readString();
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPost_time() {
		return post_time;
	}
	public void setPost_time(String post_time) {
		this.post_time = post_time;
	}
	public String getVideo_link() {
		return video_link;
	}
	public void setVideo_link(String video_link) {
		this.video_link = video_link;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(img);
		dest.writeString(name);
		dest.writeString(title);
		dest.writeString(content);
		dest.writeString(video_link);
	} 
	public static final Parcelable.Creator<Posts> CREATOR
	= new Parcelable.Creator<Posts>() {
	    public Posts createFromParcel(Parcel in) {
	        return new Posts(in);
	    }

	    public Posts[] newArray(int size) {
	        return new Posts[size];
	    }
	};

}
