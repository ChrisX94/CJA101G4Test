package com.shakemate.post.controller;

import com.shakemate.post.model.PostService;
import com.shakemate.post.model.PostVO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class PostServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("insert".equals(action)) { // 來自 addPost.jsp 的請求

            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /*************** 1.接收請求參數 - 輸入格式的錯誤處理 ***************/
                Integer userId = null;
                try {
                    userId = Integer.valueOf(req.getParameter("userId").trim());
                } catch (NumberFormatException e) {
                    errorMsgs.add("使用者編號請填入數字");
                }

                String postText = req.getParameter("postText");
                if (postText == null || postText.trim().length() == 0) {
                    errorMsgs.add("貼文內容請勿空白");
                }

                String imageUrl = req.getParameter("imageUrl");
                if (imageUrl == null || imageUrl.trim().length() == 0) {
                    imageUrl = null; // 圖片可以為空
                }

                Byte viewerPermission = null;
                try {
                    viewerPermission = Byte.valueOf(req.getParameter("viewerPermission").trim());
                } catch (NumberFormatException e) {
                    errorMsgs.add("觀看權限格式錯誤");
                }

                Timestamp postTime = new Timestamp(System.currentTimeMillis());

                PostVO postVO = new PostVO();
                postVO.setUserId(userId);
                postVO.setPostText(postText);
                postVO.setImageUrl(imageUrl);
                postVO.setViewerPermission(viewerPermission);
                postVO.setPostTime(postTime);
                postVO.setLikesCount(0);
                postVO.setCommentCount(0);

                // 有錯誤就退回
                if (!errorMsgs.isEmpty()) {
                    req.setAttribute("postVO", postVO);
                    RequestDispatcher failureView = req.getRequestDispatcher("/post/addPost.jsp");
                    failureView.forward(req, res);
                    return;
                }

                /*************** 2.開始新增資料 ***************/
                PostService postSvc = new PostService();
//                postVO = postSvc.addPost(userId, postText, imageUrl, postTime, viewerPermission, 0, 0);

                /*************** 3.新增完成，轉交頁面 ***************/
                String url = "/post/listAllPost.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url);
                successView.forward(req, res);

            } catch (Exception e) {
                errorMsgs.add("無法新增資料: " + e.getMessage());
                RequestDispatcher failureView = req.getRequestDispatcher("/post/addPost.jsp");
                failureView.forward(req, res);
            }
        }
    }
}
