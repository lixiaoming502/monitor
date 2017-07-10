package com.zhonggu.bluevally.processor;

import com.zhonggu.bluevally.RespCode;
import com.zhonggu.bluevally.protocol.ReqTradeWrapper;
import com.zhonggu.bluevally.protocol.SmsSendReqTrade;
import com.zhonggu.bluevally.utils.IoUtils;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.commons.codec.DecoderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by lixiaoming on 2017/7/10.
 * 发送短信验证码
 */
public class SmsSendReqTradeProcessor extends AbstractTradeProcessor{

    private static Logger logger = LoggerFactory.getLogger(SmsSendReqTradeProcessor.class);

    @Override
    protected <T extends SpecificRecordBase> T processBusiness(ReqTradeWrapper reqTradeWrapper) throws DecoderException, IOException {
        SmsSendReqTrade record = new SmsSendReqTrade();

        IoUtils.unWrapReqTradeWrapper(reqTradeWrapper,record);
        String mobile = record.getMobile();
        String clientIp = record.getClientIp();
        logger.info("receive ["+mobile+"|"+clientIp+"]");

        fillRespInfo(RespCode.SUCCESS);
        return null;
    }
}
