## AQUI DESCARGAMOS LA VERSION DE WILDFLY
FROM jboss/wildfly:latest

# ENV = Variables de entorno, creamos las variables para la adminsitracion
ENV Usuario john
ENV Contra john0105598254
# Varaibles para la base de datos
ENV DB_NOMBRE SistemsDistribuidos
ENV DB_USUARIO john
ENV DB_CONTRA john0105598254
ENV DB_URI dockerdb:3306

#Declarando Variables de versiones y drivers
ENV MYSQL_VERSION 8.0.23
ENV JBOSS_CLI /opt/jboss/wildfly/bin/jboss-cli.sh
ENV DEPLOYMENT_DIR /opt/jboss/wildfly/standalone/deployments/

# Seteando las variables de wildfly
RUN $JBOSS_HOME/bin/add-user.sh -u $Usuario -p $Contra --silent


# Configure Wildfly server
RUN echo "=> Starting WildFly server" && \
      bash -c '$JBOSS_HOME/bin/standalone.sh &' && \
    echo "=> Waiting for the server to boot" && \
      bash -c 'until `$JBOSS_CLI -c ":read-attribute(name=server-state)" 2> /dev/null | grep -q running`; do echo `$JBOSS_CLI -c ":read-attribute(name=server-state)" 2> /dev/null`; sleep 1; done' && \
    echo "=> Downloading MySQL driver" && \
      curl --location --output /tmp/mysql-connector-java-${MYSQL_VERSION}.jar --url http://search.maven.org/remotecontent?filepath=mysql/mysql-connector-java/${MYSQL_VERSION}/mysql-connector-java-${MYSQL_VERSION}.jar && \
    echo "=> Adding MySQL module" && \
      $JBOSS_CLI --connect --command="module add --name=com.mysql.driver --dependencies=javax.api,javax.transaction.api --resources=/tmp/mysql-connector-java-${MYSQL_VERSION}.jar" && \
    echo "=> Adding MySQL driver" && \
      $JBOSS_CLI --connect --command="/subsystem=datasources/jdbc-driver=mysql/:add(driver-module-name="com.mysql.driver",driver-name="mysql",driver-class-name=com.mysql.cj.jdbc.Driver)" && \
    echo "=> Creating a new datasource" && \
      $JBOSS_CLI --connect --command="data-source add \
        --name=${DB_NOMBRE}DS \
        --jndi-name=java:/${DB_NOMBRE}DS \
        --user-name=${DB_USUARIO} \
        --password=${DB_CONTRA} \
        --driver-name=mysql \
        --connection-url=jdbc:mysql://${DB_URI}/${DB_NOMBRE} \
        --use-ccm=false \
        --min-pool-size=10 \
		--max-pool-size=20 \
        --blocking-timeout-wait-millis=5000 \
        --enabled=true" && \
    echo "=> Shutting down WildFly and Cleaning up" && \
      $JBOSS_CLI --connect --command=":shutdown" && \
      rm -rf $JBOSS_HOME/standalone/configuration/standalone_xml_history/ $JBOSS_HOME/standalone/log/* && \
      rm -f /tmp/*.jar

# Expose http and admin ports
EXPOSE 8080 
EXPOSE 9990
COPY maven/${project.build.finalName}.${project.packaging} /opt/jboss/wildfly/standalone/deployments/
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]