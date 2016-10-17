/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.test.persistence;

import co.edu.uniandes.csw.turism.entities.NewsEntity;
import co.edu.uniandes.csw.turism.entities.TripEntity;
import co.edu.uniandes.csw.turism.persistence.ContentPersistence;
import co.edu.uniandes.csw.turism.persistence.NewsPersistence;
import co.edu.uniandes.csw.turism.persistence.TripPersistence;
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
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author dp.espitia
 */
@RunWith(Arquillian.class)
public class NewsPersistenceTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(NewsEntity.class.getPackage())
                .addPackage(ContentPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    TripEntity fatherEntity;

    /**
     * @generated
     */
    @Inject
    private NewsPersistence newsPersistence;

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
        em.createQuery("delete from NewsEntity").executeUpdate();
        em.createQuery("delete from TripEntity").executeUpdate();
    }
    
    /**
     * @generated
     */
    private List<NewsEntity> data = new ArrayList<NewsEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
            fatherEntity = factory.manufacturePojo(TripEntity.class);
            fatherEntity.setId(1L);
            em.persist(fatherEntity);
        for (int i = 0; i < 3; i++) {
            NewsEntity entity = factory.manufacturePojo(NewsEntity.class);
            entity.setTrip(fatherEntity);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un News.
     *
     * @generated
     */
    @Test
    public void createNewsTest() {
	PodamFactory factory = new PodamFactoryImpl();
        NewsEntity newEntity = factory.manufacturePojo(NewsEntity.class);
        newEntity.setTrip(fatherEntity);
        NewsEntity result = newsPersistence.create(newEntity);

        Assert.assertNotNull(result);

        NewsEntity entity = em.find(NewsEntity.class, result.getId());

        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        String strNewEntityDate = sf.format(newEntity.getDate());
        String strEntityDate = sf.format(entity.getDate());
        
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getContent(), entity.getContent());
        
        try {
            Assert.assertEquals(sf.parse(strNewEntityDate),sf.parse(strEntityDate));
        } catch (ParseException ex) {
            Logger.getLogger(NewsPersistenceTest.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
    
    /**
     * Prueba para consultar un News.
     *
     * @generated
     */
    @Test
    public void getNewsTest() {
        NewsEntity entity = data.get(0);
        NewsEntity newEntity = newsPersistence.find(entity.getId());

        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        String strNewEntityDate = sf.format(newEntity.getDate());
        String strEntityDate = sf.format(entity.getDate());        
        
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getContent(), newEntity.getContent());

        try {
            Assert.assertEquals(sf.parse(strEntityDate),sf.parse(strNewEntityDate));
        } catch (ParseException ex) {
            Logger.getLogger(TripPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    /**
     * Prueba para eliminar un News.
     *
     * @generated
     */
    @Test
    public void deleteNewsTest() {
        NewsEntity entity = data.get(0);
        newsPersistence.delete(entity.getId());
        NewsEntity deleted = em.find(NewsEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar un News.
     *
     * @generated
     */
    @Test
    public void updateNewsTest() {
        NewsEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        NewsEntity newEntity = factory.manufacturePojo(NewsEntity.class);
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = sf.format(newEntity.getDate());
        
        newEntity.setId(entity.getId());

        newsPersistence.update(newEntity);

        NewsEntity resp = em.find(NewsEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getContent(), resp.getContent());
        
        try {
            Assert.assertEquals(sf.parse(strDate),resp.getDate());
        } catch (ParseException ex) {
            Logger.getLogger(TripPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
