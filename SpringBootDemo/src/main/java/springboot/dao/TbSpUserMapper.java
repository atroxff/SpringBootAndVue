package springboot.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import springboot.entity.TbSpUser;
import springboot.entity.TbSpUserExample;

import java.util.List;

@Mapper
public interface TbSpUserMapper {
    long countByExample(TbSpUserExample example);

    int deleteByExample(TbSpUserExample example);

    int deleteByPrimaryKey(String userId);

    int insert(TbSpUser record);

    int insertSelective(TbSpUser record);

    List<TbSpUser> selectByExampleWithBLOBs(TbSpUserExample example);

    List<TbSpUser> selectByExample(TbSpUserExample example);

    TbSpUser selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") TbSpUser record, @Param("example") TbSpUserExample example);

    int updateByExampleWithBLOBs(@Param("record") TbSpUser record, @Param("example") TbSpUserExample example);

    int updateByExample(@Param("record") TbSpUser record, @Param("example") TbSpUserExample example);

    int updateByPrimaryKeySelective(TbSpUser record);

    int updateByPrimaryKeyWithBLOBs(TbSpUser record);

    int updateByPrimaryKey(TbSpUser record);

    List<TbSpUser> searchUserByCondition(@Param("userName") String userName, @Param("status") Integer status);
}
