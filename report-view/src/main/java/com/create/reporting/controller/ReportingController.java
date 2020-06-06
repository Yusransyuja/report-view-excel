package com.create.reporting.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.create.reporting.reportview.ReportExcel;

@Controller
@RequestMapping(value = "/reporting")
public class ReportingController {
	
	private static Logger logger = LogManager.getLogger(ReportingController.class);

	@Autowired
	private ReportingService reportingService;
	
	@PostMapping(value = "/doDownload")
    public ModelAndView doDownload(Model model, HttpServletRequest httpRequest) {
    	List dataList = new ArrayList();
    	
        try {

            List dataListDB = this.reportingService.getAllDataEmployee();

            dataList.add(dataListDB);

        }catch (Exception e) {
            logger.error("Exception", e);

            httpRequest.setAttribute("errorMessage", ApplicationConstants.RESPONSE_DESC_GLOBAL_ERROR);
        }

        return new ModelAndView(new ReportExcel(), "report", dataList);
    }
}
