package com.zhonggu.bluevally.processor;

import com.zhonggu.bluevally.RespCode;
import com.zhonggu.bluevally.protocol.ReqTradeWrapper;
import com.zhonggu.bluevally.protocol.RespTradeWrapper;
import com.zhonggu.bluevally.utils.IoUtils;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by lixiaoming on 2017/7/10.
 */
public abstract class AbstractTradeProcessor implements TradeProcessor{

    private static Logger logger = LoggerFactory.getLogger(AbstractTradeProcessor.class);

    RespTradeWrapper respTradeWrapper = null;
    public String process(ReqTradeWrapper reqTradeWrapper) throws DecoderException, IOException {
        logger.info("process entry!");
        respTradeWrapper = new RespTradeWrapper();
        byte[] tradWrapperBytes = null;
        try{
            SpecificRecordBase recordBase =  processBusiness(reqTradeWrapper);
            if ((recordBase!=null)){
                byte[] toResp = IoUtils.convertToBytes(recordBase);
                String md5 = DigestUtils.md5Hex(toResp);
                respTradeWrapper.setSign(md5);
                final ByteBuffer wrap = ByteBuffer.wrap(toResp);
                respTradeWrapper.setTradeBody(wrap);
            }
        }catch (Exception e){
            logger.info("",e);
            fillRespInfo(RespCode.EXCEPTION);
        }

        tradWrapperBytes = IoUtils.convertToBytes(respTradeWrapper);
        return Hex.encodeHexString(tradWrapperBytes);
    }

    protected void fillRespInfo(RespCode respCode){
        respTradeWrapper.setRespCode(respCode.getCode());
        respTradeWrapper.setRespInfo(respCode.getName());
    }

    protected abstract<T extends SpecificRecordBase> T processBusiness(ReqTradeWrapper reqTradeWrapper) throws DecoderException, IOException;
}
