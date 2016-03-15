<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="DataInterface.DataReader"%>
<%
	DataReader db;

	db = (DataReader)request.getServletContext().getAttribute("data");

    String input = request.getParameter("q");
     
    List<String> countries = db.getData(input);
 
    Iterator<String> iterator = countries.iterator();
    int i = 0;
    while(iterator.hasNext() && i<10) {
        String country = (String)iterator.next();
        out.println(country);
        i++;
    }
%>