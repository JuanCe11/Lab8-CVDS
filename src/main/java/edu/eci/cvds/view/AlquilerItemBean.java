package edu.eci.cvds.view;

import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;

import java.sql.Date;

@ManagedBean(name="AlquilerItemsBean")
@ApplicationScoped
public class AlquilerItemBean extends BasePageBean{
	
	@Inject
	private ServiciosAlquiler serviciosAlquiler;
	private Cliente selectedCliente;
	
	public List<Cliente> consultarClientes(){
		try {
			return serviciosAlquiler.consultarClientes();
		} catch (ExcepcionServiciosAlquiler e) {
			return null;
		}
	}
	
	public Cliente consultarCliente(long documento) {
		try {
			return serviciosAlquiler.consultarCliente(documento);
		} catch (ExcepcionServiciosAlquiler e) {
			return null;
		}
	}
	
	public void registrarCliente(long doc,String nombre,String telefono, String direccion,String mail){
        try{
            serviciosAlquiler.registrarCliente(new Cliente(nombre,doc,telefono,direccion,mail));
        } catch (Exception e) {}
    }
	
	public void setSelectedCliente(Cliente cliente){this.selectedCliente = cliente;}

    public Cliente getSelectedCliente(){
        return selectedCliente;
    }
	
	
	public long consultarMulta(Item it) {
		try{
			return serviciosAlquiler.consultarMultaAlquiler(it.getId(),new Date(System.currentTimeMillis()));
		}
		catch(ExcepcionServiciosAlquiler e){
            return -1;
        }
    }
	
	
	public void registrarAlquilerCliente(int id,int numDiasAlquilar){
        try{
            Item item = serviciosAlquiler.consultarItem(id);
            serviciosAlquiler.registrarAlquilerCliente(new Date(System.currentTimeMillis()),selectedCliente.getDocumento(),item,numDiasAlquilar);
        }catch(ExcepcionServiciosAlquiler e){}
    }
	
	public long consultarCosto(int id, int numDiasAlquilar){
        try {
            return serviciosAlquiler.consultarCostoAlquiler(id, numDiasAlquilar);
        } catch (ExcepcionServiciosAlquiler e){
            return -1;
        }
    }
	
	
}
