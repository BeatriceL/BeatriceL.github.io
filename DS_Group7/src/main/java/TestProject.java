import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/TestProject")
public class TestProject extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TestProject() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        // 獲取前端傳遞的關鍵字參數
        String keyword = request.getParameter("keyword");

        // 檢查關鍵字是否為空
        if (keyword == null || keyword.isEmpty()) {
            // 如果關鍵字為空，返回到搜索頁面
            String requestUri = request.getRequestURI();
            request.setAttribute("requestUri", requestUri);
            request.getRequestDispatcher("search.jsp").forward(request, response);
            return;
        }

        // 創建 synonym_finder 對象
        synonym_finder synonymFinder = new synonym_finder();

        // 使用 synonymFinder.lst 進行關鍵詞過濾
        for (String unwanted : synonymFinder.lst) {
            keyword = keyword.replaceAll(unwanted, "");
        }

        // 設定爬取的次數
        int times = 20;

        // 創建 Crawler 對象，執行查詢
        Crawler google = new Crawler(keyword, times);
        HashMap<String, String> query = google.query();

        // 創建 WebTree 實例
        WebNode rootNode = new WebNode(new WebPage("Root", "https://example.com"));
        WebTree webTree = new WebTree(rootNode);

        // 創建 traversal_PostOrder 對象
        traversal_PostOrder postOrderTraversal = new traversal_PostOrder(webTree);

        // 進行後序遍歷和評分
        postOrderTraversal.traversal();

        // 在 WebNode 上設置分數
        rootNode.setNodeScore(postOrderTraversal.keywords);

        // 獲取評分結果
        ArrayList<Keyword> scores = postOrderTraversal.keywords;

        // 使用 synonymFinder.lst 進行再次過濾
        for (String unwanted : synonymFinder.lst) {
            keyword = keyword.replaceAll(unwanted, "");
        }

        // 進行進階過濾或其他操作（根據需要）

        // 將搜尋結果和評分結果存儲在 request 屬性中
        request.setAttribute("searchResults", query);

        // 使用 scores（ArrayList<Keyword>）或 rootNode.getNodeScore()，根據您的需求
        request.setAttribute("scores", scores);  // 或 request.setAttribute("scores", rootNode.getNodeScore());

        // 創建 WebNodeList 實例
        WebNodeList webNodeList = new WebNodeList();

        // 使用深度優先搜索（DFS）遍歷 WebTree 並加入 WebNodeList
        dfs(rootNode, webNodeList);

        // 進行 WebNodeList 的排序
        webNodeList.sort();

        // 初始化 filteredList
        ArrayList<WebNode> filteredList = new ArrayList<>();

        // 遍歷 WebNodeList 並過濾評分不為零的 WebNode
        for (int i = 0; i < webNodeList.size(); i++) {
            WebNode node = webNodeList.get(i);
            if (node.nodeScore > 0) {
                filteredList.add(node);
            }
        }

        // 將過濾後的列表存儲在 request 屬性中
        request.setAttribute("filteredList", filteredList);

        // 轉發到 result1.jsp
        request.getRequestDispatcher("result1.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 處理 POST 請求，調用 doGet 方法
        doGet(request, response);
    }

    // 定義深度優先搜索（DFS）遞歸函數
    private static void dfs(WebNode node, WebNodeList nodeList) {
        nodeList.add(node);

        for (WebNode child : node.children) {
            dfs(child, nodeList);
        }
    }
}
