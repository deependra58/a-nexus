package com.texas.anexus.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.texas.anexus.repository.LoginRepository;
import com.texas.anexus.services.LoginService;
import com.texas.anexus.util.Constant;
import com.texas.anexus.util.StringUtils;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AnexusFilter implements Filter {

	private static final Logger LOG = LoggerFactory.getLogger(AnexusFilter.class);

	@Value("${anexus.token.check}")
	private boolean check = false;

	@Autowired
	private LoginService loginService;
	@Autowired
	private LoginRepository loginRepository;

	private Long loginId;

	// private Long customerId;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		LOG.debug("called filter");
		//System.out.println("\n\n\n\n\n" + "Hello from LocalLevelFilter");
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		Enumeration<String> headerNames = request.getHeaderNames();

		Map<String, String> requestHeaders = new HashMap<String, String>();

		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = ((HttpServletRequest) req).getHeader(key);
			requestHeaders.put(key, value);
		}
		String originHeaders = requestHeaders.get("Origin");
		response.setHeader("Access-Control-Allow-Origin", originHeaders);
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization,userId,token,loginId,customerId");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Max-Age", "86400");
		String loginIdString = request.getHeader("loginId");
		if (loginIdString == null || loginIdString.isEmpty())
			loginIdString = request.getHeader("userId");
		String token = request.getHeader("token");
		Long currentUserId = ((null == loginIdString || loginIdString.isEmpty())) ? 0L : Long.valueOf(loginIdString);
		this.loginId = currentUserId;
		String url = ((HttpServletRequest) req).getRequestURL().toString();
		LOG.debug("Accepted request: {}", url);
		System.out.println("Accepted request from}" + url);
		
		if (check) {
			LOG.debug("Validating token.");
			System.out.println("here1");
			if (url.contains("/api/v1")) {
				System.out.println("here2");
				if (!url.contains(Constant.LOGIN_API_V1) && !url.contains(Constant.LOGOUT_API_V1)&&!url.contains(Constant.USER_API_V1)&&!url.contains(Constant.ADDRESS_API_V1)&&!url.contains(Constant.TOKENCHECK_API_V1)
						&& !url.contains(Constant.STATUS_API) && !loginService.isValidToken(currentUserId, token)) {
					System.out.println("here3");
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					PrintWriter out = response.getWriter();
					out.print("{\"message\": \"" + Constant.TOKEN_INVALID_OR_EXPIRED + "\"}");
					out.flush();
					LOG.debug("You are not Authorized");
					return;
					
				}
				// validateMethodPermission(request, url, response);
			}
		}
		filterChain.doFilter(req, response);
	}

	/**
	 * It validates what method to allow to user.
	 * 
	 * @author Deependra karki
	 * @throws IOException
	 * @since 1.0.0
	 */
	@SuppressWarnings("unused")
//	private void validateMethodPermission(HttpServletRequest request, String url,
//			HttpServletResponse response) throws IOException {
//		if (request.getMethod().equals(RequestMethod.PUT.toString())
//				|| request.getMethod().equals(RequestMethod.DELETE.toString())) {
//			if (url.contains("/auth/api/v1/customers")) {
//				Login login = loginRepository.getOne(loginId);
//				if (login.getUserRole().equals(UserRole.USER)) {
//
//					returnMessage(response,
//							"{\"message\": \"" + "You are not Authorized \"}");
//				}
//			}
//		}
//
//	}

	/**
	 * Returns the message to the client.
	 * 
	 * @author Deependra Karki
	 * @throws IOException
	 * @since 1.0.0
	 */
	private void returnMessage(HttpServletResponse response, String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(message);
		out.flush();
		LOG.debug("You are not Authorized");
		return;

	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

	boolean didOriginateFrom(List<String> domains, String requestHost) {
		return StringUtils.contains(domains, requestHost);
	}

	public Long getLoginId() {
		return this.loginId;
	}

}
