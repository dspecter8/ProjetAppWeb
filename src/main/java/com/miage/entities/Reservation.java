package com.miage.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
/**
 * @author HRezgui
 *
 */
@Entity
@DiscriminatorValue("RES")
public class Reservation extends Operation{

	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservation(Date dateOperation, Client client, Media media, int qntMedia) {
		super(dateOperation, client, media, qntMedia);
		// TODO Auto-generated constructor stub
	}

}
