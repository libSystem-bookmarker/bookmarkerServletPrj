package com.bookmark.myweb.common;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.controller.common.ErrorController;

/**
 * @author yeonsoo
 * @create 2025.07.04 uri, class 매핑
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, CommandController> commandControllerMap = new HashMap<>(); // uri, class를 key, value로 저장

	public void init() throws ServletException {// 서블릿 초기화

		String configFile = getInitParameter("configFile");
		Properties prop = new Properties(); // 프로퍼티 객체 생성
		String configFilePath = getServletContext().getRealPath(configFile); // 설정 파일의 절대 경로

		try (FileReader reader = new FileReader(configFilePath)) { // 파일에서 스트림을 통해 프로퍼티 읽어
			prop.load(reader); // 입력 스트림에서 데이터를 읽어 프로퍼티 설정
		} catch (IOException e) {
			throw new ServletException(e);
		}

		Iterator<?> keys = prop.keySet().iterator(); // 프로퍼티에서 키 집합을 나열 객체로 반환
		while (keys.hasNext()) { // 키의 개수만큼 실행
			String uri = (String) keys.next(); // 키 집합에서 uri get
			String controllerClassName = prop.getProperty(uri); // class name get
			try {
				Class<?> controllerClass = Class.forName(controllerClassName); // 컨트롤러 클래스 이름으로 객체 생성
				CommandController controllerInterface = (CommandController) controllerClass.getDeclaredConstructor()
						.newInstance();
				;
				commandControllerMap.put(uri, controllerInterface); // 커맨드, 커맨드 객체를 키, 밸류 형식으로 맵에 저장
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}

	private void processServlet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String command = request.getRequestURI().substring(request.getContextPath().length());
		CommandController controller = commandControllerMap.get(command); // uri 를 이용해 맵으로부터 커맨드 핸들러 객체 찾음

		if (controller == null) {
			controller = new ErrorController();
		}

		String viewPage = null;

		try {// process는 명령을 처리하고 뷰 페이지 반환
			viewPage = controller.process(request, response);
			// DispatcherServlet.java
			if (viewPage == null) return;

			if ((viewPage != null) && (viewPage.indexOf("redirect:") == 0)) { // 뷰 이름 앞에 리다이렉트가 붙으면 리다이렉트
				viewPage = viewPage.substring(9); // 9는 redirect의 길이
				response.sendRedirect(request.getContextPath() + viewPage);
				return;
			}
		} catch (Throwable e) {
			throw new ServletException(e);
		}

		if (viewPage != null) {
			if (!viewPage.startsWith("/WEB-INF/")) {
				viewPage = "/WEB-INF/views/" + viewPage;
			}
		} else {
			viewPage = "/WEB-INF/views/index.jsp";
		}

		RequestDispatcher disp = request.getRequestDispatcher(viewPage);
		disp.forward(request, response);

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processServlet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processServlet(request, response);
	}

}
