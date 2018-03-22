package com.lntinfotech.practice.openshift;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Hello world!
 *
 */
public class HelloServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1535044108697450691L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = System.getenv("username");
		String password = System.getenv("password");
		printEnvVar(resp, username, password);
	}

	/**
	 * @param resp
	 * @param username
	 * @param password
	 * @throws IOException
	 */
	private void printEnvVar(HttpServletResponse resp, String username, String password) throws IOException {
		PrintWriter out = resp.getWriter();
		if ((username != null) && (password != null)) {
			out.println("Hello " + username);
			out.println("Your password is " + password);
		} else {
			out.println("Sorry, no environment variable found.");
		}
		out.flush();
		out.close();
	}

}
