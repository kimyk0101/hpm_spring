package himedia.hpm_spring.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import himedia.hpm_spring.repository.vo.UserLoginData;
import himedia.hpm_spring.repository.vo.UserVo;
import himedia.hpm_spring.service.UserService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
    private UserService userService;

    // GET : /api/users -> 모든 유저 조회
    @GetMapping
    public ResponseEntity<List<UserVo>> retrieveAllUsers() {
        List<UserVo> users = userService.retrieveAllUsers();
        return ResponseEntity.ok(users);
    }

    // POST : /api/users/login -> 로그인 (인증)
    @PostMapping("/login")
    public ResponseEntity<UserVo> authenticateUser(@RequestBody UserLoginData loginData, HttpSession session) {
        if (session != null && session.getAttribute("loginUser") != null) {
            UserVo loginUser = (UserVo) session.getAttribute("loginUser");
            return ResponseEntity.ok(loginUser);
        }

        if (loginData.getUserId().isEmpty() || loginData.getPassword().isEmpty()) {
            System.err.println("no user_id or password");
            return ResponseEntity.ofNullable(null);
        }

        UserVo loginUser = userService.authenticateUser(loginData);

        if (loginUser != null) {
            loginUser.setPassword(""); // 비밀번호 숨김 처리
            session.setAttribute("loginUser", loginUser);
            return ResponseEntity.ok(loginUser);
        } else {
            return ResponseEntity.ofNullable(null);
        }
    }

    // POST : /api/users/logout -> 로그아웃 (세션 무효화)
    @PostMapping("/logout")
    public void invalidateSession(HttpSession session) {
        session.removeAttribute("loginUser");
        session.invalidate();
    }

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    // POST : /api/users -> 새로운 유저 생성
    @PostMapping
    public ResponseEntity<UserVo> registerUser(@RequestBody UserVo user) {
        UserVo savedUser = userService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }
    	
    // PATCH : /api/users/{id} -> 기존 유저 정보 수정
    @PatchMapping("/{id}")
    public ResponseEntity<UserVo> updateUserFields(@RequestBody Map<String, Object> updates, @PathVariable Long id) {
    	// 업데이트 날짜를 DB에 저장하기 위해 백엔드에서 직접 추가 
    	updates.put("update_date", java.sql.Date.valueOf(LocalDate.now()));
    	UserVo updatedUser = userService.updateUserFields(id, updates);
        return ResponseEntity.ok(updatedUser);
    }

    // DELETE : /api/users/{id} -> 기존 유저 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    	userService.deleteUser(id);
        return ResponseEntity.ok().<Void>build();
    }

    // GET : /api/users/session -> 로그인 상태 확인
    @GetMapping("/session")
    public ResponseEntity<UserVo> retrieveSessionUser(HttpSession session) {
        UserVo loginUser = (UserVo) session.getAttribute("loginUser");

        if (loginUser != null) {
            return ResponseEntity.ok(loginUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
    
    // GET : /api/users/check-user-id/?userId=입력된 값 -> 아이디 중복 체크
    @GetMapping("/check-user-id")
    public ResponseEntity<Boolean> checkUserId(@RequestParam("userId") String userId) {
        // 클라이언트에서 보낸 userId를 처리
        boolean isTaken = userService.checkUserIdInDatabase(userId);
        return ResponseEntity.ok(isTaken);
    }
    
    // GET : /api/users/check-nickname/?nickname=입력된 값 -> 닉네임 중복 체크
    @GetMapping("/check-nickname")
    public ResponseEntity<Boolean> checkNickname(@RequestParam("nickname") String nickname) {
        // 클라이언트에서 보낸 userId를 처리
        boolean isTaken = userService.checkUserIdInDatabase(nickname);
        return ResponseEntity.ok(isTaken);
    }


}
