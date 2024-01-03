import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/backend/search")
public class SearchServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 獲取前端發送的關鍵字
        String keywords = request.getParameter("keywords");

        // 在這裡執行搜索業務邏輯，例如從數據庫中檢索結果
        // 假設你已經有了一個名為 SearchService 的服務類別處理實際的搜索邏輯
        String searchResult = SearchService.performSearch(keywords);

        // 將搜索結果放入 request 作為屬性，以便在 JSP 頁面中使用
        request.setAttribute("searchResult", searchResult);

        // 使用 RequestDispatcher 轉發到 JSP 頁面
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/result1.jsp");
        dispatcher.forward(request, response);
    }
}
