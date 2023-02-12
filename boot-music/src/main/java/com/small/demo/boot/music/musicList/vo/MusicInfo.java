package com.small.demo.boot.music.musicList.vo;

import lombok.Data;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2023/2/11 20:25
 * @version: v1.0
 */

@Data
public class MusicInfo {


    //音乐名
    private String name;
    //歌手名
    private String singerName;
    //专辑名
    private String album;
    //时长 以秒为单位
    private int duration;

    private String path;//音乐文件存放路径

    public MusicInfo() {
    }

    public MusicInfo(String name, String singerName, String album, int duration, String path) {
        this.name = name;
        this.singerName = singerName;
        this.album = album;
        this.duration = duration;
        this.path = path;
    }

    @Override
    public String toString() {
        return "MusicInfo{" +
                "songName='" + name + '\'' +
                ", singerName='" + singerName + '\'' +
                ", album='" + album + '\'' +
                ", duration=" + duration +
                '}';
    }
}
