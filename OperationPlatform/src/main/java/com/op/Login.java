package com.op;

import buaa.jj.accountservice.api.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        DubboHandler.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String username=request.getParameter("UserName");
        String password=request.getParameter("Password");
        String type=request.getParameter("Type");
        out.println("<Information> \n ");
        int num = DubboHandler.INSTANCE.accountService.userLogin(username, password);
        if(type.equals("1")&&num>0)
            out.println("<Info> \n "+ String.valueOf(num) +" \n </Info> \n");
        else if(type.equals("2")&&num>0)
            out.println("<Info> \n "+ String.valueOf(num) +" \n </Info> \n");
        else out.println("<Info> \n "+ "false" +" \n </Info> \n");
        out.println("</Information>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
