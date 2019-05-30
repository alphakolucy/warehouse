package com.warehouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.model.AdjustOrder;
import com.warehouse.model.Customer;
import com.warehouse.model.CustomerWarehouseStockReportForDay;
import com.warehouse.model.CustomerWarehouseStockReportForMonth;
import com.warehouse.model.WarehouseStock;
import com.warehouse.service.repository.CustomerWarehouseStockReportForDayRepository;
import com.warehouse.service.repository.CustomerWarehouseStockReportForMonthRepository;
import com.warehouse.util.tools.EmptyUtil;

/**
 * 库存调整-管理
 */
@RestController
@RequestMapping("/report")
public class ReportController {
	
	 @Autowired
	 private CustomerWarehouseStockReportForDayRepository customerWarehouseStockReportForDayRepository;
	 
	 @Autowired
	 private CustomerWarehouseStockReportForMonthRepository customerWarehouseStockReportForMonthRepository;

	 /**
	     * @api {GET} /report/customer/stock/day 客户库存日列表
	     * @apiGroup report
	     * @apiVersion 0.0.1
	     * @apiDescription 客户库存日列表
	     * @apiParam {Integer} [page=0] 当前页
	     * @apiParam {Integer} [size=10] 每页数量
	     * @apiParam {String} [sort=createTime,asc] 排序
	     * - 格式： sort=createTime,desc 表示在按createTime由高到低排列
	     * - 格式： sort=createTime,asc 表示在按createTime由低到高排列
	     * @apiParamExample 请求明文：
	     * /report/customer/stock/day
	     * @apiSuccess (200) {String} id  id主键  
	     * @apiSuccessExample {json} 成功:
	     * {}
	     */
	    @GetMapping(produces = "application/json", value = "/customer/stock/day")
	    public ResponseEntity<List<CustomerWarehouseStockReportForDay>> findAll(String customerId,
	                                                     @PageableDefault(value = 10,
	                                                             sort = {"createTime"},
	                                                             direction = Sort.Direction.DESC)
	                                                             Pageable pageable) {

	    		CustomerWarehouseStockReportForDay entity = new CustomerWarehouseStockReportForDay();
	    		Customer customer = new Customer();
	    		customer.setId(customerId);
	    		entity.setCustomer(customer);

	        Example<CustomerWarehouseStockReportForDay> example = Example.of(entity);
	        List<CustomerWarehouseStockReportForDay> report = this.customerWarehouseStockReportForDayRepository.findAll(example);
	        return new ResponseEntity<List<CustomerWarehouseStockReportForDay>>(report, HttpStatus.OK);
	    }
	    
	    /**
	     * @api {GET} /report/customer/stock/month 客户库存月列表
	     * @apiGroup report
	     * @apiVersion 0.0.1
	     * @apiDescription 客户库存月列表
	     * @apiParam {Integer} [page=0] 当前页
	     * @apiParam {Integer} [size=10] 每页数量
	     * @apiParam {String} [sort=createTime,asc] 排序
	     * - 格式： sort=createTime,desc 表示在按createTime由高到低排列
	     * - 格式： sort=createTime,asc 表示在按createTime由低到高排列
	     * @apiParamExample 请求明文：
	     * /report/customer/stock/day
	     * @apiSuccess (200) {String} id  id主键  
	     * @apiSuccessExample {json} 成功:
	     * {}
	     */
	    @GetMapping(produces = "application/json", value = "/customer/stock/month")
	    public ResponseEntity<List<CustomerWarehouseStockReportForMonth>> findAllMonth(String customerId,
	                                                     @PageableDefault(value = 10,
	                                                             sort = {"createTime"},
	                                                             direction = Sort.Direction.DESC)
	                                                             Pageable pageable) {

	    		CustomerWarehouseStockReportForMonth entity = new CustomerWarehouseStockReportForMonth();
	    		Customer customer = new Customer();
	    		customer.setId(customerId);
	    		entity.setCustomer(customer);

	        Example<CustomerWarehouseStockReportForMonth> example = Example.of(entity);
	        List<CustomerWarehouseStockReportForMonth> report = this.customerWarehouseStockReportForMonthRepository.findAll(example);
	        return new ResponseEntity<List<CustomerWarehouseStockReportForMonth>>(report, HttpStatus.OK);
	    }
}
