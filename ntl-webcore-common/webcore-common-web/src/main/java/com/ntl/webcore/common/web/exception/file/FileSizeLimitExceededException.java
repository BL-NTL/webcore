package com.ntl.webcore.common.web.exception.file;

import com.ntl.webcore.common.web.exception.base.BaseException;

/**
 * 文件名大小限制异常类
 * 
 * 
 */
public class FileSizeLimitExceededException extends BaseException {
    private static final long serialVersionUID = 1L;

    public FileSizeLimitExceededException(long defaultMaxSize)
    {
        super("upload.exceed.maxSize", new Object[] { defaultMaxSize });
    }
}
