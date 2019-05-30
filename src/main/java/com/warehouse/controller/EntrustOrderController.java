package com.warehouse.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.warehouse.model.*;
import com.warehouse.service.CostSettinsService;
import com.warehouse.service.EntrustOrderService;
import com.warehouse.service.WarehouseStockService;
import com.warehouse.service.repository.*;
import com.warehouse.util.HttpStatusContent;
import com.warehouse.util.ValidatorUtil;
import com.warehouse.util.enums.*;
import com.warehouse.util.tools.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.collections.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 委托单
 */
@RestController
@RequestMapping("/entrustOrder")
public class EntrustOrderController {
    @Autowired
    private EntrustOrderRepository entrustOrderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private WarehouseStockService warehouseStockService;
    @Autowired
    private WarehouseStockRepository warehouseStockRepository;
    @Autowired
    private CostSettinsRepository costSettinsRepository;
    @Autowired
    private EntrustOrderDetailRepository entrustOrderDetailRepository;
    @Autowired
    private CostSettinsService costSettinsService;

    /**
     * @api {GET} /entrustOrder 委托单列表
     * @apiGroup entrustOrder
     * @apiVersion 0.0.1
     * @apiDescription 委托单列表
     * @apiParam {String} [orderNo] 单号(支持模糊查询)
     * @apiParam {String} [orderStateList] 订单状态列表
     * - 格式逗号分隔 "50010,50020"
     * @apiParam {Integer} [orderState] 订单状态
     * - NEW_CREATE(50010, "新建"),
     * - CONFIRM(50020, "确认"),
     * - FINISH(50050, "完成"),
     * - RECEIVING(50060, "收货中"),
     * - RECEIVE_FINISH(50070, "收货完成"),
     * - ABOLISH(50080, "废止"),
     * - SENDING(50090, "发货中"),
     * - SEND_FINISH(50100, "发货完成"),
     * - INVALID(-50000, "作废");
     * @apiParam {String=火车,汽车} [arrivalMode] 到达方式
     * @apiParam {Integer} [orderType] 订单类型
     * - RECEIVE_GOODS(60010, "收货"),
     * - TAKE_GOODS(60020, "提货"),
     * - BUTT_JOINT(60030, "对接");
     * @apiParam {String} [customerId] 客户Id
     * @apiParam {String} [warehouseId] 仓库Id
     * @apiParam {Integer} [page=0] 当前页
     * @apiParam {Integer} [size=10] 每页数量
     * @apiParam {String} [sort=id,asc] 排序
     * - 格式： sort=id,desc 表示在按id由高到低排列
     * - 格式： sort=id,asc 表示在按id由低到高排列
     * @apiParamExample 请求明文：
     * /entrustOrder?opType=40010&sort=id,asc
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} orderNo 单号
     * @apiSuccess (200) {String} customerOrderNo 客户单号
     * @apiSuccess (200) {Integer} orderState 订单状态
     * @apiSuccess (200) {String} orderStateTxt 订单状态Txt
     * @apiSuccess (200) {String=火车,汽车} arrivalMode 到达方式
     * @apiSuccess (200) {Integer} orderType 订单类型
     * @apiSuccess (200) {String} orderTypeTxt 订单类型Txt
     * @apiSuccess (200) {BigDecimal} advanceFee 预收费用(对接委托单时才显示)
     * @apiSuccess (200) {BigDecimal} fee 实收费用
     * @apiSuccess (200) {String} drawer 开单人
     * @apiSuccess (200) {String} remark 备注
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * @apiSuccess (200) {String} createMan 创建人
     * @apiSuccess (200) {String} createManId 创建人Id
     * @apiSuccess (200) {Object} customerData 客户对象
     * @apiSuccess (200) {String} customerData.id id
     * @apiSuccess (200) {String} customerData.name 企业名称
     * @apiSuccess (200) {Object} warehouseData 仓库对象
     * @apiSuccess (200) {String} warehouseData.id id
     * @apiSuccess (200) {String} warehouseData.name 名称
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<EntrustOrder>> findAll(EntrustOrder entity,
                                                      @PageableDefault(value = 10, sort = {"createTime"}, direction = Sort.Direction.DESC)
                                                              Pageable pageable,
                                                      String warehouseId,
                                                      String customerId,
                                                      String orderStateList) {

        Specification<EntrustOrder> specification = new Specification<EntrustOrder>() {
            @Override
            public Predicate toPredicate(Root<EntrustOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                //订单状态列表逗号分隔
                if (EmptyUtil.isNotEmpty(orderStateList)) {
                    String[] strs = orderStateList.split(",");
                    list.add(root.get("orderState").in(strs));
                }
                //仓库Id查询
                if (EmptyUtil.isNotEmpty(warehouseId)) {
                    Warehouse warehouse = new Warehouse();
                    warehouse.setId(warehouseId);
                    list.add(cb.equal(root.get("warehouse"), warehouse));
                }
                //客户Id查询
                if (EmptyUtil.isNotEmpty(customerId)) {
                    Customer customer = new Customer();
                    customer.setId(customerId);
                    list.add(cb.equal(root.get("customer"), customer));
                }

                Class clazz = entity.getClass();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);  //启用访问控制权限
                    try {
                        String name = field.getName();
                        Object value = field.get(entity);
                        Class<?> type = field.getType();
                        //拿到对象实例(t1)的 域成员的值
                        if (null != value
                                && !Collection.class.isAssignableFrom(type)) {
                            //不为为null值且不是集合的字段名加入查询
                            if ("orderNo".equals(name)) {
                                //模糊查询
                                list.add(cb.like(root.get(name), "%" + value + "%"));
                            } else {
                                list.add(cb.equal(root.get(name), value));
                            }
                        }
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
        Page<EntrustOrder> apply = this.entrustOrderRepository.findAll(specification, pageable);


        return new ResponseEntity<Page<EntrustOrder>>(apply, HttpStatus.OK);
    }


    /**
     * @api {GET} /entrustOrder/{id} 单个委托单1
     * @apiGroup entrustOrder
     * @apiVersion 0.0.1
     * @apiDescription 单个委托单
     * @apiParam {String} id 主键id
     * @apiParamExample 请求明文：
     * /entrustOrder/111
     * @apiSuccess (200) {String} id  id主键
     * @apiSuccess (200) {String} orderNo 单号
     * @apiSuccess (200) {String} customerOrderNo 客户单号
     * @apiSuccess (200) {Integer} orderState 订单状态
     * @apiSuccess (200) {String} orderStateTxt 订单状态Txt
     * @apiSuccess (200) {String=火车,汽车} arrivalMode 到达方式
     * @apiSuccess (200) {Integer} orderType 订单类型
     * @apiSuccess (200) {String} orderTypeTxt 订单类型Txt
     * @apiSuccess (200) {BigDecimal} advanceFee 预收费用(对接委托单时才显示)
     * @apiSuccess (200) {BigDecimal} fee 实收费用
     * @apiSuccess (200) {String} drawer 开单人
     * @apiSuccess (200) {String} remark 备注
     * @apiSuccess (200) {DateTime} createTime 创建时间
     * @apiSuccess (200) {String} createMan 创建人
     * @apiSuccess (200) {String} createManId 创建人Id
     * @apiSuccess (200) {Object} customerData 客户对象
     * @apiSuccess (200) {String} customerData.id id
     * @apiSuccess (200) {String} customerData.name 企业名称
     * @apiSuccess (200) {Object} warehouseData 仓库对象
     * @apiSuccess (200) {String} warehouseData.id id
     * @apiSuccess (200) {String} warehouseData.name 名称
     * @apiSuccessExample {json} 成功:
     * {}
     */
    @GetMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable(value = "id") String id) {
        HttpStatusContent status = null;
        EntrustOrder entity = this.entrustOrderRepository.findOne(id);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<EntrustOrder>(entity, HttpStatus.OK);
    }


    /**
     * @api {POST} /entrustOrder 委托单添加
     * @apiGroup entrustOrder
     * @apiVersion 0.0.1
     * @apiDescription 委托单添加
     * @apiParam {String} warehouseId 仓库Id(第一级)
     * @apiParam {String} customerId 客户Id
     * @apiParam {String} strJsonArrayProduct 物资列表(json数组格式的字符串)
     * - 格式：[{"productId":"1","vehicleNo":"贵A12345","num":"80","tonNum":"50"},
     * {"productId":"2","vehicleNo":"贵C12345","num":"80","tonNum":"50"}]
     * - productId: 物资id
     * - vehicleNo: 车号
     * - num: 件数
     * - tonNum: 吨数(最多3位小数)
     * @apiParam {String} customerOrderNo 客户单号
     * @apiParam {String=火车,汽车} arrivalMode 到达方式
     * @apiParam {Integer} orderType 订单类型
     * - RECEIVE_GOODS(60010, "收货"),
     * - TAKE_GOODS(60020, "提货"),
     * - BUTT_JOINT(60030, "对接");
     * @apiParam {BigDecimal} advanceFee 预收费用(对接委托单时才显示)
     * @apiParam {String} drawer 开单人
     * @apiParam {String} remark 备注
     * @apiParam {String} createMan 创建人
     * @apiParam {String} createManId 创建人Id
     * @apiParamExample 请求明文：
     * /entrustOrder
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> post(EntrustOrder entity,
                                  String warehouseId,
                                  String customerId,
                                  String strJsonArrayProduct) {

        HttpStatusContent status = null;

        String orderNoType = "WT";
        if (entity.getOrderType().intValue() == EntrustOrderType.RECEIVE_GOODS.getValue().intValue()) {
            orderNoType = "WS";
        }

        if (entity.getOrderType().intValue() == EntrustOrderType.TAKE_GOODS.getValue().intValue()) {
            orderNoType = "WT";
        }

        if (entity.getOrderType().intValue() == EntrustOrderType.BUTT_JOINT.getValue().intValue()) {
            orderNoType = "WD";
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderNo = orderNoType +
                format.format((new Date()).getTime()) +
                RandomUtil.toFixdLengthString((int) ((Math.random() * 9 + 1) * 100000), 6);
        entity.setOrderNo(orderNo);
        entity.setOrderState(EntrustOrderState.NEW_CREATE.getValue());//默认状态新建

        if (!MyJSONUtil.isJSONArray(strJsonArrayProduct)) {
            status = new HttpStatusContent(OutputState.FAIL, "物资参数格式错误！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //验证实体必填字段
        Map<String, StringBuffer> validate = ValidatorUtil.validate(entity);
        if (validate != null) {
            status = new HttpStatusContent(OutputState.FAIL, ValidatorUtil.getErrorMsg(validate));
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //本年第几周
        Integer weekthOfYear = DateUtil.getWeekthOfYear(new Date());
        String strWeek = weekthOfYear.toString();
        if (strWeek.length() == 1) {
            strWeek = "0" + strWeek;//不足2位前面补0
        }
        entity.setYearWeek(strWeek);
        //本周单子序号
        String weekNo = "1000";//默认从1000开始
        EntrustOrder everyYearWeekMaxWeekNo = this.entrustOrderRepository.findEveryYearWeekMaxWeekNo(DateUtil.getYear(new Date()).toString(),
                strWeek, entity.getOrderType());
        if (everyYearWeekMaxWeekNo != null
                && EmptyUtil.isNotEmpty(everyYearWeekMaxWeekNo.getWeekNo())) {
            int i = Integer.valueOf(everyYearWeekMaxWeekNo.getWeekNo()) + 1;
            weekNo = Integer.valueOf(i).toString();
        }
        entity.setWeekNo(weekNo);

//        //提货委托单，客户单号可能重复不需要验证
//        //同类型的客户单号是否存在
//        if (entity.getOrderType().intValue() != EntrustOrderType.TAKE_GOODS.getValue().intValue()) {
//            EntrustOrder queryOrder = new EntrustOrder();
//            queryOrder.setCustomerOrderNo(entity.getCustomerOrderNo());
//            queryOrder.setOrderType(entity.getOrderType());
//            Example<EntrustOrder> entrustOrderExample = Example.of(queryOrder);
//            if(this.entrustOrderRepository.exists(entrustOrderExample)){
//                status = new HttpStatusContent(OutputState.FAIL, "客户单号已经存在！");
//                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
//            }  
//        }

        if (!EntrustOrderState.isExist(entity.getOrderState())) {
            status = new HttpStatusContent(OutputState.FAIL, "订单状态参数错误！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!EntrustOrderType.isExist(entity.getOrderType())) {
            status = new HttpStatusContent(OutputState.FAIL, "订单类型参数错误！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (EmptyUtil.isEmpty(warehouseId)) {
            status = new HttpStatusContent(OutputState.FAIL, "缺少仓库参数！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (EmptyUtil.isEmpty(customerId)) {
            status = new HttpStatusContent(OutputState.FAIL, "缺少客户参数！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Warehouse warehouse = this.warehouseRepository.findOne(warehouseId);
        if (warehouse == null) {
            status = new HttpStatusContent(OutputState.FAIL, "仓库不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        entity.setWarehouse(warehouse);

        Customer customer = this.customerRepository.findOne(customerId);
        if (customer == null) {
            status = new HttpStatusContent(OutputState.FAIL, "客户不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        entity.setCustomer(customer);

        //委托单明细 列表
        JSONArray jsonArray = JSONObject.parseArray(strJsonArrayProduct);
        Set<EntrustOrderDetail> detailList = new HashSet<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String productId = jsonObject.getString("productId");
            String vehicleNo = jsonObject.getString("vehicleNo");
            BigDecimal num = jsonObject.getBigDecimal("num");
            BigDecimal tonNum = jsonObject.getBigDecimal("tonNum");
            if (EmptyUtil.isEmpty(productId)) {
                status = new HttpStatusContent(OutputState.FAIL, "缺少物资参数！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Product product = this.productRepository.findOne(productId);
            if (EmptyUtil.isEmpty(product)) {
                status = new HttpStatusContent(OutputState.FAIL, "物资不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //收货委托不用验证车号
            if (!entity.getOrderType().equals(EntrustOrderType.RECEIVE_GOODS.getValue())
                    && EmptyUtil.isEmpty(vehicleNo)) {
                status = new HttpStatusContent(OutputState.FAIL,
                        "物资:" + product.getProductName() + ",缺少车号！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (EmptyUtil.isEmpty(num)
                    || num.compareTo(BigDecimal.ZERO) <= 0) {
                status = new HttpStatusContent(OutputState.FAIL,
                        "物资:" + product.getProductName() + ",件数必须大于0！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (EmptyUtil.isEmpty(tonNum)
                    || tonNum.compareTo(BigDecimal.ZERO) <= 0) {
                status = new HttpStatusContent(OutputState.FAIL,
                        "物资:" + product.getProductName() + ",吨数必须大于0！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            EntrustOrderDetail detail = new EntrustOrderDetail();
            detail.setEntrustOrder(entity);
            detail.setProduct(product);
            detail.setNum(num);
            detail.setTonNum(tonNum);
            detail.setVehicleNo(vehicleNo);
            detailList.add(detail);
        }
        //因为配了1对多的关系，委托添加的时候同时把明细也添加进去
        entity.setEntrustOrderDetailSet(detailList);

        //记录新建状态日志
        EntrustOrderLog log = new EntrustOrderLog();
        log.setCreateMan(entity.getCreateMan());
        log.setCreateManId(entity.getCreateManId());
        log.setOrderState(entity.getOrderState());
        log.setEntrustOrder(entity);
        log.setRemark(EntrustOrderState.getTxtByValue(entity.getOrderState()));
        Set<EntrustOrderLog> logSet = new HashSet<>();
        logSet.add(log);
        entity.setEntrustOrderLogSet(logSet);//2.记录状态变更日志(关联会自动写入)

        if (this.entrustOrderRepository.save(entity) != null) {
            status = new HttpStatusContent(OutputState.SUCCESS);
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
        }
        status = new HttpStatusContent(OutputState.FAIL, "添加失败，请稍后重试！");
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * @api {PUT} /entrustOrder/{id} 委托单修改
     * @apiGroup entrustOrder
     * @apiVersion 0.0.1
     * @apiDescription 委托单修改
     * @apiParam {String} id 主键id
     * @apiParam {String} warehouseId 仓库Id
     * @apiParam {String} customerId 客户Id
     * @apiParam {String} customerOrderNo 客户单号
     * @apiParam {String=火车,汽车} arrivalMode 到达方式
     * @apiParam {Integer} orderType 订单类型
     * - RECEIVE_GOODS(60010, "收货"),
     * - TAKE_GOODS(60020, "提货"),
     * - BUTT_JOINT(60030, "对接");
     * @apiParam {BigDecimal} advanceFee 预收费用(对接委托单时才显示)
     * @apiParam {String} drawer 开单人
     * @apiParam {String} remark 备注
     * @apiParam {String} strJsonArrayProduct 物资列表(json数组格式的字符串)
     * - 格式：[{"id":"2","productId":"1","vehicleNo":"贵A12345","num":"80","tonNum":"50"},
     * {"productId":"2","vehicleNo":"贵C12345","num":"80","tonNum":"50"}]
     * - id: id(新加数据没有id)
     * - productId: 物资id
     * - vehicleNo: 车号
     * - num: 件数
     * - tonNum: 吨数(最多3位小数)
     * - id：明细主键id
     * @apiParamExample 请求明文：
     * /entrustOrder/18
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @Transactional
    @PutMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<?> put(@PathVariable(value = "id") String id,
                                 EntrustOrder entity,
                                 String warehouseId,
                                 String customerId,
                                 String createMan,
                                 String createManId,
                                 String strJsonArrayProduct) {
        HttpStatusContent status = null;
        entity.setCreateMan(null);//不能修改之前的创建人信息
        entity.setCreateManId(null);//不能修改之前的创建人信息
        entity.setOrderState(null);//状态不能通过此接口修改

        if (EmptyUtil.isEmpty(createMan) || EmptyUtil.isEmpty(createManId)) {
            status = new HttpStatusContent(OutputState.FAIL, "缺少操作人信息！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        EntrustOrder entrustOrder = this.entrustOrderRepository.findOne(id);
        if (entrustOrder == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        //已确认的不能修改
        if (entrustOrder.getOrderState().equals(EntrustOrderState.CONFIRM.getValue())) {
            status = new HttpStatusContent(OutputState.FAIL, "已确认不能修改！");
            return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
        }

//        if(entity.getCustomerOrderNo()!=null){
//            EntrustOrder queryOrder = new EntrustOrder();
//            queryOrder.setCustomerOrderNo(entity.getCustomerOrderNo());
//            Example<EntrustOrder> entrustOrderExample = Example.of(queryOrder);
//            List<EntrustOrder> all = this.entrustOrderRepository.findAll(entrustOrderExample);
//            if(all!=null && all.size()>0){
//                for(int i=0;i<all.size();i++){
//                    if(!all.get(i).getId().equals(entrustOrder.getId())){
//                        status = new HttpStatusContent(OutputState.FAIL, "客户单号已经存在！");
//                        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
//                    }
//                }
//            }
//        }

        if (EmptyUtil.isNotEmpty(entity.getOrderType()) && !EntrustOrderType.isExist(entity.getOrderType())) {
            status = new HttpStatusContent(OutputState.FAIL, "订单类型参数错误！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (EmptyUtil.isNotEmpty(strJsonArrayProduct)) {
            JSONArray jsonArray = JSONObject.parseArray(strJsonArrayProduct);
            Set<EntrustOrderDetail> newDetailSet = new HashSet<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String productId = jsonObject.getString("productId");
                String vehicleNo = jsonObject.getString("vehicleNo");
                BigDecimal num = jsonObject.getBigDecimal("num");
                BigDecimal tonNum = jsonObject.getBigDecimal("tonNum");
                if (EmptyUtil.isEmpty(productId)) {
                    status = new HttpStatusContent(OutputState.FAIL, "缺少物资参数！");
                    return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                Product product = this.productRepository.findOne(productId);
                if (EmptyUtil.isEmpty(product)) {
                    status = new HttpStatusContent(OutputState.FAIL, "物资不存在！");
                    return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                if (EmptyUtil.isEmpty(num)
                        || num.compareTo(BigDecimal.ZERO) <= 0) {
                    status = new HttpStatusContent(OutputState.FAIL,
                            "物资:" + product.getProductName() + ",件数必须大于0！");
                    return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                if (EmptyUtil.isEmpty(tonNum)
                        || tonNum.compareTo(BigDecimal.ZERO) <= 0) {
                    status = new HttpStatusContent(OutputState.FAIL,
                            "物资:" + product.getProductName() + ",吨数必须大于0！");
                    return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                }

                EntrustOrderDetail detail = new EntrustOrderDetail();
                detail.setEntrustOrder(entrustOrder);
                detail.setProduct(product);
                detail.setNum(num);
                detail.setTonNum(tonNum);
                detail.setVehicleNo(vehicleNo);
                newDetailSet.add(detail);
            }

            if (newDetailSet.size() > 0) {
                //1.删掉老的明细
                List<EntrustOrderDetail> srcDetailList = new ArrayList<>(entrustOrder.getEntrustOrderDetailSet());
                if (srcDetailList.size() > 0) {
                    List<EntrustOrderDetail> oldDetailList = new ArrayList<>();
                    CollectionUtils.addAll(oldDetailList, new Object[srcDetailList.size()]);
                    Collections.copy(oldDetailList, srcDetailList);//把之前明细进行深拷贝
                    entrustOrder.getEntrustOrderDetailSet().clear();//清除老的对detail表数据的引用
                    Iterator<EntrustOrderDetail> iterator = oldDetailList.iterator();
                    while (iterator.hasNext()) {
                        EntrustOrderDetail detail = iterator.next();
                        detail.setEntrustOrder(null);
                        detail.setProduct(null);
                        EntrustOrderDetail one = this.entrustOrderDetailRepository.getOne(detail.getId());
                        if (one != null) {//删除之前先查数据
                            this.entrustOrderDetailRepository.delete(one.getId());
                        }
                    }
                }
                //2.通过关联重新添加(明细)
                entrustOrder.setEntrustOrderDetailSet(newDetailSet);
            }
        }

        if (EmptyUtil.isNotEmpty(warehouseId)) {
            Warehouse warehouse = this.warehouseRepository.findOne(warehouseId);
            if (warehouse == null) {
                status = new HttpStatusContent(OutputState.FAIL, "仓库不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            entrustOrder.setWarehouse(warehouse);
        }

        if (EmptyUtil.isNotEmpty(customerId)) {
            Customer customer = this.customerRepository.findOne(customerId);
            if (customer == null) {
                status = new HttpStatusContent(OutputState.FAIL, "客户不存在！");
                return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            entrustOrder.setCustomer(customer);
        }

        ObjectCopyUtil<EntrustOrder> uUtil = new ObjectCopyUtil<>();
        //同一类实体之间的属性复制
        uUtil.copyProperties(entity, entrustOrder);//entity只放基本数据，关联对象数据不放entity

        EntrustOrderLog log = new EntrustOrderLog();
        log.setCreateMan(createMan);
        log.setCreateManId(createManId);
        log.setOrderState(null);
        log.setEntrustOrder(entrustOrder);

        List<Map<String, Object>> maps = ClassAttrValCompare.newCompareToOld(entrustOrder, entity);
        //log.setRemark(maps.toString());
        log.setRemark("修改");
        Set<EntrustOrderLog> logSet = new HashSet<>();
        logSet.add(log);
        entrustOrder.setEntrustOrderLogSet(logSet);//2.记录状态变更日志(关联会自动写入)

        entity = this.entrustOrderRepository.save(entrustOrder);
        if (entity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        status = new HttpStatusContent(OutputState.SUCCESS);
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
    }

    /**
     * @api {POST} /entrustOrder/orderState/change 委托单状态变更
     * @apiGroup entrustOrder
     * @apiVersion 0.0.1
     * @apiDescription 委托单状态变更
     * @apiParam {String} id 主键id
     * @apiParam {Integer} orderState 订单状态
     * - CONFIRM(50020, "确认"),
     * - FINISH(50050, "完成"),
     * - INVALID(-50000, "作废");
     * - RECEIVE_FINISH(50070, "收货完成"),(中止操作,填备注写日志)
     * - ABOLISH(50080, "废止");
     * - SEND_FINISH(50100, "发货完成");(中止操作,填备注写日志)
     * @apiParam {String} createMan 创建人
     * @apiParam {String} createManId 创建人Id
     * @apiParamExample 请求明文：
     * /entrustOrder/orderState/change
     * @apiSuccess (200) {String} message 信息
     * @apiSuccessExample {json} 成功:
     * {"message":"成功"}
     * @apiError (500) {String} message 信息
     * @apiErrorExample {json} 失败:
     * {"message":"失败"}
     */
    @PostMapping(produces = "application/json", value = "/orderState/change")
    public ResponseEntity<?> orderStateChange(String id,
                                              Integer orderState,
                                              String createMan,
                                              String createManId,
                                              String remark) {
        HttpStatusContent status = null;

        if (EmptyUtil.isEmpty(id)) {
            status = new HttpStatusContent(OutputState.FAIL, "缺少id参数！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        if (EmptyUtil.isEmpty(createMan) || EmptyUtil.isEmpty(createManId)) {
            status = new HttpStatusContent(OutputState.FAIL, "操作人和操作人Id必传！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        EntrustOrder originEntity = this.entrustOrderRepository.findOne(id);
        if (originEntity == null) {
            status = new HttpStatusContent(OutputState.FAIL, "数据不存在！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        //变更前状态
        Integer preState = originEntity.getOrderState();

        if (orderState.equals(preState)) {
            status = new HttpStatusContent(OutputState.FAIL,
                    "当前状态已经是：" + TransferState.getTxtByValue(preState));
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.NOT_FOUND);
        }

        if (!EntrustOrderState.isExist(orderState)) {
            status = new HttpStatusContent(OutputState.FAIL, "订单状态参数错误！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (orderState.equals(EntrustOrderState.ABOLISH.getValue())
                && !preState.equals(EntrustOrderState.CONFIRM.getValue())) {
            status = new HttpStatusContent(OutputState.FAIL, "确认状态才能废止操作！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (orderState.equals(EntrustOrderState.INVALID.getValue())
                && !preState.equals(EntrustOrderState.NEW_CREATE.getValue())) {
            status = new HttpStatusContent(OutputState.FAIL, "新建状态才能作废操作！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if ((orderState.equals(EntrustOrderState.ABOLISH.getValue())
                || orderState.equals(EntrustOrderState.RECEIVE_FINISH.getValue())
                || orderState.equals(EntrustOrderState.SEND_FINISH.getValue()))
                && EmptyUtil.isEmpty(remark)) {
            status = new HttpStatusContent(OutputState.FAIL, "废止和中止操作请输入备注！");
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //确认时判断,收货不用判断
        if (EntrustOrderState.CONFIRM.getValue().equals(orderState)
                && !originEntity.getOrderType().equals(EntrustOrderType.RECEIVE_GOODS.getValue())) {
            Set<EntrustOrderDetail> entrustOrderDetailSet = originEntity.getEntrustOrderDetailSet();
            if (entrustOrderDetailSet != null && entrustOrderDetailSet.size() > 0) {
                Iterator<EntrustOrderDetail> iterator = entrustOrderDetailSet.iterator();

                BigDecimal canUseNum = BigDecimal.ZERO;
                BigDecimal canUseTonNum = BigDecimal.ZERO;
                BigDecimal canUseTonNumTotal = BigDecimal.ZERO;
                BigDecimal orderTonNumTotal = BigDecimal.ZERO;

                Page<JSONObject> jsonObjectPage = null;
                List<Integer> orderStateList = null;
                EntrustOrderDetail next = null;

                while (iterator.hasNext()) {
                    next = iterator.next();
                    //可用件数和可用吨数
                    canUseNum = BigDecimal.ZERO;
                    canUseTonNum = BigDecimal.ZERO;

                    //默认查新建和确认
                    orderStateList = new ArrayList<>();
                    orderStateList.add(EntrustOrderState.NEW_CREATE.getValue());
                    orderStateList.add(EntrustOrderState.CONFIRM.getValue());

                    jsonObjectPage = this.warehouseStockService.sumProductByOrderSates(
                            orderStateList,
                            next.getProduct().getId(),
                            null,
                            originEntity.getWarehouse().getId(),
                            originEntity.getCustomer().getId(),
                            null, null, null, null, null);
                    if (jsonObjectPage != null && jsonObjectPage.getContent().size() > 0) {
                        canUseNum = jsonObjectPage.getContent().get(0).getBigDecimal("canUseNum");
                        canUseTonNum = jsonObjectPage.getContent().get(0).getBigDecimal("canUseTonNum");

                        //总使用吨数  总提货总数
                        canUseTonNumTotal = canUseTonNumTotal.add(jsonObjectPage.getContent().get(0).getBigDecimal("canUseTonNum"));
                        orderTonNumTotal = orderTonNumTotal.add(next.getTonNum());
                    }

                    //这时可用，减了新建的。所以要把自己数量加上
                    canUseNum = canUseNum.add(next.getNum());
                    canUseTonNum = canUseTonNum.add(next.getTonNum());
                    if (next.getNum().compareTo(canUseNum) > 0) {
                        status = new HttpStatusContent(OutputState.FAIL,
                                "物资:" + next.getProduct().getProductName() +
                                        "计划件数" + next.getNum() +
                                        "不能大于可用件数" + canUseNum);
                        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                    } else if (next.getTonNum().compareTo(canUseTonNum) > 0) {
                        status = new HttpStatusContent(OutputState.FAIL,
                                "物资:" + next.getProduct().getProductName() +
                                        "计划吨数" + next.getTonNum() +
                                        "不能大于可用吨数" + canUseTonNum);
                        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }

                System.out.println("=========================>>>>>>>> : " + canUseTonNumTotal + "," + orderTonNumTotal);
                System.out.println("=========================>>>>>>>> : " + canUseTonNumTotal.subtract(orderTonNumTotal).subtract(originEntity.getCustomer().getDetentionTonNum()).intValue());
//                if (orderTonNumTotal.subtract(originEntity.getCustomer().getDetentionTonNum()).intValue() < 0) {
//                		status = new HttpStatusContent(OutputState.FAIL, 
//                                    "总使用吨数" + canUseTonNumTotal +
//                                    "，总提货吨数" + orderTonNumTotal + ", 扣押数量:" + originEntity.getCustomer().getDetentionTonNum());
//                    return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
//                }

            }
        }

        //没有录入备注才自定义备注信息
        if (EmptyUtil.isEmpty(remark)) {
            remark = "状态由"
                    + EntrustOrderState.getTxtByValue(preState)
                    + "变为" + EntrustOrderState.getTxtByValue(orderState);
        }

        originEntity.setOrderState(orderState); //1.修改委托单状态

        EntrustOrderLog log = new EntrustOrderLog();
        log.setCreateMan(createMan);
        log.setCreateManId(createManId);
        log.setOrderState(orderState);
        log.setEntrustOrder(originEntity);
        log.setRemark(remark);
        Set<EntrustOrderLog> logSet = new HashSet<>();
        logSet.add(log);
        originEntity.setEntrustOrderLogSet(logSet);//2.记录状态变更日志(关联会自动写入)

        if (this.entrustOrderRepository.save(originEntity) != null) {
            status = new HttpStatusContent(OutputState.SUCCESS);
            return new ResponseEntity<HttpStatusContent>(status, HttpStatus.OK);
        }
        status = new HttpStatusContent(OutputState.FAIL, "修改失败，请稍后重试！");
        return new ResponseEntity<HttpStatusContent>(status, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
