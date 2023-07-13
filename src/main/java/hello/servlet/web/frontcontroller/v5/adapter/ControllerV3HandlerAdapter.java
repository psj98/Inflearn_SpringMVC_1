package hello.servlet.web.frontcontroller.v5.adapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

	@Override
	public boolean supports(Object handler) {
		return (handler instanceof ControllerV3); // ControllerV3이면 true
	}

	@Override
	public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws ServletException, IOException {
		ControllerV3 controller = (ControllerV3) handler; // supports로 ControllerV3임을 확인하기 때문에 캐스팅 가능

		Map<String, String> paramMap = createParamMap(request);
		ModelView mv = controller.process(paramMap);

		return mv;
	}

	private Map<String, String> createParamMap(HttpServletRequest request) {
		Map<String, String> paramMap = new HashMap<>();
		request.getParameterNames().asIterator()
				.forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
		return paramMap;
	}
}
