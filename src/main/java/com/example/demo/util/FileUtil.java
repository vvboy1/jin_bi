package com.example.demo.util;

import com.example.demo.common.ServerResponse;


import java.io.File;

public class FileUtil {

    //判断之前的文件现在没了就删除
    public static ServerResponse delIfUrl(String oldUrl, String newUrl){
        //String path = request.getSession().getServletContext().getRealPath("/") + File.separator + "WEB-INF" + File.separator + "file";


        String[] oldList = oldUrl.split("|");
        String[] newList = newUrl.split("|");

        try {
            for(String oldTtem : oldList){

                for(String newItem : newList ){
                    if(oldTtem.equals(newItem)){
                        continue;
                    }
                }
                File targetFile = new File(PropertiesUtil.getProperty("my.server.http.prefix"),oldTtem);
                targetFile.delete();
            }
        }catch (Exception e){
            return ServerResponse.createByErrorMessage("异常");
        }

        return ServerResponse.createBySuccessMessage("删除成功");

    }
}
