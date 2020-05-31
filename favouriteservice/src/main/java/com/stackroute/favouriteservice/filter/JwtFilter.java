package com.stackroute.favouriteservice.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtFilter extends GenericFilterBean{
	

	public JwtFilter() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		final HttpServletResponse res = (HttpServletResponse) response;
		final HttpServletRequest req = (HttpServletRequest) request;
		res.setHeader("Access-Control-Allow-Origin", "*");

		res.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");

		res.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		final String authHeader = req.getHeader("authorization");
		
		if("OPTIONS".equals(req.getMethod()))
		{
			res.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(request, response);
		}else if(authHeader == null || !authHeader.startsWith("Bearer ")){
			throw new ServletException("Invalid or missing Header");
			
			
		}else{
			
		final String token = authHeader.substring(7);
		final Claims claims1 = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		

		request.setAttribute("claims", claims1);
		chain.doFilter(request, response);
		}
	}


}
