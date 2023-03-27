<%-- 
    Document   : users
    Created on : 9-Mar-2023, 3:29:36 PM
    Author     : qball
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Users</title>
    </head>
    <body>
        <h1>Manage Users</h1>
        <c:choose>
            <c:when test= "${people == no}">
                <table border="1">   
                    <tr><th>Email</th><th>First Name</th><th>Last Name</th><th>Role</th><th></th><th></th></tr>
                    <c:forEach items="${users}" var="user">
                    <tr><td>${user.email}</td><td>${user.getfirstName()}</td><td>${user.getlastName()}</td><td>${user.getRole().getName()}</td><td><a href="user?action=editUser&userEmail=${user.email}">Edit</a></td><td><a href="user?action=deleteUser&userEmail=${user.email}">Delete</a></td></tr>
                    </c:forEach>
                </table> 
            </c:when>
            <c:otherwise>No Users Please Add One</c:otherwise>
        </c:choose>
        <h2>${subTitle}</h2>
        <c:choose> 
            <c:when test="${subTitle == 'Add User'}">
            <form action="" method="post">
                Email: <input type="text" name="email"><br>
                First Name: <input type="text" name="firstName"><br>
                Last Name: <input type="text" name="latName"><br>
                Password: <input type="password" name="password"><br>
                Role: <select name="therole">
                    <c:forEach items="${roles}" var="role">
                        <option value="${role.getId()}">${role.getName()}</option>
                    </c:forEach>
                </select><br>
                <input type="submit" value="Add User">   
                <c:if test= "${messUp != mess}">
                    <p>All Fields need to be filled</p>
                </c:if>
            </form>
        </c:when>
        <c:when test="${subTitle == 'Edit User'}">
            <form action="" method="get">
                <input type="hidden" value="${userToEdit.email}" name="userEmail">
                Email:${userToEdit.email}<br>
                First Name: <input type="text" name="firstName" value="${userToEdit.getfirstName()}"><br>
                Last Name: <input type="text" name="latName" value="${userToEdit.getlastName()}"><br>
                Password: <input type="password" name="password"><br>
                Role: <select name="therole">
                    <c:forEach items="${roles}" var="role">
                        <option value="${role.getId()}" >${role.getName()}</option>
                    </c:forEach>
                </select><br>
                <input type="submit" name="action" value="Update"><input type="submit" name="action" value="Cancel" >
            </form>
        </c:when>   
        </c:choose>
    </body>
</html>
