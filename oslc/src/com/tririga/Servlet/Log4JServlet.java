package com.tririga.Servlet;

import javax.servlet.http.HttpServlet;

import org.apache.log4j.xml.DOMConfigurator;

@SuppressWarnings("serial")
public class Log4JServlet extends HttpServlet{
	public void init() {
        String path = this.getServletContext().getRealPath("/");
        String file = this.getInitParameter("log4j");
        String log4jPath = path + file;
        System.setProperty("home", path);
        DOMConfigurator.configure(log4jPath);
    }
}
