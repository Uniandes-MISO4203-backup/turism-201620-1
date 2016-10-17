/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.test.logic;


import co.edu.uniandes.csw.turism.api.INewsLogic;
import co.edu.uniandes.csw.turism.ejbs.NewsLogic;
import co.edu.uniandes.csw.turism.entities.NewsEntity;
import co.edu.uniandes.csw.turism.entities.TripEntity;
import co.edu.uniandes.csw.turism.persistence.NewsPersistence;
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
public class NewsLogicTest {
    TripEntity fatherEntity;

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private INewsLogic newsLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<NewsEntity> data = new ArrayList<NewsEntity>();

    private List<TripEntity> tripData = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(NewsEntity.class.getPackage())
                .addPackage(NewsLogic.class.getPackage())
                .addPackage(INewsLogic.class.getPackage())
                .addPackage(NewsPersistence.class.getPackage())
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
        em.createQuery("delete from NewsEntity").executeUpdate();
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
            NewsEntity entity = factory.manufacturePojo(NewsEntity.class);
            entity.setTrip(fatherEntity);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para consultar la lista de Newss
     *
     * @generated
     */
    @Test
    public void getAllNewsTest() {
        List<NewsEntity> list = newsLogic.getAllNews();
        Assert.assertEquals(data.size(), list.size());
        for (NewsEntity entity : list) {
            boolean found = false;
            for (NewsEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    

    
    /**
     * Prueba para consultar un News
     *
     * @generated
     */
    @Test
    public void getNewsTest() {
        NewsEntity entity = data.get(0);
        NewsEntity resultEntity = newsLogic.getNews(entity.getId());
        
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
        Assert.assertEquals(entity.getContent(), resultEntity.getContent());
    }    
    
   /**
     * Prueba para eliminar un news
     *
     * @generated
     */
    @Test
    public void deleteNewsTest() {
        NewsEntity entity = data.get(1);
        newsLogic.deleteNews(entity.getId());
        NewsEntity deleted = em.find(NewsEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
   
    /**
     * Prueba para actualizar un Cotent
     *
     */
    @Test
    public void updateTaxTest() {
        NewsEntity entity = data.get(0);
        NewsEntity pojoEntity = factory.manufacturePojo(NewsEntity.class);

        pojoEntity.setId(entity.getId());

        newsLogic.updateNews(pojoEntity);

        NewsEntity resp = em.find(NewsEntity.class, entity.getId());
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
        
        Assert.assertEquals(pojoEntity.getContent(), resp.getContent());        
    }
}
