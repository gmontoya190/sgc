package web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import data.model.Format;
import persistence.ConexionDB;

/**
 * Servlet implementation class ActionFormat
 */
@WebServlet("/ActionFormat")
public class ActionFormat extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String DIRECTORY_FILE = "/Users/grille/Documents/webdevel/SGC/WebContent/formatos";
	private ConexionDB conexionDB = new ConexionDB();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActionFormat() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		System.out.println("dentro del view");
		HttpSession session = request.getSession(false);
		String idProcess = (String) session.getAttribute("process");
		String isNewFormat = (String) session.getAttribute("newFormat");
		Format format = (Format) session.getAttribute("format");
		String action = (String) session.getAttribute("action");
		String nameFile = null;
		String extension = null;
		if (action!=null && "modify".equals(action)) {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart) {
				// Create a factory for disk-based file items
				FileItemFactory factory = new DiskFileItemFactory();

				// Create a new file upload handler
				ServletFileUpload upload = new ServletFileUpload(factory);
				try {
					// Parse the request
					List<FileItem> multiparts = upload.parseRequest(request);

					for (FileItem item : multiparts) {
						if (!item.isFormField()) {
							File file = new File (item.getName());
							extension = getExtension(file.getName());
							format.setExtension(extension);
							nameFile = format.getNameFormat()+"_"+format.getVersion()+"."+format.getExtension();
							if ("Y".equals(session.getAttribute("leadProcess"))) {
								item.write(new File(DIRECTORY_FILE + File.separator + idProcess + "_pendiente"
										+ File.separator + nameFile));
							} else {
								item.write(new File(DIRECTORY_FILE + File.separator + idProcess + File.separator
										+ nameFile));
							}

						}
					}
				} catch (Exception e) {
					System.out.println("File upload failed");
				}
			}
			session.removeAttribute("format");
			session.removeAttribute("newFormat");
			String userLastModi = (String) session.getAttribute("user");
			if (conexionDB.connect() != null) {
				if ("Y".equals(session.getAttribute("leadProcess"))) {
					conexionDB.createPendingFormat(format, userLastModi, format.getProcessId(), "N");
				} else {
					conexionDB.createPendingFormat(format, userLastModi, format.getProcessId(), "Y");
				}

			}
		} else {
			String fileName =null;
			if(!"downloadPendiente".equals(action)) {
				fileName = DIRECTORY_FILE + File.separator + idProcess + File.separator + format.getNameFormat()+"_"+
						format.getVersion()+"."+format.getExtension();
			} else {
				fileName = DIRECTORY_FILE + File.separator + idProcess +"_pendiente"+ File.separator + format.getNameFormat()+"_"+
						format.getVersion()+"."+format.getExtension();
			}
			
			String fileType = FilenameUtils.getExtension(fileName);
			File file = new File(fileName);
			response.setContentType(fileType);
			response.setHeader("Content-disposition", "attachment; filename=\"" + file.getName() + "\"");
			OutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(file);
			byte[] buffer = new byte[4096];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			in.close();
			out.flush();
		}

	}
	
	
	
	
	public String getExtension(String fileName) {
		String extension = "";

		int i = fileName.lastIndexOf('.');
		int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

		if (i > p) {
		    extension = fileName.substring(i+1);
		}
		return extension;
		
	}

}
