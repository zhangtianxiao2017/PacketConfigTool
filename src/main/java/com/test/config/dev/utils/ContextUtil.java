package com.test.config.dev.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import com.test.config.dev.pojo.FullpathElementDef;
import com.test.config.dev.vo.FullPathElementDefVo;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class ContextUtil {


    /**
     * 处理从数据库中查出的List集合数据，以parentPath分组后，组合成 FullPathElementDefVo 对象
     * 数据变成树结构的数据
     * @param list
     * @return
     */
    public static FullPathElementDefVo packFullPathElement (List<FullpathElementDef> list){
        // 分组结果集 已parentpath作为key
        Map<String,List<FullpathElementDef>> resultMap = new HashMap<>();

        // 遍历List集合
        for (FullpathElementDef fullpathElementDef : list) {
            // 获取集合
            String parentPath = fullpathElementDef.getParentPath();

            if(resultMap.containsKey(parentPath)){
                resultMap.get(parentPath).add(fullpathElementDef);
            }else {
                List<FullpathElementDef> fullpathElementDefs = new LinkedList<FullpathElementDef>();
                fullpathElementDefs.add(fullpathElementDef);
                resultMap.put(parentPath,fullpathElementDefs);
            }
        }

        FullpathElementDef fullpathElementDef = new FullpathElementDef();
        fullpathElementDef.setJsonItemPath("root");
        FullPathElementDefVo fullPathElementDefVo = new FullPathElementDefVo(fullpathElementDef);

        convertToTree(fullPathElementDefVo, resultMap);

        return fullPathElementDefVo;
    }

    public static void convertToTree(FullPathElementDefVo fullPathElementDefVo, Map<String,List<FullpathElementDef>> alls){

        String jsonItemPath = fullPathElementDefVo.getOwnFullPathElement().getJsonItemPath();

        if(!alls.containsKey(jsonItemPath)){
            return;
        }

        List<FullpathElementDef> fullpathElementDefs = alls.get(jsonItemPath);

        for (FullpathElementDef fullpathElementDef : fullpathElementDefs) {

            FullPathElementDefVo fullPathElementDefVo1 = new FullPathElementDefVo(fullpathElementDef);

            convertToTree(fullPathElementDefVo1,alls);

            fullPathElementDefVo.getChildFullPathElement().add(fullPathElementDefVo1);
            fullPathElementDefVo.getChildFullPathElementSorted().add(fullPathElementDefVo1);
        }
    }

    public static void covertToNodeTree(FullPathElementDefVo fullPathElementDefVo, DefaultMutableTreeNode defaultMutableTreeNode){
        FullpathElementDef ownFullPathElement = fullPathElementDefVo.getOwnFullPathElement();
        DefaultMutableTreeNode defaultMutableTreeNode1 = new DefaultMutableTreeNode(fullPathElementDefVo);

        // 注册事件

        defaultMutableTreeNode.add(defaultMutableTreeNode1);

//        List<FullPathElementDefVo> childFullPathElement = fullPathElementDefVo.getChildFullPathElement();
//        for (FullPathElementDefVo pathElementDefVo : childFullPathElement) {
//            covertToNodeTree(pathElementDefVo, defaultMutableTreeNode1);
//        }

        TreeSet<FullPathElementDefVo> childFullPathElementSorted = fullPathElementDefVo.getChildFullPathElementSorted();
        for (FullPathElementDefVo pathElementDefVo : childFullPathElementSorted) {
            covertToNodeTree(pathElementDefVo, defaultMutableTreeNode1);
        }
    }


    public static void generateJSONFile(String json, String fileName){
        // 生成报文文件 packet.json
        File file = new File("F:\\packetconfig\\"+fileName+ StringUtil.getNowDateString() +".json");
        try {
            FileWriter fileWriter = new FileWriter(file);

            fileWriter.write(json);

            fileWriter.close();
        } catch (Exception  e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 根据JSON字符串生成JSON文件
     * @param json
     */
    public static void generateJSONFile(String json){
        // 生成报文文件 packet.json
        File file = new File("F:\\packetconfig\\packetconfig"+ StringUtil.getNowDateString() +".json");
        try {
            FileWriter fileWriter = new FileWriter(file);

            fileWriter.write(json);

            fileWriter.close();
        } catch (Exception  e1) {
            e1.printStackTrace();
        }
    }


    /**
     * 生成Json
     * @param fullPathElementDefVo
     * @return
     */
    public static String convertFullPathElementDefVoToJson(FullPathElementDefVo fullPathElementDefVo){
        // 将根节点换为 endorsements
        fullPathElementDefVo.getOwnFullPathElement().setJsonItemPath("endorsements");
        fullPathElementDefVo.getOwnFullPathElement().setDesc("批单列表");

        // 遍历 fullpathelementdefvo1 生成 map
        Map<String, Object> map = ContextUtil.convertFullPathElementDefVoToMap(fullPathElementDefVo);

        // 加入 source  operator entryType 等信息 和根节点同级
        map.put("source", "来源：pc端（00） 移动端（01） 合作方（02）");
        map.put("operator", "批单录入人员Code");
        map.put("entryType", "录入方式\\n单笔录入（00）/批量导入（01）/事后补录（03）'");

        // 对map进行排序
        Comparator<Map.Entry<String, Object>> valueComparatordesc = (o1, o2) -> ObjectNum.getObjectTypeNum(o1.getValue())-ObjectNum.getObjectTypeNum(o2.getValue()) >= 0 ? 1 : -1;
        List<Map.Entry<String, Object>> list = new ArrayList<Map.Entry<String, Object>>(map.entrySet());
        Collections.sort(list,valueComparatordesc);

        HashMap hashMap = new HashMap();

        return JSON.toJSONString(map, SerializerFeature.PrettyFormat);
    }


    /**
     * 根据FullPathElementDefVo生成Map
     * @param fullPathElementDefVo
     * @return
     */
    public static Map<String,Object> convertFullPathElementDefVoToMap(FullPathElementDefVo fullPathElementDefVo){


        // 根据类型进行排序
        Map<String, Object> resultMap = new TreeMap<>();


        FullpathElementDef ownFullPathElement = fullPathElementDefVo.getOwnFullPathElement();
        String key = ownFullPathElement.getJsonItemPath();
        String desc = ownFullPathElement.getDesc();

        // 对 key 进行统一处理，
        key = key.substring(key.lastIndexOf(".")+1);

        // 获取下层的数据
        TreeSet<FullPathElementDefVo> childFullPathElementSorted = fullPathElementDefVo.getChildFullPathElementSorted();

        if(childFullPathElementSorted.size() > 0){

            // 当时数组和对象类型时 map的key中存放 key+desc的信息
            key = key + "::" +desc;

            Map<String,Object> tempMap = new HashMap<>(7);
            for (FullPathElementDefVo pathElementDefVo : childFullPathElementSorted) {
                Map<String, Object> stringObjectMap = convertFullPathElementDefVoToMap(pathElementDefVo);
                tempMap.putAll(stringObjectMap);
            }

            // 判断当前对象的数据类型 是数组还是对象
            if("01".equals(ownFullPathElement.getValueType())){
                resultMap.put(key , tempMap);
            }else {
                List tempList = new ArrayList();
                tempList.add(tempMap);

                // 为数组类型时
                resultMap.put(key , tempList);
            }

        }else {
            // 当前数据类型为字符串类型
            resultMap.put(key , desc);
        }


        return resultMap;
    }


    public static void main(String[] args){
        Date date = new Date();
        System.out.println(date);

        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddhh:mm:ss");
        System.out.println(sd.format(date));


        // 验证TreeMap排序
        TreeMap<String,Object> treeMap = new TreeMap<>();
        treeMap.put("1",new ArrayList<>());
        treeMap.put("2",new String());
        treeMap.put("3",new HashMap<>());


        System.out.println(treeMap);

    }

    /**
     * 根据集合生成excel表格
     * @param list
     */
    public static void convertFullPathElementDefVoToExcel(List<FullpathElementDef> list){
        File xlsFile = new File("F:\\packetconfig\\packetconfig"+StringUtil.getNowDateString()+".xls");

        // 创建一个工作簿
        try {
            WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);

            // 创建一个工作表
            WritableSheet sheet = workbook.createSheet("sheet1", 0);

            int row = list.size();

            // 生成Excel表头信息
            sheet.addCell(new Label(0,0,"id"));
            sheet.addCell(new Label(1,0,"no"));
            sheet.addCell(new Label(2,0,"t_fullpath_element_def_id"));
            sheet.addCell(new Label(3,0,"t_biz_entry_def_id"));
            sheet.addCell(new Label(4,0,"is_display"));
            sheet.addCell(new Label(5,0,"can_modify"));
            sheet.addCell(new Label(6,0,"is_must"));


            sheet.addCell(new Label(7,0,"描述信息"));


            // 遍历集合
            for(int i = 0; i < row; i++){

                // 写入一行数据
                FullpathElementDef fullpathElementDef = list.get(i);

                sheet.addCell(new Label(2, i+1, fullpathElementDef.getId()+""));

                sheet.addCell(new Label(4, i+1,"N"));
                sheet.addCell(new Label(5, i+1,"N"));
                sheet.addCell(new Label(6, i+1,"Y"));

                sheet.addCell(new Label(7, i+1, fullpathElementDef.getDesc()+""));

            }

            workbook.write();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
