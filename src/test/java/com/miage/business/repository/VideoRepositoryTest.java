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
import com.miage.dao.VideoRepository;
import com.miage.dao.EmployerRepository;
import com.miage.entities.Video;
import com.miage.entities.Employer;

/**
 * @author HRezgui
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringBusinessConfigTest.class })
public class VideoRepositoryTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(VideoRepositoryTest.class);

	private static final String NOM_Video = "nomVideo1";
	private static final int QUANTITE_Video = 100;
	private static final int ETAT_Video = 1;
	private static final Date DATE_CREATION_Video = new Date();
	private static final String DESCRIPTION_Video = "description1 Video";
	private static final String NOM_PHOTO_Video = "photoVideo1";
	private static final String REALISATEUR = "REALISATEURVideo";
	private static final String ACTEUR = "ACTEUR";
	private static final Date DATE_SORTIE_Video = new Date();
	private static final String STUDIO_Video = "STIDIO Video1";
	
	@Autowired
	private VideoRepository VideoRep;
	
	@Autowired
	private EmployerRepository employerRep;
	
	@Before
	public void initObjects() {
		
		if (CollectionUtils.isEmpty(VideoRep.findByName(NOM_Video))) {
			
			Employer employ = employerRep.findOne((long) 3) ;
			
			Video Video = VideoRep.save(new Video(NOM_Video, QUANTITE_Video, ETAT_Video, DATE_CREATION_Video, DESCRIPTION_Video, NOM_PHOTO_Video, employ,REALISATEUR,ACTEUR,
					DATE_SORTIE_Video, STUDIO_Video));
			
		}

		
	}
	
	
	@Test
	public void findByName_success() {
		
		List<Video> entities = (List<Video>) VideoRep.findByName(NOM_Video);
		assertNotNull(entities);
		assertEquals(entities.size(), 1);
		final Video expectedEntity = entities.get(0);
		assertEquals(expectedEntity.getNom(), NOM_Video);
		assertEquals(expectedEntity.getNom(), NOM_Video);

		LOGGER.info(">>>>> VideoRepository findByName_success OK");
	}
	
	

	@Test
	public void findByName_notFound() {
		List<Video> entities = (List<Video>) VideoRep.findByName("grrrrr");
		assertNotNull(entities);
		assertEquals(entities.size(), 0);

		LOGGER.info(">>>>> VideoRepository findByName_notFound OK");
	}
	
}
