package db.listener;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Application Lifecycle Listener implementation class DbFinder
 *
 */
public class DbFinder implements ServletContextListener {
	
	public static String dbLoc;
	public Logger LOGGER = LogManager.getLogger(this.getClass());
    /**
     * Default constructor. 
     */
    public DbFinder() {
        dbLoc = new String(); 
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         LOGGER.info("contextDestroyed call");
         ServletContext ctx = sce.getServletContext();
         dbLoc = null ;
         ctx.removeAttribute("dbStr");
    }

	/**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent scae)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
          ServletContext ctx = sce.getServletContext();
          dbLoc = ctx.getRealPath("/WEB-INF/web.xml");
          LOGGER.info("contextInitialized called");
          
          LOGGER.info("dbLoc = "+dbLoc);
          
         if(getDBPath(dbLoc).size()>0) {
        	 LOGGER.info("db size - "+getDBPath(dbLoc).size());
        	 List<String> dbList = getDBPath(dbLoc);
        	 LOGGER.info(dbList.stream().map(Object::toString).collect(Collectors.joining(", ","DbList = [","]")));
        	 ctx.setAttribute("dbStr", dbList);
         }else {
     		LOGGER.warn("No DB Found!");
     	}
    }
    
  	private LinkedList<String> getDBPath(String webPath) {

          LinkedList<String> result = new LinkedList<>();

          DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

          try {

              try (InputStream is = new FileInputStream(webPath)) {

                  // unknown XML better turn on this
                  dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

                  DocumentBuilder dBuilder = dbf.newDocumentBuilder();
                  
                  Document doc = dBuilder.parse(is); // inputStream can not be null
                  
                  NodeList nodeList = null;
                  if(doc.hasChildNodes()) {
                  	nodeList = doc.getChildNodes();
                  }else {
                  	return null;
                  }
                  Element element = doc.getDocumentElement();

                  nodeList = element.getElementsByTagName("res-ref-name");
                  if (nodeList.getLength() > 0) {
                  	for(int i=0;i<nodeList.getLength();i++) {
                  		Node node = (Element) nodeList.item(i);
                  		String ranking = node.getTextContent();
                  		 if (!"".equals(ranking)) {
                  			 result.add(ranking);
//                               LOGGER.info("catched - "+ranking);
                           }
                  	}
                  }
              }

          } catch (Exception e) {
              e.printStackTrace();
          }
          

          return result;
      }
	
}
