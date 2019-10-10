/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Cliente;

import java.sql.Date;
import java.util.List;
import edu.eci.cvds.samples.entities.ItemRentado;

/**
 *
 * @author 2149194
 */
public interface ClienteDAO {
    
    public void save(Cliente it) throws PersistenceException;
    public Cliente load(long id) throws PersistenceException;
    public List<Cliente> loadClientes() throws PersistenceException;
    public List<ItemRentado> loadItemsRentados(long idcliente) throws PersistenceException;
    public long loadMulta(int iditem, Date fechaDevolucion) throws PersistenceException;
    public long agregarItemRentado(int id,int idit, Date fechainicio,Date fechafinn) throws PersistenceException;
}
