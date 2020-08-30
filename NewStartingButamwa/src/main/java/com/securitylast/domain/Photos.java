package com.securitylast.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;




@Entity
public class Photos {
	@Id
	String photoId = UUID.randomUUID().toString();
	String PhotFile;
	String imagetitle;
	@Column(name ="imagebodyz",  length = 100)
	String imagebody;
	String whodidaction;
    Date regdate=new Date();
	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	public String getPhotFile() {
		return PhotFile;
	}

	public void setPhotFile(String photFile) {
		PhotFile = photFile;
	}

	public String getImagetitle() {
		return imagetitle;
	}

	public void setImagetitle(String imagetitle) {
		this.imagetitle = imagetitle;
	}

	public String getImagebody() {
		return imagebody;
	}

	public void setImagebody(String imagebody) {
		this.imagebody = imagebody;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getWhodidaction() {
		return whodidaction;
	}

	public void setWhodidaction(String whodidaction) {
		this.whodidaction = whodidaction;
	}
	
}
