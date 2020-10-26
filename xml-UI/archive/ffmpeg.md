
---

### FFmpeg�༭��ý���ļ�
- [FFmpeg����༭��Ƶ](https://mp.weixin.qq.com/s/9FltBw_Q3XKbvL3GVaCCnA)
- [FFmpeg+ImageMagick����Ƶ�д���gif](https://www.ostechnix.com/create-animated-gif-ubuntu-16-04/)
- [FFmpeg��PDF�д�����Ƶ](https://www.ostechnix.com/create-video-pdf-files-linux/)
- [FFmpeg��������](https://blog.csdn.net/weixin_42081389/article/details/100543007)
- [Linux����Ļ�༭](https://mp.weixin.qq.com/s/K6lZI3OuUSUKQB-9JhssAg)
---

#### ��Ƶת���ͽ�ȡ
```console
# mac�ϰ�װffmpeg
brew install ffmpeg

# �鿴��Ƶ��Ϣ
# -i input ָ�������ļ�
ffmpeg -i test.mp4
ffmpeg -i test.mp4 -hide_banner

# ת����Ƶ��ʽ
# ���������л��� -vfѹ����Ƶ -abѹ����Ƶ���� -an�޳���Ƶ -vn�޳���Ƶ, ץȡͼ�񣬲ü���Ƶ
# -y �������ļ������Զ�����
ffmpeg -i test.mp4 -y test.mp3
# -t duration �ü���Ƶ��ʼ��15�벿������
ffmpeg -i test.mp4 -t 15 te.mp4
# -ss ������Ƶ��ȡ�Ŀ�ʼ��
# -to ������Ƶ��ȡ�Ľ�����
ffmpeg -i test.mp4 -ss 00:00:10 -to 00:00:15 te.mp4
# ��ȡ��ʼʱ��������ʱ���һ����Ƶ
ffmpeg -i test.mp4 -ss 01:15:50 -t 50 output.mp4

# �������������л���concat������Ƶ��һ����Ƶ�ļ��з���
# �ü���Ƶ��������԰�burned����Ļ�е�
# ������������鿴ԭ��Ƶ�ļ���С���ٸ��ݿ�ߺ�������� crop=w:h:x:y
ffmpeg -i test.mp4 -filter:v "crop=1280:690:0:0" output.mp4
```

#### �����Ļ
������Ҫ׼����Ļ�ļ�
```console
touch subtitle.srt

# edit subtitle.srt ��UTF8��ʽ
# ��ʽ���� ��ʼʱ�� --> ����ʱ��  + ��Ļ���� 
1
00:00:00,000 --> 00:00:01,880
�ھ�����һ�������ޱ�֮��
When I was lying there in the VA hospital ...

2
00:00:02,000 --> 00:00:03,000
�ұ��ͽ���������˹����ҽԺ
... with a big hole blown through the middle of my life,

3
00:00:03,090 --> 00:00:04,000
�Ƕ�ʱ���Ҿ������ε��Լ��ڷ���
... I started having these dreams of flying.

4
00:00:04,000 --> 00:00:05,000
�ұ��ͽ���������˹����ҽԺ
... with a big hole blown through the middle of my life,

5
00:00:05,001 --> 00:00:05,999
�Ƕ�ʱ���Ҿ������ε��Լ��ڷ���
... I started having these dreams of flying.
```
�ٰ���Ļ�ļ����뵽��Ƶ�ļ���
```console
# ��Ļ���ɵ���Ƶ�ļ���ͨ����������Ļѡ�񲥷�,�൱�������Ļ
# -c copy -c:s mov_text �̶�˳�����������ΪVideo:copy, Audio:copy, Subtitle:copy with encode mov_text(movie��Ļ�ı�����)
# -metadata:s:s:0 language=eng �趨����ļ���metadata�е�һ����Ļtrack��������Ӣ�ģ������ڲ�������ѡ��
# -metadata:s:a:0 language=eng �趨����ļ���metadata�е�һ��audio stream��������
ffmpeg -i te.mp4 -i subtitle.srt -c copy -c:s mov_text -metadata:s:s:0 language=eng -y output.mp4

# Ƕ��SRT��Ļ����Ƶ�ļ�,�������޷�ѡ���൱�ھ��ǰ���Ļ����(burn)��Ƶ�ļ�
# https://trac.ffmpeg.org/wiki/HowToBurnSubtitlesIntoVideo
# ������� No such filter: 'subtitles'��������˵����װffmpegû�б���ĳЩstatic��
ffmpeg -i te.mp4 -vf subtitles=subtitle.srt -y output.mp4
ffmpeg -i te.mp4 -vf ass=subtitle.ass -y output.mp4
# ��video.mkv�е���Ļ��Ĭ�ϣ�Ƕ�뵽out.avi�ļ� 
ffmpeg -i video.mkv -vf subtitles=video.mkv out.avi
ffmpeg -i te.mp4 -filter_complex "subtitles=subtitle.srt" -c copy output.mp4

# ��Ļ��ʽת��
ffmpeg -i subtitle.srt subtitle.ass
```
#### �߼�����
Ĭ��ffmpeg����ý���ļ���video, audio, subtitle��ָ��һ��stream����(v/a/s)�����������Ϳ���ͬʱ��input files��ѡ�мӵ�output file��.
```console
# -c copy Ĭ�Ͽ��������ļ���Video/Audio/Subtitle 
# -vn disable Video stream ��ζoutput.mp4�ļ���û����Ƶ
# -an disable Audio stream ��ζoutput.mp4�ļ���û����Ƶ
# -sn disable Subtitle stream ��ζoutput.mp4�ļ���û����Ļ
# -dn disable metadata stream ��ζoutput.mp4�ļ���û��metadata
ffmpeg -i te.mp4 -c copy -vn -y output.mp4

# -map  ��ȫ�ֶ�������ת��ѡ��
# map�����ļ���ALL streams��output�ļ��൱�� ffmpeg -i test.mp4 output.mp4
ffmpeg -i te.mp4 -map 0 output.mp4
# ��������ļ���ֻ������audio streams,�ᱻmap��"0:0"��"0:1". ����ѡ����output�������һ��
ffmpeg -i te.mp4 -map 0:1 out.wav

# �����е�һ��"-map" 0 option��Ӧ��һ��input�ļ����ڶ���"-map" 1 option��Ӧ�ڶ���input�ļ�
# ѡ��a.mov�е�3��stream��b.mov�е�7��stream�����out.mov��
ffmpeg -i a.mov -i b.mov -c copy -map 0:2 -map 1:6 out.mov 
# ѡ��a.mov��ȫ����Ƶstream�͵�������Ƶstream�����out.mov��
ffmpeg -i a.mov -map 0:v -map 0:a:2 out.mov 
# ѡ��a.mov��ȫ��stream�����˵�2����Ƶstream(-0:a:1)�����out.mov��
ffmpeg -i a.mov -map 0 -map -0:a:1 out.mov
# ѡ��English audio stream�����out.mov 0:m metadata
ffmpeg -i a.mov -map 0:m:language:eng out.movPUT        
```