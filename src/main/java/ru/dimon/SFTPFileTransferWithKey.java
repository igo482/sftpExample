package ru.dimon;

import com.jcraft.jsch.*;

import java.io.File;
import java.io.FileInputStream;


public class SFTPFileTransferWithKey {

    /**
     * Метод для отправки файла на сервер через SFTP с использованием SSH-ключа.
     *
     * @param filePath       Путь к локальному файлу, который нужно отправить.
     * @param remotePath     Путь на удаленном сервере, куда нужно поместить файл.
     * @param sftpHost       Адрес хоста SFTP-сервера.
     * @param sftpPort       Порт SFTP-сервера (обычно 22).
     * @param sftpUsername   Имя пользователя для подключения.
     * @param privateKeyPath Путь к приватному SSH-ключу.
     */
    public static void uploadFileToSFTPWithKey(String filePath, String remotePath, String sftpHost, int sftpPort,
                                               String sftpUsername, String privateKeyPath) {
        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;

        try {
            JSch jsch = new JSch();
            // Добавляем приватный SSH-ключ для аутентификации
            jsch.addIdentity(privateKeyPath);
            session = jsch.getSession(sftpUsername, sftpHost, sftpPort);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp) channel;

            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);
//            channelSftp.put(fileInputStream, remotePath + File.separator + file.getName());
            channelSftp.put(fileInputStream, remotePath + "/" + file.getName());

            System.out.println("Файл успешно загружен: " + file.getName());
        } catch (JSchException e) {
            System.out.println("Ошибка при подключении к SFTP-серверу: " + e.getMessage());
        } catch (SftpException e) {
            System.out.println("Ошибка при загрузке файла: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        } finally {
            // Закрываем соединение
            if (channelSftp != null && channelSftp.isConnected()) {
                channelSftp.disconnect();
            }
            if (channel != null && channel.isConnected()) {
                channel.disconnect();
            }
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
    }

}
