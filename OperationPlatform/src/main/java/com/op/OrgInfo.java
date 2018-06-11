package com.op;

import java.io.*;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrgInfo extends HttpServlet{

    private String Classification;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String orgID=request.getParameter("orgID");
        int id=Integer.valueOf(orgID).intValue();
        Map p=DubboHandler.INSTANCE.accountService.agencyInformation(id);
        Classification="学校";

        out.println("<Information> \n ");
        out.println("<Info> \n "+ p.get("agencyID").toString() +" \n </Info> \n");
        out.println("<Info> \n "+ p.get("agencyName").toString() +" \n </Info> \n");
        out.println("<Info> \n "+ p.get("agencyName").toString() +" \n </Info> \n");
        out.println("<Info> \n "+ Classification +" \n </Info> \n");
        out.println("<Info> \n "+ p.get("agentName").toString() +" \n </Info> \n");
        out.println("<Info> \n "+ p.get("agentTel").toString() +" \n </Info> \n");
        out.println("</Information>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

}
