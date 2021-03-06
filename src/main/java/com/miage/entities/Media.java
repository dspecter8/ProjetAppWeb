package com.miage.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
//import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

//import org.hibernate.annotations.Cascade;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE_MEDIA", discriminatorType = DiscriminatorType.STRING, length = 2)
public abstract class Media implements Serializable {

	@Id
	@GeneratedValue
	private Long codeMedia;
	private String nom;
	private int quantite;
	private int etat;
	
	private String tag;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateCreation;
	
	@OneToMany(mappedBy = "media", fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE,CascadeType.MERGE, CascadeType.PERSIST }, orphanRemoval = false)
	private Collection<Operation> operations;
	
	private String description;
	//private String photo;	

	public Media() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public Media(String nom, int quantite, int etat, Date dateCreation, String description) {
//		super();
//		this.nom = nom;
//		this.quantite = quantite;
//		this.etat = etat;
//		this.dateCreation = dateCreation;
//		this.description = description;
//		//this.photo = photo;
//	}
	
	public Media(String nom, int quantite, int etat, Date dateCreation, String description) {
		super();
		this.nom = nom;
		this.quantite = quantite;
		this.etat = etat;
		this.dateCreation = dateCreation;
		this.description = description;
	}
	

	/**
	 * @param codeMedia
	 * @param nom
	 * @param quantite
	 * @param etat
	 * @param dateCreation
	 * @param description
	 * @param photo
	 */
	public Media(Long codeMedia, String nom, int quantite, int etat, Date dateCreation, String description) {
		super();
		this.codeMedia = codeMedia;
		this.nom = nom;
		this.quantite = quantite;
		this.etat = etat;
		this.dateCreation = dateCreation;
		this.description = description;
		//this.photo = photo;
	}
		
	public Media(Long codeMedia, String nom, int quantite, int etat, Date dateCreation, String description,String tag) {
		super();
		this.codeMedia = codeMedia;
		this.nom = nom;
		this.quantite = quantite;
		this.etat = etat;
		this.dateCreation = dateCreation;
		this.description = description;
		this.tag = tag;
		//this.photo = photo;
	}
	
	public Long getCodeMedia() {
		return codeMedia;
	}

	public void setCodeMedia(Long codeMedia) {
		this.codeMedia = codeMedia;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}
	
	

}
