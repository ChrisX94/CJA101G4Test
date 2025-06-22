package com.shakemate.activity.service;

import com.shakemate.activity.dto.ActivityReportCreateDTO;
import com.shakemate.activity.dto.ActivityReportDTO;
import com.shakemate.activity.dto.ActivityReportUpdateDTO;
import com.shakemate.activity.entity.ActivityReport;

import java.util.List;

public interface ActivityReportService {

    ActivityReportDTO getById(Integer id);
    List<ActivityReportDTO> getAll();
    ActivityReportDTO create(ActivityReportCreateDTO createDTO);
    ActivityReportDTO update(Integer id, ActivityReportUpdateDTO updateDTO);
    void delete(Integer id);
}
