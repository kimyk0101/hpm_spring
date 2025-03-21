package himedia.hpm_spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import himedia.hpm_spring.repository.vo.PhoneAppVo;
import himedia.hpm_spring.service.PhoneAppService;

@RestController
@RequestMapping("/api/phoneApp")
public class HPM_SpirngController {

	@Autowired
	private PhoneAppService phoneAppService;

	// GET: /api/phoneApp - 전체 연락처 조회
	@GetMapping
	public ResponseEntity<List<PhoneAppVo>> getAllNumbers() {
		List<PhoneAppVo> phoneVo = phoneAppService.selectAllNumbers();
		return ResponseEntity.ok(phoneVo);
	}

	// GET: /api/phoneApp/{id} - 특정 연락처 조회
	@GetMapping("/{id}")
	public ResponseEntity<PhoneAppVo> getNumberById(@PathVariable("id") Integer id) {
		PhoneAppVo phoneVo = phoneAppService.selectNumberById(id);
		return ResponseEntity.ok(phoneVo);
	}

	// POST: /api/phoneApp/insert - 연락처 추가
	@PostMapping
	public ResponseEntity<PhoneAppVo> addNumber(@RequestBody PhoneAppVo phoneVo) {
		PhoneAppVo addedPhoneVo = phoneAppService.insertNumber(phoneVo);
		return ResponseEntity.ok(addedPhoneVo);
	}

	// PATCH : /api/phoneApp/modify/{id} -> 기존 연락처 수정
	@PatchMapping("/modify/{id}")
	public ResponseEntity<PhoneAppVo> updateNumberPartially(@PathVariable("id") Integer id,
			@RequestBody PhoneAppVo phoneVo) {

		// nickname과 memo가 null이면 빈 문자열로 변경
		if (phoneVo.getNickname() == null) {
			phoneVo.setNickname("");
		}
		if (phoneVo.getMemo() == null) {
			phoneVo.setMemo("");
		}

		PhoneAppVo updatedPhoneVo = phoneAppService.updateNumberPartially(id, phoneVo);
		return ResponseEntity.ok(updatedPhoneVo);
	}

	// PUT: /api/phoneApp/update/{id} - 연락처 수정
	@PutMapping("/update/{id}")
	public ResponseEntity<PhoneAppVo> updateNumber(@RequestBody PhoneAppVo phoneVo, @PathVariable("id") Integer id) {
		phoneVo.setId(id);
		PhoneAppVo updatedPhoneVo = phoneAppService.updateNumber(phoneVo);
		return ResponseEntity.ok(updatedPhoneVo);
	}

	// DELETE: /api/phoneApp/delete/{id} - 연락처 삭제
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteNumber(@PathVariable("id") Integer id) {
		phoneAppService.deleteNumber(id);
		return ResponseEntity.noContent().build(); // 삭제 후 아무 것도 반환하지 않음
	}

	// GET: /api/phoneApp/search_name/{name} - 연락처 이름 검색하기
	@GetMapping("/search_name/{name}")
	public ResponseEntity<List<PhoneAppVo>> selectByName(@PathVariable String name) {
		List<PhoneAppVo> phoneVo = phoneAppService.selectByName(name);
		return ResponseEntity.ok(phoneVo);
	}

	// GET: /api/phoneApp/search_phone_number/{phone_number} - 연락처 전화번호 검색하기
	@GetMapping("/search_phone_number/{phone_number}")
	public ResponseEntity<List<PhoneAppVo>> selectByPhonenumber(@PathVariable String phone_number) {
		List<PhoneAppVo> phoneVo = phoneAppService.selectByPhonenumber(phone_number);
		return ResponseEntity.ok(phoneVo);
	}
}
