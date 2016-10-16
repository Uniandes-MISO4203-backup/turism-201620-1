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
package co.edu.uniandes.csw.turism.tests.rest;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.turism.dtos.minimum.ContentDTO;
import co.edu.uniandes.csw.turism.dtos.minimum.RaitingDTO;
import co.edu.uniandes.csw.turism.entities.AgencyEntity;
import co.edu.uniandes.csw.turism.entities.ContentEntity;
import co.edu.uniandes.csw.turism.entities.RaitingEntity;
import co.edu.uniandes.csw.turism.entities.TripEntity;
import co.edu.uniandes.csw.turism.resources.ContentResource;
import co.edu.uniandes.csw.turism.resources.RaitingResource;
import co.edu.uniandes.csw.turism.tests.Utils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.lang.time.DateUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Testing ContentResource
 * @author dh.mahecha
 */

@RunWith(Arquillian.class)
public class ContentTest {
    private WebTarget target;
    private final String apiPath = Utils.apiPath;
    private final String username = Utils.username;
    private final String password = Utils.password;
    PodamFactory factory = new PodamFactoryImpl();

    private final int Ok = Response.Status.OK.getStatusCode();
    private final int Created = Response.Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Response.Status.NO_CONTENT.getStatusCode();

    private final static List<ContentEntity> oraculo = new ArrayList<>();


    
    private final String agencyPath = "agencys";
    private final String tripPath = "trips";
    private final String contentPath = "contents";

    private AgencyEntity fatherAgencyEntity;
    private TripEntity fatherTripEntity;

