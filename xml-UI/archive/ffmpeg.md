
---

### FFmpeg编辑多媒体文件
- [FFmpeg命令编辑视频](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614334&idx=2&sn=22c884dc2553b16f7f5cf808fac3a2ce)
- [FFmpeg+ImageMagick从视频中创建gif](https://www.ostechnix.com/create-animated-gif-ubuntu-16-04/)
- [FFmpeg从PDF中创建视频](https://www.ostechnix.com/create-video-pdf-files-linux/)
- [FFmpeg常用命令](https://blog.csdn.net/weixin_42081389/article/details/100543007)
- [Linux中字幕编辑](https://mp.weixin.qq.com/s/K6lZI3OuUSUKQB-9JhssAg)
---

#### 视频转换和截取
```console
# mac上安装ffmpeg
brew install ffmpeg
# 查看视频信息
# -i input 指定输入文件
ffmpeg -i test.mp4
ffmpeg -i test.mp4 -hide_banner
# 转换视频格式
# 上文链接中还有 -vf压缩视频 -ab压缩音频参数 -an剔除音频 -vn剔除视频, 抓取图像，裁剪视频
# -y 如果输出文件存在自动覆盖
ffmpeg -i test.mp4 -y test.mp3
# -t duration 裁剪视频开始至15秒部分内容
ffmpeg -i test.mp4 -t 15 te.mp4
# -ss 设置视频截取的开始点
# -to 设置视频截取的结束点
ffmpeg -i test.mp4 -ss 00:00:10 -to 00:00:15 te.mp4
# 截取开始时间至持续时间的一段视频
ffmpeg -i test.mp4 -ss 01:15:50 -t 50 output.mp4
# 上文链接中还有concat几个视频到一个视频文件中方法
# 裁剪视频，比如可以把burned的字幕切掉
# 先用上面命令查看原视频文件大小，再根据宽高和坐标进行 crop=w:h:x:y
ffmpeg -i test.mp4 -filter:v "crop=1280:690:0:0" output.mp4
```

#### 添加字幕
首先需要准备字幕文件
```console
touch subtitle.srt

# edit subtitle.srt 用UTF8格式
# 格式就是 起始时间 --> 结束时间  + 字幕内容 
1
00:00:00,000 --> 00:00:01,880
在经历了一场人生巨变之后
When I was lying there in the VA hospital ...

2
00:00:02,000 --> 00:00:03,000
我被送进了退伍军人管理局医院
... with a big hole blown through the middle of my life,

3
00:00:03,090 --> 00:00:04,000
那段时间我经常会梦到自己在飞翔
... I started having these dreams of flying.

4
00:00:04,000 --> 00:00:05,000
我被送进了退伍军人管理局医院
... with a big hole blown through the middle of my life,

5
00:00:05,001 --> 00:00:05,999
那段时间我经常会梦到自己在飞翔
... I started having these dreams of flying.
```
再把字幕文件加入到视频文件中
```console
# 字幕集成到视频文件，通过播放器字幕选择播放,相当于外挂字幕
# -c copy -c:s mov_text 固定顺序参数，含义为Video:copy, Audio:copy, Subtitle:copy with encode mov_text(movie字幕文本编码)
# -metadata:s:s:0 language=eng 设定输出文件的metadata中第一个字幕track的名称是英文，可以在播放器中选择
# -metadata:s:a:0 language=eng 设定输出文件的metadata中第一个audio stream语言名称
ffmpeg -i te.mp4 -i subtitle.srt -c copy -c:s mov_text -metadata:s:s:0 language=eng -y output.mp4

# 嵌入SRT字幕到视频文件,播放器无法选择，相当于就是把字幕烧入(burn)视频文件
# https://trac.ffmpeg.org/wiki/HowToBurnSubtitlesIntoVideo
# 如果遇到 No such filter: 'subtitles'这样错误，说明安装ffmpeg没有编入某些static库
ffmpeg -i te.mp4 -vf subtitles=subtitle.srt -y output.mp4
ffmpeg -i te.mp4 -vf ass=subtitle.ass -y output.mp4
# 将video.mkv中的字幕（默认）嵌入到out.avi文件 
ffmpeg -i video.mkv -vf subtitles=video.mkv out.avi
ffmpeg -i te.mp4 -filter_complex "subtitles=subtitle.srt" -c copy output.mp4

# 字幕格式转换
ffmpeg -i subtitle.srt subtitle.ass
```
#### 高级参数
默认ffmpeg给多媒体文件中video, audio, subtitle都指定一种stream类型(v/a/s)。这三种类型可以同时从input files中选中加到output file中.
```console
# -c copy 默认拷贝输入文件中Video/Audio/Subtitle 
# -vn disable Video stream 意味output.mp4文件中没有视频
# -an disable Audio stream 意味output.mp4文件中没有音频
# -sn disable Subtitle stream 意味output.mp4文件中没有字幕
# -dn disable metadata stream 意味output.mp4文件中没有metadata
ffmpeg -i te.mp4 -c copy -vn -y output.mp4

# -map  完全手动来控制转换选择
# map输入文件中ALL streams到output文件相当于 ffmpeg -i test.mp4 output.mp4
ffmpeg -i te.mp4 -map 0 output.mp4
# 如果输入文件中只有两个audio streams,会被map成"0:0"和"0:1". 可以选择在output中输出哪一个
ffmpeg -i te.mp4 -map 0:1 out.wav

# 命令行第一个"-map" 0 option对应第一个input文件，第二个"-map" 1 option对应第二个input文件
# 选择a.mov中第3个stream和b.mov中第7个stream输出到out.mov中
ffmpeg -i a.mov -i b.mov -c copy -map 0:2 -map 1:6 out.mov 
# 选择a.mov中全部视频stream和第三个音频stream输出到out.mov中
ffmpeg -i a.mov -map 0:v -map 0:a:2 out.mov 
# 选择a.mov中全部stream，除了第2个音频stream(-0:a:1)输出到out.mov中
ffmpeg -i a.mov -map 0 -map -0:a:1 out.mov
# 选择English audio stream输出到out.mov 0:m metadata
ffmpeg -i a.mov -map 0:m:language:eng out.movPUT        
```