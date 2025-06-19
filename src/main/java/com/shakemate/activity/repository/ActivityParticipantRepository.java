package com.shakemate.activity.repository;

import com.shakemate.activity.entity.id.ActivityParticipantId;
import com.shakemate.activity.entity.ActivityParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityParticipantRepository extends JpaRepository<ActivityParticipant, ActivityParticipantId> {

}
