/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gs.tools.net.core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

/**
 * The Class FtpClientHelper.
 *
 * @author sabuj.das
 */
public class FtpClientHelper {

    /** The Constant logger. */
    private static final Logger logger = Logger.getLogger(FtpClientHelper.class);
    private static final String FILE_SEPARATOR;

    static {
        FILE_SEPARATOR = "/";//System.getProperty("file.separator");
    }

    /**
     * Connect.
     *
     * @param ftpHostName the ftp host name
     * @param userName the user name
     * @param password the password
     * @return the fTP client
     * @throws SocketException the socket exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public FTPClient connect(String ftpHostName, String userName,
            String password) throws SocketException, IOException {
        if (logger.isDebugEnabled()) {
            logger.debug("Connecting to : " + ftpHostName);
            logger.debug("User Name : " + userName);
        }
        FTPClient ftpClient = new FTPClient();
        ftpClient.setDefaultTimeout(60000);
        ftpClient.connect(ftpHostName);
        int reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftpClient.disconnect();
            if (logger.isDebugEnabled()) {
                logger.debug("FTP server refused connection.");
            }
            return null;
        }

        // Use passive mode as default because most of us are
        // behind firewalls these days.
        ftpClient.enterLocalPassiveMode();

        //ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

        if (ftpClient.login(userName, password)) {
            if (logger.isDebugEnabled()) {
                logger.debug("Login successful.");
            }
            return ftpClient;
        }
        return null;
    }

    /**
     * Disconnect.
     *
     * @param ftpClient the ftp client
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void disconnect(FTPClient ftpClient) throws IOException {
        if (null != ftpClient) {
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }

    /**
     * Download.
     *
     * @param ftpClient the ftp client
     * @param remotePathName the remote path name
     * @param localLocation the local location
     * @throws SocketException the socket exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void download(FTPClient ftpClient, String remotePathName,
            String localLocation) throws SocketException, IOException {
        download(ftpClient, remotePathName, localLocation, true);
    }

    /**
     * Download.
     *
     * @param ftpClient the ftp client
     * @param remotePathName the remote path name
     * @param localLocation the local location
     * @param isDirectory the is directory
     * @throws SocketException the socket exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void download(FTPClient ftpClient, String remotePathName,
            String localLocation, boolean isDirectory) throws SocketException, IOException {
        if (logger.isDebugEnabled()) {
            logger.debug("Download from : " + remotePathName + " To: " + localLocation);
        }
        if (null == ftpClient) {
            return;
        }
        if (!ftpClient.isConnected()) {
            return;
        }
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        FTPFile ftpfile = new FTPFile();
        ftpfile.setRawListing(remotePathName);
        FTPFile[] remoteFiles = ftpClient.listFiles(remotePathName);

        if (remotePathName != null && remotePathName.trim().length() > 0 && isDirectory) {
            ftpClient.changeWorkingDirectory(remotePathName);
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                throw new IOException("Unable to change working directory " + "to:" + remotePathName);
            }
        }

        if (null != remoteFiles && isDirectory) {
            FileOutputStream outputStream = null;
            for (FTPFile ftpFile : remoteFiles) {
                /*System.out.println(" Permission  " + ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION));
                System.out.println(" Permission  " + ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION));
                System.out.println(" Permission  " + ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION));*/

