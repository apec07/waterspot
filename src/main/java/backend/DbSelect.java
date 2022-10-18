package backend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class DbSelect
 */
public class DbSelect extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public String dbUrl;
	public Logger LOGGER = LogManager.getLogger(this.getClass());
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DbSelect() {
    	dbUrl = new String();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		ServletOutputStream out = res.getOutputStream();
		
		HttpSession session = req.getSession();
		String method = req.getParameter("action");
		if(method!=null) {
			LOGGER.info("method - "+method);
			dbUrl = req.getParameter("db");
			LOGGER.info("URL Selected - "+dbUrl);
			session.setAttribute("dbUrl", dbUrl);
			
		}else {
			LOGGER.error("Method is NULL");
			return;
		}
		res.sendRedirect(req.getContextPath()+"/backend/index.jsp");	
    	// return to JSP page for select
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
