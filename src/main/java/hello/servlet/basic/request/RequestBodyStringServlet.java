package hello.servlet.basic.request;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StreamUtils;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletInputStream inputStream = request.getInputStream(); // Body의 데이터를 가져옴
		String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // Byte 코드를 UTF-8로 변환

		System.out.println("messageBody = " + messageBody);

		response.getWriter().write("ok");
	}
}
