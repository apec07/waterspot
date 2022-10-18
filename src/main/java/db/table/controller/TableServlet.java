package db.table.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import db.table.model.TableDAO;

/**
 * Servlet implementation class TableList
 */
public class TableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Logger LOGGER = LogManager.getLogger(this.getClass());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TableServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		HttpSession session = req.getSession();
		String selectedUrl = (String)session.getAttribute("dbUrl");
		LOGGER.info("selectedUrl - " +selectedUrl);
		List<String> tables = new TableDAO(selectedUrl).getAllTables();
		req.setAttribute("tables", tables);
		req.getRequestDispatcher("/backend").forward(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
