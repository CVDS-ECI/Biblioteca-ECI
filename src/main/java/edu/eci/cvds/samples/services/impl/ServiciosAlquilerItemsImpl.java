package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import org.mybatis.guice.transactional.Transactional;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Singleton
public class ServiciosAlquilerItemsImpl implements ServiciosAlquiler {

    @Inject
    private ItemDAO itemDAO;

    @Inject
    private ClienteDAO clienteDAO;

    @Override
    public void registrarCliente(Cliente c) throws ExcepcionServiciosAlquiler {
        try {
            if (c==null) {
                throw new ExcepcionServiciosAlquiler("No se puede registrar un cliente sin su nombre");
            } else {
                clienteDAO.save(c);
            }

        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al insertar el cliente en ServiciosAlquilerImpl " + c, ex);
        }
    }

    @Override
    public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
        try {
            if (clienteDAO.load(docu) != null) {
                return clienteDAO.load(docu);
            } else {
                throw new ExcepcionServiciosAlquiler("No existe cliente");
            }
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el cliente " + docu, ex);
        }
    }

    @Override
    public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
        try {
            if (itemDAO.load(id) != null) {
                return itemDAO.load(id);
            } else {
                throw new ExcepcionServiciosAlquiler("Item no existente");
            }
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el item " + id, ex);
        }
    }

    @Override
    public List<Item> consultarItemsDisponibles() throws ExcepcionServiciosAlquiler {
        try {
            return itemDAO.loadAvailable();
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar los items Disponibles ", ex);
        }
    }

    @Override
    public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
        try {
            return clienteDAO.loadItemsRentados(idcliente);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar los items Rentados del cliente");
        }
    }

    @Override
    public void registrarAlquilerCliente(Date date, long docu, Item item, int numDias) throws ExcepcionServiciosAlquiler {
        try {
            clienteDAO.addItemRentado(docu, item.getId(), date, sumarDiasAFecha(date, numDias));
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al registrar un Alquiler al cliente");
        }
    }

    @Override
    public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
        try {
            return clienteDAO.consultarClientes();
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar los clientes en ServiciosAlquilerImpl", ex);
        }
    }

    @Override
    public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {
        try {
            return itemDAO.calcularMultaAlquiler(iditem, fechaDevolucion);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el valor de la Multa", ex);
        }
    }

    @Override
    public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {
        Item item = consultarItem(iditem);
        if (item == null) {
            throw new ExcepcionServiciosAlquiler("El item no existe");
        } else if (numdias < 1) {
            throw new ExcepcionServiciosAlquiler("el numero de dias debe ser mayor o igual a 1");
        } else {
            return item.getTarifaxDia() * numdias;
        }

    }

    // Estos no hay que implementarlos por ahora-------------------!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Override
    public int valorMultaRetrasoxDia(int itemId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
        try {
            itemDAO.save(i);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el costo del alquiler", ex);
        }
    }

    @Override
    public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static java.util.Date sumarDiasAFecha(Date fecha, int dias) {
        if (dias == 0) {
            return fecha;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }
}
