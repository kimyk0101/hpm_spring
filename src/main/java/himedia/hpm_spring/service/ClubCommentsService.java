package himedia.hpm_spring.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import himedia.hpm_spring.mappers.ClubCommentsMapper;
import himedia.hpm_spring.repository.vo.ClubCommentsVo;

@Service
public class ClubCommentsService {

    @Autowired
    private ClubCommentsMapper clubCommentsMapper;

    public List<ClubCommentsVo> findCommentsByClubsId(Long clubsId) {
        return clubCommentsMapper.findCommentsByClubsId(clubsId);
    }

    public ClubCommentsVo createComment(ClubCommentsVo comment) {
        comment.setUpdateDate(LocalDateTime.now(ZoneId.of("Asia/Seoul")));
        clubCommentsMapper.insertComment(comment);
        return comment;
    }

    public ClubCommentsVo updateComment(ClubCommentsVo comment) {
        comment.setUpdateDate(LocalDateTime.now(ZoneId.of("Asia/Seoul")));
        clubCommentsMapper.updateComment(comment);
        return comment;
    }

    public void deleteComment(Long id) {
        clubCommentsMapper.deleteComment(id);
    }
}