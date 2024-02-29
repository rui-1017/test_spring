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

import com.example.demo.entity.FoodEntity;
import com.example.demo.form.FoodForm;
import com.example.demo.service.FoodService;

/**
 * 科目情報 Controller
 */
@Controller
public class FoodController {

	/**
	 * 給餌情報 Service
	 */
	@Autowired
	FoodService foodService;

	/**
	 * 給餌情報一覧画面を表示
	 * @param  model Model
	 * @return  給餌情報一覧画面のHTML
	 */
	@GetMapping("/food/list")
	public String foodList(Model model) {
		List<FoodEntity> foodlist = foodService.searchAll();
		model.addAttribute("foodlist", foodlist);
		return "food/list";
	}
	
	/**
	 * 科目新規登録画面を表示
	 * @param  model Model
	 * @return  科目情報一覧画面
	 */
	@GetMapping("/food/add")
	public String foodRegister(Model model) {
		model.addAttribute("foodRequest", new FoodForm());
		return "food/add";
	}
	
	/**
	 * 科目新規登録
	 * @param  userRequest リクエストデータ
	 * @param  model Model
	 * @return  科目情報一覧画面
	 */
	@PostMapping("/food/create")
	public String foodCreate(@Validated  @ModelAttribute  FoodForm foodRequest, BindingResult result, Model model) {
		if (result.hasErrors()) {
			// 入力チェックエラーの場合
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("foodRequest", new FoodForm());
			model.addAttribute("validationError", errorList);
			return "food/add";
		}
		// 科目情報の登録
		foodService.create(foodRequest);
		return "redirect:/food/list";
	}
	
	/**
	 * 科目情報詳細画面を表示
	 * @param  id 表示する科目ID
	 * @param  model Model
	 * @return  科目情報詳細画面
	 */
	@GetMapping("/food/{id}")
	public String userDetail(@PathVariable  Integer id, Model model) {
		FoodEntity food = foodService.findById(id);
		model.addAttribute("food", food);
		return "food/detail";
	}
	
	/**
	 * 科目編集画面を表示
	 * @param  id 表示する科目ID
	 * @param  model Model
	 * @return  科目編集画面
	 */
	@GetMapping("/food/{id}/edit")
	public String userEdit(@PathVariable  Integer id, Model model) {
		FoodEntity food = foodService.findById(id);
		FoodForm foodUpdateRequest = new FoodForm();
		foodUpdateRequest.setId(food.getId());
		foodUpdateRequest.setFood(food.getFood());
		foodUpdateRequest.setNote(food.getNote());
		model.addAttribute("foodUpdateRequest", foodUpdateRequest);
		return "food/edit";
	}
	
	/**
	 * 科目更新
	 * @param  userRequest リクエストデータ
	 * @param  model Model
	 * @return  科目情報詳細画面
	 */
	@PostMapping("/food/update")
	public String foodUpdate(@Validated  @ModelAttribute  FoodForm foodUpdateRequest, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "food/edit";
		}
		// 科目情報の更新
		foodService.update(foodUpdateRequest);
		return String.format("redirect:/food/%d", foodUpdateRequest.getId());
	}
	
	 /**
	 * 科目情報削除
	 * @param  id 表示するID
	 * @param  model Model
	 * @return  科目情報詳細画面
	 */
	@GetMapping("/food/{id}/delete")
	public String foodDelete(@PathVariable Integer id, Model model) {
		// 科目情報の削除
		foodService.delete(id);
		return "redirect:/food/list";
	}
}