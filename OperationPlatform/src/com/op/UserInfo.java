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
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String userID=request.getParameter("userID");
        String msg=request.getParameter("msg");
        out.println("<Information> \n ");
        if(msg.equals("Freeze"))
            out.println("<Info> \n "+ freeze(userID) +" \n </Info> \n");
        else if(msg.equals("Active"))
            out.println("<Info> \n "+ active(userID) +" \n </Info> \n");
        else if(msg.equals("ChangePwd"))
        {
            String pwd1=request.getParameter("pwd1");
            String pwd2=request.getParameter("pwd2");
            out.println("<Info> \n "+ changePwd(pwd1,pwd2) +" \n </Info> \n");
        }
        else if(msg.equals("Load"))
        {
            getInfo(userID);
            out.println("<Info> \n "+ ID +" \n </Info> \n");
            out.println("<Info> \n "+ UserName +" \n </Info> \n");
            out.println("<Info> \n "+ TrueName +" \n </Info> \n");
            out.println("<Info> \n "+ State +" \n </Info> \n");
        }
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

    public String freeze(String userID){
        return "true";
    }

    public String active(String userID){
        return "true";
    }

    public String changePwd(String pwd1,String pwd2){
        return "true";
    }

}
