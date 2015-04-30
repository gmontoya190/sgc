package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import operations.Notification;
import data.model.Format;
import persistence.ConexionDB;

/**
 * Servlet implementation class NotificationServlet
 */
@WebServlet("/NotificationServlet")
public class NotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConexionDB conexionDB = new ConexionDB(); 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotificationServlet() {
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
		// TODO Auto-generated method stub
		if(conexionDB.connect()!=null) {
			List <Format> listFormat= conexionDB.getFormatsNapproved();			
			HttpSession session = request.getSession(false);
			String action=(String)session.getAttribute("action");
			String idFormat=request.getParameter("id_format");
			if(idFormat==null) {
				PrintWriter out= response.getWriter();
				if(listFormat!=null){
					out.println("<div class='table-responsive'>");
					out.println("<table class='table table-striped'>");
					out.println("<thead>");
					out.println("<tr>");
					out.println("<th>Nombre Formato</th>");
					out.println("<th>Id Formato</th>");
					out.println("<th>Id Proceso</th>");
					out.println("<th>Versi&oacute;n</th>");
					out.println("<th>Extension</th>");
					out.println("<th>Acciones</th>");
					out.println("</tr>");
					out.println("</thead>");
					out.println("<tbody>");
					System.out.println("size of formats "+listFormat.size());
					for (Format format : listFormat) {
						out.println("<tr>");
						out.println("<td>");
						out.println(format.getNameFormat());
						out.println("</td>");
						out.println("<td>");
						out.println(format.getIdFormat());
						out.println("</td>");
						out.println("<td>");
						out.println(format.getProcessId());
						out.println("</td>");
						out.println("<td>");
						out.println(format.getVersion());
						out.println("</td>");
						out.println("<td>");
						out.println(format.getExtension());
						out.println("</td>");
						out.println("<td>");
						/*out.println("<div class='container-fluid'>");
					out.println("<div class='row'>");
					out.println("<div class='col-md-3'>");*/
						out.println("<form  action='NotificationServlet' method='post'>");
						out.println("<button name='id_format' value='accept_"+format.getIdFormat()+"_"+format.getVersion()+"' "
								+ "type='submit' class='btn btn-primary'>"
								+ "<span class='glyphicon glyphicon-ok'></span></button>");
						out.println("<button name='id_format' value='reject_"+format.getIdFormat()+"_"+format.getVersion()+"' "
								+ "type='submit' class='btn btn-primary'>"
								+ "<span class='glyphicon glyphicon-remove'></span></button>");
						out.println("<button name='id_format' id='id_format' value='download_"+format.getIdFormat()+"_"+format.getVersion()+"'"
								+ " type='submit' class='btn btn-primary'>"
								+ "<span class='glyphicon glyphicon-download-alt'></span></button>");
						out.println("</form>");
						/*out.println("</a>");
					out.println("</div>");
					out.println("</div>");
					out.println("</div>");*/
						out.println("</td>");
						out.println("</tr>");
					}
					out.println("</tbody>");
					out.println("</table>");
					out.println("</div>");	
					//session.setAttribute("action", "modify");
				}
			} else {
				String[] split=idFormat.split("_");
				if(conexionDB.connect()!=null) {
				Format format=conexionDB.getFormat(split[1], split[2]);
				String from="/Users/grille/Documents/webdevel/SGC/WebContent/formatos/"
						+format.getProcessId()+"_pendiente"+"/"+format.getNameFormat()+"_"+format.getVersion()
						+"."+format.getExtension();
				String to="/Users/grille/Documents/webdevel/SGC/WebContent/formatos/"
						+format.getProcessId()+"/"+format.getNameFormat()+"_"+format.getVersion()+"."+format.getExtension();
			
					if("accept".equals(split[0])) {
						if(conexionDB.connect()!=null) {
							conexionDB.updateApprovalFormat(split[1], split[2], "Y");
							format.moveFormat(from,to);
							Notification noti= new Notification();
							String text="Formato con id= "+split[1]+" y version= "+split[2]+" ha sido aprobado";
							String user=(String)session.getAttribute("user");
							noti.sendEmail(format.getUserLastModification(), text);
							response.sendRedirect("WelPageLead.jsp");
						}		
						 
					} else if("reject".equals(split[0])) {
						if(conexionDB.connect()!=null) {
							conexionDB.updateActiveFormat(split[1], split[2], "N");
							format.deleteFile(from);
							Notification noti= new Notification();
							String text="Formato con id= "+split[1]+" y version= "+split[2]+"no ha sido aprobado";
							String user=(String)session.getAttribute("user");
							noti.sendEmail(format.getUserLastModification(), text);
							response.sendRedirect("WelPageLead.jsp");
						}
						
					} else {
						session.setAttribute("action","downloadPendiente");
						session.setAttribute("format",format);
						request.getServletContext().getRequestDispatcher("/ActionFormat").forward(request, response);
						// download
					}
					
					
				
				}
			}
	
		}
	}

}
