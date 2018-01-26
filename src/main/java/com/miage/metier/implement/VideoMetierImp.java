package com.miage.metier.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miage.dao.VideoRepository;
import com.miage.entities.Video;
import com.miage.metier.IVideoMetier;

/**
 * @author HRezgui
 *
 */

@Service
@Transactional
public class VideoMetierImp implements IVideoMetier {
	
	@Autowired
	private VideoRepository videoRep;

	@Override
	public List<Video> chercherVideoParNom(String nom) {
		return videoRep.findByName(nom);
	}

	@Override
	public List<Video> listVideoDisop() {
		return videoRep.lstMediaByEtat(1);
	}

	@Override
	public List<Video> listVideoInDisop() {
		return videoRep.lstMediaByEtat(0);
	}

	@Override
	public Video consulterVideo(Long code) {
		return videoRep.findOne(code);
	}

	@Override
	public List<Video> listVideo() {
		return videoRep.findAll();
	}

	@Override
	public void effacerVideo(Long code) {
		videoRep.delete(code);		
	}

	@Override
	public void modifierVideo(Long code, Video v) {
		Video v2 = videoRep.getOne(code);
		v2.setActeur(v.getActeur());
		v2.setAnneeDeSortie(v.getAnneeDeSortie());
		v2.setDateCreation(v.getDateCreation());
		v2.setEmploye(v.getEmploye());
		v2.setEtat(v.getEtat());
		v2.setNom(v.getNom());
		v2.setDescription(v.getDescription());
		v2.setRealisateur(v.getRealisateur());
		v2.setPhoto(v.getPhoto());
		
		videoRep.saveAndFlush(v2);		
	}

}
