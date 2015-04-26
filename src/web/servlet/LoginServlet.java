package web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistence.ConexionDB;
import data.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConexionDB conexionDB = new ConexionDB();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		
		String user= request.getParameter("user");
		String password= request.getParameter("password");
		System.out.println("user.. "+user);
		
		if(conexionDB.connect()!=null) {
			User userBD= conexionDB.getIfUserExists(user, password);
			if(userBD!=null){
				HttpSession session = request.getSession(false);
				session.setAttribute("user", user);
				session.setAttribute("process", userBD.getIdProcess());
				session.setAttribute("leadProcess", userBD.getProcessLead());
			    response.sendRedirect("WelPageLead.jsp");														
				
			}			
			
		}else {
			
		}
		
		
		
	
		
	}

}
