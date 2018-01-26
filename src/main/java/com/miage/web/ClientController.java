/**
 * 
 */
package com.miage.web;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestParam;

import com.miage.business.repository.AudioRepositoryTest;
import com.miage.entities.Administrateur;
import com.miage.entities.Audio;
import com.miage.entities.Client;
import com.miage.entities.Livre;
import com.miage.entities.Media;
import com.miage.entities.Video;
import com.miage.metier.implement.AudioMetierImp;
import com.miage.metier.implement.ClientMetierImpl;
import com.miage.metier.implement.EmpruntMetierImp;
import com.miage.metier.implement.LivreMetierImp;
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
	private EmpruntMetierImp empruntService;
	
	@Autowired
	private RetourMetierImp retourService;

	
	@RequestMapping(value = "/client")
	public String client(Model model,HttpSession session) {
		Client client = (Client) session.getValue("loggedInUser");
		model.addAttribute("UserCurrent", client);
		return "client/clientTemplate";
	}

	@RequestMapping(value = "/accueil")
	public String accueil(Model model,HttpSession session) {
		Client client = (Client) session.getValue("loggedInUser");
		model.addAttribute("UserCurrent", client);
		return "client/accueilClient";
	}
	
//	@RequestMapping("/listMedias")
//	public String listMedias(Model model) {
//	
//		return "PHASE2/client/client";
//	}
	
	//TODO 
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String save(@Valid Client cl, BindingResult b,Model model,HttpSession session) {
		if (b.hasErrors()) {
			return "client/editClient";
		}
		Client client = (Client) session.getValue("loggedInUser");
		model.addAttribute("UserCurrent", client);
		clientService.modifierClient(client.getCode(), cl);
		return "client/editClient";
	}
	
	@RequestMapping(value = "/edit")
	public String edit(Model model,HttpSession session) {
		Client client = (Client) session.getValue("loggedInUser");
		model.addAttribute("UserCurrent", client);
		Client cl = clientService.consulterClient(client.getCode());
		model.addAttribute("clientedi", cl);
		return "client/editClient";
	}
	

	@RequestMapping("/consultm")
	public String consultMedia(Model model,HttpSession session) {
		Client client = (Client) session.getValue("loggedInUser");
		model.addAttribute("UserCurrent", client);
		return "client/consultMedia";
	}

	
	@RequestMapping("/consultv")
	public String consultVideo(Model model,HttpSession session) {
		List<Video> vi = videoService.listVideo();
		Client client = (Client) session.getValue("loggedInUser");
		model.addAttribute("UserCurrent", client);
		model.addAttribute("listmediav", vi);
		return "client/consultvideo";
	}
	
//	   @RequestMapping(value="/consultv", method=RequestMethod.GET)
//	    public String getCodeVideo(@RequestParam("videoNom") String videoNom ,   
//	                                 Model model) {    
//
//	        Video video = videoService.chercherVideoParNom(videoNom).get(0);
//	      //  model.addAttribute("person", person);
//	        LOGGER.info(">>>>> NOMMMM CONSULT VIDEO SUCCSESS "+ video.getNom());
//	        model.addAttribute("video", video);
//			return "redirect:/client/consultv/s";
//	       
//
//	    }    
//	@RequestMapping(value = "/consultv", method = RequestMethod.POST)
//	public String submitCreate(@Valid @ModelAttribute Video m, BindingResult bindingResult, Model model) {
//		
//		if (bindingResult.hasErrors()) {
//            return "client/consultvideo";
//        }
//
//		model.addAttribute("video", m);
//		return "redirect:/consultv";
//	}
	
