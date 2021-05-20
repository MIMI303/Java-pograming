package com.iktpreobuka.restexamples.controllers;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.restexamples.entities.BankClientBean;

@RestController
@RequestMapping("/bankclients")
public class BankClientRestController {

	// TODO GET - dobavi sve klijente banke - /bankclients/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<BankClientBean> getAll() {
		return getDB();
	}

	// TODO GET -dobavi jednog klijenta banke - /bankclients/{id}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public BankClientBean getOne(@PathVariable Integer id) {
		for (BankClientBean bcb : getDB()) {
			if (bcb.getId().equals(id))
				return bcb;
		}

		/*
		 * if(id.equals(1)) { return new BankClientBean(1, "Milan", "Celikovic",
		 * "milancel@uns.ac.rs"); } else if(id.equals(2)) { return new BankClientBean(2,
		 * "Vladimir", "Dimitrieski", "dimitrieski@uns.ac.rs"); } else
		 */
		return null;

	}

	List<BankClientBean> clients = new ArrayList<BankClientBean>();

	private List<BankClientBean> getDB() {
		if (clients.size() == 0) {
			clients.add(new BankClientBean(1,"Milan","Milicic", "milan@gmail.com", LocalDate.of(1988, 1, 23), "N", "Novi Sad"));
			clients.add(new BankClientBean(2, "Vladimir", "Dimitrieski", "dimitrieski@uns.ac.rs",LocalDate.of(1993, 2, 12), "R", "Novi Sad"));
			clients.add(new BankClientBean(3, "Djordje", "Dordjevic", "djoka@hotmail.com", LocalDate.of(1965, 10, 10), "P", "Beograd"));
		}
		return clients;
	}

	// TODO POST - napravi novog klijenta banke - /bankclients/

	@RequestMapping(method = RequestMethod.POST, value = "/")
	public BankClientBean createClient(@RequestBody BankClientBean bcb) {
		List<BankClientBean> clients = getDB();
		bcb.setId((new Random()).nextInt());
		clients.add(bcb);
		return bcb;
	}

	// TODO PUT - izmeni postojeceg klijenta - - /bankclients/{id}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public BankClientBean changeClient(@PathVariable Integer id, @RequestBody BankClientBean changedBcb) {
		List<BankClientBean> clients = getDB();
		for (BankClientBean bcb : clients) {
			if (bcb.getId().equals(id)) {
				if (changedBcb.getEmail() != null)
					bcb.setEmail(changedBcb.getEmail());
				if (changedBcb.getName() != null)
					bcb.setName(changedBcb.getName());
				if (changedBcb.getSurname() != null)
					bcb.setSurname(changedBcb.getSurname());
				return bcb;
			}
		}
		return null;
	}

	// TODO DELETE - obrisi klijenta banke - /bankclients/{id}
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public BankClientBean delClient(@PathVariable Integer id) {
		List<BankClientBean> clients = getDB();
		for (BankClientBean bcb : getDB()) {
			if (bcb.getId().equals(id)) {
				clients.remove(bcb);
				return bcb;
			}
		}
		return null;
	}

	// TODO DELETE - obrisi klijenta banke - /bankclients/b/{id}
	@RequestMapping(method = RequestMethod.DELETE, value = "/b/{id}")
	public BankClientBean deleteClient(@PathVariable Integer id) {
		List<BankClientBean> clients = getDB();
		Iterator<BankClientBean> it = clients.iterator();
		while (it.hasNext()) {
			BankClientBean bcb = it.next();
			if (bcb.getId().equals(id)) {
				it.remove();
				return bcb;
			}
		}
		return null;
	}

	// TODO GET = naci korisnike koji imaju zadato ime i prezime

	@RequestMapping(method = RequestMethod.GET, value = "/findByNameAndSurname")
	public List<BankClientBean> findByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
		List<BankClientBean> retClients = new ArrayList<BankClientBean>();
		List<BankClientBean> clients = getDB();
		for (BankClientBean bcb : clients) {
			if (bcb.getName().equalsIgnoreCase(name) && (bcb.getSurname().equalsIgnoreCase(surname))) {
				retClients.add(bcb);

			}
		}
		return retClients;
	}

	// TODO GET = 1.1 endpoint koji iz liste klijenata banke uzima samo email adrese
	// svih klijenata i vraća listu email adresa
	// putanja /emails

	@RequestMapping(method = RequestMethod.GET, value = "/emails")
	public List<String> findByEmails() {
		List<String> retEmails = new ArrayList<>();
		for (BankClientBean bcb : getDB()) {
			retEmails.add(bcb.getEmail());
		}
		return retEmails;
	}

	//TODO 1.2 endpoint koji vraća listu koja sadrži imena klijenata, čije ime
	// počinje na slovo koje je prosleđeno kao parametar
	// • putanja /clients/{firstLetter}

	@RequestMapping(value = "/clients/{firstLetter}", method = RequestMethod.GET)
	public ArrayList<BankClientBean> getNameStarting(@PathVariable String firstLetter) {
		ArrayList<BankClientBean> nameFirstLetter = new ArrayList<BankClientBean>();
		for (BankClientBean bcb : getDB()) {
			if (bcb.getName().toLowerCase().startsWith(firstLetter)) {
				nameFirstLetter.add(bcb);
			}
		}
		return nameFirstLetter;
	}

	//TODO 1.3 endpoint koji vraća listu koja sadrži imena i prezimena klijenata,
	// čije ime počinje na slovo koje je prosleđeno kao parametar i čije
	// prezime počinje na slovo koje je prosleđeno kao parametar
	// • putanja /clients/firstLetters

	@RequestMapping(method = RequestMethod.GET, value = "/clients/firstLetters")
	public List<BankClientBean> getByNameAndSurname(@RequestParam String firstName, @RequestParam String surName) {
		ArrayList<BankClientBean> result = new ArrayList<BankClientBean>();
		for (BankClientBean bcb : getDB()) {
			if ((bcb.getName().toLowerCase().startsWith(firstName)) && (bcb.getSurname().toLowerCase().startsWith(surName))) {
				result.add(bcb);
			}
		}
		return result;
	}

	// TODO 1.4 endpoint koji vraća listu koja sadrži imena klijenata, koja su
	// u redosledu koji je prosleđen kao parameter
	// • putanja /clients/sort/{order}

	@RequestMapping(method = RequestMethod.GET, value = "/clients/sort/{order}")
	public List<String> sortNames(@PathVariable String order) {
		List<String> sortedNames = new ArrayList<>();
		for (BankClientBean bcb : clients) {
			sortedNames.add(bcb.getName());
		}

		if ((order.equals("desc")) || (order.equals("descending")))
			Collections.sort(sortedNames, Collections.reverseOrder());
		else if ((order.equals("asc")) || (order.equals("ascending")))
			Collections.sort(sortedNames);
		else
			;
		return sortedNames;
	}
	
	//TODO 2.1 endpoint koji u listi klijenata banke, svakom klijentu, postavlja
	//polje bonitet na ‘P’ (pozitivan) ako je klijent mlađi od 65 godina ili
	//‘N’ negativan ako je klijent stariji od 65 godina
	//• putanja /clients/bonitet
	//• u klasu BankClientBean dodati atribute datum rođenja i bonitet
	
	@RequestMapping(method = RequestMethod.POST , value = "/clients/bonitet")
	public List<BankClientBean> bonitet() {
		List<BankClientBean> clients = getDB();
		List <BankClientBean> bonitet = new ArrayList<>();
		for(BankClientBean bcb : clients){
			LocalDate now = LocalDate.now();
			Period diff = Period.between(bcb.getLocalDate(), now);
			if (diff.getYears() > 65) {
				bcb.setBonitet("N");
			} 
			else bcb.setBonitet("P");
			bonitet.add(bcb);			
		}
		return bonitet;
	}
	// TODO 2.2 endpoint koji briše klijenta iz liste klijenta ukoliko klijent nema
	//jednu od vrednosti: ime, prezime, email
	//• putanja /clients/delete
	
	@RequestMapping(method = RequestMethod.DELETE,  value = "/clients/delete")
	public List<BankClientBean> deleteBankClients() {
		List<BankClientBean> delClients = new ArrayList<BankClientBean>();
		Iterator<BankClientBean> it = clients.iterator();
		while (it.hasNext()) {
			BankClientBean bcb = it.next();
			if ((bcb.getName() == null) || (bcb.getSurname() == null) || (bcb.getEmail() == null)) {
				delClients.add(bcb);
				it.remove();
			}
		}
		return delClients;
	}
	
	// TODO • 2.3 endpoint koji vraća ukupan broj klijenata u listi klijenata koji
	//imaju manje od broja godina koje je prosleđeno kao parametar
	//• putanja /clients/countLess/{years}
	
	@RequestMapping(method = RequestMethod.GET, value = "/clients/countLess/{years}")
	public int countLess(@PathVariable Integer years) {
		List<BankClientBean> clients = getDB();
		Integer i=0;
		for(BankClientBean bcb : clients){
			LocalDate now = LocalDate.now();
			Period diff = Period.between(bcb.getLocalDate(), now);
			if (diff.getYears()<years) {
				i++;	
			}		
		}
		return i;
	}
	
	//TODO 2.4 endpoint koji prosečan broj godina klijenata iz liste klijenata
	// • putanja /clients/averageYears
	
	@RequestMapping(method = RequestMethod.GET, value = "/clients/averageYears")
	public double Average() {
		List<BankClientBean> clients = getDB();
		double i=0;
		for(BankClientBean bcb : clients){
			LocalDate now = LocalDate.now();
			Period diff = Period.between(bcb.getLocalDate(), now);
			i+=diff.getYears();
		}
		return i/clients.size();
	}

	
	// TODO • 3.1 endpoint koji omogućuje izmenu mesta stanovanja klijenta
	//• putanja /clients/changelocation/{clientId}
	//• u klasu BankClientBean dodati atribut grad
	//• novu vrednost mesta stanovanja proslediti kao QueryParameter
	
	@RequestMapping(value = "/clients/changelocation/{clientId}", method = RequestMethod.POST)
	public BankClientBean changeCity(@PathVariable Integer clientId, @RequestParam String grad) {
		for(BankClientBean bcb : clients) {
			if (bcb.getId().equals(clientId)) {
				bcb.setGrad(grad);
				return bcb;
			}
		}
		return null;
	}
	
	//TODO • 3.2 endpoint koji vraća klijente banke koji žive u gradu koji je
	//prosleđen kao parametar
	//• putanja /clients/from/{city} 
	
	@RequestMapping(method = RequestMethod.GET, value = "/clients/from/{city}")
	public List<BankClientBean> fromCity(@PathVariable String city) {
		List<BankClientBean> fromCity = new ArrayList<>();
		for (BankClientBean bcb : clients) {
			if (bcb.getGrad().equalsIgnoreCase(city))
				fromCity.add(bcb);

		}
		return fromCity;

	}
	// TODO 3.3 endpoint koji vraća klijente banke koji žive u gradu koji je
	//prosleđen kao parametar i čiji je broj godina ispod broja
	//prosleđenog kao drugi parametar
	//• putanja /clients/findByCityAndAge
	
	@RequestMapping(method = RequestMethod.GET, value = "/clients/findByCityAndAge")
	public List<BankClientBean> findByCityAndAge(@RequestParam String city, @RequestParam Integer age) {
		List<BankClientBean> clients = getDB();
		List <BankClientBean> cityAndAge = new ArrayList<BankClientBean>();
		for(BankClientBean bcb : clients){
			LocalDate now = LocalDate.now();
			Period diff = Period.between(bcb.getLocalDate(), now);
			if(bcb.getGrad().equalsIgnoreCase(city) && diff.getYears()<age) {
				cityAndAge.add(bcb);
			}				
		}
		return cityAndAge;
	}
}
