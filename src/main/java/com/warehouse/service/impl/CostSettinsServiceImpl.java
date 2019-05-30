package com.warehouse.service.impl;

import com.warehouse.model.BaseCostSettins;
import com.warehouse.model.CostSettins;
import com.warehouse.model.Customer;
import com.warehouse.model.ProductCates;
import com.warehouse.service.CostSettinsService;
import com.warehouse.service.repository.BaseCostSettinsRepository;
import com.warehouse.service.repository.CostSettinsRepository;
import com.warehouse.service.repository.ProductCatesRepository;
import com.warehouse.service.repository.wisely.Range;
import com.warehouse.util.HttpStatusContent;
import com.warehouse.util.enums.CostSettinsOpType;
import com.warehouse.util.enums.OutputState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CostSettinsServiceImpl implements CostSettinsService {
    @Autowired
    private CostSettinsRepository costSettinsRepository;
    @Autowired
    private BaseCostSettinsRepository baseCostSettinsRepository;
    @Autowired
    private ProductCatesRepository productCatesRepository;

    @Override
    public Map<String,Object> getUnitPrice(String customerId, String cateId,
                                CostSettinsOpType costSettinsOpType) {
        //查找物资分类的计费方式(一种收费类型只能有一种费用用途),取列表第1条数据
        Map<String,Object> map = new HashMap<>();

        ProductCates productCate = this.productCatesRepository.findOne(cateId);
        if(productCate == null){
            map.put("status","no");
            map.put("message","分类不存在");
            return map;
        }

        CostSettins queryCostSettins = new CostSettins();
        ProductCates cate = new ProductCates();
        cate.setId(cateId);
        queryCostSettins.setCate(cate);
        Customer customer = new Customer();
        customer.setId(customerId);
        queryCostSettins.setCustomer(customer);
        queryCostSettins.setOpType(costSettinsOpType.getValue());
        Example<CostSettins> example = Example.of(queryCostSettins);

        List<Range<CostSettins>> ranges = new ArrayList<>();
        Range<CostSettins> startRange = new Range<>("startTime",null,new Date());
        Range<CostSettins> endRange = new Range<>("endTime",new Date(),null);
        ranges.add(startRange);
        ranges.add(endRange);
        Page<CostSettins> costSettins = this.costSettinsRepository.queryByExampleWithRange(example, ranges, null);

        if(costSettins != null && costSettins.getContent().size() > 0){
            if(costSettins.getContent().get(0).getIsUse()!=null
                    && costSettins.getContent().get(0).getIsUse()){
                //有客户的计费方式
                map.put("status","yes");
                map.put("message","成功");
                map.put("unitPrice",costSettins.getContent().get(0).getFee());
                map.put("feePurpose",costSettins.getContent().get(0).getFeePurpose());
                map.put("id",costSettins.getContent().get(0).getId());
                System.out.println("costSettins:"+map);
                return map;
            }
        }

        //第一种计费方式没查到数据再查基础计费方式
        BaseCostSettins queryBaseCostSettins = new BaseCostSettins();
        ProductCates cate2 = new ProductCates();
        cate2.setId(cateId);
        queryBaseCostSettins.setCate(cate2);
        queryBaseCostSettins.setOpType(costSettinsOpType.getValue());
        Example<BaseCostSettins> example2 = Example.of(queryBaseCostSettins);

        List<Range<BaseCostSettins>> ranges2 = new ArrayList<>();
        Range<BaseCostSettins> startRange2 = new Range<>("startTime",null,new Date());
        Range<BaseCostSettins> endRange2 = new Range<>("endTime",new Date(),null);
        ranges2.add(startRange2);
        ranges2.add(endRange2);
        Page<BaseCostSettins> baseCostSettins = this.baseCostSettinsRepository.queryByExampleWithRange(example2, ranges2, null);

        if(baseCostSettins != null && baseCostSettins.getContent().size() > 0){
            if(baseCostSettins.getContent().get(0).getIsUse()!=null
                    && baseCostSettins.getContent().get(0).getIsUse()){
                //有客户的计费方式
                map.put("status","yes");
                map.put("message","成功");
                map.put("unitPrice",baseCostSettins.getContent().get(0).getFee());
                map.put("feePurpose",baseCostSettins.getContent().get(0).getFeePurpose());
                map.put("id",baseCostSettins.getContent().get(0).getId());
                System.out.println("baseCostSettins:"+map);
                return map;
            }
        }
        map.put("status","no");
        map.put("message","未找到符合条件的计费方式");
        return map;
    }
}
