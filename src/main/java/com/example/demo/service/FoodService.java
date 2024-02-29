package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.FoodEntity;
import com.example.demo.form.FoodForm;
import com.example.demo.repository.FoodRepository;

/**
 * 科目情報 Service
 */
@Service
public class FoodService {
	/**
	 * 科目情報 Repository
	 */
	@Autowired
	private FoodRepository foodRepository;

	/**
	 * 科目情報 全検索
	 * @return  検索結果
	 */
	public List<FoodEntity> searchAll() {
		return foodRepository.findAll();
	}
	
	/**
	 * 科目情報 新規登録
	 * @param  food 科目情報
	 */
	public void create(FoodForm foodRequest) {
		FoodEntity food = new FoodEntity();
		food.setFood(foodRequest.getFood());
		food.setNote(foodRequest.getNote());
		foodRepository.save(food);
	}
	
	/**
	 * 科目情報 主キー検索
	 * @return  検索結果
	 */
	public FoodEntity findById(Integer id) {
		return foodRepository.getOne(id);
	}
	
	/**
	 * 科目情報 更新
	 * @param  food 科目情報
	 */
	public void update(FoodForm foodUpdateRequest) {
		FoodEntity food = findById(foodUpdateRequest.getId());
		food.setFood(foodUpdateRequest.getFood());
		food.setNote(foodUpdateRequest.getNote());
		foodRepository.save(food);
	}
	
	/**
	 * 科目情報 物理削除
	 * @param  id ID
	 */
	public void delete(Integer id) {
		FoodEntity food = findById(id);
		foodRepository.delete(food);
	}
}