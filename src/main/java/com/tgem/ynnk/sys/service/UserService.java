package com.tgem.ynnk.sys.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tgem.ynnk.sys.dao.UserMapper;
import com.tgem.ynnk.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lmx
 * @since 2020-03-30
 */
public interface UserService extends IService<User> {
    public User findByUsernameAndPwd(String userName, String password);

    public String uploadPic(MultipartFile file,String id) throws IOException;
}
