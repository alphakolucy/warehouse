package com.warehouse.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.warehouse.model.*;
import com.warehouse.service.WarehouseStockService;
import com.warehouse.service.repository.*;

import com.warehouse.util.enums.EntrustOrderState;
import com.warehouse.util.tools.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 仓库库存 ServiceImpl
 */
@Service
public class WarehouseStockServiceImpl implements WarehouseStockService {

    @Autowired
    private WarehouseStockRepository warehouseStockRepository;

    private static Logger LOG = LoggerFactory.getLogger(WarehouseStockServiceImpl.class);

    @Transactional
    @Override
    public Boolean saveOrUpdate(List<WarehouseStock> warehouseStocks, String opOrderNo, String opOrderId, Integer opType) {

        boolean bnl = false;

        for (WarehouseStock stock : warehouseStocks) { 
            //实体作为查询条件，new一个新的对象，只需要设置id,避免原对象关联层级太多导致查询异常
            Warehouse queryWarehouse = new Warehouse();
            queryWarehouse.setId(stock.getWarehouseLevel().getId());

            Product queryProduct = new Product();
            queryProduct.setId(stock.getProduct().getId());

            Customer queryCustomer = new Customer();
            queryCustomer.setId(stock.getCustomer().getId());

            WarehouseStock warehouseStock = new WarehouseStock();
            warehouseStock.setProduct(queryProduct); //物资
            warehouseStock.setWarehouseLevel(queryWarehouse); //库层
            warehouseStock.setCustomer(queryCustomer); //客户

            WarehouseStockLog log = new WarehouseStockLog();
            List<WarehouseStockLog> logList = new ArrayList<WarehouseStockLog>();

            Example<WarehouseStock> example = Example.of(warehouseStock); 
            warehouseStock = this.warehouseStockRepository.findOne(example);
            if (warehouseStock != null) {

                warehouseStock.setNum(warehouseStock.getNum().add(stock.getNum()));
                warehouseStock.setTonNum(warehouseStock.getTonNum().add(stock.getTonNum()));
                warehouseStock.setCreateMan(stock.getCreateMan());
                warehouseStock.setCreateManId(stock.getCreateManId());

                log.setWarehouseStock(warehouseStock);
                log.setOpOrderNo(opOrderNo);
                log.setCreateMan(stock.getCreateMan());
                log.setCreateManId(stock.getCreateManId());
                log.setOpNum(stock.getNum());
                log.setOpTonNum(stock.getTonNum());
                log.setBlaNum(warehouseStock.getNum());
                log.setBlaTonNum(warehouseStock.getTonNum());
                log.setOpType(opType);
                log.setOpOrderNo(opOrderNo);
                log.setOpOrderId(opOrderId);

                logList.add(log);
                warehouseStock.setWarehouseStockLogSet(logList);

                if (this.warehouseStockRepository.save(warehouseStock) != null) {
                    bnl = true;
                } else {
                    LOG.error("更新库存失败");
                    bnl = false;
                    break;
                }

            } else {
                warehouseStock = new WarehouseStock();
                warehouseStock.setProduct(stock.getProduct()); //物资
                warehouseStock.setWarehouseLevel(stock.getWarehouseLevel()); //库层
                warehouseStock.setWarehouseSite(stock.getWarehouseLevel().getParent().getParent().getParent()); //仓库
                warehouseStock.setWarehouseArea(stock.getWarehouseLevel().getParent().getParent()); //库区
                warehouseStock.setWarehouse(stock.getWarehouseLevel().getParent()); //库位
                warehouseStock.setCustomer(stock.getCustomer()); //客户
                warehouseStock.setNum(stock.getNum());
                warehouseStock.setTonNum(stock.getTonNum());
                warehouseStock.setCreateMan(stock.getCreateMan());
                warehouseStock.setCreateManId(stock.getCreateManId());

                log.setWarehouseStock(warehouseStock);
                log.setOpOrderNo(opOrderNo);
                log.setCreateMan(stock.getCreateMan());
                log.setCreateManId(stock.getCreateManId());
                log.setOpNum(stock.getNum());
                log.setOpTonNum(stock.getTonNum());
                log.setBlaNum(warehouseStock.getNum());
                log.setBlaTonNum(warehouseStock.getTonNum());
                log.setOpType(opType);
                log.setOpOrderNo(opOrderNo);
                log.setOpOrderId(opOrderId);

                logList.add(log);
                warehouseStock.setWarehouseStockLogSet(logList);

                if (this.warehouseStockRepository.save(warehouseStock) != null) {
                    bnl = true;
                } else {
                    LOG.error("更新库存失败");
                    bnl = false;
                    break;
                }
            }
        }

        return bnl;
    }

