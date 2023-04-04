package pti.sb_kesmarki_mvc.controller;

import javax.xml.crypto.Data;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import pti.sb_kesmarki_mvc.db.Database;
import pti.sb_kesmarki_mvc.model.Address;
import pti.sb_kesmarki_mvc.model.User;

@Controller
public class AppController {
	
	
	@GetMapping("/user/{uid}")
	public String getUser(
				Model model,
				@PathVariable("uid") int userId
			) {
		
		Database db = new Database();
		User user = db.getUserById(userId);
		db.closeDb();
		
		
		model.addAttribute("user", user);
		
		return "user.html";
	}
	
	@GetMapping("/address/{addressid}")
	public String getAddress(
			Model model,
			@PathVariable("addressid") int addressId
			) {
		Database db = new Database();
		Address address = db.getAddressById(addressId);
		db.closeDb();
		
		model.addAttribute("address", address);
		
		return "address.html";
	}	
	
	@GetMapping("/userupdate/{uid}")
	public String updateUser(
				Model model,
				@PathVariable("uid") int uid,
				@RequestParam(name = "name") String newName
			) {
		
		Database db = new Database();
		
		User user = db.getUserById(uid);
		user.setName(newName);
		
		db.updateUser(user);
		
		db.closeDb();		
		
		model.addAttribute("user", user);
		
		return "user.html";
	}
	
	@GetMapping("/addressupdate/{aid}")
	public String updateAddress(
			Model model,
			@PathVariable("aid") int aid,
			@RequestParam(name = "address") String newAddress
			) {
		Database db = new Database();
		Address address = db.getAddressById(aid);
		address.setAddress(newAddress);
		db.updateAddress(address);
		db.closeDb();
		
		model.addAttribute("address", address);
		
		return "address.html";
	}
	
	@GetMapping("/useradd")
	public String addUser(
				Model model,
				@RequestParam(name = "name") String userName
			) {
		
		User user = new User();
		user.setName(userName);
		
		Database db = new Database();
		db.addUser(user);
		db.closeDb();		
		
		model.addAttribute("user", user);
		
		return "user.html";
	}
	
	@GetMapping("/addressadd/{uid}")
	public String addAddress(
			Model model,
			@PathVariable("uid") int uId,
			@RequestParam(name = "address") String addressName
			) {
		Address address = new Address();
		address.setUserId(uId);
		address.setAddress(addressName);
		Database db = new Database();
		db.addAddress(address);
		db.closeDb();
		model.addAttribute("address", address);
		return "address.html";
	}
	
	@GetMapping("/userdelete/{uid}")
	public String deleteUser(
				Model model,
				@PathVariable("uid") int userId
			) {
		
		Database db = new Database();
		
		User user = db.getUserById(userId);
		db.deleteUser(user);
		
		db.closeDb();		
		
		model.addAttribute("user", user);
		
		return "user.html";
	}
	
	@GetMapping("/addressdelete/{aid}")
	public String deleteAddress(
			Model model,
			@PathVariable("aid") int aId
			) {
		Database db = new Database();
		
		Address address = db.getAddressById(aId);
		db.deleteAddress(address);
		
		db.closeDb();
		
		model.addAttribute("address", address);
		return "address.html";
	}

}
























