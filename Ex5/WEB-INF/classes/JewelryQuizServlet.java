import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class JewelryQuizServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data
        String personality = request.getParameter("personality");
        String color = request.getParameter("color");
        String jewelry = request.getParameter("jewelry");

        // Determine suitable jewelry based on personality, color, and preference
        String gemColor = "";
        String jewelryRecommendation = "";
        String backgroundColor = "";
        String gemDescription = "";

        if ("adventurous".equals(personality)) {
            gemColor = "red";
            jewelryRecommendation = "Radiant Ruby Ring";
            backgroundColor = "#F44336";  // Ruby Red
            gemDescription = "A symbol of passion and energy, the Ruby exudes confidence and excitement!";
        } else if ("elegant".equals(personality)) {
            gemColor = "blue";
            jewelryRecommendation = "Majestic Sapphire Necklace";
            backgroundColor = "#1E90FF";  // Sapphire Blue
            gemDescription = "Graceful and timeless, the Sapphire reflects sophistication and calm beauty.";
        } else if ("creative".equals(personality)) {
            gemColor = "green";
            jewelryRecommendation = "Serene Jade Earrings";
            backgroundColor = "#008000";  // Jade Green
            gemDescription = "Fresh and inspiring, Jade represents creativity, renewal, and vitality.";
        } else if ("sophisticated".equals(personality)) {
            gemColor = "purple";
            jewelryRecommendation = "Regal Amethyst Bracelet";
            backgroundColor = "#800080";  // Amethyst Purple
            gemDescription = "Rich in history and elegance, the Amethyst radiates royalty and deep wisdom.";
        } else if ("dynamic".equals(personality)) {
            gemColor = "white";
            jewelryRecommendation = "Timeless Diamond Necklace";
            backgroundColor = "#D3D3D3";  // Diamond White
            gemDescription = "Pure and dazzling, the Diamond symbolizes clarity, strength, and unbreakable beauty.";
        }

        // Set content type and get the writer
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Your Jewelry Recommendation</title>");
        out.println("<link rel='stylesheet' type='text/css' href='styles.css'>");
        out.println("</head>");
        out.println("<body style='background-color:" + backgroundColor + ";'>");

        out.println("<h2>Your Personalized Jewelry Recommendation</h2>");
        out.println("<div class='recommendation'>");
        out.println("<h3>Based on your answers, we recommend:</h3>");
        out.println("<h4>" + jewelryRecommendation + "</h4>");
        out.println("<p><strong>" + gemDescription + "</strong></p>");

        // Display gem image based on the selected color
        out.println("<img src='images/" + gemColor + "_gem.png' alt='" + gemColor + " Gem' class='gem'>");

        out.println("</div>");
        out.println("<a href='jewel.html'>Take the survey again</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
