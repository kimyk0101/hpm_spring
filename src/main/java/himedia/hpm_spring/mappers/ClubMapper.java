package himedia.hpm_spring.mappers;

import himedia.hpm_spring.repository.vo.ClubVo;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ClubMapper {

    @Select("SELECT * FROM clubs")
    List<ClubVo> findAllClubs();

    @Select("SELECT * FROM clubs WHERE id = #{id}")
    ClubVo findClubById(Long id);

    @Select("SELECT * FROM clubs WHERE users_id = #{usersId}")
    List<ClubVo> findClubsByUsersId(Long usersId);

    @Insert("INSERT INTO clubs (name, url, content, update_date, users_id, views, title) " +
            "VALUES (#{name}, #{url}, #{content}, #{updateDate}, #{usersId}, #{views}, #{title})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertClub(ClubVo club);

    @Update("UPDATE clubs SET name = #{name}, url = #{url}, content = #{content}, update_date = #{updateDate}, title = #{title} " +
            "WHERE id = #{id}")
    void updateClub(ClubVo club);

    @Delete("DELETE FROM clubs WHERE id = #{id} AND users_id = #{usersId}")
    void deleteClub(@Param("id") Long id, @Param("usersId") Long usersId);

    @Update("UPDATE clubs SET views = views + 1 WHERE id = #{id}")
    void incrementViews(Long id);
}