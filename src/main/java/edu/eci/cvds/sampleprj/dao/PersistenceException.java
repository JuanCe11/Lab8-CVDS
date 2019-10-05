/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.sampleprj.dao;

/**
 *
 * @author 2149194
 */
public class PersistenceException extends Exception{
    public PersistenceException(String message,org.apache.ibatis.exceptions.PersistenceException e){
        super(message+e.getMessage());
    }
}
