package com.wez.rocketmq.rocketmq.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * AccountInfoDao
 *
 * @Author wez
 * @Time 2021/10/2 12:02
 */
@Mapper
@Component
public interface AccountInfoDao {

    @Update("update account_info set account_balance=account_balance+#{amount} where account_no=#{accountNo}")
    int updateAccountBalance(@Param("accountNo") String accountNo, @Param("amount") Double amount);

    @Select("select count(1) from de_duplication where tx_no = #{txNo}")
    int isExistTx(String txNo);

    @Insert("insert into de_duplication values(#{txNo},now());")
    int addTx(String txNo);

}
