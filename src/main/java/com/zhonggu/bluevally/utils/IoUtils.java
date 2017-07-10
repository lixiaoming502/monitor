package com.zhonggu.bluevally.utils;

import com.zhonggu.bluevally.protocol.ReqTradeWrapper;
import com.zhonggu.bluevally.protocol.RespTradeWrapper;
import org.apache.avro.Schema;
import org.apache.avro.io.*;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.reflect.ReflectDatumReader;
import org.apache.avro.reflect.ReflectDatumWriter;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by lixiaoming on 2017/7/7.
 */
public class IoUtils {

    public static<T extends SpecificRecordBase> String wrapReqTrade(T record) throws IOException {
        byte[] tradeBody = convertToBytes(record);
        ReqTradeWrapper reqTradeWrapper = new ReqTradeWrapper();
        //reqTradeWrapper.setSysId(AppConfig.SYS_ID);
        reqTradeWrapper.setTradeCode(record.getClass().getSimpleName());
        String md5 = DigestUtils.md5Hex(tradeBody);
        ByteBuffer tradeBodyBufer = ByteBuffer.wrap(tradeBody);
        reqTradeWrapper.setTradeBody(tradeBodyBufer);
        reqTradeWrapper.setSign(md5);
        byte[] tradWrapperBytes = convertToBytes(reqTradeWrapper);
        return Hex.encodeHexString(tradWrapperBytes);
    }


    public static <T extends SpecificRecordBase> void  unWrapRespRecord(String recvStr, T record) throws IOException, DecoderException {
        RespTradeWrapper recvWrapper = new RespTradeWrapper();
        convertToRecord(Hex.decodeHex(recvStr.toCharArray()),recvWrapper);
        convertToRecord(recvWrapper.getTradeBody().array(),record);
    }


    public static<T extends SpecificRecordBase> String wrapRespTrade(T record) throws IOException {
        byte[] toResp = convertToBytes(record);
        RespTradeWrapper respTradeWrapper = new RespTradeWrapper();
        String md5 = DigestUtils.md5Hex(toResp);
        respTradeWrapper.setSign(md5);
        final ByteBuffer wrap = ByteBuffer.wrap(toResp);
        respTradeWrapper.setTradeBody(wrap);
        byte[] tradWrapperBytes = convertToBytes(respTradeWrapper);
        return Hex.encodeHexString(tradWrapperBytes);
    }

    public static<T extends SpecificRecordBase> void unWrapReqTrade(String requestStr, T record) throws DecoderException, IOException {
        byte[] recv = Hex.decodeHex(requestStr.toCharArray());
        ReqTradeWrapper reqTradeWrapper = new ReqTradeWrapper();
        convertToRecord(recv,reqTradeWrapper);
        byte[] tradeBytes = reqTradeWrapper.getTradeBody().array();
        convertToRecord(tradeBytes,record);
    }

    public static<T extends SpecificRecordBase> void unWrapReqTradeWrapper(ReqTradeWrapper reqTradeWrapper, T record) throws DecoderException, IOException {
        byte[] tradeBytes = reqTradeWrapper.getTradeBody().array();
        convertToRecord(tradeBytes,record);
    }


    public static<T extends SpecificRecordBase> T convertToRecord(byte[] recv,T record) throws IOException {
        ReflectData reflectData = ReflectData.get();
        Schema schema = reflectData.getSchema(record.getClass());
        ReflectDatumReader<T> reader = new ReflectDatumReader<T>(schema);
        Decoder decoder = DecoderFactory.get().binaryDecoder(recv, null);
        return reader.read(record, decoder);
    }

    public static<T extends SpecificRecordBase> byte[] convertToBytes(T record) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Encoder encoder = EncoderFactory.get().binaryEncoder(os, null);
        DatumWriter<T> writer = new ReflectDatumWriter<T>(record.getSchema());
        writer.write(record, encoder);
        encoder.flush();
        return os.toByteArray();
    }
}
