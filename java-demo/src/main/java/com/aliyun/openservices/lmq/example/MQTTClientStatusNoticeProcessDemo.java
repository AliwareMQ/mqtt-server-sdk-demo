package com.aliyun.openservices.lmq.example;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.mqtt.server.ServerConsumer;
import com.alibaba.mqtt.server.callback.StatusListener;
import com.alibaba.mqtt.server.config.ChannelConfig;
import com.alibaba.mqtt.server.config.ConsumerConfig;
import com.alibaba.mqtt.server.model.StatusNotice;

public class MQTTClientStatusNoticeProcessDemo {
    public static void main(String[] args) throws Exception {
        /**
         * 此处参数内容为示意。接入点地址，购买实例，且配置完成后即可获取，接入点地址必须填写分配的域名，不得使用 IP 地址直接连接，否则可能会导致客户端异常。
         */
        String domain = "domain";

        /**
         * 使用的协议和端口必须匹配，为5672。
         */
        int port = 5672;

        /**
         * 此处参数内容为示意。MQTT 实例 ID，购买后控制台获取
         */
        String instanceId = "instanceId";

        /**
         * 此处参数内容为示意。账号 accesskey，从账号系统控制台获取
         */
        String accessKey = "accessKey";

        /**
         * 此处参数内容为示意。账号 secretKey，从账号系统控制台获取，仅在Signature鉴权模式下需要设置
         */
        String secretKey = "secretKey";

        /**
         * 此处参数内容为示意。MQTT groupId 在 MQTT 控制台申请才能使用。
         * */
        String mqttGroupId = "mqttGroupId";

        ChannelConfig channelConfig = new ChannelConfig();
        channelConfig.setDomain(domain);
        channelConfig.setPort(port);
        channelConfig.setInstanceId(instanceId);
        channelConfig.setAccessKey(accessKey);
        channelConfig.setSecretKey(secretKey);

        ServerConsumer serverConsumer = new ServerConsumer(channelConfig, new ConsumerConfig());
        serverConsumer.start();
        serverConsumer.subscribeStatus(mqttGroupId, new StatusListener() {
            @Override
            public void process(StatusNotice statusNotice) {
                System.out.println(JSONObject.toJSONString(statusNotice));
            }
        });
    }

}
