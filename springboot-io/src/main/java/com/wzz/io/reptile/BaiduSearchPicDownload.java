package com.wzz.io.reptile;

import com.alibaba.fastjson.JSON;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.tools.ant.util.StringUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class BaiduSearchPicDownload {


    ExecutorService executor = Executors.newFixedThreadPool(20);

    @Test
    public void main() throws InterruptedException {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        String searchName = "许嵩";


        Integer pn = 0;
        Integer rn = 60;
        StringBuilder sb = null;
        int count = 0;
        while (true) {
            count++;
            sb = new StringBuilder("https://image.baidu.com/search/acjson?tn=resultjson_com&ipn=rj&word=");
            sb.append(searchName);
            sb.append("&queryWord=");
            sb.append(searchName);
            sb.append("=utf-8&oe=utf-8&");
            sb.append("pn=" + pn + "&");
            sb.append("rn=" + rn + "");

            pn += 60;
//            System.out.println(sb.toString());

//        String url = "https://image.baidu.com/search/acjson?tn=resultjson_com&ipn=rj&word=" + searchName + "&queryWord="
//                + searchName + "=utf-8&oe=utf-8&pn=60&rn=60";
            HttpGet httpGet = new HttpGet(sb.toString());
            String cookie = "BDqhfp=%25B9%25ED%25B5%25B6%26%26NaN-1undefined%26%260%26%261; BIDUPSID=78D124680F504FD3F533BB2B9DA63003; PSTM=1629365365; __yjs_duid=1_f95d6c3eee033483e28f76c623fee7521629449380753; BAIDUID=DCA8FBA2A503C82879C05C283B8EC2D4:FG=1; indexPageSugList=%5B%22%E7%81%B0%E5%A4%AA%E7%8B%BC%E6%88%91%E7%88%B1%E5%B9%B3%E5%BA%95%E9%94%85%22%2C%22%E6%88%91%E7%88%B1%E5%B9%B3%E5%BA%95%E9%94%85%22%2C%22%E6%88%91%E7%88%B1%E7%81%B0%E5%A4%AA%E7%8B%BC%22%2C%22%E7%81%B0%E5%A4%AA%E7%8B%BC%22%2C%22%E8%B4%B9%E7%BF%94%22%2C%22%E6%9C%B1%E5%A9%A7%E6%B1%90%E6%80%A7%E6%84%9F%E5%86%99%E7%9C%9F%22%2C%22%E6%9C%B1%E5%A9%A7%E6%B1%90%20%E5%86%99%E7%9C%9F%22%2C%22gson%22%2C%22%E6%9C%B1%E5%A9%A7%E6%B1%90%22%5D; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; BAIDUID_BFESS=CE3C5969C8D04A2D5519C6CFABCC6AF3:FG=1; H_PS_PSSID=34429_35106_31253_34584_35871_35246_35908_35949_35984_26350; delPer=0; PSINO=6; BCLID=11459389157684172405; BDSFRCVID=lQAOJeC626mGswbDXUJZhbU2G77MyD7TH6aozX1fHw6XohApGCjXEG0PSx8g0KAb9Ib-ogKK3gOTH4PF_2uxOjjg8UtVJeC6EG0Ptf8g0f5; H_BDCLCKID_SF=tb4OoCKbJCK3h4-C2DTV2t0e5UIX5-RLfKJI5l7F5l8-hCQzQnbb-x0R3t6zaPjPtekf_h6X3I3xOKQphn6iDtKn5Njv2-vC-gvmBfnN3KJmVPP9bT3vLtj-yfKL2-biWb7M2MbdJUJP_IoG2Mn8M4bb3qOpBtQmJeTxoUJ25DnJhbLGe4bK-TrXDHDHtUK; BCLID_BFESS=11459389157684172405; BDSFRCVID_BFESS=lQAOJeC626mGswbDXUJZhbU2G77MyD7TH6aozX1fHw6XohApGCjXEG0PSx8g0KAb9Ib-ogKK3gOTH4PF_2uxOjjg8UtVJeC6EG0Ptf8g0f5; H_BDCLCKID_SF_BFESS=tb4OoCKbJCK3h4-C2DTV2t0e5UIX5-RLfKJI5l7F5l8-hCQzQnbb-x0R3t6zaPjPtekf_h6X3I3xOKQphn6iDtKn5Njv2-vC-gvmBfnN3KJmVPP9bT3vLtj-yfKL2-biWb7M2MbdJUJP_IoG2Mn8M4bb3qOpBtQmJeTxoUJ25DnJhbLGe4bK-TrXDHDHtUK; BDRCVFR[dG2JNJb_ajR]=mk3SLVN4HKm; userFrom=ala; BDRCVFR[-pGxjrCMryR]=mk3SLVN4HKm; BA_HECTOR=0l858k8h21ah010ko21h1urkq0q; BDRCVFR[X_XKQks0S63]=mk3SLVN4HKm; firstShowTip=1; ab_sr=1.0.1_ZTcxYjIwNzI0YWIyNTY4YjBjYWNkMjkzZmEzNjMyNGUzOTRiZGZjNjRlODE4MWIxZDNjZWNmZmJmNGU3MWM0ZDU1MTBkMTMwMjQ1Yzc4NTc0YTQ5OTRhMmFjMGJhMTJiNTY3NTAxN2FiMzhkOGYyNTc2YzEzYTZmYTFkMWYwNTE3YmNjYzg3YTQwNDE2NzFiZjRmYWMzMjYxNGU0OWQyNg==";
            httpGet.setHeader("Cookie", cookie);
            httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");
            httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
            httpGet.setHeader("Connection", "keep-alive");
            httpGet.setHeader("Host", "image.baidu.com");
            httpGet.setHeader("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"99\", \"Google Chrome\";v=\"99\"");
            httpGet.setHeader("sec-ch-ua-mobile", "?0");
            httpGet.setHeader("sec-ch-ua-platform", "\"Windows\"");
            httpGet.setHeader("Sec-Fetch-Dest", "document");
            httpGet.setHeader("Sec-Fetch-Mode", "navigate");
            httpGet.setHeader("Sec-Fetch-Site", "none");
            httpGet.setHeader("Sec-Fetch-User", "?1");
            httpGet.setHeader("Upgrade-Insecure-Requests", "1");
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.51 Safari/537.36");
            httpGet.setHeader("Cookie", cookie);
            final boolean[] crawling = {true};
            executor.submit(new Runnable() {
                @Override
                public void run() {
//                    synchronized (HttpGet.class) {
                        crawling[0] = crawling(httpGet, searchName);
//                    }
                }
            });

            if (!crawling[0]) {
                System.out.println("break");
                break;
            }
            Thread.sleep(1000);
        }

    }

    private static boolean crawling(HttpGet httpGet, String search) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                Pic pic = JSON.parseObject(EntityUtils.toString(response.getEntity()), Pic.class);
                System.out.println(pic.getData().size());
                if (pic.getData().size() == 1) {
                    pic.getData().clear();
                    return false;
                }
                for (Pic.DataBean datum : pic.getData()) {
                    if (datum.getThumbURL() == null) {
                        continue;
                    }

                    URLConnection con = new URL(datum.getThumbURL()).openConnection();
                    con.setConnectTimeout(5000);
                    con.setReadTimeout(5000);

                    InputStream content = con.getInputStream();
                    File file = new File("D:\\pic\\"
                            + "\\" + search);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File("D:\\pic\\"
                            + "\\" + search + "\\" + search + UUID.randomUUID().toString() + ".jpg")));
                    // 开始读取 写入
//                    byte[] bs = new byte[1024];
//                    int len;
//                    while ((len = content.read(bs)) != -1) {
//                        outputStream.write(bs, 0, len);
//                        outputStream.flush();
//                    }
                    FileCopyUtils.copy(content, outputStream);
                }
            } else {
                System.out.println("访问失败");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
