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
 * Servlet implementation class FortmatsServlet
 */
@WebServlet("/FortmatsServlet")
public class FormatsViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConexionDB conexionDB = new ConexionDB();   
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FormatsViewServlet() {
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
		// cargar los  formatos disponibles para el proceso

		HttpSession session = request.getSession(false);
		String idProcess=(String)session.getAttribute("process");
		String leadProcess=(String)session.getAttribute("leadProcess");
		response.setContentType("text/html; charset=utf-8");		
		String action=request.getParameter("action");
		String actionFormatExistent=request.getParameter("id_format");
		String loadCreateFormat=request.getParameter("createFormatView"); // Carga el contenido para crear formatos.
		String nombreFormat=request.getParameter("nombreFormato"); // redirige pa subir el formato
		if (action!=null && "loadFormats".equals(action)) {
			PrintWriter out= response.getWriter();
			if(conexionDB.connect()!=null) {
				System.out.println("process id "+idProcess);
				List<Format> listFormat= null;
				if ("Y".equals(leadProcess)) {
					listFormat=conexionDB.getFormatsByProcessOrNo(idProcess, "Y");
				} else {
					listFormat=conexionDB.getFormatsByProcessOrNo(idProcess, "N");
				}
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
						out.println("<form  action='FortmatsServlet' method='post'>");
						out.println("<button name='id_format' id='id_format' value='modify_"+format.getIdFormat()+"_"+format.getVersion()+"' "
								+ "type='submit' class='btn btn-primary'>"
								+ "<span class='glyphicon glyphicon-pencil'></span></button>");
						if("N".equals(leadProcess)) {
							out.println("<button name='id_format' value='reject_"+format.getIdFormat()+"_"+format.getVersion()+"' "
									+ "type='submit' class='btn btn-primary'>"
									+ "<span class='glyphicon glyphicon-remove'></span></button>");
						}						
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
				}			

			}
			
			out.println("<div class='form-group'>");
			//out.println("<div class='col-lg-offset-2 col-lg-10'>");
			out.println("<form  action='FortmatsServlet' method='post'>");
			out.println("<button type='submit'name='createFormatView' class='btn btn-primary'>Crear Nuevo Formato</button>");
			out.println("</form>");
			out.println("</div>");	
			
		} else if (actionFormatExistent!=null) {
			
			
			String idFormat=request.getParameter("id_format");
			String[] split=idFormat.split("_");
			if(conexionDB.connect()!=null) {
				Format format=conexionDB.getFormat(split[1], split[2]);
				session.setAttribute("format",format );
				//session.setAttribute("action",split[0] );
				if("modify".equals(split[0])) {
					session.setAttribute("action","modify" );
					request.getServletContext().getRequestDispatcher("/modifyFormat.jsp").forward(request, response);
					//request.getServletContext().getRequestDispatcher("/uploadFile.jsp").forward(request, response);
				} else if ("download".equals(split[0])){
					request.getServletContext().getRequestDispatcher("/ActionFormat").forward(request, response);
				} else {
						if(conexionDB.connect()!=null) {
						conexionDB.updateActiveFormat(split[1], split[2], "N");
						Notification noti= new Notification();
						String text="Formato con id= "+split[1]+" y version= "+split[2]+"no ha sido eliminado";
						noti.sendEmail(format.getUserLastModification(), text);
						response.sendRedirect("WelPageLead.jsp");
						}
				}
				
			
			}
			
		} else if (loadCreateFormat!=null) {
			session.setAttribute("action","create" );
			request.getServletContext().getRequestDispatcher("/createFormat.jsp").forward(request, response);
		} else if(nombreFormat!=null) {
			String actionFormat = (String) session.getAttribute("action");
			if("create".equals(actionFormat)) {
				String nameFormat=request.getParameter("nombreFormato");
				String id=request.getParameter("idProceso");
				String  version=request.getParameter("version");
				String  idFormat=request.getParameter("idFormato");
				Format newFormat= new Format();
				newFormat.setNameFormat(nameFormat);
				newFormat.setProcessId(id);
				newFormat.setVersion(version);
				newFormat.setIdFormat(idFormat);
				session.setAttribute("format",newFormat);
				session.setAttribute("newFormat","Y");
				session.setAttribute("action","modify" );
			} else {
				Format format=(Format)session.getAttribute("format");
				String nameFormat=request.getParameter("nombreFormato");
				String  version=request.getParameter("version");
				format.setNameFormat(nameFormat);
				format.setVersion(version);
				session.setAttribute("format",format);
			}
			request.getServletContext().getRequestDispatcher("/uploadFile.jsp").forward(request, response);
		}
		


	}

}
