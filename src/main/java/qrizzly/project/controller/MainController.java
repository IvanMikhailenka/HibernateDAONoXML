package qrizzly.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import qrizzly.project.model.Reports;
import qrizzly.project.service.ReportsService;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {
	@Autowired
	ReportsService reportsService;

	List<Reports> reportsList;
	@RequestMapping(method = RequestMethod.GET)
	public String sayHello(ModelMap model) {
		model.addAttribute("hello", "Hello World from Spring 4 MVC");
		return "/index";
	}

	@RequestMapping(method = RequestMethod.GET)
	public void setReportsList() {
		reportsList = reportsService.findAllReports();
		ololo(reportsList);
	}
	private void ololo(List<Reports> reportsList){
		for (Reports report :
				reportsList) {
			System.out.println(report);
		}

	}
}