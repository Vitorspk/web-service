package br.com.microdatasistemas.simintegrationws.util;

import java.util.List;

import br.com.microdata.component.facade.Facade;
import br.com.microdata.component.persistence.ContentValues;
import br.com.microdata.component.util.PersistenceMicrodataException;

public class HibernateUtil {
    private static Facade facade = new Facade();
    
    private HibernateUtil(){}
    
    public static void insertOrUpdate(Object object) throws PersistenceMicrodataException {
	facade.insertOrUpdate(object);
    }
    
    public List<Object> persistInCascade(List<Object> listObjects) throws PersistenceMicrodataException {
	return facade.persistInCascade(listObjects);
    }
    
    public Object[] persistInCascade(Object... objects) throws PersistenceMicrodataException {
	return facade.persistInCascade(objects);
    }
    
    public static void delete(Object object) throws PersistenceMicrodataException {
	facade.delete(object);
    }
    
    public static <T> T findById(T object, Long id) throws PersistenceMicrodataException {
	return facade.findById(object, id);
    }
    
    public static <T> T findByPk(T object, Object pk) throws PersistenceMicrodataException {
	return facade.findByPk(object, pk);
    }
    
    public static <T> List<T> findAll(T object, String orderField) throws PersistenceMicrodataException {
	return facade.findAll(object, orderField);
    }
    
    public static <T> T findOneByField(T object, String field, Object value) throws PersistenceMicrodataException {
	return facade.findOneByField(object, field, value);
    }
    
    public static <T> T findOneByFields(T object, String[] fields, Object[] values) throws PersistenceMicrodataException {
	return facade.findOneByFields(object, fields, values);
    }
    
    public static <T> T findOneByFields(T object, ContentValues contentValues) throws PersistenceMicrodataException {
	return facade.findOneByFields(object, contentValues);
    }
    
    public static <T> List<T> findManyByField(T object, String field, Object value, String orderField) throws PersistenceMicrodataException {
	return facade.findManyByField(object, field, value, orderField);
    }
    
    public static <T> List<T> findManyByFields(T object, String[] fields, Object[] values, String orderField) throws PersistenceMicrodataException {
	return facade.findManyByFields(object, fields, values, orderField);
    }
    
    public static <T> List<T> findManyByFields(T object, ContentValues contentValues, String orderField) throws PersistenceMicrodataException {
	return facade.findManyByFields(object, contentValues, orderField);
    }
}