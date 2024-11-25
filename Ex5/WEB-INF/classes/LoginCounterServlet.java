import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

public class LoginCounterServlet extends HttpServlet {

    // Map to store login counts for each user
    private ConcurrentHashMap<String, Integer> userLoginCounts = new ConcurrentHashMap<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check credentials (hardcoded for simplicity)
        boolean isValid = "Miraclin".equals(username) && "midstaytiny".equals(password);

        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Login Result</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background: linear-gradient(to right, #d16ba5, #ba83ca, #aa8fd8); color: white; text-align: center; padding: 20px; }");
        out.println(".container { margin: 50px auto; max-width: 400px; padding: 20px; background: rgba(255, 255, 255, 0.2); border-radius: 10px; }");
        out.println("h1 { color: #5a1d52; }");
        out.println("p { color: #f8e8f5; }");
        out.println("a, form button { color: white; text-decoration: none; background: #8e44ad; padding: 10px 20px; border-radius: 5px; transition: background-color 0.3s; border: none; cursor: pointer; font-size: 16px; }");
        out.println("a:hover, form button:hover { background: #9b59b6; }");
        out.println("form { display: inline; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        out.println("<div class='container'>");

        if (isValid) {
            // Update login count for the user
            userLoginCounts.merge(username, 1, Integer::sum);

            // Fetch the updated count for the user
            int count = userLoginCounts.get(username);

            out.println("<h1>Welcome, " + username + "!</h1>");
            out.println("<p>Login successful.</p>");
            out.println("<p>Your login count: <strong>" + count + "</strong></p>");

            // Logout button
            out.println("<form action='LogoutServlet' method='POST'>");
            out.println("<button type='submit'>Log Out</button>");
            out.println("</form>");
        } else {
            out.println("<h1>Login Failed</h1>");
            out.println("<p>Invalid username or password.</p>");
            out.println("<a href='5.e.Logincounter.html'>Try Again</a>");
        }

        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
