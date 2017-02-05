package qrizzly.project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qrizzly.project.model.Reports;
import qrizzly.project.store.ReportsDao;

import java.util.List;

@Service("reportsService")
@Transactional
public class ReportsServiceImpl implements ReportsService {


	private ReportsDao dao;

	public ReportsServiceImpl() {
	}

	public List<Reports> findAllReports() {
		return dao.findAll();
	}

	public ReportsDao getDao() {
		return dao;
	}

	public void setDao(ReportsDao reportsDao) {
		this.dao = reportsDao;
	}
}
