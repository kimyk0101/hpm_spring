package himedia.hpm_spring.repository.vo;

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
public class PhoneAppVo {

	private Integer id;			// 연락처 고유번호
	private String name;		// 이름
	private String phone_number;	// 전화번호
	private String email;		// 이메일
	private String nickname;	// 닉네임
	private String memo;		// 메모
	
	public PhoneAppVo(String name) {
		super();
		this.name = name;
	}
	
	public PhoneAppVo(String name, String phone_number, String email, String nickname, String memo) {
		super();
		this.name = name;
		this.phone_number = phone_number;
		this.email = email;
		this.nickname = nickname;
		this.memo = memo;
	}
	 
}