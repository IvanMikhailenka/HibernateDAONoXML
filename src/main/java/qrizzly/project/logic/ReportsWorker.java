package qrizzly.project.logic;

import qrizzly.project.database.DAO.QueriesToReportsByHibernate;
import qrizzly.project.model.Reports;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ivan on 20.01.2017.
 */
public class ReportsWorker {
    private QueriesToReportsByHibernate result = new QueriesToReportsByHibernate();
    private List<Reports> reportsList = new ArrayList<>(result.getAllReports());
    //получаем список всех исполнителей
    public List<String> getAllPerformers(){
        List<String> performerList = new ArrayList<>();
        for (Reports reports :
                reportsList) {
            performerList.add(reports.getPerformer());
        }
        return performerList;
    }
    //в зависимости от дат и исполнителя получаем список отчетов
    public List<Reports> getReportsByDateAndPerformer(String startDate, String endDate, String performer) throws ParseException {
        List<Reports> newReportsList = new ArrayList<>();
        long start = checkStartDate(startDate);
        long end = checkEndDate(endDate);

        for (Reports report : reportsList) {
            long startDateDb = report.getStartDate().getTime();
            long endDatedb = report.getEndDate().getTime();
            if((start-startDateDb)<=0 && (end-endDatedb)>=0 && performer.equals("All performers")) {
                newReportsList.add(report);
            }
            else if((start-startDateDb)<=0 && (end-endDatedb)>=0 && performer.equals(report.getPerformer())){
                newReportsList.add(report);
            }
        }
        return newReportsList;
    }
    //проверяем дату на корректный ввод и соответствию фармату MMM dd, yyyy
    private long checkStartDate(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd,yyyy", Locale.ENGLISH);
        if(date==null || date.equals("")){
            return 0;
        }else {
            return dateFormat.parse(date).getTime();
        }
    }
    private long checkEndDate(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd,yyyy", Locale.ENGLISH);
        if(date==null || date.equals("")){
            return new Date().getTime();
        }else {
            return dateFormat.parse(date).getTime();
        }
    }

    public List<Reports> getReportsList() {
        return reportsList;
    }
}