package com.shakemate.activity.repository;

import com.shakemate.activity.vo.ActivityParticipantId;
import com.shakemate.activity.vo.ActivityParticipantVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityParticipantRepository extends JpaRepository<ActivityParticipantVO, ActivityParticipantId> {

}
