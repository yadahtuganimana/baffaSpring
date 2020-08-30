package com.securitylast.domain;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Location implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Id
private String uuid=UUID.randomUUID().toString();
private String locname;
@Enumerated(EnumType.STRING)
private LocationType locationtype;
@ManyToOne
private Location location;
@OneToMany(mappedBy = "location",orphanRemoval = true,cascade = CascadeType.ALL)
private List<Location> list;
public String getUuid() {
	return uuid;
	//backk
}
public void setUuid(String uuid) {
	this.uuid = uuid;
}
public String getLocname() {
	return locname;
}
public void setLocname(String locname) {
	this.locname = locname;
}
public LocationType getLocationtype() {
	return locationtype;
}
public void setLocationtype(LocationType locationtype) {
	this.locationtype = locationtype;
}
public Location getLocation() {
	return location;
}
public void setLocation(Location location) {
	this.location = location;
}
public List<Location> getList() {
	return list;
}
public void setList(List<Location> list) {
	this.list = list;
}


}