    @Override
    public Page<JSONObject> sumProductByOrderSates(List<Integer> orderStates,
                                                   String productId,
                                                   String productName,
                                                   String warehouseId,
                                                   String customerId,
                                                   String spec,
                                                   String model,
                                                   String material,
                                                   String maker,
                                                   Pageable pageable) {
        //默认查新建和确认
        if(orderStates == null || orderStates.size() <=0 ){
            orderStates = new ArrayList<>();
            orderStates.add(EntrustOrderState.NEW_CREATE.getValue());
            orderStates.add(EntrustOrderState.CONFIRM.getValue());
        }

        List<JSONObject> jsonObjectList = new ArrayList<>();
        Page<Object[]> pageObjects = this.warehouseStockRepository.sumProductByOrderSates(orderStates,
                productId,productName,warehouseId,customerId,
                spec, model, material, maker,pageable);

        for(int i=0;i<pageObjects.getContent().size();i++){
            Object[] objects = pageObjects.getContent().get(i);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("productId",objects[0]);//物资id
            jsonObject.put("productName",objects[1]);//物资id
            jsonObject.put("sumNum",objects[2]);//委托占用件数
            jsonObject.put("sumTonNum",objects[3]);//委托占用吨数
            jsonObject.put("warehouseId",objects[4]);//仓库id
            jsonObject.put("warehouseName",objects[5]);//仓库名称

            jsonObject.put("customerId",objects[6]);//客户id
            jsonObject.put("customerName",objects[7]);//客户名称

            jsonObject.put("spec",objects[8]);//规格
            jsonObject.put("model",objects[9]);//型号
            jsonObject.put("material",objects[10]);//材料
            jsonObject.put("maker",objects[11]);//厂家
            jsonObject.put("unit",objects[12]);//单位

            jsonObject.put("cateId",objects[13]);//物资分类id
            jsonObject.put("productCateName",objects[14]);//物资分类名称

            jsonObject.put("stockNumTotal",objects[15]);//当前库存件数
            jsonObject.put("stockTonNumTotal",objects[16]);//当前库存吨数
            jsonObject.put("lockNumTotal",objects[17]);//锁定件数
            jsonObject.put("lockTonNumTotal",objects[18]);//锁定吨数
            jsonObject.put("rationale",objects[19]);//物资理重
            jsonObject.put("canUseNum",objects[20]);//可用件数
            jsonObject.put("canUseTonNum",objects[21]);//可用吨数


            jsonObjectList.add(jsonObject);
        }
        //重新组装分页信息
        return new PageImpl<JSONObject>(jsonObjectList,pageable,pageObjects.getTotalElements());
    }

    @Override
    public Map<String, BigDecimal> getStockNumAndTon(String warehouseSiteId,
                                                     String warehouseAreaId,
                                                     String warehouseId,
                                                     String warehouseLevelId,
                                                     String productId,
                                                     String customerId) {

        WarehouseStock warehouseStock = new WarehouseStock();
        if(EmptyUtil.isNotEmpty(productId)){
            Product queryProduct = new Product();
            queryProduct.setId(productId);
            warehouseStock.setProduct(queryProduct);
        }

        if(EmptyUtil.isNotEmpty(customerId)){
            Customer customerQuery = new Customer();
            customerQuery.setId(customerId);
            warehouseStock.setCustomer(customerQuery);
        }

        if(EmptyUtil.isNotEmpty(warehouseSiteId)){
            Warehouse warehouseSiteQuery = new Warehouse();
            warehouseSiteQuery.setId(warehouseSiteId);
            warehouseStock.setWarehouseSite(warehouseSiteQuery);
        }

        if(EmptyUtil.isNotEmpty(warehouseAreaId)){
            Warehouse warehouseAreaQuery = new Warehouse();
            warehouseAreaQuery.setId(warehouseAreaId);
            warehouseStock.setWarehouseArea(warehouseAreaQuery);
        }

        if(EmptyUtil.isNotEmpty(warehouseId)){
            Warehouse warehouseQuery = new Warehouse();
            warehouseQuery.setId(warehouseId);
            warehouseStock.setWarehouse(warehouseQuery);
        }

        if(EmptyUtil.isNotEmpty(warehouseLevelId)){
            Warehouse warehouseLevelQuery = new Warehouse();
            warehouseLevelQuery.setId(warehouseLevelId);
            warehouseStock.setWarehouseLevel(warehouseLevelQuery);
        }

        Example<WarehouseStock> warehouseStockExample = Example.of(warehouseStock);
        List<WarehouseStock> stockList = this.warehouseStockRepository.findAll(warehouseStockExample);
        BigDecimal stockSumNum = BigDecimal.ZERO;//统计件数
        BigDecimal stockSumTonNum = BigDecimal.ZERO;//统计吨数
        for(int j=0;j<stockList.size();j++){
            if(stockList.get(j).getNum()!=null){
                stockSumNum = stockSumNum.add(stockList.get(j).getNum());
                stockSumTonNum = stockSumTonNum.add(stockList.get(j).getTonNum());
            }
        }
        Map<String,BigDecimal> map = new HashMap<>();
        map.put("stockSumNum",stockSumNum);
        map.put("stockSumTonNum",stockSumTonNum);
        return map;
    }

