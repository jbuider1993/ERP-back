package com.kunlun.basedata.service;

import com.kunlun.basedata.model.CodeModel;

public interface ICodeService {
    public CodeModel getAuthCode() throws Exception;
}
