//package com.warehouse.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.warehouse.model.*;
//import com.warehouse.service.WarehouseStockService;
//import com.warehouse.service.repository.*;
//import com.warehouse.util.HttpStatusContent;
//import com.warehouse.util.ValidatorUtil;
//import com.warehouse.util.enums.EntrustOrderState;
//import com.warehouse.util.enums.OutputState;
//import com.warehouse.util.enums.WarehouseStockLogOpType;
//import com.warehouse.util.tools.EmptyUtil;
//import com.warehouse.util.tools.ObjectCopyUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.*;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.math.BigDecimal;
//import java.util.*;
//
///**
// * 仓库库存
// */
//@RestController
//@RequestMapping("/warehouseStock")
//public class WarehouseStockController {
//
//    @Autowired
//    private WarehouseStockRepository warehouseStockRepository;
//    @Autowired
//    private WarehouseStockService warehouseStockService;
//
//    /**
//     * @api {GET} /warehouseStock 仓库库存列表
//     * @apiGroup warehouseStock
//     * @apiVersion 0.0.1
//     * @apiDescription 仓库库存列表
//     * @apiParam {String} [customerId] 客户Id
//     * @apiParam {String} [warehouseSiteId] 仓库Id
//     * @apiParam {String} [warehouseAreaId] 库区Id
//     * @apiParam {String} [warehouseId] 库位Id
//     * @apiParam {String} [warehouseLevelId] 库层Id
//     * @apiParam {String} [productId] 物资Id
//     * @apiParam {String} [warehouseIsUse] 仓库是否停用(配合仓库4个参数使用)
//     * - true:启用
//     * - false:停用
//     *
//     * @apiParam {Integer} [page=0] 当前页
//     * @apiParam {Integer} [size=10] 每页数量
//     * @apiParam {String} [sort=createTime,asc] 排序
//     * - 格式： sort=createTime,desc 表示在按createTime由高到低排列
//     * - 格式： sort=createTime,asc 表示在按createTime由低到高排列
//     * @apiParamExample 请求明文：
//     * /warehouseStock
//     * @apiSuccess (200) {String} id  id主键
//     * @apiSuccess (200) {BigDecimal} num 库存件数
//     * @apiSuccess (200) {BigDecimal} tonNum 库存吨数
//     * @apiSuccess (200) {String} createMan 操作人
//     * @apiSuccess (200) {String} createManId 操作人ID
//     *
//     * @apiSuccess (200) {Object} warehouseSiteData 仓库对象
//     * @apiSuccess (200) {String} warehouseSiteData.id id
//     * @apiSuccess (200) {String} warehouseSiteData.name 名称
//     *
//     * @apiSuccess (200) {Object} warehouseAreaData 库区对象
//     * @apiSuccess (200) {String} warehouseAreaData.id id
//     * @apiSuccess (200) {String} warehouseAreaData.name 名称
//     *
//     * @apiSuccess (200) {Object} warehouseData 库位对象
//     * @apiSuccess (200) {String} warehouseData.id id
//     * @apiSuccess (200) {String} warehouseData.name 名称
//     *
//     * @apiSuccess (200) {Object} warehouseLevelData 库层对象
//     * @apiSuccess (200) {String} warehouseLevelData.id id
//     * @apiSuccess (200) {String} warehouseLevelData.name 名称
//     *
//     * @apiSuccess (200) {Object} customerData 库存客户对象
//     * @apiSuccess (200) {String} customerData.id id
//     * @apiSuccess (200) {String} customerData.name 企业名称
//     *
//     * @apiSuccess (200) {Object} productData 库存物资对象
//     * @apiSuccess (200) {String} productData.id id
//     * @apiSuccess (200) {String} productData.productName 物资名称
//     * @apiSuccess (200) {String} productData.unit 物资单位
//     * @apiSuccess (200) {String} productData.rationale 物资理重
//     * @apiSuccess (200) {String} productData.spec 物资规格
//     * @apiSuccess (200) {String} productData.model 物资型号
//     * @apiSuccess (200) {String} productData.material 物资材料
//     * @apiSuccess (200) {String} productData.maker 物资厂家
//     * @apiSuccess (200) {String} productData.productCateName 分类名
//     * @apiSuccessExample {json} 成功:
//     * {}
//     */
//    @GetMapping(produces = "application/json")
//    public ResponseEntity<Page<WarehouseStock>> findAll(WarehouseStock entity,
//                                                        String warehouseSiteId,
//                                                        String warehouseAreaId,
//                                                        String warehouseId,
//                                                        String warehouseLevelId,
//                                                        String productId,
//                                                        String customerId,
//                                                        Boolean warehouseIsUse,
//                                                        String productName,
//                                                        String productSpec,
//                                                        String productModel,
//                                                        String productMaterial,
//                                                        String productMaker,
//                                                        @PageableDefault(value = 10,
//                                                                sort = {"createTime"},
//                                                                direction = Sort.Direction.DESC)
//                                                                Pageable pageable) {
//
//        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
//                //客户名称模糊方式查
//                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
//
//        // 根据仓库customerId查询
//        if (EmptyUtil.isNotEmpty(customerId)) {
//            Customer customer = new Customer();
//            customer.setId(customerId);
//            entity.setCustomer(customer);
//        }
//
//        // 根据仓库warehouseSiteId查询
//        if (EmptyUtil.isNotEmpty(warehouseSiteId)) {
//            Warehouse warehouseSite = new Warehouse();
//            warehouseSite.setId(warehouseSiteId);
//            if(warehouseIsUse!=null){
//                warehouseSite.setIsUse(true);
//            }
//            entity.setWarehouseSite(warehouseSite);
//        }
//        // 根据仓库warehouseAreaId查询
//        if (EmptyUtil.isNotEmpty(warehouseAreaId)) {
//            Warehouse warehouseArea = new Warehouse();
//            warehouseArea.setId(warehouseAreaId);
//            if(warehouseIsUse!=null){
//                warehouseArea.setIsUse(true);
//            }
//            entity.setWarehouseArea(warehouseArea);
//        }
//        // 根据仓库warehouseId查询
//        if (EmptyUtil.isNotEmpty(warehouseId)) {
//            Warehouse warehouse = new Warehouse();
//            warehouse.setId(warehouseId);
//            if(warehouseIsUse!=null){
//                warehouse.setIsUse(true);
//            }
//            entity.setWarehouse(warehouse);
//        }
//        // 库层warehouseLevelId查询
//        if (EmptyUtil.isNotEmpty(warehouseLevelId)) {
//            Warehouse warehouseLevel = new Warehouse();
//            warehouseLevel.setId(warehouseLevelId);
//            if(warehouseIsUse!=null){
//                warehouseLevel.setIsUse(true);
//            }
//            entity.setWarehouseLevel(warehouseLevel);
//        }
//
//        // 根据物资productId查询
//        if (EmptyUtil.isNotEmpty(productId)) {
//            Product product = new Product();
//            product.setId(productId);
//            entity.setProduct(product);
//        }
//
//        if (EmptyUtil.isNotEmpty(productName)) {
//        		Product product = new Product();
//            product.setProductName(productName);
//            entity.setProduct(product);
//        }
//
//        if (EmptyUtil.isNotEmpty(productSpec)) {
//	    		Product product = new Product();
//	        product.setSpec(productSpec);
//	        entity.setProduct(product);
//	    }
//
//        if (EmptyUtil.isNotEmpty(productModel)) {
//	    		Product product = new Product();
//	        product.setModel(productModel);
//	        entity.setProduct(product);
//	    }
//
//        if (EmptyUtil.isNotEmpty(productMaterial)) {
//	    		Product product = new Product();
//	        product.setMaterial(productMaterial);
//	        entity.setProduct(product);
//	    }
//
//        if (EmptyUtil.isNotEmpty(productMaker)) {
//	    		Product product = new Product();
//	        product.setMaker(productMaker);
//	        entity.setProduct(product);
//	    }
//
//        Example<WarehouseStock> example = Example.of(entity, matcher);
//        Page<WarehouseStock> apply = this.warehouseStockRepository.findAll(example, pageable);
//        return new ResponseEntity<Page<WarehouseStock>>(apply, HttpStatus.OK);
//    }
//
//    /**
//     * @api {GET} /warehouseStock/{id} 仓库库存详细
//     * @apiGroup warehouseStock
//     * @apiVersion 0.0.1
//     * @apiDescription 仓库库存详细
//     * @apiParam {String} id 主键id
//     * @apiParamExample 请求明文：
//     * /warehouseStock/1
//     * @apiSuccess (200) {String} id  id主键
//     * @apiSuccess (200) {BigDecimal} num 库存件数
//     * @apiSuccess (200) {BigDecimal} tonNum 库存吨数
//     * @apiSuccess (200) {String} createMan 操作人
//     * @apiSuccess (200) {String} createManId 操作人ID
//     *
//     * @apiSuccess (200) {Object} warehouseSiteData 仓库对象
//     * @apiSuccess (200) {String} warehouseSiteData.id id
//     * @apiSuccess (200) {String} warehouseSiteData.name 名称
//     *
//     * @apiSuccess (200) {Object} warehouseAreaData 库区对象
//     * @apiSuccess (200) {String} warehouseAreaData.id id
//     * @apiSuccess (200) {String} warehouseAreaData.name 名称
//     *
//     * @apiSuccess (200) {Object} warehouseData 库位对象
//     * @apiSuccess (200) {String} warehouseData.id id
//     * @apiSuccess (200) {String} warehouseData.name 名称
//     *
//     * @apiSuccess (200) {Object} warehouseLevelData 库层对象
//     * @apiSuccess (200) {String} warehouseLevelData.id id
//     * @apiSuccess (200) {String} warehouseLevelData.name 名称
//     *
//     * @apiSuccess (200) {Object} customerData 库存客户对象
//     * @apiSuccess (200) {String} customerData.id id
//     * @apiSuccess (200) {String} customerData.name 企业名称
//     *
//     * @apiSuccess (200) {Object} productData 库存物资对象
//     * @apiSuccess (200) {String} productData.id id
//     * @apiSuccess (200) {String} productData.productName 物资名称
//     * @apiSuccessExample {json} 成功:
//     * {}
//     */
//    @GetMapping(produces = "application/json", value = "/{id}")
//    public ResponseEntity<?> getOne(@PathVariable(value = "id") String id) {
//        HttpStatusContent status = null;
//        WarehouseStock entity = this.warehouseStockRepository.findOne(id);
//        if (entity == null) {
//            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
//            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(entity, HttpStatus.OK);
//    }
//
//    /**
//     * @api {GET} /warehouseStock/productOccupy 查询物资被委托单占用情况列表
//     * @apiGroup warehouseStock
//     * @apiVersion 0.0.1
//     * @apiDescription 查询物资被委托单占用情况列表
//     * @apiParam {String} [orderStateStrList="50010,50020"] 委托单状态列表(多种状态逗号分隔)
//     * - 格式："50010,50020"
//     * @apiParam {String} [productId] 物资Id
//     * @apiParam {String} [warehouseId] 仓库id(第一级)
//     * @apiParam {String} [productName] 物资名称(模糊查询)
//     * @apiParam {String} [customerId] 客户id
//     * @apiParam {String} [spec] 规格
//     * @apiParam {String} [model] 型号
//     * @apiParam {String} [material] 材料
//     * @apiParam {String} [maker] 厂家
//     *
//     * @apiParam {Integer} [page=0] 当前页
//     * @apiParam {Integer} [size=10] 每页数量
//     * @apiParamExample 请求明文：
//     * /warehouseStock/productOccupy
//     * @apiSuccess (200) {String} productId  物资id
//     * @apiSuccess (200) {String} productName  物资名称
//     *
//     * @apiSuccess (200) {String} warehouseId 仓库id(第一级)
//     * @apiSuccess (200) {String} warehouseName 仓库名称(第一级)
//
//     * @apiSuccess (200) {String} cateId 物资分类id
//     * @apiSuccess (200) {String} productCateName 物资分类名称
//     *
//     * @apiSuccess (200) {String} customerId 客户id
//     * @apiSuccess (200) {String} customerName 客户名称
//     *
//     * @apiSuccess (200) {String} spec 规格
//     * @apiSuccess (200) {String} model 型号
//     * @apiSuccess (200) {String} material 材料
//     * @apiSuccess (200) {String} maker 厂家
//     * @apiSuccess (200) {String} unit 单位
//     * @apiSuccess (200) {String} rationale 物资理重
//
//     * @apiSuccess (200) {BigDecimal} sumNum 占用件数
//     * @apiSuccess (200) {BigDecimal} sumTonNum 占用吨数
//     * @apiSuccess (200) {BigDecimal} stockNumTotal 库存件数
//     * @apiSuccess (200) {BigDecimal} stockTonNumTotal 库存吨数
//     * @apiSuccess (200) {BigDecimal} lockNumTotal 锁定件数
//     * @apiSuccess (200) {BigDecimal} lockTonNumTotal 锁定吨数
//     * @apiSuccess (200) {BigDecimal} canUseNum 可用件数
//     * @apiSuccess (200) {BigDecimal} canUseTonNum 可用吨数
//     *
//     * @apiSuccessExample {json} 成功:
//     * {}
//     */
//    @GetMapping(produces = "application/json",value = "/productOccupy")
//    public ResponseEntity<?> findOccupy(String orderStateStrList,
//                                        String productId,
//                                        String productName,
//                                        String warehouseId,
//                                        String customerId,
//                                        String spec,
//                                        String model,
//                                        String material,
//                                        String maker,
//                                        @PageableDefault(value = 10)
//                                                Pageable pageable) {
//
//        List<Integer> orderStateList = new ArrayList<>();//如果为空,Service查默认状态
//        if(EmptyUtil.isNotEmpty(orderStateStrList) && orderStateStrList.split(",").length > 0){
//            String[] split = orderStateStrList.split(",");
//            for(int i=0;i<split.length;i++){
//                orderStateList.add(Integer.valueOf(split[i]));
//            }
//        }
//        Page<JSONObject> jsonObjectPage = this.warehouseStockService.sumProductByOrderSates(
//                orderStateList,productId,productName,warehouseId,customerId,
//                spec,
//                model,
//                material,
//                maker,pageable);
//        return new ResponseEntity<Page<JSONObject>>(jsonObjectPage, HttpStatus.OK);
//    }
//
//    /**
//     * @api {GET} /warehouseStock/productOccupyTotal 占用库存列表页面的统计部分
//     * @apiGroup warehouseStock
//     * @apiVersion 0.0.1
//     * @apiDescription 占用库存列表页面的统计部分
//     * @apiParam {String} [orderStateStrList="50010,50020"] 委托单状态列表(多种状态逗号分隔)
//     * - 格式："50010,50020"
//     * @apiParam {String} [productId] 物资Id
//     * @apiParam {String} [warehouseId] 仓库id(第一级)
//     * @apiParam {String} [productName] 物资名称(模糊查询)
//     * @apiParam {String} [customerId] 客户id
//     *
//     * @apiParam {String} [spec] 规格
//     * @apiParam {String} [model] 型号
//     * @apiParam {String} [material] 材料
//     * @apiParam {String} [maker] 厂家
//     * @apiParamExample 请求明文：
//     * /warehouseStock/productOccupyTotal
//     * @apiSuccess (200) {BigDecimal} occupyNumTotal 占用件数合计
//     * @apiSuccess (200) {BigDecimal} occupyTonTotal 占用吨数合计
//     * @apiSuccess (200) {BigDecimal} stockNumTotal 当前库存件数合计
//     * @apiSuccess (200) {BigDecimal} stockTonTotal 当前库存吨数合计
//     * @apiSuccess (200) {BigDecimal} lockNumTotal 锁定件数合计
//     * @apiSuccess (200) {BigDecimal} lockTonTotal 锁定吨数合计
//     * @apiSuccess (200) {BigDecimal} canNumTotal 可用件数合计
//     * @apiSuccess (200) {BigDecimal} canTonTotal 可用吨数合计
//     *
//     * @apiSuccessExample {json} 成功:
//     * {}
//     */
//    @GetMapping(produces = "application/json",value = "/productOccupyTotal")
//    public ResponseEntity<?> occupyTotal(String orderStateStrList,
//                                        String productId,
//                                        String productName,
//                                        String warehouseId,
//                                        String customerId,
//                                         String spec,
//                                         String model,
//                                         String material,
//                                         String maker) {
//
//        List<Integer> orderStateList = new ArrayList<>();//如果为空,Service查默认状态
//        if(EmptyUtil.isNotEmpty(orderStateStrList) && orderStateStrList.split(",").length > 0){
//            String[] split = orderStateStrList.split(",");
//            for(int i=0;i<split.length;i++){
//                orderStateList.add(Integer.valueOf(split[i]));
//            }
//        }
//        Map<String, Object> map = this.warehouseStockService.totalNumByOrderSates(
//                orderStateList, productId, productName, warehouseId, customerId,
//                spec,
//                model,
//                material,
//                maker);
//        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//    }
//
//    /**
//     * @api {GET} /warehouseStock/sumByNoCustomer 排除客户查询库存列表
//     * @apiGroup warehouseStock
//     * @apiVersion 0.0.1
//     * @apiDescription 排除客户查询库存列表
//     * @apiParam {String} [warehouseSiteId] 仓库Id
//     * @apiParam {String} [warehouseAreaId] 库区Id
//     * @apiParam {String} [warehouseId] 库位Id
//     * @apiParam {String} [warehouseLevelId] 库层Id
//     * @apiParam {String} [productId] 物资Id
//     *
//     * @apiParam {Integer} [page=0] 当前页
//     * @apiParam {Integer} [size=10] 每页数量
//     * @apiParamExample 请求明文：
//     * /warehouseStock/sumByNoCustomer
//     *
//     * @apiSuccess (200) {String} warehouseSiteId 仓库id
//     * @apiSuccess (200) {String} warehouseSiteName 仓库名称
//
//     * @apiSuccess (200) {String} warehouseAreaId 库区id
//     * @apiSuccess (200) {String} warehouseAreaName 库区名称
//     *
//     * @apiSuccess (200) {String} warehouseId 库位id
//     * @apiSuccess (200) {String} warehouseName 库位名称
//     *
//     * @apiSuccess (200) {String} warehouseLevelId 库层id
//     * @apiSuccess (200) {String} warehouseLevelName 库层名称
//     *
//     * @apiSuccess (200) {BigDecimal} numTotal 件数合计
//     * @apiSuccess (200) {BigDecimal} tonTotal 吨数合计
//     *
//     * @apiSuccess (200) {String} productId  物资id
//     * @apiSuccess (200) {String} productName  物资名称
//     * @apiSuccess (200) {String} spec 规格
//     * @apiSuccess (200) {String} model 型号
//     * @apiSuccess (200) {String} material 材料
//     * @apiSuccess (200) {String} maker 厂家
//     * @apiSuccess (200) {String} unit 单位
//     * @apiSuccess (200) {String} rationale 物资理重
//     *
//     * @apiSuccess (200) {String} cateId 物资分类id
//     * @apiSuccess (200) {String} productCateName 物资分类名称
//
//     * @apiSuccessExample {json} 成功:
//     * {}
//     */
//    @GetMapping(produces = "application/json",value = "/sumByNoCustomer")
//    public ResponseEntity<?> sumByNoCustomer(String warehouseSiteId,
//                                            String warehouseAreaId,
//                                            String warehouseId,
//                                            String warehouseLevelId,
//                                            String productId,
//                                            @PageableDefault(value = 10)
//                                                    Pageable pageable) {
//
//        Page<JSONObject> jsonObjectPage = this.warehouseStockService.sumByNoCustomer(
//                productId, warehouseSiteId, warehouseAreaId, warehouseId, warehouseLevelId, pageable);
//        return new ResponseEntity<Page<JSONObject>>(jsonObjectPage, HttpStatus.OK);
//    }
//}
