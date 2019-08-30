package com.example.demo.controller;

import com.example.demo.common.Const;
import com.example.demo.common.ResponseCode;
import com.example.demo.common.ServerResponse;
import com.example.demo.service.IuploadService;
import com.example.demo.util.PropertiesUtil;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static com.jayway.jsonpath.internal.function.ParamType.JSON;

@RestController
@RequestMapping("/upload")
@CrossOrigin //跨域
public class UploadController {

    private Logger logger = LoggerFactory.getLogger(UploadController.class);
    @Autowired
    private IuploadService iuploadService;


    /**
     * 上传文件
     * @param session
     * @param fileList
     * @param request
     * @return
     */
    @RequestMapping("upload.do")
    public ServerResponse upload(HttpSession session, @RequestParam(value = "upload_file[]",required = false) List<MultipartFile> fileList, HttpServletRequest request){
        //required=false表示不传的话，会给参数赋值为null，required=true就是必须要有，不然报错
//        UserVo uservo = (UserVo)session.getAttribute(Const.CURRENT_USER);
//        if(uservo == null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
//        }

        List<Map> list = Lists.newArrayList();
        if(!fileList.isEmpty()&&fileList.size()>0&&fileList!=null){
            for(MultipartFile file : fileList) {
                String fileName = file.getOriginalFilename();
                if(fileName.contains(",")||fileName.contains("，")){
                    return ServerResponse.createByErrorMessage("文件名不合法");
                }
                String path = request.getSession().getServletContext().getRealPath("upload");
                //String targetFileName = iuploadService.uploadName(uservo.getStuno(),file, path);
                String targetFileName = iuploadService.uploadName(UUID.randomUUID().toString(),file, path);
                //String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/upload/" + targetFileName;
                String url = PropertiesUtil.getProperty("my.server.http.prefix",PropertiesUtil.getProperty("my.server.http.prefix")) + "/upload/" + targetFileName;

                Map fileMap = new HashMap();
                fileMap.put("oldfilename",fileName);
                fileMap.put("uri", "/upload/" + targetFileName);
                fileMap.put("url", url);

                list.add(fileMap);
            }
            return ServerResponse.createBySuccess(list);
        }else {
            return ServerResponse.createByErrorMessage("没有接收到文件");
        }
    }

//    /**
//     * wangEdier富文本上传图片
//     * @param session
//     * @param fileList
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("richtext_img_upload.do")
//    @ResponseBody
//    public Map richtextImgUpload(HttpSession session, @RequestParam(value = "upload_file",required = false) List<MultipartFile> fileList, HttpServletRequest request, HttpServletResponse response){
//        Map resultMap = new HashMap();
//        UserVo userVo = (UserVo)session.getAttribute(Const.CURRENT_USER);
//        if(userVo == null){
//            resultMap.put("success",false);
//            resultMap.put("msg","未登录");
//            return resultMap;
//        }
//        //富文本中对于返回值有自己的要求,我们使用是simditor所以按照simditor的要求进行返回
////        {
////            "success": true/false,
////                "msg": "error message", # optional
////            "file_path": "[real file path]"
////        }
////wangEditor
////        {
////            errno:0,//正确是0，错误是不等于0其他数字，自定义处理
////            data:["url1","url2"]
////        }
//
//
//        String path = request.getSession().getServletContext().getRealPath("upload");
//        List<String> list = Lists.newArrayList();
//        for(MultipartFile file :fileList){
//            String targetFileName = iFileService.uploadName(userVo.getStuno(),file,path);
//            String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/upload/"+targetFileName;
//            list.add(url);
//        }
//
//
//        if(CollectionUtils.isEmpty(list)||list==null){
//            resultMap.put("success",false);
//            resultMap.put("msg","没接收到文件");
//            return resultMap;
//        }
//
//
//        resultMap.put("success",true);
//        resultMap.put("msg","上传文件成功");
//        resultMap.put("file_path",list);
//
//        return resultMap;
//
//    }

}