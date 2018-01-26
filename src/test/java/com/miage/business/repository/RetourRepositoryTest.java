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
import com.miage.dao.RetourRepository;
import com.miage.dao.ClientRepository;
import com.miage.dao.MediaRepository;
import com.miage.entities.Retour;
import com.miage.entities.Client;
import com.miage.entities.Media;

/**
 * @author HRezgui
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringBusinessConfigTest.class })
public class RetourRepositoryTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(RetourRepositoryTest.class);

	private static final Date DATE_OPERATION_Retour = new Date();
	
	@Autowired
	private RetourRepository RetourRep;
	
	@Autowired
	private ClientRepository clientRep;
	
	@Autowired
	private MediaRepository mediaRep;
	
	Client client;
	
	@Before
	public void initObjects() {
		
		if (CollectionUtils.isEmpty(RetourRep.findAll())) {
			
			client = clientRep.findOne((long) 4) ;
			Media media= mediaRep.findOne((long) 1);
			
			Retour Retour = RetourRep.save(new Retour(DATE_OPERATION_Retour,client,media));
			
		}

		
	}
	
	
	@Test
	public void findAll_success() {
		List<Retour> entities = (List<Retour>) RetourRep.consulterRetByClient(client);
		assertNotNull(entities);
		assertEquals(entities.size(), 1);
		final Retour expectedEntity = entities.get(0);
		assertEquals(expectedEntity.getClient(), client.getNom());

		LOGGER.info(">>>>> RetourRepository findByClient_success OK");
	}
	
	

	@Test
	public void findByName_notFound() {
		List<Retour> entities = (List<Retour>) RetourRep.consulterRetByClient(client);
		assertNotNull(entities);
		assertEquals(entities.size(), 0);

		LOGGER.info(">>>>> RetourRepository findByClient_notFound OK");
	}

	
}
