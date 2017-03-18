package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.model.Reports;
import project.store.ReportsDao;

import java.util.List;

@Service("reportsServiceImpl")
@Transactional
public class ReportsServiceImpl implements ReportsService {

  @Autowired
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
