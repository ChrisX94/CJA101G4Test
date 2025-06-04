package com.shakemate.servicecase.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.shakemate.servicecase.model.*;

public class ServiceCaseServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 查詢單筆
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String str = req.getParameter("caseId");
				if (str == null || str.trim().isEmpty()) {
					errorMsgs.add("請輸入案件編號");
				}
				Integer caseId = Integer.valueOf(str);

				ServiceCaseService svc = new ServiceCaseService();
				ServiceCaseVO vo = svc.getOneServiceCase(caseId);
				if (vo == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/servicecase/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				req.setAttribute("serviceCaseVO", vo);
				RequestDispatcher successView = req.getRequestDispatcher("/servicecase/listOneServiceCase.jsp");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("查詢失敗: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/servicecase/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// 準備修改資料
		if ("getOne_For_Update".equals(action)) {
			Integer caseId = Integer.valueOf(req.getParameter("caseId"));
			ServiceCaseService svc = new ServiceCaseService();
			ServiceCaseVO vo = svc.getOneServiceCase(caseId);

			req.setAttribute("serviceCaseVO", vo);
			RequestDispatcher successView = req.getRequestDispatcher("/servicecase/update_servicecase_input.jsp");
			successView.forward(req, res);
		}

		// 修改
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer caseId = Integer.valueOf(req.getParameter("caseId"));
				Integer userId = Integer.valueOf(req.getParameter("userId"));
				Integer admId = Integer.valueOf(req.getParameter("admId"));
				Integer caseTypeId = Integer.valueOf(req.getParameter("caseTypeId"));
				String title = req.getParameter("title");
				String content = req.getParameter("content");
				Integer caseStatus = Integer.valueOf(req.getParameter("caseStatus"));

				Timestamp now = new Timestamp(System.currentTimeMillis());

				ServiceCaseService svc = new ServiceCaseService();
				ServiceCaseVO vo = svc.updateServiceCase(caseId, userId, admId, caseTypeId, null, now, title, content, caseStatus);

				req.setAttribute("serviceCaseVO", vo);
				RequestDispatcher successView = req.getRequestDispatcher("/servicecase/listOneServiceCase.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("修改失敗: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/servicecase/update_servicecase_input.jsp");
				failureView.forward(req, res);
			}
		}

		// 新增
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer userId = Integer.valueOf(req.getParameter("userId"));
				Integer admId = Integer.valueOf(req.getParameter("admId"));
				Integer caseTypeId = Integer.valueOf(req.getParameter("caseTypeId"));
				String title = req.getParameter("title");
				String content = req.getParameter("content");
				Integer caseStatus = Integer.valueOf(req.getParameter("caseStatus"));

				Timestamp now = new Timestamp(System.currentTimeMillis());

				ServiceCaseService svc = new ServiceCaseService();
				svc.addServiceCase(userId, admId, caseTypeId, now, now, title, content, caseStatus);

				RequestDispatcher successView = req.getRequestDispatcher("/servicecase/listAllServiceCase.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("新增失敗: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/servicecase/addServiceCase.jsp");
				failureView.forward(req, res);
			}
		}

		// 刪除
		if ("delete".equals(action)) {
			try {
				Integer caseId = Integer.valueOf(req.getParameter("caseId"));
				ServiceCaseService svc = new ServiceCaseService();
				svc.deleteServiceCase(caseId);

				RequestDispatcher successView = req.getRequestDispatcher("/servicecase/listAllServiceCase.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				req.setAttribute("errorMsgs", List.of("刪除失敗: " + e.getMessage()));
				RequestDispatcher failureView = req.getRequestDispatcher("/servicecase/listAllServiceCase.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
