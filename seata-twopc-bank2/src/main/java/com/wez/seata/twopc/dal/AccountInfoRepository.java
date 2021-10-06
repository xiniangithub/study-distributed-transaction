package com.wez.seata.twopc.dal;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * AccountInfoRepository
 *
 * @Author wez
 * @Time 2021/9/30 21:58
 */
@Mapper
@Component
public interface AccountInfoRepository {

    //更新账户金额
    @Update("UPDATE account_info SET account_balance = account_balance + #{amount} WHERE account_no = #{accountNo}")
    int updateAccountBalance(@Param("accountNo") String accountNo, @Param("amount") Double amount);

}
