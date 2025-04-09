package himedia.hpm_spring.repository.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class MountainReviewLikeVo {
	private int id;

	@JsonProperty("users_id")
	private int usersId;
	
	@JsonProperty("reviews_id")
	private int reviewsId;
	
	@JsonProperty("is_like")
	private boolean isLike;
}
