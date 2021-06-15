/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;


import entities.Developer;
import dto.DeveloperDTO;
import entities.Developer;
import entities.Project;
import facades.DeveloperFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

/**
 *
 * @author Jacob
 */
public class DeveloperTest {

    private static EntityManagerFactory emf;
    private static DeveloperFacade facade;
    private Developer d1 = new Developer("Jacob", "mail@mail.dk", 12312322, 115);
    private Developer d2 = new Developer("Emil", "emil@emilsen.dk", 11121122, 250);
    private Project p1 = new Project("java", "backendopgave");
    private Project p2 = new Project("javascript", "frontendopgave");
    

    public DeveloperTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = DeveloperFacade.getFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Developer.deleteAllRows").executeUpdate();
            em.createNamedQuery("Project.deleteAllRows").executeUpdate();

            d1.addProject(p1);
            d2.addProject(p2);
            em.persist(d1);
            em.persist(d2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testNumberOfPersons() {
        assertEquals(2, facade.getAllDevelopers().size(), "Expects two rows in the database");
    }
     @Test
    public void testNumberOfprojects() {
        assertEquals(2, facade.getAllProjects().size(), "Expects two rows in the database");
    }
    
    @Test
    public void testDeletePerson() {
        DeveloperDTO developerDTO = facade.deleteDeveloper(d1.getId());
        assertEquals(1, facade.getAllDevelopers().size(), "Expects one row in the database since we deleted one");

    /*
    @Test
    public void testFindDeveloper() {
        DeveloperDTO developerDTO = facade.getDeveloper(dto.Developer)
        assertEquals("Developer 1", developerDTO.getName());
    }
    
    
    @Test
    public void testEditPersonExceptionJob() {
        p2.setName("New Name");
        PersonDTO personDTO = new PersonDTO(p2);
        try {
            personDTO = facade.editPerson(personDTO);
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (WebApplicationException ex) {
            assertThat(ex.getMessage(), is("Job title is missing."));
        }
    }

    @Test
    public void testEditPersonExceptionNickName() {
        p2.setName("New Name");
        p2.setJob(new Job("Test Job"));
        PersonDTO personDTO = new PersonDTO(p2);
        try {
            personDTO = facade.editPerson(personDTO);
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (WebApplicationException ex) {
            assertThat(ex.getMessage(), is("Nickname is missing."));
        }
    }

    @Test
    public void testEditPersonExceptionHobby() {
        p2.setName("New Name");
        p2.setJob(new Job("Test Job"));
        p2.setNickName(new NickName("Test Nickname"));
        PersonDTO personDTO = new PersonDTO(p2);
        try {
            personDTO = facade.editPerson(personDTO);
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (WebApplicationException ex) {
            assertThat(ex.getMessage(), is("Hobbies are missing."));
        }
    }

    @Test
    public void testEditPerson() {
        p1.setName("New Name");
        PersonDTO personDTO = new PersonDTO(p1);
        personDTO = facade.editPerson(personDTO);
        assertEquals("New Name", personDTO.getName());
    }

    
    
*/
}
}