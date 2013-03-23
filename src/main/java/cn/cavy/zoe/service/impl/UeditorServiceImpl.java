package cn.cavy.zoe.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadBase.InvalidContentTypeException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.cavy.common.util.RequestUtil;
import cn.cavy.common.util.StringUtil;
import cn.cavy.zoe.action.vo.UploadResult;
import cn.cavy.zoe.exception.UploadException;
import cn.cavy.zoe.service.UeditorService;

@Service("uploadService")
public class UeditorServiceImpl implements UeditorService {

    Logger log = LoggerFactory.getLogger(UeditorServiceImpl.class);

    public static final String DEFAULT_UPLOAD_PATH = "upload";

    // 单位:kb
    public static final int DEFAULT_MAX_SIZE = 10 * 1024;

    public static final String DEFAULT_ENCODING = "utf-8";

    public static final String STATE_NOFILE = "NOFILE";

    public static final String STATE_TYPE = "TYPE";

    public static final String STATE_SUCCESS = "SUCCESS";

    private static final String[] IMG_FILE_TYPE = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };

    private static final String PARAM_PICTITLE = "Filename";

    public UploadResult upload(HttpServletRequest request) {
        UploadResult result = new UploadResult();

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            throw new UploadException(UploadException.NOFILE);
        }

        DiskFileItemFactory dff = new DiskFileItemFactory();
        dff.setRepository(getUploadDir(request));
        try {
            String pictitle = request.getParameter(PARAM_PICTITLE);

            ServletFileUpload sfu = new ServletFileUpload(dff);
            sfu.setSizeMax(getMaxSize());
            sfu.setHeaderEncoding(DEFAULT_ENCODING);
            FileItemIterator fii = sfu.getItemIterator(request);
            while (fii.hasNext()) {
                FileItemStream fis = fii.next();
                if (!fis.isFormField()) {
                    // 上传的文件名
                    result.setOriginal(fis.getName().substring(
                            fis.getName().lastIndexOf(System.getProperty("file.separator")) + 1));

                    if (!checkFileType(result.getOriginal())) {
                        result.setState(STATE_TYPE);
                        continue;
                    }

                    // 上传后的文件名
                    String fileName = this.getName(result.getOriginal(), pictitle);
                    // 文件的访问路径
                    result.setUrl(this.getUrl(request, fileName));

                    BufferedInputStream in = new BufferedInputStream(fis.openStream());
                    FileOutputStream out = new FileOutputStream(this.getUploadFile(request, fileName));
                    BufferedOutputStream output = new BufferedOutputStream(out);
                    Streams.copy(in, output, true);
                    result.setState(STATE_SUCCESS);
                    // UE中只会处理单张上传，完成后即退出
                    break;
                }
            }
        } catch (SizeLimitExceededException e) {
            log.error(e.getMessage(), e);
            throw new UploadException(UploadException.SIZE);
        } catch (InvalidContentTypeException e) {
            log.error(e.getMessage(), e);
            throw new UploadException(UploadException.ENTYPE);
        } catch (FileUploadException e) {
            log.error(e.getMessage(), e);
            throw new UploadException(UploadException.REQUEST);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new UploadException(UploadException.UNKNOWN);
        }
        return result;
    }

    /**
     * 获取上传到的目标文件
     * 
     * @param request
     * @param fileName
     * @return
     */
    private File getUploadFile(HttpServletRequest request, String fileName) {
        File file = new File(RequestUtil.getPhysicalPath(request, this.getUploadPath()) + "/" + fileName);
        if (file.exists())
            throw new UploadException(UploadException.EXISTS);
        return file;
    }

    /**
     * 获取上传文件的访问URL
     * 
     * @param request
     * @param fileName
     * @return
     */
    private String getUrl(HttpServletRequest request, String fileName) {
        return request.getContextPath() + "/" + this.getUploadPath() + "/" + fileName;
    }

    /**
     * 获取上传文件的目录，如果不存在则创建
     * 
     * @param request
     * @return
     */
    private File getUploadDir(HttpServletRequest request) {
        String uploadPath = RequestUtil.getPhysicalPath(request, this.getUploadPath());
        File dir = new File(uploadPath);
        if (!dir.exists()) {
            try {
                dir.mkdirs();
            } catch (Exception e) {
                throw new UploadException(UploadException.DIR);
            }
        }
        return dir;
    }

    /**
     * 依据原始文件名生成新文件名
     * 
     * @param pictitle
     * 
     * @return string
     */
    private String getName(String fileName, String pictitle) {
        if (StringUtil.isEmpty(pictitle))
            return fileName;
        // return "" + System.currentTimeMillis() + this.getFileExt(fileName);
        return pictitle + this.getFileExt(fileName);
    }

    /**
     * 获取文件扩展名
     * 
     * @return string
     */
    private String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    private boolean checkFileType(String fileName) {
        Iterator<String> type = Arrays.asList(IMG_FILE_TYPE).iterator();
        while (type.hasNext()) {
            String ext = type.next();
            if (fileName.toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    private int getMaxSize() {
        return DEFAULT_MAX_SIZE * 1024;
    }

    private String getUploadPath() {
        return DEFAULT_UPLOAD_PATH + "/" + new SimpleDateFormat("yyyyMMdd").format(new Date());
    }
}