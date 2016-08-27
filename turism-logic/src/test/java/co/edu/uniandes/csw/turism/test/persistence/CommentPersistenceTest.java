/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.test.persistence;

import co.edu.uniandes.csw.turism.entities.CommentEntity;
import co.edu.uniandes.csw.turism.entities.CommentEntity;
import co.edu.uniandes.csw.turism.persistence.AgencyPersistence;
import co.edu.uniandes.csw.turism.persistence.CommentPersistence;
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
 * @author da.prieto1
 */
@RunWith(Arquillian.class)
public class CommentPersistenceTest {
    
     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CommentEntity.class.getPackage())
                .addPackage(AgencyPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private CommentPersistence commentPersistence;
    
    @PersistenceContext
    private EntityManager em;

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
     */
    private void clearData() {
        em.createQuery("delete from TripEntity").executeUpdate();
        em.createQuery("delete from ClientEntity").executeUpdate();
        em.createQuery("delete from CommentEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<CommentEntity> data = new ArrayList<CommentEntity>();
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CommentEntity entity = factory.manufacturePojo(CommentEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un Comment.
     *
     * @generated
     */
    @Test
    public void createCommentTest() {
        PodamFactory factory = new PodamFactoryImpl();
        CommentEntity newEntity = factory.manufacturePojo(CommentEntity.class);
        CommentEntity result = commentPersistence.create(newEntity);

        Assert.assertNotNull(result);

        CommentEntity entity = em.find(CommentEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
    /**
     * Prueba para consultar la lista de Comments.
     *
     * @generated
     */
    @Test
    public void getCommentsTest() {
        List<CommentEntity> list = commentPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CommentEntity ent : list) {
            boolean found = false;
            for (CommentEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar un Comment.
     *
     * @generated
     */
    @Test
    public void getCommentTest() {
        CommentEntity entity = data.get(0);
        CommentEntity newEntity = commentPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Prueba para eliminar un Comment.
     *
     * @generated
     */
    @Test
    public void deleteCommentTest() {
        CommentEntity entity = data.get(0);
        commentPersistence.delete(entity.getId());
        CommentEntity deleted = em.find(CommentEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Comment.
     *
     * @generated
     */
    @Test
    public void updateCommentTest() {
        CommentEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CommentEntity newEntity = factory.manufacturePojo(CommentEntity.class);

        newEntity.setId(entity.getId());

        commentPersistence.update(newEntity);

        CommentEntity resp = em.find(CommentEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
    
}
