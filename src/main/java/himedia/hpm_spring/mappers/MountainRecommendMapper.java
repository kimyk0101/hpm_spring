package himedia.hpm_spring.mappers;

import himedia.hpm_spring.repository.vo.MountainRecommendVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MountainRecommendMapper {

    @Select("SELECT mountains.id AS id, mountains.location AS location, mountain_courses.difficulty_level AS difficultyLevel, mountain_courses.course_time AS courseTime, mountains.name AS name, mountain_courses.course_name AS courseName, mountains.selection_reason AS selectionReason, mountains.transportation_info AS transportationInfo " +
            "FROM mountains " +
            "INNER JOIN mountain_courses ON mountains.id = mountain_courses.mountains_id")
    List<MountainRecommendVo> findAllMountainRecommends();
}