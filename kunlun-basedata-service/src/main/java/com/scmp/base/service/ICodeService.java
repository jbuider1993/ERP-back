package com.scmp.base.service;

import com.scmp.base.model.CodeModel;

public interface ICodeService {
    public CodeModel getAuthCode() throws Exception;
}
