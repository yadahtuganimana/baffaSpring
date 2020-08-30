package com.securitylast.implementation;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.securitylast.domain.Clients;
import com.securitylast.domain.FarmerDetails;
import com.securitylast.domain.Location;
import com.securitylast.domain.LocationType;
import com.securitylast.domain.Photos;
import com.securitylast.domain.Selling;
import com.securitylast.domain.User;
import com.securitylast.domain.UserEmails;
import com.securitylast.interfaces.MyPrincipalInt;
import com.securitylast.interfaces.UserInt;
import com.securitylast.repository.ClientRepository;
import com.securitylast.repository.FarmerDetailsRepository;
import com.securitylast.repository.LocationRepository;
import com.securitylast.repository.PhotoRepository;
import com.securitylast.repository.SellingRepository;
import com.securitylast.repository.UserRepository;
import com.securitylast.repository.UseremailsRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserImplimentation implements UserInt, MyPrincipalInt {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UseremailsRepo userRep;
	@Autowired
	PhotoRepository photorep;
	@Autowired
	FarmerDetailsRepository farmerRep;
	@Autowired
	ClientRepository clientrepo;
	@Autowired
	SellingRepository sellrepo;
	@Autowired
	LocationRepository locrepo;
	@Override
	public User saveUSer(User user) {

		return userRepository.save(user);
	}

	@Override
	public User findbyname(String user) {

		return userRepository.findByUsername(user);
	}

	@Override
	public UserEmails findbynamee(String user) {

		return userRep.findByUsername(user);
	}

	@Override
	public UserEmails saveUSerEmils(UserEmails user) {

		return userRep.save(user);
	}

	@Override
	public Iterable<User> findAllUSers() {
		String admin = "ADMIN";
		String visitor = "VISITOR";
		String farmer = "FARMER";
		return userRepository.findByNewuserEqualsAndRolesEqualsOrRolesEqualsOrRolesEquals("New", farmer, admin,
				visitor);
		// return userRepository.findAll();
	}

	@Override
	public void deleteUser(String users) {

		userRepository.deleteById(users);

	}

	@Override
	public void findbystatus(int user) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findbyid(String user) {

		return userRepository.findByIdnum(user);
	}

	@Override
	public void saveImages(MultipartFile images, Photos photo) throws Exception {
		Path currentpath = Paths.get(".");
		Path absolutepath = currentpath.toAbsolutePath();
		String here = absolutepath + "/src/main/resources/static/images/";

		byte[] bytes = images.getBytes();

		Path path = Paths.get(here + File.separator + StringUtils.cleanPath(images.getOriginalFilename()));
		Files.write(path, bytes);

		photorep.save(photo);
	}

	@Override
	public Iterable<Photos> findAllAmakuru() {

		return photorep.findAll();
	}

	@Override
	public String getName() {
//		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//	     String currentUser = auth.getPrincipal().toString(); 
//		User usr = new User();
//		usr.setUserfullnames(currentUser);
		return "";

	}

	@Override
	public User findbyFullnames(String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FarmerDetails saveFarmer(FarmerDetails farmer) {

		return farmerRep.save(farmer);
	}

	@Value("${upload.path}")
	private String url;

	@Override
	public void saveImagesz(MultipartFile images, String url, String filename, MultipartFile imagess,
			FarmerDetails photo) throws Exception {
		InputStream is = imagess.getInputStream();
		Path currentpath = Paths.get(".");
		Path absolutepath = currentpath.toAbsolutePath();
		String here = absolutepath + "/src/main/resources/static/images/farmerimages";
		String heree = absolutepath + "/src/main/resources/static/images/farmerimages/profile";
		byte[] bytes = images.getBytes();

		Path path = Paths.get(here + File.separator + StringUtils.cleanPath(images.getOriginalFilename()));
		String format = imagess.getContentType();
		String extension = (format.equalsIgnoreCase("image/jpeg") ? ".jpg"
				: (format.equalsIgnoreCase("image/png")) ? ".png"
						:  "");
		String newName = UUID.randomUUID().toString() + extension;
		Files.copy(is, Paths.get(url + filename), StandardCopyOption.REPLACE_EXISTING);
		// Files.copy(imagess.getInputStream(), copyLocation,
		// StandardCopyOption.REPLACE_EXISTING);
		farmerRep.save(photo);

	}

	@Override
	public Iterable<FarmerDetails> findAbashumbaDetails() {

		return farmerRep.findAll();
	}

	@Override
	public Iterable<User> findAllUSersRolesFarmer() {
		String farmer = "farmerr";
		// String fam = "FARMER";
		return userRepository.findByRolesEquals(farmer);
	}

	@Override
	public void deleteFarmer(String farmer) {
		farmerRep.deleteById(farmer);

	}

	@Override
	public FarmerDetails findFarmerbyid(String farmer) {
		// TODO Auto-generated method stub
		return farmerRep.findByIdnum(farmer);
	}

	@Override
	public Clients saveClient(Clients client) {
		// TODO Auto-generated method stub
		return clientrepo.save(client);
	}

	@Override
	public Selling saveSelling(Selling sell) {
		// TODO Auto-generated method stub
		return sellrepo.save(sell);
	}

	@Override
	public Clients findClientbyid(String user) {
		// TODO Auto-generated method stub
		return clientrepo.findByIdnum(user);
	}

	@Override
	public Selling findbySellingid(String user) {
		// TODO Auto-generated method stub
		return sellrepo.findByIdnum(user);
	}

	@Override
	public Iterable<Clients> findAllClients() {

		return clientrepo.findAll();
	}

	@Override
	public Iterable<Selling> findAllSelling() {

		return sellrepo.findAll();
	}

	@Override
	public Clients clientFindByContact(String contact) {
		// TODO Auto-generated method stub
		return clientrepo.findByContact(contact);
	}

	@Override
	public Clients findClientsByFullnames(String fullname) {

		return clientrepo.findByFullname(fullname);
	}

	@Override
	public void deleteClient(String users) {
		clientrepo.deleteById(users);

	}

	@Override
	public void deleteSelling(String users) {
		// TODO Auto-generated method stub
		sellrepo.deleteById(users);
	}

	@Override
	public void deletePhotos(String photo) {
		// TODO Auto-generated method stub
		photorep.deleteById(photo);
	}

	@Override
	public Photos findbyPhotos(String user) {
		// TODO Auto-generated method stub
		return photorep.findByPhotoId(user);
	}

	@Override
	public Iterable<FarmerDetails> findAllFarmerbyid(String id) {

		return farmerRep.findAllByIdnum(id);
	}

	@Override
	public Iterable<Selling> findRecorderonBetweenImp(Date Starting,Date  ending) {
		
		return sellrepo.findByRecorderonInBetwwen(Starting, ending);
	}

	@Override
	public Iterable<Location> findAllLocation() {
		
		return locrepo.findAll();
	}

	@Override
	public Location saveLocation(Location location) {
		
		return locrepo.save(location);
	}

	@Override
	public Location findbyidloc(String id) {
		// TODO Auto-generated method stub
		return locrepo.findByLocname(id);
	}

	@Override
	public List<Location> findbyLocType(LocationType loc) {
		return locrepo.findByLocationtype(loc);
	}

	

//	@Override
//	public void updateUser(String users) {
//		userRepository.updateRoles(users);
//		
//	}

}
