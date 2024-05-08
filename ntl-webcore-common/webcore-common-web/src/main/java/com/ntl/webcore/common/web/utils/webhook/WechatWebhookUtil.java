package com.ntl.webcore.common.web.utils.webhook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class WechatWebhookUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(WechatWebhookUtil.class);


    public static void sendTextMessageThread(String webhookUrl, String content) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                sendTextMessage(webhookUrl, content);
            }
        });
        thread.start();
    }

    public static void sendMarkdownMessageThread(String webhookUrl, String title, String content) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                sendMarkdownMessage(webhookUrl, title, content);
            }
        });
        thread.start();
    }

    public static int sendTextMessage(String webhookUrl, String content) {
        int responseCode = 0;
        try {
            URL url = new URL(webhookUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            String message = String.format("{\"msgtype\": \"text\", \"text\": {\"content\": \"%s\"}}",
                    content);

            OutputStream os = connection.getOutputStream();
            os.write(message.getBytes("UTF-8"));

            os.flush();
            os.close();

            //int responseCode = connection.getResponseCode();
            String errorMsg = ((List)connection.getHeaderFields().get("Error-Msg")).get(0).toString();
            if(!"ok".equals(errorMsg)){
                responseCode = -1;
                LOGGER.error("钉钉机器人发送失败，webhookUrl:{}, content:{}, errorMsg:", webhookUrl, content, errorMsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseCode = -1;
        }
        return responseCode;
    }

    public static int sendMarkdownMessage(String webhookUrl, String title, String content) {
        int responseCode = 0;
        try {
            URL url = new URL(webhookUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            String message = String.format("{\"msgtype\": \"markdown\", \"markdown\": {\"content\": \"%s\", \"title\": \"%s\"}}",
                    content, title);

            OutputStream os = connection.getOutputStream();
            os.write(message.getBytes("UTF-8"));
            os.flush();
            os.close();

            //int responseCode = connection.getResponseCode();
            String errorMsg = ((List)connection.getHeaderFields().get("Error-Msg")).get(0).toString();
            if(!"ok".equals(errorMsg)){
                responseCode = -1;
                LOGGER.error("钉钉机器人发送失败，webhookUrl:{}, content:{}, errorMsg:", webhookUrl, content, errorMsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseCode = -1;
        }
        return responseCode;
    }

}

