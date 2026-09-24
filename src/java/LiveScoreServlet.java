
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//import org.apache.hc.client5.http.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import org.json.*;

@WebServlet("/LiveScoreServlet")
public class LiveScoreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setIntHeader("Refresh", 10);

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<style>"
                + "body{"
                + "background: linear-gradient(135deg, #1a2980, #26d0ce);"
                + "color: white;}"
                + ".back-btn{"
                + "float: right;"
                + "color: white;"
                + "background-color: #216fdb;"
                + "border-radius: 8px;"
                + "border: 1px solid #999;"
                + "padding: 7px;"
                + "width: 75px;"
                + "font-weight: bold;"
                + "font-size: 17px;"
                + "cursor: pointer;"
                + "text-decoration: none;"
                + "text-align: center;}"
                + ".back-btn:hover{"
                + "background-color: #5a6268;}"
                + "</style>");

        String apiKey = System.getenv("CRIC_API_KEY");

        String apiUrl = "https://api.cricapi.com/v1/currentMatches?apikey="+ apiKey+ "&offset=0";
        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpGet request = new HttpGet(apiUrl);
            String response = client.execute(request, httpResponse
                    -> EntityUtils.toString(httpResponse.getEntity())
            );
//out.println(response);
            // Parse JSON using org.json
            JSONObject json = new JSONObject(response);

            out.println("<button type='button' onclick=\"window.location.href = 'index.html'\" class='back-btn'>Back</button>");
            out.println("<h1>Live Cricket Scores</h1>");

            if (json.has("data")) {
                JSONArray data = json.getJSONArray("data");

                for (int i = 0; i < data.length(); i++) {
                    JSONObject match = data.getJSONObject(i);

                    String name = match.optString("name", "Unknown Match");
                    String status = match.optString("status", "Status unavailable");

                    out.println("<h3>" + name + "</h3>");
                    out.println("<p>Status: " + status + "</p>");

                    if (match.has("score")) {
                        JSONArray scores = match.getJSONArray("score");

                        for (int j = 0; j < scores.length(); j++) {
                            JSONObject s = scores.getJSONObject(j);

                            String inning = s.optString("inning");
                            String r = s.optString("r");
                            String w = s.optString("w");
                            String o = s.optString("o");

                            out.println("<p><b>" + inning + ":</b> "
                                    + r + "/" + w + " in " + o + " overs</p>");
                        }
                    }

                    out.println("<hr>");
                }
            } else {
                out.println("<p>No live matches available.</p>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>Error fetching score: " + e.getMessage() + "</p>");
        }

        out.close();
    }
}
