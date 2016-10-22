package com.subbu.aegean.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.subbu.aegean.model.Supplier;

@SuppressWarnings("deprecation")
@Repository("supplierDAO")
public class SupplierDAOImpl implements SupplierDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SupplierDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory= sessionFactory;
	}
	
	@Transactional
	public void saveOrUpdate(Supplier supplier){
		sessionFactory.getCurrentSession().saveOrUpdate(supplier);;
		
	}
	
	@Transactional
	public void delete(String id){
		Supplier supplier = new Supplier();
		supplier.setId(id);
		sessionFactory.getCurrentSession().delete(supplier);
		
	}

	@Transactional
	public Supplier get(String id){
		String hql = "from Supplier where id="+"'"+id+"'";
		@SuppressWarnings({ "rawtypes" })
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings({ "unchecked" })
		List<Supplier> listSupplier =(List<Supplier>) query.list();
		
		if (listSupplier !=null && !listSupplier.isEmpty()){
			return listSupplier.get(0);
			
		}
		return null;
	}
	
	@Transactional
	public List<Supplier> list(){
		@SuppressWarnings("unchecked")
		List<Supplier> listSupplier = (List<Supplier>)
				sessionFactory.getCurrentSession().createCriteria(Supplier.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listSupplier;
		
	}
	
	



	
	
	
	
}