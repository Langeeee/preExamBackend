/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;


import entities.Developer;
import entities.Project;
import java.util.ArrayList;
import java.util.List;
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
        this.id = 0;
        this.name = name;
        this.description = description;
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
    public String toString() {
        return "ProjectDTO{" + "id=" + id + ", name=" + name + ", description=" + description + '}';
    }

   


    
    
     
     
     
    
}
