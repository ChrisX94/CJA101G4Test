package com.shakemate.adm.model;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shakemate.adm.util.PasswordUtil;

//import hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_Emp3;

@Service("admService")

public class AdmService {

	@Autowired
	AdmRepository repository;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	AuthFuncRepository authFuncRepository;

	public List<AdmVO> getAll() {
		return repository.findAll();
	}

	public void addAdm(AdmVO admVO) {
		//取出使用者輸入的明文密碼
		String plainPassword = admVO.getAdmPwd();
		//呼叫工具類加密（hash）
		String hashedPassword = PasswordUtil.hashPassword(plainPassword);
		//把加密後的密碼塞回 admVO
		admVO.setAdmPwd(hashedPassword);
		repository.save(admVO);
	}

	public void updateAdm(AdmVO admVO) {
		repository.save(admVO);
	}

	public void deleteAdm(Integer admId) {
		if (repository.existsById(admId))
			repository.deleteById(admId);
	}

	public AdmVO getOneAdm(Integer admId) {
		Optional<AdmVO> optional = repository.findById(admId);
		return optional.orElse(null);
	}

	public void addAdmWithAuth(AdmVO admVO, List<Integer> authFuncIds) {
		repository.save(admVO);
		if (authFuncIds != null) {
			Set<AuthFuncVO> authFuncs = authFuncRepository.findAllById(authFuncIds).stream()
					.collect(Collectors.toSet());
			admVO.setAuthFuncs(authFuncs);
			repository.save(admVO);
		}
	}

	public void updateAdmWithAuth(AdmVO admVO, List<Integer> authFuncIds) {
		Optional<AdmVO> optional = repository.findById(admVO.getAdmId());
		if (optional.isPresent()) {
			AdmVO existing = optional.get();
			existing.setAdmName(admVO.getAdmName());
			existing.setAdmAcc(admVO.getAdmAcc());
			existing.setAdmPwd(admVO.getAdmPwd());
			if (authFuncIds != null) {
				Set<AuthFuncVO> authFuncs = authFuncRepository.findAllById(authFuncIds).stream()
						.collect(Collectors.toSet());
				existing.setAuthFuncs(authFuncs);
			} else {
				existing.setAuthFuncs(null);
			}
			repository.save(existing);
		}
	}

	public List<AdmVO> findByName(String name) {
		return repository.findByAdmNameContaining(name);
	}

	public AdmVO findByAcc(String admAcc) {
		return repository.findByAdmAcc(admAcc); // 你要在 repository 寫這個方法
	}

	public List<AdmVO> findByConditions(String admName, String admAccount, String authName) {
		return null;
	}

	public AdmVO validateLogin(String admAcc, String admPwd) {
		AdmVO adm = repository.findByAdmAcc(admAcc); // only search for account
		if (adm != null && adm.getAdmPwd().equals(admPwd)) {
			return adm;
		}
		return null;

	}

}
