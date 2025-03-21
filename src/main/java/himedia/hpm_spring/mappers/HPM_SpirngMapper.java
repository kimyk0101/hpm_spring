package himedia.hpm_spring.mappers;

import java.util.List;

import himedia.hpm_spring.repository.vo.PhoneAppVo;


public interface HPM_SpirngMapper {

//	<select id="selectAllNumbers" resultType="PhoneAppVo">
	List<PhoneAppVo> selectAllNumbers();			// 전체 연락처 조회
	
//	<select id="selectNumberById" parameterType="Integer" resultType="PhoneAppVo">
	PhoneAppVo selectNumberById(Integer id);		// 특정 연락처 조회
	
//	<insert id="insertNumber" parameterType="PhoneAppVo">
	int insertNumber(PhoneAppVo phoneVo);			//	연락처 추가

//	<update id="updateNumberPartially" parameterType="PhoneAppVo">
	int updateNumberPartially(PhoneAppVo phoneVo);	//	기존 연락처 수정
	
//	<update id="updateNumber" parameterType="PhoneAppVo">
	int updateNumber(PhoneAppVo phoneVo);			//	연락처 수정
	
//	<delete id="deleteNumber" parameterType="int">
	int deleteNumber(Integer id);					//	연락처 삭제

//	<select id="selectByName" parameterType="PhoneAppVo" resultType="PhoneAppVo">
	List<PhoneAppVo> selectByName(String name);		//	연락처 이름 검색
	
//	<select id="selectByPhonenumber" parameterType="PhoneAppVo" resultType="PhoneAppVo">
	List<PhoneAppVo> selectByPhonenumber(String phone_number);	//	연락처 전화번호 검색
}