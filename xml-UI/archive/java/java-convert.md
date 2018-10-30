***

## 一些类型转换

### primitive long
系统默认数值类型是int，因此当数值超出范围时候会报错。这个时候可以在数尾加上L/l指定类型，不过小写l跟数字1很像，用大写更合理
```java 
long value = 3504235896859348553;
compile error:
integer number too large: 3504235896859348553
#compile successfully
long value = 3504235896859348553L;

#long数值仍旧报错，表明这个数值超出long型范围
long value = 12999962314720761107L;
compile error:
integer number too large: 12999962314720761107
#可以将其对待成无符号long，把最高位当成符号位，解析成有符号long
long right = Long.parseUnsignedLong("12999962314720761107");
``` 
有些UUID还原需要这样处理，比如google protobuf会把UUID转成如下输出，高位和低位的long都是20个数位超过范围，只能当成无符号long
```java 
node_uuid {
  left: 10546586156302093832
  right: 12999962314720761107
}

long left = Long.parseUnsignedLong("10546586156302093832");
long right = Long.parseUnsignedLong("12999962314720761107");
System.out.println(String.format("left: %d --------- right: %d", left, right));
System.out.println("--------- " + new UUID(left, right));
 
output:
left: -7900157917407457784 --------- right: -5446781758988790509
--------- 925d003b-1f70-4e08-b469-24d936655513
```



