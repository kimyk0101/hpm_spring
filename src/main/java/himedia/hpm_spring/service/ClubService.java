package himedia.hpm_spring.service;

import himedia.hpm_spring.mappers.ClubMapper;
import himedia.hpm_spring.repository.vo.ClubVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClubService {

    @Autowired
    private ClubMapper clubMapper;

    public List<ClubVo> findAllClubs() {
        return clubMapper.findAllClubs();
    }

    public ClubVo findClubById(Long id) {
        return clubMapper.findClubById(id);
    }

    public List<ClubVo> findClubsByUsersId(Long usersId) {
        return clubMapper.findClubsByUsersId(usersId);
    }

    public ClubVo createClub(ClubVo club) {
        club.setUpdateDate(LocalDateTime.now());
        clubMapper.insertClub(club);
        return club;
    }

    public ClubVo updateClub(ClubVo club) {
        club.setUpdateDate(LocalDateTime.now());
        clubMapper.updateClub(club);
        return club;
    }

    public void deleteClub(Long id, Long usersId) {
    	clubMapper.deleteClub(id, usersId);
    }

    public void incrementViews(Long id) {
    	clubMapper.incrementViews(id);
    }
}