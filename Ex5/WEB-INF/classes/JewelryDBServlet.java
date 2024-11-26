import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class JewelryDBServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jewelry_db";
    private static final String DB_USER = "root"; // Replace with your MySQL username
    private static final String DB_PASSWORD = ""; // Replace with your MySQL password

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Jewelry Catalog</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background: linear-gradient(to right, #d16ba5, #c777b9, #ba83ca, #aa8fd8); color: white; text-align: center; }");
        out.println("h1 { color: #5a1d52; }");
        out.println("table { margin: 20px auto; border-collapse: collapse; width: 70%; background: rgba(255, 255, 255, 0.9); color: #333; }");
        out.println("th, td { padding: 10px; border: 1px solid #ccc; }");
        out.println("th { background-color: #8e44ad; color: white; }");
        out.println(".button { padding: 10px 20px; background-color: #8e44ad; color: white; text-decoration: none; border-radius: 5px; display: inline-block; margin-top: 20px; }");
        out.println(".button:hover { background-color: #a569bd; }");
        out.println("</style>");
        out.println("</head><body>");
        out.println("<h1>Jewelry Catalog</h1>");

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Execute SQL query
            String sql = "SELECT * FROM jewelry_items";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Display data in an HTML table
            out.println("<table>");
            out.println("<tr><th>ID</th><th>Name</th><th>Type</th><th>Price</th><th>Description</th></tr>");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                double price = rs.getDouble("price");
                String description = rs.getString("description");

                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + type + "</td>");
                out.println("<td>$" + price + "</td>");
                out.println("<td>" + description + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");

            // Close resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }

        out.println("<a href='5.d.jewelry.html' class='button'>Back to Home</a>");
        out.println("</body></html>");
    }
}
