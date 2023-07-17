package hello.servlet.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hello.servlet.domain.member.MemberRepository;

@WebServlet(name = "memberFormServlet", urlPatterns = "/servlet/members/new-form")
public class MemberFormServlet extends HttpServlet {

	private MemberRepository memberRepository = MemberRepository.getInstance();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");

		PrintWriter w = response.getWriter();
		w.write("<!DOCTYPE html>\n");
		w.write("<html>\n");
		w.write("<head>\n");
		w.write("	<meta charset=\"UTF-8\">\n");
		w.write("	<title>Title</title>\n");
		w.write("</head>\n");
		w.write("<body>\n");
		w.write("	<form action=\"/servlet/members/save\" method=\"post\">\n");
		w.write("		username: <input type=\"text\" name=\"username\" />\n");
		w.write("		age: <input type=\"text\" name=\"age\" />\n");
		w.write("		<button type=\"submit\">전송</button>\n");
		w.write("	</form>\n");
		w.write("</body>\n");
		w.write("</html>\n");
	}
}
