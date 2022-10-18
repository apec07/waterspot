package backend;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class Moring
 */
public class Moring extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Logger LOGGER = LogManager.getLogger(this.getClass()); 
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		LOGGER.info("Morgin get");
		PrintWriter out = res.getWriter();
		out.println("Hello - ");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}
 */
}
