package himedia.hpm_spring.service;

import himedia.hpm_spring.mappers.ClubsMapper;
import himedia.hpm_spring.repository.vo.ClubVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClubsService {

    @Autowired
    private ClubsMapper clubsMapper;

    public List<ClubVo> findAllClubs() {
        return clubsMapper.findAllClubs();
    }

    public ClubVo findClubById(Long id) {
        return clubsMapper.findClubById(id);
    }

    public List<ClubVo> findClubsByUsersId(Long usersId) {
        return clubsMapper.findClubsByUsersId(usersId);
    }

    public ClubVo createClub(ClubVo club) {
        club.setUpdateDate(LocalDateTime.now());
        clubsMapper.insertClub(club);
        return club;
    }

    public ClubVo updateClub(ClubVo club) {
        club.setUpdateDate(LocalDateTime.now());
        clubsMapper.updateClub(club);
        return club;
    }

    public void deleteClub(Long id, Long usersId) {
        clubsMapper.deleteClub(id, usersId);
    }

    public void incrementViews(Long id) {
        clubsMapper.incrementViews(id);
    }
}