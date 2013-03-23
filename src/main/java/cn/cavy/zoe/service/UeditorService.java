package cn.cavy.zoe.service;

import javax.servlet.http.HttpServletRequest;

import cn.cavy.zoe.action.vo.UploadResult;

public interface UeditorService {

    UploadResult upload(HttpServletRequest request);

}
