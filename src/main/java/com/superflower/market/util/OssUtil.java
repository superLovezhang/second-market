package com.superflower.market.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class OssUtil {

    private static String endpoint = "你自己的";
    private static String accessKeyId = "你自己的";
    private static String accessKeySecret = "你自己的";
    private static String bucketName = "edu-teachers";

    public static String upload(MultipartFile file) throws Exception {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(OssUtil.endpoint, OssUtil.accessKeyId, OssUtil.accessKeySecret);

        String fileName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        fileName = uuid + fileName;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String formatDate = sdf.format(date);
        fileName = formatDate + "/" + fileName;

        // 上传文件流。
        ossClient.putObject(OssUtil.bucketName, fileName, file.getInputStream());

        // 关闭OSSClient。
        ossClient.shutdown();

        return "https://" + OssUtil.bucketName +  "." + OssUtil.endpoint + "/" + fileName;
    }

}
