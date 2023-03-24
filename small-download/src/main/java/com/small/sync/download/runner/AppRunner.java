package com.small.sync.download.runner;

import com.small.sync.download.task.ChannelManager;
import com.small.sync.download.task.DownLoadChannel4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2023/3/19 13:28
 * @version: v1.0
 */
@Component
public class AppRunner implements ApplicationRunner {
    @Autowired
    private ChannelManager channelManager ;

    @Autowired
    private DownLoadChannel4 downLoadChannel4 ;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //Channels channels = SpringContextHolder.getBean("channels", Channels.class);
        channelManager.addChannel(downLoadChannel4);
        channelManager.start();
    }
}
