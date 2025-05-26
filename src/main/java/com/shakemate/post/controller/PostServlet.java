package com.shakemate.post.controller;

import com.shakemate.post.model.PostService;
import com.shakemate.post.model.PostVO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/post.do")
public class PostServlet extends HttpServlet {
    private PostService postService;

    @Override
    public void init() {
        postService = new PostService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=UTF-8");
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        switch (action) {
            case "getOne_For_Display":
                getOneForDisplay(req, res);
                break;
            case "insert":
                insert(req, res);
                break;
            case "getOne_For_Update":
                getOneForUpdate(req, res);
                break;
            case "update":
                update(req, res);
                break;
            case "delete":
                delete(req, res);
                break;
            default:
                RequestDispatcher rd = req.getRequestDispatcher("/post/index.jsp");
                rd.forward(req, res);
                break;
        }
    }

    // 顯示單一貼文
    private void getOneForDisplay(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Map<String, String> errors = new LinkedHashMap<>();
        req.setAttribute("errorMsgs", errors);

        String strPostId = req.getParameter("postId");
        if (strPostId == null || strPostId.trim().isEmpty()) {
            errors.put("postId", "請輸入貼文編號");
        }

        Integer postId = null;
        try {
            postId = Integer.valueOf(strPostId);
        } catch (NumberFormatException e) {
            errors.put("postId", "貼文編號格式錯誤");
        }

        if (!errors.isEmpty()) {
            RequestDispatcher rd = req.getRequestDispatcher("/post/post_page.jsp");
            rd.forward(req, res);
            return;
        }

        PostVO postVO = postService.getOnePost(postId);
        if (postVO == null) {
            errors.put("postId", "查無此貼文");
            RequestDispatcher rd = req.getRequestDispatcher("/post/post_page.jsp");
            rd.forward(req, res);
            return;
        }

        req.setAttribute("post", postVO);
        RequestDispatcher rd = req.getRequestDispatcher("/post/listOnePost.jsp");
        rd.forward(req, res);
    }

    // 新增貼文
    private void insert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Map<String, String> errors = new LinkedHashMap<>();
        req.setAttribute("errorMsgs", errors);

        PostVO postVO = new PostVO();

        // 取得並驗證輸入參數
        String userIdStr = req.getParameter("userId");
        String postText = req.getParameter("postText");
        String imageUrl = req.getParameter("imageUrl");
        postVO.setImageUrl(imageUrl);
        String viewerPermissionStr = req.getParameter("viewerPermission");

        Integer userId = null;
        Byte viewerPermission = null;

        if (userIdStr == null || userIdStr.trim().isEmpty()) {
            errors.put("userId", "使用者編號不能為空");
        } else {
            try {
                userId = Integer.valueOf(userIdStr);
                postVO.setUserId(userId);
            } catch (NumberFormatException e) {
                errors.put("userId", "使用者編號格式錯誤");
            }
        }

        if (postText == null || postText.trim().isEmpty()) {
            errors.put("postText", "貼文內容不能為空");
        } else {
            postVO.setPostText(postText);
        }

        if (viewerPermissionStr == null || viewerPermissionStr.trim().isEmpty()) {
            errors.put("viewerPermission", "瀏覽權限不能為空");
        } else {
            try {
                viewerPermission = Byte.valueOf(viewerPermissionStr);
                postVO.setViewerPermission(viewerPermission);
            } catch (NumberFormatException e) {
                errors.put("viewerPermission", "瀏覽權限格式錯誤");
            }
        }



        if (!errors.isEmpty()) {
            req.setAttribute("postVO", postVO);
            RequestDispatcher rd = req.getRequestDispatcher("/post/addPost.jsp");
            rd.forward(req, res);
            return;
        }

        Timestamp postTime = new Timestamp(System.currentTimeMillis());

        PostVO postVO1 = postService.addPost(userId, postText, imageUrl, postTime, viewerPermission);

