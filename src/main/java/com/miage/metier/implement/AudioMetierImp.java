package com.miage.metier.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miage.dao.AudioRepository;
import com.miage.entities.Audio;
import com.miage.metier.IAudioMetier;

/**
 * @author HRezgui
 *
 */

@Service
@Transactional
public class AudioMetierImp  implements IAudioMetier{
	
	@Autowired
	private AudioRepository audioRep;

	@Override
	public List<Audio> chercherAudioParNom(String nom) {
		return audioRep.findByName(nom);
	}

	@Override
	public List<Audio> listAudioDisop() {
		return audioRep.lstMediaByEtat(1);
	}

	@Override
	public List<Audio> listAudioInDisop( ) {
		return audioRep.lstMediaByEtat(0);
	}

	@Override
	public Audio consulterAudio(Long code) {
		return audioRep.findOne(code);
	}

	@Override
	public List<Audio> listAudio() {
		return audioRep.findAll();
	}

	@Override
	public void effacerAudio(Long code) {
		audioRep.delete(code);		
	}

	@Override
	public void modifierAudio(Long code, Audio v) {
		
		Audio v2 = audioRep.getOne(code);
		v2.setChateur(v.getChateur());
		v2.setAnneeDeSortie(v.getAnneeDeSortie());
		v2.setDateCreation(v.getDateCreation());
		v2.setEmploye(v.getEmploye());
		v2.setEtat(v.getEtat());
		v2.setNom(v.getNom());
		v2.setDescription(v.getDescription());
		v2.setPhoto(v.getPhoto());
		
		audioRep.saveAndFlush(v2);

		 
	}

}
