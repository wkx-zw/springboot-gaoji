package com.cs.springbootvue;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName: ppp
 * Package: com.cs.springbootvue
 * Description:
 *
 * @Author zhuwen
 * @Create 2023/8/7 19:51
 * @Version 1.0
 */
public class ppp {
    public static void main(String[] args) {
        String url = "https://www.zg-qccd.com/cn/historical-data";  // 替换为你要访问的网址

        try {
            // 获取网站内容
            String websiteContent = getWebsiteContent(url);

            // 提取电话号码 [\u4e00-\u9fa5]+
            //Pattern pattern = Pattern.compile("\\+\\d{1,3} \\(\\d{3}\\) \\d{3}-\\d{4}");
            Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]+");
            Matcher matcher = pattern.matcher(websiteContent);

            // 输出匹配到的电话号码
            while (matcher.find()) {
                System.out.println(matcher.group());
            }
        } catch (IOException e) {
            System.out.println("无法获取网站内容: " + e.getMessage());
        }
    }

    public static String getWebsiteContent(String url) throws IOException {
        StringBuilder content = new StringBuilder();

        try (Scanner scanner = new Scanner(new URL(url).openStream(), "UTF-8")) {
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }
        }

        return content.toString();
    }
}

/**
 *  public static void main(String[] args) {
 *         String url = "https://www.example.com";  // 替换为你要访问的网址
 *
 *         try {
 *             // 解析HTML并获取网页内容
 *             Document doc = Jsoup.connect(url).get();
 *
 *             // 提取电话号码
 *             Elements phoneNumbers = doc.getElementsMatchingOwnText("\\+\\d{1,3} \\(\\d{3}\\) \\d{3}-\\d{4}");
 *
 *             // 输出匹配到的电话号码
 *             for (int i = 0; i < phoneNumbers.size(); i++) {
 *                 System.out.println(phoneNumbers.get(i).text());
 *             }
 *         } catch (IOException e) {
 *             System.out.println("无法获取网站内容: " + e.getMessage());
 *         }
 *     }
 *
 *
 */
