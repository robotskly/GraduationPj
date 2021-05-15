package com.example.graduationpj.support.utils

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import com.example.graduationpj.module.media.model.Song

class MusicUtil {
    val songList:ArrayList<Song> = arrayListOf()
    var song:Song = Song()

    companion object{
        fun newInstance():MusicUtil{
            return MusicUtil()
        }
    }
    fun getMusic(context: Context?):ArrayList<Song>{
        //Internal 还是 External
        val cursor: Cursor? = context?.contentResolver?.query(MediaStore.Audio.Media.INTERNAL_CONTENT_URI,null,null,null, MediaStore.Audio.AudioColumns.IS_MUSIC)

        if(cursor!=null){
            while(cursor.moveToNext()){
                song = Song()
                song.songName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME))
                song.songSinger = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST))
                song.songPath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA))
                song.songDuration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION))
                song.songSize = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE))

                if(song.songSize!! > 1000 * 800) {
                    if (song.songName!!.contains("-")) {
                         val str= song.songName!!.split ("-")
                        song.songSinger = str[0];
                        song.songName= str[1];
                    }
                    songList.add(song)
                }
            }
        }
        cursor?.close()
        return songList
    }

    //    转换歌曲时间的格式
    fun formatTime(time: Int): String? {
        return if (time / 1000 % 60 < 10) {
            (time / 1000 / 60).toString() + ":0" + time / 1000 % 60
        } else {
            (time / 1000 / 60).toString() + ":" + time / 1000 % 60
        }
    }

}