/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import dto.ProjectDTO;
import entities.Developer;
import entities.Project;
import entities.Developer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Jacob
 */
public class DeveloperDTO {
    
    private int id;
    private String name;
    private String email;
    private int phone;
    private int billingPrHour;
    private ArrayList<ProjectDTO> projects;

    public DeveloperDTO() {
    }
    
     public DeveloperDTO(Developer developer) {
        this.id = developer.getId();
        this.name = developer.getName();
        this.email = developer.getEmail();
        this.phone = developer.getPhone();
        this.billingPrHour = developer.getBillingPrHour();
        this.projects = developer.getProjects() != null ? makeDTO2list(developer.getProjects()) : new ArrayList<>();
    }

    public DeveloperDTO(int id, String name, String email, int phone, int billingPrHour, ArrayList<ProjectDTO> projects) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.billingPrHour = billingPrHour;
        this.projects = projects;
    }
    

    public DeveloperDTO(String name, String email, int phone, int billingPrHour, ArrayList<ProjectDTO> projects) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.billingPrHour = billingPrHour;
        this.projects = projects;
    }

    public DeveloperDTO(String name) {
        this.name = name;
    }
   
      private ArrayList<ProjectDTO> makeDTO2list(List<Project> projects) {
        ArrayList<ProjectDTO> projectsDTOlist = new ArrayList<>();
        for (Project project : projects) {
            projectsDTOlist.add(new ProjectDTO(project));
        }
        return projectsDTOlist;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getBillingPrHour() {
        return billingPrHour;
    }

    public void setBillingPrHour(int billingPrHour) {
        this.billingPrHour = billingPrHour;
    }

    public ArrayList<ProjectDTO> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<ProjectDTO> projects) {
        this.projects = projects;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.id;
        hash = 73 * hash + Objects.hashCode(this.name);
        hash = 73 * hash + Objects.hashCode(this.email);
        hash = 73 * hash + this.phone;
        hash = 73 * hash + this.billingPrHour;
        hash = 73 * hash + Objects.hashCode(this.projects);
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
        final DeveloperDTO other = (DeveloperDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.phone != other.phone) {
            return false;
        }
        if (this.billingPrHour != other.billingPrHour) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.projects, other.projects)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DeveloperDTO{" + "id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", billingPrHour=" + billingPrHour + ", projects=" + projects + '}';
    }
    
    
    

   

    
    
    
    
}