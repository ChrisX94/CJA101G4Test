package com.shakemate.activity.model;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Version 1
public class ActivityDAO implements ActivityDAO_interface {

    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/testshakemate?serverTimezone=Asia/Taipei";
    String userid = "root";
    String passwd = "hys123456";


    private static final String INSERT_STMT =
            "INSERT INTO activity (" +
                    "user_id, title, content, image_url, location, activity_status, " +
                    "created_time, updated_time, reg_start_time, reg_end_time, " +
                    "activ_start_time, activ_end_time, gender_filter, max_age, min_age, " +
                    "expired_time, max_people, min_people, signup_count, rating_count, " +
                    "rating, comment_count, report_count" +
                    ") VALUES (" +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?" +
                    ")";

    private static final String GET_ALL_STMT =
            "SELECT " +
                    "activity_id, user_id, title, content, image_url, location, activity_status, " +
                    "created_time, updated_time, reg_start_time, reg_end_time, " +
                    "activ_start_time, activ_end_time, gender_filter, max_age, min_age, " +
                    "expired_time, max_people, min_people, signup_count, rating_count, " +
                    "rating, comment_count, report_count " +
                    "FROM activity";

    private static final String GET_ONE_STMT =
            "SELECT " +
                    "activity_id, user_id, title, content, image_url, location, activity_status, " +
                    "created_time, updated_time, reg_start_time, reg_end_time, " +
                    "activ_start_time, activ_end_time, gender_filter, max_age, min_age, " +
                    "expired_time, max_people, min_people, signup_count, rating_count, " +
                    "rating, comment_count, report_count " +
                    "FROM activity WHERE activity_id = ?";

    private static final String DELETE_ACTIV =
            "DELETE FROM activity WHERE activity_id = ?";

    private static final String UPDATE =
            "UPDATE activity SET " +
                    "user_id = ?, " +
                    "title = ?, " +
                    "content = ?, " +
                    "image_url = ?, " +
                    "location = ?, " +
                    "activity_status = ?, " +
                    "created_time = ?, " +
                    "updated_time = ?, " +
                    "reg_start_time = ?, " +
                    "reg_end_time = ?, " +
                    "activ_start_time = ?, " +
                    "activ_end_time = ?, " +
                    "gender_filter = ?, " +
                    "max_age = ?, " +
                    "min_age = ?, " +
                    "expired_time = ?, " +
                    "max_people = ?, " +
                    "min_people = ?, " +
                    "signup_count = ?, " +
                    "rating_count = ?, " +
                    "rating = ?, " +
                    "comment_count = ?, " +
                    "report_count = ? " +
                    "WHERE activity_id = ?";


    @Test
    public void test() {
        ActivityVO activityVO = new ActivityVO(
                null,
                4,
                "活動標題99",
                "活動內容描述10",
                "https://example.com/image10.jpg",
                "台北市中正區10號",
                (byte) 2,
                Timestamp.valueOf("2025-05-18 14:10:05"),
                Timestamp.valueOf("2025-05-19 11:24:05"),
                Timestamp.valueOf("2025-05-19 22:15:05"),
                Timestamp.valueOf("2025-05-22 02:22:05"),
                Timestamp.valueOf("2025-05-22 22:04:05"),
                Timestamp.valueOf("2025-05-25 03:24:05"),
                (byte) 1,
                27,
                22,
                Timestamp.valueOf("2025-05-23 11:35:05"),
                17,
                12,
                0,
                3,
                8,
                2,
                4
        );

        insert(activityVO);
        System.out.println("Insert OK!");

//        update(activityVO);
//        System.out.println("Update OK!");

//        delete(12);
//        System.out.println("Delete OK!");

//        ActivityVO byPrimaryKey = findByPrimaryKey(11);
//        System.out.println(byPrimaryKey);

//        List<ActivityVO> all = getAll();
//        System.out.println(all);

    }

