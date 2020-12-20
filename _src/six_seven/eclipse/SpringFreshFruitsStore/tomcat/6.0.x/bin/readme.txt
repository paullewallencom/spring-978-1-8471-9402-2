To enable JMX exposure in tomcat add:

set JAVA_OPTS=-Dcom.sun.management.jmxremote.port=7009 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false

in catalina.bat or catalina.sh