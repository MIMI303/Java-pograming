package com.iktpreobuka.project.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.project.entities.UserEntity;

@RestController
@RequestMapping(value = "/project/users")
public class UserControllers {

	List<UserEntity> users = new ArrayList<>();

	private List<UserEntity> getDB() {
		if (users.size() == 0) {
			UserEntity u1 = new UserEntity(1, "Vladimir", "Dimitrieski", "dimitrieski@uns.ac.rs", "vladimir",
					"vladimir", Roles.ROLE_CUSTOMER);
			UserEntity u2 = new UserEntity(2, "Milan", "Celikovic", "milancel@uns.ac.rs", "milan", "milan",
					Roles.ROLE_CUSTOMER);
			UserEntity u3 = new UserEntity(3, "Nebojsa", "Horvat", "horva.n@uns.ac.rs", "nebojsa", "nebojsa",
					Roles.ROLE_CUSTOMER);
			users.add(u1);
			users.add(u2);
			users.add(u3);
		}
		return users;
	}

	// TODO 1.3 kreirati REST endpoint koji vraća listu korisnik aplikacije
	// • putanja /project/users

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<UserEntity> getAll() {
		return getDB();
	}

	// TODO 1.4 kreirati REST endpoint koji vraća korisnika po vrednosti prosleđenog
	// ID-a
	// • putanja /project/users/{id}
	// • u slučaju da ne postoji korisnik sa traženom vrednošću ID-a vratiti null

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public UserEntity GetOne(@PathVariable Integer id) {
		for (UserEntity ue : getDB()) {
			if (ue.getId().equals(id))
				return ue;
		}
		return null;
	}

	// TODO • 1.5 kreirati REST endpoint koji omogućava dodavanj novog korisnika
	// • putanja /project/users
	// • u okviru ove metode postavi vrednost atributa user role na ROLE_CUSTOMER
	// • metoda treba da vrati dodatog korisnika

	@RequestMapping(method = RequestMethod.POST, value = "/")
	public UserEntity createNewUser(@RequestBody UserEntity ue) {
		List<UserEntity> users = getDB();
		ue.setId((new Random()).nextInt());
		ue.setEUserRole(Roles.ROLE_CUSTOMER);
		users.add(ue);
		return ue;
	}

	// TODO 1.6 kreirati REST endpoint koji omogućava izmenu postojećeg korisnika
	// • putanja /project/users/{id}
	// • ukoliko je prosleđen ID koji ne pripada nijednom korisniku metoda
	// treba da vrati null, a u suprotnom vraća podatke korisnika izmenjenim
	// vrednostima
	// • NAPOMENA: u okviru ove metode ne menjati vrednost atributa user role i
	// password

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public UserEntity changeUser(@PathVariable Integer id, @RequestBody UserEntity changedUE) {
		List<UserEntity> users = getDB();
		for (UserEntity ue : users) {
			if (ue.getId().equals(id)) {
				if (changedUE.getEmail() != null)
					ue.setEmail(changedUE.getEmail());
				if (changedUE.getFirstName() != null)
					ue.setFirstName(changedUE.getFirstName());
				if (changedUE.getLastName() != null)
					ue.setLastName(changedUE.getLastName());
				if (changedUE.getUserName() != null)
					ue.setUserName(changedUE.getUserName());
				return ue;
			}
		}
		return null;
	}

	// TODO 1.7 kreirati REST endpoint koji omogućava izmenu atributa user_role
	// postojećeg korisnika
	// • putanja /project/users/change/{id}/role/{role}
	// • ukoliko je prosleđen ID koji ne pripada nijednom korisniku metoda
	// treba da vrati null, a u suprotnom vraća podatke korisnika sa
	// izmenjenom vrednošću atributa user role

	@RequestMapping(value = "change/{id}/role/{role}", method = RequestMethod.PUT)
	public UserEntity changeUserRole(@PathVariable Integer id, @PathVariable Roles role) {
		List<UserEntity> users = getDB();
		for (UserEntity ue : users) {
			if (ue.getId().equals(id))
				if (role != null)
					ue.setEUserRole(role);
			return ue;
		}
		return null;
	}

	// TODO 1.8 kreirati REST endpoint koji omogućava izmenu vrednosti atributa
	// password postojećeg korisnika
	// • putanja /project/users/changePassword/{id}
	// • kao RequestParam proslediti vrednosti stare i nove lozinke
	// • ukoliko je prosleđen ID koji ne pripada nijednom korisniku metoda
	// treba da vrati null, a u suprotnom vraća podatke korisnika sa
	// izmenjenom vrednošću atributa password
	// • NAPOMENA: da bi vrednost atributa password mogla da bude
	// zamenjena sa novom vrednošću, neophodno je da se vrednost
	// stare lozinke korisnika poklapa sa vrednošću stare lozinke prosleđene kao
	// RequestParam

	@RequestMapping(value = "changePassword/{id}", method = RequestMethod.PUT)
	public UserEntity changeUserPassword(@RequestParam String oldUserPass, @RequestParam String newUserPass,
			@PathVariable Integer id) {
		List<UserEntity> users = getDB();
		for (UserEntity ue : users) {
			if (ue.getId().equals(id))
				if (ue.getPassword().equals(oldUserPass))
					ue.setPassword(newUserPass);
			return ue;
		}
		return null;
	}

	// TODO 1.9 kreirati REST endpoint koji omogućava brisanje postojećeg korisnika
	// • putanja /project/users/{id}
	// • ukoliko je prosleđen ID koji ne pripada nijednom korisniku metoda
	// treba da vrati null, a u suprotnom vraća podatke o korisniku koji je obrisan

	@RequestMapping(value = "/{id}")
	public UserEntity deleteUser(@PathVariable Integer id) {
		List<UserEntity> users = getDB();
		Iterator<UserEntity> it = users.iterator();
		while (it.hasNext()) {
			UserEntity ue = it.next();
			if (ue.getId().equals(id)) {
				it.remove();
				return ue;
			}

		}
		return null;
	}
	
	//1.10 kreirati REST endpoint koji vraća korisnika po vrednosti prosleđenog username-a
	//• putanja /project/users/by-username/{username}
	//• u slučaju da ne postoji korisnik sa traženim username-om vratiti null
	
	@RequestMapping(value = "/by-username/{username}" , method = RequestMethod.GET)
	public UserEntity findUserByUsername (@PathVariable String username) {
		
		List<UserEntity> users = getDB();
		for (UserEntity ue : users) {
			if(ue.getUserName().equalsIgnoreCase(username))
				return ue;
		}
		return null;
	}
}
