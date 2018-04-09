> [Stream 2 String](https://stackoverflow.com/questions/309424/read-convert-an-inputstream-to-a-string) 

***

## Ways to convert an InputStream to a String:

### 1.Using IOUtils.toString (Apache Utils)
```java
String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
```
### 2.Using CharStreams (guava)
```
String result = CharStreams.toString(new InputStreamReader(inputStream, Charsets.UTF_8));
```
### 3.Using Scanner (JDK)
```
Scanner s = new Scanner(inputStream).useDelimiter("\\A");
String result = s.hasNext() ? s.next() : "";
```
### 4.Using Stream Api (Java 8). Warning: This solution convert different line breaks (like \r\n) to \n.
```
String result = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
```
### 5.Using parallel Stream Api (Java 8). Warning: This solution convert different line breaks (like \r\n) to \n.
```
String result = new BufferedReader(new InputStreamReader(inputStream)).lines().parallel().collect(Collectors.joining("\n"));
```
### 6.Using InputStreamReader and StringBuilder (JDK)
```
final int bufferSize = 1024;
final char[] buffer = new char[bufferSize];
final StringBuilder out = new StringBuilder();
Reader in = new InputStreamReader(inputStream, "UTF-8");
for (; ; ) {
    int rsz = in.read(buffer, 0, buffer.length);
    if (rsz < 0)
        break;
    out.append(buffer, 0, rsz);
}
return out.toString();
```
### 7.Using StringWriter and IOUtils.copy (Apache Commons)
```
StringWriter writer = new StringWriter();
IOUtils.copy(inputStream, writer, "UTF-8");
return writer.toString();
```
### 8.Using ByteArrayOutputStream and inputStream.read (JDK)
```
ByteArrayOutputStream result = new ByteArrayOutputStream();
byte[] buffer = new byte[1024];
int length;
while ((length = inputStream.read(buffer)) != -1) {
	result.write(buffer, 0, length);
}
// StandardCharsets.UTF_8.name() > JDK 7
return result.toString("UTF-8");
```
### 9.Using BufferedReader (JDK). Warning: This solution convert different line breaks (like \n\r) to line.separator system property (for example, in Windows to "\r\n").
```
String newLine = System.getProperty("line.separator");
BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
StringBuilder result = new StringBuilder();
String line; boolean flag = false;
while ((line = reader.readLine()) != null) {
	result.append(flag? newLine: "").append(line);
	flag = true;
}
return result.toString();
```
### 10.Using BufferedInputStream and ByteArrayOutputStream (JDK)
```
BufferedInputStream bis = new BufferedInputStream(inputStream);
ByteArrayOutputStream buf = new ByteArrayOutputStream();
int result = bis.read();
while(result != -1) {
	buf.write((byte) result);
	result = bis.read();
}
// StandardCharsets.UTF_8.name() > JDK 7
return buf.toString("UTF-8");
```
### 11.Using inputStream.read() and StringBuilder (JDK). Warning: This solution has problem with Unicode, for example with Russian text (work correctly only with non-Unicode text)
``` 
int ch;
StringBuilder sb = new StringBuilder();
while((ch = inputStream.read()) != -1)
sb.append((char)ch);
reset();
return sb.toString();
``` 

---

#### Warning:
* Solutions 4, 5 and 9 convert different line breaks to one.
* Solution 11 can't work correctly with Unicode text

