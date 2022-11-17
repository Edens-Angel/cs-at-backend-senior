package com.example.utilities;

import com.example.entities.Feed;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is the webscraper to scrape the feeds from the Nos website.
 * */
@Component
public class FeedScraper {

    private List<Feed> scrapedFeeds = new ArrayList<>();

    public List<Feed> scrape() throws ParseException {

        WebDriver driver = setupScraper();

        this.findArticles(driver);

        driver.quit();

        return scrapedFeeds;
    }

    private WebDriver setupScraper() {
        final String URL = "https://feeds.nos.nl/nosnieuwsalgemeen";

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        WebDriver driver = new ChromeDriver(options);

        driver.get(URL);

        return driver;
    }

    private void findArticles(WebDriver driver) throws ParseException {
        List<WebElement> articles = driver.findElements(By.tagName("article"));

        for (WebElement article : articles) {
            WebElement header = article.findElement(By.tagName("header"));
            String headerText = header.findElement(By.tagName("h1")).getText();
            String publishDate = header.findElement(By.tagName("time")).getText();

            String description = article.findElements(By.tagName("p")).get(0).getText();
            String image = article.findElement(By.tagName("img")).getAttribute("src");

            Date parsedDate = new SimpleDateFormat("dd MM yyyy hh:mm:ss").parse(DateUtil.transformDate(publishDate));

            Feed feed = new Feed(headerText, description, parsedDate, image);

            scrapedFeeds.add(feed);
        }
    }
}
