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
public class FAQPersistenceTest {
     /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FAQEntity.class.getPackage())
                .addPackage(FAQPersistence.class.getPackage())
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
    private FAQPersistence faqPersistence;

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
        em.createQuery("delete from FAQEntity").executeUpdate();
        em.createQuery("delete from AgencyEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<FAQEntity> data = new ArrayList<FAQEntity>();

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
            FAQEntity entity = factory.manufacturePojo(FAQEntity.class);
            
            entity.setAgency(fatherEntity);
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un FAQ.
     *
     * @generated
     */
    @Test
    public void createFAQTest() {
		PodamFactory factory = new PodamFactoryImpl();
        FAQEntity newEntity = factory.manufacturePojo(FAQEntity.class);
        newEntity.setAgency(fatherEntity);
        FAQEntity result = faqPersistence.create(newEntity);

        Assert.assertNotNull(result);

        FAQEntity entity = em.find(FAQEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getQuestion(), entity.getQuestion());
        Assert.assertEquals(newEntity.getResponse(), entity.getResponse());
    }

    /**
     * Prueba para consultar un FAQ.
     *
     * @generated
     */
    @Test
    public void getFAQTest() {
        FAQEntity entity = data.get(0);
        FAQEntity newEntity = faqPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getQuestion(), newEntity.getQuestion());
        Assert.assertEquals(entity.getResponse(), newEntity.getResponse());
    }

    /**
     * Prueba para eliminar un FAQ.
     *
     * @generated
     */
    @Test
    public void deleteFAQTest() {
        FAQEntity entity = data.get(0);
        faqPersistence.delete(entity.getId());
        FAQEntity deleted = em.find(FAQEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un FAQ.
     *
     * @generated
     */
    @Test
    public void updateFAQTest() {
        FAQEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        FAQEntity newEntity = factory.manufacturePojo(FAQEntity.class);

        newEntity.setId(entity.getId());

        faqPersistence.update(newEntity);

        FAQEntity resp = em.find(FAQEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getQuestion(), resp.getQuestion());
        Assert.assertEquals(newEntity.getResponse(), resp.getResponse());
    }
    
}
