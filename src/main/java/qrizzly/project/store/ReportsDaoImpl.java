package qrizzly.project.store;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import qrizzly.project.model.Reports;

import java.util.List;

@Repository
public class ReportsDaoImpl extends AbstractDao implements ReportsDao {

	public ReportsDaoImpl() {
	}

	@SuppressWarnings("unchecked")
	public List<Reports> findAll() {
		return (List<Reports>) getSession().createQuery("FROM Reports ").list();
	}

	@Override
	public void add(Reports reports) {
		Session session = getSession();
		session.save(reports);
	}

	@Override
	public void delete(int id) {
		Session session = getSession();
		Query query= session.createQuery("delete Reports as reports where reports.id=:id");
		query.setParameter("id", id);
		query.executeUpdate();
	}


}
