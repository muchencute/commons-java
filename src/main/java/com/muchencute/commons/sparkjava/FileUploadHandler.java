package com.muchencute.commons.sparkjava;

import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.util.*;
import java.util.function.UnaryOperator;

/**
 * 文件上传请求 Handler
 *
 * @author miguoliang
 */
public class FileUploadHandler {

    private static final String FIELD_NAME = "file";

    private final String mFieldName;

    private final HttpServletRequest mRequest;

    private final File mDestination;

    private final Set<String> mExtensions;

    private final boolean mMultiple;

    public FileUploadHandler(HttpServletRequest req, File destination, Set<String> extensions) {

        this(req, destination, extensions, FIELD_NAME, false);
    }

    public FileUploadHandler(final HttpServletRequest req, final File destination, final Set<String> extensions,
                             final String fieldName, final boolean multiple) {

        this.mFieldName = fieldName;
        this.mRequest = req;
        this.mDestination = destination;
        this.mMultiple = multiple;

        extensions.forEach(s -> s = s.toLowerCase());
        this.mExtensions = extensions;
    }

    public List<File> handle(UnaryOperator<String> filenameOperator) throws IOException, ServletException {

        List<File> files = new LinkedList<>();

        for (Part part : mRequest.getParts()) {
            if (part.getName().equalsIgnoreCase(mFieldName) &&
                    mExtensions.contains(FilenameUtils.getExtension(part.getSubmittedFileName()).toLowerCase())) {
                files.add(persistOnDisk(part, filenameOperator));
                if (!mMultiple) {
                    break;
                }
            }
        }

        return files;
    }

    private File persistOnDisk(Part part, UnaryOperator<String> filenameOperator) throws IOException {

        Set<PosixFilePermission> defaultPermissions = new HashSet<PosixFilePermission>() {{
            add(PosixFilePermission.OWNER_READ);
            add(PosixFilePermission.OWNER_WRITE);
            add(PosixFilePermission.GROUP_READ);
            add(PosixFilePermission.OTHERS_READ);
        }};

        final String filename = filenameOperator.apply(part.getSubmittedFileName());
        final Path target = Paths.get(mDestination.getAbsolutePath(), filename);
        Files.copy(part.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        Files.setPosixFilePermissions(target, defaultPermissions);

        return new File(mDestination, filename);
    }

}
