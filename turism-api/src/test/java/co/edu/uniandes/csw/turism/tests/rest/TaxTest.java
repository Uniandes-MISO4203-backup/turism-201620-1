/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.tests.rest;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.turism.dtos.minimum.TaxDTO;
import co.edu.uniandes.csw.turism.entities.TripEntity;
import co.edu.uniandes.csw.turism.entities.TaxEntity;
import co.edu.uniandes.csw.turism.resources.TaxResource;
import co.edu.uniandes.csw.turism.tests.Utils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
 * @author lm.ariza10
 */

@RunWith(Arquillian.class)
public class TaxTest {
     private WebTarget target;
    private final String apiPath = Utils.apiPath;
    private final String username = Utils.username;
    private final String password = Utils.password;
    PodamFactory factory = new PodamFactoryImpl();

    private final int Ok = Response.Status.OK.getStatusCode();
    private final int Created = Response.Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Response.Status.NO_CONTENT.getStatusCode();

    private final static List<TaxEntity> oraculo = new ArrayList<>();

    private final String agencyPath = "agencys";
    private final String taxPath = "taxs";

    TripEntity fatherAgencyEntity;

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
                .addPackage(TaxResource.class.getPackage())
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
        em.createQuery("delete from TaxEntity").executeUpdate();
        em.createQuery("delete from AgencyEntity").executeUpdate();
        oraculo.clear();
    }

   /**
     * Datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    public void insertData() {
        fatherAgencyEntity = factory.manufacturePojo(TripEntity.class);
        fatherAgencyEntity.setId(1L);
        em.persist(fatherAgencyEntity);

        for (int i = 0; i < 3; i++) {            
            TaxEntity tax = factory.manufacturePojo(TaxEntity.class);
            tax.setId(i + 1L);
            tax.setTrip(fatherAgencyEntity);
            em.persist(tax);
            oraculo.add(tax);
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
                .path(taxPath);
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
     * Prueba para crear un Tax
     *
     * @generated
     */
    @Test
    public void createTaxTest() throws IOException {
        TaxDTO tax = factory.manufacturePojo(TaxDTO.class);
        Cookie cookieSessionId = login(username, password);

        Response response = target
            .request().cookie(cookieSessionId)
            .post(Entity.entity(tax, MediaType.APPLICATION_JSON));

        TaxDTO  taxTest = (TaxDTO) response.readEntity(TaxDTO.class);

        Assert.assertEquals(Created, response.getStatus());

        Assert.assertEquals(tax.getName(), taxTest.getName());
        Assert.assertEquals(tax.getDescription(), taxTest.getDescription());
        Assert.assertEquals(tax.getValue(), taxTest.getValue());

        TaxEntity entity = em.find(TaxEntity.class, taxTest.getId());
        Assert.assertNotNull(entity);
    }

    /**
     * Prueba para consultar un Tax
     *
     * @generated
     */
    @Test
    public void getTaxByIdTest() {
        Cookie cookieSessionId = login(username, password);

        TaxDTO taxTest = target
            .path(oraculo.get(0).getId().toString())
            .request().cookie(cookieSessionId).get(TaxDTO.class);
        
        Assert.assertEquals(taxTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(taxTest.getName(), oraculo.get(0).getName());
        Assert.assertEquals(taxTest.getDescription(), oraculo.get(0).getDescription());
        Assert.assertEquals(taxTest.getValue(), oraculo.get(0).getValue());
    }

    /**
     * Prueba para consultar la lista de Taxs
     *
     * @generated
     */
    @Test
    public void listTaxTest() throws IOException {
        Cookie cookieSessionId = login(username, password);

        Response response = target
            .request().cookie(cookieSessionId).get();

        String listTax = response.readEntity(String.class);
        List<TaxDTO> listTaxTest = new ObjectMapper().readValue(listTax, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(3, listTaxTest.size());
    }

    /**
     * Prueba para actualizar un Tax
     *
     * @generated
     */
    @Test
    public void updateTaxTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        TaxDTO tax = new TaxDTO(oraculo.get(0));

        TaxDTO taxChanged = factory.manufacturePojo(TaxDTO.class);

        tax.setName(taxChanged.getName());
        tax.setDescription(taxChanged.getDescription());
        tax.setValue(taxChanged.getValue());

        Response response = target
            .path(tax.getId().toString())
            .request().cookie(cookieSessionId)
            .put(Entity.entity(tax, MediaType.APPLICATION_JSON));

        TaxDTO taxTest = (TaxDTO) response.readEntity(TaxDTO.class);

        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(tax.getName(), taxTest.getName());
        Assert.assertEquals(tax.getDescription(), taxTest.getDescription());
        Assert.assertEquals(tax.getValue(), taxTest.getValue());
    }

    /**
     * Prueba para eliminar un Tax
     *
     * @generated
     */
    @Test
    public void deleteTaxTest() {
        Cookie cookieSessionId = login(username, password);
        TaxDTO tax = new TaxDTO(oraculo.get(0));
        Response response = target
            .path(tax.getId().toString())
            .request().cookie(cookieSessionId).delete();

        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
    
}
