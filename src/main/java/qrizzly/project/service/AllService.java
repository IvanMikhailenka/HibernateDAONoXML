package qrizzly.project.service;

import org.springframework.stereotype.Service;
import qrizzly.project.store.ReportsDao;

/**
 * Created by Ivan on 29.01.2017.
 */
@Service
public class AllService {
    ReportsDao reportsDao;

    public AllService() {
    }

    public ReportsDao getReportsDao() {
        return reportsDao;
    }

    public void setReportsDao(ReportsDao reportsDao) {
        this.reportsDao = reportsDao;
    }
}
