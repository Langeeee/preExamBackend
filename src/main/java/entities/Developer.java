/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


import entities.Project;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
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
@Table(name = "developers")
@NamedQueries({
    @NamedQuery(name = "Developer.deleteAllRows", query = "DELETE from Developer"),
    @NamedQuery(name = "Developer.getAllRows", query = "SELECT p from Developer p"),
    @NamedQuery(name = "Developer.getDeveloper", query = "SELECT p from Developer p WHERE p = :p")
})
public class Developer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name", length = 175, nullable = false, unique = false)
    private String name;
    @Column(name = "email", length = 175, nullable = false, unique = false)
    private String email;
    @Column(name = "phone", length = 175, nullable = false, unique = false)
    private int phone;
    @Column(name = "billingPrHour", length = 175, nullable = false, unique = false)
    private int billingPrHour;

    
    //***************Many to Many****************
    @ManyToMany(mappedBy = "developers", cascade = {CascadeType.PERSIST, CascadeType.MERGE}) // , fetch = FetchType.EAGER
    private List<Project> projects;
   

    public void addProject(Project project) {
        if (project != null) {
            this.projects.add(project);
            project.addDeveloper(this);
        }
    }
    
     public void removeProject(Project project) {
        if (project != null) {
            this.projects.remove(project);
            project.getDevelopers().remove(this); 
            
        }
     }
    
    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects (List<Project> projects) {
        this.projects = projects;
    }
    //*******************************

    public Developer() {
    }

    public Developer(String name, String email, int phone, int billingPrHour) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.billingPrHour = billingPrHour;
        this.projects = new ArrayList<>();
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

    @Override
    public String toString() {
        return "Developer{" + "id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", billingPrHour=" + billingPrHour + ", projects=" + projects + '}';
    }

   

   
   
    
    
    
    
    
    
}
