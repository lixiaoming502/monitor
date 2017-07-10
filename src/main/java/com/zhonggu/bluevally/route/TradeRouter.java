package com.zhonggu.bluevally.route;

import com.zhonggu.bluevally.processor.SmsSendReqTradeProcessor;
import com.zhonggu.bluevally.processor.TradeProcessor;
import com.zhonggu.bluevally.protocol.ReqTradeWrapper;
import com.zhonggu.bluevally.protocol.SmsSendReqTrade;
import com.zhonggu.bluevally.utils.IoUtils;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by lixiaoming on 2017/7/10.
 */
public class TradeRouter {

    private static Logger logger = LoggerFactory.getLogger(TradeRouter.class);

    private static final long GAP = 20;

    private static String processTrade(String tradeCode, ReqTradeWrapper reqTradeWrapper) throws DecoderException, IOException {
        long t1 = System.currentTimeMillis();
        TradeProcessor tradeProcessor = findProcessor(tradeCode);
        final long gap = (System.currentTimeMillis() - t1) / 1000;
        String resp = tradeProcessor.process(reqTradeWrapper);
        logger.info("route ["+tradeCode+"] process cost ["+gap+"]");
        if(gap >= GAP){
            logger.warn("route ["+tradeCode+"] process cost-to-long ["+gap+"]");
        }
        return resp;
    }

    private static TradeProcessor findProcessor(String tradeCode) {
        if(tradeCode.equals(SmsSendReqTrade.class.getSimpleName())){
            return new SmsSendReqTradeProcessor();
        }
        throw new IllegalArgumentException("tradeCode参数错误！未找到合适的处理器！tradeCode["+tradeCode+"]");
    }

    public static String processRequest(String request) throws DecoderException, IOException {
        byte[] rev = Hex.decodeHex(request.toCharArray());
        ReqTradeWrapper reqTradeWrapper = new ReqTradeWrapper();
        IoUtils.convertToRecord(rev,reqTradeWrapper);
        //获取交易码，开始做路由转换
        String tradeCode = reqTradeWrapper.getTradeCode();
        return processTrade(tradeCode,reqTradeWrapper);
    }
}
