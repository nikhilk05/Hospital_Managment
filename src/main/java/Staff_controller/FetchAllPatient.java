package Staff_controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccessDB;
import dto.Patient;

@WebServlet("/staffpatientfetch")
public class FetchAllPatient extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("staff")==null)
		{
			resp.getWriter().print("<h1 style='color:red'>Session Expired</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		}
		else{
			AccessDB dao=new AccessDB();
			List<Patient> list=dao.fetchAllPatient();
			if(list.isEmpty())
			{
				resp.getWriter().print("<h1 style='color:red'>No Patient Data Found</h1>");
				req.getRequestDispatcher("BookAppointment.jsp").include(req, resp);
			}
			else{
				req.setAttribute("list", list);
				req.getRequestDispatcher("PatientList.jsp").forward(req, resp);
			}
		}
	}
}
