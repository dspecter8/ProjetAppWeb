package com.miage.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.miage.entities.Administrateur;
import com.miage.entities.Client;
import com.miage.entities.Employer;
import com.miage.entities.Personne;
import com.miage.metier.IAdminMetier;


//@RequestMapping(value = "/in")
@Controller
@SessionAttributes("user")
public class LoginController {

	@Autowired
	private IAdminMetier admin;
	
	public  ModelMap modelstatic = new ModelMap();
	
	//public String loginError ="Aucune connecyion actuellement";
	
//	public Administrateur connect() {
//		return new Administrateur();
//	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String connexionLogin(Model model) {
		model.addAttribute("loginError","Aucune connexion actuellement");
		return "login";
	}
	
	@RequestMapping(value = "/addClient", method = RequestMethod.GET)
	public String addClient() {
		return "addClient";
	}
	
	//@ModelAttribute("user")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String verificationLogin(@RequestParam String email, @RequestParam String mdp, HttpSession session,
	//public String verificationLogin(@ModelAttribute("user") Personne user, HttpSession session,
			Model model) {
		
		Personne p = admin.loginConnect(email, mdp);
		if (p == null) {
			model.addAttribute("loginError", "Erreur de connexion, Re-essayez SVP");
			return "redirect:login";
		}

		else if (p instanceof Administrateur) {
			Administrateur a = (Administrateur) p;
			
			session.setAttribute("loggedInUser", a);
			model.addAttribute("UserCurrent",a);
			model.addAttribute("userConnect1",session.getValueNames());
			model.addAttribute("perso1",a);
			
			//modelstatic.addAttribute("perso", a);
			return "adm/admin";
		}

		else if (p instanceof Employer) {
			Employer e = (Employer) p;
			session.setAttribute("loggedInUser", e);
			model.addAttribute("UserCurrent",e);
			
			model.addAttribute("perso1",e);
		//	modelstatic.addAttribute("perso", e);
			return "Employer/employerTemplate";
		} else if (p instanceof Client) {
			Client c = (Client) p;
			session.setAttribute("loggedInUser", c);
			//modelstatic.addAttribute("perso", c);
			model.addAttribute("perso1",c);
			model.addAttribute("UserCurrent",session.getId());
			return "client/clientTemplate";
		}
		return "redirect:login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession sesion) {
		sesion.removeAttribute("loggedInUser");
		return "redirect:/login";
	}

}
