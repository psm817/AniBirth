package com.cod.AniBirth.adopt.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class AdoptionnoticeForm {
    @NotBlank(message="이름은 필수항목입니다.")
    @Size(max=5, message = "이름을 5자 이하로 입력해주세요.")
    private String name;

    @NotBlank(message = "입양상태를 입력해주세요")
    private String adoptionStatusCd;

    @NotBlank(message="나이는 필수항목입니다.")
    private String age;

    @NotBlank(message = "유기동물구분은 필수항목입니다.")
    private String classification; //1:개 , 2:고양이,3:기타동물

    @NotBlank(message = "성별구분은 필수 입니다")
    private String gender;//1:암, 2:수

    @NotBlank(message = "털색을 입력해주세요")
    private String hairColor;

    @NotBlank(message = "특이사항을 입력해주세요")
    private String memo;

    @NotBlank(message = "동물등록번호를 입력해주세요")
    private String regId;

    @NotBlank(message = "구조날짜를 입력해주세요")
    private String rescueDate;


    @NotBlank(message = "몸무게를 입력해주세요")
    private String weight;

    @NotBlank(message = "강아지 견종, 고양이 종류, 동물 종류를 입력해주세요")
    private String species;

    @NotNull(message="사진을 선택해주세요")
    private MultipartFile thumbnail;

}
