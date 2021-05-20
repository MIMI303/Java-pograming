package com.iktpreobuka.mojPrviProjekatTest.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyFirstController {

	@RequestMapping("/hello")
	public String hello() {
		return "Pozdrav iz mog prvog kontrolera.";
	}

	@RequestMapping("/greetings")
	public String greetings() {
		return "Hello Michal Supek!";
	}

	@RequestMapping("/date")
	public String date() {
		return LocalDateTime.now().toString();
	}

	@RequestMapping("/family")
	public ArrayList<String> porodica() {
		ArrayList<String> family = new ArrayList<String>();
		family.add("Tanja");
		family.add("Mihajlo");
		family.add("Marija");
		return family;
	}

	@RequestMapping("/myClass")
	public String myClass() {
		return "<!DOCTYPE html>\r\n" + "<html>\r\n" + "<title>Moja grupa</title>\r\n" + "<head>\r\n" + "<style>\r\n"
				+ "table {\r\n" + "width: 50%;\r\n" + "}\r\n" + "table, th, td {\r\n" + "  border: 1px solid black;\r\n"
				+ "}\r\n" + "</style>\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n" + "<h2>Moja Grupa</h2>\r\n" + "\r\n"
				+ "<table style=\"width:100%\">\r\n" + "  <tr>\r\n" + "    <th>Firstname</th>\r\n"
				+ "    <th>Lastname</th> \r\n" + "  </tr>\r\n" + "  <tr>\r\n" + "    <td>Nemanja</td>\r\n"
				+ "    <td>Stevanov</td>\r\n" + "  </tr>\r\n" + "  <tr>\r\n" + "    <td>Michal</td>\r\n"
				+ "    <td>Supek</td>\r\n" + "  </tr>\r\n" + "  <tr>\r\n" + "    <td>Branka</td>\r\n"
				+ "    <td>Kablar</td>\r\n" + "  </tr>\r\n" + "<tr>\r\n" + "    <td>Andrea</td>\r\n"
				+ "    <td>Sloboda</td>\r\n" + "  </tr>\r\n" + "<tr>\r\n" + "    <td>Nikola</td>\r\n"
				+ "    <td>Vetnic</td>\r\n" + "  </tr>\r\n" + "<tr>\r\n" + "    <td>Marijana</td>\r\n"
				+ "    <td>Verlic</td>\r\n" + "  </tr>\r\n" + "<tr>\r\n" + "    <td>Nemanja</td>\r\n"
				+ "    <td>Kovacevic</td>\r\n" + "  </tr>\r\n" + "</table>\r\n" + "\r\n" + "</body>\r\n" + "</html>";

	}

	@RequestMapping("/intarr")
	public int[] intArr() {
		int[] arr0 = { 44, 12, 34, 5, 51 };
		return arr0;
	}

	@RequestMapping("/intarrsort")
	public int[] intArrSort() {
		int[] arr0 = { 44, 12, 34, 5, 51 };
		int i, j;
		for (i = 1; i < arr0.length; i++) {
			int temp = arr0[i];
			for (j = i; j > 0 && temp < arr0[j - 1]; j--)
				arr0[j] = arr0[j - 1];
			arr0[j] = temp;
		}
		return arr0;
	}

	@RequestMapping("/minmax")
	public String minMax() {
		int[] arr0 = { 44, 12, 34, 5, 51 };
		int min = arr0[0];
		int max = arr0[0];
		for (int i = 0; i < arr0.length; i++) {
			if (arr0[i] > max) {
				max = arr0[i];
			} else if (arr0[i] < min) {
				min = arr0[i];
			}
		}
		return "Miniumum je " + min + "Maximum je " + max;
	}

	@RequestMapping("/sumaNiza")
	public String sumNiza() {
		int[] niz = { 2, 3, 12, 55, 32 };
		int sum = 0;
		for (int i = 0; i < niz.length; i++)
			sum += niz[i];
		return "Suma niza je: " + sum;

	}
	// TODO 4.3 endpoint koji predstavlja englesko-srpski rečnik i koji za reč na
	//srpskom vrati odgovarajući prevod na engleski jezik
	//• putanja /recnik/{trazena_rec}
	//• DODATNO: ukoliko za traženu reč ne postoji prevod, tada ispisati „Rec trazena_rec ne postoji u recniku.“
	@RequestMapping(value = "/recnik/{id}", method = RequestMethod.GET)
	private String recnik(@PathVariable String id) {
		Map<String, String> recnik = new HashMap<String, String>();
		recnik.put("tastatura", "keyboard");
		recnik.put("knjiga", "book");
		recnik.put("prozor", "window");
		if (recnik.get(id)!=null)
			return recnik.get(id);
		else
			return ("Rec '" + id + " ne postoji u recniku.");
	}
	
	// TODO 4.1 endpoint koji vraća „Hello yourName!“, gde yourName
	//prosleđeno kao parametar
	//• putanja /greetings/{name}
	
	@RequestMapping(value = "/greetings/{name}", method = RequestMethod.GET)
	public String helloYourName(@PathVariable String name){
		return ("Hello " + name);
	}
	
	//TODO 4.2 endpoint koji vraća sumu prvih n brojeva
	//• putanja /sumaNiza/{n}
	
	@RequestMapping(value = "/sumaNiza/{n}", method = RequestMethod.GET)
	public Integer helloYourName(@PathVariable Integer n){
		int[] arr0 = {0,5,12,33,45,54,6,32};
		int suma=0;
		for (int i=0;i<n;i++)
			suma+=arr0[i];
		return suma;
	}


}
