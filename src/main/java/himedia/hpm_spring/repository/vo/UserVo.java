package himedia.hpm_spring.repository.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserVo {

	private Long id;				// 	유저 고유번호
	private String name;			// 	이름
	private String nickname;		// 	닉네임
	private String userId;			//	아이디
	private String password;		//	비밀번호
	private Date birth;				//	생년월일
	private String phone_number;	// 	전화번호
	private String email;			// 	이메일
	private String address;			// 	주소
	private Date update_date;		//	갱신 시간
//	private Boolean is_deleted;		// 	유저정보(탈퇴) 삭제 여부 (보안상)
//	private String register_date;	//	가입 날짜

}
