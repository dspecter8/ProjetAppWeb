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
import com.miage.dao.AudioRepository;
import com.miage.dao.EmployerRepository;
import com.miage.entities.Audio;
import com.miage.entities.Employer;

/**
 * @author HRezgui
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringBusinessConfigTest.class })
public class AudioRepositoryTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AudioRepositoryTest.class);

	private static final String NOM_AUDIO = "nomAudio1";
	private static final int QUANTITE_AUDIO = 100;
	private static final int ETAT_AUDIO = 1;
	private static final Date DATE_CREATION_AUDIO = new Date();
	private static final String DESCRIPTION_AUDIO = "description1 audio";
	private static final String NOM_PHOTO_AUDIO = "photoaudio1";
	private static final String CHANTEUR = "chanteuraudio";
	private static final Date DATE_SORTIE_AUDIO = new Date();
	private static final String STUDIO_AUDIO = "STIDIO AUDIO1";
	
	@Autowired
	private AudioRepository audioRep;
	
	@Autowired
	private EmployerRepository employerRep;
	
	@Before
	public void initObjects() {
		
		if (CollectionUtils.isEmpty(audioRep.findByName(NOM_AUDIO))) {
			
			Employer employ = employerRep.findOne((long) 3) ;
			
			Audio audio = audioRep.save(new Audio(NOM_AUDIO, QUANTITE_AUDIO, ETAT_AUDIO, DATE_CREATION_AUDIO, DESCRIPTION_AUDIO, NOM_PHOTO_AUDIO, employ,CHANTEUR,
					DATE_SORTIE_AUDIO, STUDIO_AUDIO));
			
		}

		
	}
	
	
	@Test
	public void findByName_success() {
		List<Audio> entities = (List<Audio>) audioRep.findByName(NOM_AUDIO);
		assertNotNull(entities);
		assertEquals(entities.size(), 1);
		final Audio expectedEntity = entities.get(0);
		assertEquals(expectedEntity.getNom(), NOM_AUDIO);

		LOGGER.info(">>>>> AudioRepository findByName_success OK");
	}
	
	

	@Test
	public void findByName_notFound() {
		List<Audio> entities = (List<Audio>) audioRep.findByName("grrrrr");
		assertNotNull(entities);
		assertEquals(entities.size(), 0);

		LOGGER.info(">>>>> AudioRepository findByName_notFound OK");
	}
	
	
	
	
}
