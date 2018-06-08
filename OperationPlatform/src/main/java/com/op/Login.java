package com.op;

import buaa.jj.accountservice.api.AccountService;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {

    /*@Override
    public void init() throws ServletException {
        super.init();
        DubboHandler.init();
    }*/

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String responseText=null;
        String username=request.getParameter("UserName");
        String password=request.getParameter("Password");
        String type=request.getParameter("Type");
        if(type.equals("1")&&DubboHandler.INSTANCE.accountService.userLogin(username,password)>0)
            responseText=String.valueOf(DubboHandler.INSTANCE.accountService.userLogin(username,password));
        else if(type.equals("2")&&succeed2(username,password)>0)
            responseText=String.valueOf(succeed2(username,password));
        else responseText="false";
        response.getWriter().print(responseText);
        response.getWriter().close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private int succeed1(String username,String password){
        if(username.equals("Hestia")&&password.equals("123456"))
            return 1;
        return -1;
    }

    private int succeed2(String username,String password){
        if(username.equals("OOO")&&password.equals("123456"))
            return 1;
        return -1;
    }

}
