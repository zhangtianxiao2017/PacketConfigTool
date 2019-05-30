package com.test.config.dev.pojo;

import org.springframework.stereotype.Component;

import javax.persistence.*;


@Table(name = "t_fullpath_element_def")
@Entity
public class FullpathElementDef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "json_item_path")
    private String jsonItemPath;

    @Column(name = "parent_path")
    private String parentPath;

    /**
     * 字段类型
     主字段（00）/扩展字段（01）
     */
    @Column(name = "col_type")
    private String colType;

    /**
     * 值类型
     字符串（00）、数组（01）
     */
    @Column(name = "value_type")
    private String valueType;

    /**
     * 对应上下文属性
     */
    @Column(name = "fullpath_element")
    private String fullpathElement;

    private String desc;

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
     * @return json_item_path
     */
    public String getJsonItemPath() {
        return jsonItemPath;
    }

    /**
     * @param jsonItemPath
     */
    public void setJsonItemPath(String jsonItemPath) {
        this.jsonItemPath = jsonItemPath;
    }

    /**
     * @return parent_path
     */
    public String getParentPath() {
        return parentPath;
    }

    /**
     * @param parentPath
     */
    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    /**
     * 获取字段类型
     主字段（00）/扩展字段（01）
     *
     * @return col_type - 字段类型
    主字段（00）/扩展字段（01）
     */
    public String getColType() {
        return colType;
    }

    /**
     * 设置字段类型
     主字段（00）/扩展字段（01）
     *
     * @param colType 字段类型
    主字段（00）/扩展字段（01）
     */
    public void setColType(String colType) {
        this.colType = colType;
    }

    /**
     * 获取值类型
     字符串（00）、数组（01）
     *
     * @return value_type - 值类型
    字符串（00）、数组（01）
     */
    public String getValueType() {
        return valueType;
    }

    /**
     * 设置值类型
     字符串（00）、数组（01）
     *
     * @param valueType 值类型
    字符串（00）、数组（01）
     */
    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    /**
     * 获取对应上下文属性
     *
     * @return fullpath_element - 对应上下文属性
     */
    public String getFullpathElement() {
        return fullpathElement;
    }

    /**
     * 设置对应上下文属性
     *
     * @param fullpathElement 对应上下文属性
     */
    public void setFullpathElement(String fullpathElement) {
        this.fullpathElement = fullpathElement;
    }

    /**
     * @return desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

}
