package com.muchencute.commons.sparkjava;

import org.apache.commons.codec.digest.DigestUtils;
import spark.Request;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 文件上传请求 Handler
 */
public class FileUploadHandler {

    private boolean mErrorOccurred = false;

    private String mErrorMessage = "";

    private String mFilename = "";

    private String mNewFilename = "";

    public FileUploadHandler(Request req, String storagePath, int maxFileSize,
                             ArrayList<String> allowedExtensionName) {

        this(req, storagePath, maxFileSize, allowedExtensionName, "file", true);
    }

    public FileUploadHandler(Request req, String storagePath, int maxFileSize, ArrayList<String> allowedExtensionName,
                             String formName, boolean useOriginFilename) {

        req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement(storagePath));

        Part part;
        try {
            part = req.raw().getPart(formName);
        } catch (IOException | ServletException e) {
            mErrorOccurred = true;
            mErrorMessage = e.getLocalizedMessage();
            e.printStackTrace();
            return;
        }

        if (part == null) {
            mErrorOccurred = true;
            mErrorMessage = "no requested part";
            return;
        }

        InputStream inputStream;
        try {
            inputStream = part.getInputStream();
            mFilename = getFileName(part);
            String extension = getFileExt(mFilename != null ? mFilename : "");

            if (!allowedExtensionName.contains(extension.toLowerCase())) {
                mErrorOccurred = true;
                mErrorMessage = "Not allowed file type";
                return;
            }

            int fileSize = inputStream.available();

            if (fileSize < 0 || fileSize > maxFileSize) {
                mErrorOccurred = true;
                mErrorMessage = "Invalid file size";
                return;
            }

            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedInputStream.mark(0);

            if (!useOriginFilename) {
                mNewFilename = String.format("%s%s",
                        DigestUtils.md5Hex(bufferedInputStream), extension);
            } else {
                mNewFilename = mFilename;
            }

            Path target = Paths.get(storagePath, mNewFilename);
            inputStream.reset();
            Files.copy(bufferedInputStream, target, StandardCopyOption.REPLACE_EXISTING);

            Set<PosixFilePermission> defaultPermissions = new HashSet<>();
            defaultPermissions.add(PosixFilePermission.OWNER_READ);
            defaultPermissions.add(PosixFilePermission.OWNER_WRITE);
            defaultPermissions.add(PosixFilePermission.GROUP_READ);
            defaultPermissions.add(PosixFilePermission.OTHERS_READ);

            Files.setPosixFilePermissions(target, defaultPermissions);

        } catch (IOException e) {
            mErrorOccurred = true;
            mErrorMessage = e.getLocalizedMessage();
            e.printStackTrace();
        }
    }

    private static String getFileName(Part part) {

        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private static String getFileExt(String name) {
        // 包含点 (.)
        return name.substring(name.lastIndexOf('.'));
    }

    public String getErrorMessage() {

        return mErrorMessage;
    }

    public boolean isErrorOccurred() {

        return mErrorOccurred;
    }

    public String getNewFilename() {

        return mNewFilename;
    }

    public String getFilename() {

        return mFilename;
    }
}
