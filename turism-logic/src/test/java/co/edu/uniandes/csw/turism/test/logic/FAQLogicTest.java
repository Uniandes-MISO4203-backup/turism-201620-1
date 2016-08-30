/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.test.logic;

import co.edu.uniandes.csw.turism.api.IFAQLogic;
import co.edu.uniandes.csw.turism.ejbs.FAQLogic;
import co.edu.uniandes.csw.turism.entities.AgencyEntity;
import co.edu.uniandes.csw.turism.entities.CategoryEntity;
import co.edu.uniandes.csw.turism.entities.FAQEntity;
import co.edu.uniandes.csw.turism.persistence.FAQPersistence;
import java.util.ArrayList;
import java.util.List;
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
 * @author lm.ariza10
 */
@RunWith(Arquillian.class)
public class FAQLogicTest {

    AgencyEntity fatherEntity;

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private IFAQLogic faqLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<FAQEntity> data = new ArrayList<FAQEntity>();

    private List<AgencyEntity> agencyData = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FAQEntity.class.getPackage())
                .addPackage(FAQLogic.class.getPackage())
                .addPackage(IFAQLogic.class.getPackage())
                .addPackage(FAQPersistence.class.getPackage())
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
        em.createQuery("delete from FAQEntity").executeUpdate();
        em.createQuery("delete from AgencyEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {

        fatherEntity = factory.manufacturePojo(AgencyEntity.class);
        fatherEntity.setId(1L);
        em.persist(fatherEntity);
        for (int i = 0; i < 3; i++) {
            FAQEntity entity = factory.manufacturePojo(FAQEntity.class);
            entity.setAgency(fatherEntity);
            em.persist(entity);
            data.add(entity);
        }
    }
 

    /**
     * Prueba para consultar la lista de FAQs
     *
     * @generated
     */
    @Test
    public void getFAQsTest() {
        List<FAQEntity> list = faqLogic.getFAQs();
        Assert.assertEquals(data.size(), list.size());
        for (FAQEntity entity : list) {
            boolean found = false;
            for (FAQEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un FAQ
     *
     * @generated
     */
    @Test
    public void getFAQTest() {
        FAQEntity entity = data.get(0);
        FAQEntity resultEntity = faqLogic.getFAQ(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getQuestion(), resultEntity.getQuestion());
        Assert.assertEquals(entity.getResponse(), resultEntity.getResponse());
    }

    /**
     * Prueba para eliminar un FAQ
     *
     * @generated
     */
    @Test
    public void deleteFAQTest() {
        FAQEntity entity = data.get(1);
        faqLogic.deleteFAQ(entity.getId());
        FAQEntity deleted = em.find(FAQEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un FAQ
     *
     */
    @Test
    public void updateFAQTest() {
        FAQEntity entity = data.get(0);
        FAQEntity pojoEntity = factory.manufacturePojo(FAQEntity.class);

        pojoEntity.setId(entity.getId());

        faqLogic.updateFAQ(pojoEntity);

        FAQEntity resp = em.find(FAQEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getQuestion(), resp.getQuestion());
        Assert.assertEquals(pojoEntity.getResponse(), resp.getResponse());
    }


}
