package com.groupeisi.micrometer.api;

import com.groupeisi.micrometer.api.dao.IProfesseurRepository;
import com.groupeisi.micrometer.api.entities.ProfesseurEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringBootGrafanaPrometheusApplication {
	@Autowired
	private IProfesseurRepository repository;
	@PostConstruct
	public void initUsers(){
		//if(professeurService.isListEmpty()) {
		List<ProfesseurEntity> users = Stream.of(
				new ProfesseurEntity(101, "MBOW", "Fallou", "Hann Mariste 1", "mbow@gmail.com", "passer123", 1, null),
				new ProfesseurEntity(102, "DIOP", "Moustapha", "Pikine Guinaw Rail", "diop@gmail.com", "passer123", 1, null),
				new ProfesseurEntity(103, "SECK", "Diama", "Cité Keur Gorgui", "seck@gmail.com", "passer123", 1, null),
				new ProfesseurEntity(104, "TALL", "Madicke", "Thiès - Hersent", "tall@gmail.com", "passer123", 1, null)
		).collect(Collectors.toList());
		//professeurService.saveProfessors(users);
		repository.saveAll(users);
		//}
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootGrafanaPrometheusApplication.class, args);
	}

}
