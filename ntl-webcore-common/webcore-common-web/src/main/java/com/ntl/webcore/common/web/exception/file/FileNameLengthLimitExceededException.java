package com.ntl.webcore.common.web.exception.file;

import com.ntl.webcore.common.web.exception.base.BaseException;

/**
 * 文件名称超长限制异常类
 * 
 * 
 */
public class FileNameLengthLimitExceededException extends BaseException {
    private static final long serialVersionUID = 1L;

    public FileNameLengthLimitExceededException(int defaultFileNameLength)
    {
        super("upload.filename.exceed.length", new Object[] { defaultFileNameLength });
    }
}
