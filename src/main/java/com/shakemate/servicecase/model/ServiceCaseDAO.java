package com.shakemate.servicecase.model;

import java.sql.*;
import java.util.*;

public class ServiceCaseDAO implements ServiceCaseDAO_interface {
	private static final String INSERT = "INSERT INTO servicecase (USER_ID, ADM_ID, CASE_TYPE_ID, CREATE_TIME, UPDATE_TIME, TITLE, CONTENT, CASE_STATUS) VALUES (?, ?, ?, NOW(), NOW(), ?, ?, ?)";
	private static final String UPDATE = "UPDATE servicecase SET USER_ID=?, ADM_ID=?, CASE_TYPE_ID=?, UPDATE_TIME=NOW(), TITLE=?, CONTENT=?, CASE_STATUS=? WHERE CASE_ID=?";
	private static final String DELETE = "DELETE FROM servicecase WHERE CASE_ID=?";
	private static final String GET_ONE = "SELECT * FROM servicecase WHERE CASE_ID=?";
	private static final String GET_ALL = "SELECT * FROM servicecase";

	// 建立資料庫連線
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/你的資料庫名稱", "root", "12345678");
	}
	@Override
	public void insert(ServiceCaseVO vo) {
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(INSERT)) {
			ps.setInt(1, vo.getUserId());
			ps.setInt(2, vo.getAdmId());
			ps.setInt(3, vo.getCaseTypeId());
			ps.setString(4, vo.getTitle());
			ps.setString(5, vo.getContent());
			ps.setInt(6, vo.getCaseStatus());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void update(ServiceCaseVO vo) {
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(UPDATE)) {
			ps.setInt(1, vo.getUserId());
			ps.setInt(2, vo.getAdmId());
			ps.setInt(3, vo.getCaseTypeId());
			ps.setString(4, vo.getTitle());
			ps.setString(5, vo.getContent());
			ps.setInt(6, vo.getCaseStatus());
			ps.setInt(7, vo.getCaseId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void delete(Integer  caseId) {
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(DELETE)) {
			ps.setInt(1, caseId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public ServiceCaseVO findByPrimaryKey(Integer caseId) {
		ServiceCaseVO vo = null;
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(GET_ONE)) {
			ps.setInt(1, caseId);
			ResultSet rs = ps.executeQuery();
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
	@Override
	public List<ServiceCaseVO> getAll() {
		List<ServiceCaseVO> list = new ArrayList<>();
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(GET_ALL)) {
			ResultSet rs = ps.executeQuery();
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
