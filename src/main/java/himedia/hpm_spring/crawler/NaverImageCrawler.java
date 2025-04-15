package himedia.hpm_spring.crawler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class NaverImageCrawler {
    private static final String DRIVER_PATH = "C:\\chromedriver-win64\\chromedriver.exe"; // 본인 환경에 맞게 수정

    public static void main(String[] args) {
        // 검색할 산 리스트 설정 (100개의 산 이름 리스트)
        String[] mountains = {
        		"가리산",
        		"가리왕산",
        		"가야산",
        		"가지산",
        		"감악산",
        		"강천산",
        		"계룡산",
        		"계방산",
        		"공작산",
        		"관악산",
        		"구병산",
        		"금산",
        		"금수산",
        		"금오산",
        		"금정산",
        		"깃대봉",
        		"남산",
        		"내연산",
        		"내장산",
        		"대둔산",
        		"대암산",
        		"대야산",
        		"덕숭산(수덕산)",
        		"덕유산(향적봉)",
        		"덕항산",
        		"도락산",
        		"도봉산(자운봉)",
        		"두륜산",
        		"두타산",
        		"마니산",
        		"마이산",
        		"명성산",
        		"명지산",
        		"모악산",
        		"무등산",
        		"무학산",
        		"미륵산",
        		"민주지산",
        		"방장산",
        		"방태산",
        		"백덕산",
        		"백암산",
        		"백운산(광양)",
        		"백운산(정선)",
        		"백운산(포천)",
        		"변산",
        		"북한산",
        		"비슬산",
        		"삼악산",
        		"서대산",
        		"선운산",
        		"설악산",
        		"성인봉",
        		"소백산",
        		"소요산",
        		"속리산",
        		"신불산",
        		"연화산",
        		"오대산",
        		"오봉산",
        		"용문산",
        		"용화산",
        		"운문산",
        		"운악산",
        		"운장산",
        		"월악산",
        		"월출산",
        		"유명산",
        		"응봉산",
        		"장안산",
        		"재약산",
        		"적상산",
        		"점봉산",
        		"조계산",
        		"주왕산",
        		"주흘산",
        		"지리산",
        		"지리산(통영)",
        		"천관산",
        		"천마산",
        		"천성산",
        		"천태산",
        		"청량산",
        		"추월산",
        		"축령산",
        		"치악산",
        		"칠갑산",
        		"태백산",
        		"태화산",
        		"팔공산",
        		"팔봉산",
        		"팔영산",
        		"한라산",
        		"화악산(중봉)",
        		"화왕산",
        		"황매산",
        		"황석산",
        		"황악산",
        		"황장산",
        		"희양산"
            // 추가로 원하는 산 이름을 여기에 추가
        };

        // 각 산에 대해 이미지 다운로드
        int imageIndex = 1;
        for (String mountain : mountains) {
            List<String> imageUrls = getNaverImages(mountain, 5);

            // 이미지 다운로드
            for (String url : imageUrls) {
                try {
                    // 산 이름을 파일 이름에 추가하여 저장
                    downloadImage(url, "C:\\mountainImages\\" + mountain + "_image" + imageIndex++ + ".jpg");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static List<String> getNaverImages(String query, int count) {
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        List<String> imageUrls = new ArrayList<>();

        try {
            // 네이버 이미지 검색 페이지 열기
            String url = "https://search.naver.com/search.naver?where=image&query=" + query;
            driver.get(url);
            Thread.sleep(2000); // 페이지 로딩 대기

            // 이미지 요소 가져오기
            List<WebElement> images = driver.findElements(By.cssSelector("img._fe_image_tab_content_thumbnail_image"));
            
            for (int i = 0; i < Math.min(count, images.size()); i++) {
                String imgUrl = images.get(i).getAttribute("src");
                imageUrls.add(imgUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

        return imageUrls;
    }

    // 이미지 URL에서 이미지를 다운로드하여 지정된 경로에 저장하는 메서드
    public static void downloadImage(String imageUrl, String saveFileName) throws IOException {
        // 이미지 URL을 URL 객체로 변환
        URL url = new URL(imageUrl);

        // URL에서 이미지를 가져오기 위한 InputStream
        InputStream in = url.openStream();

        // 지정된 경로에 이미지 파일을 저장
        try (FileOutputStream out = new FileOutputStream(saveFileName)) {
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        } finally {
            in.close();
        }

        System.out.println("이미지 저장 완료: " + saveFileName);
    }
}
