package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Item;
import java.util.Date;
import java.util.List;

public interface ItemDAO {

   public void save(Item it) throws PersistenceException;
   

   public Item load(int id) throws PersistenceException;
   
   public List<Item> loadAvailable() throws PersistenceException;
   
   public long calcularCostoAlquiler(int id, int numDias) throws PersistenceException;
   
   
   public long calcularMultaAlquiler (int idItem, Date fecha) throws PersistenceException;
}