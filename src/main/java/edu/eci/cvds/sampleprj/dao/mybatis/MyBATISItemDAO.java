package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.samples.entities.TipoItem;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class MyBATISItemDAO implements ItemDAO {

    @Inject
    private ItemMapper itemMapper;

    @Override
    public void save(Item it) throws PersistenceException {
        try {
            itemMapper.insertarItem(it);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al registrar el item " + it.toString(), e);
        }

    }

    @Override
    public Item load(int id) throws PersistenceException {
        try {
            return itemMapper.consultarItem(id);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar el item " + id, e);
        }
    }

    @Override
    public List<Item> loadAvailable() throws PersistenceException {
        try {
            return itemMapper.consultarItemsDisponibles();
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar los items disponibles ", e);
        }

    }

    @Override
    public long calcularCostoAlquiler(int id, int numDias) throws PersistenceException {
        try {
            return itemMapper.calcularCostoAlquiler(id, numDias);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al calcular el costo del alquiler ", e);
        }
    }

    @Override
    public long calcularMultaAlquiler(int idItem, Date fecha) throws PersistenceException {
        try {
            return itemMapper.calcularMultaAlquiler(idItem, fecha);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al calcular la multa del Alquiler ", e);
        }
    }
 
    

}
