package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.SubjectEntity;
import com.example.demo.form.SubjectForm;
import com.example.demo.service.SubjectService;

/**
 * 科目情報 Controller
 */
@Controller
public class SubjectController {

	/**
	 * 科目情報 Service
	 */
	@Autowired
	SubjectService subjectService;

	/**
	 * 科目情報一覧画面を表示
	 * @param  model Model
	 * @return  科目情報一覧画面のHTML
	 */
	@GetMapping("/subject/list")
	public String subjectList(Model model) {
		List<SubjectEntity> subjectlist = subjectService.searchAll();
		model.addAttribute("subjectlist", subjectlist);
		return "subject/list";
	}
	
	/**
	 * 科目新規登録画面を表示
	 * @param  model Model
	 * @return  科目情報一覧画面
	 */
	@GetMapping("/subject/add")
	public String subjectRegister(Model model) {
		model.addAttribute("subjectRequest", new SubjectForm());
		return "subject/add";
	}
	
	/**
	 * 科目新規登録
	 * @param  userRequest リクエストデータ
	 * @param  model Model
	 * @return  科目情報一覧画面
	 */
	@PostMapping("/subject/create")
	public String subjectCreate(@Validated  @ModelAttribute  SubjectForm subjectRequest, BindingResult result, Model model) {
		if (result.hasErrors()) {
			// 入力チェックエラーの場合
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("subjectRequest", new SubjectForm());
			model.addAttribute("validationError", errorList);
			return "subject/add";
		}
		// 科目情報の登録
		subjectService.create(subjectRequest);
		return "redirect:/subject/list";
	}
	
	/**
	 * 科目情報詳細画面を表示
	 * @param  id 表示する科目ID
	 * @param  model Model
	 * @return  科目情報詳細画面
	 */
	@GetMapping("/subject/{id}")
	public String userDetail(@PathVariable  Integer id, Model model) {
		SubjectEntity subject = subjectService.findById(id);
		model.addAttribute("subject", subject);
		return "subject/detail";
	}
	
	/**
	 * 科目編集画面を表示
	 * @param  id 表示する科目ID
	 * @param  model Model
	 * @return  科目編集画面
	 */
	@GetMapping("/subject/{id}/edit")
	public String userEdit(@PathVariable  Integer id, Model model) {
		SubjectEntity subject = subjectService.findById(id);
		SubjectForm subjectUpdateRequest = new SubjectForm();
		subjectUpdateRequest.setId(subject.getId());
		subjectUpdateRequest.setSubject(subject.getSubject());
		subjectUpdateRequest.setNote(subject.getNote());
		model.addAttribute("subjectUpdateRequest", subjectUpdateRequest);
		return "subject/edit";
	}
	
	/**
	 * 科目更新
	 * @param  userRequest リクエストデータ
	 * @param  model Model
	 * @return  科目情報詳細画面
	 */
	@PostMapping("/subject/update")
	public String subjectUpdate(@Validated  @ModelAttribute  SubjectForm subjectUpdateRequest, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "subject/edit";
		}
		// 科目情報の更新
		subjectService.update(subjectUpdateRequest);
		return String.format("redirect:/subject/%d", subjectUpdateRequest.getId());
	}
	
	 /**
	 * 科目情報削除
	 * @param  id 表示するID
	 * @param  model Model
	 * @return  科目情報詳細画面
	 */
	@GetMapping("/subject/{id}/delete")
	public String subjectDelete(@PathVariable Integer id, Model model) {
		// 科目情報の削除
		subjectService.delete(id);
		return "redirect:/subject/list";
	}
}