    @Override
    public Map<String,Object> totalNumByOrderSates(List<Integer> orderStates,
                                                   String productId, String productName,
                                                   String warehouseId, String customerId,
                                                   String spec,
                                                   String model,
                                                   String material,
                                                   String maker) {
        Map<String,Object> map = new HashMap<>();
        //默认查新建和确认
        if(orderStates == null || orderStates.size() <=0 ){
            orderStates = new ArrayList<>();
            orderStates.add(EntrustOrderState.NEW_CREATE.getValue());
            orderStates.add(EntrustOrderState.CONFIRM.getValue());
        }

        List<Object[]> objects = this.warehouseStockRepository.totalNumByOrderSates(orderStates,
                productId,productName,warehouseId,customerId,spec,
                model,
                material,
                maker);
        //统计只有一行数据，所以取第一行就行了.
        Object[] object = (objects.size()>0)?objects.get(0):new Object[1];
        map.put("occupyNumTotal",object[0]!=null?object[0]:"");//委托占用件数
        map.put("occupyTonTotal",object[1]!=null?object[1]:"");//委托占用吨数
        map.put("stockNumTotal",object[2]!=null?object[2]:"");//当前库存件数
        map.put("stockTonTotal",object[3]!=null?object[3]:"");//当前库存吨数
        map.put("lockNumTotal",object[4]!=null?object[4]:"");//锁定件数
        map.put("lockTonTotal",object[5]!=null?object[5]:"");//锁定吨数
        map.put("canNumTotal",object[6]!=null?object[6]:"");//可用件数
        map.put("canTonTotal",object[7]!=null?object[7]:"");//可用吨数
        return map;

    }

    @Override
    public Page<JSONObject> sumByNoCustomer(String productId, String warehouseSiteId, String warehouseAreaId, String warehouseId, String warehouseLevelId, Pageable pageable) {

        List<JSONObject> jsonObjectList = new ArrayList<>();
        Page<Object[]> pageObjects = this.warehouseStockRepository.sumByNoCustomer(
                productId,warehouseSiteId,warehouseAreaId,warehouseId,warehouseLevelId,pageable);

        for(int i=0;i<pageObjects.getContent().size();i++){
            Object[] objects = pageObjects.getContent().get(i);

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("warehouseSiteId",objects[0]);//仓库id
            jsonObject.put("warehouseSiteName",objects[1]);//仓库名称

            jsonObject.put("warehouseAreaId",objects[2]);//库区id
            jsonObject.put("warehouseAreaName",objects[3]);//名称

            jsonObject.put("warehouseId",objects[4]);//库位id
            jsonObject.put("warehouseName",objects[5]);//名称

            jsonObject.put("warehouseLevelId",objects[6]);//库层id
            jsonObject.put("warehouseLevelName",objects[7]);//库层名称

            jsonObject.put("numTotal",objects[8]);//件数合计
            jsonObject.put("tonTotal",objects[9]);//吨数合计

            jsonObject.put("productId",objects[10]);//物资id
            jsonObject.put("productName",objects[11]);//物资名称
            jsonObject.put("spec",objects[12]);//规格
            jsonObject.put("model",objects[13]);//型号
            jsonObject.put("material",objects[14]);//材料
            jsonObject.put("maker",objects[15]);//厂家
            jsonObject.put("unit",objects[16]);//单位
            jsonObject.put("rationale",objects[17]);//物资理重
            jsonObject.put("cateId",objects[18]);//物资分类id
            jsonObject.put("productCateName",objects[19]);//物资分类名称

            jsonObjectList.add(jsonObject);
        }
        //重新组装分页信息
        return new PageImpl<JSONObject>(jsonObjectList,pageable,pageObjects.getTotalElements());
    }

}
