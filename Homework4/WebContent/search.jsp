<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<%@page import="DataInterface.DataReader"%>
<%@page import="model.Page"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/jquery.autocomplete.css" />
    
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

    <script type="text/javascript"
            src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
    <script src="js/jquery.autocomplete.js"></script>  
    <style>
        input {
            font-size: 120%;
        }
    </style>
</head>
<body>



<table width= "50%">
<br>
<br>
 <tr> <td>

  <form action="/Homework4/InitServlet" method="post" id="search_form" class="clearfix">
	<input align ="middle" "text" size="150" maxlength="150" id="country" name="country" placeholder="What are you looking for?" id="search_text" />
	<input type="submit" name="search" value="Search" id="search_button" />
	</form>
	</td>
	</tr>
	</table>
	
	<br>
	<br>
	
	<table>
	
	
	 <%
    
    ArrayList<String> pages;
			
			

   // pages = (ArrayList<String>)request.getServletContext().getAttribute("result");
			
    pages = (ArrayList<String>)request.getSession().getAttribute("result");
    
    String input = request.getParameter("country");
    
    if (pages!=null)
    {
    	
    
    
    for (String s : pages )
    {
    	Page p = new Page();
    	p = DataReader.UrlsMap.get(s);
    	String title = "";
    	String description ="";
    	String url ="";
    	String path ="";
    	url = p.getUrl();
    	title = p.getTitle();
    	description = p.getDescription();
    	path = p.getPath();
    	
%>

<tr>
	<td><table>
	<tr><td> <a href='<%= path %>'><%= url %></a></td></tr>
	<tr><td> <%= description %></td></tr>
	</table></td></tr>
<%
 	 
    	
    	System.out.println("pages ----> "+s);
    }
    }
    %>
	</table>
	<script>
        $("#country").autocomplete("getdata.jsp");
    </script>
	
	</body>
	</html>