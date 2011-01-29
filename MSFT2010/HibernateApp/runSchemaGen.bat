set SGCP=.
set SGCP=%SGCP%;lib\antlr-2.7.6.jar
set SGCP=%SGCP%;lib\commons-collections-3.1.jar
set SGCP=%SGCP%;lib\dom4j-1.6.1.jar
set SGCP=%SGCP%;lib\hibernate3.jar
set SGCP=%SGCP%;lib\javassist-3.9.0.GA.jar
set SGCP=%SGCP%;lib\jta-1.1.jar
set SGCP=%SGCP%;lib\slf4j-api-1.5.8.jar
set SGCP=%SGCP%;lib\slf4j-nop-1.5.11.jar

copy ..\application.cfg.xml .
copy ..\appmappings.hbm.xml .
java -classpath %SGCP% org.hibernate.tool.hbm2ddl.SchemaExport --text --output=app.sql --config=hibernate.cfg.xml

set SGCP=
