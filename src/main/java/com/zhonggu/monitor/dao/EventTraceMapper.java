package com.zhonggu.monitor.dao;

import com.zhonggu.monitor.model.EventTrace;
import com.zhonggu.monitor.model.EventTraceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EventTraceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_event_trace
     *
     * @mbggenerated Mon Jun 11 17:28:36 CST 2018
     */
    int countByExample(EventTraceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_event_trace
     *
     * @mbggenerated Mon Jun 11 17:28:36 CST 2018
     */
    int deleteByExample(EventTraceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_event_trace
     *
     * @mbggenerated Mon Jun 11 17:28:36 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_event_trace
     *
     * @mbggenerated Mon Jun 11 17:28:36 CST 2018
     */
    int insert(EventTrace record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_event_trace
     *
     * @mbggenerated Mon Jun 11 17:28:36 CST 2018
     */
    int insertSelective(EventTrace record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_event_trace
     *
     * @mbggenerated Mon Jun 11 17:28:36 CST 2018
     */
    List<EventTrace> selectByExample(EventTraceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_event_trace
     *
     * @mbggenerated Mon Jun 11 17:28:36 CST 2018
     */
    EventTrace selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_event_trace
     *
     * @mbggenerated Mon Jun 11 17:28:36 CST 2018
     */
    int updateByExampleSelective(@Param("record") EventTrace record, @Param("example") EventTraceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_event_trace
     *
     * @mbggenerated Mon Jun 11 17:28:36 CST 2018
     */
    int updateByExample(@Param("record") EventTrace record, @Param("example") EventTraceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_event_trace
     *
     * @mbggenerated Mon Jun 11 17:28:36 CST 2018
     */
    int updateByPrimaryKeySelective(EventTrace record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_event_trace
     *
     * @mbggenerated Mon Jun 11 17:28:36 CST 2018
     */
    int updateByPrimaryKey(EventTrace record);
}