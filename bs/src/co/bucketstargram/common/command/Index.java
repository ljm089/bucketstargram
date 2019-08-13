package co.bucketstargram.common.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.bucketstargram.common.common.Command;
import co.bucketstargram.common.common.HttpRes;

public class Index implements Command {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Index.java");
		String viewPage = "index.jsp";
		HttpRes.forward(request, response, viewPage);
	}
}
