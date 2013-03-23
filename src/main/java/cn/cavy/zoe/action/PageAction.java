package cn.cavy.zoe.action;

import javax.servlet.http.HttpServletRequest;

import cn.cavy.common.util.RequestUtil;

public class PageAction extends BaseAction {

    public static final String PARAM_PAGE_INDEX = "page";

    public static final String PARAM_PAGE_SIZE = "size";

    public static Integer DEFAULT_PAGE_INDEX = 1;

    public static Integer DEFAULT_PAGE_SIZE = 10;

    protected Integer getPageIndex(HttpServletRequest request) {
        Integer pageIndex = RequestUtil.getParamIntValue(request, PARAM_PAGE_INDEX);
        return pageIndex == null ? DEFAULT_PAGE_INDEX : pageIndex;
    }

    protected Integer getPageSize(HttpServletRequest request) {
        Integer pageSize = RequestUtil.getParamIntValue(request, PARAM_PAGE_SIZE);
        return pageSize == null ? DEFAULT_PAGE_SIZE : pageSize;
    }
}