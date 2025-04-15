package himedia.hpm_spring.crawler;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class NaverAdressCrawler {

    private static final String DRIVER_PATH = "C:\\chromedriver-win64\\chromedriver.exe"; // 본인 환경에 맞게 수정

    public static void main(String[] args) {
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

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("MountainAddresses");
        XSSFRow headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("산 이름");
        headerRow.createCell(1).setCellValue("주소");

        for (int i = 0; i < mountains.length; i++) {
            String mountain = mountains[i];
            String address = getMountainAddress(mountain);
            XSSFRow dataRow = sheet.createRow(i + 1);
            dataRow.createCell(0).setCellValue(mountain);
            dataRow.createCell(1).setCellValue(address != null ? address : "주소를 찾을 수 없습니다.");
        }

        try (FileOutputStream outputStream = new FileOutputStream("mountain_addresses.xlsx")) {
            workbook.write(outputStream);
            System.out.println("데이터가 mountain_addresses.xlsx 파일에 저장되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getMountainAddress(String mountain) {
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        String address = null;

        try {
            String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_sly.hst&fbm=0&acr=1&ie=utf8&query=" + mountain;
            driver.get(url);
            Thread.sleep(2000); // 페이지 로딩 대기

            // 주소 요소 찾기
            WebElement addressElement = driver.findElement(By.className("PkgBl"));
            address = addressElement.getText();

            // 필요한 경우 주소에서 불필요한 부분 제거
            address = address.replaceAll("상세정보", "").trim();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

        return address;
    }
}