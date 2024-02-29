package com.example.demo.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 科目情報 リクエストデータ
 */
@Data
public class SubjectForm {
  /**
   * ID
   */
  private Integer id;
  
  /**
   * 科目
   */
  @NotEmpty(message = "品目を入力してください")
  @Size(max = 50, message = "品目は50文字以内で入力してください")
  private String subject;
  
  @NotEmpty(message = "備考を入力してください")
  @Size(max = 50, message = "備考は50文字以内で入力してください")
  private String note;

}