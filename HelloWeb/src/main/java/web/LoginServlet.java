package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;
import service.UserService;
import service.UserServiceImpl;
import utils.Constants;



public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		// Get username and password from request
        
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        System.out.println(username);
        
        // use service to check login
        UserService userservice = new UserServiceImpl();
        User searchedUser = null;
        searchedUser = userservice.userLogin(username, password);
        
        // login succeed
        if(searchedUser != null) {
        	
        	// add user to session
        	request.getSession().setAttribute(Constants.USER_SESSION, searchedUser);
        	
        	// login succeed goto welcome.jsp
        	//response.sendRedirect("ShowEmployee.jsp");
        	
        	request.getRequestDispatcher("ShowEmployee.jsp").forward(request,response);
        
        } else {
        	
        	// redirect to index.jsp
        	// tell user wrong username or password pls try it again
        	
        	request.setAttribute( "errormsg" , "wrong username or password");
            
        	request.getRequestDispatcher("index.jsp").forward(request,response);
        }
        
        
	}
}
