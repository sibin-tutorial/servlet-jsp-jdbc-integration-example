package com.raj.dev;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class ListOfUserServlet
 */
@WebServlet("/ListOfUserServlet")
public class ListOfUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";	
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "root";
	  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListOfUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            Statement stmt = conn.createStatement();
            
            String name = request.getParameter("name");
        	String email = request.getParameter("email");
         
        	if(name!=null && email!=null) {
            // 1. CREATE (Insert)
            String insertSQL = "INSERT INTO users (name, email) VALUES (?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertSQL)) {
            	
            	  ps.setString(1, name);
                ps.setString(2, email);
                ps.executeUpdate();
                System.out.println("Record inserted successfully.");
            }
        	}
        

            System.out.println("Record inserted successfully");

         // 2. READ (Select)
            String selectSQL = "SELECT * FROM users";
            ResultSet rs = stmt.executeQuery(selectSQL);
            
            List<User> users = new ArrayList<>();
            while (rs.next()) {
            	User u = new User();
                int id = rs.getInt("id");
                String namee = rs.getString("name");
                String emaill = rs.getString("email");
                
                u.setEmail(emaill);
                u.setId(id);
                u.setName(namee);
                users.add(u);
                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
            }
            
            request.setAttribute("users", users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		
		
		 request.getRequestDispatcher("/list.jsp").forward(request, response);
	}

}
