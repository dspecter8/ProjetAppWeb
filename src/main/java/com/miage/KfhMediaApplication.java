package com.miage;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.miage.dao.AdministrateurRepository;
import com.miage.dao.ClientRepository;
import com.miage.dao.EmployerRepository;
import com.miage.dao.MediaRepository;
import com.miage.dao.OperationRepository;
import com.miage.entities.Administrateur;
import com.miage.entities.Audio;
import com.miage.entities.Client;
import com.miage.entities.Employer;
import com.miage.entities.Emprunt;
import com.miage.entities.Livre;
import com.miage.entities.Media;
import com.miage.entities.Operation;
import com.miage.entities.Personne;
import com.miage.entities.Retour;
import com.miage.entities.Video;
import com.miage.metier.IAdminMetier;
import com.miage.metier.IEmployerMetier;

@ComponentScan
//@EnableAutoConfiguration
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class KfhMediaApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository cliRep;
	@Autowired
	private AdministrateurRepository AdmRep;
	@Autowired
	private MediaRepository mRep;
	@Autowired
	private EmployerRepository emplRep;
	@Autowired
	private OperationRepository opeRep;

	@Autowired
	public IAdminMetier admMetier;

	@Autowired
	public IEmployerMetier emMetier;

	public static void main(String[] args) {
		SpringApplication.run(KfhMediaApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		
		Administrateur adm1 = AdmRep.save(new Administrateur("dddd", "renjm", "Memail", "motDePasse", "adress"));
		Administrateur adm = AdmRep.save(new Administrateur("dddd", "renom", "Memail1", "motDePasse", "adress"));
		Employer empl1 = emplRep
				.save(new Employer("22", "prenom", "ema7il1", "motDePasse", "adress", new Date(), "Non actif", adm));
		Client client = cliRep.save(new Client("REZGUI", "Hamza", "Cli7ent1email","Mr", "Client1motDePasse",
				"44 Boulevard STOESSEL MULHOUSE 68200", "0707070707", new Date(), new Date(), 0, empl1));
		Employer em2 = new Employer("22111", "pre222nom", "emai7l2", "mot222DePasse", "ad2222ress", new Date(), "Non actif",
				adm);
		emplRep.save(em2);
		Employer em3 = new Employer("aaaa111aaaaaaaa", "pre222nom", "e8mail3", "mot222DePasse", "ad2222ress",
				new Date(), "Non actif", adm);
	
//		Emprunt empr = opeRep.save(new Emprunt(new Date(), client));
		Employer emModif = new Employer("Modif11", "pre222nom", "email4", "mot222DePasse", "ad2222ress", new Date(),
				"Non actif", adm);
		// Test metier
		// Test metier
				Livre l = new Livre("Nature", 100, 1, new Date(), "titi tata", "12.jpg", empl1, "Toledo", new Date(),
						"nomEdition1", 45);
				//Livre lll = new Livre(nom, quantite, etat, dateCreation, description, photo, employe, auteur, anneeDeSortie, nomEdition, nombrePage)
				
				Livre l1 = new Livre("informatique", 0,0, new Date(), "tit4444444444i tata", "informatique.jpg", empl1, "Toledo", new Date(),
						"nomEdition1", 45);
				
				Livre l2 = new Livre("Nature2", 78,1, new Date(), "tit4444444444i tata", "13.jpg", empl1, "Toledo", new Date(),
						"nomEdition1", 45);
				
				emMetier.ajouterLivre(l);
				
				Client cl = new Client("Client1nom", "Client1Metierprenom", "ClieMetiernt1email", "ClientMetier1motDePasse", "ClientMetier1adress",
						new Date(), new Date(), 0, empl1);
				emMetier.ajouterClient(cl);
				admMetier.ajouterEmployer(em3);
				
				//emMetier.modifierMedia(l.getCodeMedia(), l1);
				
				admMetier.updateSalaireEmployer(1275.57, em2.getCode());
				admMetier.updateSalaireEmployer(1275.57, empl1.getCode());
				admMetier.updateSalaireEmployer(1275.57, em3.getCode());
				admMetier.activerEmployer(em2.getCode());
				
//				Media mediaV = mRep.save(new Video("videoGame", 100, 1, new Date(), "description Video1", "videoGame.jpg", empl1, "Réalisatuer V1",
//						"Acteurs V1",new Date(), "nomStudio V1"));
				
				Media mediaV2 = mRep.save(new Video("100-ans-crimes", 150, 1, new Date(), "description Video2", "100-ans-crimes.jpg", empl1, "Réalisatuer V2",
						"Acteurs V2",new Date(), "nomStudio V2"));

				Media mediaV3 = mRep.save(new Video("documentaire", 0, 0, new Date(), "description Video3", "documentaire-evenement.jpg", empl1, "Réalisatuer V3",
						"Acteurs V2",new Date(), "nomStudio V2"));
				
				Media media5 = mRep.save(new Audio("Phone", 45, 1, new Date(), "description", "e1.jpg", empl1, "chateur",
						new Date(), "nomStudio"));
				
				Media media6 = mRep.save(new Audio("Smart WATCH", 25, 1, new Date(), "description", "e2.jpg", empl1, "chateur",
						new Date(), "nomStudio"));
				
				Media media7 = mRep.save(new Audio("Watch", 0, 0, new Date(), "description", "watch.jpg", empl1, "chateur",
						new Date(), "nomStudio"));
				
				Media mediaV4 = mRep.save(l);
				Media mediaV5 = mRep.save(l2);
				Media mediaV6 = mRep.save(l1);
				
				Date date1= new Date((new Date()).getYear(),(new Date()).getMonth(),(new Date()).getDate()+15);
				Date date2= new Date((new Date()).getYear(),(new Date()).getMonth(),(new Date()).getDate()+17);
				Date date3= new Date((new Date()).getYear(),(new Date()).getMonth(),(new Date()).getDate()+9);
				Date date4= new Date((new Date()).getYear(),(new Date()).getMonth(),(new Date()).getDate()+15);
				
				Emprunt empr1 = opeRep.save(new Emprunt(new Date(),date1,client,media5,1));
				
				Emprunt empr2 = opeRep.save(new Emprunt(new Date(), date2,client,media6,2));
				
				Retour retour1 = opeRep.save(new Retour(date3,client,media5,1));
				Retour retour2 = opeRep.save(new Retour(date4,client,media6,2));

	}

}
