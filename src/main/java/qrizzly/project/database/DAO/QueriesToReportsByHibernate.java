package qrizzly.project.database.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import qrizzly.project.database.hibernate.HibernateUtil;
import qrizzly.project.model.Reports;

import java.util.List;


public class QueriesToReportsByHibernate implements ReportsDAO {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    // получаем все записи из таблици reports
    public List<Reports> getAllReports() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Reports");
        session.getTransaction().commit();
        return query.list();
    }
}
