package com.test.config.dev.pojo;

import javax.persistence.*;

@Table(name = "t_biz_factor_def")
@Entity
public class BizFactorDef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 渠道编码
     */
    @Column(name = "channel_code")
    private String channelCode;

    /**
     * 产品编码
     */
    @Column(name = "product_code")
    private String productCode;

    /**
     * 来源
     */
    @Column(name = "source")
    private String source;

    /**
     * 定义类型。

提交订单（a1）



     */
    private String kind;

    @Column(name = "t_entry_id")
    private Integer tEntryId;

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
     * 获取产品编码
     *
     * @return product_code - 产品编码
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 设置产品编码
     *
     * @param productCode 产品编码
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * 获取定义类型。

提交订单（a1）



     *
     * @return kind - 定义类型。

提交订单（a1）



     */
    public String getKind() {
        return kind;
    }

    /**
     * 设置定义类型。

提交订单（a1）



     *
     * @param kind 定义类型。

提交订单（a1）



     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * @return t_entry_id
     */
    public Integer gettEntryId() {
        return tEntryId;
    }

    /**
     * @param tEntryId
     */
    public void settEntryId(Integer tEntryId) {
        this.tEntryId = tEntryId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}