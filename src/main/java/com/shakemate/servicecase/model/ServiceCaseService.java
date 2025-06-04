package com.shakemate.servicecase.model;

import java.sql.Timestamp;
import java.util.List;

public class ServiceCaseService {

    private ServiceCaseDAO_interface dao;

    public ServiceCaseService() {
        dao = new ServiceCaseJDBCDAO();
    }

    public ServiceCaseVO addServiceCase(Integer userId, Integer admId, Integer caseTypeId,
                                        Timestamp createTime, Timestamp updateTime,
                                        String title, String content, Integer caseStatus) {

        ServiceCaseVO vo = new ServiceCaseVO();
        vo.setUserId(userId);
        vo.setAdmId(admId);
        vo.setCaseTypeId(caseTypeId);
        vo.setCreateTime(createTime);
        vo.setUpdateTime(updateTime);
        vo.setTitle(title);
        vo.setContent(content);
        vo.setCaseStatus(caseStatus);
        dao.insert(vo);

        return vo;
    }

    public ServiceCaseVO updateServiceCase(Integer caseId, Integer userId, Integer admId, Integer caseTypeId,
                                           Timestamp createTime, Timestamp updateTime,
                                           String title, String content, Integer caseStatus) {

        ServiceCaseVO vo = new ServiceCaseVO();
        vo.setCaseId(caseId);
        vo.setUserId(userId);
        vo.setAdmId(admId);
        vo.setCaseTypeId(caseTypeId);
        vo.setCreateTime(createTime);
        vo.setUpdateTime(updateTime);
        vo.setTitle(title);
        vo.setContent(content);
        vo.setCaseStatus(caseStatus);
        dao.update(vo);

        return vo;
    }

    public void deleteServiceCase(Integer caseId) {
        dao.delete(caseId);
    }

    public ServiceCaseVO getOneServiceCase(Integer caseId) {
        return dao.findByPrimaryKey(caseId);
    }

    public List<ServiceCaseVO> getAll() {
        return dao.getAll();
    }
}
