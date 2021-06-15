/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDTO;
import entities.Developer;
import entities.Hobby;
import entities.Job;
import entities.NickName;
import entities.Person;
import entities.Project;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import java.net.URI;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

/**
 *
 * @author Jacob
 */
public class DeveloperResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private Developer d1 = new Developer("Jacob", "mail@mail.dk", 12312322, 115);
    private Developer d2 = new Developer("Emil", "emil@emilsen.dk", 11121122, 250);
    private Project p1 = new Project("java", "backendopgave");
    private Project p2 = new Project("javascript", "frontendopgave");

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Developer.deleteAllRows").executeUpdate();
            em.createNamedQuery("Project.deleteAllRows").executeUpdate();
            
            //d1.addProject(p1);
            //d2.addProject(p2);
            
            em.persist(d1);
            em.persist(d2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/exam/msg").then().statusCode(200);
    }

    @Test
    public void testDummyMsg() throws Exception {
        given()
                .contentType("application/json")
                .get("/exam/msg").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("msg", equalTo("Hello World"));
    }

    @Test
    public void printJSON() {
        given().log().all().when().get("/developers").then().log().body();
    }

    @Test
    public void testAll() {
        given()
                .contentType("application/json")
                .get("/exam/developers/all").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("name", hasItems("Jacob", "Emil"));
    
    }
    
   @Test
    public void testDevelopersSize() {
        given()
                .contentType("application/json")
                .get("/exam/developers/all").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("size()", equalTo(2))
                .body("", hasSize(2))
                .body("$", hasSize(2));
        
    }

    
    
 //******* DELETE *******
    @Test
    public void testDELETErequest() {
        given()
                .contentType("application/json")
                .when()
                .delete("/exam/{id}", d2.getId())
                .then()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .and()
                .body("status", equalTo("removed"));
    }


}