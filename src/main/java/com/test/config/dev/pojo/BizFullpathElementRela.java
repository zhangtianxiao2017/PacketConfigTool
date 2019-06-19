package com.test.config.dev.pojo;

import javax.persistence.*;

@Table(name = "t_biz_fullpath_element_rela")
@Entity
public class BizFullpathElementRela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 顺序
     */
    private Integer no;

    @Column(name = "t_fullpath_element_def_id")
    private Integer tFullpathElementDefId;

    @Column(name = "t_biz_entry_def_id")
    private Integer tBizEntryDefId;

    /**
     * 是否展示字段
     */
    @Column(name = "is_display", columnDefinition = "CHAR")
    private String isDisplay;

    /**
     * 是否可以批改
     */
    @Column(name = "can_modify",columnDefinition = "CHAR")
    private String canModify;

    /**
     * 是否必填
     */
    @Column(name = "is_must",columnDefinition = "CHAR")
    private String isMust;

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
     * 获取顺序
     *
     * @return no - 顺序
     */
    public Integer getNo() {
        return no;
    }

    /**
     * 设置顺序
     *
     * @param no 顺序
     */
    public void setNo(Integer no) {
        this.no = no;
    }

    /**
     * @return t_fullpath_element_def_id
     */
    public Integer gettFullpathElementDefId() {
        return tFullpathElementDefId;
    }

    /**
     * @param tFullpathElementDefId
     */
    public void settFullpathElementDefId(Integer tFullpathElementDefId) {
        this.tFullpathElementDefId = tFullpathElementDefId;
    }

    /**
     * @return t_biz_entry_def_id
     */
    public Integer gettBizEntryDefId() {
        return tBizEntryDefId;
    }

    /**
     * @param tBizEntryDefId
     */
    public void settBizEntryDefId(Integer tBizEntryDefId) {
        this.tBizEntryDefId = tBizEntryDefId;
    }

    /**
     * 获取是否展示字段
     *
     * @return isDisplay - 是否展示字段
     */
    public String getIsDisplay() {
        return isDisplay;
    }

    /**
     * 设置是否展示字段
     *
     * @param isDisplay 是否展示字段
     */
    public void setIsDisplay(String isDisplay) {
        this.isDisplay = isDisplay;
    }

    /**
     * 获取是否可以批改
     *
     * @return canModify - 是否可以批改
     */
    public String getCanModify() {
        return canModify;
    }

    /**
     * 设置是否可以批改
     *
     * @param canModify 是否可以批改
     */
    public void setCanModify(String canModify) {
        this.canModify = canModify;
    }

    /**
     * 获取是否必填
     *
     * @return isMust - 是否必填
     */
    public String getIsMust() {
        return isMust;
    }

    /**
     * 设置是否必填
     *
     * @param isMust 是否必填
     */
    public void setIsMust(String isMust) {
        this.isMust = isMust;
    }
}