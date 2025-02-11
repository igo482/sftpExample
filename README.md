# sftpExample
Пример работы с sftp сервером, подключение по закрытому ssh ключу

Для запуска сервера SFTP:

docker run -v c:/tmp/id_rsa.pub:/home/user/.ssh/keys/id_rsa.pub:ro -v c:/tmp/share:/home/user/share -e SFTP_USERS="user:pas:1001" -p 2222:22 -d atmoz/sftp

Ключи в папки ./keys