package project.store;


import project.model.Reports;

import java.util.List;

public interface ReportsDao extends Storage<Reports> {

	@Override
	List<Reports> findAll();
    @Override
	void add(Reports r);
	@Override
	void delete(int id);
}