//	
//	@RequestMapping(value = "/cosnultv", method = RequestMethod.POST)
//	public String verificationLogin(@RequestParam String nameVideo, HttpSession session) 
//	{
//	 return null;
//	  }
	
	
	@RequestMapping("/consulta")
	public String consultAudio(Model model,HttpSession session) {
		List<Audio> vi = audioService.listAudio();
		Client client = (Client) session.getValue("loggedInUser");
		model.addAttribute("UserCurrent", client);
		model.addAttribute("listmediaa", vi);
		return "client/consultaudio";
	}
	
	@RequestMapping("/consultl")
	public String consultLivre(Model model,HttpSession session) {
		Client client = (Client) session.getValue("loggedInUser");
		model.addAttribute("UserCurrent", client);
		List<Livre> vi = livreService.listLivre();
			model.addAttribute("listmedial", vi);
		return "client/consultlivre";
	}
	
	
	@RequestMapping("/consultme")
	public String consultMediaEmp(Model model,HttpSession session) {
		Client client = (Client) session.getValue("loggedInUser");
		model.addAttribute("UserCurrent", client);
		return "client/consultMediaEmp";
	}
	
	@RequestMapping("/consultvEmp")
	public String consultVideoEmp(Client client, Model model,HttpSession session) {
		Client cl= (Client) session.getValue("loggedInUser");
		model.addAttribute("UserCurrent", cl);
		
		List<Media> empv = empruntService.consulterMediaEmp(client);
		
		//TODO selecter que les videos dans la liste 
		model.addAttribute("listmediaempv", empv);
		return "client/consultvideoEmp";
	}
	
	@RequestMapping("/consultaEmp")
	public String consultAudioEmp(Client client, Model model,HttpSession session) {
		Client cl = (Client) session.getValue("loggedInUser");
		model.addAttribute("UserCurrent", cl);
		
		List<Media> empa = empruntService.consulterMediaEmp(client);
		model.addAttribute("listmediaempa", empa);
		return "client/consultaudioEmp";
	}
	
	@RequestMapping("/consultlEmp")
	public String consultLivreEmp(Client client, Model model,HttpSession session) {
		Client cl = (Client) session.getValue("loggedInUser");
		model.addAttribute("UserCurrent", cl);
		
		List<Media> empl = empruntService.consulterMediaEmp(client);
		model.addAttribute("listmediaempl", empl);
		return "client/consultlivreEmp";
	}
	
	
	@RequestMapping("/consultmr")
	public String consultMediaRet(Model model,HttpSession session) {
		
		Client cl = (Client) session.getValue("loggedInUser");
		model.addAttribute("UserCurrent", cl);
		
		Client client = (Client) session.getValue("loggedInUser");
		model.addAttribute("UserCurrent", client);
		return "client/consultMediaRet";
	}
	
	@RequestMapping("/consultvRet")
	public String consultVideoRet(Client client, Model model,HttpSession session) {
		Client cl = (Client) session.getValue("loggedInUser");
		model.addAttribute("UserCurrent", cl);
		
		List<Media> Retv= retourService.consulterMediaRet(client);
		
        model.addAttribute("listmediaRetv", Retv);
		return "client/consultvideoRet";
	}
	
	@RequestMapping("/consultaRet")
	public String consultAudioRet(Client client, Model model,HttpSession session) {
		Client cl = (Client) session.getValue("loggedInUser");
		model.addAttribute("UserCurrent", cl);
		
		List<Media> Reta = retourService.consulterMediaRet(client);
		
		model.addAttribute("listmediaReta", Reta);
		return "client/consultaudioRet";
	}
	
	
	@RequestMapping("/consultlRet")
	public String consultLivreRet(Client client, Model model,HttpSession session) {
		Client cl = (Client) session.getValue("loggedInUser");
		model.addAttribute("UserCurrent", cl);
		List<Media> Retl = retourService.consulterMediaRet(client);
		
		model.addAttribute("listmediaRetl", Retl);
		return "client/consultlivreRet";
	}
	
	
	@RequestMapping("/parametre")
	public String parametre(HttpSession session,Model model) {
		Client client = (Client) session.getValue("loggedInUser");
		model.addAttribute("UserCurrent", client);
	return "client/parametre";
	}
	
	
}
