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
import com.miage.dao.EmpruntRepository;
import com.miage.dao.MediaRepository;
import com.miage.dao.ClientRepository;
import com.miage.dao.EmployerRepository;
import com.miage.entities.Emprunt;
import com.miage.entities.Media;
import com.miage.entities.Client;
import com.miage.entities.Employer;

/**
 * @author HRezgui
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringBusinessConfigTest.class })
public class EmpruntRepositoryTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmpruntRepositoryTest.class);


	private static final Date DATE_OPERATION_Emprunt = new Date();
	private static final Date DATE_LIMITR_Emprunt = new Date();
	
	@Autowired
	private EmpruntRepository EmpruntRep;
	
	@Autowired
	private ClientRepository clientRep;
	
	@Autowired
	private MediaRepository mediaRep;
	
	Client client;
	
	@Before
	public void initObjects() {
		
		if (CollectionUtils.isEmpty(EmpruntRep.findAll())) {
			
			client = clientRep.findOne((long) 4) ;
			Media media= mediaRep.findOne((long) 1);
			
			Emprunt Emprunt = EmpruntRep.save(new Emprunt(DATE_OPERATION_Emprunt,DATE_LIMITR_Emprunt,client,media));
			
		}

		
	}
	
	
	@Test
	public void findAll_success() {
		List<Emprunt> entities = (List<Emprunt>) EmpruntRep.consulterEmpByClient(client);
		assertNotNull(entities);
		assertEquals(entities.size(), 1);
		final Emprunt expectedEntity = entities.get(0);
		assertEquals(expectedEntity.getClient(), client.getNom());

		LOGGER.info(">>>>> EmpruntRepository findByClient_success OK");
	}
	
	

	@Test
	public void findByName_notFound() {
		List<Emprunt> entities = (List<Emprunt>) EmpruntRep.consulterEmpByClient(client);
		assertNotNull(entities);
		assertEquals(entities.size(), 0);

		LOGGER.info(">>>>> EmpruntRepository findByClient_notFound OK");
	}
	
}
