/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.test.persistence;

import co.edu.uniandes.csw.turism.entities.TripEntity;
import co.edu.uniandes.csw.turism.entities.TaxEntity;
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
public class TaxPersistenceTest {
     /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TaxEntity.class.getPackage())
                .addPackage(TaxPersistence.class.getPackage())
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
    private TaxPersistence taxPersistence;

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
        em.createQuery("delete from TaxEntity").executeUpdate();
        em.createQuery("delete from AgencyEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<TaxEntity> data = new ArrayList<TaxEntity>();

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
            TaxEntity entity = factory.manufacturePojo(TaxEntity.class);
            entity.setTrip(fatherEntity);
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un Tax.
     *
     * @generated
     */
    @Test
    public void createTaxTest() {
		PodamFactory factory = new PodamFactoryImpl();
        TaxEntity newEntity = factory.manufacturePojo(TaxEntity.class);
        newEntity.setTrip(fatherEntity);
        TaxEntity result = taxPersistence.create(newEntity);

        Assert.assertNotNull(result);

        TaxEntity entity = em.find(TaxEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getDescription(), entity.getDescription());
        Assert.assertEquals(newEntity.getValue(), entity.getValue());
    }

    /**
     * Prueba para consultar un Tax.
     *
     * @generated
     */
    @Test
    public void getTaxTest() {
        TaxEntity entity = data.get(0);
        TaxEntity newEntity = taxPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getDescription(), newEntity.getDescription());
        Assert.assertEquals(entity.getValue(), newEntity.getValue());
    }

    /**
     * Prueba para eliminar un Tax.
     *
     * @generated
     */
    @Test
    public void deleteTaxTest() {
        TaxEntity entity = data.get(0);
        taxPersistence.delete(entity.getId());
        TaxEntity deleted = em.find(TaxEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Tax.
     *
     * @generated
     */
    @Test
    public void updateTaxTest() {
        TaxEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TaxEntity newEntity = factory.manufacturePojo(TaxEntity.class);

        newEntity.setId(entity.getId());

        taxPersistence.update(newEntity);

        TaxEntity resp = em.find(TaxEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getDescription(), resp.getDescription());
        Assert.assertEquals(newEntity.getValue(), resp.getValue());
    }
    
}
