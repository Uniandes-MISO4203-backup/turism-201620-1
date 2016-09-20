/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.csw.turism.test.persistence;
import co.edu.uniandes.csw.turism.entities.AgencyEntity;
import co.edu.uniandes.csw.turism.entities.TripEntity;
import co.edu.uniandes.csw.turism.persistence.TripPersistence;
import co.edu.uniandes.csw.turism.test.logic.TripLogicTest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class TripPersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TripEntity.class.getPackage())
                .addPackage(TripPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    AgencyEntity fatherEntity;

    /**
     * @generated
     */
    @Inject
    private TripPersistence tripPersistence;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    UserTransaction utx;

    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     * @generated
     */
    private void clearData() {
        em.createQuery("delete from TripEntity").executeUpdate();
        em.createQuery("delete from AgencyEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<TripEntity> data = new ArrayList<TripEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
            fatherEntity = factory.manufacturePojo(AgencyEntity.class);
            fatherEntity.setId(1L);
            em.persist(fatherEntity);
        for (int i = 0; i < 3; i++) {
            TripEntity entity = factory.manufacturePojo(TripEntity.class);
            
            entity.setAgency(fatherEntity);
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un Trip.
     *
     * @generated
     */
    @Test
    public void createTripTest() {
		PodamFactory factory = new PodamFactoryImpl();
        TripEntity newEntity = factory.manufacturePojo(TripEntity.class);
        newEntity.setAgency(fatherEntity);
        TripEntity result = tripPersistence.create(newEntity);

        Assert.assertNotNull(result);

        TripEntity entity = em.find(TripEntity.class, result.getId());
        
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        String strNewEntityDate = sf.format(newEntity.getDate());
        String strEntityDate = sf.format(entity.getDate());


        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getImage(), entity.getImage());
        Assert.assertEquals(newEntity.getPrice(), entity.getPrice());

        try {
            Assert.assertEquals(sf.parse(strNewEntityDate),sf.parse(strEntityDate));
        } catch (ParseException ex) {
            Logger.getLogger(TripPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertEquals(newEntity.getOrigin(), entity.getOrigin());
        Assert.assertEquals(newEntity.getDestination(), entity.getDestination());
        Assert.assertEquals(newEntity.getTransportType(), entity.getTransportType());
        Assert.assertEquals(newEntity.getSpecialRequirements(), entity.getSpecialRequirements());

    }

    /**
     * Prueba para consultar la lista de Trips.
     *
     * @generated
     */
    @Test
    public void getTripsTest() {
        List<TripEntity> list = tripPersistence.findAll(null, null, fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (TripEntity ent : list) {
            boolean found = false;
            for (TripEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Trip.
     *
     * @generated
     */
    @Test
    public void getTripTest() {
        TripEntity entity = data.get(0);
        TripEntity newEntity = tripPersistence.find(entity.getAgency().getId(), entity.getId());
        
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        String strNewEntityDate = sf.format(newEntity.getDate());
        String strEntityDate = sf.format(entity.getDate());
        
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getImage(), newEntity.getImage());
        Assert.assertEquals(entity.getPrice(), newEntity.getPrice());
       
        
        try {
            Assert.assertEquals(sf.parse(strEntityDate),sf.parse(strNewEntityDate));
        } catch (ParseException ex) {
            Logger.getLogger(TripPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertEquals(entity.getOrigin(), newEntity.getOrigin());
        Assert.assertEquals(entity.getDestination(), newEntity.getDestination());        
        Assert.assertEquals(entity.getTransportType(), newEntity.getTransportType());
        Assert.assertEquals(entity.getSpecialRequirements(), newEntity.getSpecialRequirements());

    }

    /**
     * Prueba para eliminar un Trip.
     *
     * @generated
     */
    @Test
    public void deleteTripTest() {
        TripEntity entity = data.get(0);
        tripPersistence.delete(entity.getId());
        TripEntity deleted = em.find(TripEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Trip.
     *
     * @generated
     */
    @Test
    public void updateTripTest() {
        TripEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TripEntity newEntity = factory.manufacturePojo(TripEntity.class);

        newEntity.setId(entity.getId());

        tripPersistence.update(newEntity);

        TripEntity resp = em.find(TripEntity.class, entity.getId());
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = sf.format(newEntity.getDate());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getImage(), resp.getImage());
        Assert.assertEquals(newEntity.getPrice(), resp.getPrice());

        try {
            Assert.assertEquals(sf.parse(strDate),resp.getDate());
        } catch (ParseException ex) {
            Logger.getLogger(TripPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertEquals(newEntity.getOrigin(), resp.getOrigin());
        Assert.assertEquals(newEntity.getDestination(), resp.getDestination());        
        Assert.assertEquals(newEntity.getTransportType(), resp.getTransportType());
        Assert.assertEquals(newEntity.getSpecialRequirements(), resp.getSpecialRequirements());
    }
}
