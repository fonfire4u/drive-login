# kodi-login
Edit for local login

I tested on windows 10.

Before run.
Install JAVA JDK.
and add Windows Environment Variables of
JAVA_HOME : Folder of JDK 
Example : C:\Program Files\Java\jdk-12.0.2

Test MVN Environment
on project folder run mvnw -v
it should be show like this.

Apache Maven 3.5.0 (ff8f5e7444045639af65f6095c62210b5713f426; 2017-04-04T02:39:06+07:00)
Maven home: C:\Users\xxxx\.m2\wrapper\dists\apache-maven-3.5.0-bin\6ps54u5pnnbbpr6ds9rppcc7iv\apache-maven-3.5.0
Java version: 12.0.2, vendor: Oracle Corporation
Java home: C:\Program Files\Java\jdk-12.0.2
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"


Step to run
1. change server url on addon setting => http://xx.xx.xx.xx:8080
2. update CLIENT_ID and CLIENT_SECRET on src/main/resources/application.properties 
3. run run.bat
4. Add account on addon
5. goto http://xx.xx.xx.xx:8080 for authen
