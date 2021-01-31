package edu.epam.web.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

public class FileUploader {

    public static final Logger logger = LogManager.getLogger(FileUploader.class);
    public static final String FILE_PATH = "file";

    public boolean uploadFile(HttpServletRequest request) throws Exception{
        ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
        List<FileItem> multipleFiles;
        boolean isUploaded = false;
        try {
            multipleFiles = sfu.parseRequest(request);
            for (FileItem item : multipleFiles) {
                item.write(new File(FILE_PATH));
            }
            isUploaded = true;
        } catch (FileUploadException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return isUploaded;
    }
}
