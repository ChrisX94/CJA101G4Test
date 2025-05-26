package com.shakemate.post.model;

import java.sql.Timestamp;
import java.util.List;

public class PostService {

    private PostDAO_interface dao;

    public PostService() {
        dao = new PostDAO();
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

    public PostVO updatePost(Integer postId, String postText, String imageUrl,
                             Byte viewerPermission) {

        PostVO postVO = new PostVO();

        postVO.setPostId(postId);
        postVO.setPostText(postText);
        postVO.setImageUrl(imageUrl);
        postVO.setViewerPermission(viewerPermission);

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
