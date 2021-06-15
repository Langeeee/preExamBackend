/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;


import entities.Developer;
import entities.Project;
import java.util.Objects;

/**
 *
 * @author Jacob
 */
public class ProjectDTO {
    
     private int id;
     private String name;
     private String description;

    public ProjectDTO() {
    }

    public ProjectDTO(Project project) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    
    
    public ProjectDTO(String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    
     public ProjectDTO(String name) {
        this.name = name;
        
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.description);
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
        final ProjectDTO other = (ProjectDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProjectDTO{" + "id=" + id + ", name=" + name + ", description=" + description + '}';
    }

    
    
     
     
     
    
}