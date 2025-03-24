package himedia.hpm_spring.repository.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GroupVo {

	private Long id;				// 	그룹 게시글 고유번호
	private String name;			//	그룹 이름
	private String category_code;	//	카테고리 코드
	private String title;			//	게시글 제목
	private String content;			// 	게시글 내용
	private String upload_date;		//	게시글 게시 시간
	private String users_id;			//	게시글 작성자 아이디
}
