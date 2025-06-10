package com.shakemate.user.repository;

import com.shakemate.activity.vo.ActivityVO;
import com.shakemate.user.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {

}
