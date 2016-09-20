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
package co.edu.uniandes.csw.turism.test.logic;

import co.edu.uniandes.csw.turism.ejbs.TripLogic;
import co.edu.uniandes.csw.turism.api.ITripLogic;
import co.edu.uniandes.csw.turism.entities.TripEntity;
import co.edu.uniandes.csw.turism.entities.AgencyEntity;
import co.edu.uniandes.csw.turism.persistence.TripPersistence;
import co.edu.uniandes.csw.turism.entities.AgencyEntity;
import co.edu.uniandes.csw.turism.entities.CategoryEntity;
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
public class TripLogicTest {

    /**
     * @generated
     */
    AgencyEntity fatherEntity;

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private ITripLogic tripLogic;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    private UserTransaction utx;

    /**
     * @generated
     */
    private List<TripEntity> data = new ArrayList<TripEntity>();

    /**
     * @generated
     */
    private List<AgencyEntity> agencyData = new ArrayList<>();

    /**
     * @generated
     */
    private List<CategoryEntity> categoryData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TripEntity.class.getPackage())
                .addPackage(TripLogic.class.getPackage())
                .addPackage(ITripLogic.class.getPackage())
                .addPackage(TripPersistence.class.getPackage())
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
        em.createQuery("delete from TripEntity").executeUpdate();
        em.createQuery("delete from AgencyEntity").executeUpdate();
        em.createQuery("delete from CategoryEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            CategoryEntity category = factory.manufacturePojo(CategoryEntity.class);
            em.persist(category);
            categoryData.add(category);
        }

        fatherEntity = factory.manufacturePojo(AgencyEntity.class);
        fatherEntity.setId(1L);
        em.persist(fatherEntity);
        for (int i = 0; i < 3; i++) {
            TripEntity entity = factory.manufacturePojo(TripEntity.class);
            entity.setAgency(fatherEntity);

            entity.getCategory().add(categoryData.get(0));

            em.persist(entity);
            data.add(entity);
        }
    }
   /**
     * Prueba para crear un Trip
     *
     * @generated
     */
    @Test
    public void createTripTest() {
        TripEntity newEntity = factory.manufacturePojo(TripEntity.class);
        TripEntity result = tripLogic.createTrip(fatherEntity.getId(), newEntity);
        Assert.assertNotNull(result);
        TripEntity entity = em.find(TripEntity.class, result.getId());
        
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        String strEntityDate = sf.format(newEntity.getDate());
        String strNewEntityDate = sf.format(newEntity.getDate());
        
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getImage(), entity.getImage());
        Assert.assertEquals(newEntity.getPrice(), entity.getPrice());
        try {
            Assert.assertEquals(sf.parse(strEntityDate),sf.parse(strNewEntityDate));
        } catch (ParseException ex) {
            Logger.getLogger(TripLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertEquals(newEntity.getOrigin(), entity.getOrigin());
        Assert.assertEquals(newEntity.getDestination(), entity.getDestination());
        Assert.assertEquals(newEntity.getTransportType(), entity.getTransportType());
        Assert.assertEquals(newEntity.getSpecialRequirements(), entity.getSpecialRequirements());
        Assert.assertEquals(newEntity.getQuota(), entity.getQuota());
        Assert.assertEquals(newEntity.getDuration(), entity.getDuration());

    }

    /**
     * Prueba para consultar la lista de Trips
     *
     * @generated
     */
    @Test
    public void getTripsTest() {
        List<TripEntity> list = tripLogic.getTrips(fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (TripEntity entity : list) {
            boolean found = false;
            for (TripEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un Trip
     *
     * @generated
     */
    @Test
    public void getTripTest() {
        TripEntity entity = data.get(0);
        TripEntity resultEntity = tripLogic.getTrip(entity.getId());

        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        String strEntityDate = sf.format(entity.getDate());
        String strResultEntDate = sf.format(resultEntity.getDate());
        
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getImage(), resultEntity.getImage());
        Assert.assertEquals(entity.getPrice(), resultEntity.getPrice());
        
        try {
            Assert.assertEquals(sf.parse(strEntityDate),sf.parse(strResultEntDate));
        } catch (ParseException ex) {
            Logger.getLogger(TripLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertEquals(entity.getOrigin(), resultEntity.getOrigin());
        Assert.assertEquals(entity.getDestination(), resultEntity.getDestination());
        Assert.assertEquals(entity.getTransportType(), resultEntity.getTransportType());
        Assert.assertEquals(entity.getSpecialRequirements(), resultEntity.getSpecialRequirements());

        Assert.assertEquals(entity.getQuota(),resultEntity.getQuota());
        Assert.assertEquals(entity.getDuration(),resultEntity.getDuration());

    }

    /**
     * Prueba para eliminar un Trip
     *
     * @generated
     */
    @Test
    public void deleteTripTest() {
        TripEntity entity = data.get(1);
        tripLogic.deleteTrip(entity.getId());
        TripEntity deleted = em.find(TripEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Trip
     *
     * @generated
     */
    @Test
    public void updateTripTest() {
        TripEntity entity = data.get(0);
        TripEntity pojoEntity = factory.manufacturePojo(TripEntity.class);

        pojoEntity.setId(entity.getId());

        tripLogic.updateTrip(fatherEntity.getId(), pojoEntity);

        TripEntity resp = em.find(TripEntity.class, entity.getId());
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        String strPojoDate = sf.format(pojoEntity.getDate());
        String strRespDate = sf.format(resp.getDate());
        
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getImage(), resp.getImage());
        Assert.assertEquals(pojoEntity.getPrice(), resp.getPrice());
        
        try {
            Assert.assertEquals(sf.parse(strPojoDate),sf.parse(strRespDate));
        } catch (ParseException ex) {
            Logger.getLogger(TripLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        Assert.assertEquals(pojoEntity.getOrigin(), resp.getOrigin());
        Assert.assertEquals(pojoEntity.getDestination(), resp.getDestination());

        Assert.assertEquals(pojoEntity.getTransportType(), resp.getTransportType());
        Assert.assertEquals(pojoEntity.getSpecialRequirements(), resp.getSpecialRequirements());
        Assert.assertEquals(pojoEntity.getQuota(), resp.getQuota());
        Assert.assertEquals(pojoEntity.getDuration(),resp.getDuration());
    }

    /**
     * Prueba para obtener una instancia de Category asociada a una instancia Trip
     *
     * @generated
     */
    @Test
    public void getCategoryTest() {
        TripEntity entity = data.get(0);
        CategoryEntity categoryEntity = categoryData.get(0);
        CategoryEntity response = tripLogic.getCategory(entity.getId(), categoryEntity.getId());

        Assert.assertEquals(categoryEntity.getId(), response.getId());
        Assert.assertEquals(categoryEntity.getName(), response.getName());
    }

    /**
     * Prueba para obtener una colección de instancias de Category asociadas a una instancia Trip
     *
     * @generated
     */
    @Test
    public void listCategoryTest() {
        List<CategoryEntity> list = tripLogic.listCategory(data.get(0).getId());
        Assert.assertEquals(1, list.size());
    }

    /**
     *Prueba para asociar un Category existente a un Trip
     *
     * @generated
     */
    @Test
    public void addCategoryTest() {
        TripEntity entity = data.get(0);
        CategoryEntity categoryEntity = categoryData.get(1);
        CategoryEntity response = tripLogic.addCategory(entity.getId(), categoryEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(categoryEntity.getId(), response.getId());
    }

    /**
     * Prueba para remplazar las instancias de Category asociadas a una instancia de Trip
     *
     * @generated
     */
    @Test
    public void replaceCategoryTest() {
        TripEntity entity = data.get(0);
        List<CategoryEntity> list = categoryData.subList(1, 3);
        tripLogic.replaceCategory(entity.getId(), list);

        entity = tripLogic.getTrip(entity.getId());
        Assert.assertFalse(entity.getCategory().contains(categoryData.get(0)));
        Assert.assertTrue(entity.getCategory().contains(categoryData.get(1)));
        Assert.assertTrue(entity.getCategory().contains(categoryData.get(2)));
    }

    /**
     * Prueba para desasociar un Category existente de un Trip existente
     *
     * @generated
     */
    @Test
    public void removeCategoryTest() {
        tripLogic.removeCategory(data.get(0).getId(), categoryData.get(0).getId());
        CategoryEntity response = tripLogic.getCategory(data.get(0).getId(), categoryData.get(0).getId());
        Assert.assertNull(response);
    }
}

