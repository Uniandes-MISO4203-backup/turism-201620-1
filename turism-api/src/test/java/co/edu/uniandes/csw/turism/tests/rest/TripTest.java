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
import co.edu.uniandes.csw.turism.entities.TripEntity;
import co.edu.uniandes.csw.turism.entities.AgencyEntity;
import co.edu.uniandes.csw.turism.dtos.minimum.TripDTO;
import co.edu.uniandes.csw.turism.resources.TripResource;
import co.edu.uniandes.csw.turism.tests.Utils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.UserTransaction;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
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

/*
 * Testing URI: agencys/{tripsId: \\d+}/trips/
 */
@RunWith(Arquillian.class)
public class TripTest {

    private WebTarget target;
    private final String apiPath = Utils.apiPath;
    private final String username = Utils.username;
    private final String password = Utils.password;
    PodamFactory factory = new PodamFactoryImpl();

    private final int Ok = Status.OK.getStatusCode();
    private final int Created = Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();

    private final static List<TripEntity> oraculo = new ArrayList<>();

    private final String agencyPath = "agencys";
    private final String tripPath = "trips";

    AgencyEntity fatherAgencyEntity;

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
                .addPackage(TripResource.class.getPackage())
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
        em.createQuery("delete from TripEntity").executeUpdate();
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
        fatherAgencyEntity.setId(1L);
        em.persist(fatherAgencyEntity);

        for (int i = 0; i < 3; i++) {            
            TripEntity trip = factory.manufacturePojo(TripEntity.class);
            trip.setId(i + 1L);
            trip.setAgency(fatherAgencyEntity);
            em.persist(trip);
            oraculo.add(trip);
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
                .path(tripPath);
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
     * Prueba para crear un Trip
     *
     * @generated
     */
    @Test
    public void createTripTest() throws IOException {
        TripDTO trip = factory.manufacturePojo(TripDTO.class);
        Cookie cookieSessionId = login(username, password);

        Response response = target
            .request().cookie(cookieSessionId)
            .post(Entity.entity(trip, MediaType.APPLICATION_JSON));

        TripDTO  tripTest = (TripDTO) response.readEntity(TripDTO.class);

        Assert.assertEquals(Created, response.getStatus());

        Assert.assertEquals(trip.getName(), tripTest.getName());
        Assert.assertEquals(trip.getImage(), tripTest.getImage());
        Assert.assertEquals(trip.getPrice(), tripTest.getPrice());
        
        Assert.assertEquals(trip.getOrigin(), tripTest.getOrigin());
        Assert.assertEquals(trip.getDestination(), tripTest.getDestination());
        
        Assert.assertEquals(trip.getDuration(), tripTest.getDuration());
        Assert.assertEquals(trip.getQuota(), tripTest.getQuota());

        Assert.assertEquals(trip.getTransportType(), tripTest.getTransportType());
        Assert.assertEquals(trip.getSpecialRequirements(), tripTest.getSpecialRequirements());

        Assert.assertEquals(trip.getDailyDescription(), tripTest.getDailyDescription());
        Assert.assertEquals(trip.getIncludesDescription(), tripTest.getIncludesDescription());
       
        
        TripEntity entity = em.find(TripEntity.class, tripTest.getId());
        Assert.assertNotNull(entity);
    }

    /**
     * Prueba para consultar un Trip
     *
     * @generated
     */
    @Test
    public void getTripByIdTest() {
        Cookie cookieSessionId = login(username, password);

        TripDTO tripTest = target
            .path(oraculo.get(0).getId().toString())
            .request().cookie(cookieSessionId).get(TripDTO.class);
        
        Assert.assertEquals(tripTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(tripTest.getName(), oraculo.get(0).getName());
        Assert.assertEquals(tripTest.getImage(), oraculo.get(0).getImage());
        Assert.assertEquals(tripTest.getPrice(), oraculo.get(0).getPrice());
        
        Assert.assertEquals(tripTest.getOrigin(), oraculo.get(0).getOrigin());
        Assert.assertEquals(tripTest.getDestination(), oraculo.get(0).getDestination());
    
        Assert.assertEquals(tripTest.getDuration(), oraculo.get(0).getDuration());
        Assert.assertEquals(tripTest.getQuota(), oraculo.get(0).getQuota());
    
        Assert.assertEquals(tripTest.getTransportType(), oraculo.get(0).getTransportType());
        Assert.assertEquals(tripTest.getSpecialRequirements(), oraculo.get(0).getSpecialRequirements());
    
        Assert.assertEquals(tripTest.getDailyDescription(), oraculo.get(0).getDailyDescription());
        Assert.assertEquals(tripTest.getIncludesDescription(), oraculo.get(0).getIncludesDescription());
    
    
    
    
    }

    /**
     * Prueba para consultar la lista de Trips
     *
     * @generated
     */
    @Test
    public void listTripTest() throws IOException {
        Cookie cookieSessionId = login(username, password);

        Response response = target
            .request().cookie(cookieSessionId).get();

        String listTrip = response.readEntity(String.class);
        List<TripDTO> listTripTest = new ObjectMapper().readValue(listTrip, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(3, listTripTest.size());
    }

    /**
     * Prueba para actualizar un Trip
     *
     * @generated
     */
    @Test
    public void updateTripTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        TripDTO trip = new TripDTO(oraculo.get(0));

        TripDTO tripChanged = factory.manufacturePojo(TripDTO.class);

        trip.setName(tripChanged.getName());
        trip.setImage(tripChanged.getImage());
        trip.setPrice(tripChanged.getPrice());
        
        trip.setDuration(tripChanged.getDuration());
        trip.setQuota(tripChanged.getQuota());
        
        trip.setOrigin(tripChanged.getOrigin());
        trip.setDestination(tripChanged.getDestination());
        
        trip.setTransportType(tripChanged.getTransportType());
        trip.setSpecialRequirements(tripChanged.getSpecialRequirements());
        
        trip.setDailyDescription(tripChanged.getDailyDescription());
        trip.setIncludesDescription(tripChanged.getIncludesDescription());

        Response response = target
            .path(trip.getId().toString())
            .request().cookie(cookieSessionId)
            .put(Entity.entity(trip, MediaType.APPLICATION_JSON));

        TripDTO tripTest = (TripDTO) response.readEntity(TripDTO.class);

        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(trip.getName(), tripTest.getName());
        Assert.assertEquals(trip.getImage(), tripTest.getImage());
        Assert.assertEquals(trip.getPrice(), tripTest.getPrice());
        
        Assert.assertEquals(trip.getDuration(), tripTest.getDuration());
        Assert.assertEquals(trip.getQuota(), tripTest.getQuota());
        
        Assert.assertEquals(trip.getOrigin(), tripTest.getOrigin());
        Assert.assertEquals(trip.getDestination(), tripTest.getDestination());
        
        Assert.assertEquals(trip.getTransportType(), tripTest.getTransportType());
        Assert.assertEquals(trip.getSpecialRequirements(), tripTest.getSpecialRequirements());
        
        Assert.assertEquals(trip.getDailyDescription(), tripTest.getDailyDescription());
        Assert.assertEquals(trip.getIncludesDescription(), tripTest.getIncludesDescription());
        
    }

    /**
     * Prueba para eliminar un Trip
     *
     * @generated
     */
    @Test
    public void deleteTripTest() {
        Cookie cookieSessionId = login(username, password);
        TripDTO trip = new TripDTO(oraculo.get(0));
        Response response = target
            .path(trip.getId().toString())
            .request().cookie(cookieSessionId).delete();

        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
}
