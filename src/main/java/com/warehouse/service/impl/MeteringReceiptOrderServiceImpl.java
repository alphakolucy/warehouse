package com.warehouse.service.impl;

import com.warehouse.model.MeteringReceiptOrder;
import com.warehouse.model.Receipt;
import com.warehouse.service.MeteringReceiptOrderService;
import com.warehouse.service.repository.MeteringReceiptOrderRepository;
import com.warehouse.service.repository.ReceiptRepository;
import com.warehouse.util.enums.ReceiptOrderState;
import com.warehouse.util.enums.ReceiptOrderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class MeteringReceiptOrderServiceImpl implements MeteringReceiptOrderService {
    @Autowired
    private MeteringReceiptOrderRepository meteringReceiptOrderRepository;
    @Autowired
    private ReceiptRepository receiptRepository;

    @Transactional
    @Override
    public MeteringReceiptOrder add(MeteringReceiptOrder entity) {
        if(entity.getReceipt()!=null){
            Receipt receipt = entity.getReceipt();
            if(receipt.getOrderState().equals(ReceiptOrderState.NEW_CREATE.getValue())){
                //修改状态为计量中
                receipt.setOrderState(ReceiptOrderState.IN_METERING.getValue());
                this.receiptRepository.save(receipt);
            }
        }
        return this.meteringReceiptOrderRepository.save(entity);
    }

    @Transactional
    @Override
    public MeteringReceiptOrder confirm(MeteringReceiptOrder entity) {
        if(entity.getReceipt()!=null){
            Receipt receipt = entity.getReceipt();
            if(receipt.getOrderState().equals(ReceiptOrderState.IN_METERING.getValue())) {
                //修改状态为已计量
                //如果计量单的合计件数或者吨数大于等于 收发货 单里的计划数量,改为已计量
                BigDecimal tonTotal = BigDecimal.ZERO;//之前吨数合计
                BigDecimal numTotal = BigDecimal.ZERO;//之前件数合计
                Set<MeteringReceiptOrder> meteringReceiptOrderSet = receipt.getMeteringReceiptOrderSet();
                Iterator<MeteringReceiptOrder> iterator = meteringReceiptOrderSet.iterator();
                while (iterator.hasNext()){
                    MeteringReceiptOrder next = iterator.next();
                    tonTotal = tonTotal.add(next.getTonNum());
                    numTotal = numTotal.add(next.getNum());
                }

                System.out.println("01.meteringReceiptOrderSet数量Size:"+meteringReceiptOrderSet.size());
                System.out.println("1.tonTotal:"+tonTotal);
                System.out.println("2.numTotal:"+numTotal);

                //加上本次计量一起判断
                tonTotal = tonTotal.add(entity.getTonNum());
                numTotal = numTotal.add(entity.getNum());
                //大于计划吨数
                if(tonTotal.compareTo(receipt.getEntrustOrderDetail().getTonNum())>=0){
                    receipt.setOrderState(ReceiptOrderState.HAD_METERING.getValue());
                    this.receiptRepository.save(receipt);
                    System.out.println("3.修改");
                }//大于计划件数
                else if(numTotal.compareTo(receipt.getEntrustOrderDetail().getNum())>=0){
                    receipt.setOrderState(ReceiptOrderState.HAD_METERING.getValue());
                    this.receiptRepository.save(receipt);
                    System.out.println("4.修改");
                }
            }
        }
        return this.meteringReceiptOrderRepository.save(entity);
    }

    @Override
    public List<MeteringReceiptOrder> confirmList(List<MeteringReceiptOrder> entities) {
        for(MeteringReceiptOrder entity:entities){
            if(entity.getReceipt()!=null){
                Receipt receipt = entity.getReceipt();
                if(receipt.getOrderState().equals(ReceiptOrderState.IN_METERING.getValue())) {
                    //修改状态为已计量
                    //如果计量单的合计件数或者吨数大于等于 收发货 单里的计划数量,改为已计量
                    BigDecimal tonTotal = BigDecimal.ZERO;//之前吨数合计
                    BigDecimal numTotal = BigDecimal.ZERO;//之前件数合计
                    Set<MeteringReceiptOrder> meteringReceiptOrderSet = receipt.getMeteringReceiptOrderSet();
                    Iterator<MeteringReceiptOrder> iterator = meteringReceiptOrderSet.iterator();
                    while (iterator.hasNext()){
                        MeteringReceiptOrder next = iterator.next();
                        tonTotal = tonTotal.add(next.getTonNum());
                        numTotal = numTotal.add(next.getNum());
                    }

                    System.out.println("01.meteringReceiptOrderSet数量Size:"+meteringReceiptOrderSet.size());
                    System.out.println("1.tonTotal:"+tonTotal);
                    System.out.println("2.numTotal:"+numTotal);

                    //加上本次计量一起判断
                    tonTotal = tonTotal.add(entity.getTonNum());
                    numTotal = numTotal.add(entity.getNum());
                    //大于计划吨数
                    if(tonTotal.compareTo(receipt.getEntrustOrderDetail().getTonNum())>=0){
                        receipt.setOrderState(ReceiptOrderState.HAD_METERING.getValue());
                        this.receiptRepository.save(receipt);
                        System.out.println("3.修改");
                    }//大于计划件数
                    else if(numTotal.compareTo(receipt.getEntrustOrderDetail().getNum())>=0){
                        receipt.setOrderState(ReceiptOrderState.HAD_METERING.getValue());
                        this.receiptRepository.save(receipt);
                        System.out.println("4.修改");
                    }
                }
            }
        }
        return this.meteringReceiptOrderRepository.save(entities);
    }
}
