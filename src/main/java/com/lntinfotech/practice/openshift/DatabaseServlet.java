/**
 * 
 */
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
 * @author 10647868
 *
 */
public class DatabaseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6829138006856413892L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		
		printDatabaseInformation(out);
	}

	private void printDatabaseInformation(PrintWriter out) {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://mysql:3306/sampledb", "os_mysql", "os_mysql");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from books");
			out.println("<pre>");
			out.println("Books list");
			while (rs.next()) {
				out.println(rs.getString("book_title") + "::" + rs.getString("author"));
			}
			out.println("</pre>");
			rs.close();
		} catch (Exception e) {
			out.println("Error while connecting databse: " + e.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					out.println("Error while closing connection." + e.getMessage());
				}
			}
		}
	}

}
