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
import co.edu.uniandes.csw.turism.dtos.minimum.RaitingDTO;
import co.edu.uniandes.csw.turism.entities.RaitingEntity;
import co.edu.uniandes.csw.turism.entities.TripEntity;
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
 * Testing RaitingResource
 * @author da.salinas3247
 */

@RunWith(Arquillian.class)
public class RaitingTest {
    private WebTarget target;
    private final String apiPath = Utils.apiPath;
    private final String username = Utils.username;
    private final String password = Utils.password;
    PodamFactory factory = new PodamFactoryImpl();

    private final int Ok = Response.Status.OK.getStatusCode();
    private final int Created = Response.Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Response.Status.NO_CONTENT.getStatusCode();

    private final static List<RaitingEntity> oraculo = new ArrayList<>();

    private final String clientPath = "clients";
    private final String itemPath = "wishList";
    private final String tripPath = "trips";
    private final String raitingPath = "raitings";

    TripEntity fatherTripEntity;

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
                .addPackage(RaitingResource.class.getPackage())
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
        em.createQuery("delete from RaitingEntity").executeUpdate();
        em.createQuery("delete from TripEntity").executeUpdate();
        oraculo.clear();
    }

   /**
     * Datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    public void insertData() {
        fatherTripEntity = factory.manufacturePojo(TripEntity.class);
        fatherTripEntity.setId(1L);
        em.persist(fatherTripEntity);

        for (int i = 0; i < 3; i++) {            
            RaitingEntity raiting = factory.manufacturePojo(RaitingEntity.class);
            raiting.setId(i + 1L);
            raiting.setTrip(fatherTripEntity);
            em.persist(raiting);
            oraculo.add(raiting);
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
                //.path(tripPath)
                //.path(fatherTripEntity.getId().toString())
                .path(raitingPath);
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
     * Prueba para crear un Raiting
     *
     * @generated
     */
    @Test
    public void createRaitingTest() throws IOException {
        RaitingDTO raiting = factory.manufacturePojo(RaitingDTO.class);
        //Accuracy of dates is untiil day of month 
        Date dateTruncated = DateUtils.truncate(raiting.getDate(),Calendar.DAY_OF_MONTH);
        raiting.setDate(dateTruncated);
        Cookie cookieSessionId = login(username, password);

        Response response = target
            .request().cookie(cookieSessionId)
            .post(Entity.entity(raiting, MediaType.APPLICATION_JSON));

        RaitingDTO  raitingTest = (RaitingDTO) response.readEntity(RaitingDTO.class);

        Assert.assertEquals(Created, response.getStatus());

        Assert.assertEquals(raiting.getName(), raitingTest.getName());
        Assert.assertEquals(raiting.getValue(), raitingTest.getValue());
        Assert.assertEquals(raiting.getDate(), raitingTest.getDate());

        RaitingEntity entity = em.find(RaitingEntity.class, raitingTest.getId());
        Assert.assertNotNull(entity);
    }

    /**
     * Prueba para consultar un Raiting
     *
     * @generated
     */
    @Test
    public void getRaitingByIdTest() {
        Cookie cookieSessionId = login(username, password);

        RaitingDTO raitingTest = target
            .path(oraculo.get(0).getId().toString())
            .request().cookie(cookieSessionId).get(RaitingDTO.class);
        
        Assert.assertEquals(raitingTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(raitingTest.getName(), oraculo.get(0).getName());
        Assert.assertEquals(raitingTest.getValue(), oraculo.get(0).getValue());
        Assert.assertEquals(raitingTest.getDate(), oraculo.get(0).getDate());
    }

    /**
     * Prueba para consultar la lista de Raitings
     *
     * @generated
     */
    @Test
    public void listRaitingTest() throws IOException {
        Cookie cookieSessionId = login(username, password);

        Response response = target
            .request().cookie(cookieSessionId).get();

        String listRaiting = response.readEntity(String.class);
        List<RaitingDTO> listRaitingTest = new ObjectMapper().readValue(listRaiting, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(3, listRaitingTest.size());
    }

    /**
     * Prueba para actualizar un Raiting
     *
     * @generated
     */
    @Test
    public void updateRaitingTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        RaitingDTO raiting = new RaitingDTO(oraculo.get(0));

        RaitingDTO raitingChanged = factory.manufacturePojo(RaitingDTO.class);

        raiting.setName(raitingChanged.getName());
        raiting.setValue(raitingChanged.getValue());
        //Accuracy of dates is untiil day of month 
        Date dateChangedTruncated = DateUtils.truncate(raiting.getDate(),Calendar.DAY_OF_MONTH);
        raiting.setDate(dateChangedTruncated);

        Response response = target
            .path(raiting.getId().toString())
            .request().cookie(cookieSessionId)
            .put(Entity.entity(raiting, MediaType.APPLICATION_JSON));

        RaitingDTO raitingTest = (RaitingDTO) response.readEntity(RaitingDTO.class);

        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(raiting.getName(), raitingTest.getName());
        Assert.assertEquals(raiting.getValue(), raitingTest.getValue());
        Assert.assertEquals(raiting.getDate(), raitingTest.getDate());
    }

    /**
     * Prueba para eliminar un Raiting
     *
     * @generated
     */
    @Test
    public void deleteRaitingTest() {
        Cookie cookieSessionId = login(username, password);
        RaitingDTO raiting = new RaitingDTO(oraculo.get(0));
        Response response = target
            .path(raiting.getId().toString())
            .request().cookie(cookieSessionId).delete();

        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
    
}
