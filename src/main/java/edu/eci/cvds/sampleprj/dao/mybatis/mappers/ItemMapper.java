package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Item;

/**
 *
 * @author 2106913
 */
public interface ItemMapper {

    public List<Item> consultarItems();

    public Item consultarItem(@Param("itid") int id);

    public void insertarItem(@Param("it") Item it);

    public List<Item> consultarItemsDisponibles();

    public long calcularCostoAlquiler(@Param("itemId") int id, @Param("numDias") int numDias);

    public long calcularMultaAlquiler(@Param("itemId") int id, @Param("fecha") Date fecha);

}
