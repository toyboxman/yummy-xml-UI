
---

### *��ͬ���͵İ�ȫ֤��ת��*
- [digital signature/certificate](http://www.ruanyifeng.com/blog/2011/08/what_is_a_digital_signature.html)<br>

---

Java��Ӧ�����ʹ��TLSЭ�飬��Ҫ����ȫ֤��, ֤��ͨ��JDK�Դ�keystore���洢map entry[private RSA key, public certificate]��
��׼X.509�淶֧��PKCS12��ʽ, ֤���׺��".p12"��JDK֧��X.509

��ʵ�ϵ�certificate��key��ʽ��׼PEM(Privacy-enhanced Electronic Mail), ֤���׺��".pem",".cer",".crt",��˽Կ��׺��".key"
��ʱ��Ҳ�Ὣ��Կ��֤�鶼����һ��pem���ļ���

���Ҫ��pem��ʽ֤�鵼��JDK keystore����ת����PKCS12��ʽ��Ȼ�����ͨ��JDK�ṩ��keytool����
- [certificate-transform](https://www.digitalocean.com/community/tutorials/java-keytool-essentials-working-with-java-keystores)

## *JDK keytool*

* Create java keystore repo
```shell
# create keystore.jks in current folder, meanwhile generate a key pair within alias name 'controller'
root@photon# keytool -keystore keystore.jks -genkey -alias controller
# same as command above, specially designate generation with algorithm RSA
root@photon# keytool -keystore ./keystore.jks -genkeypair -alias controller -keyalg RSA
Enter keystore password:  
Re-enter new password: 
What is your first and last name?
  [Unknown]:  
What is the name of your organizational unit?
  [Unknown]:  
What is the name of your organization?
  [Unknown]:  
What is the name of your City or Locality?
  [Unknown]:  
What is the name of your State or Province?
  [Unknown]:  
What is the two-letter country code for this unit?
  [Unknown]:  
Is CN=Unknown, OU=Unknown, O=Unknown, L=Unknown, ST=Unknown, C=Unknown correct?
  [no]:  yes

Enter key password for <controller>
        (RETURN if same as keystore password):  


#The default JKS keystore uses a proprietary format. It is recommended 
#to migrate to PKCS12 which is an industry standard format
root@photon# keytool -importkeystore -srckeystore ./keystore.jks -destkeystore ./keystore.jks -deststoretype pkcs12
```
* List keystore repo content
```shell
root@photon# keytool -list -keystore ./keystore.jks
Enter keystore password:  
Keystore type: JKS
Keystore provider: SUN

Your keystore contains 1 entry

controller, Jan 16, 2018, PrivateKeyEntry, 
Certificate fingerprint (SHA1): 4A:6B:B8:FF:9B:9B:A0:17:3C:91:BA:DD:54:DC:50:CA:23:60:72:56
```

* Import the PKCS12 file into the Java keystore
``` bash
$ keytool -importkeystore -srckeystore /tmp/hostname.p12 -srcstoretype PKCS12 \
-srcstorepass <password> -alias hostname -deststorepass \
-destkeypass <password> -destkeystore /opt/cloudera/security/jks/hostname-keystore.jks

$ keytool -v -importkeystore -srckeystore eneCert.pkcs12 -srcstoretype PKCS12 \
-destkeystore keystore.jks -deststoretype JKS

root@photon# keytool -v -importkeystore -srckeystore b.pkcs12 -srcstoretype PKCS12 -srcstorepass 123456 \
-alias controller -deststorepass 123456 -destkeystore keystore.jks
```

* Export the private key and certificate from JKS to PKCS12
``` bash
$ keytool -importkeystore -srckeystore /opt/cloudera/security/jks/hostname-keystore.jks \
-srcstorepass <password> -srckeypass <password> -destkeystore /tmp/hostname-keystore.p12 \
-deststoretype PKCS12 -srcalias hostname -deststorepass <password> -destkeypass <password>
```

* Delete Alias
``` bash
$ keytool -delete -alias controller -keystore keystore.jks
```

* Rename Alias
``` bash
$ keytool -changealias -alias <old> -destalias <new> -keystore keystore.jks

# ��aliasΪ1��entry��Ϊcontroller
$ keytool -changealias -alias 1 -destalias controller -keystore keystore.jks

# �޸�ǰentry
Keystore type: JKS
Keystore provider: SUN

Your keystore contains 1 entry

1, Jan 16, 2018, PrivateKeyEntry, 
Certificate fingerprint (SHA1): AA:B8:4E:7A:0E:D8:D8:A9:48:1A:37:EC:13:D0:C7:42:35:56:E9:19

# �޸ĺ�entry
Keystore type: JKS
Keystore provider: SUN

Your keystore contains 1 entry

controller, Jan 16, 2018, PrivateKeyEntry, 
Certificate fingerprint (SHA1): AA:B8:4E:7A:0E:D8:D8:A9:48:1A:37:EC:13:D0:C7:42:35:56:E9:19

```

## *OpenSSL pkcs12*
* Create p12 keystore importing from pem file
``` bash
#The generated KeyStore is eneCert.pkcs12 with an entry specified by the myAlias alias.
#This entry contains the private key and the certificate provided by the -in argument.
$ openssl pkcs12 -export -out eneCert.pkcs12 -in eneCert.pem

#Generate a PKCS12 KeyStore with the private key and certificate from one pem file.
#The noiter and nomaciter options must be specified to allow the generated 
#KeyStore to be recognized properly by JSSE.
$ cat mykey.pem.txt mycertificate.pem.txt>mykeycertificate.pem.txt
$ openssl pkcs12 -export -in mykeycertificate.pem.txt -out mykeystore.pkcs12 -name -noiter -nomaciter

#Generate a PKCS12 KeyStore with the private key and certificate from different pem files
$ openssl pkcs12 -export -in certificate.pem -inkey rsakey.pem \
  -out /tmp/cert.p12 -name -passin pass:<password> -passout pass:<password>
$ openssl pkcs12 -export -in cert.pem -inkey key.pem \
-out b.p12 -name controller -passin pass:123456 -passout pass:123456
```

* Extract stuffs from PKCS Keystore
```bash
# only extract private key from PKCS12 Keystore 
$ openssl pkcs12 -in /tmp/hostname-keystore.p12 -passin pass:<password> \
   -nocerts -out /opt/cloudera/security/pki/hostname.key -passout pass:<password>

# only extract the certificate file from PKCS12 file
$ openssl pkcs12 -in /tmp/hostname-keystore.p12 -passin pass:password \
  -nokeys -out /opt/cloudera/security/pki/hostname.pem
```

## *OpenSSL x509*
- [pem format](https://www.digicert.com/ssl-support/pem-ssl-creation.htm)
* Converting DER Encoded Certificates to PEM
``` bash
$ openssl x509 -inform der -in /opt/cloudera/security/pki/hostname.cer -out /tmp/hostname.pem
```
* Converting PEM Encoded Certificates to DER
```bash
$ openssl x509 -outform der -in certificate.pem -out certificate.der
```

## *Show certificate*
```bash
# �����ı���ʽչʾ֤������
$ openssl x509 -in certificate.pem -text
$ openssl x509 -in MYCERT.crt -text

# ��ӡ֤���ı���ʽ
$ keytool -printcert -file certificate.pem
$ keytool -printcert -file MYCERT.crt

# �����ı���ʽչʾder֤������
$ openssl x509 -in MYCERT.der -inform der -text

# �鿴Զ��server��֤����
$ echo -n | openssl s_client -connect scan.coverity.com:443
# ��Զ��server��֤������д�뱾���ļ�
$ echo -n | openssl s_client -connect scan.coverity.com:443 \
| sed -ne '/-BEGIN CERTIFICATE-/,/-END CERTIFICATE-/p' | sudo tee -a /etc/ssl/certs/ca-
```