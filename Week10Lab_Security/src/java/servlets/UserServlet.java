package servlets;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.*;
import dataaccess.*;
/**
 *
 * @author qball
 */
public class UserServlet extends HttpServlet {
    @SuppressWarnings("UnusedAssignment")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDB DBconnection = new UserDB();
        RoleDB roleConnection = new RoleDB();
        List<User> users = new ArrayList<>();
        List<Role> roles = new ArrayList<>();
        users = (List<User>)DBconnection.getall();
        roles = (List<Role>)roleConnection.getall();
        int size = users.size();
        if (size == 0){
            request.setAttribute("people", "no");
        }
        request.setAttribute("users", users);
        request.setAttribute("roles", roles);
        String userEmail;
        String stuff = request.getParameter("userEmail");
        
        String theAction = request.getParameter("action");
        if(theAction == null || theAction.equals("Cancel") || theAction.equals("editUser")){
            userEmail="";
        }
        if(theAction ==  null || theAction.equals("Cancel")){
            String bottom = "Add User";
            request.setAttribute("subTitle", bottom);
        }
        else if(theAction.equals("editUser")){
            String bottom = "Edit User";
            request.setAttribute("subTitle", bottom);
            userEmail = request.getParameter("userEmail");
            userEmail = userEmail.replace(" ", "+");
            User theUser = DBconnection.getUser(userEmail);
            request.setAttribute("userToEdit", theUser);
        }
        else if(theAction.equals("Update")){
            String email = stuff.replace(" ", "+");
            String fname = (String)request.getParameter("firstName");
            String lname = (String)request.getParameter("latName");
            String pass = (String)request.getParameter("password");
            String roleId = request.getParameter("therole"); 
            int roleIdNum = Integer.parseInt(roleId);
            if(pass.equals("")){
                DBconnection.updateUser(email, fname, lname, roleIdNum);
            }
            else{
                DBconnection.updateUser(email, fname, lname, pass, roleIdNum);
            }
            List<User> updateUsers = (List<User>)DBconnection.getall();
            request.setAttribute("users", updateUsers);
            String bottom = "Add User";
            request.setAttribute("subTitle", bottom);
        }
        else if(theAction.equals("deleteUser")){
            String email = stuff.replace(" ", "+");
            DBconnection.delete(email);
            String bottom = "Add User";
            request.setAttribute("subTitle", bottom);
            List<User> updateUsers = (List<User>)DBconnection.getall();
            request.setAttribute("users", updateUsers);
            
        }
        if (size == 0){
            request.setAttribute("people", "no");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDB DBconnection = new UserDB();
        String email = (String)request.getParameter("email");
        String firstname = (String)request.getParameter("firstName");
        String lastname = (String)request.getParameter("latName");
        String password = (String)request.getParameter("password");
        String roleId = request.getParameter("therole");
        
        if(email.equals("") || firstname.equals("") || lastname.equals("") || password.equals("")){
            request.setAttribute("messUp", "mess");
        }
        else{
            int role = Integer.parseInt(roleId);
            DBconnection.addUser(email, firstname, lastname, password, role);
        }
            List<User> updateUsers = (List<User>)DBconnection.getall();
            request.setAttribute("users", updateUsers);
            String bottom = "Add User";
            request.setAttribute("subTitle", bottom);
            RoleDB roleConnection = new RoleDB();
            List<Role> roles = new ArrayList<>();
            roles = (List<Role>)roleConnection.getall();
            request.setAttribute("roles", roles);
        
        
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }
}




//String sql = "SELECT * FROM user WHERE lastname=?";
//PreparedStatement ps = connection.prepareStatement(sql);
//ps.setString(1, lastname);
//ResultSet users = ps.executeQuery();

//String sql = "SELECT * FROM Users";
//ResultSet users = ps.executeQuery();
//ArrayList<User> users = new ArrayList<>();
//while (users.next()) {
//  String email = users.getString(1);
//  String firstname = users.getString(2);
//  String lastname = users.getString(3);
//  String password = users.getString(4);
//  String role = users.getString(5);
//  User user = new User(email, firstname, lastname, password, role);
//  users.add(user);
//}
//String preparedSQL = "UPDATE User SET "
//                   + "    email = ?, "
//                   + "    firstname = ?, "
//                   + "    lastname = ?, "
//                   + "    password = ?, "
//                   + "    role + ? "
//                   + "WHERE firstname = ?";
//int rows = ps.executeUpdate();
//INSERT INTO User (email, firstname, lastname, password, role) VALUES (?, ?, ?)";