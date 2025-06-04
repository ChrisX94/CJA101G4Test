package com.shakemate.servicecase.model;

import java.sql.*;
import java.util.*;

public class ServiceCaseJDBCDAO implements ServiceCaseDAO_interface {

    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/testshakemate?serverTimezone=Asia/Taipei";
    String userid = "root";
    String passwd = "12345678";

    private static final String INSERT_STMT = 
        "INSERT INTO servicecase (USER_ID, ADM_ID, CASE_TYPE_ID, CREATE_TIME, UPDATE_TIME, TITLE, CONTENT, CASE_STATUS) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = 
        "UPDATE servicecase SET USER_ID=?, ADM_ID=?, CASE_TYPE_ID=?, CREATE_TIME=?, UPDATE_TIME=?, TITLE=?, CONTENT=?, CASE_STATUS=? WHERE CASE_ID = ?";
    private static final String DELETE = 
        "DELETE FROM servicecase WHERE CASE_ID = ?";
    private static final String GET_ONE_STMT = 
        "SELECT * FROM servicecase WHERE CASE_ID = ?";
    private static final String GET_ALL_STMT = 
        "SELECT * FROM servicecase ORDER BY CASE_ID DESC";

    @Override
    public void insert(ServiceCaseVO vo) {
        try (
            Connection con = DriverManager.getConnection(url, userid, passwd);
            PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)
        ) {
            Class.forName(driver);
            pstmt.setInt(1, vo.getUserId());
            pstmt.setInt(2, vo.getAdmId());
            pstmt.setInt(3, vo.getCaseTypeId());
            pstmt.setTimestamp(4, vo.getCreateTime());
            pstmt.setTimestamp(5, vo.getUpdateTime());
            pstmt.setString(6, vo.getTitle());
            pstmt.setString(7, vo.getContent());
            pstmt.setInt(8, vo.getCaseStatus());
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Insert fail: " + e.getMessage());
        }
    }

    @Override
    public void update(ServiceCaseVO vo) {
        try (
            Connection con = DriverManager.getConnection(url, userid, passwd);
            PreparedStatement pstmt = con.prepareStatement(UPDATE)
        ) {
            Class.forName(driver);
            pstmt.setInt(1, vo.getUserId());
            pstmt.setInt(2, vo.getAdmId());
            pstmt.setInt(3, vo.getCaseTypeId());
            pstmt.setTimestamp(4, vo.getCreateTime());
            pstmt.setTimestamp(5, vo.getUpdateTime());
            pstmt.setString(6, vo.getTitle());
            pstmt.setString(7, vo.getContent());
            pstmt.setInt(8, vo.getCaseStatus());
            pstmt.setInt(9, vo.getCaseId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Update fail: " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer caseId) {
        try (
            Connection con = DriverManager.getConnection(url, userid, passwd);
            PreparedStatement pstmt = con.prepareStatement(DELETE)
        ) {
            Class.forName(driver);
            pstmt.setInt(1, caseId);
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Delete fail: " + e.getMessage());
        }
    }

    @Override
    public ServiceCaseVO findByPrimaryKey(Integer caseId) {
        ServiceCaseVO vo = null;
        try (
            Connection con = DriverManager.getConnection(url, userid, passwd);
            PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT)
        ) {
            Class.forName(driver);
            pstmt.setInt(1, caseId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                vo = new ServiceCaseVO();
                vo.setCaseId(rs.getInt("CASE_ID"));
                vo.setUserId(rs.getInt("USER_ID"));
                vo.setAdmId(rs.getInt("ADM_ID"));
                vo.setCaseTypeId(rs.getInt("CASE_TYPE_ID"));
                vo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
                vo.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
                vo.setTitle(rs.getString("TITLE"));
                vo.setContent(rs.getString("CONTENT"));
                vo.setCaseStatus(rs.getInt("CASE_STATUS"));
            }
        } catch (Exception e) {
            throw new RuntimeException("Find by PK fail: " + e.getMessage());
        }
        return vo;
    }

    @Override
    public List<ServiceCaseVO> getAll() {
        List<ServiceCaseVO> list = new ArrayList<>();
        try (
            Connection con = DriverManager.getConnection(url, userid, passwd);
            PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);
            ResultSet rs = pstmt.executeQuery()
        ) {
            Class.forName(driver);
            while (rs.next()) {
                ServiceCaseVO vo = new ServiceCaseVO();
                vo.setCaseId(rs.getInt("CASE_ID"));
                vo.setUserId(rs.getInt("USER_ID"));
                vo.setAdmId(rs.getInt("ADM_ID"));
                vo.setCaseTypeId(rs.getInt("CASE_TYPE_ID"));
                vo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
                vo.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
                vo.setTitle(rs.getString("TITLE"));
                vo.setContent(rs.getString("CONTENT"));
                vo.setCaseStatus(rs.getInt("CASE_STATUS"));
                list.add(vo);
            }
        } catch (Exception e) {
            throw new RuntimeException("Get all fail: " + e.getMessage());
        }
        return list;
    }
    
    public static void main(String[] args) {
        ServiceCaseJDBCDAO dao = new ServiceCaseJDBCDAO();

        // 測試新增
        ServiceCaseVO vo = new ServiceCaseVO();
        vo.setUserId(1);
        vo.setAdmId(1);
        vo.setCaseTypeId(1);
        vo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        vo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        vo.setTitle("測試標題");
        vo.setContent("這是測試內容");
        vo.setCaseStatus(0);
        dao.insert(vo);

        System.out.println("新增成功");
    }

}
