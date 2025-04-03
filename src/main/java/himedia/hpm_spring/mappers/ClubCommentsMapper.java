package himedia.hpm_spring.mappers;

import himedia.hpm_spring.repository.vo.ClubCommentsVo;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ClubCommentsMapper {

    @Select("SELECT cc.id, cc.content, cc.update_date AS updateDate, cc.clubs_id AS clubsId, cc.users_id AS usersId, u.nickname " +
            "FROM club_comments cc " +
            "JOIN users u ON cc.users_id = u.id " +
            "WHERE cc.clubs_id = #{clubsId}")
    List<ClubCommentsVo> findCommentsByClubsId(Long clubsId);

    @Insert("INSERT INTO club_comments (content, update_date, clubs_id, users_id) " +
            "VALUES (#{content}, #{updateDate}, #{clubsId}, #{usersId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertComment(ClubCommentsVo comment);

    @Update("UPDATE club_comments SET content = #{content}, update_date = #{updateDate} WHERE id = #{id}")
    void updateComment(ClubCommentsVo comment);

    @Delete("DELETE FROM club_comments WHERE id = #{id}")
    void deleteComment(Long id);
}