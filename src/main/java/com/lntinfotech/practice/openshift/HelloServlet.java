package com.lntinfotech.practice.openshift;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

		PrintWriter out = resp.getWriter();

		resp.setContentType("text/html");

		printEnvVar(out);

		printConfigMapVar(out);

		printConfigFromFile(out);

		out.flush();
		out.close();
	}

	/**
	 * @param out
	 */
	private void printConfigFromFile(PrintWriter out) {
		out.println("<pre>");
		String gameProperty = System.getenv("game_property");
		if (gameProperty != null) {
			out.println("Game Property : " + gameProperty);
		} else {
			out.println("Sorry, config map from property cannot be read.");
		}
		out.println("</pre>");
	}

	/**
	 * @param out
	 */
	private void printConfigMapVar(PrintWriter out) {
		out.println("<pre>");
		String propertyOne = System.getenv("example_property_1");
		String propertyTwo = System.getenv("example_property_2");

		if ((propertyOne != null) && (propertyTwo != null)) {
			out.println("Property 1: " + propertyOne);
			out.println("Property 2: " + propertyTwo);
		} else {
			out.println("Sorry, no config map found.");
		}
		out.println("</pre>");
	}

	/**
	 * @param resp
	 * @param username
	 * @param password
	 * @throws IOException
	 */
	private void printEnvVar(PrintWriter out) throws IOException {
		out.println("<pre>");
		String username = System.getenv("username");
		String password = System.getenv("password");
		if ((username != null) && (password != null)) {
			out.println("Hello " + username);
			out.println("Your password is " + password);
		} else {
			out.println("Sorry, no secret found.");
		}
		out.println("</pre>");
	}

}
