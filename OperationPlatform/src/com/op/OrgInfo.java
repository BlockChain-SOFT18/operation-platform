package com.op;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrgInfo extends HttpServlet{

    private String ID,OrgName1,OrgName2,Classification,PersonName,PhoneNumber;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String orgID=request.getParameter("orgID");
        getInfo(orgID);
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<Information> \n ");
        out.println("<Info> \n "+ ID +" \n </Info> \n");
        out.println("<Info> \n "+ OrgName1 +" \n </Info> \n");
        out.println("<Info> \n "+ OrgName2 +" \n </Info> \n");
        out.println("<Info> \n "+ Classification +" \n </Info> \n");
        out.println("<Info> \n "+ PersonName +" \n </Info> \n");
        out.println("<Info> \n "+ PhoneNumber +" \n </Info> \n");
        out.println("</Information>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request,response);
    }

    public void getInfo(String orgID){
        ID="123456";
        OrgName1="北京航空航天大学";
        OrgName2="北京航空航天大学";
        Classification="学校";
        PersonName="徐惠彬";
        PhoneNumber="12345678901";
    }
}
