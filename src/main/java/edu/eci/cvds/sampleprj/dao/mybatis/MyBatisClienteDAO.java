
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 2145120
 */
public class MyBatisClienteDAO implements ClienteDAO {

    @Inject
    private ClienteMapper clienteMapper;

    @Override
    public void save(Cliente cl) throws PersistenceException {
        try {
            clienteMapper.agregarCliente(cl);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al agregar el cliente en MyBatisClienteDAO " + cl.toString(), e);
        }

    }

    @Override
    public Cliente load(long id) throws PersistenceException {
        try {
            return clienteMapper.consultarCliente(id);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar el cliente " + id, e);
        }

    }
    
    @Override
    public List<Cliente> consultarClientes() throws PersistenceException {
        try {
            return clienteMapper.consultarClientes();
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar los clientes ", e);
        }

    }
    
    @Override
    public List<ItemRentado> loadItemsRentados(long id) throws PersistenceException {
        try {
            return clienteMapper.consultarItemsRentados(id);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar los items Rentados del cliente " + id, e);
        }
    }

    @Override
    public void addItemRentado(long id, int iditem, Date fechainicio, Date fechafin) throws PersistenceException {
        try {
           clienteMapper.agregarItemRentadoACliente(id,iditem,fechainicio,fechafin);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar los items Rentados del cliente " + id, e);
        }
    }
}
