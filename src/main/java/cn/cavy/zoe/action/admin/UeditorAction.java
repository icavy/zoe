package cn.cavy.zoe.action.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cavy.zoe.action.vo.UploadResult;
import cn.cavy.zoe.service.UeditorService;

@Controller
@RequestMapping("/ueditor")
public class UeditorAction {

    @Resource
    UeditorService ueditorService;

    @RequestMapping("/upload")
    @ResponseBody
    public UploadResult imageUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UploadResult uploadResult = ueditorService.upload(request);
        return uploadResult;
    }
}