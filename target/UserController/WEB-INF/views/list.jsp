<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring 4 MVC -HelloWorld</title>
</head>
<body>
	<center>
		<h2>List</h2>
		
		
	<table>
    			
		<c:forEach items="${userList}" var="user">
    		<tr>
    			<td>
    			<c:out value="${user.name}-${user.surname}-${user.phone}"/>
    			</td>
    				<td>
    					<a href="#" onclick="javascript: window.open('uptade/${user.name}/${user.surname}/${user.phone}', '', 'toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=false,width=480,height=550'); return false" >Edit</a>
    					 </td>
    			
    				<td>
    					 <a  href="/list/${user.name}"  onclick="return confirm('silmek istediginize emin misin ? ');">Delete
    					  
    					 </a>
    					 
    				</td>
    				
    			
    				<td>
    					 <a href="#" onclick="javascript: window.open('list/${user.name}', '', 'toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=false,width=480,height=550'); return false" >N Delete</a>
    						</td>
    				
    			</tr>
    				
               
		</c:forEach>
             </table>   
                    
   
	</center>
	
</body>
</html>