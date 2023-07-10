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

import com.fasterxml.jackson.databind.ObjectMapper;

import hello.servlet.basic.HelloData;

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

	private ObjectMapper objectMapper = new ObjectMapper(); // JSON 형식을 클래스에 맞게 매핑하는 객체

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletInputStream inputStream = request.getInputStream(); // Body의 데이터를 가져옴
		String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // Byte 코드를 UTF-8로 변환

		System.out.println("messageBody = " + messageBody);

		HelloData helloData = objectMapper.readValue(messageBody, HelloData.class); // HelloData 클래스의 변수에 맞게 JSON 객체를 매핑

		System.out.println("helloData.username = " + helloData.getUsername());
		System.out.println("helloData.age = " + helloData.getAge());

		response.getWriter().write("ok");
	}
}
