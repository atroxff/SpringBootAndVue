package springboot.common;

import springboot.model.DataResult;

/**
 * @description:
 * @auther: hefeng
 * @creatTime: 2019-8-12 09:53:34
 */
public class BaseController {
    public DataResult buildSuccessResponse(){
        return new DataResult(200,null,null);
    };
    public DataResult buildFailureResponse(){
        return new DataResult(404,null,null);
    };
    public DataResult buildErrorResponse(){
        return new DataResult(500,null,null);
    };
    public DataResult buildSuccessResponse(Object data){
        return new DataResult(200,"请求成功",data);
    };
    public DataResult buildFailureResponse(Object data){
        return new DataResult(404,"请求失败",data);
    };
    public DataResult buildErrorResponse(Object data){
        return new DataResult(500,"系统异常",data);
    };
    public DataResult buildSuccessResponse(Object data,String msg){
        return new DataResult(200,msg,data);
    };
    public DataResult buildFailureResponse(Object data,String msg){
        return new DataResult(404,msg,data);
    };
    public DataResult buildErrorResponse(Object data,String msg){
        return new DataResult(500,msg,data);
    };
}
