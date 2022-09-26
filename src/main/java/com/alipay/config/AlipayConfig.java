package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2021000121669495";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCD6DJoB3RIvFDLte1FH3+n6kvDsepVgk3udErds6IW87hHlBSfx6yFgKymmLK0hgr5KgN8R923A1/ngeDrO8CPpQMtZ3mD/0WTGWI6Y6Oeny81pHY9oBkrU4QcfTZTDdFGotX4/PtA7j2dGzqDgfkOSsJiM4m3/FonzFU1ut7HVbUq9wf63a3T7csX/HNBB90ncRLzehOpH58s5qsNNCTsy71GEoWe1l6o8+XSiNTUuBKNH9qVy5cXtJN73/R3+B6/WocwipF3lIl81LBcMw2mAAUfX9dMNk9h3WnsLW0tpfPOldK3cdZbsV8Q0yt1l1PX5iDLO7kQTKxKrrgQNPjNAgMBAAECggEAVjG/5nygfvsXbNZX0mhv2YDfu2QiNXgthplVZqQAUjE2hQd9pp8q3B4mM8LqW86PkxviJS8s8W9yFuym1fA4t2Tju6K3JAsGIsZeirsbawaoKcxrO3/HkqlDVf54JjOMQjUbz1U4tcg4WNY3GUIwRieR9+unnaN4IjOCptaSxvwJRyQKhW5Qw/nH1O9qjVxEZBz6tM75eHhqXSaRZn66Uj8RvfVefjSxRQJd1PNW/4GAVorZIGNIAq8rLnkN1aqfX6y07eU48lHCcH26oXBJ4QC9pQLwNT03R2viqlVkxFTYdbhgeE8k0wHNcmdmfe+uR53M3GKimkJS+Yor74z0oQKBgQC/b0WWE7u/zup1R3ZyWNFf1+RuSVQZT9G3EB29iTILC78sjo7URnPiaqvJmg94wX0f2mOkjrAWbQl9ON4A4o/KhZdBz6I8ssVL03MrCjoa4NDxUrYT6zCotpW1eWOLllXS0OUxG1dop0tQoBdi43aEaDeoarNv9MoPgnPORzTYaQKBgQCwZTpki6xEaMR/X8Ad8VL5q8RW8T0Lbkf6SZ/4cVa2Ux8Vq1fJ+fU/9XM8MzTiU2rFgXDmBO3XdvuoInN2YUKjfV2Mgs6MvGBoWQUoJJnvXHRvH8HdTDkD7NgDy+94GKCgnS3Y9EvYqjPmWhWhdLjliNLg1RQySm++uyHBd2jwxQKBgQClVRubPxXC1ZjGi16v6SeDCDlAStJ4PeiYmzGkVU5EHfQRLloK2YISRSBysDy2hR1kmj++ioFoDOnKHYgW6ZVYSsJU01eB5r/g+hEYVe/S8OFj2pRoZZKT04U+w0UG9GLQ/RtEqyF1wC6axnkMLflY541SRj0M7zRn6rnLqNHqKQKBgH58d/y1Mxfy0ERN0qMl8svZYXa+kXlq4O4WcUFjdDxVKksNjGvmcvgj9zfJuDg6fKuXf+9QxYPla9tKqOTQJUq6kKQ7AAVm5vKsMshwRnmfIFa2vPsEtzWbVeKTD+pcLoI9cyzJMMHyejjJjA00d4kQLbbO1CajvbFU2oWyLGvJAoGAXwvaEmT7r2giwamk8Vl1jwZADiib+wJlqIj/jTUJqpNV2LmiB189noSz/eAYsp7LnL859ri9gCIx+Hb1gRNLdEgcNDZJKDhDqscl5Xy3/qCke68r2eutWCeIrxSjDRxiyUI2upwk0Fg41ineY4aZ3cLRp6VXbx8zSlNJTyumzJ4=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqrocz1wadtlMY/lBCLwfpdqtxu2MSdXL5RXZ4bXALW/zLxGQaVcOTcBci8UAWxDWn19mdoz5w4iJ6OoXUCYa3cI1cdtM4vkhyHPfUBzzt7BAL/z70sjvCm3PMRhH3PHtVLKviMZhq1Uz+UCoP6E2e7lyfGGGODQ4RyH6FpL1H4C9YyeVBE0UM+kJWsrFzbrAA+UaMrhyJzSIediNv+BFAHSj4FOAdeRBpKF8wGav3YanoyHRHLNHa67x9llFIEWEF7oGVQQNCSliw8/VeO7/o1TaPwkPi7fzFrGyijhOK0sogdSyBtjR3TfT2xRYcRwb6OzZVBsQPMH3LbxMagixXwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/SmileClassRoom/pay/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/SmileClassRoom/pay/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

