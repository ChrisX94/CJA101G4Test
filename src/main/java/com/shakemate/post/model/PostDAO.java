package com.shakemate.post.model;


//import org.junit.jupiter.api.Test;

import com.shakemate.post.vo.PostVO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostDAO implements PostDAO_interface{

    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/testshakemate?serverTimezone=Asia/Taipei";
    String userid = "root";
    String passwd = "hsp123456";

    private static DataSource ds = null;
    static {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/testshakemate");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private static final String INSERT_STMT =
            "INSERT INTO POST (USER_ID, POST_TEXT, IMAGE_URL, POST_TIME, VIEWER_PERMISSION, LIKES_COUNT, COMMENT_COUNT) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE =
            "UPDATE POST SET USER_ID = ?, POST_TEXT = ?, IMAGE_URL = ?, POST_TIME = ?, VIEWER_PERMISSION = ?, LIKES_COUNT = ?, COMMENT_COUNT = ? "
                    + "WHERE POST_ID = ?";

    private static final String DELETE_POST =
            "DELETE FROM POST WHERE POST_ID = ?";

    private static final String GET_ONE_STMT =
            "SELECT * FROM POST WHERE POST_ID = ?";

    private static final String GET_ALL_STMT =
            "SELECT * FROM POST ORDER BY POST_ID";


//    @Test
    public void Test() {
        PostVO postVO = new PostVO();
        postVO.setUserId(1);  // 假設 userId 1 存在
        postVO.setPostText("這是一篇測試貼文。");
        postVO.setImageUrl("https://example.com/image.jpg"); // 可為 null 或空字串
        postVO.setPostTime(Timestamp.valueOf(LocalDateTime.now()));
        postVO.setViewerPermission((byte) 0); // 0: 所有人
        postVO.setLikesCount(0); // 初始為 0
        postVO.setCommentCount(0); // 初始為 0

        insert(postVO);
        System.out.println("貼文新增成功！");


//        PostVO postVO = new PostVO();
//        postVO.setPostId(1); // 替換為實際存在的 POST_ID
//        postVO.setUserId(1);
//        postVO.setPostText("這是一篇修改過的貼文內容。");
//        postVO.setImageUrl("https://example.com/updated_image.jpg");
//        postVO.setPostTime(new Timestamp(System.currentTimeMillis()));
//        postVO.setViewerPermission((byte) 2); // 僅限自己
//        postVO.setLikesCount(5);
//        postVO.setCommentCount(3);
//
//        update(postVO);
//        System.out.println("修改貼文成功");



//        delete(11); // 替換為實際存在的 POST_ID
//        System.out.println("刪除貼文成功");

//        PostVO postVO = findByPrimaryKey(1); // 替換為實際存在的 POST_ID
//
//        if (postVO != null) {
//            System.out.println("貼文內容：" + postVO.getPostText());
//            System.out.println("發文者：" + postVO.getUserId());
//        } else {
//            System.out.println("查無貼文");
//        }

//        List<PostVO> postList = getAll();
//
//        for (PostVO postVO : postList) {
//            System.out.println("ID: " + postVO.getPostId() + ", 發文者: " + postVO.getUserId() + ", 內容: " + postVO.getPostText());
//        }


    }

    @Override
    public void insert(PostVO postVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try{

//            Class.forName(driver);
//            con = DriverManager.getConnection(url, userid, passwd);
            con = ds.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setInt(1, postVO.getUserId());
            pstmt.setString(2, postVO.getPostText());
            pstmt.setString(3, postVO.getImageUrl());
            pstmt.setTimestamp(4, postVO.getPostTime());
            pstmt.setByte(5, postVO.getViewerPermission());
            pstmt.setInt(6, postVO.getLikesCount());
            pstmt.setInt(7, postVO.getCommentCount());

            pstmt.executeUpdate();

        }
//        catch (ClassNotFoundException e) {
//            throw new RuntimeException("Couldn't load database driver. "
//                    + e.getMessage());
//            // Handle any SQL errors
//        }
        catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }

    }

    @Override
    public void update(PostVO postVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try{

//            Class.forName(driver);
//            con = DriverManager.getConnection(url, userid, passwd);
            con = ds.getConnection();
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setInt(1, postVO.getUserId());
            pstmt.setString(2, postVO.getPostText());
            pstmt.setString(3, postVO.getImageUrl());
            pstmt.setTimestamp(4, postVO.getPostTime());
            pstmt.setByte(5, postVO.getViewerPermission());
            pstmt.setInt(6, postVO.getLikesCount());
            pstmt.setInt(7, postVO.getCommentCount());
            pstmt.setInt(8, postVO.getPostId());

            pstmt.executeUpdate();

        }
//        catch (ClassNotFoundException e) {
//            throw new RuntimeException("Couldn't load database driver. "
//                    + e.getMessage());
//            // Handle any SQL errors
//        }
        catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    @Override
    public void delete(Integer postId) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try{

//            Class.forName(driver);
//            con = DriverManager.getConnection(url, userid, passwd);
            con = ds.getConnection();
            pstmt = con.prepareStatement(DELETE_POST);

            pstmt.setInt(1, postId);
            pstmt.executeUpdate();

        }
//        catch (ClassNotFoundException e) {
//            throw new RuntimeException("Couldn't load database driver. "
//                    + e.getMessage());
//            // Handle any SQL errors
//        }
        catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }

    }

    @Override
    public PostVO findByPrimaryKey(Integer postId) {

        PostVO postVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{

//            Class.forName(driver);
//            con = DriverManager.getConnection(url, userid, passwd);
            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, postId);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                postVO = new PostVO();

                postVO.setPostId(rs.getInt("POST_ID"));
                postVO.setUserId(rs.getInt("USER_ID"));
                postVO.setPostText(rs.getString("POST_TEXT"));
                postVO.setImageUrl(rs.getString("IMAGE_URL"));
                postVO.setPostTime(rs.getTimestamp("POST_TIME"));
                postVO.setViewerPermission(rs.getByte("VIEWER_PERMISSION"));
                postVO.setLikesCount(rs.getInt("LIKES_COUNT"));
                postVO.setCommentCount(rs.getInt("COMMENT_COUNT"));

            }

        }
//        catch (ClassNotFoundException e) {
//            throw new RuntimeException("Couldn't load database driver. "
//                    + e.getMessage());
//            // Handle any SQL errors
//        }
        catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }

        return postVO;
    }

    @Override
    public List<PostVO> getAll() {

        List<PostVO> list = new ArrayList<PostVO>();
        PostVO postVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{

//            Class.forName(driver);
//            con = DriverManager.getConnection(url, userid, passwd);
            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                postVO = new PostVO();

                postVO.setPostId(rs.getInt("POST_ID"));
                postVO.setUserId(rs.getInt("USER_ID"));
                postVO.setPostText(rs.getString("POST_TEXT"));
                postVO.setImageUrl(rs.getString("IMAGE_URL"));
                postVO.setPostTime(rs.getTimestamp("POST_TIME"));
                postVO.setViewerPermission(rs.getByte("VIEWER_PERMISSION"));
                postVO.setLikesCount(rs.getInt("LIKES_COUNT"));
                postVO.setCommentCount(rs.getInt("COMMENT_COUNT"));


                list.add(postVO);

            }


        }
//        catch (ClassNotFoundException e) {
//            throw new RuntimeException("Couldn't load database driver. "
//                    + e.getMessage());
//            // Handle any SQL errors
//        }
        catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }

        return list;
    }
}
