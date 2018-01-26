package com.miage.metier;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.miage.entities.Video;
/**
 * @author HRezgui
 *
 */
public interface IVideoMetier {
	
	public List<Video> chercherVideoParNom(String nom);
	public List<Video> listVideoDisop();
	public List<Video> listVideoInDisop();
	
	public Video consulterVideo(Long code);
	public List<Video> listVideo();
	public void effacerVideo(Long code);
	public void modifierVideo (Long code, Video video);

}