        req.setAttribute("post", postVO1);
        req.setAttribute("success", "新增成功");
        RequestDispatcher rd = req.getRequestDispatcher("/post/listAllPost.jsp");
        rd.forward(req, res);
    }

    // 取得一筆貼文準備修改
    private void getOneForUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String postIdStr = req.getParameter("postId");
        Integer postId = null;
        try {
            postId = Integer.valueOf(postIdStr);
        } catch (NumberFormatException e) {
            req.setAttribute("errorMsgs", Map.of("postId", "貼文編號格式錯誤"));
            RequestDispatcher rd = req.getRequestDispatcher("/post/postAll.jsp");
            rd.forward(req, res);
            return;
        }

        PostVO postVO = postService.getOnePost(postId);
        if (postVO == null) {
            req.setAttribute("errorMsgs", Map.of("postId", "查無此貼文"));
            RequestDispatcher rd = req.getRequestDispatcher("/post/postAll.jsp");
            rd.forward(req, res);
            return;
        }

        // 使用URL帶參數，也可改用 setAttribute
        String param = "?postId=" + postVO.getPostId()
                + "&userId=" + postVO.getUserId()
                + "&postText=" + (postVO.getPostText() != null ? postVO.getPostText() : "")
                + "&imageUrl=" + (postVO.getImageUrl() != null ? postVO.getImageUrl() : "")
                + "&viewerPermission=" + postVO.getViewerPermission();

        RequestDispatcher rd = req.getRequestDispatcher("/post/updatePost.jsp" + param);
        rd.forward(req, res);
    }

    // 更新貼文
    private void update(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Map<String, String> errors = new LinkedHashMap<>();
        req.setAttribute("errorMsgs", errors);

        String postIdStr = req.getParameter("postId");
        String postText = req.getParameter("postText");
        String imageUrl = req.getParameter("imageUrl");
        String viewerPermissionStr = req.getParameter("viewerPermission");

        Integer postId = null;
        Byte viewerPermission = null;

        try {
            postId = Integer.valueOf(postIdStr);
        } catch (NumberFormatException e) {
            errors.put("postId", "貼文編號格式錯誤");
        }

        if (postText == null || postText.trim().isEmpty()) {
            errors.put("postText", "貼文內容不能為空");
        }

        if (viewerPermissionStr == null || viewerPermissionStr.trim().isEmpty()) {
            errors.put("viewerPermission", "瀏覽權限不能為空");
        } else {
            try {
                viewerPermission = Byte.valueOf(viewerPermissionStr);
            } catch (NumberFormatException e) {
                errors.put("viewerPermission", "瀏覽權限格式錯誤");
            }
        }

        if (!errors.isEmpty()) {
            RequestDispatcher rd = req.getRequestDispatcher("/post/updatePost.jsp");
            rd.forward(req, res);
            return;
        }

        PostVO updatedPost = postService.updatePost(postId, postText, imageUrl, viewerPermission);

        req.setAttribute("post", updatedPost);
        req.setAttribute("success", "更新成功");
        RequestDispatcher rd = req.getRequestDispatcher("/post/listOnePost.jsp");
        rd.forward(req, res);
    }

    // 刪除貼文
    private void delete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Map<String, String> errors = new LinkedHashMap<>();
        req.setAttribute("errorMsgs", errors);

        String postIdStr = req.getParameter("postId");
        Integer postId = null;
        try {
            postId = Integer.valueOf(postIdStr);
        } catch (NumberFormatException e) {
            errors.put("postId", "貼文編號格式錯誤");
            RequestDispatcher rd = req.getRequestDispatcher("/post/postAll.jsp");
            rd.forward(req, res);
            return;
        }

        postService.deletePost(postId);

        req.setAttribute("success", "貼文編號 " + postId + " 已刪除");
        RequestDispatcher rd = req.getRequestDispatcher("/post/postAll.jsp");
        rd.forward(req, res);
    }
}
