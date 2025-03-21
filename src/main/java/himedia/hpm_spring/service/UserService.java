package himedia.hpm_spring.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import himedia.hpm_spring.exception.UserNotFoundException;
import himedia.hpm_spring.mappers.UserMapper;
import himedia.hpm_spring.repository.vo.UserLoginData;
import himedia.hpm_spring.repository.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	// 전체 유저 조회
	public List<UserVo> selectAllUsers() {
		return userMapper.selectAllUsers();
	}

	// 로그인
	public UserVo loginUser(UserLoginData loginData) {
		UserVo user = userMapper.loginUser(loginData.getUser_id(), loginData.getPassword());

		return user;
	}

	// 회원가입
	public UserVo registerUser(UserVo user) {
		userMapper.registerUser(user);

		Long id = user.getId();

		return userMapper.selectById(id);
	}

	// 유저 정보 수정
	public UserVo updateUserFields(Long id, Map<String, Object> updates) {
		// 1️. 데이터베이스에서 해당 id를 가진 유저를 찾음
		UserVo user = userMapper.selectById(id);

		// 유저가 없다면 예외 처리
		if (user == null) {
			throw new UserNotFoundException("User with id " + id + " not found");
		}

		// 2️. updates에 있는 값만 수정
		updates.forEach((key, value) -> {
			if (value == null) {
				// null 값이 들어올 경우, 필수 필드는 예외 처리
				if ("name".equals(key) || "nickname".equals(key) || "user_id".equals(key) || "password".equals(key)) {
					throw new IllegalArgumentException(key + " cannot be null");
				}
				// 선택적 필드들에 대해서는 null 값이 들어오면 빈 문자열("")로 처리
				value = "";
			}

			// 필드 값 수정
			switch (key) {
			case "name":
				user.setName((String) value);
				break;
			case "nickname":
				user.setNickname((String) value);
				break;
			case "user_id":
				user.setUser_id((String) value);
				break;
			case "password":
				user.setPassword((String) value);
				break;
			case "birth":
				user.setBirth((String) value);
				break;
			case "phone_number":
				user.setPhone_number((String) value);
				break;
			case "email":
				user.setEmail((String) value);
				break;
			case "address":
				user.setAddress((String) value);
				break;
			 default:
	                throw new IllegalArgumentException("Invalid field name: " + key);
			}
			
		});
		// 3️. 수정된 데이터를 저장
		userMapper.updateUser(user);

		// 4️. 업데이트된 유저 정보 반환 
		return user;
	}

	// 유저 삭제
	public int deleteUser(Long id) {
		return userMapper.deleteUser(id);
	}
}