package web.servlet;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import operations.ActivitiesLogic;
import persistence.ConexionDB;
import data.model.Objective;
import data.model.Strategy;

/**
 * Servlet implementation class ActivitiesView
 */
@WebServlet("/ActivitiesView")
public class ActivitiesView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConexionDB conexionDB = new ConexionDB(); 
    private ActivitiesLogic act= new ActivitiesLogic();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivitiesView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out= response.getWriter();	
			List<Objective> listObjectives=act.getActivitiesView();
			if(listObjectives!=null) {
				out.println("<div class'panel-group' id='accordion'>");
				for (Objective ob:listObjectives) {
					
					out.println("<div class='panel panel-default'>");
					out.println("<div class='panel-heading'>");
					out.println("<h4 class='panel-title'>");
					out.println("<a data-toggle='collapse' data-parent='#accordion' href='#"+ob.getIdObjective()+"_ob'>");
					out.println(ob.getDescription());
					out.println("</a>");					
					out.println("</h4>");
					out.println("</div>");
					
					
					out.println("<div id='"+ob.getIdObjective()+"_ob' class='panel-collapse collapse in'>");
					out.println("<div class='panel-body'>");
					for (Strategy stra: ob.getListStrategies()) {
						
					
					
					out.println("<div class='panel panel-default'>");//
					out.println("<div class='panel-heading'>");//
					out.println("<h4 class='panel-title'>");//
					out.println("<a data-toggle='collapse' data-parent='#accordion' href='#"+stra.getIdObjective()+"_st'>");//
					out.println(stra.getDescription());
					out.println("</a>");//					
					out.println("</h4>"); // h4 class
					out.println("</div>"); // panel heading strategy
					
					out.println("<div id='"+stra.getIdObjective()+"_st'' class='panel-collapse collapse in'>");//
					/*out.println("<div class='panel-body'>");//
					out.println("test");
					out.println("</div>");*/// panel body
					out.println("</div>");//panle collapse
					out.println("</div>"); // panel default
					
					
					}
					out.println("</div>");
					out.println("</div>");
					
					out.println("</div>");
				}
				out.println("</div>"); // accordion group
				
			}
			
		
	}

}
