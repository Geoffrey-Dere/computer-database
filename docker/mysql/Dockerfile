from mysql:5.5

ENV MYSQL_ROOT_PASSWORD=pass

ADD 1-SCHEMA.sql /docker-entrypoint-initdb.d/1-SCHEMA.sql
ADD 2-PRIVILEGES.sql /docker-entrypoint-initdb.d/2-PRIVILEGES.sql
ADD 3-ENTRIES.sql /docker-entrypoint-initdb.d/3-ENTRIES.sql

EXPOSE 3306

#docker run -d --name=mysql-prod --net=prod-network --ip=192.168.0.56 mysqlprod
