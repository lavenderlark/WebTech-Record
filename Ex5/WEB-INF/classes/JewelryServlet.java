import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.URLEncoder;
import java.net.URLDecoder;

public class JewelryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve jewelry selection
        String selectedJewelry = request.getParameter("jewelry");
        String extraData = request.getParameter("extraData");

        // Store selection in HttpSession
        HttpSession session = request.getSession();
        session.setAttribute("favoriteJewelry", selectedJewelry);

        // Create and add a cookie for the selection
        String encodedJewelry = URLEncoder.encode(selectedJewelry, "UTF-8");
        Cookie jewelryCookie = new Cookie("favoriteJewelry", encodedJewelry);
        jewelryCookie.setMaxAge(60 * 60 * 24); // 1 day expiration
        response.addCookie(jewelryCookie);

        // Redirect using URL rewriting with extra data
        String encodedURL = response.encodeRedirectURL("viewJewelry.html?jewelry=" + selectedJewelry + "&extraData=" + URLEncoder.encode(extraData, "UTF-8"));
        response.sendRedirect(encodedURL);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve jewelry selection from HttpSession
        HttpSession session = request.getSession(false);
        String favoriteJewelry = (session != null) ? (String) session.getAttribute("favoriteJewelry") : null;

        // Fallback to cookies if session is unavailable
        if (favoriteJewelry == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("favoriteJewelry".equals(cookie.getName())) {
                        favoriteJewelry = URLDecoder.decode(cookie.getValue(), "UTF-8");
                        break;
                    }
                }
            }
        }

        // Fallback to URL parameters if cookies are unavailable
        if (favoriteJewelry == null) {
            favoriteJewelry = request.getParameter("jewelry");
        }

        // If no selection, set a default
        if (favoriteJewelry == null) {
            favoriteJewelry = "None";
        }

        // Prepare additional details
        String image = "";
        String description = "";
        String price = "";

        switch (favoriteJewelry) {
            case "Radiant Ruby":
                image = "images/ruby.jpg";
                description = "A symbol of passion and energy, the Ruby exudes confidence and charm.";
                price = "$1200";
                break;
            case "Majestic Sapphire":
                image = "images/sapphire.jpg";
                description = "Graceful and timeless, the Sapphire reflects sophistication.";
                price = "$1800";
                break;
            case "Elegant Emerald":
                image = "images/emerald.jpg";
                description = "Fresh and vibrant, the Emerald brings elegance and renewal.";
                price = "$1500";
                break;
            case "Timeless Diamond":
                image = "images/diamond.jpg";
                description = "Dazzling and pure, the Diamond symbolizes strength and beauty.";
                price = "$2000";
                break;
            default:
                image = "jewels.jpg";
                description = "Select your favorite jewelry to see details!";
                price = "N/A";
        }

        // Display the favorite jewelry with additional details
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Your Favorite Jewelry</title>");
        out.println("<style>");
        out.println("body { font-family: 'Arial', sans-serif; background: linear-gradient(to right, #d9a7c7, #fffcdc); margin: 0; padding: 0; text-align: center; }");
        out.println("h1 { color: #800080; font-size: 2.5em; margin: 20px; }");
        out.println(".container { max-width: 600px; margin: 50px auto; padding: 20px; background: #ffffff; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); border-radius: 10px; }");
        out.println(".jewelry-image { max-width: 100%; height: auto; border-radius: 10px; margin-bottom: 20px; }");
        out.println(".details { text-align: left; }");
        out.println(".details p { font-size: 1.2em; color: #333; }");
        out.println("a { display: inline-block; margin-top: 20px; padding: 10px 20px; background: #800080; color: #fff; text-decoration: none; border-radius: 5px; font-size: 1em; }");
        out.println("a:hover { background: #4b0082; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Your Favorite Jewelry</h1>");
        out.println("<div class='container'>");
        out.println("<img src='" + image + "' alt='Jewelry Image' class='jewelry-image'>");
        out.println("<div class='details'>");
        out.println("<p><strong>Jewelry:</strong> " + favoriteJewelry + "</p>");
        out.println("<p><strong>Description:</strong> " + description + "</p>");
        out.println("<p><strong>Price:</strong> " + price + "</p>");
        out.println("</div>");
        out.println("<a href='selectJewelry.html'>Change Your Selection</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
