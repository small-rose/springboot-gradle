package com.small.demo.boot.music.musicList.service;

import com.small.demo.boot.music.musicList.vo.MusicInfo;
import lombok.extern.slf4j.Slf4j;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2023/2/11 22:03
 * @version: v1.0
 */
@Slf4j
@Service
public class MusicService {

    public MusicInfo getMusicInfo(String filePath) {

        MusicInfo music = null;

        try {
            String name = new File(filePath).getName();

            if (name !=null && name.toLowerCase().endsWith(".mp3")) {
                MP3File mp3File = (MP3File) AudioFileIO.read(new File(filePath));
                MP3AudioHeader audioHeader = (MP3AudioHeader) mp3File.getAudioHeader();

                String songname = mp3File.getID3v2Tag().frameMap.get("TIT2").toString();//歌名
                String artist = mp3File.getID3v2Tag().frameMap.get("TPE1").toString();//歌手
                String album = mp3File.getID3v2Tag().frameMap.get("TALB").toString();//专辑
                int duration = audioHeader.getTrackLength();//时长

                music = new MusicInfo(reg(songname), reg(artist), reg(album), duration, filePath);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return music;
    }

    //去除不必要的字符串
    private String reg(String input) {
        return input.substring(input.indexOf('"') + 1, input.lastIndexOf('"'));
    }
}
