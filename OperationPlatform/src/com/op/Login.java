package com.op;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdk.nashorn.internal.parser.JSONParser;
import net.sf.json.JSONObject;

public class Login extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String responseText=null;
        String username=request.getParameter("UserName");
        String password=request.getParameter("Password");
        String type=request.getParameter("Type");
        if(type.equals("1")&&succeed1(username,password))
            responseText="true";
        else if(type.equals("2")&&succeed2(username,password))
            responseText="true";
        else responseText="false";
        response.getWriter().print(responseText);
        response.getWriter().close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private boolean succeed1(String username,String password){
        if(username.equals("Hestia")&&password.equals("123456"))
            return true;
        return false;
    }

    private boolean succeed2(String username,String password){
        if(username.equals("OOO")&&password.equals("123456"))
            return true;
        return false;
    }

}
