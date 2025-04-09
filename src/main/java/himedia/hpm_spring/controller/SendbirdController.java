package himedia.hpm_spring.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/sendbird")
public class SendbirdController {

    private static final String API_TOKEN = "YOUR_API_TOKEN";
    private static final String APP_ID = "YOUR_APP_ID";

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody Map<String, String> user) {
        String userId = user.get("userId");
        String nickname = user.get("nickname");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Api-Token", API_TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = new HashMap<>();
        body.put("user_id", userId);
        body.put("nickname", nickname);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        String url = "https://api-" + APP_ID + ".sendbird.com/v3/users";

        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForEntity(url, request, String.class);
            return ResponseEntity.ok("User created");
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == 400) {
                return ResponseEntity.ok("User may already exist (ignored)");
            }
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
        }
    }
}
