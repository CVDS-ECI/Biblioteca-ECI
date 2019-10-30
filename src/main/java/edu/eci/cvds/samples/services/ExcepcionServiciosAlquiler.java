/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.samples.services;

/**
 *
 * @author 2145120
 */
public class ExcepcionServiciosAlquiler extends Exception {

    public static final String VP_INVALIDO = "El vector de probabilidades que ingresó es inválido";

    public ExcepcionServiciosAlquiler(String message) {
        super(message);
    }

    public ExcepcionServiciosAlquiler(String message, Throwable tr) {
        super(message);
    }
}