    @ArquillianResource
    private URL deploymentURL;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                // Se agrega las dependencias
                .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
                        .importRuntimeDependencies().resolve()
                        .withTransitivity().asFile())
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(ContentResource.class.getPackage())
                // El archivo que contiene la configuracion a la base de datos.
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo beans.xml es necesario para injeccion de dependencias.
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                // El archivo shiro.ini es necesario para injeccion de dependencias
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/shiro.ini"))
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));
    }

    private WebTarget createWebTarget() {
        return ClientBuilder.newClient().target(deploymentURL.toString()).path(apiPath);
    }

    @PersistenceContext(unitName = "TurismPU")
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private void clearData() {
        List<TripEntity> records = em.createQuery("SELECT u FROM TripEntity u").getResultList();
        for (TripEntity record : records) {
            em.remove(record);
        }
        em.createQuery("delete from ContentEntity").executeUpdate();
        em.createQuery("delete from AgencyEntity").executeUpdate();
        oraculo.clear();
    }

   /**
     * Datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    public void insertData() {
           fatherAgencyEntity = factory.manufacturePojo(AgencyEntity.class);
            em.persist(fatherAgencyEntity);
            fatherTripEntity = factory.manufacturePojo(TripEntity.class);
            fatherTripEntity.setAgency(fatherAgencyEntity);
            em.persist(fatherTripEntity);

            for (int i = 0; i < 3; i++) {
                ContentEntity content = factory.manufacturePojo(ContentEntity.class);
                em.persist(content);
                if(i<2){                
                    fatherTripEntity.getContents().add(content);
                }
                oraculo.add(content);
            }
    }

    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void setUpTest() {
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
        target = createWebTarget()
                .path(agencyPath)
                .path(fatherAgencyEntity.getId().toString())
                .path(tripPath)
                .path(fatherTripEntity.getId().toString())
                .path(contentPath);
    }

    /**
     * Login para poder consultar los diferentes servicios
     *
     * @param username Nombre de usuario
     * @param password Clave del usuario
     * @return Cookie con información de la sesión del usuario
     * @generated
     */
    public Cookie login(String username, String password) {
        UserDTO user = new UserDTO();
        user.setUserName(username);
        user.setPassword(password);
        user.setRememberMe(true);
        Response response = createWebTarget().path("users").path("login").request()
                .post(Entity.entity(user, MediaType.APPLICATION_JSON));
        if (response.getStatus() == Ok) {
            return response.getCookies().get(JWT.cookieName);
        } else {
            return null;
        }
    }

    /**
     * Prueba para crear un Content
     *
     * @generated
     */
    @Test
    public void createContentTest() throws IOException {
        ContentDTO content = factory.manufacturePojo(ContentDTO.class);
        
        //Accuracy of dates is untiil day of month 
        Date dateTruncated = DateUtils.truncate(content.getDate(),Calendar.DAY_OF_MONTH);
        content.setDate(dateTruncated);
        Cookie cookieSessionId = login(username, password);

        Response response = target
            .request().cookie(cookieSessionId)
            .post(Entity.entity(content, MediaType.APPLICATION_JSON));

        ContentDTO  contentTest = (ContentDTO) response.readEntity(ContentDTO.class);

        Assert.assertEquals(Created, response.getStatus());

        Assert.assertEquals(content.getName(), contentTest.getName());
        Assert.assertEquals(content.getDate(), contentTest.getDate());
        Assert.assertEquals(content.getContentValue(), contentTest.getContentValue()); 

        ContentEntity entity = em.find(ContentEntity.class, contentTest.getId());
        Assert.assertNotNull(entity);
    }

    /**
     * Prueba para consultar un Content
     *
     * @generated
     */
    @Test
    public void getContentByIdTest() {
        Cookie cookieSessionId = login(username, password);

        
        ContentDTO contentTest = target
            .path(oraculo.get(0).getId().toString())
            .request().cookie(cookieSessionId).get(ContentDTO.class);
        
        Date dateTruncated = DateUtils.truncate(oraculo.get(0).getDate(),Calendar.DAY_OF_MONTH);
 
        
        Assert.assertEquals(contentTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(contentTest.getName(), oraculo.get(0).getName());
        Assert.assertEquals(contentTest.getContentValue(), oraculo.get(0).getContentValue());
        Assert.assertEquals(contentTest.getDate(), dateTruncated);
    }

    /**
     * Prueba para consultar la lista de Contents
     *
     * @generated
     */
    @Test
    public void listContentTest() throws IOException {
        Cookie cookieSessionId = login(username, password);

        Response response = target
            .request().cookie(cookieSessionId).get();

        String listContent = response.readEntity(String.class);
        List<ContentDTO> listContentTest = new ObjectMapper().readValue(listContent, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(3, listContentTest.size());
    }

    /**
     * Prueba para actualizar un Content
     *
     * @generated
     */
    @Test
    public void updateContentTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        ContentDTO content = new ContentDTO(oraculo.get(0));

        ContentDTO contentChanged = factory.manufacturePojo(ContentDTO.class);

        content.setName(contentChanged.getName());
        content.setContentValue(contentChanged.getContentValue());
        //Accuracy of dates is untiil day of month 
        Date dateChangedTruncated = DateUtils.truncate(content.getDate(),Calendar.DAY_OF_MONTH);
        content.setDate(dateChangedTruncated);

        Response response = target
            .path(content.getId().toString())
            .request().cookie(cookieSessionId)
            .put(Entity.entity(content, MediaType.APPLICATION_JSON));

        ContentDTO contentTest = (ContentDTO) response.readEntity(ContentDTO.class);

        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(content.getName(), contentTest.getName());
        Assert.assertEquals(content.getContentValue(), contentTest.getContentValue());
        Assert.assertEquals(content.getDate(), contentTest.getDate());
    }

    /**
     * Prueba para eliminar un Content
     *
     * @generated
     */
    @Test
    public void deleteContentTest() {
        Cookie cookieSessionId = login(username, password);
        ContentDTO content = new ContentDTO(oraculo.get(0));
        Response response = target
            .path(content.getId().toString())
            .request().cookie(cookieSessionId).delete();

        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
    
}
