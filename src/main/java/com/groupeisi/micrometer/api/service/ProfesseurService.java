package com.groupeisi.micrometer.api.service;

import com.groupeisi.micrometer.api.dao.IProfesseurRepository;
import com.groupeisi.micrometer.api.dto.Professeur;
import com.groupeisi.micrometer.api.exception.EntityAlreadyExistsException;
import com.groupeisi.micrometer.api.exception.EntityNotFoundException;
import com.groupeisi.micrometer.api.mapping.ProfesseurMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class ProfesseurService {
	
	private IProfesseurRepository professeurRepository;
	private ProfesseurMapper professeurMapper;
	private MessageSource messageSource;

	// Is the List of Profs empty
//	public Boolean isListEmpty(){
//		return professeurRepository.findAll().isEmpty();
//	}

	// Get All Users
	@Transactional(readOnly = true)
	public List<Professeur> getProfesseurs() {
		int id = 0;
		return StreamSupport.stream(Optional.ofNullable(professeurRepository.findAll()).orElseThrow(() ->
								new EntityNotFoundException(messageSource.getMessage("professeur.notfound", new Object[]{id}, Locale.getDefault())))
						.spliterator(), false)
				.map(professeurMapper::toProfesseur)
				.collect(Collectors.toList());
	}

	// Get One User By his ID
	@Transactional(readOnly = true)
	public Professeur getProfesseur(Integer id) {
		 return professeurMapper.toProfesseur(professeurRepository.findById(id)
				 .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("professeurId.notfound", new Object[]{id}, Locale.getDefault())))
		 );
	}

	// Get One User By his Email
	@Transactional(readOnly = true)
	public Professeur getProfesseurByEmail(String email) {
		return professeurMapper.toProfesseur(Optional.ofNullable(professeurRepository.findByEmailIgnoreCase(email))
				.orElseThrow(() ->
					new EntityNotFoundException(messageSource.getMessage("professeurEmail.notfound", new Object[]{email}, Locale.getDefault()))
				)
		);
	}

	@Transactional(readOnly = true)
	public List<Professeur> getProfesseurByLastname(String lastname) {
		return StreamSupport.stream(Optional.ofNullable(professeurRepository.findByNomIgnoreCase(lastname))
					.orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("professeurNom.notfound", new Object[]{lastname}, Locale.getDefault())))
					.spliterator(), false)
				.map(professeurMapper::toProfesseur)
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<Professeur> getProfesseurByFirstname(String firstname) {
		return StreamSupport.stream(Optional.ofNullable(professeurRepository.findByPrenomIgnoreCase(firstname))
					.orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("professeurPrenom.notfound", new Object[]{firstname}, Locale.getDefault())))
					.spliterator(), false)
				.map(professeurMapper::toProfesseur)
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<Professeur> getProfesseurByPrenomAndNom(String firstname, String lastname) {
		return StreamSupport.stream(Optional.ofNullable(professeurRepository.findByPrenomIgnoreCaseAndNomIgnoreCase(firstname, lastname))
					.orElseThrow(() -> new EntityNotFoundException( messageSource.getMessage("professeurPrenomNom.notfound", new Object[]{firstname, lastname}, Locale.getDefault())))
					.spliterator(), false)
				.map(professeurMapper::toProfesseur)
				.collect(Collectors.toList());
	}

	@Transactional
	public Professeur createProfesseur(Professeur professeur) {
		Professeur prof = professeurMapper.toProfesseur(professeurRepository.findByEmailIgnoreCase(professeur.getEmail()));
		if (prof == null){
			return professeurMapper.toProfesseur(professeurRepository.save(professeurMapper.fromProfesseur(professeur)));
		}
		else {
			throw new EntityAlreadyExistsException( messageSource.getMessage("professeurEmail.exists", new Object[]{prof.getEmail()}, Locale.getDefault()));
		}
	}

	// Save a List of Profs
//	public String saveProfessors(List<ProfesseurEntity> profs){
//		professeurRepository.saveAll(profs);
//		return "Professors added successfully !";
//	}
	
	@Transactional
	public Professeur updateProfesseur(Integer id, Professeur Professeur) {
		return professeurRepository.findById(id)
				.map(entity -> {
					Professeur.setId(id);
					return professeurMapper.toProfesseur(professeurRepository.save(professeurMapper.fromProfesseur(Professeur)));
				})
				.orElseThrow(
						() -> new EntityNotFoundException(messageSource.getMessage("professeurId.notfound", new Object[]{id}, Locale.getDefault()))
				);
	}
	
	@Transactional
	public void deleteProfesseur(Integer id) {
		try {
			professeurRepository.deleteById(id);
		} catch (Exception e) {
			throw new EntityNotFoundException(messageSource.getMessage("professeur.errordeletion", new Object[] {id},
					Locale.getDefault()));
		}
	}

}
