package com.shakemate.post.model;

import com.shakemate.post.vo.PostVO;

import java.sql.Timestamp;
import java.util.List;

public class PostService {

    private PostDAO_interface dao;

    public PostService() {
//        dao = new PostDAO();
        dao = new PostHibernateDAO();
    }

    public PostVO addPost(Integer userId, String postText, String imageUrl,
                          Timestamp postTime, Byte viewerPermission) {

        PostVO postVO = new PostVO();

        postVO.setUserId(userId);
        postVO.setPostText(postText);
        postVO.setImageUrl(imageUrl);
        postVO.setPostTime(postTime);
        postVO.setViewerPermission(viewerPermission);
        postVO.setLikesCount(0);     // 初始值
        postVO.setCommentCount(0);   // 初始值

        dao.insert(postVO);

        return postVO;
    }

//    public PostVO updatePost(Integer postId, String postText, String imageUrl,
//                             Byte viewerPermission) {
//
//        PostVO postVO = new PostVO();
//
//        postVO.setPostId(postId);
//        postVO.setPostText(postText);
//        postVO.setImageUrl(imageUrl);
//        postVO.setViewerPermission(viewerPermission);
//
//        dao.update(postVO);
//
//        return postVO;
//    }

    public PostVO updatePost(Integer postId, String postText, String imageUrl, Byte viewerPermission) {
        // 先查出舊資料
        PostVO postVO = dao.findByPrimaryKey(postId);

        if (postVO == null) {
            throw new RuntimeException("找不到要更新的貼文 ID：" + postId);
        }

        // 設置更新欄位
        postVO.setPostText(postText);
        postVO.setImageUrl(imageUrl);
        postVO.setViewerPermission(viewerPermission);

        // 執行更新
        dao.update(postVO);

        return postVO;
    }

    public void deletePost(Integer postId) {
        dao.delete(postId);
    }

    public PostVO getOnePost(Integer postId) {
        return dao.findByPrimaryKey(postId);
    }

    public List<PostVO> getAll() {
        return dao.getAll();
    }


}
