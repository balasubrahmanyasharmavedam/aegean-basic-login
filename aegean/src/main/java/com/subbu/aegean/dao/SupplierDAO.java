package com.subbu.aegean.dao;

import java.util.List;

import com.subbu.aegean.model.Supplier;

public interface SupplierDAO {
	public List<Supplier> list();

	public Supplier get(String id);

	public void saveOrUpdate(Supplier supplier);

	public void delete(String id);

}
