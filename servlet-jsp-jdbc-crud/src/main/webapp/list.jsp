<%@ page import="java.util.List" %>
<%@ page import="com.raj.dev.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of Users</title>
</head>
<body>
    <h2>List of Users</h2>

    <table border="1">
        <thead>
            <tr>
                <th>User ID</th>
                <th>Username</th>
                <th>Email</th>
            </tr>
        </thead>
        <tbody>
            <% 
                // Retrieve the user list from the request attribute
                List<User> userList = (List<User>) request.getAttribute("users");
                if (userList != null) {
                    // Iterate through the list
                    for (User user : userList) {
            %>
            <tr>
                <td><%= user.getId() %></td>
                <td><%= user.getName() %></td>
                <td><%= user.getEmail() %></td>
            </tr>
            <% 
                    }
                } else {
            %>
            <tr>
                <td colspan="3">No users available</td>
            </tr>
            <% } %>
        </tbody>
    </table>
    
    <form action="index.jsp" method="get">

<input type="submit" value = "Add User"/>
</form>
</body>
</html>
