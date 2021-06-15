/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import dto.DeveloperDTO;
import java.util.List;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Jacob
 */
public interface IDeveloperFacade {

    public DeveloperDTO addDeveloper(DeveloperDTO developerDTO) throws WebApplicationException;

    public DeveloperDTO deleteDeveloper(int id) throws WebApplicationException;

    public DeveloperDTO getDeveloper(int id) throws WebApplicationException;

    public List<DeveloperDTO> getAllDeveloper() throws WebApplicationException;

    public DeveloperDTO editDeveloper(DeveloperDTO developerDTO) throws WebApplicationException;
}
