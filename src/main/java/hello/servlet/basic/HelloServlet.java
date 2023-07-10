package hello.servlet.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "helloServlet", urlPatterns = "/hello") // '/hello'로 요청이 오면, 해당 클래스 실행
public class HelloServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("HelloServlet.service");
		System.out.println("request = " + request);
		System.out.println("response = " + response);

		String username = request.getParameter("username"); // 요청받은 파라미터 값 -> /hello?username=__
		System.out.println("username = " + username);

		// encoding 설정
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write("hello " + username);
	}
}
