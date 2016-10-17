/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.tests.rest;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.turism.dtos.detail.AgencyDetailDTO;
import co.edu.uniandes.csw.turism.dtos.minimum.NewsDTO;
import co.edu.uniandes.csw.turism.entities.AgencyEntity;
import co.edu.uniandes.csw.turism.entities.NewsEntity;
import co.edu.uniandes.csw.turism.entities.TripEntity;
import co.edu.uniandes.csw.turism.resources.NewsResource;
import co.edu.uniandes.csw.turism.tests.Utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
 *
 * @author dp.espitia
 */

@RunWith(Arquillian.class)
public class NewsTest {
    private WebTarget target;
    private WebTarget targetCU;
    private final String apiPath = Utils.apiPath;
    private final String username = Utils.username;
    private final String password = Utils.password;
    PodamFactory factory = new PodamFactoryImpl();
    
    private final int Ok = Status.OK.getStatusCode();
    private final int Created = Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();
    private final static List<NewsEntity> oraculo = new ArrayList<>();
    private final String agencyPath = "agencys";
    private final String tripPath = "trips";
    private final String newsPath = "news";
    
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
                .addPackage(NewsResource.class.getPackage())
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
        em.createQuery("delete from NewsEntity").executeUpdate();
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
       System.out.println("DIANA-");
       System.out.println("ID:");
       System.out.println(fatherAgencyEntity.getId());
       System.out.println("NAME:");
       System.out.println(fatherAgencyEntity.getName());
       
       em.persist(fatherAgencyEntity);
       fatherTripEntity = factory.manufacturePojo(TripEntity.class);
       fatherTripEntity.setAgency(fatherAgencyEntity);
       em.persist(fatherTripEntity);

            for (int i = 0; i < 3; i++) {
                NewsEntity news = factory.manufacturePojo(NewsEntity.class);
                em.persist(news);
                if(i<2){                
                    fatherTripEntity.getNews().add(news);
                }
                oraculo.add(news);
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
                .path(newsPath);


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
     * Prueba para crear un News
     *
     * @throws java.io.IOException
     * @generated
     */
    public void createNewsTest() throws IOException {
        NewsDTO news = factory.manufacturePojo(NewsDTO.class);
        //Accuracy of dates is untiil day of month 
        Date dateTruncated = DateUtils.truncate(news.getDate(),Calendar.DAY_OF_MONTH);
        news.setDate(dateTruncated);
        Cookie cookieSessionId = login(username, password);

        Response response = target
            .request().cookie(cookieSessionId)
            .post(Entity.entity(news, MediaType.APPLICATION_JSON));

        NewsDTO  newsTest = (NewsDTO) response.readEntity(NewsDTO.class);

        Assert.assertEquals(Created, response.getStatus());

        Assert.assertEquals(news.getName(), newsTest.getName());
        Assert.assertEquals(news.getContent(), newsTest.getContent());
        Assert.assertEquals(news.getDate(), newsTest.getDate());

        NewsEntity entity = em.find(NewsEntity.class, newsTest.getId());
        Assert.assertNotNull(entity);
    }

    /**
     * Prueba para consultar un FAQ
     *
     * @generated
     */
    @Test
    public void getNewsByIdTest() {
        Cookie cookieSessionId = login(username, password);
        
        NewsDTO newsTest = target
            .path(oraculo.get(0).getId().toString())
            .request().cookie(cookieSessionId).get(NewsDTO.class);
        
        Date dateTruncated = DateUtils.truncate(oraculo.get(0).getDate(),Calendar.DAY_OF_MONTH);
        
        Assert.assertEquals(newsTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(newsTest.getName(), oraculo.get(0).getName());
        Assert.assertEquals(newsTest.getContent(), oraculo.get(0).getContent());
        Assert.assertEquals(newsTest.getDate(), dateTruncated);
    }

    /**
     * Prueba para consultar la lista de FAQs
     *
     * @generated
     */
    @Test
    public void listNewsTest() throws IOException {
        Cookie cookieSessionId = login(username, password);

        Response response = target
            .request().cookie(cookieSessionId).get();

        String listNews = response.readEntity(String.class);
        List<NewsDTO> listNewsTest = new ObjectMapper().readValue(listNews, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(3, listNewsTest.size());
    }

    /**
     * Prueba para actualizar un News
     *
     * @generated
     */
    @Test
    public void updateNewsTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        NewsDTO news = new NewsDTO(oraculo.get(0));
        NewsDTO newsChanged = factory.manufacturePojo(NewsDTO.class);

        newsChanged.setName(newsChanged.getName());
        newsChanged.setContent(newsChanged.getContent());
        //Accuracy of dates is untiil day of month 
        Date dateChangeTruncated = DateUtils.truncate(news.getDate(),Calendar.DAY_OF_MONTH);
        news.setDate(dateChangeTruncated);

        Response response = target
            .path(news.getId().toString())
            .request().cookie(cookieSessionId)
            .put(Entity.entity(news, MediaType.APPLICATION_JSON));

        NewsDTO newsTest = (NewsDTO) response.readEntity(NewsDTO.class);

        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(news.getName(), newsTest.getName());
        Assert.assertEquals(news.getContent(), newsTest.getContent());
        Assert.assertEquals(news.getDate(), newsTest.getDate());
    }

    /**
     * Prueba para eliminar un FAQ
     *
     * @generated
     */
    @Test
    public void deleteNewsTest() {
        Cookie cookieSessionId = login(username, password);
        NewsDTO news = new NewsDTO(oraculo.get(0));
        Response response = target
            .path(news.getId().toString())
            .request().cookie(cookieSessionId).delete();

        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
}
