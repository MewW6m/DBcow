FROM mysql:5.7

EXPOSE 3306
# 設定ファイルをコンテナにコピー
COPY ./my.cnf /etc/mysql/my.cnf
# 設定ファイルの権限を変更
RUN chmod 644 /etc/mysql/my.cnf
# データの初期化を行うDDLをコンテナにコピー
COPY ./dbcow/src/main/resources/create.sql ./sql_init/.
COPY ./dbcow/src/main/resources/data.sql ./sql_init/.
COPY ./sql_init /docker-entrypoint-initdb.d
