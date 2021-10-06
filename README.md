# 分布式事务

# 基于Seata实现2PC
案例业务：模拟张三给李四转账。

案例代码：
- seata-twopc-bank1
- seata-twopc-bank2

测试场景：
1. 张三向李四转账成功；
    访问：http://127.0.0.1:8080/service-bank1/transfer?amount=1
2. 李四事务失败，张三回滚事务成功；
    访问：http://127.0.0.1:8080/service-bank1/transfer?amount=2
3. 张三事务失败，李四回滚事务成功；
    访问：http://127.0.0.1:8080/service-bank1/transfer?amount=3
4. 分支事务超时回滚测试；
    在bank2服务打断点；
    访问：http://127.0.0.1:8080/service-bank1/transfer?amount=1
    停掉bank2服务

# 基于RocketMQ实现可靠消息最终一致性
案例业务：模拟张三给李四转账。

案例代码：
- rocketmq-transaction-bank1
- rocketmq-transaction-bank2

测试场景：
1. 张三向李四转账成功；
    访问：http://localhost:8080/rocketmq-service-bank1/transfer?accountNo=1&amount=1
2. 张三转账失败，事务回滚，消息回滚，李四未接收到转账消息；
    访问：http://localhost:8080/rocketmq-service-bank1/transfer?accountNo=1&amount=3
3. 张三转账成功，李四执行异常，RocketMQ自动重发消息给李四；
    访问：http://localhost:8080/rocketmq-service-bank1/transfer?accountNo=1&amount=4

# 基于RocketMQ实现最大努力通知
案例业务：用户充值。

案例代码：
- rocketmq-notify-account
- rocketma-notify-pay

测试场景：
1. 给账户充值，手动查询充值记录，更新账户充值结果；
    充值访问：http://127.0.0.1:8081/rocketmq-notify-pay/paydo?accountNo=1&payAmount=1
    手动查询充值揭露：http://127.0.0.1:8080/rocketmq-notify-account/payresult?txNo=9d7de792-6853-4ba5-9714-c71919b24f49
    
2. 给账户充值，通过消息通知充值记录，更新账户充值结果；
    充值访问：http://127.0.0.1:8081/rocketmq-notify-pay/paydo?accountNo=1&payAmount=1
