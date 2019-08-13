package co.bucketstargram.common.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.bucketstargram.common.command.Index;
import co.bucketstargram.common.command.LoginForm;
import co.bucketstargram.common.command.LoginOK;
import co.bucketstargram.common.common.Command;
import co.bucketstargram.common.common.Trace;
/**
 * Servlet implementation class Controller
 */
@WebServlet(name = "Controllerd", urlPatterns = { "/Controllerd" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
        map = new HashMap<String, Command>();
        map.put("/Index.do", new Index());
        map.put("/LoginForm.do", new LoginForm());
        map.put("/LoginOK.do", new LoginOK());
     }

     /**
      * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
      *      response)
      */
     protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Trace.init();
  	  	request.setCharacterEncoding("UTF-8");
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String path = uri.substring(contextPath.length()); //최종path
//        System.out.println("uri = " + uri);
//        System.out.println("contextPath = " + contextPath);
//        System.out.println("path = " + path);
//        System.out.println("map.size() = " + map.size());
//        System.out.println();
//        System.out.println(Integer.parseInt(request.getParameter("id")));
        Command command = map.get(path);   //map에서 path를 통해 인스턴스를 생성하고 
        command.execute(request, response);   //실행함
     }

}
