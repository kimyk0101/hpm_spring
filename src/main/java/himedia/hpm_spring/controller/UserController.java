package himedia.hpm_spring.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import himedia.hpm_spring.repository.vo.UserLoginData;
import himedia.hpm_spring.repository.vo.UserVo;
import himedia.hpm_spring.service.UserService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/hpmuser")
public class UserController {

	@Autowired
	private UserService userService;

	// GET : /api/hpmuser
	@GetMapping
	public ResponseEntity<List<UserVo>> getAllUsers() {
		List<UserVo> items = userService.selectAllUsers();
		return ResponseEntity.ok(items);
	}

	// POST : /api/hpmuserlogin -> 로그인
	@PostMapping("/login")
	public ResponseEntity<UserVo> loginUser(@RequestBody UserLoginData loginData, HttpSession session) {
		// 세션 정보가 있다면
		if (session != null && session.getAttribute("loginUser") != null) {
			UserVo loginUser = (UserVo) session.getAttribute("loginUser");
			return ResponseEntity.ok(loginUser);
		}

		// 입력한 아이디나 비번이 없을 경우
		if (loginData.getUser_id().length() == 0 || loginData.getPassword().length() == 0) {
			System.err.println("no user_id or password");

			return ResponseEntity.ofNullable(null);
		}

		UserVo loginUser = userService.loginUser(loginData);

		// 로그인 성공 시
		if (loginUser != null) {
			// 비밀번호 정보 지움
			loginUser.setPassword("");

			// 로그인 시 세션정보 생성
			session.setAttribute("loginUser", loginUser);
			return ResponseEntity.ok(loginUser);
		} else {
			return ResponseEntity.ofNullable(null);
		}
	}

	// 로그아웃 시 세선 정보 소멸
	@PostMapping("/logout")
	public void logout(HttpSession session) {
		session.removeAttribute("loginUser");
		session.invalidate();
	}

	// POST : /api/hpmuser -> 새로운 유저 항목 생성
	@PostMapping
	public ResponseEntity<UserVo> createUser(@RequestBody UserVo user) {
		UserVo savedUser = userService.registerUser(user);

		return ResponseEntity.ok(savedUser);
	}

	// PATCH : /api/hpmuser/modify/{id} -> 기존 유저 항목 수정
	@PatchMapping("/modify/{id}")
	public ResponseEntity<UserVo> updateUser(@RequestBody Map<String, Object> updates, @PathVariable Long id) {
	    UserVo updatedUser = userService.updateUserFields(id, updates);
	    return ResponseEntity.ok(updatedUser);
	}

	// DELETE : /api/gogumauser/{id} -> 기존 유저 항목 삭제
	@DeleteMapping("/{id}")
	// Body에 실어 보낼 내용이 없음 -> Void
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.ok().<Void>build();
	}

	// Session : /api/session -> 로그인 상태 확인
	@GetMapping("/session")
	public ResponseEntity<UserVo> getSessionUser(HttpSession session) {
		UserVo loginUser = (UserVo) session.getAttribute("loginUser");

		if (loginUser != null) {
			return ResponseEntity.ok(loginUser);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}
}
