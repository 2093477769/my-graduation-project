package com.zb.express.setting.task;

import com.zb.express.setting.service.InExpressService;
import com.zb.express.setting.service.OutExpressService;
import com.zb.express.setting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskManager {

    @Autowired
    private UserService userService;

    @Autowired
    private InExpressService inExpressService;

    @Autowired
    private OutExpressService outExpressService;


    @Value("${upload.path.avatar}")
    private String avatarPath;

    @Value("${upload.path.inExpressImg}")
    private String inExpressImgPath;

    @Value("${upload.path.outExpressImg}")
    private String outExpressImgPath;

    //删除多余头像图片
    @Scheduled(cron = "0 0 */1 * * ?")
    public void clearAvatar(){
        List<String> avatars=userService.queryAllAvatar();
        // 创建一个File对象
        File folder = new File(avatarPath);

        // 获取文件夹中的所有文件名
        String[] fileList = folder.list();

        List<String> list=new ArrayList<>();

        for (String fileName : fileList) {
            // 如果文件名为"defaultAvatar.jpg"，则跳过当前循环
            if (fileName.equals("defaultAvatar.jpg")) {
                continue;
            }

            boolean flag=true;
            for (String avatar : avatars) {
                avatar=avatar.substring(avatar.lastIndexOf("/")+1);
                if (fileName.equals(avatar)){
                    flag=false;
                }
            }
            if (flag){
                list.add(fileName);
            }
        }

        for (String s : list) {
            // 创建一个File对象
            File file = new File(avatarPath,s);
            // 判断文件是否存在
            if (file.exists()) {
                // 尝试删除文件
                boolean deleted = file.delete();

                if (deleted) {
                    System.out.println("文件删除成功");
                } else {
                    System.out.println("文件删除失败");
                }
            } else {
                System.out.println("文件不存在");
            }
        }
    }

    //删除多余寄入快递图片
    @Scheduled(cron = "0 0 */1 * * ?")
    public void clearInExpressImg(){
        List<String> inExpressImgs=inExpressService.queryAllInExpressImg();
        // 创建一个File对象
        File folder = new File(inExpressImgPath);

        // 获取文件夹中的所有文件名
        String[] fileList = folder.list();

        List<String> list=new ArrayList<>();

        for (String fileName : fileList) {
            int count=0;
            for (String inExpressImg : inExpressImgs) {
                inExpressImg=inExpressImg.substring(inExpressImg.lastIndexOf("/")+1);
                if (fileName.equals(inExpressImg)){
                    count++;
                }
            }
            if (count==0){
                list.add(fileName);
            }
        }

        for (String s : list) {
            // 创建一个File对象
            File file = new File(inExpressImgPath,s);
            // 判断文件是否存在
            if (file.exists()) {
                // 尝试删除文件
                boolean deleted = file.delete();

                if (deleted) {
                    System.out.println("文件删除成功");
                } else {
                    System.out.println("文件删除失败");
                }
            } else {
                System.out.println("文件不存在");
            }
        }



    }

    //删除多余寄出快递图片
    @Scheduled(cron = "0 0 */1 * * ?")
    public void clearOutExpressImg(){
        List<String> outExpressImgs=outExpressService.queryAllOutExpressImg();
        // 创建一个File对象
        File folder = new File(outExpressImgPath);

        // 获取文件夹中的所有文件名
        String[] fileList = folder.list();

        List<String> list=new ArrayList<>();

        for (String fileName : fileList) {
            int count=0;
            for (String outExpressImg : outExpressImgs) {
                outExpressImg=outExpressImg.substring(outExpressImg.lastIndexOf("/")+1);
                if (fileName.equals(outExpressImg)){
                    count++;
                }
            }
            if (count==0){
                list.add(fileName);
            }
        }

        for (String s : list) {
            // 创建一个File对象
            File file = new File(outExpressImgPath,s);
            // 判断文件是否存在
            if (file.exists()) {
                // 尝试删除文件
                boolean deleted = file.delete();

                if (deleted) {
                    System.out.println("文件删除成功");
                } else {
                    System.out.println("文件删除失败");
                }
            } else {
                System.out.println("文件不存在");
            }
        }



    }
}
