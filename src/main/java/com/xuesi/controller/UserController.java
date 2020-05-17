package com.xuesi.controller;

import com.xuesi.pojo.*;
import com.xuesi.resultVo.R;
import com.xuesi.service.UserService;
import com.xuesi.utils.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/user")
@Api(value = "用户管理", tags = "用户管理")

public class UserController {
    @Autowired
    UserService userService;


    /**
     * 用户登录
     *
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("/loginUser")
    @ResponseBody
    public String loginUser(User user, HttpSession session) {
        // user.setPassword(Md5Utils.getMd5Str(user.getPassword()));
        int rows = userService.loginUser(user);
        if (rows > 0) {
            session.setAttribute("userAccount", user.getEmail());
            User user1 = userService.getUserByEmail(user.getEmail());
            session.setAttribute("name",user1.getNickname());
            return "success";
        }
        return "fail";
    }

    /**
     * 检验邮箱
     *
     * @param user
     * @return
     */
    @RequestMapping("/validateEmail")
    @ResponseBody
    public String validateEmail(User user) {
        int res = userService.validateEmail(user);
        if (res > 0) {
            System.out.println("fail");
            return "fail";
        }
        return "success";

    }

    /**
     * 注册用户信息
     *
     * @param user
     * @param session
     * @return
     */

    @RequestMapping("/insertUser")
    @ResponseBody
    public String insertUser(User user, HttpSession session) {
        Integer rows = userService.addUser(user);

        if (rows != null) {
            session.setAttribute("userAccount", user.getEmail());

            return "success";
        }
        return "fail";
    }

