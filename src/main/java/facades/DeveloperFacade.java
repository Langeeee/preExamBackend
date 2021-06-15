/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.DeveloperDTO;
import dto.HobbyDTO;
import dto.PersonDTO;
import dto.ProjectDTO;
import entities.Developer;
import entities.Hobby;
import entities.Person;
import entities.Project;
import interfaces.IDeveloperFacade;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Jacob
 */
public class DeveloperFacade {
    
      private static DeveloperFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private DeveloperFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static DeveloperFacade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new DeveloperFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
     public List<ProjectDTO> getAllProjects2() {
    List<ProjectDTO> dtos = new ArrayList<>();
    EntityManager em = emf.createEntityManager();
    try{
      TypedQuery<Project> query = em.createQuery("SELECT p FROM Project p", Project.class);
      List<Project> res = query.getResultList();

      for(Project p: res){
        dtos.add(new ProjectDTO(p));
      }

    }catch (NoResultException ex) {
      return new ArrayList<>();
    }finally {
      em.close();
    }
    return dtos;
  }

  public long getNumberOfSchools(){
    EntityManager em = emf.createEntityManager();
    try{
      return (long)em.createQuery("SELECT COUNT(s) FROM School s").getSingleResult();
    }finally{
      em.close();
    }
  }
    
    /*
    public List<ProjectDTO> getAllProjects() throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Project> query = em.createQuery("SELECT project FROM Project project", Project.class);
            List<Project> projects = query.getResultList();
//            System.out.println(persons.size());
            ArrayList<ProjectDTO> projectDTOs = new ArrayList<>();
            for(Project project : projects){
                projectDTOs.add(new ProjectDTO(project));
            }
            return projectDTOs;
        } catch (RuntimeException ex) {
            throw new WebApplicationException("Internal Server Problem. We are sorry for the inconvenience", 500);
        } finally {
            em.close();
        }
    }
    */
     public List<DeveloperDTO> getAllDevelopers() throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Developer> query = em.createQuery("SELECT developer FROM Developer Developer", Developer.class);
            List<Developer> developers = query.getResultList();
//            System.out.println(persons.size());
            ArrayList<DeveloperDTO> developerDTOs = new ArrayList<>();
            for(Developer developer : developers){
                developerDTOs.add(new DeveloperDTO(developer));
            }
            return developerDTOs;
        } catch (RuntimeException ex) {
            throw new WebApplicationException("Internal Server Problem. We are sorry for the inconvenience", 500);
        } finally {
            em.close();
        }
    }
     
     public DeveloperDTO getDeveloper(DeveloperDTO dto)  {
    EntityManager em = emf.createEntityManager();
    Developer developer = new Developer();
    try {
      Query query = em.createQuery("SELECT d FROM Developer d WHERE d.id = :id", Developer.class);
      query.setParameter("email", developer.getEmail());
      developer = (Developer) query.getSingleResult();

    } catch (RuntimeException ex) {
     
    } finally {
      em.close();
    }
    return new DeveloperDTO(developer);
  }
     
     

    
   
    public synchronized DeveloperDTO addDeveloper(DeveloperDTO developerDTO) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        if (developerDTO.getName() == null) {
            throw new WebApplicationException("Name is missing", 400);
        
        } else if (developerDTO.getProjects().isEmpty() || developerDTO.getProjects() == null) {
            throw new WebApplicationException("Projects are missing.", 400);
        }
        try {
            em.getTransaction().begin();
            
            Developer developer = new Developer();

            //Create or use existing Project
            if (developerDTO.getProjects() != null) {
                Project project;
                for (ProjectDTO projectDTO : developerDTO.getProjects()) {
                    project = findProjectInDB(projectDTO);
                    if (project.getId() > 0) {
                        project = em.find(Project.class, project.getId());
                    }
                    System.out.println(project);
                    developer.addProject(project);
                }

            }
            
            em.persist(developer);
            em.getTransaction().commit();
            return new DeveloperDTO(developer);
            
        } catch (RuntimeException ex) {
            throw new WebApplicationException("Internal Server Problem. We are sorry for the inconvenience", 500);
        } finally {
            em.close();
        }
    }
    
    
    public ProjectDTO deleteProject(int id) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Project project = em.find(Project.class, id);
            em.remove(project);
            em.getTransaction().commit();
            return new ProjectDTO(project);
        } catch (NullPointerException | IllegalArgumentException ex) {
            throw new WebApplicationException("Could not delete, provided id: " + id + " does not exist", 404);
        } catch (RuntimeException ex) {
            throw new WebApplicationException("Internal Server Problem. We are sorry for the inconvenience", 500);
        } finally {
            em.close();
        }
    }

    
  

    
    public List<ProjectDTO> getAllProjects() throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Project> query = em.createQuery("SELECT project FROM Project project", Project.class);
            List<Project> projects = query.getResultList();
