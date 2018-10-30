***

## ������Ĺ���

### CFR
CFR��һ��������Java�����빤�ߣ�ʹ������ϸ��ӣ�����Ŀǰ֧�ֵ�Java9�����롣����ʱ����Ҫ����һ��lib<br>
**project homepage**  <http://www.benf.org/other/cfr/>   
```bash
#decompile a jar named main-1.0.jar
java -jar cfr_128.jar --analyseas JAR main-1.0.jar --outputpath ./

#help sub option content
java -jar cfr_128.jar --help jarfilter

#options
   --aexagg                         (boolean)
   --aggressivesizethreshold        (int >= 0)  default: 15000
   --allowcorrecting                (boolean)  default: true
   --analyseas                      (One of [JAR, WAR, CLASS])
   --arrayiter                      (boolean)  default: true if class file from version 49.0 (Java 5) or greater
   --caseinsensitivefs              (boolean)  default: false
   --clobber                        (boolean)
   --collectioniter                 (boolean)  default: true if class file from version 49.0 (Java 5) or greater
   --commentmonitors                (boolean)  default: false
   --comments                       (boolean)  default: true
   --decodeenumswitch               (boolean)  default: true if class file from version 49.0 (Java 5) or greater
   --decodefinally                  (boolean)  default: true
   --decodelambdas                  (boolean)  default: true if class file from version 52.0 (Java 8) or greater
   --decodestringswitch             (boolean)  default: true if class file from version 51.0 (Java 7) or greater
   --dumpclasspath                  (boolean)  default: false
   --eclipse                        (boolean)  default: true
   --elidescala                     (boolean)  default: false
   --extraclasspath                 (string)
   --forcecondpropagate             (boolean)
   --forceexceptionprune            (boolean)
   --forcereturningifs              (boolean)
   --forcetopsort                   (boolean)
   --forcetopsortaggress            (boolean)
   --forloopaggcapture              (boolean)
   --hidebridgemethods              (boolean)  default: true
   --hidelangimports                (boolean)  default: true
   --hidelongstrings                (boolean)  default: false
   --hideutf                        (boolean)  default: true
   --ignoreexceptions               (boolean)  default: false
   --innerclasses                   (boolean)  default: true
   --j14classobj                    (boolean)  default: false if class file from version 49.0 (Java 5) or greater
   --jarfilter                      (string)
   --labelledblocks                 (boolean)  default: true
   --lenient                        (boolean)  default: false
   --liftconstructorinit            (boolean)  default: true
   --outputdir                      (string)
   --outputpath                     (string)
   --override                       (boolean)  default: true if class file from version 50.0 (Java 6) or greater
   --pullcodecase                   (boolean)  default: false
   --recover                        (boolean)  default: true
   --recovertypeclash               (boolean)
   --recovertypehints               (boolean)
   --relinkconststring              (boolean)  default: true
   --removebadgenerics              (boolean)  default: true
   --removeboilerplate              (boolean)  default: true
   --removedeadmethods              (boolean)  default: true
   --removeinnerclasssynthetics     (boolean)  default: true
   --rename                         (boolean)  default: false
   --renamedupmembers
   --renameenumidents
   --renameillegalidents
   --renamesmallmembers             (int >= 0)  default: 0
   --showinferrable                 (boolean)  default: false if class file from version 51.0 (Java 7) or greater
   --showops                        (int >= 0)  default: 0
   --showversion                    (boolean)  default: true
   --silent                         (boolean)  default: false
   --stringbuffer                   (boolean)  default: false if class file from version 49.0 (Java 5) or greater
   --stringbuilder                  (boolean)  default: true if class file from version 49.0 (Java 5) or greater
   --sugarasserts                   (boolean)  default: true
   --sugarboxing                    (boolean)  default: true
   --sugarenums                     (boolean)  default: true if class file from version 49.0 (Java 5) or greater
   --tidymonitors                   (boolean)  default: true
   --tryresources                   (boolean)  default: true if class file from version 51.0 (Java 7) or greater
   --usenametable                   (boolean)  default: true
   --help                           (string)
```

### jd-gui
jd-gui��һ��ͼ�λ�Java�����빤�ߣ�Ҳ�ṩIDE���ɵĲ��������ʹ�÷ǳ����㣬����Ŀǰ�汾��֧��closure��ʽ����<br>
![jd-gui for windows](http://jd.benow.ca/img/screenshot17.png)

### IntelliJ
IntelliJ������Ĭ���ṩһ�������������IDE��ֱ�ӷ�����<br>
![IntelliJ](https://www.jetbrains.com/idea/features/screenshots/16/why_decompiler.png)