    /**
     * 展示个人信息
     *
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/showMyProfile")
    public String showMyProfile(HttpSession session, Model model) {
        String email = (String) session.getAttribute("userAccount");
        User user = userService.getUserByEmail(email);
        user.setImgurl(InfoUtils.getProperties("IMG_PATH") + user.getImgurl());

        model.addAttribute("user", user);
        return "before/my_profile";
    }

    /**
     * 修改用户头像照片
     *
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/changeAvatar")
    public String changeAvatar(HttpSession session, Model model) {
        String email = (String) session.getAttribute("userAccount");
        User user = userService.getUserByEmail(email);
        user.setImgurl(InfoUtils.getProperties("IMG_PATH") + user.getImgurl());
        model.addAttribute("user", user);
        return "before/change_avatar";
    }

    /**
     * 图片上传
     *
     * @param image_file
     * @param session
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     * @return
     */
    @RequestMapping("/upLoadImage")
    public String upLoadImage(MultipartFile image_file, HttpSession session, String x1, String x2, String y1, String y2) {

        String filename = image_file.getOriginalFilename();
        String substring = filename.substring(filename.lastIndexOf("."));
        String newName = UUIDUtils.getUUID() + substring;
        String fileUrl = InfoUtils.getProperties("UPLOAD_LOC");
        File file = new File(fileUrl, newName);
        try {
            image_file.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        float _x1 = Float.valueOf(x1);
        float _x2 = Float.valueOf(x2);
        float _y1 = Float.valueOf(y1);
        float _y2 = Float.valueOf(y2);
        ImageCut imageCut = new ImageCut();
        System.out.println(fileUrl + "\\" + newName);
        imageCut.cutImage(fileUrl + "\\" + newName, (int) _x1, (int) _y1, (int) (_x2 - _x1), (int) (_y2 - _y1));
        String email = (String) session.getAttribute("userAccount");
        User user = new User();
        user.setEmail(email);
        user.setImgurl(newName);
        int rows = userService.upLoadImage(user);
        return "redirect:/user/showMyProfile";
    }

    /**
     * 修改个人信息,带着信息添转页面
     *
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/changeProfile")
    public String changeProfile(HttpSession session, Model model) {
        String email = (String) session.getAttribute("userAccount");
        User user = userService.getUserByEmail(email);
        user.setImgurl(InfoUtils.getProperties("IMG_PATH") + user.getImgurl());
        model.addAttribute("user", user);
        return "before/change_profile";
    }

    /**
     * 修改个人信息，修改完成返回个人中心
     *
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("/updateUser")
    public String updateUser(User user, HttpSession session) {
        String email = (String) session.getAttribute("userAccount");
        user.setEmail(email);
        int rows = userService.updateUser(user);
        return "redirect:/user/showMyProfile";
    }

    /**
     * 查询信息后，跳转密码安全设置
     *
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/passwordSafe")
    public String passwordSafe(HttpSession session, Model model) {
        String email = (String) session.getAttribute("userAccount");
        User user = userService.getUserByEmail(email);
        user.setImgurl(InfoUtils.getProperties("IMG_PATH") + user.getImgurl());
        model.addAttribute("user", user);
        return "before/password_safe";
    }

    /**
     * 验证密码
     *
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/validatePassword")
    @ResponseBody
    public String validatePassword(String password, HttpSession session) {
        String email = (String) session.getAttribute("userAccount");
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        Integer integer = userService.loginUser(user);
        if (integer > 0) {
            return "success";
        }
        return "fail";
    }

    /**
     * 修改密码
     *
     * @param newPassword
     * @param session
     * @return
     */
    @RequestMapping("/updatePassword")
    @ResponseBody
    public String updatePassword(String newPassword, HttpSession session) {

        String email = (String) session.getAttribute("userAccount");
        User user = new User();
        user.setEmail(email);
        user.setPassword(newPassword);
        int rows = userService.updateUser(user);
        if (rows > 0) {
            return "success";
        }
        return "fail";
    }

    /**
     * 跳转忘记密码
     *
     * @return
     */
    @RequestMapping("/forgetPassword")
    public String forgetPassword() {
        return "before/forget_password";
    }

    /**
     * 发送邮件
     *
     * @param email
     * @param session
     * @return
     */
    @RequestMapping("/sendEmail")
    @ResponseBody
    public String sendEmail(String email, HttpSession session) {
        try {
            String code = VerificationCodeUtils.getRandomCode(6);
            System.out.println(code);
            session.setAttribute("code", code);
            MailUtil.sendEmail(email, "验证码：" + code + "，请在30分钟内输入。", "学思网教育验证码");
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }

    }

    /**
     * 验证邮箱验证码
     *
     * @param email
     * @param code
     * @param session
     * @return
     */
    @RequestMapping("/validateEmailCode")
    @ResponseBody
    public String validateEmailCode(String email, String code, HttpSession session) {
        System.out.println(code);
        String incode = (String) session.getAttribute("code");
        System.out.println(incode.equals(code));
        if (incode.equals(code)) {
            session.setAttribute("email", email);
            return "success";
        }
        return "fail";
    }

    /**
     * 跳转到忘记密码
     *
     * @return
     */
    @RequestMapping("/showResetPassword")
    public String resetPassword() {
        return "before/reset_password";
    }

    /**
     * 忘记密码
     *
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/resetPassword")
    public String resetPassword(String password, HttpSession session) {
        System.out.println(password);
        String email = (String) session.getAttribute("email");
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        int rows = userService.updateUser(user);
        System.out.println(rows);
        return "redirect:/index.jsp";
    }

    /**
     * 退出
     *
     * @param session
     * @return
     */
    @RequestMapping("/loginOut2")
    public String loginout(HttpSession session) {
        session.removeAttribute("userAccount");
        return "redirect：/index";
        //springmvc默认采用的是转发   就是一次请求
        //因为转发会导致在一次登录登录界面时出现404
        //设置成了重定向 -- 表示执行一个新的请求-->不受到视图解析器的影响
    }

    @RequestMapping("/loginOut")
    public void loginOut2(HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        session.removeAttribute("userAccount");
        try {
            response.sendRedirect(request.getContextPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public Result getAll(Integer stu, String timerange, Integer page, Integer limit, HttpServletRequest request) {
        Admin admin1 = (Admin) request.getSession().getAttribute("user");
        Admin admin2 = new Admin();
        admin2.setrId(admin1.getrId());

        Result result = userService.getAll(stu, timerange, page, limit);
        return result;


    }

    @RequestMapping("/beforeuser")
    public String beforeUser() {
        return "behind/beforeUser";
    }

    @RequestMapping("/about")
    public String about() {
        return "before/about";
    }

    @RequestMapping("/case")
    public String case1() {
        return "before/case";
    }
}