//            System.out.println(developers.size());
            ArrayList<ProjectDTO> projectDTOs = new ArrayList<>();
            for(Project project : projects){
                projectDTOs.add(new ProjectDTO(project));
            }
            return projectDTOs;
        } catch (RuntimeException ex) {
            throw new WebApplicationException("Internal Server Problem. We are sorry for the inconvenience", 500);
        } finally {
            em.close();
        }
    }

    /*
    public PersonDTO editPerson(PersonDTO personDTO) throws WebApplicationException {
        if (personDTO.getName() == null) {
            throw new WebApplicationException("Name is missing", 400);
        } else if (personDTO.getJob() == null || personDTO.getJob().getTitle() == null) {
            throw new WebApplicationException("Job title is missing.", 400);
        } else if (personDTO.getNickName() == null || personDTO.getNickName().getNickName() == null) {
            throw new WebApplicationException("Nickname is missing.", 400);
        } else if (personDTO.getHobbies().isEmpty() || personDTO.getHobbies() == null) {
            throw new WebApplicationException("Hobbies are missing.", 400);
        }
        EntityManager em = emf.createEntityManager();
        Person personEditInfo = new Person(personDTO.getName());
        personEditInfo.setJob(new Job(personDTO.getJob().getTitle()));
        personEditInfo.setNickName(new NickName(personDTO.getNickName().getNickName()));
        personEditInfo.setHobbies(personDTO.makeHobbylist());

        Person oldPerson = em.find(Person.class, personDTO.getId());

        try {
            em.getTransaction().begin();

            oldPerson.setName(personDTO.getName());

            //Create or use existing Job
            if (personDTO.getJob() != null && !oldPerson.getJob().equals(personEditInfo.getJob())) {
                Job job = findJobInDB(personDTO.getJob());
                if (job.getId() > 0) {
                    job = em.find(Job.class, job.getId());
                }
                job.addPerson(oldPerson);
                em.persist(job);
            }

            //Create or use existing NickName
            if (personDTO.getNickName() != null && !oldPerson.getNickName().equals(personEditInfo.getNickName())) {
                NickName nickName = findNickNameInDB(personDTO.getNickName());
                if (nickName.getId() > 0) {
                    nickName = em.find(NickName.class, nickName.getId());
                }
                System.out.println(nickName);
                em.persist(nickName);
                oldPerson.setNickName(nickName);
//                em.merge(oldPerson);
                System.out.println(nickName);
            }

            //Create or use existing Hobby
            if (personDTO.getHobbies() != null) {

                for (int i = 0; i < personDTO.makeHobbylist().size(); i++) {
                    Hobby hobby;
                    int count = 0;
                    for (int j = 0; j < oldPerson.getHobbies().size(); j++) {
                        if (personDTO.makeHobbylist().get(i).equals(oldPerson.getHobbies().get(j))) {
                            count = 1;
                        }
                    }
                    if (count == 0) {
                        hobby = findHobbyInDB(new HobbyDTO(personDTO.makeHobbylist().get(i)));
                        if (hobby.getId() > 0) {
                            hobby = em.find(Hobby.class, hobby.getId());
                        }
                        oldPerson.addHobby(hobby);
//                        em.merge(oldPerson);
                    }
                }
            }
            em.persist(oldPerson);
            em.getTransaction().commit();
            return new PersonDTO(oldPerson);
        } //        catch (RuntimeException ex) {
        //            throw new WebApplicationException("Internal Server Problem. We are sorry for the inconvenience", 500);
        //        } 
        finally {
            em.close();
        }
    }
    */
     private Project findProjectInDB(ProjectDTO projectDTO) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT p FROM Project p WHERE p.name = :name ", Project.class);
            query.setParameter("name", projectDTO.getName());
            Project project = (Project) query.getSingleResult();
            return project;
        }  finally {
            em.close();
        }
    }

    
}
