package com.miage.metier.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miage.dao.MediaRepository;
import com.miage.entities.Media;
import com.miage.metier.IMediaMetier;

/**
 * @author HRezgui
 *
 */

@Service
@Transactional
public class MediaMetierImp implements IMediaMetier{

	@Autowired
	private MediaRepository mediaRep;


	@Override
	public List<Media> listMedia() {
		// TODO Auto-generated method stub
		return mediaRep.findAll();
	}
	
	@Override
	public List<Media> chercherMediaParNom(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

}
