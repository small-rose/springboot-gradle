package com.small.demo.boot.music.musicList;

import com.small.demo.boot.music.BootMusicApplicationTests;
import com.small.demo.boot.music.musicList.service.MusicService;
import com.small.demo.boot.music.musicList.vo.MusicInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2023/2/11 22:43
 * @version: v1.0
 */


public class MusicListTest extends BootMusicApplicationTests {

    @Autowired
    MusicService musicService ;


    @Test
    void contextLoads() {
        File file = new File("D:\\音乐\\");
        File[] list = file.listFiles((d-> d.isFile()));
        for (File f : list) {
            System.out.println("---测试---"+f.getAbsolutePath());
            MusicInfo music = musicService.getMusicInfo(f.getAbsolutePath());
            System.out.println(music);

        }

    }

}
