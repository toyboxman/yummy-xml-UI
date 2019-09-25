***

## Ways to use string

### 字符串中重复出现字串index
org.apache.commons.lang.StringUtils的ordinalIndexOf方法返回指定重复子串位置的index
```java 
HashMap<String, Object> hashMap = new HashMap<>();
StackOverflowError stackOverflowError = new StackOverflowError();
hashMap.put("JSESSIONID=BD630DE2614A157CB4BAF8D9698DC899; JSESSIONID=F176DA45D8CB22B2BC5D092A64F42355", stackOverflowError);
hashMap.put("3c90c6e9-ac12-4624-815a-370dfe85f417", stackOverflowError);
hashMap.forEach((key, value)
        -> {
    System.out.println(String.format("#xtrace-dumpContext# Key: %s --> Value: %s", key, value));
});

String url = "#view=fabric/nodes/editor/monitor";
System.out.println(StringUtils.ordinalIndexOf(url, "/", 5));
System.out.println(url.substring(0, StringUtils.ordinalIndexOf(url, "/", 2)));
``` 
执行结果
```
#xtrace-dumpContext# Key: JSESSIONID=BD630DE2614A157CB4BAF8D9698DC899; JSESSIONID=F176DA45D8CB22B2BC5D092A64F42355 --> Value: java.lang.StackOverflowError
#xtrace-dumpContext# Key: 3c90c6e9-ac12-4624-815a-370dfe85f417 --> Value: java.lang.StackOverflowError

-1
#view=fabric/nodes
```
