package com.miage.business.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import com.miage.business.SpringBusinessConfigTest;
import com.miage.dao.LivreRepository;
import com.miage.dao.EmployerRepository;
import com.miage.entities.Livre;
import com.miage.entities.Employer;

/**
 * @author HRezgui
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringBusinessConfigTest.class })
public class LivreRepositoryTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(LivreRepositoryTest.class);

	private static final String NOM_Livre = "nomLivre1";
	private static final int QUANTITE_Livre = 100;
	private static final int ETAT_Livre = 1;
	private static final int NBR_PAGE = 50;
	private static final Date DATE_CREATION_Livre = new Date();
	private static final String DESCRIPTION_Livre = "description1 Livre";
	private static final String NOM_PHOTO_Livre = "photoLivre1";
	private static final String AUTEUR = "AUTEUR Livre";
	private static final Date DATE_SORTIE_Livre = new Date();
	private static final String NOM_EDITION = "STIDIO Livre1";
	
	@Autowired
	private LivreRepository LivreRep;
	
	@Autowired
	private EmployerRepository employerRep;
	
	@Before
	public void initObjects() {
		
		if (CollectionUtils.isEmpty(LivreRep.findByName(NOM_Livre))) {
			
			Employer employ = employerRep.findOne((long) 3) ;
			
			Livre Livre = LivreRep.save(new Livre(NOM_Livre, QUANTITE_Livre, ETAT_Livre, DATE_CREATION_Livre, DESCRIPTION_Livre, NOM_PHOTO_Livre, employ,AUTEUR,
					DATE_SORTIE_Livre, NOM_EDITION,NBR_PAGE));
			
		}

		
	}
	
	
	@Test
	public void findByName_success() {
		List<Livre> entities = (List<Livre>) LivreRep.findByName(NOM_Livre);
		assertNotNull(entities);
		assertEquals(entities.size(), 1);
		final Livre expectedEntity = entities.get(0);
		assertEquals(expectedEntity.getNom(), NOM_Livre);
		assertEquals(expectedEntity.getNom(), NOM_Livre);

		LOGGER.info(">>>>> LivreRepository findByName_success OK");
	}
	
	

	@Test
	public void findByName_notFound() {
		List<Livre> entities = (List<Livre>) LivreRep.findByName("grrrrr");
		assertNotNull(entities);
		assertEquals(entities.size(), 0);

		LOGGER.info(">>>>> LivreRepository findByName_notFound OK");
	}
	

}
