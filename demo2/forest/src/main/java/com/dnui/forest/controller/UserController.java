package com.dnui.forest.controller;

import com.dnui.forest.pojo.User;
import com.dnui.forest.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {




    @Resource
    private UserService userService;

    UploadController uploadController;

    private final String uploadDir ="D:\\JavaWeb\\end\\forest\\src\\main\\resources\\static\\data\\";



    @PostMapping ("/insert")
    public String insert(@RequestBody User user) {


         try {
                user.setPicture(uploadDir+user.getPicture());
             userService.insert(user);
             return "success";

         }catch (Exception e) {
             e.printStackTrace(); // 打印到控制台
             // 返回错误信息
             return "error";
         }
    }



    @RequestMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        userService.deleteById(id);
        return "success";
    }

    @RequestMapping("/update")
    public String update(@RequestBody User user) {

        try{
            userService.update(user);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/select")
    public List<User> selectAll() {
        return userService.selectAll();
    }

    @RequestMapping("/find")
    public User findByName(@RequestParam("name") String username) {
        return userService.findByName(username);

    }

//    @PostMapping("/upload")
//    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
//        if (file == null || file.isEmpty()) {
//            throw new IllegalArgumentException("上传文件为空");
//        }
//
//        String extension = "";
//        String originalFilename = file.getOriginalFilename();
//        if (originalFilename != null && originalFilename.contains(".")) {
//            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
//        }
//
//        String newFileName = UUID.randomUUID().toString() + extension;
//
//        Path uploadPath = Paths.get(uploadDir);
//        if (!Files.exists(uploadPath)) {
//            Files.createDirectories(uploadPath);
//        }
//
//        // 5. 将文件复制到目标位置
//        Path targetPath = uploadPath.resolve(newFileName);
//        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
//
//
//
//        return  newFileName;
//    }

}
