FROM library/tomcat:latest

COPY file.txt /var/tmp/

ADD counter-api.war /usr/local/tomcat/webapps/

CMD "catalina.sh" "run"

EXPOSE 80

EXPOSE 8080

EXPOSE 24
