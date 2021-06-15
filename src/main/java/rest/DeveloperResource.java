package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.ProjectDTO;
import entities.Project;
import dto.DeveloperDTO;
import entities.Developer;
import facades.DeveloperFacade;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import utils.EMF_Creator;

/**
 *
 * @author Tweny
 */
@Path("exam")
public class DeveloperResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final DeveloperFacade FACADE = DeveloperFacade.getFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @Path("/msg")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello -  World\"}";
    }
    
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllProjects() {
        try {
            List<ProjectDTO> list = FACADE.getAllProjects();
            return GSON.toJson(list);
        } catch (WebApplicationException ex) {
            String errorString = "{\"code\": " + ex.getResponse().getStatus() + ", \"message\": \"" + ex.getMessage() + "\"}";
            return errorString;
        }
    }
   
 
    @Path("/security")
    @RolesAllowed("user")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllPersonsSecurity() {
        try {
            List<ProjectDTO> list = FACADE.getAllProjects();
            return GSON.toJson(list);
        } catch (WebApplicationException ex) {
            String errorString = "{\"code\": " + ex.getResponse().getStatus() + ", \"message\": \"" + ex.getMessage() + "\"}";
            return errorString;
        }
    }
    
    
     @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("projects/all")
  public String allProjects(){
    System.out.println("GET REQUEST: getAllProjects");
    return GSON.toJson(FACADE.getAllProjects());
  }
  
   @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("developers/all")
  public String allDevelopers(){
    System.out.println("GET REQUEST: allDevelopers");
    return GSON.toJson(FACADE.getAllDevelopers());
  }
     

   @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getProjectById(@PathParam("id") int id) {
        try {
            ProjectDTO projectDTO = FACADE.getProject(id);
            return GSON.toJson(projectDTO);
        } catch (WebApplicationException ex) {
            String errorString = "{\"code\": " + ex.getResponse().getStatus() + ", \"message\": \"" + ex.getMessage() + "\"}";
            return errorString;
        }
    }
    
    /*
    @Path("{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editDeveloper(@PathParam("id") int id, String developer) {
        try {
            //System.out.println(person);
            DeveloperDTO developerDTOEditInfo = GSON.fromJson(developer, DeveloperDTO.class); //manual conversion
            developerDTOEditInfo.setId(id);
            developerDTOEditInfo = FACADE.editDeveloper(DeveloperDTOEditInfo);
            return GSON.toJson(DeveloperDTOEditInfo);
        } catch (WebApplicationException ex) {
            String errorString = "{\"code\": " + ex.getResponse().getStatus() + ", \"message\": \"" + ex.getMessage() + "\"}";
            return errorString;
        }
    }
    */
    
    
     @Path("{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteDeveloper(@PathParam("id") int id) {
        try {
            DeveloperDTO developerDTO = FACADE.deleteDeveloper(id);
            System.out.println(developerDTO);
            return "{\"status\": \"removed\"}";
        } catch (WebApplicationException ex) {
            String errorString = "{\"code\": " + ex.getResponse().getStatus() + ", \"message\": \"" + ex.getMessage() + "\"}";
            return errorString;
        }
    }

  

    }