                boolean retValue = false;
                if (!ftpFile.isDirectory()) {

                    if (logger.isDebugEnabled()) {
                        logger.debug("file block : ");
                    }

                    downloadData(ftpClient,
                            remotePathName + FILE_SEPARATOR + ftpFile.getName(),
                            localLocation + FILE_SEPARATOR + ftpFile.getName());

                    /*retValue = ftpClient.retrieveFile(ftpFile.getName(), outputStream);
                    if (!retValue) {
                    throw new IOException("Downloading of remote file "
                    + ftpFile.getName()
                    + " failed. retrieveFile() returned false.");
                    }*/
                    ftpClient.completePendingCommand();
                } else {

                    if (logger.isDebugEnabled()) {
                        logger.debug("directory block : ");
                    }

                    File localDir = new File(localLocation + FILE_SEPARATOR
                            + ftpFile.getName());
                    if (!localDir.exists()) {
                        localDir.mkdirs();
                    }
                    download(ftpClient, remotePathName + FILE_SEPARATOR
                            + ftpFile.getName(), localLocation + FILE_SEPARATOR
                            + ftpFile.getName());
                }
            }
        } else {

            String localPathName = localLocation + ((remotePathName.indexOf("/") < 0) ? "/" + remotePathName
                    : remotePathName.substring(remotePathName.lastIndexOf("/")));

            //boolean retValue = ftpClient.retrieveFile(remotePathName, outputStream);

            downloadData(ftpClient, remotePathName, localPathName);

            ftpClient.completePendingCommand();
        }

    }

    /**
     * Download data.
     *
     * @param ftpClient the ftp client
     * @param remotePathName the remote path name
     * @param localPathName the local path name
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void downloadData(FTPClient ftpClient, String remotePathName,
            String localPathName) throws IOException {

        if (logger.isDebugEnabled()) {
            logger.debug("remotePathName : " + remotePathName + " and localPathName :" + localPathName + " in downloaddata");
        }

        BufferedInputStream remoteInputStream = null;
        BufferedOutputStream localOutputStream = null;
        try {
            remoteInputStream = new BufferedInputStream(ftpClient.retrieveFileStream(remotePathName));
            localOutputStream = new BufferedOutputStream(new FileOutputStream(localPathName));
            int bytesRead = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((bytesRead = remoteInputStream.read(buffer)) != -1) {
                localOutputStream.write(buffer, 0, bytesRead);
            }
            if (logger.isDebugEnabled()) {
                logger.debug("file downloaded successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("Error while downloading files :" + e.getMessage());
        } finally {
            if (null != remoteInputStream) {
                remoteInputStream.close();
            }
            if (null != localOutputStream) {
                localOutputStream.close();
            }
        }
    }

    /**
     * Upload.
     *
     * @param ftpClient the ftp client
     * @param localPathName the local path name
     * @param remoteLocation the remote location
     * @throws SocketException the socket exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void upload(FTPClient ftpClient, String localPathName,
            String remoteLocation) throws SocketException, IOException {
        if (null == ftpClient) {
            return;
        }
        if (!ftpClient.isConnected()) {
            return;
        }
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        FTPFile ftpfile = new FTPFile();
        ftpfile.setRawListing(remoteLocation);

        if (remoteLocation != null && remoteLocation.trim().length() > 0) {
            ftpClient.changeWorkingDirectory(remoteLocation);
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.makeDirectory(remoteLocation);
                ftpClient.changeWorkingDirectory(remoteLocation);
                reply = ftpClient.getReplyCode();
                if (!FTPReply.isPositiveCompletion(reply)) {
                    throw new IOException("Unable to change working directory " + "to:" + remoteLocation);
                }
            }

        }


        File localPath = new File(localPathName);
        File[] localFiles = localPath.listFiles();
        if (null != localFiles) {
            for (File file : localFiles) {
                if (file.isDirectory()) {
                    ftpClient.makeDirectory(remoteLocation + FILE_SEPARATOR + file.getName());
                    upload(ftpClient, localPathName + FILE_SEPARATOR + file.getName(), remoteLocation + FILE_SEPARATOR + file.getName());
                } else {
                    ftpClient.storeFile(remoteLocation + FILE_SEPARATOR + file.getName(), new FileInputStream(file));
                }
            }
        } else {
            ftpClient.makeDirectory(remoteLocation);
            ftpClient.storeFile(remoteLocation + FILE_SEPARATOR + localPath.getName(), new FileInputStream(localPath));
        }

    }

    /**
     * Copy to.
     *
     * @param ftpClient the ftp client
     * @param remoteDestination the remote destination
     * @param remoteSource the remote source
     * @param fileNames the file names
     * @throws SocketException the socket exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Deprecated
    public void copyTo(FTPClient ftpClient, String remoteDestination, String remoteSource, String... fileNames) throws SocketException, IOException {
        if (null == ftpClient) {
            return;
        }
        if (!ftpClient.isConnected()) {
            return;
        }

        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.makeDirectory(remoteDestination);


    }

    /**
     * Move to.
     *
     * @param ftpClient the ftp client
     * @param remoteDestination the remote destination
     * @param remoteSource the remote source
     * @param fileNames the file names
     * @throws SocketException the socket exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void moveTo(FTPClient ftpClient, String remoteDestination, String remoteSource, String... fileNames) throws SocketException, IOException {


        if (null == ftpClient) {
            return;
        }
        if (!ftpClient.isConnected()) {
            return;
        }

        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.makeDirectory(remoteDestination);

        if (!remoteSource.endsWith("/")) {
            remoteSource += "/";
        }
        if (!remoteDestination.endsWith("/")) {
            remoteDestination += "/";
        }

        if (null != fileNames && fileNames.length > 0) {
            for (String fileName : fileNames) {
                boolean isFileMoved = ftpClient.rename(remoteSource + fileName, remoteDestination + fileName);
                if (!isFileMoved) {
                    ftpClient.deleteFile(remoteSource + fileName);
                }
            }
        } else {
            FTPFile[] remoteFiles = ftpClient.listFiles(remoteSource);

            for (FTPFile ftpFile : remoteFiles) {
                if (ftpFile.isDirectory()) {
                    moveTo(ftpClient, remoteDestination + ftpFile.getName(), remoteSource + ftpFile.getName(), null);
                } else {
                    boolean isFileMoved = ftpClient.rename(remoteSource + ftpFile.getName(), remoteDestination + ftpFile.getName());
                    if (!isFileMoved) {
                        ftpClient.deleteFile(remoteSource + ftpFile.getName());
                    }
                }
            }

        }
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        FtpClientHelper client = new FtpClientHelper();
        FTPClient ftpClient = null;
        try {
            ftpClient = client.connect("146.215.153.181", "dhemant", "newpw123");

            //client.upload(ftpClient,"D:\\TEMP\\opinio\\report", "/opinio-support/report/");
            //client.download(ftpClient, "/opinio-support/","D:\\TEMP\\123test", true);

            //SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");

            //String dest = "/opinio/test002/" + dateFormat.format(new Date());

            client.moveTo(ftpClient, "/opinio-support/report/LA/TestData/ARC/", "/opinio-support/report/LA/TestData/TV/", null);

        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                client.disconnect(ftpClient);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
