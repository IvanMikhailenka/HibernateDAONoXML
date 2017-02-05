package qrizzly.project;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import qrizzly.project.model.Reports;
import qrizzly.project.service.AllService;
import qrizzly.project.spring.AppConfig;

import java.util.List;

/**
 * Created by Ivan on 05.02.2017.
 */
public class k {
  public static void main(String... args){
    AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

//        ReportsDao reportsDao = (ReportsDao) context.getBean("reportsDao");
//        List<Reports> allReports = reportsDao.findAll();
//        for (Reports emp : allReports) {
//            System.out.println(emp);
//        }
//
//        ReportsService service = (ReportsService) context.getBean("reportsS");
//        List<Reports> reportss = service.findAll();
//        for (Reports emp : reportss) {
//            System.out.println(emp);
//        }
    AllService allService = (AllService) context.getBean("allService1");


    Reports reports = new Reports();
    reports.setPerformer("Kate");
    reports.setActivity("someActivity");

   // allService.getReportsDao().add(reports);
    // allService.getReportsDao().delete(1);
    List<Reports> reportsList = allService.getReportsDao().findAll();
    for (Reports emp : reportsList) {
      System.out.println(emp);
    }



    context.close();

  }
}