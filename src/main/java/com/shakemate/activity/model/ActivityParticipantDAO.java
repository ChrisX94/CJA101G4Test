package com.shakemate.activity.model;


import com.shakemate.activity.model.vo.ActivityParticipantVO;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityParticipantDAO implements ActivityParticipantDAO_interface {


    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/testshakemate?serverTimezone=Asia/Taipei";
    String userid = "root";
    String passwd = "hys123456";

    private static final String INSERT_STMT =
            "INSERT INTO activity_participant (" +
                    "participant_id, activity_id, adm_review_time, par_status, applying_date, rating, review_content, review_time) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_STMT =
            "UPDATE activity_participant SET " +
                    "adm_review_time = ?, par_status = ?, applying_date = ?, rating = ?, review_content = ?, review_time = ? " +
                    "WHERE participant_id = ? AND activity_id = ?";

    private static final String DELETE_STMT =
            "DELETE FROM activity_participant WHERE participant_id = ? AND activity_id = ?";

    private static final String FIND_BY_PK_STMT =
            "SELECT participant_id, activity_id, adm_review_time, par_status, applying_date, rating, review_content, review_time " +
                    "FROM activity_participant WHERE participant_id = ? AND activity_id = ?";

    private static final String GET_ALL_STMT =
            "SELECT participant_id, activity_id, adm_review_time, par_status, applying_date, rating, review_content, review_time " +
                    "FROM activity_participant";

    @Test
    public void test() {
        List<ActivityParticipantVO> all = getAll();
        System.out.println(all);
    }

    @Override
    public void insert(ActivityParticipantVO vo) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setInt(1, vo.getParticipantId());
            pstmt.setInt(2, vo.getActivityId());
            pstmt.setTimestamp(3, vo.getAdmReviewTime());
            pstmt.setByte(4, vo.getParStatus());
            pstmt.setTimestamp(5, vo.getApplyingDate());
            pstmt.setByte(6, vo.getRating());
            pstmt.setString(7, vo.getReviewContent());
            pstmt.setTimestamp(8, vo.getReviewTime());

            pstmt.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. " + se.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException se) {
                se.printStackTrace(System.err);
            }
        }
    }

    @Override
    public void update(ActivityParticipantVO vo) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(UPDATE_STMT);

            pstmt.setTimestamp(1, vo.getAdmReviewTime());
            pstmt.setByte(2, vo.getParStatus());
            pstmt.setTimestamp(3, vo.getApplyingDate());
            pstmt.setByte(4, vo.getRating());
            pstmt.setString(5, vo.getReviewContent());
            pstmt.setTimestamp(6, vo.getReviewTime());

            pstmt.setInt(7, vo.getParticipantId());
            pstmt.setInt(8, vo.getActivityId());

            pstmt.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. " + se.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException se) {
                se.printStackTrace(System.err);
            }
        }
    }

    @Override
    public void delete(Integer participantId, Integer activityId) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            con.setAutoCommit(false);

            pstmt = con.prepareStatement(DELETE_STMT);
            pstmt.setInt(1, participantId);
            pstmt.setInt(2, activityId);
            pstmt.executeUpdate();

            con.commit();
            con.setAutoCommit(true);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
        } catch (SQLException se) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException excep) {
                    throw new RuntimeException("Rollback error occurred. " + excep.getMessage());
                }
            }
            throw new RuntimeException("A database error occurred. " + se.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException se) {
                se.printStackTrace(System.err);
            }
        }
    }

    @Override
    public ActivityParticipantVO findByPrimaryKey(Integer participantId, Integer activityId) {
        ActivityParticipantVO vo = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(FIND_BY_PK_STMT);

            pstmt.setInt(1, participantId);
            pstmt.setInt(2, activityId);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                vo = new ActivityParticipantVO();

                vo.setParticipantId(rs.getInt("participant_id"));
                vo.setActivityId(rs.getInt("activity_id"));
                vo.setAdmReviewTime(rs.getTimestamp("adm_review_time"));
                vo.setParStatus(rs.getByte("par_status"));
                vo.setApplyingDate(rs.getTimestamp("applying_date"));
                vo.setRating(rs.getByte("rating"));
                vo.setReviewContent(rs.getString("review_content"));
                vo.setReviewTime(rs.getTimestamp("review_time"));
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. " + se.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException se) {
                se.printStackTrace(System.err);
            }
        }
        return vo;
    }

    @Override
    public List<ActivityParticipantVO> getAll() {
        List<ActivityParticipantVO> list = new ArrayList<>();
        ActivityParticipantVO vo = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                vo = new ActivityParticipantVO();

                vo.setParticipantId(rs.getInt("participant_id"));
                vo.setActivityId(rs.getInt("activity_id"));
                vo.setAdmReviewTime(rs.getTimestamp("adm_review_time"));
                vo.setParStatus(rs.getByte("par_status"));
                vo.setApplyingDate(rs.getTimestamp("applying_date"));
                vo.setRating(rs.getByte("rating"));
                vo.setReviewContent(rs.getString("review_content"));
                vo.setReviewTime(rs.getTimestamp("review_time"));

                list.add(vo);
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. " + se.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException se) {
                se.printStackTrace(System.err);
            }
        }
        return list;
    }


}
