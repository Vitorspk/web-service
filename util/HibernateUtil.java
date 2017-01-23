package util;

import java.util.List;

import facade.Facade;
import ContentValues;
import component.util.PersistenceException;

public class HibernateUtil {
    private static Facade facade = new Facade();
    
    private HibernateUtil(){}
    
    public static void insertOrUpdate(Object object) throws PersistenceException {
	facade.insertOrUpdate(object);
    }
    
    public List<Object> persistInCascade(List<Object> listObjects) throws PersistenceException {
	return facade.persistInCascade(listObjects);
    }
    
    public Object[] persistInCascade(Object... objects) throws PersistenceException {
	return facade.persistInCascade(objects);
    }
    
    public static void delete(Object object) throws PersistenceException {
	facade.delete(object);
    }
    
    public static <T> T findById(T object, Long id) throws PersistenceException {
	return facade.findById(object, id);
    }
    
    public static <T> T findByPk(T object, Object pk) throws PersistenceException {
	return facade.findByPk(object, pk);
    }
    
    public static <T> List<T> findAll(T object, String orderField) throws PersistenceException {
	return facade.findAll(object, orderField);
    }
    
    public static <T> T findOneByField(T object, String field, Object value) throws PersistenceException {
	return facade.findOneByField(object, field, value);
    }
    
    public static <T> T findOneByFields(T object, String[] fields, Object[] values) throws PersistenceException {
	return facade.findOneByFields(object, fields, values);
    }
    
    public static <T> T findOneByFields(T object, ContentValues contentValues) throws PersistenceException {
	return facade.findOneByFields(object, contentValues);
    }
    
    public static <T> List<T> findManyByField(T object, String field, Object value, String orderField) throws PersistenceException {
	return facade.findManyByField(object, field, value, orderField);
    }
    
    public static <T> List<T> findManyByFields(T object, String[] fields, Object[] values, String orderField) throws PersistenceException {
	return facade.findManyByFields(object, fields, values, orderField);
    }
    
    public static <T> List<T> findManyByFields(T object, ContentValues contentValues, String orderField) throws PersistenceException {
	return facade.findManyByFields(object, contentValues, orderField);
    }
}
