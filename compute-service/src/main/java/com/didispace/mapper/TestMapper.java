package com.didispace.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.didispace.model.SecuTrade;
import com.didispace.model.User;

@Mapper
public interface TestMapper {
   @Select("select ID id,OperatorName name from HS_OPRightList_All where OperatorName=#{name} ")
	List<User> findbyName(@Param("name") String name);
   
   @Select(" select FundSecuAbbr,SecuCode,SecuAbbr,EntrustDirection,DealPrice,DealVolume,EndDate endDateJson from HS_DailyTrade_Total where EndDate>=#{startDate} and EndDate<=#{endDate} order by EndDate desc")
   List<SecuTrade> Trade(@Param("startDate") Date startDate,@Param("endDate") Date endDate);
}
