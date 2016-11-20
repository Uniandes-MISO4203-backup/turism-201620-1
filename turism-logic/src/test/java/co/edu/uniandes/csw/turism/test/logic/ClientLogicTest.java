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

import co.edu.uniandes.csw.turism.ejbs.ClientLogic;
import co.edu.uniandes.csw.turism.api.IClientLogic;
import co.edu.uniandes.csw.turism.entities.ClientEntity;
import co.edu.uniandes.csw.turism.persistence.ClientPersistence;
import java.util.ArrayList;
import java.util.List;

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
public class ClientLogicTest {

    /**
     * @generated
     */

    /**
     * @generated
     */
    private final PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private IClientLogic clientLogic;

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
    private final List<ClientEntity> data = new ArrayList<ClientEntity>();

    /**
     * @return 
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ClientEntity.class.getPackage())
                .addPackage(ClientLogic.class.getPackage())
                .addPackage(IClientLogic.class.getPackage())
                .addPackage(ClientPersistence.class.getPackage())
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
        em.createQuery("delete from ItemEntity").executeUpdate();
        em.createQuery("delete from ClientEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ClientEntity entity = factory.manufacturePojo(ClientEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un Client
     *
     * @generated
     */
    @Test
    public void createClientTest() {
        ClientEntity newEntity = factory.manufacturePojo(ClientEntity.class);
        ClientEntity result = clientLogic.createClient(newEntity);
        Assert.assertNotNull(result);
        ClientEntity entity = em.find(ClientEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getFirstName(), entity.getFirstName());
        Assert.assertEquals(newEntity.getMiddleName(), entity.getMiddleName());
        Assert.assertEquals(newEntity.getLastName(), entity.getLastName());                
        Assert.assertEquals(newEntity.getEmail(), entity.getEmail());
        Assert.assertEquals(newEntity.getHomeAddress(), entity.getHomeAddress());
        Assert.assertEquals(newEntity.getPhoneNumber(), entity.getPhoneNumber());
        Assert.assertEquals(newEntity.getImage(), entity.getImage());
    }

    /**
     * Prueba para consultar la lista de Clients
     *
     * @generated
     */
    @Test
    public void getClientsTest() {
        List<ClientEntity> list = clientLogic.getClients();
        Assert.assertEquals(data.size(), list.size());
        for (ClientEntity entity : list) {
            boolean found = false;
            for (ClientEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un Client
     *
     * @generated
     */
    @Test
    public void getClientTest() {
        ClientEntity entity = data.get(0);
        ClientEntity resultEntity = clientLogic.getClient(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getFirstName(), resultEntity.getFirstName());
        Assert.assertEquals(entity.getMiddleName(), resultEntity.getMiddleName());
        Assert.assertEquals(entity.getLastName(), resultEntity.getLastName());
        Assert.assertEquals(entity.getEmail(), resultEntity.getEmail());
        Assert.assertEquals(entity.getHomeAddress(), resultEntity.getHomeAddress());
        Assert.assertEquals(entity.getPhoneNumber(), resultEntity.getPhoneNumber());
        Assert.assertEquals(entity.getImage(), resultEntity.getImage());
    }

    /**
     * Prueba para eliminar un Client
     *
     * @generated
     */
    @Test
    public void deleteClientTest() {
        ClientEntity entity = data.get(1);
        clientLogic.deleteClient(entity.getId());
        ClientEntity deleted = em.find(ClientEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Client
     *
     * @generated
     */
    @Test
    public void updateClientTest() {
        ClientEntity entity = data.get(0);
        ClientEntity pojoEntity = factory.manufacturePojo(ClientEntity.class);

        pojoEntity.setId(entity.getId());

        clientLogic.updateClient(pojoEntity);

        ClientEntity resp = em.find(ClientEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getFirstName(), resp.getFirstName());
        Assert.assertEquals(pojoEntity.getMiddleName(), resp.getMiddleName());
        Assert.assertEquals(pojoEntity.getLastName(), resp.getLastName());        
        Assert.assertEquals(pojoEntity.getEmail(), resp.getEmail());
        Assert.assertEquals(pojoEntity.getHomeAddress(), resp.getHomeAddress());
        Assert.assertEquals(pojoEntity.getPhoneNumber(), resp.getPhoneNumber());
        Assert.assertEquals(pojoEntity.getImage(), resp.getImage());        
    }
}

