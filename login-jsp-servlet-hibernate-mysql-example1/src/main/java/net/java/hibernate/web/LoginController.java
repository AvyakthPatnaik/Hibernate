	package net.java.hibernate.web;
	import java.io.IOException;
	
	import jakarta.servlet.RequestDispatcher;
	import jakarta.servlet.ServletException;
	import jakarta.servlet.annotation.WebServlet;
	import jakarta.servlet.http.HttpServlet;
	import jakarta.servlet.http.HttpServletRequest;
	import jakarta.servlet.http.HttpServletResponse;
	import net.java.hibernate.dao.UserDao;
	
	@WebServlet("/login")
	public class LoginController extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private UserDao loginDao;

	    public void init() {
	        loginDao = new UserDao();
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	        response.sendRedirect("login.jsp");
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	        try {
	            authenticate(request, response);
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }

	    private void authenticate(HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");

	        if (loginDao.validate(username, password)) {
	            RequestDispatcher dispatcher = request.getRequestDispatcher("login-success.jsp");
	            dispatcher.forward(request, response);
	        } else {
	        	 RequestDispatcher dispatcher = request.getRequestDispatcher("login-error.jsp");
		            dispatcher.forward(request, response);
	        }
	    }
	}