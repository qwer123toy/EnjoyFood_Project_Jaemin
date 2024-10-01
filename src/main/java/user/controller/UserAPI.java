package user.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet({ "/api/v1/user", "/api/v1/user/*" })
public class UserAPI extends HttpServlet {

}
