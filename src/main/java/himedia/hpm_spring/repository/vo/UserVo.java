package himedia.hpm_spring.repository.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	
	@JsonProperty("user_id")
	private String userId;			//	아이디
	private String password;		//	비밀번호
	private Date birth;				//	생년월일
	
	@JsonProperty("phone_number")
	private String phoneNumber;		// 	전화번호
	private String email;			// 	이메일
	private String address;			// 	주소
	
	@JsonProperty("update_date")
	private Date updateDate;		//	갱신 시간
}
