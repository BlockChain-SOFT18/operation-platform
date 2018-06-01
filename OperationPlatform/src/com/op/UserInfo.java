package com.op;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInfo extends HttpServlet{

    private String ID,UserName,TrueName,State;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String userID=request.getParameter("userID");
        getInfo(userID);
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<Information> \n ");
        out.println("<Info> \n "+ ID +" \n </Info> \n");
        out.println("<Info> \n "+ UserName +" \n </Info> \n");
        out.println("<Info> \n "+ TrueName +" \n </Info> \n");
        out.println("<Info> \n "+ State +" \n </Info> \n");
        out.println("</Information>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request,response);
    }

    public void getInfo(String userID){
        ID="123456";
        UserName="DeadPool";
        TrueName="Wed";
        State="可使用";
    }
}
