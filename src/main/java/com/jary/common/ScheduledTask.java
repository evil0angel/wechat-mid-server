package com.jary.common;

import com.jary.model.WxToken;
import com.jary.service.IWxTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by GF on 2017/6/19.
 */
@Component
public class ScheduledTask {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

    @Autowired
    private IWxTokenService wxTokenService;

    /**
     * 每间隔7000s执行一次任务
     */
    @Scheduled(fixedRate = 7000 * 1000)
    public void executeRefreshTask() {
        logger.info("刷新任务开始，当前时间" +
                System.currentTimeMillis() + ".");
        List<WxToken> wxTokenList = wxTokenService.getAllByStatus(wxTokenService.STATUS_NORMAL);
        logger.info("需要刷新的数据有" + wxTokenList.size() + "条");
        ThreadPoolExecutor tpe = ThreadPoolFactory.getExecutor();
        for (WxToken wxToken : wxTokenList) {
            while (getQueueSize(tpe.getQueue()) >= ThreadPoolFactory.getQueueDeep()) {
                System.out.println("队列已满，等3秒再添加任务");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            RefreshThread ttp = new RefreshThread(wxTokenService, wxToken);
            tpe.execute(ttp);
        }
    }

    private synchronized int getQueueSize(Queue queue) {
        return queue.size();
    }

}
