package co.bucketstargram.common.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.bucketstargram.common.common.Command;
import co.bucketstargram.common.common.HttpRes;

public class LoginForm implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String viewPage = "jsp/LoginForm.jsp";
		HttpRes.forward(request, response, viewPage);
	}

}
