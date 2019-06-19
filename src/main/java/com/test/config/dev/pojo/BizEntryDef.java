package com.test.config.dev.pojo;

import javax.persistence.*;

@Table(name = "t_biz_entry_def")
@Entity
public class BizEntryDef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 入口名称
     */
    @Column(name = "entry_name")
    private String entryName;

    /**
     * 入口描述
     */
    @Column(name = "entry_desc")
    private String entryDesc;

    /**
     * 渠道编码
     */
    @Column(name = "channel_code")
    private String channelCode;

    /**
     * 渠道名称
     */
    @Column(name = "channel_name")
    private String channelName;

    /**
     * 项目编码
     */
    @Column(name = "project_code")
    private String projectCode;

    /**
     * 项目名称
     */
    @Column(name = "project_name")
    private String projectName;

//    /**
//     * 批单类型
//     */
//    @Column(name = "endorsements_type")
//    private String endorsementsType;
//
//    /**
//     * @return endorsements_type
//     */
//    public String getEndorsementsType() {
//        return endorsementsType;
//    }
//
//    /**
//     *
//     * @param endorsementsType
//     */
//    public void setEndorsementsType(String endorsementsType) {
//        this.endorsementsType = endorsementsType;
//    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取入口名称
     *
     * @return entry_name - 入口名称
     */
    public String getEntryName() {
        return entryName;
    }

    /**
     * 设置入口名称
     *
     * @param entryName 入口名称
     */
    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    /**
     * 获取入口描述
     *
     * @return entry_desc - 入口描述
     */
    public String getEntryDesc() {
        return entryDesc;
    }

    /**
     * 设置入口描述
     *
     * @param entryDesc 入口描述
     */
    public void setEntryDesc(String entryDesc) {
        this.entryDesc = entryDesc;
    }

    /**
     * 获取渠道编码
     *
     * @return channel_code - 渠道编码
     */
    public String getChannelCode() {
        return channelCode;
    }

    /**
     * 设置渠道编码
     *
     * @param channelCode 渠道编码
     */
    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    /**
     * 获取渠道名称
     *
     * @return channel_name - 渠道名称
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * 设置渠道名称
     *
     * @param channelName 渠道名称
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * 获取项目编码
     *
     * @return project_code - 项目编码
     */
    public String getProjectCode() {
        return projectCode;
    }

    /**
     * 设置项目编码
     *
     * @param projectCode 项目编码
     */
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    /**
     * 获取项目名称
     *
     * @return project_name - 项目名称
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * 设置项目名称
     *
     * @param projectName 项目名称
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}