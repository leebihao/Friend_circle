package com.lbh.friendcircledemo.domain;

public class UserData {

	public String userIconUrl;
	public String contentPhotoUrl;
	public String userName;
	public String contentText;
	public String comment;
	public String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUserIconUrl() {
		return userIconUrl;
	}

	public void setUserIconUrl(String userIconUrl) {
		this.userIconUrl = userIconUrl;
	}

	public String getContentPhotoUrl() {
		return contentPhotoUrl;
	}

	public void setContentPhotoUrl(String contentPhotoUrl) {
		this.contentPhotoUrl = contentPhotoUrl;
	}

	@Override
	public String toString() {
		return "UserData [userIconUrl=" + userIconUrl + ", contentPhotoUrl="
				+ contentPhotoUrl + ", userName=" + userName + ", contentText="
				+ contentText + ", comment=" + comment + ", time=" + time + "]";
	}

}
