package com.mycompany.filewebservice;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import javax.jws.WebService;

@WebService(endpointInterface = "com.mycompany.filewebservice.IFileWebService", serviceName = "FileWeb")
public class FileWebServiceImpl implements IFileWebService {

    private static final String DIRECTORY_NAME = "Documents";
    private static final String DIRECTORY_PATH = getProjectPath() + File.separator + DIRECTORY_NAME;

    private static String getProjectPath() {
        String classFilePath = FileWebServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String decodedPath = decodePath(classFilePath);
        int endIndex = decodedPath.indexOf("/Client-Server-Architecture") + 28;

        File projectDir = new File(decodedPath.substring(0, endIndex));

        return projectDir.getAbsolutePath();
    }

    private static String decodePath(String path) {
        try {
            return URLDecoder.decode(path, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return path;
        }
    }

    @Override
    public String[] getListFiles() {
        File directory = new File(DIRECTORY_PATH);
        if (directory.exists() && directory.isDirectory()) {
            return directory.list();
        }
        return new String[0];
    }

    @Override
    public byte[] getFile(String filePath) {
        File file = new File(DIRECTORY_PATH+File.separator+filePath);
        if (file.exists() && file.isFile()) {
            try {
                return Files.readAllBytes(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new byte[0];
    }
}
