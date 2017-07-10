package com.zhonggu.bluevally.processor;

import com.zhonggu.bluevally.protocol.ReqTradeWrapper;
import org.apache.commons.codec.DecoderException;

import java.io.IOException;

/**
 * Created by lixiaoming on 2017/7/10.
 */
public interface TradeProcessor {

    String process(ReqTradeWrapper reqTradeWrapper) throws DecoderException, IOException;
}
