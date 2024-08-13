package com.cod.AniBirth.adopt.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CreateReviewForm {
    @NotBlank(message="제목은 필수항목입니다.")
    @Size(max=15, message = "제목을 15자 이하로 입력해주세요.")
    private String title;

    @NotBlank(message="내용은 필수항목입니다.")
    @Size(max=200000, message = "내용을 200000자 이하로 입력해주세요.")
    private String content;

    private MultipartFile images;

}
