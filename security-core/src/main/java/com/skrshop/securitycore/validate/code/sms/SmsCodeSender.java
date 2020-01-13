package com.skrshop.securitycore.validate.code.sms;

/**
 * 短信验证码发送接口
 */
public interface SmsCodeSender {
    /**
     * 发送短信验证码,这里建议提供对应的服务，做转发和条数的限制。
     * 调用单独的短信和邮件服务器
     *
     * @param mobile  手机号
     * @param code 验证码
     */
    void send(String mobile, String code);
}
