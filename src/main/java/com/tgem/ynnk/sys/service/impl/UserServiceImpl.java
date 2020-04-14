package com.tgem.ynnk.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import com.tgem.ynnk.response.ResultCode;
import com.tgem.ynnk.sys.dao.UserMapper;
import com.tgem.ynnk.sys.entity.User;
import com.tgem.ynnk.sys.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;

    public User findByUsernameAndPwd(String userName, String password){
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",userName);
        queryWrapper.eq("password",password);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public String uploadPic(MultipartFile file, String id) throws IOException {
        User user = userMapper.selectById(id);
        String encode = "data:image/png;base64,"+ Base64.encode(file.getBytes());
        user.setStaffPhoto(encode);
        int i = userMapper.updateById(user);
        if(i>0) {
            return encode;
        }else{
            throw new IOException();
        }
    }

}
