FROM mysql
ENV MYSQL_DATABASE book_library
ENV MYSQL_USER book_library
ENV MYSQL_PASSWORD book_library
ENV MYSQL_ROOT_PASSWORD book_library
COPY ./docker-files/mysql/schema-generation-mysql.sql /docker-entrypoint-initdb.d/
