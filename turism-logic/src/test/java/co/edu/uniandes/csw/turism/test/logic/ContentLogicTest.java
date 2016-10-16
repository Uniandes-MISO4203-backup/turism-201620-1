/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.test.logic;

import co.edu.uniandes.csw.turism.api.IContentLogic;
import co.edu.uniandes.csw.turism.ejbs.ContentLogic;
import co.edu.uniandes.csw.turism.entities.ContentEntity;
import co.edu.uniandes.csw.turism.entities.TripEntity;
import co.edu.uniandes.csw.turism.persistence.ContentPersistence;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author dh.mahecha
 */
@RunWith(Arquillian.class)
public class ContentLogicTest {
    TripEntity fatherEntity;

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private IContentLogic contentLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ContentEntity> data = new ArrayList<ContentEntity>();

    private List<TripEntity> tripData = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ContentEntity.class.getPackage())
                .addPackage(ContentLogic.class.getPackage())
                .addPackage(IContentLogic.class.getPackage())
                .addPackage(ContentPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
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
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {

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
     * Prueba para consultar la lista de Contents
     *
     * @generated
     */
    @Test
    public void getContentsTest() {
        List<ContentEntity> list = contentLogic.getContents();
        Assert.assertEquals(data.size(), list.size());
        for (ContentEntity entity : list) {
            boolean found = false;
            for (ContentEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    

    
    /**
     * Prueba para consultar un Content
     *
     * @generated
     */
    @Test
    public void getContentTest() {
        ContentEntity entity = data.get(0);
        ContentEntity resultEntity = contentLogic.getContent(entity.getId());
        
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        String strEntityDate = sf.format(entity.getDate());
        String strResultEntDate = sf.format(resultEntity.getDate());
        
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        try {
            Assert.assertEquals(sf.parse(strEntityDate),sf.parse(strResultEntDate));
        } catch (ParseException ex) {
            Logger.getLogger(TripLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertEquals(entity.getContentValue(), resultEntity.getContentValue());
    }    
    
   /**
     * Prueba para eliminar un content
     *
     * @generated
     */
    @Test
    public void deleteContentTest() {
        ContentEntity entity = data.get(1);
        contentLogic.deleteContent(entity.getId());
        ContentEntity deleted = em.find(ContentEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
   
    /**
     * Prueba para actualizar un Cotent
     *
     */
    @Test
    public void updateTaxTest() {
        ContentEntity entity = data.get(0);
        ContentEntity pojoEntity = factory.manufacturePojo(ContentEntity.class);

        pojoEntity.setId(entity.getId());

        contentLogic.updateContent(pojoEntity);

        ContentEntity resp = em.find(ContentEntity.class, entity.getId());
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        String strPojoDate = sf.format(pojoEntity.getDate());
        String strRespDate = sf.format(resp.getDate());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        try {
            Assert.assertEquals(sf.parse(strPojoDate),sf.parse(strRespDate));
        } catch (ParseException ex) {
            Logger.getLogger(TripLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Assert.assertEquals(pojoEntity.getContentValue(), resp.getContentValue());        
    }
}
