package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.SubjectEntity;
import com.example.demo.form.SubjectForm;
import com.example.demo.repository.SubjectRepository;

/**
 * 科目情報 Service
 */
@Service
public class SubjectService {
	/**
	 * 科目情報 Repository
	 */
	@Autowired
	private SubjectRepository subjectRepository;

	/**
	 * 科目情報 全検索
	 * @return  検索結果
	 */
	public List<SubjectEntity> searchAll() {
		return subjectRepository.findAll();
	}
	
	/**
	 * 科目情報 新規登録
	 * @param  subject 科目情報
	 */
	public void create(SubjectForm subjectRequest) {
		SubjectEntity subject = new SubjectEntity();
		subject.setSubject(subjectRequest.getSubject());
		subject.setNote(subjectRequest.getNote());
		subjectRepository.save(subject);
	}
	
	/**
	 * 科目情報 主キー検索
	 * @return  検索結果
	 */
	public SubjectEntity findById(Integer id) {
		return subjectRepository.getOne(id);
	}
	
	/**
	 * 科目情報 更新
	 * @param  subject 科目情報
	 */
	public void update(SubjectForm subjectUpdateRequest) {
		SubjectEntity subject = findById(subjectUpdateRequest.getId());
		subject.setSubject(subjectUpdateRequest.getSubject());
		subject.setNote(subjectUpdateRequest.getNote());
		subjectRepository.save(subject);
	}
	
	/**
	 * 科目情報 物理削除
	 * @param  id ID
	 */
	public void delete(Integer id) {
		SubjectEntity subject = findById(id);
		subjectRepository.delete(subject);
	}
}