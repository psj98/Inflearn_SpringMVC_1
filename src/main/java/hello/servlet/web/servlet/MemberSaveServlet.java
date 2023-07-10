package hello.servlet.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

	private MemberRepository memberRepository = MemberRepository.getInstance();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age"));

		// Member 저장
		Member member = new Member(username, age);
		memberRepository.save(member);

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");

		PrintWriter w = response.getWriter();
		w.write("<html>\n");
		w.write("<head>\n");
		w.write("	<meta charset=\"UTF-8\">\n");
		w.write("</head>\n");
		w.write("<body>\n");
		w.write("	성공\n");
		w.write("	<ul>\n");
		w.write("		<li>id=" + member.getId() + "</li>\n");
		w.write("		<li>username=" + member.getUsername() + "</li>\n");
		w.write("		<li>age=" + member.getAge() + "</li>\n");
		w.write("	</ul>\n");
		w.write("	<a href=\"/index.html\">메인</a>\n");
		w.write("	<a href=\"/servlet/members\">모든 정보 조회</a>\n");
		w.write("</body>\n");
		w.write("</html>");
	}
}
