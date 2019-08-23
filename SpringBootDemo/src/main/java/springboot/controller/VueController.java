package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springboot.common.BaseController;
import springboot.entity.TbSpUser;
import springboot.model.DataResult;
import springboot.model.User;
import springboot.service.VueService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @description:
 * @auther: hefeng
 * @creatTime: 2019-8-12 09:50:12
 */
@RestController
@RequestMapping("/api/vue")
public class VueController extends BaseController {

    @Autowired
    VueService vueService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public DataResult login(String username,String password){
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return buildErrorResponse();
        }
        try {
            if(username.equals("admin")&&password.equals("123456")){
                String token = username + password + "uuid";
                return buildSuccessResponse(token, "登录成功");
            }
            return buildFailureResponse();
        } catch (Exception e) {
            return buildErrorResponse(null, "登录成功");
        }
    }

    @RequestMapping(value = "/searchUserByCondition", method = RequestMethod.POST)
    public DataResult searchUserByCondition(Integer currentPage,Integer pageSize, Integer status,String userName){

        try {
            PageHelper.startPage(currentPage,pageSize);
            List<TbSpUser> userList = vueService.getAllUser(status,userName);
            PageInfo<TbSpUser> pageInfo = new PageInfo<>(userList);
            return buildSuccessResponse(pageInfo, "查询成功");
        } catch (Exception e) {
            return buildErrorResponse(null);
        }
    }

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public DataResult regist(@RequestBody TbSpUser user){

        try {
            vueService.addUser(user);
            return buildSuccessResponse(null, "注册成功");
        } catch (Exception e) {
            return buildErrorResponse(null);
        }
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public DataResult regist(String userId){
        try {
            vueService.deleteUser(userId);
            return buildSuccessResponse(null, "注册成功");
        } catch (Exception e) {
            return buildErrorResponse(null);
        }
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public DataResult updateUser(@RequestBody TbSpUser user){

        try {
            vueService.updateUser(user);
            return buildSuccessResponse(null, "注册成功");
        } catch (Exception e) {
            return buildErrorResponse(null);
        }
    }


}