    @Override
    public void insert(ActivityVO activityVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setInt(1, activityVO.getUserId());
            pstmt.setString(2, activityVO.getTitle());
            pstmt.setString(3, activityVO.getContent());
            pstmt.setString(4, activityVO.getImageUrl());
            pstmt.setString(5, activityVO.getLocation());
            pstmt.setByte(6, activityVO.getActivityStatus());
            pstmt.setTimestamp(7, activityVO.getCreatedTime());
            pstmt.setTimestamp(8, activityVO.getUpdatedTime());
            pstmt.setTimestamp(9, activityVO.getRegStartTime());
            pstmt.setTimestamp(10, activityVO.getRegEndTime());
            pstmt.setTimestamp(11, activityVO.getActivStartTime());
            pstmt.setTimestamp(12, activityVO.getActivEndTime());
            pstmt.setByte(13, activityVO.getGenderFilter());
            pstmt.setInt(14, activityVO.getMaxAge());
            pstmt.setInt(15, activityVO.getMinAge());
            pstmt.setTimestamp(16, activityVO.getExpiredTime());
            pstmt.setInt(17, activityVO.getMaxPeople());
            pstmt.setInt(18, activityVO.getMinPeople());
            pstmt.setInt(19, activityVO.getSignupCount());
            pstmt.setInt(20, activityVO.getRatingCount());
            pstmt.setInt(21, activityVO.getRating());
            pstmt.setInt(22, activityVO.getCommentCount());
            pstmt.setInt(23, activityVO.getReportCount());


            pstmt.executeUpdate();

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
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
    public void update(ActivityVO activityVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setInt(1, activityVO.getUserId());
            pstmt.setString(2, activityVO.getTitle());
            pstmt.setString(3, activityVO.getContent());
            pstmt.setString(4, activityVO.getImageUrl());
            pstmt.setString(5, activityVO.getLocation());
            pstmt.setByte(6, activityVO.getActivityStatus());
            pstmt.setTimestamp(7, activityVO.getCreatedTime());
            pstmt.setTimestamp(8, activityVO.getUpdatedTime());
            pstmt.setTimestamp(9, activityVO.getRegStartTime());
            pstmt.setTimestamp(10, activityVO.getRegEndTime());
            pstmt.setTimestamp(11, activityVO.getActivStartTime());
            pstmt.setTimestamp(12, activityVO.getActivEndTime());
            pstmt.setByte(13, activityVO.getGenderFilter());
            pstmt.setInt(14, activityVO.getMaxAge());
            pstmt.setInt(15, activityVO.getMinAge());
            pstmt.setTimestamp(16, activityVO.getExpiredTime());
            pstmt.setInt(17, activityVO.getMaxPeople());
            pstmt.setInt(18, activityVO.getMinPeople());
            pstmt.setInt(19, activityVO.getSignupCount());
            pstmt.setInt(20, activityVO.getRatingCount());
            pstmt.setInt(21, activityVO.getRating());
            pstmt.setInt(22, activityVO.getCommentCount());
            pstmt.setInt(23, activityVO.getReportCount());
            pstmt.setInt(24, activityVO.getActivityId()); // WHERE 條件


            pstmt.executeUpdate();

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
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
    public void delete(Integer activityId) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);

            // 1●設定於 pstm.executeUpdate()之前
            con.setAutoCommit(false);

            //
            pstmt = con.prepareStatement(DELETE_ACTIV);
            pstmt.setInt(1, activityId);
            pstmt.executeUpdate();

            // 2●設定於 pstm.executeUpdate()之後
            con.commit();
            con.setAutoCommit(true);

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            if (con != null) {
                try {
                    // 3●設定於當有exception發生時之catch區塊內
                    con.rollback();
                } catch (SQLException excep) {
                    throw new RuntimeException("rollback error occured. "
                            + excep.getMessage());
                }
            }
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
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
    public ActivityVO findByPrimaryKey(Integer activityId) {
        ActivityVO activityVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, activityId);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                activityVO = new ActivityVO();

                activityVO.setActivityId(rs.getInt("activity_id"));
                activityVO.setUserId(rs.getInt("user_id"));
                activityVO.setTitle(rs.getString("title"));
                activityVO.setContent(rs.getString("content"));
                activityVO.setImageUrl(rs.getString("image_url"));
                activityVO.setLocation(rs.getString("location"));
                activityVO.setActivityStatus(rs.getByte("activity_status"));
                activityVO.setCreatedTime(rs.getTimestamp("created_time"));
                activityVO.setUpdatedTime(rs.getTimestamp("updated_time"));
                activityVO.setRegStartTime(rs.getTimestamp("reg_start_time"));
                activityVO.setRegEndTime(rs.getTimestamp("reg_end_time"));
                activityVO.setActivStartTime(rs.getTimestamp("activ_start_time"));
                activityVO.setActivEndTime(rs.getTimestamp("activ_end_time"));
                activityVO.setGenderFilter(rs.getByte("gender_filter"));
                activityVO.setMaxAge(rs.getInt("max_age"));
                activityVO.setMinAge(rs.getInt("min_age"));
                activityVO.setExpiredTime(rs.getTimestamp("expired_time"));
                activityVO.setMaxPeople(rs.getInt("max_people"));
                activityVO.setMinPeople(rs.getInt("min_people"));
                activityVO.setSignupCount(rs.getInt("signup_count"));
                activityVO.setRatingCount(rs.getInt("rating_count"));
                activityVO.setRating(rs.getInt("rating"));
                activityVO.setCommentCount(rs.getInt("comment_count"));
                activityVO.setReportCount(rs.getInt("report_count"));

            }

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
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
        return activityVO;
    }

    @Override
    public List<ActivityVO> getAll() {
        List<ActivityVO> list = new ArrayList<ActivityVO>();
        ActivityVO activityVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                activityVO = new ActivityVO();

                activityVO.setActivityId(rs.getInt("activity_id"));
                activityVO.setUserId(rs.getInt("user_id"));
                activityVO.setTitle(rs.getString("title"));
                activityVO.setContent(rs.getString("content"));
                activityVO.setImageUrl(rs.getString("image_url"));
                activityVO.setLocation(rs.getString("location"));
                activityVO.setActivityStatus(rs.getByte("activity_status"));
                activityVO.setCreatedTime(rs.getTimestamp("created_time"));
                activityVO.setUpdatedTime(rs.getTimestamp("updated_time"));
                activityVO.setRegStartTime(rs.getTimestamp("reg_start_time"));
                activityVO.setRegEndTime(rs.getTimestamp("reg_end_time"));
                activityVO.setActivStartTime(rs.getTimestamp("activ_start_time"));
                activityVO.setActivEndTime(rs.getTimestamp("activ_end_time"));
                activityVO.setGenderFilter(rs.getByte("gender_filter"));
                activityVO.setMaxAge(rs.getInt("max_age"));
                activityVO.setMinAge(rs.getInt("min_age"));
                activityVO.setExpiredTime(rs.getTimestamp("expired_time"));
                activityVO.setMaxPeople(rs.getInt("max_people"));
                activityVO.setMinPeople(rs.getInt("min_people"));
                activityVO.setSignupCount(rs.getInt("signup_count"));
                activityVO.setRatingCount(rs.getInt("rating_count"));
                activityVO.setRating(rs.getInt("rating"));
                activityVO.setCommentCount(rs.getInt("comment_count"));
                activityVO.setReportCount(rs.getInt("report_count"));

                list.add(activityVO); // Store the row in the list
            }

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
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
