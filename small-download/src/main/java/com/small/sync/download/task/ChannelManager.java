package com.small.sync.download.task;

import com.small.sync.download.common.DataTaskContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2023/3/19 13:30
 * @version: v1.0
 */
@Slf4j
@Component
public class ChannelManager {

    @Autowired
    private DataTaskContext dataTaskContext ;

    private List<Channel> channels = new ArrayList<>(10);


    public void addChannel(Channel channel){
        channels.add(channel);
    }

    /**
     * 启动程序
     */
    public void start() {
        for (Channel channel : channels) {
            log.info("启动通道:" + channel.getName());
            channel.start();
        }
    }

    /**
     * 停止程序
     */
    public void stop() {
        for (Channel channel : channels) {
            channel.close();
        }
        ExecutorService service = (ExecutorService) dataTaskContext.getPool();
        service.shutdown();
    }

}
