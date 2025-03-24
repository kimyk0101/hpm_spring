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
public class UserLoginData {

	private String user_id;			//	아이디
	private String password;		//	비밀번호
}
