package com.bookmark.myweb.common;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.controller.common.ErrorController;

/**
 * @author yeonsoo
 * @create 2025.07.04 uri, class 매핑
 */
@MultipartConfig(
    maxFileSize = 10 * 1024 * 1024,       // 10MB
    maxRequestSize = 20 * 1024 * 1024,    // 20MB
    fileSizeThreshold = 1024              // 1KB
//@MultipartConfig( // 이거 추가를 httpServlet을 상속받는 서블릿에 해야한다고 한다..
//	    location = "C:/upload/book",              // 업로드 임시 경로
//		maxFileSize = 10 * 1024 * 1024, // 10MB
//		maxRequestSize = 20 * 1024 * 1024, // 20MB
//		fileSizeThreshold = 1024 // 1KB 메모리 버퍼
)
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, CommandController> commandControllerMap = new HashMap<>();

	@Override
	public void init() throws ServletException {
		String configFile = getInitParameter("configFile");
		Properties prop = new Properties();
		String configFilePath = getServletContext().getRealPath(configFile);

		try (FileReader reader = new FileReader(configFilePath)) {
			prop.load(reader);
		} catch (IOException e) {
			throw new ServletException(e);
		}

		Iterator<?> keys = prop.keySet().iterator();
		while (keys.hasNext()) {
			String uri = (String) keys.next();
			String controllerClassName = prop.getProperty(uri);
			try {
				Class<?> controllerClass = Class.forName(controllerClassName);
				CommandController controllerInstance = (CommandController) controllerClass.getDeclaredConstructor().newInstance();
				commandControllerMap.put(uri, controllerInstance);
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}

	private void processServlet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String command = request.getRequestURI().substring(request.getContextPath().length());

		// 루트 접근 시 index.do로 리디렉션
		if (command.equals("/") || command.equals("")) {
			response.sendRedirect(request.getContextPath() + "/index.do");
			return;
		}

		CommandController controller = commandControllerMap.get(command);
		if (controller == null) {
			controller = new ErrorController();
		}

		String viewPage = null;

		try {
			viewPage = controller.process(request, response);
			if (viewPage == null) return;
			// DispatcherServlet.java
			if (viewPage == null)
				return;

			if (viewPage.startsWith("redirect:")) {
				viewPage = viewPage.substring(9);
				response.sendRedirect(request.getContextPath() + viewPage);
				return;
			}
		} catch (Throwable e) {
			throw new ServletException(e);
		}

		if (!viewPage.startsWith("/WEB-INF/")) {
			viewPage = "/WEB-INF/views/" + viewPage;
		if (viewPage != null) {
			if (!viewPage.startsWith("/WEB-INF/")) {
				viewPage = "/WEB-INF/views/" + viewPage;
			} else {
				viewPage = "/WEB-INF/views/index.jsp";
			}
		}

		RequestDispatcher disp = request.getRequestDispatcher(viewPage);
		disp.forward(request, response);
	}
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processServlet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processServlet(request, response);
	}
}
