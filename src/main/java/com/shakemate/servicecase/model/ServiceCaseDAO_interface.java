package com.shakemate.servicecase.model;

import java.util.*;

public interface ServiceCaseDAO_interface {
    public void insert(ServiceCaseVO serviceCaseVO);
    public void update(ServiceCaseVO serviceCaseVO);
    public void delete(Integer caseId);
    public ServiceCaseVO findByPrimaryKey(Integer caseId);
    public List<ServiceCaseVO> getAll();
}
