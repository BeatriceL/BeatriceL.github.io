
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map.Entry" %>

<%
    // 模擬從外部數據源（例如API）獲取的搜索結果
    HashMap<String, String> searchResults = (HashMap<String, String>) request.getAttribute("filteredList");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Result</title>
    <link href="/Users/ys/Desktop/NCCU/資料結構/Final Project/DS_Project/dist/out.css" rel="stylesheet">
</head>
<body>
    <!-- tailwind.config.js -->

    <!-- component -->
    <div class="max-w-4xl px-10 py-6 mx-auto bg-white rounded-lg shadow-md">
        <h1 class="text-xl font-bold text-gray-700 mb-4">Search Results</h1>


        <% if (searchResults != null && !searchResults.isEmpty()) { %>
            <% for (Entry<String, String> entry : searchResults.entrySet()) { %>
            <div class="mt-6">
                <div class="flex items-center justify-between">
                    <span class="font-light text-gray-600"><%= entry.getKey() %></span>
                    <a href="/DS_Group7/<%= java.net.URLEncoder.encode(entry.getKey(), "UTF-8") %>" class="px-2 py-1 font-bold text-gray-100 bg-gray-600 rounded hover:bg-gray-500">Read more</a>

                </div>
                <div class="mt-2">
                    <p class="text-gray-600"><%= entry.getValue() %></p>
                </div>
            </div>
            <% } %>
        <% } else { %>
            <p>No search results found.</p>
        <% } %>
        
    </div>
</body>
</html>