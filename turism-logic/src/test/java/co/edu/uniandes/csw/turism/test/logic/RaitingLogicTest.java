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

import co.edu.uniandes.csw.turism.api.IRaitingLogic;
import co.edu.uniandes.csw.turism.ejbs.RaitingLogic;
import co.edu.uniandes.csw.turism.entities.TripEntity;
import co.edu.uniandes.csw.turism.entities.RaitingEntity;
import co.edu.uniandes.csw.turism.persistence.RaitingPersistence;
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
 * @author da.salinas3247
 */
@RunWith(Arquillian.class)
public class RaitingLogicTest {

    TripEntity fatherEntity;

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private IRaitingLogic raitingLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<RaitingEntity> data = new ArrayList<RaitingEntity>();

    private List<TripEntity> tripData = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RaitingEntity.class.getPackage())
                .addPackage(RaitingLogic.class.getPackage())
                .addPackage(IRaitingLogic.class.getPackage())
                .addPackage(RaitingPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     *
     * Prueba de capa logica
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
     * Prueba de capa logica
     */
    private void clearData() {
        em.createQuery("delete from RaitingEntity").executeUpdate();
        em.createQuery("delete from TripEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * Prueba de capa logica
     */
    private void insertData() {

        fatherEntity = factory.manufacturePojo(TripEntity.class);
        fatherEntity.setId(1L);
        em.persist(fatherEntity);
        for (int i = 0; i < 3; i++) {
            RaitingEntity entity = factory.manufacturePojo(RaitingEntity.class);
            entity.setTrip(fatherEntity);
            em.persist(entity);
            data.add(entity);
        }
    }
 

    /**
     * Prueba para consultar la lista de Raitings
     *
     * Prueba de capa logica
     */
    @Test
    public void getRaitingsTest() {
        List<RaitingEntity> list = raitingLogic.getRaitings();
        Assert.assertEquals(data.size(), list.size());
        for (RaitingEntity entity : list) {
            boolean found = false;
            for (RaitingEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un Raiting
     *
     * Prueba de capa logica
     */
    @Test
    public void getRaitingTest() {
        RaitingEntity entity = data.get(0);
        RaitingEntity resultEntity = raitingLogic.getRaiting(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getValue(), resultEntity.getValue());
        Assert.assertEquals(entity.getDate(), resultEntity.getDate());
        Assert.assertEquals(entity.getTextComment(), resultEntity.getTextComment());
    }

    /**
     * Prueba para eliminar un Raiting
     *
     * Prueba de capa logica
     */
    @Test
    public void deleteRaitingTest() {
        RaitingEntity entity = data.get(1);
        raitingLogic.deleteRaiting(entity.getId());
        RaitingEntity deleted = em.find(RaitingEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Raiting
     *
     */
    @Test
    public void updateRaitingTest() {
        RaitingEntity entity = data.get(0);
        RaitingEntity pojoEntity = factory.manufacturePojo(RaitingEntity.class);

        pojoEntity.setId(entity.getId());

        raitingLogic.updateRaiting(pojoEntity);

        RaitingEntity resp = em.find(RaitingEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getValue(), resp.getValue());
        Assert.assertEquals(pojoEntity.getDate(), resp.getDate());
        Assert.assertEquals(pojoEntity.getTextComment(), resp.getTextComment());
    }


}
