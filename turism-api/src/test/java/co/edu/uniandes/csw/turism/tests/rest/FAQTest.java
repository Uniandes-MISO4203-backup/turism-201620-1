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
import co.edu.uniandes.csw.turism.dtos.minimum.FAQDTO;
import co.edu.uniandes.csw.turism.entities.AgencyEntity;
import co.edu.uniandes.csw.turism.entities.FAQEntity;
import co.edu.uniandes.csw.turism.resources.FAQResource;
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
public class FAQTest {
     private WebTarget target;
    private final String apiPath = Utils.apiPath;
    private final String username = Utils.username;
    private final String password = Utils.password;
    PodamFactory factory = new PodamFactoryImpl();

    private final int Ok = Response.Status.OK.getStatusCode();
    private final int Created = Response.Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Response.Status.NO_CONTENT.getStatusCode();

    private final static List<FAQEntity> oraculo = new ArrayList<>();

    private final String agencyPath = "agencys";
    private final String faqPath = "faqs";

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
                .addPackage(FAQResource.class.getPackage())
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
        em.createQuery("delete from FAQEntity").executeUpdate();
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
            FAQEntity faq = factory.manufacturePojo(FAQEntity.class);
            faq.setId(i + 1L);
            faq.setAgency(fatherAgencyEntity);
            em.persist(faq);
            oraculo.add(faq);
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
                .path(faqPath);
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
     * Prueba para crear un FAQ
     *
     * @generated
     */
    @Test
    public void createFAQTest() throws IOException {
        FAQDTO faq = factory.manufacturePojo(FAQDTO.class);
        Cookie cookieSessionId = login(username, password);

        Response response = target
            .request().cookie(cookieSessionId)
            .post(Entity.entity(faq, MediaType.APPLICATION_JSON));

        FAQDTO  faqTest = (FAQDTO) response.readEntity(FAQDTO.class);

        Assert.assertEquals(Created, response.getStatus());

        Assert.assertEquals(faq.getName(), faqTest.getName());
        Assert.assertEquals(faq.getQuestion(), faqTest.getQuestion());
        Assert.assertEquals(faq.getResponse(), faqTest.getResponse());

        FAQEntity entity = em.find(FAQEntity.class, faqTest.getId());
        Assert.assertNotNull(entity);
    }

    /**
     * Prueba para consultar un FAQ
     *
     * @generated
     */
    @Test
    public void getFAQByIdTest() {
        Cookie cookieSessionId = login(username, password);

        FAQDTO faqTest = target
            .path(oraculo.get(0).getId().toString())
            .request().cookie(cookieSessionId).get(FAQDTO.class);
        
        Assert.assertEquals(faqTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(faqTest.getName(), oraculo.get(0).getName());
        Assert.assertEquals(faqTest.getQuestion(), oraculo.get(0).getQuestion());
        Assert.assertEquals(faqTest.getResponse(), oraculo.get(0).getResponse());
    }

    /**
     * Prueba para consultar la lista de FAQs
     *
     * @generated
     */
    @Test
    public void listFAQTest() throws IOException {
        Cookie cookieSessionId = login(username, password);

        Response response = target
            .request().cookie(cookieSessionId).get();

        String listFAQ = response.readEntity(String.class);
        List<FAQDTO> listFAQTest = new ObjectMapper().readValue(listFAQ, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(3, listFAQTest.size());
    }

    /**
     * Prueba para actualizar un FAQ
     *
     * @generated
     */
    @Test
    public void updateFAQTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        FAQDTO faq = new FAQDTO(oraculo.get(0));

        FAQDTO faqChanged = factory.manufacturePojo(FAQDTO.class);

        faq.setName(faqChanged.getName());
        faq.setQuestion(faqChanged.getQuestion());
        faq.setResponse(faqChanged.getResponse());

        Response response = target
            .path(faq.getId().toString())
            .request().cookie(cookieSessionId)
            .put(Entity.entity(faq, MediaType.APPLICATION_JSON));

        FAQDTO faqTest = (FAQDTO) response.readEntity(FAQDTO.class);

        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(faq.getName(), faqTest.getName());
        Assert.assertEquals(faq.getQuestion(), faqTest.getQuestion());
        Assert.assertEquals(faq.getResponse(), faqTest.getResponse());
    }

    /**
     * Prueba para eliminar un FAQ
     *
     * @generated
     */
    @Test
    public void deleteFAQTest() {
        Cookie cookieSessionId = login(username, password);
        FAQDTO faq = new FAQDTO(oraculo.get(0));
        Response response = target
            .path(faq.getId().toString())
            .request().cookie(cookieSessionId).delete();

        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
    
}
