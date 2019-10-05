/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.samples.services;

import edu.eci.cvds.sampleprj.dao.PersistenceException;

/**
 *
 * @author 2149194
 */
public class ExcepcionServiciosAlquiler extends Exception {
    
    public ExcepcionServiciosAlquiler(String message,PersistenceException e){
        super(message+e.getMessage());
    }
    
    public ExcepcionServiciosAlquiler(String message){
        super(message);
    }
    
}
