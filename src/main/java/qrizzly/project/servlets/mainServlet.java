package qrizzly.project.servlets;

import qrizzly.project.logic.DateCalculator;
import qrizzly.project.logic.ReportsWorker;
import qrizzly.project.model.Reports;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

;

/**
 * Created by Ivan on 19.01.2017.
 */
@WebServlet(urlPatterns = "/mainServlet")
public class mainServlet extends HttpServlet {
    private ReportsWorker reports = new ReportsWorker();
    private List<String> performerList = new ArrayList<>(reports.getAllPerformers());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setDataToMainPage(req);

        req.getRequestDispatcher("/pages/mainPage.jsp").forward(req, resp);
    }
    // отображает таблицу после нажатия кнопки Submit
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setDataToMainPage(req);

        List<Reports> reportsList = new ArrayList<>();
        try {
            String startDate = req.getParameter("startDate");
            String endDate = req.getParameter("endDate");
            String performer = req.getParameter("Performer");
            reportsList.addAll(reports.getReportsByDateAndPerformer(startDate, endDate, performer));
            if(reportsList.isEmpty()){
                suchReportsNotFound(req,resp);
            }

        } catch (ParseException e) {
            suchReportsNotFound(req,resp);
        }
        req.setAttribute("reportsList", reportsList);
        req.setAttribute("StartDate", "StartDate");
        req.setAttribute("EndDate", "EndDate");
        req.setAttribute("Performer", "Performer");
        req.setAttribute("Activity", "Activity");
        req.getRequestDispatcher("/pages/mainPage.jsp").forward(req, resp);

    }


    // метод устанавливает значания в выплывающие списки
    private void setDataToMainPage(HttpServletRequest req){
        req.setAttribute("performerList", performerList);
        req.setAttribute("LastCalendarStartDate", DateCalculator.getLastYearStartDate());
        req.setAttribute("LastCalendarEndDate", DateCalculator.getLastYearEndDate());
        req.setAttribute("LastQtrStartDate", DateCalculator.getLastQtrStartDate());
        req.setAttribute("LastQtrEndDate", DateCalculator.getLastQtrEndDate());
        req.setAttribute("LastMonthEnd", DateCalculator.getLastMonthEnd());
        req.setAttribute("LastMonthStart", DateCalculator.getLastMonthStart());
        req.setAttribute("CurrentDate", DateCalculator.getCurrentDate());
        req.setAttribute("CurrentQtr", DateCalculator.getCurrentQtrStartDate());
        req.setAttribute("CurrentMonth", DateCalculator.getCurrentMonthStart());
        req.setAttribute("CurrentYear", DateCalculator.getCurrentYearStartDate());
    }
    private void suchReportsNotFound(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("StartDate", "Such Report Not Found");
        req.getRequestDispatcher("/pages/mainPage.jsp").forward(req, resp);
        return;
    }

}
