***

## һЩ����ת��

### primitive long
ϵͳĬ����ֵ������int����˵���ֵ������Χʱ��ᱨ�����ʱ���������β����L/lָ�����ͣ�����Сдl������1�����ô�д������
```java 
long value = 3504235896859348553;
compile error:
integer number too large: 3504235896859348553
#compile successfully
long value = 3504235896859348553L;

#long��ֵ�Ծɱ������������ֵ����long�ͷ�Χ
long value = 12999962314720761107L;
compile error:
integer number too large: 12999962314720761107
#���Խ���Դ����޷���long�������λ���ɷ���λ���������з���long
long right = Long.parseUnsignedLong("12999962314720761107");
``` 
��ЩUUID��ԭ��Ҫ������������google protobuf���UUIDת�������������λ�͵�λ��long����20����λ������Χ��ֻ�ܵ����޷���long
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



