package himedia.hpm_spring.mappers;

import java.util.List;

import himedia.hpm_spring.repository.vo.UserVo;


public interface UserMapper {

//	<select id="selectAllUsers" resultType="hpmUser">	// 전체 유저 조회
	List<UserVo> selectAllUsers();
	
//	<select id="selectById" parameterType="Long" resultType="hpmUser">	// 특정 유저 조회
	UserVo selectById(Long id);
	
//	<insert id="registerUser" parameterType="hpmUser">	// 회원가입
	int registerUser(UserVo user);
	
//	<select id="loginUser" resultType="hpmUser">			// 로그인 
	UserVo loginUser(String user_id, String password);
	
//	<update id="updateUser" parameterType="UserVo">		//	유저 정보 수정
	int updateUser(UserVo user);
	
//	<delete id="deleteUser" parameterType="Long">		//	유저 삭제
	int deleteUser(Long id);
}