package ru.dimon;

import static ru.dimon.SFTPFileTransferWithKey.uploadFileToSFTPWithKey;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "App started!" );
        // Пример использования
        String localFilePath = "c:/tmp2/testcopy.txt"; // Локальный путь к файлу
        String remoteDirectory = "/share"; // Папка на удаленном сервере
        String sftpHost = "localhost"; // Адрес SFTP-сервера
        int sftpPort = 2222; // Порт SFTP-сервера
        String sftpUsername = "user"; // Имя пользователя
        String privateKeyPath = "c:/tmp/id_rsa"; // Путь к приватному SSH-ключу

        uploadFileToSFTPWithKey(localFilePath, remoteDirectory, sftpHost, sftpPort, sftpUsername, privateKeyPath);
        System.out.println( "App ended!" );
    }
}
