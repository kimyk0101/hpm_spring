package himedia.hpm_spring.crawler;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MountainCourseCrawler {

    public static void main(String[] args) throws IOException {
        String[] mountains = {
                "가리산", "가리왕산", "가야산", "가지산", "감악산", "강천산", "계룡산", "계방산", "공작산", "관악산",
                "구병산", "금산", "금수산", "금오산", "금정산", "깃대봉", "남산", "내연산", "내장산", "대둔산",
                "대암산", "대야산", "덕숭산(수덕산)", "덕유산(향적봉)", "덕항산", "도락산", "도봉산(자운봉)", "두륜산", "두타산", "마니산",
                "마이산", "명성산", "명지산", "모악산", "무등산", "무학산", "미륵산", "민주지산", "방장산", "방태산",
                "백덕산", "백암산", "백운산(광양)", "백운산(정선)", "백운산(포천)", "변산", "북한산", "비슬산", "삼악산", "서대산",
                "선운산", "설악산", "성인봉", "소백산", "소요산", "속리산", "신불산", "연화산", "오대산", "오봉산",
                "용문산", "용화산", "운문산", "운악산", "운장산", "월악산", "월출산", "유명산", "응봉산", "장안산",
                "재약산", "적상산", "점봉산", "조계산", "주왕산", "주흘산", "지리산", "지리산(통영)", "천관산", "천마산",
                "천성산", "천태산", "청량산", "추월산", "축령산", "치악산", "칠갑산", "태백산", "태화산", "팔공산",
                "팔봉산", "팔영산", "한라산", "화악산(중봉)", "화왕산", "황매산", "황석산", "황악산", "황장산", "희양산"
        };

        WebDriverManager.chromedriver().setup();

        List<String[]> allData = new ArrayList<>();
        int mountainIndex = 1; // ✅ 산 순서 고정용 인덱스

        for (String mountain : mountains) {
            System.out.println("📍 크롤링 중: " + mountain);

            List<String[]> mountainData = crawlMountainTrail(mountain, mountainIndex);

            if (!mountainData.isEmpty()) {
                // -----산이름----- 형태로 구분자 삽입
                allData.add(new String[]{"-----" + mountain + "-----"});
                allData.addAll(mountainData);
            } else {
                System.out.println("⚠️ 코스 없음: " + mountain);
            }

            mountainIndex++; // ✅ 코스 유무와 관계없이 인덱스 증가
        }

        // 결과 CSV 저장
        String savePath = "C:\\문호정\\최종 프로젝트\\코스정보\\mountain_courses.csv";
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(savePath, StandardCharsets.UTF_8), CSVFormat.DEFAULT)) {
            for (String[] row : allData) {
                printer.printRecord((Object[]) row);
            }
        }

        System.out.println("✅ 전체 CSV 저장 완료: " + savePath);
    }

    public static List<String[]> crawlMountainTrail(String mountain, int mountainIndex) {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        List<String[]> data = new ArrayList<>();

        try {
            String query = URLEncoder.encode(mountain + " 등산코스", StandardCharsets.UTF_8);
            String url = "https://map.naver.com/p/search/" + query;
            driver.get(url);

            Thread.sleep(5000);
            driver.switchTo().frame("searchIframe");

            List<WebElement> items = driver.findElements(By.className("CKku0"));

            for (WebElement item : items) {
                String name = getTextSafe(item, By.className("jtHh7"));
                String diff = getTextSafe(item, By.className("yLF4O"));

                String time = "", length = "";
                List<WebElement> spans = item.findElements(By.cssSelector(".LuxFa"));
                for (WebElement span : spans) {
                    try {
                        String label = span.getText();
                        if (label.contains("소요 시간")) {
                            time = span.findElement(By.tagName("em")).getText();
                        } else if (label.contains("길이")) {
                            length = span.findElement(By.tagName("em")).getText();
                        }
                    } catch (Exception ignored) {}
                }

                data.add(new String[]{name, diff, time, length, String.valueOf(mountainIndex)});
            }

        } catch (Exception e) {
            System.out.println("❌ [" + mountain + "] 크롤링 실패: " + e.getMessage());
        } finally {
            driver.quit();
        }

        return data;
    }

    public static String getTextSafe(WebElement element, By by) {
        try {
            return element.findElement(by).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }
}
