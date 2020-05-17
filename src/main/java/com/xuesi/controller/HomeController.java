package com.xuesi.controller;


import cn.dsna.util.images.ValidateCode;

import com.xuesi.pojo.Admin;
import com.xuesi.pojo.Result;
import com.xuesi.pojo.Speaker;
import com.xuesi.service.HomeService;
import com.xuesi.utils.SendmsUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/home")
@Api(value = "后台的用户管理功能", tags = "后台的用户管理功能")
public class HomeController {

    @Autowired
    private HomeService homeService;


    @RequestMapping("/getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response) {
        //声明验证码
        //参数列表:宽度 , 高度, 验证的字符数量, 干扰线的数量
        ValidateCode validateCode = new ValidateCode(120, 40, 5, 50);
        //需要将验证码信息放在后台(session-->key--value)并且返回给前端
        //session
        //   code-->123
        //   code-->456
        request.getSession().setAttribute("code", validateCode.getCode());
        //将验证码图片以流的方式返回给前端
        try {
            validateCode.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @param code
     * @param request
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public Result login(String username, String password, String code, HttpServletRequest request) {
        Result result = homeService.login(username, password, code, request);

        return result;
    }


    /**
     * 获得短信验证码
     *
     * @param tel
     * @param request
     * @return
     */
    @RequestMapping("/getTelCode")
    @ResponseBody
    public Result getTelCode(String tel, HttpServletRequest request) {
        //我们进行手机短信认证  需要一个手机号
        String telCode = SendmsUtil.getTelCode(tel);
        HttpSession session = request.getSession();
        session.setAttribute("telCode", telCode);
        //需要设置过期时间
        session.setMaxInactiveInterval(60 * 5);
        Result result = new Result();
        result.setMessage("短息已发送成功!");
        return result;
    }

    /**
     * 注册账号
     *
     * @param tel     电话号码
     * @param code    验证码
     * @param request
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public Result register(String tel, String password, String code, HttpServletRequest request) {
        Result result = homeService.register(tel, password, code, request);
        return result;
    }


    /**
     * 后台登录
     *
     * @param request
     * @return
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        //在进行跳转login之前  先判断我们的的session中是否存在之前的用户信息
        //如果有   则直接进入主页面   如果没有直接跳转到login页面
        HttpSession session = request.getSession();

        Admin user = (Admin) session.getAttribute("user");
        if (user == null) {
            return "behind/login";
        } else {
            //这里面是user不为null的情况
            session.setAttribute("user", user);
            //重新设置过期时间
            session.setMaxInactiveInterval(60 * 60 * 24);
            //直接跳转到mian
            return "behind/main";
        }
    }


    @RequestMapping("/editPassword")
    @ResponseBody
    public Result editPassword(HttpServletRequest request, String oldPassword, String password, String newPassword) {
        Admin admin1 = (Admin) request.getSession().getAttribute("user");
        Admin admin2 = new Admin();
        admin2.setrId(admin1.getrId());
        System.out.println("测试从前端获得的rId：" + admin1.getrId());
        admin2.setUsername(admin1.getUsername());
        System.out.println("测试从前端获得的Username：" + admin1.getUsername());
        admin2.setId(admin1.getId());
        Result result = homeService.editPassword(oldPassword, password, newPassword, request);
        return result;
    }

    @RequestMapping("/editAdmin")
    @ResponseBody
    public Result editAdmin(HttpServletRequest request,Admin admin,String vid) {
        Admin admin1 = (Admin) request.getSession().getAttribute("user");
        Admin admin2 = new Admin();
        admin2.setUsername(admin1.getUsername());
        System.out.println("测试从前端获得的Username：" + admin1.getUsername());
        Result result = homeService.editAdmin(vid,admin,request);
        return result;

    }

    @RequestMapping("/getAllUser")
    @ResponseBody
    public Result getAllUser(Integer stu, String timerange,Integer page, Integer limit, HttpServletRequest request) {
        Result result = homeService.getAllUser(stu,timerange,page, limit,request);
        return result;


    }

    @RequestMapping("/editRid")
    @ResponseBody
    public Result editRid(String vid,String rId){
        Result result = homeService.editRid(vid,rId);
        return result;
    }
//--------------------------------后台跳转------------------------

    /**
     * 进入后台登录
     *
     * @return
     */
    @RequestMapping("/Login")
    public String login() {

        return "behind/login";
    }

    /**
     * 进入后台主页
     *
     * @return
     */
    @RequestMapping("/returnmain")
    public String returnmain() {

        return "behind/main";
    }

    @RequestMapping("/loginout")
    public String loginout(HttpServletRequest request) {
        request.getSession().invalidate();
        //springmvc默认采用的是转发   就是一次请求
        //因为转发会导致在一次登录登录界面时出现404
        //设置成了重定向 -- 表示执行一个新的请求-->不受到视图解析器的影响
        return "redirect:index";
    }

    /**
     * 进入后台注册页面
     *
     * @return
     */
    @RequestMapping("/returnRegister")
    public String returnRegister() {

        return "behind/register";
    }

    /**
     * 进入后台注册页面
     *
     * @return
     */
    @RequestMapping("/returnPassword")
    public String password() {

        return "behind/newPassword";
    }
    /**
     * 进入后台注册页面
     *
     * @return
     */
    @RequestMapping("/returnEditAdmin")
    public String returnEditAdmin() {

        return "behind/editAdmin";
    }


    @RequestMapping("/behinduser")
    public String behindUser() {
        return "behind/behindUser";
    }

    /**
     * 进入到权限编辑页面
     * @return
     */
    @RequestMapping("/getEdit")
    public String editrid(String vid, Model model){
        //通过id取找当前行的数据,并且返回给前端  session域     request域
        Admin admin1 = homeService.selectById(vid);
        model.addAttribute("admin1", admin1);
        return "behind/editRid";
    }

}
