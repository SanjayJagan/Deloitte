package com.smart.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TASK")
public class Contact {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cId;

	private String title;
	@Column(length=10000)
	private String content;
	
	
	@ManyToOne
	private User user;
	
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	/*
	 * @Override public String toString() { return "Contact [cId=" + cId +
	 * ", title=" + title + ", content=" + content + ", user=" + user + "]"; }
	 */
	@Override
	public boolean equals(Object obj) {
		
		return this.cId==((Contact)obj).getcId();
	}
	
}
