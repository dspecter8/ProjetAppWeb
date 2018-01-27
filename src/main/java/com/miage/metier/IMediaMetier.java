package com.miage.metier;

import java.util.List;

import com.miage.entities.Media;

public interface IMediaMetier  {
	
	public List<Media> chercherMediaParNom(String nom);
	public List<Media> listMedia();

}
