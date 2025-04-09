package himedia.hpm_spring.controller;

import himedia.hpm_spring.repository.vo.ClubVo;
import himedia.hpm_spring.service.ClubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/clubs")
public class ClubController {

    @Autowired
    private ClubService clubService;

    @GetMapping
    public ResponseEntity<List<ClubVo>> retrieveAllClubs() {
        List<ClubVo> clubs = clubService.findAllClubs();
        return ResponseEntity.ok(clubs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubVo> retrieveClubById(@PathVariable Long id) {
        ClubVo club = clubService.findClubById(id);
        return ResponseEntity.ok(club);
    }

    @GetMapping("/my/{usersId}")
    public ResponseEntity<List<ClubVo>> retrieveClubsByUsersId(@PathVariable Long usersId) {
        List<ClubVo> clubs = clubService.findClubsByUsersId(usersId);
        return ResponseEntity.ok(clubs);
    }

    @PostMapping
    public ResponseEntity<ClubVo> createClub(@RequestBody ClubVo club) {
        if (club.getUsersId() == null || club.getUsersId() <= 0) {
            return ResponseEntity.badRequest().body(null);
        }
        ClubVo savedClub = clubService.createClub(club);
        return ResponseEntity.ok(savedClub);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClubVo> updateClub(@RequestBody ClubVo club, @PathVariable Long id) {
        club.setId(id);
        ClubVo updatedClub = clubService.updateClub(club);
        return ResponseEntity.ok(updatedClub);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable Long id, @RequestBody Map<String, Long> requestBody) {
        Long usersId = requestBody.get("usersId");
        clubService.deleteClub(id, usersId);
        return ResponseEntity.ok().<Void>build();
    }

    @PutMapping("/{id}/increment-views")
    public ResponseEntity<Void> incrementViews(@PathVariable("id") Long id) {
    	clubService.incrementViews(id);
        return ResponseEntity.ok().build();
    }
}