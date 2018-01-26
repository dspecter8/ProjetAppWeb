package com.miage.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * @author Specter
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="TYPE_OP", discriminatorType=DiscriminatorType.STRING,length=3)
public abstract class Operation implements Serializable {
	
	@Id
	@GeneratedValue
	private Long numero;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateOperation = new Date();
	
	@ManyToOne
	@JoinColumn(name="CODE_MEDIA")
	private Media media;
	
	@ManyToOne
	@JoinColumn(name="CODE_CLI")
	private Client client;
	
/*	@ManyToOne
	@JoinColumn(name="CODE_CLI")
	private Client client;*/
	
/*	@ManyToOne
	@JoinColumn(name="CODE_EMPL")
	private Employer employee;
	*/



/*	public Employer getEmployee() {
		return employee;
	}



	public void setEmployee(Employer employee) {
		this.employee = employee;
	}
*/



	public Operation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Operation(Date dateOperation,Client client) {
		super();
		this.dateOperation = dateOperation;
		this.client = client;
		//this.media = media;
	}
	

	public Operation(Date dateOperation,Client client, Media media) {
		super();
		this.dateOperation = dateOperation;
		this.client = client;
		this.media = media;
	}



	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Date getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
