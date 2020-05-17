package com.xuesi.service.impl;

import com.xuesi.mapper.HomeMapper;
import com.xuesi.mapper.SpeakerMapper;
import com.xuesi.pojo.Admin;
import com.xuesi.pojo.Result;
import com.xuesi.pojo.User;
import com.xuesi.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    HomeMapper homeMapper;
    @Autowired
    SpeakerMapper speakerMapper;

    @Override
    public Result login(String username, String password, String code, HttpServletRequest request) {
        Result result = new Result();
        //判定验证码 1   后台的验证码是放在session里面的 code-->?
        String codeValue = (String) request.getSession().getAttribute("code");
        if (code.equalsIgnoreCase(codeValue)) {
            //判定用户名密码 2-->先用用户名取数据库判断-->在判断密码
            System.out.println("name:"+username);
            Admin user = homeMapper.selectByUserName(username);
            //对user进行判断
            //在进行密码判定之前别将前端传进来的密码按照加密方式进行加密以后在比较
            if (user == null || !user.getPassword().equals(password)) {
                // if(user==null || !user.getPassword().equals(Md5Util.encryption(password,username))){
                //执行失败的信息
                result.setStatus(500);
                result.setMessage("用户名或者密码错误,如果没有账号请进行注册!");
            } else {
                result.setStatus(200);
                //将登录成功的用户信息放在session中,方便后面做权限使用-->前端使用session获角色
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("name1",user.getNickname());
                session.setMaxInactiveInterval(60 * 60 * 24);
            }
        } else {
            //设置失败的信息
            result.setStatus(500);
            result.setMessage("验证码错误!");
        }
        return result;
    }

    @Override
    public Result register(String tel, String password, String code, HttpServletRequest request) {
        Result result = new Result();
        //判定验证码
        String telcode = (String) request.getSession().getAttribute("telCode");
        System.out.println("telcode的值：" + telcode);
        if (code.equals(telcode)) {
            //需要判定当前手机号是否被注册
            Admin u = new Admin();
            u.setUsername(tel);
            System.out.println("实现层里的电话号码：" + u.getUsername());
            Admin admin = homeMapper.selectByUserName(u.getUsername());
            if (admin == null) {

                u.setPassword(password);
                u.setrId("2");
                //2为讲师的权限
                try {
                    int i = homeMapper.insert(u);
                    //可以插入一个注册speaker，speakerController里的方法不能用，
                    //添加speaker时，照片为空值，名字，描述为空值，讲师职称，定义字符串为初级讲师，插入进去，id不为自增加
                    //也就是添加了一个字段为speakerJob，
                    // sql可以这样插入，id和speakerJob。防止删除记录引发的admin.id和speaker.id不同，造成数据库插入错误。
                    //晚安   admin.id可以进行根据username进行查询。
                    if (i > 0) {
                        //获取admin.id
                        //这里出了问题
                        Admin admin1 = homeMapper.selectAdminByUserName(u.getUsername());
                        admin1.getId();
                        System.out.println(" admin1.getId():" + admin1.getId());
                        try {
                            int id = Integer.parseInt(admin1.getId());
                            //强转为int类型。
                            System.out.println("获取到的" + id);
                            if (id > 0) {
                                String speakerJob = "初级讲师";
                                System.out.println("获得到的admin中的id：" + id);
                                int j = speakerMapper.addSpeakerByRegister(id, speakerJob);

                                if (j > 0) {
                                    result.setStatus(200);
                                } else {
                                    result.setStatus(500);
                                    result.setMessage("注册用户的同时，添加讲师信息失败！");
                                }
                            }
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                        result.setStatus(200);
                    } else {
                        result.setStatus(500);
                        result.setMessage("注册失败!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    result.setStatus(500);
                    result.setMessage("系统异常!");
                }
            } else {
                result.setStatus(500);
                result.setMessage("该手机号已被注册!");
            }
        } else {
            result.setStatus(500);
            result.setMessage("验证码错误!请重新发送!");
        }

        return result;
    }

    @Override
    public Result editPassword(String oldPassword, String password, String newPassword, HttpServletRequest request) {
        Admin admin1 = (Admin) request.getSession().getAttribute("user");
        Admin admin2 = new Admin();
        admin2.setUsername(admin1.getUsername());
        System.out.println("测试从前端获得的Username：" + admin1.getUsername());
        Result result = new Result();
        int rows = homeMapper.selectTureByOldPassword(admin1.getUsername(), oldPassword);
        System.out.println("rows--------:" + rows);
        if (rows > 0) {
            try {
                //修改密码,判断两次输入的密码是否相同
                if (password.equals(newPassword)) {
                    int i = homeMapper.editPassword(admin1.getUsername(), password);
                    if (i > 0) {
                        result.setMessage("操作成功!");
                        result.setStatus(200);
                        return result;
                    } else {
                        result.setMessage("修改密码失败!");
                        result.setStatus(500);
                        return result;
                    }
                } else {
                    result.setMessage("两次输入的密码不同，请重新输入!");
                    result.setStatus(500);
                    return result;
                }
            } catch (Exception e) {
                e.printStackTrace();
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                result.setMessage("未知异常!");
                result.setStatus(500);
                return result;
            }
        } else {
            result.setMessage("原密码输入错误，请重新输入");
            result.setStatus(500);
            return result;
        }

    }

    @Override
    public Result editAdmin(String vid,Admin admin, HttpServletRequest request) {

        System.out.println("从前面获取的vid:" + vid);
        System.out.println("speaker从前端获取到的地址：" + admin.getFaceImage());
        String nickname = admin.getNickname();
        String faceImage = "img/" + admin.getFaceImage();

        System.out.println("speaker从前端获取到的地址：" + admin.getFaceImage());
        System.out.println("自己拼接的图片地址："+faceImage);
        System.out.println("nickname："+nickname);
        Result result = new Result();
        try {
            int i = homeMapper.editAdmin(vid,nickname,faceImage);
            if (i > 0) {
                Admin user = homeMapper.selectByVid(vid);
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                result.setMessage("操作成功!");
                result.setStatus(200);
                return result;
            } else {
                result.setMessage("修改用户信息失败!");
                result.setStatus(500);
                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setMessage("未知异常!");
            result.setStatus(500);
            return result;
        }


    }

    @Override
    public Result getAllUser(Integer stu, String timerange, Integer page, Integer limit,HttpServletRequest request) {
        Integer start = (page - 1) * limit;
        List<Admin> list = null;
        String[] split = null;
        System.out.println("下标的start值："+start);
        if (timerange != null && timerange != "") {
            split = timerange.split("~");
            list = homeMapper.selectUser(stu, start, limit, split[0], split[1]);
        } else {
            list = homeMapper.selectUser(stu, start, limit, null, null);
        }
        Integer total = homeMapper.getCount();
        Result result = new Result();
        result.setTotal(total);
        result.setItem(list);
        return result;
    }

    @Override
    public Result editRid(String vid, String rId) {
        Result result = new Result();
        int roes = homeMapper.editRid(vid,rId);
        if (roes >0){
             result.setStatus(200);
             result.setMessage("修改权限成功！");
            return  result;
        }else {
            result.setStatus(500);
            result.setMessage("未知异常,请重试！");
            return result;
        }
    }

    @Override
    public Admin selectById(String vid) {
        Admin admin = homeMapper.selectByPrimaryKey(vid);
        return  admin;
    }
}
