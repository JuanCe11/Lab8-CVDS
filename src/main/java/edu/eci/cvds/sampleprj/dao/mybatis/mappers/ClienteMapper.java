package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.ItemRentado;

/**
 *
 * @author 2106913
 */
public interface ClienteMapper {
    
    public Cliente consultarCliente(@Param("idcli") long id); 
    
    /**
     * Registrar un nuevo item rentado asociado al cliente identificado
     * con 'idc' y relacionado con el item identificado con 'idi'
     * @param id
     * @param idit
     * @param fechainicio
     * @param fechafin
     * @return
     */
    public long agregarItemRentadoACliente(@Param("idcli")long id,
                                           @Param("idite") int idit,
                                           @Param("fechainit") Date fechainicio,
                                           @Param("fechaend") Date fechafin);

    /**
     * Consultar todos los clientes
     * @return 
     */
    public List<Cliente> consultarClientes();
    
    
    public void agregarCliente(@Param("cliente") Cliente cliente);
    
    public List<ItemRentado> consultarItems(@Param("idcliente") long idcliente);
	
}
