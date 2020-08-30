package com.securitylast.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.securitylast.domain.Clients;
import com.securitylast.domain.FarmerDetails;
import com.securitylast.domain.Location;
import com.securitylast.domain.LocationType;
import com.securitylast.domain.Photos;
import com.securitylast.domain.Selling;
import com.securitylast.domain.User;
import com.securitylast.domain.UserEmails;

public interface UserInt {
	void deleteUser(String users);

	void deleteClient(String users);

	void deleteSelling(String users);

	void deletePhotos(String photo);

	void deleteFarmer(String farmer);

//	void updateUser(String users);
	public Iterable<Selling> findRecorderonBetweenImp(Date Starting, Date ending);

	public FarmerDetails saveFarmer(FarmerDetails farmer);

	public User saveUSer(User user);

	public List<Location> findbyLocType(LocationType loc);

	public Location saveLocation(Location location);

	public Clients clientFindByContact(String contact);

	public Clients saveClient(Clients client);

	public Selling saveSelling(Selling sell);

	public void saveImages(MultipartFile images, Photos photo) throws Exception;

	public UserEmails saveUSerEmils(UserEmails user);

	public void saveImagesz(MultipartFile images, String url, String filename, MultipartFile imagess,
			FarmerDetails photo) throws Exception;

	public User findbyname(String user);

	public Clients findClientsByFullnames(String fullname);

	public User findbyid(String user);

	public Location findbyidloc(String id);

	public Photos findbyPhotos(String user);

	public Clients findClientbyid(String user);

	public Selling findbySellingid(String user);

	public FarmerDetails findFarmerbyid(String farmer);

	public void findbystatus(int user);

	Iterable<User> findAllUSers();

	Iterable<Location> findAllLocation();

	Iterable<Clients> findAllClients();

	Iterable<Selling> findAllSelling();

	Iterable<FarmerDetails> findAllFarmerbyid(String id);

	Iterable<User> findAllUSersRolesFarmer();

	Iterable<Photos> findAllAmakuru();

	Iterable<FarmerDetails> findAbashumbaDetails();

	public UserEmails findbynamee(String user);
}
