package com.miage.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@DiscriminatorValue("EMP")
public class Emprunt extends Operation {

	// @ManyToOne
	// @JoinColumn(name="CODE_CLI")
	// private Client client;
	//private Date retourPrevu = new Date();
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateLimRetour = new Date((new Date()).getYear(),(new Date()).getMonth(),(new Date()).getDate()+15);

	@OneToMany(mappedBy = "emprunt", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE, CascadeType.MERGE,
			CascadeType.PERSIST }, orphanRemoval = false)
	private Collection<Livre> livres;

	@OneToMany(mappedBy = "emprunt", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE, CascadeType.MERGE,
			CascadeType.PERSIST }, orphanRemoval = false)
	private Collection<Audio> audios;

	@OneToMany(mappedBy = "emprunt", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE, CascadeType.MERGE,
			CascadeType.PERSIST }, orphanRemoval = false)
	private Collection<Video> videos;
	// Com
	public Emprunt() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Emprunt(Date dateOperation, Client client) {
		super(dateOperation, client);
		//this.dateLimRetour =dateLimRetour;
	}
	
	public Emprunt(Date dateOperation, Date dateLimtRetour, Client client, Media media) {
		super(dateOperation, client,media);
		this.dateLimRetour =dateLimtRetour;
		
	}
	
	

	public Date getDateLimRetour() {
		return dateLimRetour;
	}

	public void setDateLimRetour(Date dateLimRetour) {
		this.dateLimRetour = dateLimRetour;
	}
	
	
	

}
