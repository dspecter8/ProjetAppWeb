/**
 * 
 */
package com.miage.web;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
//import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.miage.dao.AdministrateurRepository;
import com.miage.dao.AudioRepository;
import com.miage.dao.EmployerRepository;
import com.miage.dao.LivreRepository;
import com.miage.dao.VideoRepository;
import com.miage.entities.Administrateur;
import com.miage.entities.Audio;
import com.miage.entities.Employer;
import com.miage.entities.Livre;
import com.miage.entities.Video;
import com.miage.metier.IAdminMetier;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

/**
 * @author Specter
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private IAdminMetier admin;

	@Autowired
	private EmployerRepository e;

	@Autowired
	private AdministrateurRepository ar;

	@Autowired
	private VideoRepository v;
	@Autowired
	private LivreRepository l;
	@Autowired
	private AudioRepository au;


	@RequestMapping("/consult")
	public String consultEmpl(HttpSession session,Model model, @RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {
		Page<Employer> emp = e.findByName("%" + mc + "%", new PageRequest(p, 8));

		int nbPage = emp.getTotalPages();
		int[] pages = new int[nbPage];
		for (int i = 0; i < nbPage; i++)
			pages[i] = i;
		Administrateur a1 = (Administrateur) session.getValue("loggedInUser");
		Administrateur a2 = a1;
		model.addAttribute("UserCurrent", a2);
		
		model.addAttribute("pages", pages);
		model.addAttribute("PageEmployer", emp);
		model.addAttribute("pageCourant", p);
		model.addAttribute("motCle", mc);
		return "adm/consultEmpl";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addEmployer(HttpSession session, Model model) {
		Administrateur a1 = (Administrateur) session.getValue("loggedInUser");
		Employer em = new Employer();
		em.setAdmin(a1);
		model.addAttribute("employerad", em);
		model.addAttribute("UserCurrent", a1);
		return "adm/addEmployer";
	}

	@RequestMapping(value = "/admin")
	public String admin(HttpSession session,Model model) {
		Administrateur a1 = (Administrateur) session.getValue("loggedInUser");
		Administrateur a2 = a1;
		model.addAttribute("UserCurrent", a2);
		return "adm/admin";
	}

	@RequestMapping(value = "/accueil")
	public String accueil(HttpSession session,Model model) {
		Administrateur a1 = (Administrateur) session.getValue("loggedInUser");
		model.addAttribute("UserCurrent", a1);
		return "adm/accueilAdmin";
	}

	// Fin modif
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid Employer e, BindingResult b) {
		if (b.hasErrors()) {
			return "adm/addEmployer";
		}
		admin.ajouterEmployer(e);
		return "redirect:consult";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Valid Employer ee, BindingResult b) {
		
		if (b.hasErrors()) {
			return "adm/editEmployer";
		}
		Employer e1 = ee;
		//e.delete(ee);
		e.saveAndFlush(e1);
		return "redirect:consult";
	}

	@RequestMapping(value = "/supprimer")
	public String delete(Long code) {
		e.delete(code);
		return "redirect:consult";
	}

	@RequestMapping(value = "/edit")
	public String edit(HttpSession session,Long code, Model model) {
		Administrateur a1 = (Administrateur) session.getValue("loggedInUser");
		model.addAttribute("UserCurrent", a1);
		Employer em = e.getOne(code);
		model.addAttribute("employeredi", em);
		return "adm/editEmployer";
	}

	// Consultation média
	@RequestMapping("/consultv")
	public String consultv(HttpSession session,Model model) {
		List<Video> vi = v.findAll();
		Administrateur a1 = (Administrateur) session.getValue("loggedInUser");
		Administrateur a2 = a1;
		model.addAttribute("UserCurrent", a2);
		model.addAttribute("mediav", vi);
		return "adm/consultvideo";
	}

	@RequestMapping("/consultl")
	public String consultl(HttpSession session,Model model) {
		List<Livre> li = l.findAll();
		Administrateur a1 = (Administrateur) session.getValue("loggedInUser");
		Administrateur a2 = a1;
		model.addAttribute("UserCurrent", a2);
		model.addAttribute("medial", li);
		return "adm/consultlivre";
	}

	@RequestMapping("/consulta")
	public String consulta(HttpSession session,Model model) {
		Administrateur a1 = (Administrateur) session.getValue("loggedInUser");
		Administrateur a2 = a1;
		model.addAttribute("UserCurrent", a2);
		List<Audio> aa = au.findAll();
		model.addAttribute("mediaa", aa);
		return "adm/consultaudio";
	}

	@RequestMapping("/parametre")
	public String parametre(HttpSession session,Model model) {
		Administrateur a1 = (Administrateur) session.getValue("loggedInUser");
		Administrateur a2 = a1;
		model.addAttribute("UserCurrent", a2);
		return "adm/parametre";
	}
	
	@RequestMapping("/consultadmin")
	public String ConsultAdmin(HttpSession session,Model model) {
		Administrateur a1 = (Administrateur) session.getValue("loggedInUser");
		Administrateur a2 = a1;
		model.addAttribute("UserCurrent", a2);
		return "adm/consultAdmin";
	}
	
	@RequestMapping("/maj")
	public String majAdmin(HttpSession session,Model model) {
		Administrateur a1 = (Administrateur) session.getValue("loggedInUser");
		Administrateur a2 = a1;
		model.addAttribute("UserCurrent", a2);
		model.addAttribute("adminedit", a2);
		return "adm/majAdmin";
	}
	
	@RequestMapping(value = "/updatead", method = RequestMethod.POST)
	public String updatead(@Valid Administrateur ee, BindingResult b) {
		
		if (b.hasErrors()) {
			return "adm/majAdmin";
		}
		Administrateur e1 = ee;
		ar.saveAndFlush(e1);
		return "redirect:consultadmin";
	}

}
