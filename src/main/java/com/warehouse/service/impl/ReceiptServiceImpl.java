package com.warehouse.service.impl;

import com.warehouse.model.*;
import com.warehouse.service.ReceiptService;
import com.warehouse.service.WarehouseStockService;
import com.warehouse.service.repository.EntrustOrderRepository;
import com.warehouse.service.repository.MeteringReceiptOrderRepository;
import com.warehouse.service.repository.ReceiptRepository;
import com.warehouse.util.enums.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class ReceiptServiceImpl implements ReceiptService {
    @Autowired
    private WarehouseStockService warehouseStockService;
    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private EntrustOrderRepository entrustOrderRepository;
    @Autowired
    private MeteringReceiptOrderRepository meteringReceiptOrderRepository;

    @Transactional
    @Override
    public Receipt feeConfirm(Receipt entity,
                              BigDecimal opNum,
                              BigDecimal opTonNum,
                              String opMan,
                              String opManId,
                              Customer newCustomer,
                              List<MeteringReceiptOrder> confirmOrders) {

        //委托单对应最后一个入库单，确认了，委托单改为收货完成
        if(entity.getOrderType().equals(ReceiptOrderType.IN_STOCK.getValue())){
            //入库改委托单状态
            EntrustOrder entrustOrder = entity.getEntrustOrder();
            Set<Receipt> receiptSet = entrustOrder.getReceiptSet();
            Iterator<Receipt> iterator = receiptSet.iterator();
            int confirmCount = 0;
            while (iterator.hasNext()){
                Receipt next = iterator.next();
                if(next.getOrderState().equals(ReceiptOrderState.CONFIRM.getValue())){
                    confirmCount++;
                }
            }

            //当前是最后一单入库单确认,修改委托单为收货完成
            if(confirmCount == receiptSet.size()){
                entrustOrder.setOrderState(EntrustOrderState.RECEIVE_FINISH.getValue());
                this.entrustOrderRepository.save(entrustOrder);
            }
        }

        //对接类型不需要修改库存
        if(!entity.getEntrustOrderType().equals(EntrustOrderType.BUTT_JOINT.getValue())){
            //修改库存
            WarehouseStock warehouseStock = new WarehouseStock();

            warehouseStock.setWarehouseLevel(entity.getWarehouseLevel());//库层
            warehouseStock.setProduct(entity.getEntrustOrderDetail().getProduct());
            warehouseStock.setCustomer(entity.getEntrustOrderDetail().getEntrustOrder().getCustomer());
            warehouseStock.setCreateMan(opMan);
            warehouseStock.setCreateManId(opManId);

            WarehouseStockLogOpType opType = null;
            if(ReceiptOrderType.IN_STOCK.getValue().equals(entity.getOrderType())){
                warehouseStock.setNum(opNum);
                warehouseStock.setTonNum(opTonNum);
                opType = WarehouseStockLogOpType.TAKE_OVER;
            }else if(ReceiptOrderType.OUT_STOCK.getValue().equals(entity.getOrderType())){
                warehouseStock.setNum(opNum.negate());//负数
                warehouseStock.setTonNum(opTonNum.negate());//负数
                opType = WarehouseStockLogOpType.CARRY_GOOD;
            }
            if(opType != null){
                List<WarehouseStock> warehouseStockList = new ArrayList<>();
                warehouseStockList.add(warehouseStock);

                Boolean result = warehouseStockService.saveOrUpdate(warehouseStockList,
                        entity.getOrderNo(), entity.getId(),
                        opType.getValue());
                if(result){
                    return this.receiptRepository.save(entity);
                }
            }

            if(newCustomer!=null){
                //修改委托和委托所有的收货单里的客户(第一个分配了后面就不用再分配了)
                EntrustOrder entrustOrder = entity.getEntrustOrderDetail().getEntrustOrder();
                Set<EntrustOrderDetail> entrustOrderDetailSet = entrustOrder.getEntrustOrderDetailSet();
                Iterator<EntrustOrderDetail> iterator = entrustOrderDetailSet.iterator();
                while (iterator.hasNext()){
                    EntrustOrderDetail detail = iterator.next();
                    Receipt receiptQuery = new Receipt();
                    EntrustOrderDetail detail2 = new EntrustOrderDetail();
                    detail2.setId(detail.getId());
                    receiptQuery.setEntrustOrderDetail(detail2);
                    Example<Receipt> receiptExample = Example.of(receiptQuery);
                    List<Receipt> all = this.receiptRepository.findAll(receiptExample);
                    //每个明细只有一条收货数据，取第1条，且不是当前数据
                    if(all!=null&&all.size()>0 && !all.get(0).getId().equals(entity.getId())){
                        Receipt receipt = all.get(0);
                        receipt.setCustomer(newCustomer);
                        this.receiptRepository.save(receipt);
                    }
                }
                entrustOrder.setCustomer(newCustomer);
                this.entrustOrderRepository.save(entrustOrder);
            }
            if(confirmOrders!=null && confirmOrders.size()>0){
                this.meteringReceiptOrderRepository.save(confirmOrders);//批量修改计量单
            }
        }else{
            return this.receiptRepository.save(entity);
        }


        return null;
    }

    @Transactional
    @Override
    public Receipt add(Receipt entity) {
        //修改改委托单状态
        if(entity.getEntrustOrderDetail()!=null
                && entity.getEntrustOrderDetail().getEntrustOrder() != null){
            EntrustOrder entrustOrder = entity.getEntrustOrderDetail().getEntrustOrder();
            if(entrustOrder.getOrderState().equals(EntrustOrderState.CONFIRM.getValue())){
                if(entity.getOrderType().equals(ReceiptOrderType.IN_STOCK.getValue())){
                    //修改为收货中
                    entrustOrder.setOrderState(EntrustOrderState.RECEIVING.getValue());
                }else if(entity.getOrderType().equals(ReceiptOrderType.OUT_STOCK.getValue())){
                    //修改为发货中
                    entrustOrder.setOrderState(EntrustOrderState.SENDING.getValue());
                }
                this.entrustOrderRepository.save(entrustOrder);
            }
        }
        return this.receiptRepository.save(entity);
    }
}
