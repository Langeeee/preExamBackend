 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.Developer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Jacob
 */
@Entity
@Table(name = "projects")
@NamedQueries({
    @NamedQuery(name = "Project.deleteAllRows", query = "DELETE from Project"),
    @NamedQuery(name = "Project.getAllRows", query = "SELECT p from Project p"),
    @NamedQuery(name = "Project.getPerson", query = "SELECT p from Project p WHERE p.id = :id")
})
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", length = 175, nullable = false, unique = false)
    private String name;
    @Column(name = "description", length = 175, nullable = false, unique = false)
    private String description;
    
    

    @ManyToMany
    private List<Developer> developers;

    public Project() {
    }

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
        this.developers = new ArrayList<>();
    }

 

    public Project(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.developers = new ArrayList<>();
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Project> getProjects(List<Project> projects) {
        return projects;
    }
    
    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }
    
    //Methods
    public void addDeveloper(Developer developer) {
        if (developer != null) {
            this.developers.add(developer);
            developer.getProjects().add(this);
           
        }
    }

    public void removeDeveloper(Developer developer) {
        if (developer != null) {
            this.developers.remove(developer);
            developer.getProjects().remove(this);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.developers);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Project other = (Project) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.developers, other.developers)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Project{" + "id=" + id + ", name=" + name + ", description=" + description + ", developers=" + developers + '}';
    }
    
    
    
    
}
