package com.miage.metier;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.miage.entities.Audio;

public interface IAudioMetier {
	
	public List<Audio> chercherAudioParNom(String nom);
	public List<Audio> listAudioDisop();
	public List<Audio> listAudioInDisop();
	
	public Audio consulterAudio(Long code);
	public List<Audio> listAudio();
	public void effacerAudio(Long code);
	public void modifierAudio (Long code, Audio audio);
	
	
	
	

}
