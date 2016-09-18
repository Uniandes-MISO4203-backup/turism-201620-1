/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.test.logic;

import co.edu.uniandes.csw.turism.api.ITaxLogic;
import co.edu.uniandes.csw.turism.ejbs.TaxLogic;
import co.edu.uniandes.csw.turism.entities.TripEntity;
import co.edu.uniandes.csw.turism.entities.CategoryEntity;
import co.edu.uniandes.csw.turism.entities.TaxEntity;
import co.edu.uniandes.csw.turism.entities.TripEntity;
import co.edu.uniandes.csw.turism.persistence.TaxPersistence;
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
public class TaxLogicTest {

    TripEntity fatherEntity;

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ITaxLogic taxLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<TaxEntity> data = new ArrayList<TaxEntity>();

    private List<TripEntity> agencyData = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TaxEntity.class.getPackage())
                .addPackage(TaxLogic.class.getPackage())
                .addPackage(ITaxLogic.class.getPackage())
                .addPackage(TaxPersistence.class.getPackage())
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
        em.createQuery("delete from TaxEntity").executeUpdate();
        em.createQuery("delete from AgencyEntity").executeUpdate();
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
            TaxEntity entity = factory.manufacturePojo(TaxEntity.class);
            entity.setTrip(fatherEntity);
            em.persist(entity);
            data.add(entity);
        }
    }
 

    /**
     * Prueba para consultar la lista de Taxs
     *
     * @generated
     */
    @Test
    public void getTaxesTest() {
        List<TaxEntity> list = taxLogic.getTaxes();
        Assert.assertEquals(data.size(), list.size());
        for (TaxEntity entity : list) {
            boolean found = false;
            for (TaxEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un Tax
     *
     * @generated
     */
    @Test
    public void getTaxTest() {
        TaxEntity entity = data.get(0);
        TaxEntity resultEntity = taxLogic.getTax(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getDescription(), resultEntity.getDescription());
        Assert.assertEquals(entity.getValue(), resultEntity.getValue());
    }

    /**
     * Prueba para eliminar un Tax
     *
     * @generated
     */
    @Test
    public void deleteTaxTest() {
        TaxEntity entity = data.get(1);
        taxLogic.deleteTax(entity.getId());
        TaxEntity deleted = em.find(TaxEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Tax
     *
     */
    @Test
    public void updateTaxTest() {
        TaxEntity entity = data.get(0);
        TaxEntity pojoEntity = factory.manufacturePojo(TaxEntity.class);

        pojoEntity.setId(entity.getId());

        taxLogic.updateTax(pojoEntity);

        TaxEntity resp = em.find(TaxEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getDescription(), resp.getDescription());
        Assert.assertEquals(pojoEntity.getValue(), resp.getValue());
    }


}
