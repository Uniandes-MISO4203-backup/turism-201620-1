/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.test.persistence;

import co.edu.uniandes.csw.turism.entities.ContentEntity;
import co.edu.uniandes.csw.turism.entities.TripEntity;
import co.edu.uniandes.csw.turism.persistence.ContentPersistence;
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
 * @author dh.mahecha
 */
@RunWith(Arquillian.class)
public class ContentPersistenceTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ContentEntity.class.getPackage())
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
    private ContentPersistence contentPersistence;

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
        em.createQuery("delete from ContentEntity").executeUpdate();
        em.createQuery("delete from TripEntity").executeUpdate();
    }
    
    /**
     * @generated
     */
    private List<ContentEntity> data = new ArrayList<ContentEntity>();

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
            ContentEntity entity = factory.manufacturePojo(ContentEntity.class);
            entity.setTrip(fatherEntity);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un Content.
     *
     * @generated
     */
    @Test
    public void createContentTest() {
	PodamFactory factory = new PodamFactoryImpl();
        ContentEntity newEntity = factory.manufacturePojo(ContentEntity.class);
        newEntity.setTrip(fatherEntity);
        ContentEntity result = contentPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ContentEntity entity = em.find(ContentEntity.class, result.getId());

        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        String strNewEntityDate = sf.format(newEntity.getDate());
        String strEntityDate = sf.format(entity.getDate());
        
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getContentValue(), entity.getContentValue());
        
        try {
            Assert.assertEquals(sf.parse(strNewEntityDate),sf.parse(strEntityDate));
        } catch (ParseException ex) {
            Logger.getLogger(ContentPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
    
    /**
     * Prueba para consultar un Content.
     *
     * @generated
     */
    @Test
    public void getContentTest() {
        ContentEntity entity = data.get(0);
        ContentEntity newEntity = contentPersistence.find(entity.getId());

        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        String strNewEntityDate = sf.format(newEntity.getDate());
        String strEntityDate = sf.format(entity.getDate());        
        
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getContentValue(), newEntity.getContentValue());
        Assert.assertEquals(entity.getContentValue(), newEntity.getContentValue());

        try {
            Assert.assertEquals(sf.parse(strEntityDate),sf.parse(strNewEntityDate));
        } catch (ParseException ex) {
            Logger.getLogger(TripPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    /**
     * Prueba para eliminar un Content.
     *
     * @generated
     */
    @Test
    public void deleteContentTest() {
        ContentEntity entity = data.get(0);
        contentPersistence.delete(entity.getId());
        ContentEntity deleted = em.find(ContentEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar un Content.
     *
     * @generated
     */
    @Test
    public void updateContentTest() {
        ContentEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ContentEntity newEntity = factory.manufacturePojo(ContentEntity.class);
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = sf.format(newEntity.getDate());
        
        newEntity.setId(entity.getId());

        contentPersistence.update(newEntity);

        ContentEntity resp = em.find(ContentEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getContentValue(), resp.getContentValue());
        
        try {
            Assert.assertEquals(sf.parse(strDate),resp.getDate());
        } catch (ParseException ex) {
            Logger.getLogger(TripPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
