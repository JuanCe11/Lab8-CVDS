/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import java.util.List;

/**
 *
 * @author 2149194
 */
public class MyBATISClienteDAO implements ClienteDAO{
    
    @Inject
    private ClienteMapper clienteMapper;
    
    @Override
    public void save(Cliente it) throws PersistenceException {
        try{
            clienteMapper.agregarCliente(it);
        }catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    @Override
    public Cliente load(long id) throws PersistenceException {
        try{
            return clienteMapper.consultarCliente(id);
        }catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el cliente "+id,e);
        }
    }

    @Override
    public List<Cliente> loadClientes() throws PersistenceException {
        try{
            return clienteMapper.consultarClientes();
        }catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar los clientes ",e);
        }
    }

    @Override
    public List<ItemRentado> loadItemsRentados(long idcliente) throws PersistenceException {
        try{
            return clienteMapper.consultarItems(idcliente);
        }catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar los items rentados",e);
        }
    }
    
    
}
