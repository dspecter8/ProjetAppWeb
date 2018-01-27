/**
 * 
 */
package com.miage.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.miage.entities.Audio;
import com.miage.entities.Client;
import com.miage.entities.Emprunt;
import com.miage.entities.Livre;
import com.miage.entities.Media;
import com.miage.entities.Reservation;
import com.miage.entities.Retour;
import com.miage.entities.Video;
import com.miage.metier.implement.AudioMetierImp;
import com.miage.metier.implement.ClientMetierImpl;
import com.miage.metier.implement.EmpruntMetierImp;
import com.miage.metier.implement.LivreMetierImp;
import com.miage.metier.implement.MediaMetierImp;
import com.miage.metier.implement.ReservationMetierImp;
import com.miage.metier.implement.RetourMetierImp;
import com.miage.metier.implement.VideoMetierImp;

/**
 * @author HRezgui
 *
 */
@Controller
@RequestMapping("/client")
public class ClientController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	private ClientMetierImpl clientService;
	@Autowired
	private VideoMetierImp videoService;

	@Autowired
	private AudioMetierImp audioService;
	@Autowired
	private LivreMetierImp livreService;

	@Autowired
	private MediaMetierImp mediaService;
	@Autowired
	private EmpruntMetierImp empruntService;

	@Autowired
	private ReservationMetierImp resrvationService;
	
	@Autowired
	private RetourMetierImp retourService;

	@RequestMapping(value = "/client")
	public String client(Model model, HttpSession session) {
		
		Client client = (Client) session.getValue("loggedInUser");
		model.addAttribute("UserCurrent", client);
		model.addAttribute("listmedia", mediaService.listMedia());

		Emprunt emprunt = empruntService.alertRetSuivant(client).get(0);
		model.addAttribute("emprunteSuivant", emprunt);
		LOGGER.info(">>>>> DAAATE LIMT EMPRUNT SUCCSESS " + emprunt.getDateLimRetour());

		return "client/clientTemplate";
	}


	@RequestMapping(value = "/consultClient")
	public String consultClient(Model model, HttpSession session) {
		Client client = (Client) session.getValue("loggedInUser");
		model.addAttribute("UserCurrent", client);

		Emprunt emprunt = empruntService.alertRetSuivant(client).get(0);
		model.addAttribute("emprunteSuivant", emprunt);
		return "client/consultClient";
	}

	@RequestMapping(value = "/edit")
	public String edit(Model model, HttpSession session) {
		
		Client client = (Client) session.getValue("loggedInUser");

		Emprunt emprunt = empruntService.alertRetSuivant(client).get(0);
		model.addAttribute("emprunteSuivant", emprunt);
		return "client/editClient";
	}

	@RequestMapping(value = "/editerCompte", method = RequestMethod.POST)
	public String updatead(@Valid Client clientP, BindingResult b, HttpSession session) {

		Client client = (Client) session.getValue("loggedInUser");
		if (b.hasErrors()) {
			return "client/edit";
		}
		clientService.modifierClient(  clientP);
		return "redirect:consultClient";
	}

	@RequestMapping("/consultm")
	public String consultMedia(Model model, HttpSession session) {
		
		Client client = (Client) session.getValue("loggedInUser");
		model.addAttribute("listmedia", mediaService.listMedia());

		Emprunt emprunt = empruntService.alertRetSuivant(client).get(0);
		model.addAttribute("emprunteSuivant", emprunt);

		return "client/consultMedia";
	}

	@RequestMapping("/consultv")
	public String consultVideo(Model model, HttpSession session) {
		List<Video> vi = videoService.listVideo();
		Client client = (Client) session.getValue("loggedInUser");
		model.addAttribute("listmediav", vi);

		LOGGER.info(">>>>> NOMMMM CONSULT VIDEO SUCCSESS " + vi.get(0).getNom());

		Emprunt emprunt = empruntService.alertRetSuivant(client).get(0);
		model.addAttribute("emprunteSuivant", emprunt);

		return "client/consultvideo";
	}

	@RequestMapping("/consulta")
	public String consultAudio(Model model, HttpSession session) {
		
		List<Audio> vi = audioService.listAudio();
		Client client = (Client) session.getValue("loggedInUser");
		model.addAttribute("listmediaa", vi);

		Emprunt emprunt = empruntService.alertRetSuivant(client).get(0);
		model.addAttribute("emprunteSuivant", emprunt);
		return "client/consultaudio";
	}

	@RequestMapping("/consultl")
	public String consultLivre(Model model, HttpSession session) {
		
		Client client = (Client) session.getValue("loggedInUser");
		List<Livre> vi = livreService.listLivre();
		model.addAttribute("listmedial", vi);

		Emprunt emprunt = empruntService.alertRetSuivant(client).get(0);
		model.addAttribute("emprunteSuivant", emprunt);
		return "client/consultlivre";
	}

	@RequestMapping("/consultme")
	public String consultMediaEmp(Model model, HttpSession session) {
		
		Client client = (Client) session.getValue("loggedInUser");

		List<Emprunt> emp = empruntService.consulterEmpByClient(client);
		model.addAttribute("listmediaemp", emp);

		Emprunt emprunt = empruntService.alertRetSuivant(client).get(0);
		model.addAttribute("emprunteSuivant", emprunt);
		return "client/consultMediaEmp";
	}

	@RequestMapping("/consultmr")
	public String consultMediaRet(Model model, HttpSession session) {

		Client client = (Client) session.getValue("loggedInUser");

		List<Retour> ret = retourService.consulterRetByClient(client);
		model.addAttribute("listmediaret", ret);

		Emprunt emprunt = empruntService.alertRetSuivant(client).get(0);
		model.addAttribute("emprunteSuivant", emprunt);
		return "client/consultMediaRet";
	}

	@RequestMapping("/parametre")
	public String parametre(HttpSession session, Model model) {
		Client client = (Client) session.getValue("loggedInUser");

		Emprunt emprunt = empruntService.alertRetSuivant(client).get(0);
		model.addAttribute("emprunteSuivant", emprunt);
		return "client/parametre";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String verificationLogin(@RequestParam Reservation reservartion,	HttpSession session,	Model model) {
		Client client = (Client) session.getValue("loggedInUser");
		 
		resrvationService.ajouterRes(new Reservation( reservartion.getDateOperation(), client,reservartion.getMedia() , reservartion.getQuantiteMedia()));
		return "client/consultMedia";
	}
	

}
