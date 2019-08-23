package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.dao.TbSpUserMapper;
import springboot.entity.TbSpUser;
import springboot.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @description:
 * @auther: hefeng
 * @creatTime: 2019-8-13 10:25:07
 */
@Service
public class VueService {
    @Autowired
    TbSpUserMapper userMapper;

    public List<User> getUserList(){
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            String username = "user" + i;
            String account = "user_" + i;
            int sex = i % 2;
            int status = (i + 1) % 2;
            Date creatTime = new Date();
            User user = new User(username, sex, account, status, creatTime);
            userList.add(user);
        }
        return userList;
    }

    public List<TbSpUser> getAllUser(Integer status,String userName){
        List<TbSpUser> list = userMapper.searchUserByCondition(userName, status);
        return list;
    }

    public void addUser(TbSpUser user) {
        user.setUserId(UUID.randomUUID().toString());
        user.setCreateTime(new Date());
        user.setPermission(0);
        userMapper.insert(user);
    }

    public void deleteUser(String userId) {
        userMapper.deleteByPrimaryKey(userId);
    }

    public void updateUser(TbSpUser user) {
        userMapper.updateByPrimaryKey(user);
    }
}
