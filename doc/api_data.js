define({ "api": [
  {
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "varname1",
            "description": "<p>No type.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "varname2",
            "description": "<p>With type.</p>"
          }
        ]
      }
    },
    "type": "",
    "url": "",
    "version": "0.0.0",
    "filename": "./doc/main.js",
    "group": "C__Program_wms_api_warehouse_doc_main_js",
    "groupTitle": "C__Program_wms_api_warehouse_doc_main_js",
    "name": ""
  },
  {
    "type": "GET",
    "url": "/adjustOrder",
    "title": "库存调整列表",
    "group": "adjustOrder",
    "version": "0.0.1",
    "description": "<p>库存调整列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "orderNo",
            "description": "<p>单号(支持模糊查询)</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "createTime,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=createTime,desc 表示在按createTime由高到低排列</li> <li>格式： sort=createTime,asc 表示在按createTime由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/adjustOrder",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockId",
            "description": "<p>库存id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>单号</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "num",
            "description": "<p>操作件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "tonNum",
            "description": "<p>操作吨数</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderStateTxt",
            "description": "<p>订单状态名称</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>订单状态</p> <ul> <li>NEW_CREATE(50010, &quot;新建&quot;),</li> <li>CONFIRM(50020, &quot;确认&quot;),</li> <li>INVALID(-50000, &quot;作废&quot;);</li> </ul>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>操作人ID</p>"
          },
          {
            "group": "200",
            "type": "Date",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "customer",
            "description": "<p>客户对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customer.id",
            "description": "<p>客户id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customer.name",
            "description": "<p>企业名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "cate",
            "description": "<p>物资分类对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "cate.id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "cate.productCateName",
            "description": "<p>物资分类名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/AdjustOrderController.java",
    "groupTitle": "adjustOrder",
    "name": "GetAdjustorder"
  },
  {
    "type": "GET",
    "url": "/adjustOrder/{id}",
    "title": "库存调整详细",
    "group": "adjustOrder",
    "version": "0.0.1",
    "description": "<p>库存调整详细</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/adjustOrder/1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockId",
            "description": "<p>库存id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>单号</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "num",
            "description": "<p>操作件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "tonNum",
            "description": "<p>操作吨数</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderStateTxt",
            "description": "<p>订单状态名称</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>订单状态</p> <ul> <li>NEW_CREATE(50010, &quot;新建&quot;),</li> <li>CONFIRM(50020, &quot;确认&quot;),</li> <li>INVALID(-50000, &quot;作废&quot;);</li> </ul>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>操作人ID</p>"
          },
          {
            "group": "200",
            "type": "Date",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "customer",
            "description": "<p>客户对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customer.id",
            "description": "<p>客户id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customer.name",
            "description": "<p>企业名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "cate",
            "description": "<p>物资分类对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "cate.id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "cate.productCateName",
            "description": "<p>物资分类名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/AdjustOrderController.java",
    "groupTitle": "adjustOrder",
    "name": "GetAdjustorderId"
  },
  {
    "type": "GET",
    "url": "/adjustOrderLog",
    "title": "库存调整日志列表",
    "group": "adjustOrderLog",
    "version": "0.0.1",
    "description": "<p>库存调整日志列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "orderState",
            "description": "<p>订单状态(支持模糊查询)</p> <ul> <li>TAKE_OVER(20010, &quot;新建&quot;),</li> <li>CARRY_GOOD(20020, &quot;确认&quot;),</li> <li>REMOVE_STOCK(40040, &quot;作废&quot;)，</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "createTime,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=createTime,desc 表示在按createTime由高到低排列</li> <li>格式： sort=createTime,asc 表示在按createTime由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/adjustOrderLog",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderStateTxt",
            "description": "<p>订单状态名称</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>订单状态</p> <ul> <li>TAKE_OVER(20010, &quot;新建&quot;),</li> <li>CARRY_GOOD(20020, &quot;确认&quot;),</li> <li>REMOVE_STOCK(40040, &quot;作废&quot;)，</li> </ul>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "adjustOrder",
            "description": "<p>调整实体</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "adjustOrder.id",
            "description": "<p>库存调整id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "adjustOrder.orderNo",
            "description": "<p>单号</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "adjustOrder.tonNum",
            "description": "<p>操作吨数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "adjustOrder.num",
            "description": "<p>操作件数</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "adjustOrder.orderState",
            "description": "<p>订单状态</p> <ul> <li>TAKE_OVER(20010, &quot;新建&quot;),</li> <li>CARRY_GOOD(20020, &quot;确认&quot;),</li> <li>REMOVE_STOCK(40040, &quot;作废&quot;)，</li> </ul>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "adjustOrder.remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "adjustOrder.createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/AdjustOrderLogController.java",
    "groupTitle": "adjustOrderLog",
    "name": "GetAdjustorderlog"
  },
  {
    "type": "POST",
    "url": "/adjustOrder",
    "title": "库存调整添加",
    "group": "adjustOrder",
    "version": "0.0.1",
    "description": "<p>库存调整添加</p>",
    "parameter": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockId",
            "description": "<p>库存id</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "num",
            "description": "<p>操作件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "tonNum",
            "description": "<p>操作吨数</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>操作人ID</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/adjustOrder",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/AdjustOrderController.java",
    "groupTitle": "adjustOrder",
    "name": "PostAdjustorder"
  },
  {
    "type": "POST",
    "url": "/adjustOrder/adjustConfirm",
    "title": "库存调整确认",
    "group": "adjustOrder",
    "version": "0.0.1",
    "description": "<p>库存调整确认</p>",
    "parameter": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "adjustOrderIds",
            "description": "<p>(以逗号隔开的格式字符串)</p> <ul> <li>格式：1,2,3,4</li> <li>调整单id,多个逗号隔开</li> </ul>"
          }
        ],
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/adjustOrder/adjustConfirm",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/AdjustOrderController.java",
    "groupTitle": "adjustOrder",
    "name": "PostAdjustorderAdjustconfirm"
  },
  {
    "type": "POST",
    "url": "/adjustOrder/adjustState",
    "title": "调整订单状态变更",
    "group": "adjustOrder",
    "version": "0.0.1",
    "description": "<p>调整订单状态变更</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>调整订单状态</p> <ul> <li>NEW_CREATE(50010, &quot;新建&quot;),</li> <li>CONFIRM(50020, &quot;确认&quot;),</li> <li>INVALID(-50000, &quot;作废&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/adjustOrder/adjustState",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/AdjustOrderController.java",
    "groupTitle": "adjustOrder",
    "name": "PostAdjustorderAdjuststate"
  },
  {
    "type": "PUT",
    "url": "/adjustOrder",
    "title": "库存调整修改",
    "group": "adjustOrder",
    "version": "0.0.1",
    "description": "<p>库存调整修改</p>",
    "parameter": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "num",
            "description": "<p>操作件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "tonNum",
            "description": "<p>操作吨数</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>操作人ID</p>"
          }
        ],
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/adjustOrder",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/AdjustOrderController.java",
    "groupTitle": "adjustOrder",
    "name": "PutAdjustorder"
  },
  {
    "type": "GET",
    "url": "/baseCostSettins",
    "title": "基础计费方式列表",
    "group": "baseCostSettins",
    "version": "0.0.1",
    "description": "<p>基础计费方式列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "opType",
            "description": "<p>收费类型</p> <ul> <li>IN_STOCK(40010, &quot;入库&quot;),</li> <li>OUT_STOCK(40020, &quot;出库&quot;),</li> <li>PROCESS(40030, &quot;加工&quot;),</li> <li>REMOVE_STOCK(40040, &quot;移库&quot;),</li> <li>BUTT_JOINT(40050, &quot;对接&quot;),</li> <li>TRANSFER_NAME(40060, &quot;过户&quot;),</li> <li>TRAY(40070, &quot;托盘&quot;),</li> <li>STORAGE(40080, &quot;仓储&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "cateId",
            "description": "<p>物资分类Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "feePurpose",
            "description": "<p>费用用途</p> <ul> <li>THEORY(13010, &quot;理重&quot;),</li> <li>FACT(13020, &quot;实重&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "isUse",
            "description": "<p>是否使用</p> <ul> <li>true:使用</li> <li>false:停用;</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "id,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=id,desc 表示在按id由高到低排列</li> <li>格式： sort=id,asc 表示在按id由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/baseCostSettins?contract_num=1111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "opType",
            "description": "<p>收费类型</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "opTypeTxt",
            "description": "<p>收费类型Txt</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "fee",
            "description": "<p>费用</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "startTime",
            "description": "<p>开始时间</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "endTime",
            "description": "<p>结束时间</p>"
          },
          {
            "group": "200",
            "type": "Boolean",
            "optional": false,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "chargeUnit",
            "description": "<p>计费单位</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "feePurpose",
            "description": "<p>费用用途</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "cateData",
            "description": "<p>物资分类对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "cateData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "cateData.productCateName",
            "description": "<p>物资分类名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/BaseCostSettinsController.java",
    "groupTitle": "baseCostSettins",
    "name": "GetBasecostsettins"
  },
  {
    "type": "GET",
    "url": "/baseCostSettins/{id}",
    "title": "单个基础计费方式",
    "group": "baseCostSettins",
    "version": "0.0.1",
    "description": "<p>单个基础计费方式</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/baseCostSettins/111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "opType",
            "description": "<p>收费类型</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "opTypeTxt",
            "description": "<p>收费类型Txt</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "fee",
            "description": "<p>费用</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "startTime",
            "description": "<p>开始时间</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "endTime",
            "description": "<p>结束时间</p>"
          },
          {
            "group": "200",
            "type": "Boolean",
            "optional": false,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "chargeUnit",
            "description": "<p>计费单位</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "feePurpose",
            "description": "<p>费用用途</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "cateData",
            "description": "<p>物资分类对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "cateData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "cateData.productCateName",
            "description": "<p>物资分类名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/BaseCostSettinsController.java",
    "groupTitle": "baseCostSettins",
    "name": "GetBasecostsettinsId"
  },
  {
    "type": "POST",
    "url": "/baseCostSettins",
    "title": "基础计费方式添加",
    "group": "baseCostSettins",
    "version": "0.0.1",
    "description": "<p>基础计费方式添加</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "cateId",
            "description": "<p>物质分类Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "opType",
            "description": "<p>收费类型</p> <ul> <li>IN_STOCK(40010, &quot;入库&quot;),</li> <li>OUT_STOCK(40020, &quot;出库&quot;),</li> <li>PROCESS(40030, &quot;加工&quot;),</li> <li>REMOVE_STOCK(40040, &quot;移库&quot;),</li> <li>BUTT_JOINT(40050, &quot;对接&quot;),</li> <li>TRANSFER_NAME(40060, &quot;过户&quot;),</li> <li>TRAY(40070, &quot;托盘&quot;),</li> <li>STORAGE(40080, &quot;仓储&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "feePurpose",
            "description": "<p>费用用途</p> <ul> <li>THEORY(13010, &quot;理重&quot;),</li> <li>FACT(13020, &quot;实重&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "BigDecimal",
            "optional": false,
            "field": "fee",
            "description": "<p>费用</p>"
          },
          {
            "group": "Parameter",
            "type": "DateTime",
            "optional": false,
            "field": "startTime",
            "description": "<p>开始时间</p>"
          },
          {
            "group": "Parameter",
            "type": "DateTime",
            "optional": false,
            "field": "endTime",
            "description": "<p>结束时间</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": false,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/baseCostSettins",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/BaseCostSettinsController.java",
    "groupTitle": "baseCostSettins",
    "name": "PostBasecostsettins"
  },
  {
    "type": "PUT",
    "url": "/baseCostSettins/{id}",
    "title": "基础计费方式修改",
    "group": "baseCostSettins",
    "version": "0.0.1",
    "description": "<p>基础计费方式修改</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "cateId",
            "description": "<p>物质分类Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "opType",
            "description": "<p>收费类型</p> <ul> <li>IN_STOCK(40010, &quot;入库&quot;),</li> <li>OUT_STOCK(40020, &quot;出库&quot;),</li> <li>PROCESS(40030, &quot;加工&quot;),</li> <li>REMOVE_STOCK(40040, &quot;移库&quot;),</li> <li>BUTT_JOINT(40050, &quot;对接&quot;),</li> <li>TRANSFER_NAME(40060, &quot;过户&quot;),</li> <li>TRAY(40070, &quot;托盘&quot;),</li> <li>STORAGE(40080, &quot;仓储&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "feePurpose",
            "description": "<p>费用用途</p> <ul> <li>THEORY(13010, &quot;理重&quot;),</li> <li>FACT(13020, &quot;实重&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "BigDecimal",
            "optional": false,
            "field": "fee",
            "description": "<p>费用</p>"
          },
          {
            "group": "Parameter",
            "type": "DateTime",
            "optional": false,
            "field": "startTime",
            "description": "<p>开始时间</p>"
          },
          {
            "group": "Parameter",
            "type": "DateTime",
            "optional": false,
            "field": "endTime",
            "description": "<p>结束时间</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": false,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/baseCostSettins/18",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/BaseCostSettinsController.java",
    "groupTitle": "baseCostSettins",
    "name": "PutBasecostsettinsId"
  },
  {
    "type": "GET",
    "url": "/costSettins",
    "title": "计费方式列表",
    "group": "costSettins",
    "version": "0.0.1",
    "description": "<p>计费方式列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "opType",
            "description": "<p>收费类型</p> <ul> <li>IN_STOCK(40010, &quot;入库&quot;),</li> <li>OUT_STOCK(40020, &quot;出库&quot;),</li> <li>PROCESS(40030, &quot;加工&quot;),</li> <li>REMOVE_STOCK(40040, &quot;移库&quot;),</li> <li>BUTT_JOINT(40050, &quot;对接&quot;),</li> <li>TRANSFER_NAME(40060, &quot;过户&quot;),</li> <li>TRAY(40070, &quot;托盘&quot;),</li> <li>STORAGE(40080, &quot;仓储&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "customerId",
            "description": "<p>客户Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "cateId",
            "description": "<p>物资分类Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "feePurpose",
            "description": "<p>费用用途</p> <ul> <li>THEORY(13010, &quot;理重&quot;),</li> <li>FACT(13020, &quot;实重&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "isUse",
            "description": "<p>是否使用</p> <ul> <li>true:使用</li> <li>false:停用;</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "id,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=id,desc 表示在按id由高到低排列</li> <li>格式： sort=id,asc 表示在按id由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/costSettins?contract_num=1111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "opType",
            "description": "<p>收费类型</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "opTypeTxt",
            "description": "<p>收费类型Txt</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "fee",
            "description": "<p>费用</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "startTime",
            "description": "<p>开始时间</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "endTime",
            "description": "<p>结束时间</p>"
          },
          {
            "group": "200",
            "type": "Boolean",
            "optional": false,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "chargeUnit",
            "description": "<p>计费单位</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "feePurpose",
            "description": "<p>费用用途</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "cateData",
            "description": "<p>物资分类对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "cateData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "cateData.productCateName",
            "description": "<p>物资分类名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "customerData",
            "description": "<p>客户对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customerData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customerData.name",
            "description": "<p>企业名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/CostSettinsController.java",
    "groupTitle": "costSettins",
    "name": "GetCostsettins"
  },
  {
    "type": "GET",
    "url": "/costSettins/{id}",
    "title": "单个计费方式",
    "group": "costSettins",
    "version": "0.0.1",
    "description": "<p>单个计费方式</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/costSettins/111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "opType",
            "description": "<p>收费类型</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "opTypeTxt",
            "description": "<p>收费类型Txt</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "fee",
            "description": "<p>费用</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "startTime",
            "description": "<p>开始时间</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "endTime",
            "description": "<p>结束时间</p>"
          },
          {
            "group": "200",
            "type": "Boolean",
            "optional": false,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "chargeUnit",
            "description": "<p>计费单位</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "feePurpose",
            "description": "<p>费用用途</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "cateData",
            "description": "<p>物资分类对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "cateData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "cateData.productCateName",
            "description": "<p>物资分类名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "customerData",
            "description": "<p>客户对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customerData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customerData.name",
            "description": "<p>企业名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/CostSettinsController.java",
    "groupTitle": "costSettins",
    "name": "GetCostsettinsId"
  },
  {
    "type": "POST",
    "url": "/costSettins",
    "title": "计费方式添加",
    "group": "costSettins",
    "version": "0.0.1",
    "description": "<p>计费方式添加</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "customerId",
            "description": "<p>客户Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "cateId",
            "description": "<p>物质分类Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "opType",
            "description": "<p>收费类型</p> <ul> <li>IN_STOCK(40010, &quot;入库&quot;),</li> <li>OUT_STOCK(40020, &quot;出库&quot;),</li> <li>PROCESS(40030, &quot;加工&quot;),</li> <li>REMOVE_STOCK(40040, &quot;移库&quot;),</li> <li>BUTT_JOINT(40050, &quot;对接&quot;),</li> <li>TRANSFER_NAME(40060, &quot;过户&quot;),</li> <li>TRAY(40070, &quot;托盘&quot;),</li> <li>STORAGE(40080, &quot;仓储&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "feePurpose",
            "description": "<p>费用用途</p> <ul> <li>THEORY(13010, &quot;理重&quot;),</li> <li>FACT(13020, &quot;实重&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "BigDecimal",
            "optional": false,
            "field": "fee",
            "description": "<p>费用</p>"
          },
          {
            "group": "Parameter",
            "type": "DateTime",
            "optional": false,
            "field": "startTime",
            "description": "<p>开始时间</p>"
          },
          {
            "group": "Parameter",
            "type": "DateTime",
            "optional": false,
            "field": "endTime",
            "description": "<p>结束时间</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": false,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/costSettins",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/CostSettinsController.java",
    "groupTitle": "costSettins",
    "name": "PostCostsettins"
  },
  {
    "type": "PUT",
    "url": "/costSettins/{id}",
    "title": "计费方式修改",
    "group": "costSettins",
    "version": "0.0.1",
    "description": "<p>计费方式修改</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "customerId",
            "description": "<p>客户Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "cateId",
            "description": "<p>物质分类Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "opType",
            "description": "<p>收费类型</p> <ul> <li>IN_STOCK(40010, &quot;入库&quot;),</li> <li>OUT_STOCK(40020, &quot;出库&quot;),</li> <li>PROCESS(40030, &quot;加工&quot;),</li> <li>REMOVE_STOCK(40040, &quot;移库&quot;),</li> <li>BUTT_JOINT(40050, &quot;对接&quot;),</li> <li>TRANSFER_NAME(40060, &quot;过户&quot;),</li> <li>TRAY(40070, &quot;托盘&quot;),</li> <li>STORAGE(40080, &quot;仓储&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "feePurpose",
            "description": "<p>费用用途</p> <ul> <li>THEORY(13010, &quot;理重&quot;),</li> <li>FACT(13020, &quot;实重&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "BigDecimal",
            "optional": false,
            "field": "fee",
            "description": "<p>费用</p>"
          },
          {
            "group": "Parameter",
            "type": "DateTime",
            "optional": false,
            "field": "startTime",
            "description": "<p>开始时间</p>"
          },
          {
            "group": "Parameter",
            "type": "DateTime",
            "optional": false,
            "field": "endTime",
            "description": "<p>结束时间</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": false,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/costSettins/18",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/CostSettinsController.java",
    "groupTitle": "costSettins",
    "name": "PutCostsettinsId"
  },
  {
    "type": "GET",
    "url": "/craneBindSteelyardLog",
    "title": "龙门吊绑秤日志列表",
    "group": "craneBindSteelyardLog",
    "version": "0.0.1",
    "description": "<p>龙门吊绑秤日志列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "craneId",
            "description": "<p>龙门吊Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "opType",
            "description": "<p>操作类型</p> <ul> <li>true:绑定</li> <li>false:解绑</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "createTime,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=createTime,desc 表示在按createTime由高到低排列</li> <li>格式： sort=createTime,asc 表示在按createTime由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/craneBindSteelyardLog",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "Boolean",
            "optional": false,
            "field": "opType",
            "description": "<p>操作类型</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "opTypeTxt",
            "description": "<p>操作类型Txt</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "steelyardData",
            "description": "<p>秤对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "steelyardData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "steelyardData.steelyardNo",
            "description": "<p>编号</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "craneData",
            "description": "<p>龙门吊对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "craneData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "craneData.craneNo",
            "description": "<p>编号</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/CraneBindSteelyardLogController.java",
    "groupTitle": "craneBindSteelyardLog",
    "name": "GetCranebindsteelyardlog"
  },
  {
    "type": "GET",
    "url": "/crane",
    "title": "龙门吊列表",
    "group": "crane",
    "version": "0.0.1",
    "description": "<p>龙门吊列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "craneNo",
            "description": "<p>吊车编号(支持模糊查询)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseId",
            "description": "<p>库区Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "id,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=id,desc 表示在按id由高到低排列</li> <li>格式： sort=id,asc 表示在按id由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/crane?contract_num=1111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "craneNo",
            "description": "<p>吊车编号</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "maintainPeriod",
            "description": "<p>维护周期</p>"
          },
          {
            "group": "200",
            "type": "Date",
            "optional": false,
            "field": "maintainDate",
            "description": "<p>维护日期</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "steelyardData",
            "description": "<p>秤对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "steelyardData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "steelyardData.steelyardNo",
            "description": "<p>编号</p>"
          },
          {
            "group": "200",
            "type": "Boolean",
            "optional": false,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/CraneController.java",
    "groupTitle": "crane",
    "name": "GetCrane"
  },
  {
    "type": "GET",
    "url": "/crane/{id}",
    "title": "单个龙门吊",
    "group": "crane",
    "version": "0.0.1",
    "description": "<p>单个龙门吊</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/crane/111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "craneNo",
            "description": "<p>吊车编号</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "maintainPeriod",
            "description": "<p>维护周期</p>"
          },
          {
            "group": "200",
            "type": "Date",
            "optional": false,
            "field": "maintainDate",
            "description": "<p>维护日期</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "steelyardData",
            "description": "<p>秤对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "steelyardData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "steelyardData.steelyardNo",
            "description": "<p>编号</p>"
          },
          {
            "group": "200",
            "type": "Boolean",
            "optional": false,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/CraneController.java",
    "groupTitle": "crane",
    "name": "GetCraneId"
  },
  {
    "type": "POST",
    "url": "/crane",
    "title": "龙门吊添加",
    "group": "crane",
    "version": "0.0.1",
    "description": "<p>龙门吊添加</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "craneNo",
            "description": "<p>吊车编号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "warehouseId",
            "description": "<p>库区Id(第二级)</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "maintainPeriod",
            "description": "<p>维护周期</p>"
          },
          {
            "group": "Parameter",
            "type": "Date",
            "optional": false,
            "field": "putIntoDate",
            "description": "<p>投用日期</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "isUse",
            "defaultValue": "true",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/crane",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/CraneController.java",
    "groupTitle": "crane",
    "name": "PostCrane"
  },
  {
    "type": "POST",
    "url": "/crane/bind",
    "title": "龙门吊绑定/解绑秤",
    "group": "crane",
    "version": "0.0.1",
    "description": "<p>龙门吊绑定/解绑秤</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "steelyardId",
            "description": "<p>秤Id(绑定)</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": false,
            "field": "opType",
            "description": "<p>操作类型</p> <ul> <li>true:绑定</li> <li>false:解绑</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注(解绑)</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/crane/bind",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/CraneController.java",
    "groupTitle": "crane",
    "name": "PostCraneBind"
  },
  {
    "type": "PUT",
    "url": "/crane/{id}",
    "title": "龙门吊修改",
    "group": "crane",
    "version": "0.0.1",
    "description": "<p>龙门吊修改</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "craneNo",
            "description": "<p>吊车编号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "warehouseId",
            "description": "<p>库区Id(第二级)</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "maintainPeriod",
            "description": "<p>维护周期</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/crane/18",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/CraneController.java",
    "groupTitle": "crane",
    "name": "PutCraneId"
  },
  {
    "type": "GET",
    "url": "/customer",
    "title": "客户列表",
    "group": "customer",
    "version": "0.0.1",
    "description": "<p>客户列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "name",
            "description": "<p>企业名称(支持模糊查询)</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "settlementMethod",
            "description": "<p>结算方式</p> <ul> <li>MONTH_SETTLEMENT(20010, &quot;月结&quot;),</li> <li>NOW_SETTLEMENT(20020, &quot;现结&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "weightMethod",
            "description": "<p>计重方式</p> <ul> <li>EXCEPT_WEIGHT(30010, &quot;理重&quot;),</li> <li>FACT_WEIGHT(30020, &quot;实重&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "id,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=id,desc 表示在按id由高到低排列</li> <li>格式： sort=id,asc 表示在按id由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/customer?contract_num=1111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>企业名称</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "settlementMethod",
            "description": "<p>结算方式</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "settlementMethodTxt",
            "description": "<p>结算方式Txt</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "weightMethod",
            "description": "<p>计重方式</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "weightMethodTxt",
            "description": "<p>计重方式Txt</p>"
          },
          {
            "group": "200",
            "type": "Boolean",
            "optional": false,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/CustomerController.java",
    "groupTitle": "customer",
    "name": "GetCustomer"
  },
  {
    "type": "GET",
    "url": "/customer/{id}",
    "title": "单个客户",
    "group": "customer",
    "version": "0.0.1",
    "description": "<p>单个客户</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/customer/111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>企业名称</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "settlementMethod",
            "description": "<p>结算方式</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "settlementMethodTxt",
            "description": "<p>结算方式Txt</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "weightMethod",
            "description": "<p>计重方式</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "weightMethodTxt",
            "description": "<p>计重方式Txt</p>"
          },
          {
            "group": "200",
            "type": "Boolean",
            "optional": false,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/CustomerController.java",
    "groupTitle": "customer",
    "name": "GetCustomerId"
  },
  {
    "type": "POST",
    "url": "/customer",
    "title": "客户添加",
    "group": "customer",
    "version": "0.0.1",
    "description": "<p>客户添加</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>企业名称</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "settlementMethod",
            "description": "<p>结算方式</p> <ul> <li>MONTH_SETTLEMENT(20010, &quot;月结&quot;),</li> <li>NOW_SETTLEMENT(20020, &quot;现结&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "weightMethod",
            "description": "<p>计重方式</p> <ul> <li>EXCEPT_WEIGHT(30010, &quot;理重&quot;),</li> <li>FACT_WEIGHT(30020, &quot;实重&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "isUse",
            "defaultValue": "true",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/customer",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/CustomerController.java",
    "groupTitle": "customer",
    "name": "PostCustomer"
  },
  {
    "type": "POST",
    "url": "/customer/product",
    "title": "客户添加",
    "group": "customer",
    "version": "0.0.1",
    "description": "<p>客户添加</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>企业名称</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "settlementMethod",
            "description": "<p>结算方式</p> <ul> <li>MONTH_SETTLEMENT(20010, &quot;月结&quot;),</li> <li>NOW_SETTLEMENT(20020, &quot;现结&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "weightMethod",
            "description": "<p>计重方式</p> <ul> <li>EXCEPT_WEIGHT(30010, &quot;理重&quot;),</li> <li>FACT_WEIGHT(30020, &quot;实重&quot;);</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/customer/product",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/CustomerController.java",
    "groupTitle": "customer",
    "name": "PostCustomerProduct"
  },
  {
    "type": "PUT",
    "url": "/customer/{id}",
    "title": "客户修改",
    "group": "customer",
    "version": "0.0.1",
    "description": "<p>客户修改</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>企业名称</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "settlementMethod",
            "description": "<p>结算方式</p> <ul> <li>MONTH_SETTLEMENT(20010, &quot;月结&quot;),</li> <li>NOW_SETTLEMENT(20020, &quot;现结&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "weightMethod",
            "description": "<p>计重方式</p> <ul> <li>EXCEPT_WEIGHT(30010, &quot;理重&quot;),</li> <li>FACT_WEIGHT(30020, &quot;实重&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": false,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/customer/18",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/CustomerController.java",
    "groupTitle": "customer",
    "name": "PutCustomerId"
  },
  {
    "type": "GET",
    "url": "/deviceMaintainLedger",
    "title": "设备维护台账列表",
    "group": "deviceMaintainLedger",
    "version": "0.0.1",
    "description": "<p>设备维护台账列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "deviceNo",
            "description": "<p>设备编号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "deviceId",
            "description": "<p>设备id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "deviceType",
            "description": "<p>设备类型</p> <ul> <li>CRANE(17010, &quot;龙门吊&quot;),</li> <li>STEELYARD(17020, &quot;秤&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "maintainDate",
            "description": "<p>维护日期</p> <ul> <li>yyyy-MM-dd</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "id,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=id,desc 表示在按id由高到低排列</li> <li>格式： sort=id,asc 表示在按id由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/deviceMaintainLedger?name=1111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "deviceNo",
            "description": "<p>设备编号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "deviceId",
            "description": "<p>设备id</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "deviceType",
            "description": "<p>设备类型</p> <ul> <li>CRANE(17010, &quot;龙门吊&quot;),</li> <li>STEELYARD(17020, &quot;秤&quot;);</li> </ul>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "content",
            "description": "<p>维护内容</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "maintainMan",
            "description": "<p>维护人员(来自排班)</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "Date",
            "optional": false,
            "field": "maintainDate",
            "description": "<p>维护日期</p> <ul> <li>yyyy-MM-dd</li> </ul>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/DeviceMaintainLedgerController.java",
    "groupTitle": "deviceMaintainLedger",
    "name": "GetDevicemaintainledger"
  },
  {
    "type": "GET",
    "url": "/deviceMaintainLedger/{id}",
    "title": "单个设备维护台账",
    "group": "deviceMaintainLedger",
    "version": "0.0.1",
    "description": "<p>单个设备维护台账</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/deviceMaintainLedger/111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "deviceNo",
            "description": "<p>设备编号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "deviceId",
            "description": "<p>设备id</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "deviceType",
            "description": "<p>设备类型</p> <ul> <li>CRANE(17010, &quot;龙门吊&quot;),</li> <li>STEELYARD(17020, &quot;秤&quot;);</li> </ul>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "content",
            "description": "<p>维护内容</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "maintainMan",
            "description": "<p>维护人员(来自排班)</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "Date",
            "optional": false,
            "field": "maintainDate",
            "description": "<p>维护日期</p> <ul> <li>yyyy-MM-dd</li> </ul>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/DeviceMaintainLedgerController.java",
    "groupTitle": "deviceMaintainLedger",
    "name": "GetDevicemaintainledgerId"
  },
  {
    "type": "POST",
    "url": "/deviceMaintainLedger",
    "title": "设备维护台账添加",
    "group": "deviceMaintainLedger",
    "version": "0.0.1",
    "description": "<p>设备维护台账添加</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "deviceNo",
            "description": "<p>设备编号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "deviceId",
            "description": "<p>设备id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "deviceType",
            "description": "<p>设备类型</p> <ul> <li>CRANE(17010, &quot;龙门吊&quot;),</li> <li>STEELYARD(17020, &quot;秤&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "content",
            "description": "<p>维护内容</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "maintainMan",
            "description": "<p>维护人员(来自排班)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "Date",
            "optional": false,
            "field": "maintainDate",
            "description": "<p>维护日期</p> <ul> <li>yyyy-MM-dd</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/deviceMaintainLedger",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/DeviceMaintainLedgerController.java",
    "groupTitle": "deviceMaintainLedger",
    "name": "PostDevicemaintainledger"
  },
  {
    "type": "PUT",
    "url": "/deviceMaintainLedger/{id}",
    "title": "设备维护台账修改",
    "group": "deviceMaintainLedger",
    "version": "0.0.1",
    "description": "<p>设备维护台账修改</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "deviceNo",
            "description": "<p>设备编号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "deviceId",
            "description": "<p>设备id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "deviceType",
            "description": "<p>设备类型</p> <ul> <li>CRANE(17010, &quot;龙门吊&quot;),</li> <li>STEELYARD(17020, &quot;秤&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "content",
            "description": "<p>维护内容</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "maintainMan",
            "description": "<p>维护人员(来自排班)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "Date",
            "optional": false,
            "field": "maintainDate",
            "description": "<p>维护日期</p> <ul> <li>yyyy-MM-dd</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/deviceMaintainLedger/18",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/DeviceMaintainLedgerController.java",
    "groupTitle": "deviceMaintainLedger",
    "name": "PutDevicemaintainledgerId"
  },
  {
    "type": "GET",
    "url": "/entrustOrderDetail",
    "title": "委托单明细列表",
    "group": "entrustOrderDetail",
    "version": "0.0.1",
    "description": "<p>委托单明细列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "productId",
            "description": "<p>物资Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "entrustOrderId",
            "description": "<p>委托单Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "id,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=id,desc 表示在按id由高到低排列</li> <li>格式： sort=id,asc 表示在按id由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/entrustOrderDetail?opType=40010&sort=id,asc",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "vehicleNo",
            "description": "<p>车号</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "num",
            "description": "<p>件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "tonNum",
            "description": "<p>吨数</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "productData",
            "description": "<p>物资对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.productName",
            "description": "<p>物资名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.unit",
            "description": "<p>物资单位</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.rationale",
            "description": "<p>物资理重</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.spec",
            "description": "<p>物资规格</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.model",
            "description": "<p>物资型号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.material",
            "description": "<p>物资材料</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.maker",
            "description": "<p>物资厂家</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "entrustOrderData",
            "description": "<p>委托单对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "entrustOrderData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "entrustOrderData.orderNo",
            "description": "<p>委托单单号</p>"
          }
        ],
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Date",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/EntrustOrderDetailController.java",
    "groupTitle": "entrustOrderDetail",
    "name": "GetEntrustorderdetail"
  },
  {
    "type": "GET",
    "url": "/entrustOrder",
    "title": "委托单列表",
    "group": "entrustOrder",
    "version": "0.0.1",
    "description": "<p>委托单列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "orderNo",
            "description": "<p>单号(支持模糊查询)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "orderStateList",
            "description": "<p>订单状态列表</p> <ul> <li>格式逗号分隔 &quot;50010,50020&quot;</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "orderState",
            "description": "<p>订单状态</p> <ul> <li>NEW_CREATE(50010, &quot;新建&quot;),</li> <li>CONFIRM(50020, &quot;确认&quot;),</li> <li>FINISH(50050, &quot;完成&quot;),</li> <li>RECEIVING(50060, &quot;收货中&quot;),</li> <li>RECEIVE_FINISH(50070, &quot;收货完成&quot;),</li> <li>ABOLISH(50080, &quot;废止&quot;),</li> <li>SENDING(50090, &quot;发货中&quot;),</li> <li>SEND_FINISH(50100, &quot;发货完成&quot;),</li> <li>INVALID(-50000, &quot;作废&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "allowedValues": [
              "火车",
              "汽车"
            ],
            "optional": true,
            "field": "arrivalMode",
            "description": "<p>到达方式</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "orderType",
            "description": "<p>订单类型</p> <ul> <li>RECEIVE_GOODS(60010, &quot;收货&quot;),</li> <li>TAKE_GOODS(60020, &quot;提货&quot;),</li> <li>BUTT_JOINT(60030, &quot;对接&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "customerId",
            "description": "<p>客户Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseId",
            "description": "<p>仓库Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "id,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=id,desc 表示在按id由高到低排列</li> <li>格式： sort=id,asc 表示在按id由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/entrustOrder?opType=40010&sort=id,asc",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>单号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customerOrderNo",
            "description": "<p>客户单号</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>订单状态</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderStateTxt",
            "description": "<p>订单状态Txt</p>"
          },
          {
            "group": "200",
            "type": "String",
            "allowedValues": [
              "火车",
              "汽车"
            ],
            "optional": false,
            "field": "arrivalMode",
            "description": "<p>到达方式</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "orderType",
            "description": "<p>订单类型</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderTypeTxt",
            "description": "<p>订单类型Txt</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "advanceFee",
            "description": "<p>预收费用(对接委托单时才显示)</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "fee",
            "description": "<p>实收费用</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "drawer",
            "description": "<p>开单人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "customerData",
            "description": "<p>客户对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customerData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customerData.name",
            "description": "<p>企业名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseData",
            "description": "<p>仓库对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseData.name",
            "description": "<p>名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/EntrustOrderController.java",
    "groupTitle": "entrustOrder",
    "name": "GetEntrustorder"
  },
  {
    "type": "GET",
    "url": "/entrustOrder/{id}",
    "title": "单个委托单",
    "group": "entrustOrder",
    "version": "0.0.1",
    "description": "<p>单个委托单</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/entrustOrder/111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>单号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customerOrderNo",
            "description": "<p>客户单号</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>订单状态</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderStateTxt",
            "description": "<p>订单状态Txt</p>"
          },
          {
            "group": "200",
            "type": "String",
            "allowedValues": [
              "火车",
              "汽车"
            ],
            "optional": false,
            "field": "arrivalMode",
            "description": "<p>到达方式</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "orderType",
            "description": "<p>订单类型</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderTypeTxt",
            "description": "<p>订单类型Txt</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "advanceFee",
            "description": "<p>预收费用(对接委托单时才显示)</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "fee",
            "description": "<p>实收费用</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "drawer",
            "description": "<p>开单人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "customerData",
            "description": "<p>客户对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customerData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customerData.name",
            "description": "<p>企业名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseData",
            "description": "<p>仓库对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseData.name",
            "description": "<p>名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/EntrustOrderController.java",
    "groupTitle": "entrustOrder",
    "name": "GetEntrustorderId"
  },
  {
    "type": "GET",
    "url": "/entrustOrderLog",
    "title": "委托单日志列表",
    "group": "entrustOrderLog",
    "version": "0.0.1",
    "description": "<p>委托单日志列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "entrustOrderId",
            "description": "<p>委托单Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "orderState",
            "description": "<p>订单状态</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "id,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=id,desc 表示在按id由高到低排列</li> <li>格式： sort=id,asc 表示在按id由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/entrustOrderLog?opType=40010&sort=id,asc",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>订单状态</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderStateTxt",
            "description": "<p>订单状态Txt</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>操作人ID</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "entrustOrderData",
            "description": "<p>委托单对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "entrustOrderData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "entrustOrderData.orderNo",
            "description": "<p>委托单单号</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/EntrustOrderLogController.java",
    "groupTitle": "entrustOrderLog",
    "name": "GetEntrustorderlog"
  },
  {
    "type": "POST",
    "url": "/entrustOrder",
    "title": "委托单添加",
    "group": "entrustOrder",
    "version": "0.0.1",
    "description": "<p>委托单添加</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "warehouseId",
            "description": "<p>仓库Id(第一级)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "customerId",
            "description": "<p>客户Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "strJsonArrayProduct",
            "description": "<p>物资列表(json数组格式的字符串)</p> <ul> <li>格式：[{&quot;productId&quot;:&quot;1&quot;,&quot;vehicleNo&quot;:&quot;贵A12345&quot;,&quot;num&quot;:&quot;80&quot;,&quot;tonNum&quot;:&quot;50&quot;}, {&quot;productId&quot;:&quot;2&quot;,&quot;vehicleNo&quot;:&quot;贵C12345&quot;,&quot;num&quot;:&quot;80&quot;,&quot;tonNum&quot;:&quot;50&quot;}]</li> <li>productId: 物资id</li> <li>vehicleNo: 车号</li> <li>num: 件数</li> <li>tonNum: 吨数(最多3位小数)</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "customerOrderNo",
            "description": "<p>客户单号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "allowedValues": [
              "火车",
              "汽车"
            ],
            "optional": false,
            "field": "arrivalMode",
            "description": "<p>到达方式</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "orderType",
            "description": "<p>订单类型</p> <ul> <li>RECEIVE_GOODS(60010, &quot;收货&quot;),</li> <li>TAKE_GOODS(60020, &quot;提货&quot;),</li> <li>BUTT_JOINT(60030, &quot;对接&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "BigDecimal",
            "optional": false,
            "field": "advanceFee",
            "description": "<p>预收费用(对接委托单时才显示)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "drawer",
            "description": "<p>开单人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/entrustOrder",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/EntrustOrderController.java",
    "groupTitle": "entrustOrder",
    "name": "PostEntrustorder"
  },
  {
    "type": "POST",
    "url": "/entrustOrder/orderState/change",
    "title": "委托单状态变更",
    "group": "entrustOrder",
    "version": "0.0.1",
    "description": "<p>委托单状态变更</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>订单状态</p> <ul> <li>CONFIRM(50020, &quot;确认&quot;),</li> <li>FINISH(50050, &quot;完成&quot;),</li> <li>INVALID(-50000, &quot;作废&quot;);</li> <li>RECEIVE_FINISH(50070, &quot;收货完成&quot;),(中止操作,填备注写日志)</li> <li>ABOLISH(50080, &quot;废止&quot;);</li> <li>SEND_FINISH(50100, &quot;发货完成&quot;);(中止操作,填备注写日志)</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/entrustOrder/orderState/change",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/EntrustOrderController.java",
    "groupTitle": "entrustOrder",
    "name": "PostEntrustorderOrderstateChange"
  },
  {
    "type": "PUT",
    "url": "/entrustOrder/{id}",
    "title": "委托单修改",
    "group": "entrustOrder",
    "version": "0.0.1",
    "description": "<p>委托单修改</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "warehouseId",
            "description": "<p>仓库Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "customerId",
            "description": "<p>客户Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "customerOrderNo",
            "description": "<p>客户单号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "allowedValues": [
              "火车",
              "汽车"
            ],
            "optional": false,
            "field": "arrivalMode",
            "description": "<p>到达方式</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "orderType",
            "description": "<p>订单类型</p> <ul> <li>RECEIVE_GOODS(60010, &quot;收货&quot;),</li> <li>TAKE_GOODS(60020, &quot;提货&quot;),</li> <li>BUTT_JOINT(60030, &quot;对接&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "BigDecimal",
            "optional": false,
            "field": "advanceFee",
            "description": "<p>预收费用(对接委托单时才显示)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "drawer",
            "description": "<p>开单人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "strJsonArrayProduct",
            "description": "<p>物资列表(json数组格式的字符串)</p> <ul> <li>格式：[{&quot;id&quot;:&quot;2&quot;,&quot;productId&quot;:&quot;1&quot;,&quot;vehicleNo&quot;:&quot;贵A12345&quot;,&quot;num&quot;:&quot;80&quot;,&quot;tonNum&quot;:&quot;50&quot;}, {&quot;productId&quot;:&quot;2&quot;,&quot;vehicleNo&quot;:&quot;贵C12345&quot;,&quot;num&quot;:&quot;80&quot;,&quot;tonNum&quot;:&quot;50&quot;}]</li> <li>id: id(新加数据没有id)</li> <li>productId: 物资id</li> <li>vehicleNo: 车号</li> <li>num: 件数</li> <li>tonNum: 吨数(最多3位小数)</li> <li>id：明细主键id</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/entrustOrder/18",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/EntrustOrderController.java",
    "groupTitle": "entrustOrder",
    "name": "PutEntrustorderId"
  },
  {
    "type": "GET",
    "url": "/inventoryDetail",
    "title": "库存盘点单明细列表",
    "group": "inventoryDetail",
    "version": "0.0.1",
    "description": "<p>库存盘点单明细列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "inventoryId",
            "description": "<p>库存盘点Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "productId",
            "description": "<p>物资Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "id,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=createTime,desc 表示在按id由高到低排列</li> <li>格式： sort=createTime,asc 表示在按id由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/inventoryDetail?sort=createTime,asc",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "num",
            "description": "<p>件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "tonNum",
            "description": "<p>吨数</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "inventoryData",
            "description": "<p>库存盘点对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventoryData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventoryData.orderNo",
            "description": "<p>盘点单号</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "inventoryData.num",
            "description": "<p>库存件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "inventoryData.tonNum",
            "description": "<p>库存吨数</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventoryData.createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventoryData.createManId",
            "description": "<p>创建人id</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "inventoryData.createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventoryData.orderStateTxt",
            "description": "<p>盘点状态名称</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "inventoryData.orderState",
            "description": "<p>盘点状态</p> <ul> <li>NEW_CREATE(50010, &quot;新建&quot;),</li> <li>CONFIRM(50020, &quot;确认&quot;),</li> <li>INVALID(-50000, &quot;作废&quot;);</li> </ul>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventoryData.remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "productData",
            "description": "<p>物资对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.productName",
            "description": "<p>物资名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.unit",
            "description": "<p>物资单位</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.rationale",
            "description": "<p>物资理重</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.spec",
            "description": "<p>物资规格</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.model",
            "description": "<p>物资型号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.material",
            "description": "<p>物资材料</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.maker",
            "description": "<p>物资厂家</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.productCateName",
            "description": "<p>分类名</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseLevelData",
            "description": "<p>库层对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseLevelData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseLevelData.name",
            "description": "<p>名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/InventoryDetailController.java",
    "groupTitle": "inventoryDetail",
    "name": "GetInventorydetail"
  },
  {
    "type": "GET",
    "url": "/inventoryDetail/{id}",
    "title": "库存盘点单明细详情",
    "group": "inventoryDetail",
    "version": "0.0.1",
    "description": "<p>库存盘点单明细详情</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/inventoryDetail/1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "num",
            "description": "<p>件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "tonNum",
            "description": "<p>吨数</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "inventoryData",
            "description": "<p>库存盘点对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventoryData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventoryData.orderNo",
            "description": "<p>盘点单号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventoryData.num",
            "description": "<p>库存件数</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventoryData.tonNum",
            "description": "<p>库存吨数</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventoryData.createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventoryData.createManId",
            "description": "<p>创建人id</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "inventoryData.createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventoryData.orderStateTxt",
            "description": "<p>盘点状态名称</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "inventoryData.orderState",
            "description": "<p>盘点状态</p> <ul> <li>NEW_CREATE(50010, &quot;新建&quot;),</li> <li>CONFIRM(50020, &quot;确认&quot;),</li> <li>INVALID(-50000, &quot;作废&quot;);</li> </ul>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventoryData.remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "productData",
            "description": "<p>物资对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.productName",
            "description": "<p>物资名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.unit",
            "description": "<p>物资单位</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.rationale",
            "description": "<p>物资理重</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.spec",
            "description": "<p>物资规格</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.model",
            "description": "<p>物资型号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.material",
            "description": "<p>物资材料</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.maker",
            "description": "<p>物资厂家</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.productCateName",
            "description": "<p>分类名</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseLevelData",
            "description": "<p>库层对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseLevelData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseLevelData.name",
            "description": "<p>名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/InventoryDetailController.java",
    "groupTitle": "inventoryDetail",
    "name": "GetInventorydetailId"
  },
  {
    "type": "GET",
    "url": "/inventory",
    "title": "库存盘点列表",
    "group": "inventory",
    "version": "0.0.1",
    "description": "<p>库存盘点列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "orderNo",
            "description": "<p>单号(支持模糊查询)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseSiteId",
            "description": "<p>仓库Id(一级)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseAreaId",
            "description": "<p>库区Id(二级)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseId",
            "description": "<p>库位Id(三级)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseLevelId",
            "description": "<p>库层Id(4级)</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "id,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=id,desc 表示在按id由高到低排列</li> <li>格式： sort=id,asc 表示在按id由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/inventory",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>单号</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "num",
            "description": "<p>操作件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "tonNum",
            "description": "<p>操作吨数</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderStateTxt",
            "description": "<p>盘点状态名称</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>盘点状态</p> <ul> <li>NEW_CREATE(50010, &quot;新建&quot;),</li> <li>CONFIRM(50020, &quot;确认&quot;),</li> <li>INVALID(-50000, &quot;作废&quot;);</li> </ul>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseSiteData",
            "description": "<p>仓库对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseSiteData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseSiteData.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseAreaData",
            "description": "<p>库区对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseAreaData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseAreaData.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseData",
            "description": "<p>库位对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseData.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseLevelData",
            "description": "<p>库层对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseLevelData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseLevelData.name",
            "description": "<p>名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/InventoryController.java",
    "groupTitle": "inventory",
    "name": "GetInventory"
  },
  {
    "type": "GET",
    "url": "/inventory/{id}",
    "title": "库存盘点详情",
    "group": "inventory",
    "version": "0.0.1",
    "description": "<p>库存盘点详情</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/inventory/1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>单号</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "num",
            "description": "<p>操作件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "tonNum",
            "description": "<p>操作吨数</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderStateTxt",
            "description": "<p>盘点状态名称</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>盘点状态</p> <ul> <li>NEW_CREATE(50010, &quot;新建&quot;),</li> <li>CONFIRM(50020, &quot;确认&quot;),</li> <li>INVALID(-50000, &quot;作废&quot;);</li> </ul>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>操作人Id</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseSiteData",
            "description": "<p>仓库对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseSiteData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseSiteData.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseAreaData",
            "description": "<p>库区对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseAreaData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseAreaData.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseData",
            "description": "<p>库位对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseData.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseLevelData",
            "description": "<p>库层对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseLevelData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseLevelData.name",
            "description": "<p>名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/InventoryController.java",
    "groupTitle": "inventory",
    "name": "GetInventoryId"
  },
  {
    "type": "GET",
    "url": "/inventoryLog",
    "title": "库存盘点日志列表",
    "group": "inventoryLog",
    "version": "0.0.1",
    "description": "<p>库存盘点日志列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "inventoryId",
            "description": "<p>库存盘点Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "id,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=createTime,desc 表示在按id由高到低排列</li> <li>格式： sort=createTime,asc 表示在按id由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/inventoryLog?sort=createTime,asc",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>操作人id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderStateTxt",
            "description": "<p>盘点日志状态名称</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>盘点日志状态</p> <ul> <li>NEW_CREATE(50010, &quot;新建&quot;),</li> <li>CONFIRM(50020, &quot;确认&quot;),</li> <li>INVALID(-50000, &quot;作废&quot;);</li> </ul>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventoryData.remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "inventory",
            "description": "<p>盘点对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventory.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventory.warehouseStockId",
            "description": "<p>库存id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventory.orderNo",
            "description": "<p>盘点单号</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "inventory.orderState",
            "description": "<p>盘点状态</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventory.orderStateTxt",
            "description": "<p>盘点状态名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventory.createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventory.createManId",
            "description": "<p>操作人id</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "inventory.num",
            "description": "<p>库存件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "inventory.tonNum",
            "description": "<p>库存吨数</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "inventory.createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventory.remark",
            "description": "<p>备注</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/InventoryLogController.java",
    "groupTitle": "inventoryLog",
    "name": "GetInventorylog"
  },
  {
    "type": "GET",
    "url": "/inventoryLog/{id}",
    "title": "库存盘点日志详情",
    "group": "inventoryLog",
    "version": "0.0.1",
    "description": "<p>库存盘点日志详情</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/inventoryLog/1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>操作人id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderStateTxt",
            "description": "<p>盘点日志状态名称</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>盘点日志状态</p> <ul> <li>NEW_CREATE(50010, &quot;新建&quot;),</li> <li>CONFIRM(50020, &quot;确认&quot;),</li> <li>INVALID(-50000, &quot;作废&quot;);</li> </ul>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventoryData.remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "inventory",
            "description": "<p>盘点对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventory.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventory.warehouseStockId",
            "description": "<p>库存id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventory.orderNo",
            "description": "<p>盘点单号</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "inventory.orderState",
            "description": "<p>盘点状态</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventory.orderStateTxt",
            "description": "<p>盘点状态名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventory.createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventory.createManId",
            "description": "<p>操作人id</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "inventory.num",
            "description": "<p>库存件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "inventory.tonNum",
            "description": "<p>库存吨数</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "inventory.createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "inventory.remark",
            "description": "<p>备注</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/InventoryLogController.java",
    "groupTitle": "inventoryLog",
    "name": "GetInventorylogId"
  },
  {
    "type": "POST",
    "url": "/inventory",
    "title": "库存盘点添加",
    "group": "inventory",
    "version": "0.0.1",
    "description": "<p>库存盘点添加</p>",
    "parameter": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "num",
            "description": "<p>操作件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "tonNum",
            "description": "<p>操作吨数</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>操作人Id</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseSiteId",
            "description": "<p>仓库Id(四选一)</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseAreaId",
            "description": "<p>库区Id(四选一)</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseId",
            "description": "<p>库位Id(四选一)</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseLevelId",
            "description": "<p>库层Id(四选一)</p>"
          },
          {
            "group": "200",
            "type": "List",
            "optional": false,
            "field": "detailsJson",
            "description": "<p>明细列表Json</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "Object",
            "description": "<p>明细单个对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Object.productId",
            "description": "<p>物资id</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "Object.num",
            "description": "<p>件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "Object.tonNum",
            "description": "<p>吨数</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/inventory",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/InventoryController.java",
    "groupTitle": "inventory",
    "name": "PostInventory"
  },
  {
    "type": "POST",
    "url": "/inventory/inventoryState",
    "title": "盘点状态变更",
    "group": "inventory",
    "version": "0.0.1",
    "description": "<p>盘点状态变更</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>盘点状态</p> <ul> <li>NEW_CREATE(50010, &quot;新建&quot;),</li> <li>CONFIRM(50020, &quot;确认&quot;),</li> <li>INVALID(-50000, &quot;作废&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/inventory/inventoryState",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/InventoryController.java",
    "groupTitle": "inventory",
    "name": "PostInventoryInventorystate"
  },
  {
    "type": "PUT",
    "url": "/inventory",
    "title": "库存盘点修改",
    "group": "inventory",
    "version": "0.0.1",
    "description": "<p>库存盘点修改</p>",
    "parameter": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>库存盘点Id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseSiteId",
            "description": "<p>仓库Id(四选一)</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseAreaId",
            "description": "<p>库区Id(四选一)</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseId",
            "description": "<p>库位Id(四选一)</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseLevelId",
            "description": "<p>库层Id(四选一)</p>"
          },
          {
            "group": "200",
            "type": "List",
            "optional": false,
            "field": "detailsJson",
            "description": "<p>明细对象列表</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "Object",
            "description": "<p>明细单个对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "Object.productId",
            "description": "<p>物资id</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "Object.num",
            "description": "<p>件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "Object.tonNum",
            "description": "<p>吨数</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>操作人Id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/inventory",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/InventoryController.java",
    "groupTitle": "inventory",
    "name": "PutInventory"
  },
  {
    "type": "GET",
    "url": "/lockStockOrderDetail",
    "title": "锁库单明细列表",
    "group": "lockStockOrderDetail",
    "version": "0.0.1",
    "description": "<p>锁库单明细列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "productId",
            "description": "<p>物资Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "lockStockOrderId",
            "description": "<p>锁库单Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "id,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=id,desc 表示在按id由高到低排列</li> <li>格式： sort=id,asc 表示在按id由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/lockStockOrderDetail?opType=40010&sort=id,asc",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "vehicleNo",
            "description": "<p>车号</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "num",
            "description": "<p>件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "tonNum",
            "description": "<p>吨数</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "productData",
            "description": "<p>物资对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.productName",
            "description": "<p>物资名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.unit",
            "description": "<p>物资单位</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.rationale",
            "description": "<p>物资理重</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.spec",
            "description": "<p>物资规格</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.model",
            "description": "<p>物资型号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.material",
            "description": "<p>物资材料</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.maker",
            "description": "<p>物资厂家</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "lockStockOrderData",
            "description": "<p>锁库单对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "lockStockOrderData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "lockStockOrderData.orderNo",
            "description": "<p>单号</p>"
          }
        ],
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Date",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/LockStockOrderDetailController.java",
    "groupTitle": "lockStockOrderDetail",
    "name": "GetLockstockorderdetail"
  },
  {
    "type": "GET",
    "url": "/lockStockOrder",
    "title": "锁库列表",
    "group": "lockStockOrder",
    "version": "0.0.1",
    "description": "<p>锁库列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "orderNo",
            "description": "<p>单号(支持模糊查询)</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "orderState",
            "description": "<p>锁库状态</p> <ul> <li>NEW_CREATE(16010, &quot;新建&quot;),</li> <li>LOCK(16020, &quot;锁定&quot;),</li> <li>UNLOCK(16030, &quot;解锁&quot;),</li> <li>INVALID(-16000, &quot;作废&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "customerId",
            "description": "<p>客户Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseId",
            "description": "<p>仓库Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "id,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=id,desc 表示在按id由高到低排列</li> <li>格式： sort=id,asc 表示在按id由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/lockStockOrder?opType=40010&sort=id,asc",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>单号</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>状态</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderStateTxt",
            "description": "<p>状态Txt</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "customerData",
            "description": "<p>客户对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customerData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customerData.name",
            "description": "<p>企业名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseData",
            "description": "<p>仓库对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseData.name",
            "description": "<p>名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/LockStockOrderController.java",
    "groupTitle": "lockStockOrder",
    "name": "GetLockstockorder"
  },
  {
    "type": "GET",
    "url": "/lockStockOrder/{id}",
    "title": "单个锁库",
    "group": "lockStockOrder",
    "version": "0.0.1",
    "description": "<p>单个锁库</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/lockStockOrder/111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>单号</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>锁库状态</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderStateTxt",
            "description": "<p>锁库状态Txt</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseStockData",
            "description": "<p>库存对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockData.id",
            "description": "<p>id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/LockStockOrderController.java",
    "groupTitle": "lockStockOrder",
    "name": "GetLockstockorderId"
  },
  {
    "type": "GET",
    "url": "/lockStockOrderLog",
    "title": "锁库日志列表",
    "group": "lockStockOrderLog",
    "version": "0.0.1",
    "description": "<p>锁库日志列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "orderState",
            "description": "<p>锁库状态</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "lockStockOrderId",
            "description": "<p>锁库id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "createTime,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=createTime,desc 表示在按createTime由高到低排列</li> <li>格式： sort=createTime,asc 表示在按createTime由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/lockStockOrderLog",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderStateTxt",
            "description": "<p>订单状态名称</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>订单状态</p> <ul> <li>TAKE_OVER(20010, &quot;新建&quot;),</li> <li>CARRY_GOOD(20020, &quot;确认&quot;),</li> <li>REMOVE_STOCK(40040, &quot;作废&quot;)，</li> </ul>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "lockStockOrderData",
            "description": "<p>锁库对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "lockStockOrderData.id",
            "description": "<p>锁库id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "lockStockOrderData.orderNo",
            "description": "<p>单号</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/LockStockOrderLogController.java",
    "groupTitle": "lockStockOrderLog",
    "name": "GetLockstockorderlog"
  },
  {
    "type": "POST",
    "url": "/lockStockOrder",
    "title": "锁库单添加",
    "group": "lockStockOrder",
    "version": "0.0.1",
    "description": "<p>锁库单添加</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "warehouseId",
            "description": "<p>仓库Id(第一级)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "customerId",
            "description": "<p>客户Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "strJsonArrayProduct",
            "description": "<p>物资列表(json数组格式的字符串)</p> <ul> <li>格式：[{&quot;productId&quot;:&quot;1&quot;,&quot;num&quot;:&quot;80&quot;,&quot;tonNum&quot;:&quot;50&quot;}, {&quot;productId&quot;:&quot;2&quot;,&quot;num&quot;:&quot;80&quot;,&quot;tonNum&quot;:&quot;50&quot;}]</li> <li>productId: 物资id</li> <li>num: 件数</li> <li>tonNum: 吨数</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/lockStockOrder",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/LockStockOrderController.java",
    "groupTitle": "lockStockOrder",
    "name": "PostLockstockorder"
  },
  {
    "type": "POST",
    "url": "/lockStockOrder/orderState/change",
    "title": "锁库状态变更",
    "group": "lockStockOrder",
    "version": "0.0.1",
    "description": "<p>锁库状态变更</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>锁库状态</p> <ul> <li>LOCK(16020, &quot;锁定&quot;),</li> <li>UNLOCK(16030, &quot;解锁&quot;),</li> <li>INVALID(-16000, &quot;作废&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注(解锁/锁定必填)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/lockStockOrder/orderState/change",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/LockStockOrderController.java",
    "groupTitle": "lockStockOrder",
    "name": "PostLockstockorderOrderstateChange"
  },
  {
    "type": "PUT",
    "url": "/lockStockOrder/{id}",
    "title": "锁库单修改",
    "group": "lockStockOrder",
    "version": "0.0.1",
    "description": "<p>锁库单修改</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "warehouseId",
            "description": "<p>仓库Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "customerId",
            "description": "<p>客户Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "strJsonArrayProduct",
            "description": "<p>物资列表(json数组格式的字符串)</p> <ul> <li>格式：[{&quot;id&quot;:&quot;2&quot;,&quot;productId&quot;:&quot;1&quot;,&quot;num&quot;:&quot;80&quot;,&quot;tonNum&quot;:&quot;50&quot;}, {&quot;productId&quot;:&quot;2&quot;,&quot;num&quot;:&quot;80&quot;,&quot;tonNum&quot;:&quot;50&quot;}]</li> <li>productId: 物资id</li> <li>num: 件数</li> <li>tonNum: 吨数</li> <li>id：明细主键id</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/lockStockOrder/18",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/LockStockOrderController.java",
    "groupTitle": "lockStockOrder",
    "name": "PutLockstockorderId"
  },
  {
    "type": "GET",
    "url": "/meteringReceiptOrder",
    "title": "计量单列表",
    "group": "meteringReceiptOrder",
    "version": "0.0.1",
    "description": "<p>计量单列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "orderNo",
            "description": "<p>单号(支持模糊查询)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "craneId",
            "description": "<p>吊车Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "steelyardId",
            "description": "<p>秤Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "receiptId",
            "description": "<p>收发货单Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "orderState",
            "description": "<p>订单状态</p> <ul> <li>NEW_CREATE(11010, &quot;新建&quot;),</li> <li>CONFIRM(11020, &quot;确认&quot;),</li> <li>INVALID(-11000, &quot;作废&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "id,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=id,desc 表示在按id由高到低排列</li> <li>格式： sort=id,asc 表示在按id由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/meteringReceiptOrder?opType=40010&sort=id,asc",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>单号</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>订单状态</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderStateTxt",
            "description": "<p>订单状态Txt</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "num",
            "description": "<p>实收件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "tonNum",
            "description": "<p>实收吨数</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "fee",
            "description": "<p>实收费用</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "receiptData",
            "description": "<p>收发货单对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "receiptData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "receiptData.orderNo",
            "description": "<p>单号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "receiptData.productMaker",
            "description": "<p>厂家</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "receiptData.productName",
            "description": "<p>物资名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "receiptData.productMaterial",
            "description": "<p>材料</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "receiptData.productModel",
            "description": "<p>型号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "receiptData.productSpec",
            "description": "<p>规格</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "receiptData.productRationale",
            "description": "<p>理重</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "receiptData.productUnit",
            "description": "<p>单位</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "craneData",
            "description": "<p>吊车对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "craneData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "craneData.craneNo",
            "description": "<p>吊车编号</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "steelyardData",
            "description": "<p>秤对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "steelyardData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "steelyardData.steelyardNo",
            "description": "<p>秤编号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "vehicleNo",
            "description": "<p>车号(发货单计量才有)</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "tallyMan",
            "description": "<p>理货员(新增)</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "meteringMan",
            "description": "<p>计量员(新增)</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "trafficMan",
            "description": "<p>行车工(新增)</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/MeteringReceiptOrderController.java",
    "groupTitle": "meteringReceiptOrder",
    "name": "GetMeteringreceiptorder"
  },
  {
    "type": "GET",
    "url": "/meteringReceiptOrder/{id}",
    "title": "单个计量单",
    "group": "meteringReceiptOrder",
    "version": "0.0.1",
    "description": "<p>单个计量单</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/meteringReceiptOrder/111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>单号</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>订单状态</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderStateTxt",
            "description": "<p>订单状态Txt</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "num",
            "description": "<p>实收件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "tonNum",
            "description": "<p>实收吨数</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "fee",
            "description": "<p>实收费用</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "receiptData",
            "description": "<p>收发货单对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "receiptData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "receiptData.orderNo",
            "description": "<p>单号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "receiptData.productMaker",
            "description": "<p>厂家</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "receiptData.productName",
            "description": "<p>物资名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "receiptData.productMaterial",
            "description": "<p>材料</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "receiptData.productModel",
            "description": "<p>型号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "receiptData.productSpec",
            "description": "<p>规格</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "receiptData.productRationale",
            "description": "<p>理重</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "receiptData.productUnit",
            "description": "<p>单位</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "craneData",
            "description": "<p>吊车对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "craneData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "craneData.craneNo",
            "description": "<p>吊车编号</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "steelyardData",
            "description": "<p>秤对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "steelyardData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "steelyardData.steelyardNo",
            "description": "<p>秤编号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "vehicleNo",
            "description": "<p>车号(发货单计量才有)</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "tallyMan",
            "description": "<p>理货员(新增)</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "meteringMan",
            "description": "<p>计量员(新增)</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "trafficMan",
            "description": "<p>行车工(新增)</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/MeteringReceiptOrderController.java",
    "groupTitle": "meteringReceiptOrder",
    "name": "GetMeteringreceiptorderId"
  },
  {
    "type": "GET",
    "url": "/meteringReceiptOrderLog",
    "title": "计量单日志列表",
    "group": "meteringReceiptOrderLog",
    "version": "0.0.1",
    "description": "<p>计量单日志列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "meteringReceiptOrderId",
            "description": "<p>计量单Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "orderState",
            "description": "<p>订单状态</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "id,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=id,desc 表示在按id由高到低排列</li> <li>格式： sort=id,asc 表示在按id由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/meteringReceiptOrderLog?opType=40010&sort=id,asc",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>订单状态</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderStateTxt",
            "description": "<p>订单状态Txt</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>操作人ID</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "meteringReceiptOrderData",
            "description": "<p>计量单对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "meteringReceiptOrderData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "meteringReceiptOrderData.orderNo",
            "description": "<p>计量单单号</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/MeteringReceiptOrderLogController.java",
    "groupTitle": "meteringReceiptOrderLog",
    "name": "GetMeteringreceiptorderlog"
  },
  {
    "type": "POST",
    "url": "/meteringReceiptOrder",
    "title": "计量单添加",
    "group": "meteringReceiptOrder",
    "version": "0.0.1",
    "description": "<p>计量单添加</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "craneId",
            "description": "<p>吊车Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "steelyardId",
            "description": "<p>秤Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "receiptId",
            "description": "<p>收发货单Id</p>"
          },
          {
            "group": "Parameter",
            "type": "BigDecimal",
            "optional": false,
            "field": "num",
            "description": "<p>实收件数</p>"
          },
          {
            "group": "Parameter",
            "type": "BigDecimal",
            "optional": false,
            "field": "tonNum",
            "description": "<p>实收吨数</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "vehicleNo",
            "description": "<p>车号(发货单计量才有)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "tallyMan",
            "description": "<p>理货员(新增)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "meteringMan",
            "description": "<p>计量员(新增)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "trafficMan",
            "description": "<p>行车工(新增)</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/meteringReceiptOrder",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/MeteringReceiptOrderController.java",
    "groupTitle": "meteringReceiptOrder",
    "name": "PostMeteringreceiptorder"
  },
  {
    "type": "POST",
    "url": "/meteringReceiptOrder/transferState/change",
    "title": "计量单状态变更",
    "group": "meteringReceiptOrder",
    "version": "0.0.1",
    "description": "<p>计量单状态变更</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>订单状态</p> <ul> <li>CONFIRM(11020, &quot;确认&quot;),</li> <li>INVALID(-11000, &quot;作废&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/meteringReceiptOrder/transferState/change",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/MeteringReceiptOrderController.java",
    "groupTitle": "meteringReceiptOrder",
    "name": "PostMeteringreceiptorderTransferstateChange"
  },
  {
    "type": "POST",
    "url": "/meteringReceiptOrder/transferState/multi",
    "title": "计量单状态确认(批量)",
    "group": "meteringReceiptOrder",
    "version": "0.0.1",
    "description": "<p>计量单状态确认(批量)</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "ids",
            "description": "<p>主键id列表(逗号分隔)</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>订单状态</p> <ul> <li>CONFIRM(11020, &quot;确认&quot;),</li> <li>INVALID(-11000, &quot;作废&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/meteringReceiptOrder/transferState/multi",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/MeteringReceiptOrderController.java",
    "groupTitle": "meteringReceiptOrder",
    "name": "PostMeteringreceiptorderTransferstateMulti"
  },
  {
    "type": "PUT",
    "url": "/meteringReceiptOrder/{id}",
    "title": "计量单修改",
    "group": "meteringReceiptOrder",
    "version": "0.0.1",
    "description": "<p>计量单修改</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "craneId",
            "description": "<p>吊车Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "steelyardId",
            "description": "<p>秤Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "receiptId",
            "description": "<p>收发货单Id</p>"
          },
          {
            "group": "Parameter",
            "type": "BigDecimal",
            "optional": false,
            "field": "num",
            "description": "<p>实收件数</p>"
          },
          {
            "group": "Parameter",
            "type": "BigDecimal",
            "optional": false,
            "field": "tonNum",
            "description": "<p>实收吨数</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "vehicleNo",
            "description": "<p>车号(发货单计量才有)</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/meteringReceiptOrder/18",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/MeteringReceiptOrderController.java",
    "groupTitle": "meteringReceiptOrder",
    "name": "PutMeteringreceiptorderId"
  },
  {
    "type": "GET",
    "url": "/moveStockOrder",
    "title": "移库列表",
    "group": "moveStockOrder",
    "version": "0.0.1",
    "description": "<p>移库列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "orderNo",
            "description": "<p>单号(支持模糊查询)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "toWarehouseId",
            "description": "<p>到库位id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "productId",
            "description": "<p>物资id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "createTime,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=createTime,desc 表示在按id由高到低排列</li> <li>格式： sort=createTime,asc 表示在按id由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/moveStockOrder",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>单号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "num",
            "description": "<p>操作件数</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "tonNum",
            "description": "<p>操作吨数</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderStateTxt",
            "description": "<p>订单状态名称</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>订单状态</p> <ul> <li>NEW_CREATE(50010, &quot;新建&quot;),</li> <li>CONFIRM(50020, &quot;确认&quot;),</li> <li>INVALID(-50000, &quot;作废&quot;);</li> </ul>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>操作人ID</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "toWarehouse",
            "description": "<p>库层实体</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "toWarehouse.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "toWarehouse.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "productData",
            "description": "<p>库存物资对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.productName",
            "description": "<p>物资名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.unit",
            "description": "<p>物资单位</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.rationale",
            "description": "<p>物资理重</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.spec",
            "description": "<p>物资规格</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.model",
            "description": "<p>物资型号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.material",
            "description": "<p>物资材料</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.maker",
            "description": "<p>物资厂家</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.productCateName",
            "description": "<p>分类名</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "forkliftMan",
            "description": "<p>叉车工</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/MoveStockOrderController.java",
    "groupTitle": "moveStockOrder",
    "name": "GetMovestockorder"
  },
  {
    "type": "GET",
    "url": "/moveStockOrder/{id}",
    "title": "移库详细",
    "group": "moveStockOrder",
    "version": "0.0.1",
    "description": "<p>移库详细</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/moveStockOrder/1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>单号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "num",
            "description": "<p>操作件数</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "tonNum",
            "description": "<p>操作吨数</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderStateTxt",
            "description": "<p>订单状态名称</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>订单状态</p> <ul> <li>NEW_CREATE(50010, &quot;新建&quot;),</li> <li>CONFIRM(50020, &quot;确认&quot;),</li> <li>INVALID(-50000, &quot;作废&quot;);</li> </ul>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>操作人ID</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "toWarehouse",
            "description": "<p>库层实体</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "toWarehouse.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "toWarehouse.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "productData",
            "description": "<p>库存物资对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.productName",
            "description": "<p>物资名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.unit",
            "description": "<p>物资单位</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.rationale",
            "description": "<p>物资理重</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.spec",
            "description": "<p>物资规格</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.model",
            "description": "<p>物资型号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.material",
            "description": "<p>物资材料</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.maker",
            "description": "<p>物资厂家</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.productCateName",
            "description": "<p>分类名</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "forkliftMan",
            "description": "<p>叉车工</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/MoveStockOrderController.java",
    "groupTitle": "moveStockOrder",
    "name": "GetMovestockorderId"
  },
  {
    "type": "GET",
    "url": "/moveStockOrderLog",
    "title": "移库单日志列表",
    "group": "moveStockOrderLog",
    "version": "0.0.1",
    "description": "<p>移库单日志列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "orderState",
            "description": "<p>移库单状态</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "moveStockOrderId",
            "description": "<p>移库id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "createTime,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=createTime,desc 表示在按createTime由高到低排列</li> <li>格式： sort=createTime,asc 表示在按createTime由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/moveStockOrderLog",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderStateTxt",
            "description": "<p>订单状态名称</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>订单状态</p> <ul> <li>TAKE_OVER(20010, &quot;新建&quot;),</li> <li>CARRY_GOOD(20020, &quot;确认&quot;),</li> <li>REMOVE_STOCK(40040, &quot;作废&quot;)，</li> </ul>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "moveStockOrder",
            "description": "<p>库存移库</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "moveStockOrder.id",
            "description": "<p>库存移库id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "moveStockOrder.orderNo",
            "description": "<p>单号</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "moveStockOrder.tonNum",
            "description": "<p>操作吨数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "moveStockOrder.num",
            "description": "<p>操作件数</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "moveStockOrder.remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "moveStockOrder.createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "moveStockOrder.createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "moveStockOrder.createManId",
            "description": "<p>操作人Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/MoveStockOrderLogController.java",
    "groupTitle": "moveStockOrderLog",
    "name": "GetMovestockorderlog"
  },
  {
    "type": "POST",
    "url": "/moveStockOrder",
    "title": "库存移库修改",
    "group": "moveStockOrder",
    "version": "0.0.1",
    "description": "<p>库存移库修改</p>",
    "parameter": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "toWarehouseId",
            "description": "<p>仓库库层id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockId",
            "description": "<p>库存id</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "num",
            "description": "<p>操作件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "tonNum",
            "description": "<p>操作吨数</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>操作人ID</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/moveStockOrder",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/MoveStockOrderController.java",
    "groupTitle": "moveStockOrder",
    "name": "PostMovestockorder"
  },
  {
    "type": "POST",
    "url": "/moveStockOrder",
    "title": "库存移库添加",
    "group": "moveStockOrder",
    "version": "0.0.1",
    "description": "<p>库存移库添加</p>",
    "parameter": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "toWarehouseId",
            "description": "<p>仓库库层id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockId",
            "description": "<p>库存id</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "num",
            "description": "<p>操作件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "tonNum",
            "description": "<p>操作吨数</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>操作人ID</p>"
          }
        ],
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "forkliftMan",
            "description": "<p>叉车工</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/moveStockOrder",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/MoveStockOrderController.java",
    "groupTitle": "moveStockOrder",
    "name": "PostMovestockorder"
  },
  {
    "type": "POST",
    "url": "/moveStockOrder/moveStockConfirm",
    "title": "确认移库",
    "group": "moveStockOrder",
    "version": "0.0.1",
    "description": "<p>确认移库</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/moveStockOrder/moveStockConfirm",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/MoveStockOrderController.java",
    "groupTitle": "moveStockOrder",
    "name": "PostMovestockorderMovestockconfirm"
  },
  {
    "type": "POST",
    "url": "/moveStockOrder/moveStockState",
    "title": "移库订单状态变更",
    "group": "moveStockOrder",
    "version": "0.0.1",
    "description": "<p>移库订单状态变更</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "ids",
            "description": "<p>主键id 多个id,以逗号隔开</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>移库订单状态</p> <ul> <li>NEW_CREATE(50010, &quot;新建&quot;),</li> <li>INVALID(-50000, &quot;作废&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/moveStockOrder/moveStockState",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/MoveStockOrderController.java",
    "groupTitle": "moveStockOrder",
    "name": "PostMovestockorderMovestockstate"
  },
  {
    "type": "GET",
    "url": "/productCates",
    "title": "物资分类列表",
    "group": "productCates",
    "version": "0.0.1",
    "description": "<p>物资分类列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "productCateName",
            "description": "<p>物资分类名称(支持模糊查询)</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "createTime,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=createTime,desc 表示在按createTime由高到低排列</li> <li>格式： sort=createTime,asc 表示在按createTime由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/productCates",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productCateName",
            "description": "<p>物资分类名称</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/ProductCatesController.java",
    "groupTitle": "productCates",
    "name": "GetProductcates"
  },
  {
    "type": "GET",
    "url": "/productCates/{id}",
    "title": "物资分类详细",
    "group": "productCates",
    "version": "0.0.1",
    "description": "<p>物资分类详细</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/productCates/1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productCateName",
            "description": "<p>物资名称</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/ProductCatesController.java",
    "groupTitle": "productCates",
    "name": "GetProductcatesId"
  },
  {
    "type": "POST",
    "url": "/productCates",
    "title": "物资分类添加",
    "group": "productCates",
    "version": "0.0.1",
    "description": "<p>物资分类添加</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>物资分类名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/productCates",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/ProductCatesController.java",
    "groupTitle": "productCates",
    "name": "PostProductcates"
  },
  {
    "type": "PUT",
    "url": "/productCates",
    "title": "物资分类修改",
    "group": "productCates",
    "version": "0.0.1",
    "description": "<p>物资分类修改</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>物资分类名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/productCates",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/ProductCatesController.java",
    "groupTitle": "productCates",
    "name": "PutProductcates"
  },
  {
    "type": "GET",
    "url": "/product",
    "title": "物资列表",
    "group": "product",
    "version": "0.0.1",
    "description": "<p>物资列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "productName",
            "description": "<p>物资名称(支持模糊查询)</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "createTime,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=createTime,desc 表示在按createTime由高到低排列</li> <li>格式： sort=createTime,asc 表示在按createTime由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/product",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productName",
            "description": "<p>物资名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "unit",
            "description": "<p>物资单位</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "rationale",
            "description": "<p>物资理重</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "spec",
            "description": "<p>物资规格</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "model",
            "description": "<p>物资型号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "material",
            "description": "<p>物资材料</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "maker",
            "description": "<p>物资厂家</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "customer",
            "description": "<p>客户对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customer.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customer.name",
            "description": "<p>企业名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "cate",
            "description": "<p>物资分类对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "cate.id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "cate.productCateName",
            "description": "<p>物资分类名称</p>"
          },
          {
            "group": "200",
            "type": "Boolean",
            "optional": false,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          }
        ],
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Date",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/ProductController.java",
    "groupTitle": "product",
    "name": "GetProduct"
  },
  {
    "type": "GET",
    "url": "/product/{id}",
    "title": "物资详细",
    "group": "product",
    "version": "0.0.1",
    "description": "<p>物资详细</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/product/1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productName",
            "description": "<p>物资名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "unit",
            "description": "<p>物资单位</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "rationale",
            "description": "<p>物资理重</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "spec",
            "description": "<p>物资规格</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "model",
            "description": "<p>物资型号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "material",
            "description": "<p>物资材料</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "maker",
            "description": "<p>物资厂家</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "customer",
            "description": "<p>客户对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customer.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customer.name",
            "description": "<p>企业名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "cate",
            "description": "<p>物资分类对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "cate.id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "cate.productCateName",
            "description": "<p>物资分类名称</p>"
          },
          {
            "group": "200",
            "type": "Boolean",
            "optional": false,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          }
        ],
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Date",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/ProductController.java",
    "groupTitle": "product",
    "name": "GetProductId"
  },
  {
    "type": "POST",
    "url": "/product",
    "title": "物资添加",
    "group": "product",
    "version": "0.0.1",
    "description": "<p>物资添加</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "customerId",
            "description": "<p>客户id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "cateId",
            "description": "<p>物资分类id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "productName",
            "description": "<p>物资名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "unit",
            "description": "<p>物资单位</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "rationale",
            "description": "<p>物资理重</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "spec",
            "description": "<p>物资规格</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "model",
            "description": "<p>物资型号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "material",
            "description": "<p>物资材料</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "maker",
            "description": "<p>物资厂家</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "isUse",
            "defaultValue": "true",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/product",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/ProductController.java",
    "groupTitle": "product",
    "name": "PostProduct"
  },
  {
    "type": "PUT",
    "url": "/product",
    "title": "物资修改",
    "group": "product_1",
    "version": "0.0.1",
    "description": "<p>物资修改</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "customerId",
            "description": "<p>客户id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "cateId",
            "description": "<p>物资分类id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "productName",
            "description": "<p>物资名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "unit",
            "description": "<p>物资单位</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "rationale",
            "description": "<p>物资理重</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "spec",
            "description": "<p>物资规格</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "model",
            "description": "<p>物资型号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "material",
            "description": "<p>物资材料</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "maker",
            "description": "<p>物资厂家</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": false,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/product",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/ProductController.java",
    "groupTitle": "product_1",
    "name": "PutProduct"
  },
  {
    "type": "GET",
    "url": "/receipt",
    "title": "收发货单列表",
    "group": "receipt",
    "version": "0.0.1",
    "description": "<p>收发货单列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "receiptSettlementId",
            "description": "<p>收发货结算单Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "entrustOrderDetailId",
            "description": "<p>委托单明细Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "orderNo",
            "description": "<p>单号(支持模糊查询)</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "orderType",
            "description": "<p>订单类型</p> <ul> <li>IN_STOCK(14010, &quot;入库&quot;),</li> <li>OUT_STOCK(14020, &quot;出库&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "orderState",
            "description": "<p>订单状态</p> <ul> <li>NEW_CREATE(90010, &quot;新建&quot;),</li> <li>IN_METERING(90020, &quot;计量中&quot;),</li> <li>HAD_METERING(90030, &quot;已计量&quot;),</li> <li>CONFIRM(90040, &quot;已确认&quot;),</li> <li>PASS(90060, &quot;已放行&quot;),</li> <li>INVALID(-90000, &quot;作废&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "allowedValues": [
              "true",
              "false"
            ],
            "optional": false,
            "field": "hasMeteringConfirm",
            "description": "<p>计量单是否有一个确认过</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "entrustOrderId",
            "description": "<p>委托单Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "handWarehouse",
            "description": "<p>是否分配库区/库层</p> <ul> <li>true:已分配</li> <li>false:未分配</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/receipt?contract_num=1111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>单号</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>订单状态</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderStateTxt",
            "description": "<p>订单状态Txt</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "orderType",
            "description": "<p>订单类型</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderTypeTxt",
            "description": "<p>订单类型Txt</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "fee",
            "description": "<p>费用</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "num",
            "description": "<p>件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "tonNum",
            "description": "<p>吨数</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseSiteData",
            "description": "<p>仓库对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseSiteData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseSiteData.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseAreaData",
            "description": "<p>库区对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseAreaData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseAreaData.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseData",
            "description": "<p>库位对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseData.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "customerData",
            "description": "<p>客户对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customerData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customerData.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "productData",
            "description": "<p>物资对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.productName",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "entrustOrderDetailData",
            "description": "<p>委托单明细对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "entrustOrderDetailData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "entrustOrderDetailData.vehicleNo",
            "description": "<p>车号</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "entrustOrderDetailData.num",
            "description": "<p>件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "entrustOrderDetailData.tonNum",
            "description": "<p>吨数</p>"
          },
          {
            "group": "200",
            "type": "Boolean",
            "allowedValues": [
              "true",
              "false"
            ],
            "optional": false,
            "field": "hasMeteringConfirm",
            "description": "<p>计量单是否有一个确认过</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "planData",
            "description": "<p>计划对象</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "planData.planNum",
            "description": "<p>数量</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "noMeterData",
            "description": "<p>未计量对象</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "noMeterData.meterNum",
            "description": "<p>数量</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "noMeterData.meterTonNum",
            "description": "<p>吨数</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "meterData",
            "description": "<p>已计量</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "meterData.meterNum",
            "description": "<p>数量</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "meterData.meterTonNum",
            "description": "<p>吨数</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "yardman",
            "description": "<p>调度员(收货单新增)</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "tallyMan",
            "description": "<p>理货员(收货单新增)</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "loadMan",
            "description": "<p>装卸工(收、发货单确认)</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "auditMan",
            "description": "<p>稽核员(收货单确认)</p>"
          },
          {
            "group": "200",
            "type": "Boolean",
            "optional": false,
            "field": "handWarehouse",
            "description": "<p>是否分配库区/库层</p> <ul> <li>true:已分配</li> <li>false:未分配</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/ReceiptController.java",
    "groupTitle": "receipt",
    "name": "GetReceipt"
  },
  {
    "type": "GET",
    "url": "/receipt/{id}",
    "title": "收发货单详细",
    "group": "receipt",
    "version": "0.0.1",
    "description": "<p>收发货单详细</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/receipt/111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>单号</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>订单状态</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderStateTxt",
            "description": "<p>订单状态Txt</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "orderType",
            "description": "<p>订单类型</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderTypeTxt",
            "description": "<p>订单类型Txt</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "fee",
            "description": "<p>费用</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "num",
            "description": "<p>件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "tonNum",
            "description": "<p>吨数</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseSiteData",
            "description": "<p>仓库对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseSiteData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseSiteData.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseAreaData",
            "description": "<p>库区对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseAreaData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseAreaData.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseData",
            "description": "<p>库位对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseData.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "customerData",
            "description": "<p>客户对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customerData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customerData.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "productData",
            "description": "<p>物资对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.productName",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "entrustOrderDetailData",
            "description": "<p>委托单明细对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "entrustOrderDetailData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "entrustOrderDetailData.vehicleNo",
            "description": "<p>车号</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "entrustOrderDetailData.num",
            "description": "<p>件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "entrustOrderDetailData.tonNum",
            "description": "<p>吨数</p>"
          },
          {
            "group": "200",
            "type": "Boolean",
            "allowedValues": [
              "true",
              "false"
            ],
            "optional": false,
            "field": "hasMeteringConfirm",
            "description": "<p>计量单是否有一个确认过</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "planData",
            "description": "<p>计划对象</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "planData.planNum",
            "description": "<p>数量</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "noMeterData",
            "description": "<p>未计量对象</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "noMeterData.meterNum",
            "description": "<p>数量</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "noMeterData.meterTonNum",
            "description": "<p>吨数</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "meterData",
            "description": "<p>已计量</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "meterData.meterNum",
            "description": "<p>数量</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "meterData.meterTonNum",
            "description": "<p>吨数</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "yardman",
            "description": "<p>调度员(收货单新增)</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "tallyMan",
            "description": "<p>理货员(收货单新增)</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "loadMan",
            "description": "<p>装卸工(收、发货单确认)</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "auditMan",
            "description": "<p>稽核员(收货单确认)</p>"
          },
          {
            "group": "200",
            "type": "Boolean",
            "optional": false,
            "field": "handWarehouse",
            "description": "<p>是否分配库区/库层</p> <ul> <li>true:已分配</li> <li>false:未分配</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/ReceiptController.java",
    "groupTitle": "receipt",
    "name": "GetReceiptId"
  },
  {
    "type": "GET",
    "url": "/receiptLog",
    "title": "收发货日志列表",
    "group": "receiptLog",
    "version": "0.0.1",
    "description": "<p>收发货日志列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "receiptId",
            "description": "<p>收发货Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "orderState",
            "description": "<p>订单状态</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "id,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=id,desc 表示在按id由高到低排列</li> <li>格式： sort=id,asc 表示在按id由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/receiptLog?opType=40010&sort=id,asc",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>订单状态</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderStateTxt",
            "description": "<p>订单状态Txt</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>操作人ID</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "receiptData",
            "description": "<p>收发货对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "receiptData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "receiptData.orderNo",
            "description": "<p>收发货单号</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/ReceiptLogController.java",
    "groupTitle": "receiptLog",
    "name": "GetReceiptlog"
  },
  {
    "type": "POST",
    "url": "/receipt",
    "title": "收发货单修改",
    "group": "receipt",
    "version": "0.0.1",
    "description": "<p>收发货单修改</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "entrustOrderDetailId",
            "description": "<p>委托单明细Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/customer",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/ReceiptController.java",
    "groupTitle": "receipt",
    "name": "PostReceipt"
  },
  {
    "type": "POST",
    "url": "/receipt",
    "title": "收发货单添加",
    "group": "receipt",
    "version": "0.0.1",
    "description": "<p>收发货单添加</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "entrustOrderDetailId",
            "description": "<p>委托单明细Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "orderType",
            "description": "<p>订单类型（入库 出库）</p> <ul> <li>IN_STOCK(14010, &quot;入库&quot;),</li> <li>OUT_STOCK(14020, &quot;出库&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "yardman",
            "description": "<p>调度员(收货单新增)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "tallyMan",
            "description": "<p>理货员(收货单新增)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>操作人ID</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/customer",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/ReceiptController.java",
    "groupTitle": "receipt",
    "name": "PostReceipt"
  },
  {
    "type": "POST",
    "url": "/receipt/orderState/change",
    "title": "收发货单状态变更",
    "group": "receipt",
    "version": "0.0.1",
    "description": "<p>收发货单状态变更</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>订单状态</p> <ul> <li>HAD_METERING(90030, &quot;已计量&quot;),</li> <li>PASS(90060, &quot;已放行&quot;),(针对已确认的发货单)</li> <li>INVALID(-90000, &quot;作废&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseLevelId",
            "description": "<p>库层Id(第4级)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseAreaId",
            "description": "<p>库区Id(第2级)</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "allowedValues": [
              "true"
            ],
            "optional": true,
            "field": "handWarehouse",
            "description": "<p>是否分配库区\\库层</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/receipt/orderState/change",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/ReceiptController.java",
    "groupTitle": "receipt",
    "name": "PostReceiptOrderstateChange"
  },
  {
    "type": "POST",
    "url": "/receipt/orderState/feeConfirm",
    "title": "收发货单收费确认",
    "group": "receipt",
    "version": "0.0.1",
    "description": "<p>收发货单收费确认</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseLevelId",
            "description": "<p>库层id(收货单 分配库层)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "loadMan",
            "description": "<p>装卸工(收、发货单确认)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "auditMan",
            "description": "<p>稽核员(收货单确认)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "customerId",
            "description": "<p>客户id(未知客户:收货单确认)</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/receipt/orderState/feeConfirm",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/ReceiptController.java",
    "groupTitle": "receipt",
    "name": "PostReceiptOrderstateFeeconfirm"
  },
  {
    "type": "GET",
    "url": "/receiptSettlement",
    "title": "收发货结算单列表",
    "group": "receiptSettlement",
    "version": "0.0.1",
    "description": "<p>收发货结算单列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "orderNo",
            "description": "<p>结算单单号(支持模糊查询)</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "allowedValues": [
              "true",
              "false"
            ],
            "optional": true,
            "field": "isPay",
            "description": "<p>是否生成过支付单</p> <ul> <li>true：已生成</li> <li>false:未生成</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "id,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=id,desc 表示在按id由高到低排列</li> <li>格式： sort=id,asc 表示在按id由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/receiptSettlement?contract_num=1111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>结算单号</p>"
          },
          {
            "group": "200",
            "type": "Boolean",
            "allowedValues": [
              "true",
              "false"
            ],
            "optional": false,
            "field": "isPay",
            "description": "<p>是否生成过支付单</p> <ul> <li>true：已生成</li> <li>false:未生成</li> </ul>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "amount",
            "description": "<p>结算费用</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/ReceiptSettlementController.java",
    "groupTitle": "receiptSettlement",
    "name": "GetReceiptsettlement"
  },
  {
    "type": "GET",
    "url": "/receiptSettlement/{id}",
    "title": "单个收发货结算单",
    "group": "receiptSettlement",
    "version": "0.0.1",
    "description": "<p>单个收发货结算单</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/receiptSettlement/111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>结算单号</p>"
          },
          {
            "group": "200",
            "type": "Boolean",
            "allowedValues": [
              "true",
              "false"
            ],
            "optional": false,
            "field": "isPay",
            "description": "<p>是否生成过支付单</p> <ul> <li>true：已生成</li> <li>false:未生成</li> </ul>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "amount",
            "description": "<p>结算费用</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/ReceiptSettlementController.java",
    "groupTitle": "receiptSettlement",
    "name": "GetReceiptsettlementId"
  },
  {
    "type": "GET",
    "url": "/receiptSettlementPayment",
    "title": "收发货结算支付单列表",
    "group": "receiptSettlementPayment",
    "version": "0.0.1",
    "description": "<p>收发货结算支付单列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "orderNo",
            "description": "<p>支付单号(支持模糊查询)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "receiptSettlementId",
            "description": "<p>收发货结算单Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "id,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=id,desc 表示在按id由高到低排列</li> <li>格式： sort=id,asc 表示在按id由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/receiptSettlementPayment?contract_num=1111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>结算单号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "bankVoucherNo",
            "description": "<p>支付银行凭证号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "bankName",
            "description": "<p>支付银行</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "draweeTime",
            "description": "<p>支付时间</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "transferSettlementData",
            "description": "<p>收发货结算单对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "transferSettlementData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "transferSettlementData.orderNo",
            "description": "<p>结算单号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "transferSettlementData.amount",
            "description": "<p>结算费用</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/ReceiptSettlementPaymentController.java",
    "groupTitle": "receiptSettlementPayment",
    "name": "GetReceiptsettlementpayment"
  },
  {
    "type": "GET",
    "url": "/receiptSettlementPayment/{id}",
    "title": "单个收发货结算支付单",
    "group": "receiptSettlementPayment",
    "version": "0.0.1",
    "description": "<p>单个收发货结算支付单</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/receiptSettlementPayment/111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>结算单号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "bankVoucherNo",
            "description": "<p>支付银行凭证号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "bankName",
            "description": "<p>支付银行</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "draweeTime",
            "description": "<p>支付时间</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "transferSettlementData",
            "description": "<p>收发货结算单对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "transferSettlementData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "transferSettlementData.orderNo",
            "description": "<p>结算单号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "transferSettlementData.amount",
            "description": "<p>结算费用</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/ReceiptSettlementPaymentController.java",
    "groupTitle": "receiptSettlementPayment",
    "name": "GetReceiptsettlementpaymentId"
  },
  {
    "type": "POST",
    "url": "/receiptSettlementPayment",
    "title": "收发货结算支付单添加",
    "group": "receiptSettlementPayment",
    "version": "0.0.1",
    "description": "<p>收发货结算支付单添加</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "receiptSettlementId",
            "description": "<p>收发货结算单id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "bankVoucherNo",
            "description": "<p>支付银行凭证号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "bankName",
            "description": "<p>支付银行</p>"
          },
          {
            "group": "Parameter",
            "type": "DateTime",
            "optional": false,
            "field": "draweeTime",
            "description": "<p>支付时间</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/receiptSettlementPayment",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/ReceiptSettlementPaymentController.java",
    "groupTitle": "receiptSettlementPayment",
    "name": "PostReceiptsettlementpayment"
  },
  {
    "type": "PUT",
    "url": "/receiptSettlementPayment/{id}",
    "title": "收发货结算支付单修改",
    "group": "receiptSettlementPayment",
    "version": "0.0.1",
    "description": "<p>收发货结算支付单修改</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "receiptSettlementId",
            "description": "<p>收发货结算单id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "bankVoucherNo",
            "description": "<p>支付银行凭证号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "bankName",
            "description": "<p>支付银行</p>"
          },
          {
            "group": "Parameter",
            "type": "DateTime",
            "optional": false,
            "field": "draweeTime",
            "description": "<p>支付时间</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/receiptSettlementPayment/18",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/ReceiptSettlementPaymentController.java",
    "groupTitle": "receiptSettlementPayment",
    "name": "PutReceiptsettlementpaymentId"
  },
  {
    "type": "POST",
    "url": "/receiptSettlement",
    "title": "收发货结算单添加",
    "group": "receiptSettlement",
    "version": "0.0.1",
    "description": "<p>收发货结算单添加</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "receiptIds",
            "description": "<p>收发货单id列表</p> <ul> <li>格式：&quot;1,2,3,4&quot;</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "BigDecimal",
            "optional": false,
            "field": "amount",
            "description": "<p>结算费用</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/receiptSettlement",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/ReceiptSettlementController.java",
    "groupTitle": "receiptSettlement",
    "name": "PostReceiptsettlement"
  },
  {
    "type": "PUT",
    "url": "/receiptSettlement/{id}",
    "title": "收发货结算单修改",
    "group": "receiptSettlement",
    "version": "0.0.1",
    "description": "<p>收发货结算单修改</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "receiptIds",
            "description": "<p>收发货单id列表</p> <ul> <li>格式：&quot;1,2,3,4&quot;</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "BigDecimal",
            "optional": false,
            "field": "amount",
            "description": "<p>结算费用</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/receiptSettlement/18",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/ReceiptSettlementController.java",
    "groupTitle": "receiptSettlement",
    "name": "PutReceiptsettlementId"
  },
  {
    "type": "DELETE",
    "url": "/scheduling/{id}",
    "title": "排班删除",
    "group": "scheduling",
    "version": "0.0.1",
    "description": "<p>排班删除</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/scheduling/18",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/SchedulingController.java",
    "groupTitle": "scheduling",
    "name": "DeleteSchedulingId"
  },
  {
    "type": "GET",
    "url": "/scheduling",
    "title": "排班列表",
    "group": "scheduling",
    "version": "0.0.1",
    "description": "<p>排班列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "name",
            "description": "<p>名称(支持模糊查询)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "job",
            "description": "<p>岗位(支持模糊查询)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "schedulingTime",
            "description": "<p>当值时间</p> <ul> <li>yyyy-MM-dd</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "startDate",
            "description": "<p>当值时间开始&gt;=</p> <ul> <li>yyyy-MM-dd</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "endDate",
            "description": "<p>当值时间结束&lt;=</p> <ul> <li>yyyy-MM-dd</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseSiteId",
            "description": "<p>仓库Id(第一级)</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "id,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=id,desc 表示在按id由高到低排列</li> <li>格式： sort=id,asc 表示在按id由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/scheduling?name=1111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "job",
            "description": "<p>岗位</p>"
          },
          {
            "group": "200",
            "type": "Date",
            "optional": false,
            "field": "schedulingTime",
            "description": "<p>当值时间</p> <ul> <li>yyyy-MM-dd</li> </ul>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseSiteData",
            "description": "<p>仓库对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseSiteData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseSiteData.name",
            "description": "<p>名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/SchedulingController.java",
    "groupTitle": "scheduling",
    "name": "GetScheduling"
  },
  {
    "type": "GET",
    "url": "/scheduling/{id}",
    "title": "单个排班",
    "group": "scheduling",
    "version": "0.0.1",
    "description": "<p>单个排班</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/scheduling/111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "job",
            "description": "<p>岗位</p>"
          },
          {
            "group": "200",
            "type": "Date",
            "optional": false,
            "field": "schedulingTime",
            "description": "<p>当值时间</p> <ul> <li>yyyy-MM-dd</li> </ul>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseSiteData",
            "description": "<p>仓库对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseSiteData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseSiteData.name",
            "description": "<p>名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/SchedulingController.java",
    "groupTitle": "scheduling",
    "name": "GetSchedulingId"
  },
  {
    "type": "POST",
    "url": "/scheduling",
    "title": "排班添加",
    "group": "scheduling",
    "version": "0.0.1",
    "description": "<p>排班添加</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>名称(来自worker)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "job",
            "description": "<p>岗位(来自worker)</p>"
          },
          {
            "group": "Parameter",
            "type": "Date",
            "optional": false,
            "field": "schedulingTime",
            "description": "<p>当值时间</p> <ul> <li>yyyy-MM-dd</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "warehouseSiteId",
            "description": "<p>仓库Id(第一级)</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/scheduling",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/SchedulingController.java",
    "groupTitle": "scheduling",
    "name": "PostScheduling"
  },
  {
    "type": "PUT",
    "url": "/scheduling/{id}",
    "title": "排班修改",
    "group": "scheduling",
    "version": "0.0.1",
    "description": "<p>排班修改</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>名称(来自worker)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "job",
            "description": "<p>岗位(来自worker)</p>"
          },
          {
            "group": "Parameter",
            "type": "Date",
            "optional": false,
            "field": "schedulingTime",
            "description": "<p>当值时间</p> <ul> <li>yyyy-MM-dd</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseSiteId",
            "description": "<p>仓库Id(第一级)</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/scheduling/18",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/SchedulingController.java",
    "groupTitle": "scheduling",
    "name": "PutSchedulingId"
  },
  {
    "type": "GET",
    "url": "/steelyard",
    "title": "秤列表",
    "group": "steelyard",
    "version": "0.0.1",
    "description": "<p>秤列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "steelyardNo",
            "description": "<p>吊车编号(支持模糊查询)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseId",
            "description": "<p>库区Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "id,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=id,desc 表示在按id由高到低排列</li> <li>格式： sort=id,asc 表示在按id由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/steelyard?contract_num=1111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "steelyardNo",
            "description": "<p>吊车编号</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "maintainPeriod",
            "description": "<p>维护周期</p>"
          },
          {
            "group": "200",
            "type": "Date",
            "optional": false,
            "field": "maintainDate",
            "description": "<p>维护日期</p>"
          },
          {
            "group": "200",
            "type": "Boolean",
            "optional": false,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/SteelyardController.java",
    "groupTitle": "steelyard",
    "name": "GetSteelyard"
  },
  {
    "type": "GET",
    "url": "/steelyard/{id}",
    "title": "单个秤",
    "group": "steelyard",
    "version": "0.0.1",
    "description": "<p>单个秤</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/steelyard/111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "steelyardNo",
            "description": "<p>吊车编号</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "maintainPeriod",
            "description": "<p>维护周期</p>"
          },
          {
            "group": "200",
            "type": "Date",
            "optional": false,
            "field": "maintainDate",
            "description": "<p>维护日期</p>"
          },
          {
            "group": "200",
            "type": "Boolean",
            "optional": false,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/SteelyardController.java",
    "groupTitle": "steelyard",
    "name": "GetSteelyardId"
  },
  {
    "type": "POST",
    "url": "/steelyard",
    "title": "秤添加",
    "group": "steelyard",
    "version": "0.0.1",
    "description": "<p>秤添加</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "steelyardNo",
            "description": "<p>吊车编号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "warehouseId",
            "description": "<p>库区Id(第二级)</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "maintainPeriod",
            "description": "<p>维护周期</p>"
          },
          {
            "group": "Parameter",
            "type": "Date",
            "optional": false,
            "field": "putIntoDate",
            "description": "<p>投用日期</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "isUse",
            "defaultValue": "true",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/steelyard",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/SteelyardController.java",
    "groupTitle": "steelyard",
    "name": "PostSteelyard"
  },
  {
    "type": "PUT",
    "url": "/steelyard/{id}",
    "title": "秤修改",
    "group": "steelyard",
    "version": "0.0.1",
    "description": "<p>秤修改</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "steelyardNo",
            "description": "<p>吊车编号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "warehouseId",
            "description": "<p>库区Id(第二级)</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "maintainPeriod",
            "description": "<p>维护周期</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/steelyard/18",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/SteelyardController.java",
    "groupTitle": "steelyard",
    "name": "PutSteelyardId"
  },
  {
    "type": "GET",
    "url": "/transferDetail",
    "title": "过户单单明细列表",
    "group": "transferDetail",
    "version": "0.0.1",
    "description": "<p>过户单单明细列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "transferId",
            "description": "<p>过户单Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseStockId",
            "description": "<p>库存单Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "id,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=createTime,desc 表示在按id由高到低排列</li> <li>格式： sort=createTime,asc 表示在按id由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/transferDetail?sort=createTime,asc",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "num",
            "description": "<p>件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "tonNum",
            "description": "<p>吨数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "fee",
            "description": "<p>费用</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "transferData",
            "description": "<p>过户单对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "transferData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "transferData.orderNo",
            "description": "<p>单号</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "transferData.num",
            "description": "<p>库存件数</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseStockData",
            "description": "<p>库存对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "warehouseStockData.num",
            "description": "<p>库存件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "warehouseStockData.tonNum",
            "description": "<p>库存吨数</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "warehouseStockData.createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseStockData.customerData",
            "description": "<p>客户对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockData.customerData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockData.customerData.name",
            "description": "<p>企业名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseStockData.productData",
            "description": "<p>物资分类对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockData.productData.id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockData.productData.productName",
            "description": "<p>物资分类名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseStockData.warehouseSiteData",
            "description": "<p>仓库实体</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockData.warehouseSiteData.id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockData.warehouseSiteData.name",
            "description": "<p>仓库名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseStockData.warehouseAreaData",
            "description": "<p>库区实体</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockData.warehouseAreaData.id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockData.warehouseAreaData.name",
            "description": "<p>库区名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseStockData.warehouseData",
            "description": "<p>库位实体</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockData.warehouseData.id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockData.warehouseData.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseStockData.warehouseLevelData",
            "description": "<p>库位实体</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockData.warehouseLevelData.id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockData.warehouseLevelData.name",
            "description": "<p>名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/TransferDetailController.java",
    "groupTitle": "transferDetail",
    "name": "GetTransferdetail"
  },
  {
    "type": "GET",
    "url": "/transferDetail/{id}",
    "title": "过户单单明细详情",
    "group": "transferDetail",
    "version": "0.0.1",
    "description": "<p>过户单单明细详情</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/transferDetail/1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "num",
            "description": "<p>件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "tonNum",
            "description": "<p>吨数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "fee",
            "description": "<p>费用</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "transferData",
            "description": "<p>过户单对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "transferData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "transferData.orderNo",
            "description": "<p>单号</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "transferData.num",
            "description": "<p>库存件数</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseStockData",
            "description": "<p>库存对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "warehouseStockData.num",
            "description": "<p>库存件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "warehouseStockData.tonNum",
            "description": "<p>库存吨数</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "warehouseStockData.createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseStockData.customerData",
            "description": "<p>客户对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockData.customerData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockData.customerData.name",
            "description": "<p>企业名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseStockData.productData",
            "description": "<p>物资分类对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockData.productData.id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockData.productData.productName",
            "description": "<p>物资分类名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseStockData.warehouseSiteData",
            "description": "<p>仓库实体</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockData.warehouseSiteData.id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockData.warehouseSiteData.name",
            "description": "<p>仓库名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseStockData.warehouseAreaData",
            "description": "<p>库区实体</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockData.warehouseAreaData.id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockData.warehouseAreaData.name",
            "description": "<p>库区名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseStockData.warehouseData",
            "description": "<p>库位实体</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockData.warehouseData.id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockData.warehouseData.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseStockData.warehouseLevelData",
            "description": "<p>库位实体</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockData.warehouseLevelData.id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStockData.warehouseLevelData.name",
            "description": "<p>名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/TransferDetailController.java",
    "groupTitle": "transferDetail",
    "name": "GetTransferdetailId"
  },
  {
    "type": "GET",
    "url": "/transfer",
    "title": "过户列表",
    "group": "transfer",
    "version": "0.0.1",
    "description": "<p>过户列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "orderNo",
            "description": "<p>单号(支持模糊查询)</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "transferState",
            "description": "<p>过户状态</p> <ul> <li>NEW_CREATE(70010, &quot;新建&quot;),</li> <li>HAD_TRANSFER(70020, &quot;已过户&quot;),</li> <li>INVALID(-70000, &quot;作废&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "transferType",
            "description": "<p>过户方式</p> <ul> <li>DIRECT_TRANSFER(80010, &quot;直接过户&quot;),//（直接调整库存）</li> <li>NEED_WAREHOUSE_CONFIRM(80020, &quot;需要仓库确认&quot;);//（库仓库确认为调整）</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "fromCustomerId",
            "description": "<p>从某个客户Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "toCustomerId",
            "description": "<p>到某个客户Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "transferSettlementId",
            "description": "<p>过户结算单Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "id,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=id,desc 表示在按id由高到低排列</li> <li>格式： sort=id,asc 表示在按id由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/transfer?opType=40010&sort=id,asc",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>单号</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "transferState",
            "description": "<p>过户状态</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "transferStateTxt",
            "description": "<p>过户状态Txt</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "transferType",
            "description": "<p>过户方式</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "transferTypeTxt",
            "description": "<p>过户类型Txt</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "fee",
            "description": "<p>费用</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "fromCustomerData",
            "description": "<p>从某个客户对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "fromCustomerData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "fromCustomerData.name",
            "description": "<p>企业名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "toCustomerData",
            "description": "<p>到某个客户对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "toCustomerData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "toCustomerData.name",
            "description": "<p>企业名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/TransferController.java",
    "groupTitle": "transfer",
    "name": "GetTransfer"
  },
  {
    "type": "GET",
    "url": "/transfer/{id}",
    "title": "单个过户",
    "group": "transfer",
    "version": "0.0.1",
    "description": "<p>单个过户</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/transfer/111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>单号</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "transferState",
            "description": "<p>过户状态</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "transferStateTxt",
            "description": "<p>过户状态Txt</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "transferType",
            "description": "<p>过户方式</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "transferTypeTxt",
            "description": "<p>过户类型Txt</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "fee",
            "description": "<p>费用</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "fromCustomerData",
            "description": "<p>从某个客户对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "fromCustomerData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "fromCustomerData.name",
            "description": "<p>企业名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "toCustomerData",
            "description": "<p>到某个客户对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "toCustomerData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "toCustomerData.name",
            "description": "<p>企业名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/TransferController.java",
    "groupTitle": "transfer",
    "name": "GetTransferId"
  },
  {
    "type": "GET",
    "url": "/transferLog",
    "title": "过户单日志列表",
    "group": "transferLog",
    "version": "0.0.1",
    "description": "<p>过户单日志列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "transferId",
            "description": "<p>过户单Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "orderState",
            "description": "<p>订单状态</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "id,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=id,desc 表示在按id由高到低排列</li> <li>格式： sort=id,asc 表示在按id由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/transferLog?opType=40010&sort=id,asc",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "orderState",
            "description": "<p>订单状态</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderStateTxt",
            "description": "<p>订单状态Txt</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>操作人ID</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "transferData",
            "description": "<p>过户单对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "transferData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "transferData.orderNo",
            "description": "<p>过户单单号</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/TransferLogController.java",
    "groupTitle": "transferLog",
    "name": "GetTransferlog"
  },
  {
    "type": "POST",
    "url": "/transfer",
    "title": "过户添加",
    "group": "transfer",
    "version": "0.0.1",
    "description": "<p>过户添加</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "detailsJson",
            "description": "<p>详细JSON数组</p> <ul> <li>格式：[{&quot;warehouseStockId&quot;:&quot;2&quot;,&quot;num&quot;:&quot;15&quot;,&quot;tonNum&quot;:&quot;255&quot;}, {&quot;warehouseStockId&quot;:&quot;5&quot;,&quot;num&quot;:&quot;33&quot;,&quot;tonNum&quot;:&quot;556&quot;}]</li> <li>warehouseStockId:库存id</li> <li>num:操作件数</li> <li>tonNum:操作吨数</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "fromCustomerId",
            "description": "<p>从某个客户Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "toCustomerId",
            "description": "<p>到某个客户Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "transferType",
            "description": "<p>过户方式</p> <ul> <li>DIRECT_TRANSFER(80010, &quot;直接过户&quot;),//（直接调整库存）</li> <li>NEED_WAREHOUSE_CONFIRM(80020, &quot;需要仓库确认&quot;);//（库仓库确认为调整）</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/transfer",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/TransferController.java",
    "groupTitle": "transfer",
    "name": "PostTransfer"
  },
  {
    "type": "POST",
    "url": "/transfer/transferState/change",
    "title": "过户状态变更",
    "group": "transfer",
    "version": "0.0.1",
    "description": "<p>过户状态变更</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "transferState",
            "description": "<p>过户状态</p> <ul> <li>INVALID(-70000, &quot;作废&quot;);</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/transfer/transferState/change",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/TransferController.java",
    "groupTitle": "transfer",
    "name": "PostTransferTransferstateChange"
  },
  {
    "type": "POST",
    "url": "/transfer/warehouseConfirm",
    "title": "确认",
    "group": "transfer",
    "version": "0.0.1",
    "description": "<p>确认</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "transferState",
            "description": "<p>过户状态</p> <ul> <li>HAD_TRANSFER(70020, &quot;已过户&quot;)</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/transfer/warehouseConfirm",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/TransferController.java",
    "groupTitle": "transfer",
    "name": "PostTransferWarehouseconfirm"
  },
  {
    "type": "PUT",
    "url": "/transfer/{id}",
    "title": "过户修改",
    "group": "transfer",
    "version": "0.0.1",
    "description": "<p>过户修改</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "detailsJson",
            "description": "<p>明细json数组</p> <ul> <li>格式：[{&quot;warehouseStockId&quot;:&quot;2&quot;,&quot;num&quot;:&quot;15&quot;,&quot;tonNum&quot;:&quot;255&quot;}, {&quot;warehouseStockId&quot;:&quot;5&quot;,&quot;num&quot;:&quot;33&quot;,&quot;tonNum&quot;:&quot;556&quot;}]</li> <li>warehouseStockId:库存id</li> <li>num:操作件数</li> <li>tonNum:操作吨数</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "fromCustomerId",
            "description": "<p>从某个客户Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "toCustomerId",
            "description": "<p>到某个客户Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "transferType",
            "description": "<p>过户方式</p> <ul> <li>DIRECT_TRANSFER(80010, &quot;直接过户&quot;),//（直接调整库存）</li> <li>NEED_WAREHOUSE_CONFIRM(80020, &quot;需要仓库确认&quot;);//（库仓库确认为调整）</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/transfer/18",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/TransferController.java",
    "groupTitle": "transfer",
    "name": "PutTransferId"
  },
  {
    "type": "GET",
    "url": "/transferSettlement",
    "title": "过户结算单列表",
    "group": "transferSettlement",
    "version": "0.0.1",
    "description": "<p>过户结算单列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "orderNo",
            "description": "<p>结算单单号(支持模糊查询)</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "allowedValues": [
              "true",
              "false"
            ],
            "optional": true,
            "field": "isPay",
            "description": "<p>是否生成过支付单</p> <ul> <li>true：已生成</li> <li>false:未生成</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "id,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=id,desc 表示在按id由高到低排列</li> <li>格式： sort=id,asc 表示在按id由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/transferSettlement?contract_num=1111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>结算单号</p>"
          },
          {
            "group": "200",
            "type": "Boolean",
            "allowedValues": [
              "true",
              "false"
            ],
            "optional": false,
            "field": "isPay",
            "description": "<p>是否生成过支付单</p> <ul> <li>true：已生成</li> <li>false:未生成</li> </ul>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "amount",
            "description": "<p>结算费用</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/TransferSettlementController.java",
    "groupTitle": "transferSettlement",
    "name": "GetTransfersettlement"
  },
  {
    "type": "GET",
    "url": "/transferSettlement/{id}",
    "title": "单个过户结算单",
    "group": "transferSettlement",
    "version": "0.0.1",
    "description": "<p>单个过户结算单</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/transferSettlement/111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>结算单号</p>"
          },
          {
            "group": "200",
            "type": "Boolean",
            "allowedValues": [
              "true",
              "false"
            ],
            "optional": false,
            "field": "isPay",
            "description": "<p>是否生成过支付单</p> <ul> <li>true：已生成</li> <li>false:未生成</li> </ul>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "amount",
            "description": "<p>结算费用</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/TransferSettlementController.java",
    "groupTitle": "transferSettlement",
    "name": "GetTransfersettlementId"
  },
  {
    "type": "GET",
    "url": "/transferSettlementPayment",
    "title": "过户结算支付单列表",
    "group": "transferSettlementPayment",
    "version": "0.0.1",
    "description": "<p>过户结算支付单列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "orderNo",
            "description": "<p>支付单号(支持模糊查询)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "transferSettlementId",
            "description": "<p>过户结算单Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "id,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=id,desc 表示在按id由高到低排列</li> <li>格式： sort=id,asc 表示在按id由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/transferSettlementPayment?contract_num=1111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>结算单号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "bankVoucherNo",
            "description": "<p>支付银行凭证号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "bankName",
            "description": "<p>支付银行</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "draweeTime",
            "description": "<p>支付时间</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "receiptSettlementData",
            "description": "<p>过户结算单对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "receiptSettlementData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "receiptSettlementData.orderNo",
            "description": "<p>结算单号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "receiptSettlementData.amount",
            "description": "<p>结算费用</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/TransferSettlementPaymentController.java",
    "groupTitle": "transferSettlementPayment",
    "name": "GetTransfersettlementpayment"
  },
  {
    "type": "GET",
    "url": "/transferSettlementPayment/{id}",
    "title": "单个过户结算支付单",
    "group": "transferSettlementPayment",
    "version": "0.0.1",
    "description": "<p>单个过户结算支付单</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/transferSettlementPayment/111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>结算单号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "bankVoucherNo",
            "description": "<p>支付银行凭证号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "bankName",
            "description": "<p>支付银行</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "draweeTime",
            "description": "<p>支付时间</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "receiptSettlementData",
            "description": "<p>过户结算单对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "receiptSettlementData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "receiptSettlementData.orderNo",
            "description": "<p>结算单号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "receiptSettlementData.amount",
            "description": "<p>结算费用</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/TransferSettlementPaymentController.java",
    "groupTitle": "transferSettlementPayment",
    "name": "GetTransfersettlementpaymentId"
  },
  {
    "type": "POST",
    "url": "/transferSettlementPayment",
    "title": "过户结算支付单添加",
    "group": "transferSettlementPayment",
    "version": "0.0.1",
    "description": "<p>过户结算支付单添加</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "transferSettlementId",
            "description": "<p>过户结算单id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "bankVoucherNo",
            "description": "<p>支付银行凭证号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "bankName",
            "description": "<p>支付银行</p>"
          },
          {
            "group": "Parameter",
            "type": "DateTime",
            "optional": false,
            "field": "draweeTime",
            "description": "<p>支付时间</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/transferSettlementPayment",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/TransferSettlementPaymentController.java",
    "groupTitle": "transferSettlementPayment",
    "name": "PostTransfersettlementpayment"
  },
  {
    "type": "PUT",
    "url": "/transferSettlementPayment/{id}",
    "title": "过户结算支付单修改",
    "group": "transferSettlementPayment",
    "version": "0.0.1",
    "description": "<p>过户结算支付单修改</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "transferSettlementId",
            "description": "<p>过户结算单id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "bankVoucherNo",
            "description": "<p>支付银行凭证号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "bankName",
            "description": "<p>支付银行</p>"
          },
          {
            "group": "Parameter",
            "type": "DateTime",
            "optional": false,
            "field": "draweeTime",
            "description": "<p>支付时间</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/transferSettlementPayment/18",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/TransferSettlementPaymentController.java",
    "groupTitle": "transferSettlementPayment",
    "name": "PutTransfersettlementpaymentId"
  },
  {
    "type": "POST",
    "url": "/transferSettlement",
    "title": "过户结算单添加",
    "group": "transferSettlement",
    "version": "0.0.1",
    "description": "<p>过户结算单添加</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "transferIds",
            "description": "<p>过户单id列表</p> <ul> <li>格式：&quot;1,2,3,4&quot;</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "BigDecimal",
            "optional": false,
            "field": "amount",
            "description": "<p>结算费用</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>创建人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>创建人Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/transferSettlement",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/TransferSettlementController.java",
    "groupTitle": "transferSettlement",
    "name": "PostTransfersettlement"
  },
  {
    "type": "PUT",
    "url": "/transferSettlement/{id}",
    "title": "过户结算单修改",
    "group": "transferSettlement",
    "version": "0.0.1",
    "description": "<p>过户结算单修改</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "transferIds",
            "description": "<p>过户单id列表</p> <ul> <li>格式：&quot;1,2,3,4&quot;</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "BigDecimal",
            "optional": false,
            "field": "amount",
            "description": "<p>结算费用</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/transferSettlement/18",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/TransferSettlementController.java",
    "groupTitle": "transferSettlement",
    "name": "PutTransfersettlementId"
  },
  {
    "type": "GET",
    "url": "/warehouse",
    "title": "仓库列表",
    "group": "warehouse",
    "version": "0.0.1",
    "description": "<p>仓库列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "name",
            "description": "<p>仓库名称(支持模糊查询)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "parentId",
            "description": "<p>仓库父级Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "allowedValues": [
              "true"
            ],
            "optional": true,
            "field": "parentIsNull",
            "description": "<p>一级菜单是否为null</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "id,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=id,desc 表示在按id由高到低排列</li> <li>格式： sort=id,asc 表示在按id由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/warehouse",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "province",
            "description": "<p>省</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "city",
            "description": "<p>市</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "area",
            "description": "<p>区</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "regionCode",
            "description": "<p>地区编号</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "numMaxCapacity",
            "description": "<p>件数最大容量(目前只对库位这一级)</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "tonNumMaxCapacity",
            "description": "<p>吨数最大容量(目前只对库位这一级)</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "levelOrder",
            "description": "<p>层级顺序</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "parentData",
            "description": "<p>父级对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "parentData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "parentData.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "List",
            "optional": false,
            "field": "child",
            "description": "<p>子组织</p>"
          },
          {
            "group": "200",
            "type": "Boolean",
            "optional": false,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/WarehouseController.java",
    "groupTitle": "warehouse",
    "name": "GetWarehouse"
  },
  {
    "type": "GET",
    "url": "/warehouse/{id}",
    "title": "仓库详细",
    "group": "warehouse",
    "version": "0.0.1",
    "description": "<p>仓库详细</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/warehouse/1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "province",
            "description": "<p>省</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "city",
            "description": "<p>市</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "area",
            "description": "<p>区</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "regionCode",
            "description": "<p>地区编号</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "numMaxCapacity",
            "description": "<p>件数最大容量(目前只对库位这一级)</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "tonNumMaxCapacity",
            "description": "<p>吨数最大容量(目前只对库位这一级)</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "levelOrder",
            "description": "<p>层级顺序</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "parentData",
            "description": "<p>父级对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "parentData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "parentData.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "List",
            "optional": false,
            "field": "child",
            "description": "<p>子组织</p>"
          },
          {
            "group": "200",
            "type": "Boolean",
            "optional": false,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/WarehouseController.java",
    "groupTitle": "warehouse",
    "name": "GetWarehouseId"
  },
  {
    "type": "GET",
    "url": "/warehouseLockLog",
    "title": "仓库锁定日志(针对库位)列表",
    "group": "warehouseLockLog",
    "version": "0.0.1",
    "description": "<p>仓库锁定日志(针对库位)列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseId",
            "description": "<p>库位Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "opType",
            "description": "<p>操作类型</p> <ul> <li>true:锁定</li> <li>false:解锁</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "createTime,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=createTime,desc 表示在按createTime由高到低排列</li> <li>格式： sort=createTime,asc 表示在按createTime由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/warehouseLockLog",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "200",
            "type": "Boolean",
            "optional": false,
            "field": "opType",
            "description": "<p>操作类型</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "opTypeTxt",
            "description": "<p>操作类型Txt</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseData",
            "description": "<p>库位对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseData.name",
            "description": "<p>名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/WarehouseLockLogController.java",
    "groupTitle": "warehouseLockLog",
    "name": "GetWarehouselocklog"
  },
  {
    "type": "POST",
    "url": "/warehouse",
    "title": "仓库添加",
    "group": "warehouse",
    "version": "0.0.1",
    "description": "<p>仓库添加</p>",
    "parameter": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": true,
            "field": "parentId",
            "description": "<p>仓库父Id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": true,
            "field": "province",
            "description": "<p>省(添加一级)</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": true,
            "field": "city",
            "description": "<p>市(添加一级)</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": true,
            "field": "area",
            "description": "<p>区(添加一级)</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": true,
            "field": "regionCode",
            "description": "<p>地区编号(添加一级)</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": true,
            "field": "numMaxCapacity",
            "description": "<p>件数最大容量(目前只对库位这一级)</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": true,
            "field": "tonNumMaxCapacity",
            "description": "<p>吨数最大容量(目前只对库位这一级)</p>"
          },
          {
            "group": "200",
            "type": "Boolean",
            "optional": true,
            "field": "isUse",
            "defaultValue": "true",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/warehouse",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/WarehouseController.java",
    "groupTitle": "warehouse",
    "name": "PostWarehouse"
  },
  {
    "type": "POST",
    "url": "/warehouse/lock",
    "title": "库位锁定/解锁",
    "group": "warehouse",
    "version": "0.0.1",
    "description": "<p>库位锁定/解锁</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": false,
            "field": "opType",
            "description": "<p>操作类型</p> <ul> <li>true:锁定</li> <li>false:解锁</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/warehouse/lock",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/WarehouseController.java",
    "groupTitle": "warehouse",
    "name": "PostWarehouseLock"
  },
  {
    "type": "PUT",
    "url": "/warehouse",
    "title": "仓库修改",
    "group": "warehouse",
    "version": "0.0.1",
    "description": "<p>仓库修改</p>",
    "parameter": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>仓库Id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": true,
            "field": "parentId",
            "description": "<p>仓库父Id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": true,
            "field": "name",
            "description": "<p>仓库名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": true,
            "field": "province",
            "description": "<p>省</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": true,
            "field": "city",
            "description": "<p>市</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": true,
            "field": "area",
            "description": "<p>区</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": true,
            "field": "regionCode",
            "description": "<p>地区编号</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": true,
            "field": "numMaxCapacity",
            "description": "<p>件数最大容量(目前只对库位这一级)</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": true,
            "field": "tonNumMaxCapacity",
            "description": "<p>吨数最大容量(目前只对库位这一级)</p>"
          },
          {
            "group": "200",
            "type": "Boolean",
            "optional": true,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/warehouse",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/WarehouseController.java",
    "groupTitle": "warehouse",
    "name": "PutWarehouse"
  },
  {
    "type": "GET",
    "url": "/warehouseStock",
    "title": "仓库库存列表",
    "group": "warehouseStock",
    "version": "0.0.1",
    "description": "<p>仓库库存列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "customerId",
            "description": "<p>客户Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseSiteId",
            "description": "<p>仓库Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseAreaId",
            "description": "<p>库区Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseId",
            "description": "<p>库位Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseLevelId",
            "description": "<p>库层Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "productId",
            "description": "<p>物资Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseIsUse",
            "description": "<p>仓库是否停用(配合仓库4个参数使用)</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "createTime,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=createTime,desc 表示在按createTime由高到低排列</li> <li>格式： sort=createTime,asc 表示在按createTime由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/warehouseStock",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "num",
            "description": "<p>库存件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "tonNum",
            "description": "<p>库存吨数</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>操作人ID</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseSiteData",
            "description": "<p>仓库对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseSiteData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseSiteData.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseAreaData",
            "description": "<p>库区对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseAreaData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseAreaData.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseData",
            "description": "<p>库位对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseData.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseLevelData",
            "description": "<p>库层对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseLevelData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseLevelData.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "customerData",
            "description": "<p>库存客户对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customerData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customerData.name",
            "description": "<p>企业名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "productData",
            "description": "<p>库存物资对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.productName",
            "description": "<p>物资名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.unit",
            "description": "<p>物资单位</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.rationale",
            "description": "<p>物资理重</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.spec",
            "description": "<p>物资规格</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.model",
            "description": "<p>物资型号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.material",
            "description": "<p>物资材料</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.maker",
            "description": "<p>物资厂家</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.productCateName",
            "description": "<p>分类名</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/WarehouseStockController.java",
    "groupTitle": "warehouseStock",
    "name": "GetWarehousestock"
  },
  {
    "type": "GET",
    "url": "/warehouseStock/{id}",
    "title": "仓库库存详细",
    "group": "warehouseStock",
    "version": "0.0.1",
    "description": "<p>仓库库存详细</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/warehouseStock/1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "num",
            "description": "<p>库存件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "tonNum",
            "description": "<p>库存吨数</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>操作人ID</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseSiteData",
            "description": "<p>仓库对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseSiteData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseSiteData.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseAreaData",
            "description": "<p>库区对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseAreaData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseAreaData.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseData",
            "description": "<p>库位对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseData.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseLevelData",
            "description": "<p>库层对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseLevelData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseLevelData.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "customerData",
            "description": "<p>库存客户对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customerData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customerData.name",
            "description": "<p>企业名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "productData",
            "description": "<p>库存物资对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productData.productName",
            "description": "<p>物资名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/WarehouseStockController.java",
    "groupTitle": "warehouseStock",
    "name": "GetWarehousestockId"
  },
  {
    "type": "GET",
    "url": "/warehouseStock/productOccupy",
    "title": "查询物资被委托单占用情况列表",
    "group": "warehouseStock",
    "version": "0.0.1",
    "description": "<p>查询物资被委托单占用情况列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "orderStateStrList",
            "defaultValue": "50010,50020",
            "description": "<p>委托单状态列表(多种状态逗号分隔)</p> <ul> <li>格式：&quot;50010,50020&quot;</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "productId",
            "description": "<p>物资Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseId",
            "description": "<p>仓库id(第一级)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "productName",
            "description": "<p>物资名称(模糊查询)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "customerId",
            "description": "<p>客户id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/warehouseStock/productOccupy",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productId",
            "description": "<p>物资id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productName",
            "description": "<p>物资名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseId",
            "description": "<p>仓库id(第一级)</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseName",
            "description": "<p>仓库名称(第一级)</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "cateId",
            "description": "<p>物资分类id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productCateName",
            "description": "<p>物资分类名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customerId",
            "description": "<p>客户id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "customerName",
            "description": "<p>客户名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "spec",
            "description": "<p>规格</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "model",
            "description": "<p>型号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "material",
            "description": "<p>材料</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "maker",
            "description": "<p>厂家</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "unit",
            "description": "<p>单位</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "rationale",
            "description": "<p>物资理重</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "sumNum",
            "description": "<p>占用件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "sumTonNum",
            "description": "<p>占用吨数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "stockNumTotal",
            "description": "<p>库存件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "stockTonNumTotal",
            "description": "<p>库存吨数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "lockNumTotal",
            "description": "<p>锁定件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "lockTonNumTotal",
            "description": "<p>锁定吨数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "canUseNum",
            "description": "<p>可用件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "canUseTonNum",
            "description": "<p>可用吨数</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/WarehouseStockController.java",
    "groupTitle": "warehouseStock",
    "name": "GetWarehousestockProductoccupy"
  },
  {
    "type": "GET",
    "url": "/warehouseStock/productOccupyTotal",
    "title": "占用库存列表页面的统计部分",
    "group": "warehouseStock",
    "version": "0.0.1",
    "description": "<p>占用库存列表页面的统计部分</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "orderStateStrList",
            "defaultValue": "50010,50020",
            "description": "<p>委托单状态列表(多种状态逗号分隔)</p> <ul> <li>格式：&quot;50010,50020&quot;</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "productId",
            "description": "<p>物资Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseId",
            "description": "<p>仓库id(第一级)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "productName",
            "description": "<p>物资名称(模糊查询)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "customerId",
            "description": "<p>客户id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/warehouseStock/productOccupyTotal",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "occupyNumTotal",
            "description": "<p>占用件数合计</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "occupyTonTotal",
            "description": "<p>占用吨数合计</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "stockNumTotal",
            "description": "<p>当前库存件数合计</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "stockTonTotal",
            "description": "<p>当前库存吨数合计</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "lockNumTotal",
            "description": "<p>锁定件数合计</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "lockTonTotal",
            "description": "<p>锁定吨数合计</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "canNumTotal",
            "description": "<p>可用件数合计</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "canTonTotal",
            "description": "<p>可用吨数合计</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/WarehouseStockController.java",
    "groupTitle": "warehouseStock",
    "name": "GetWarehousestockProductoccupytotal"
  },
  {
    "type": "GET",
    "url": "/warehouseStock/sumByNoCustomer",
    "title": "排除客户查询库存列表",
    "group": "warehouseStock",
    "version": "0.0.1",
    "description": "<p>排除客户查询库存列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseSiteId",
            "description": "<p>仓库Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseAreaId",
            "description": "<p>库区Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseId",
            "description": "<p>库位Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseLevelId",
            "description": "<p>库层Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "productId",
            "description": "<p>物资Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/warehouseStock/sumByNoCustomer",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseSiteId",
            "description": "<p>仓库id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseSiteName",
            "description": "<p>仓库名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseAreaId",
            "description": "<p>库区id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseAreaName",
            "description": "<p>库区名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseId",
            "description": "<p>库位id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseName",
            "description": "<p>库位名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseLevelId",
            "description": "<p>库层id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseLevelName",
            "description": "<p>库层名称</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "numTotal",
            "description": "<p>件数合计</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "tonTotal",
            "description": "<p>吨数合计</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productId",
            "description": "<p>物资id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productName",
            "description": "<p>物资名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "spec",
            "description": "<p>规格</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "model",
            "description": "<p>型号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "material",
            "description": "<p>材料</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "maker",
            "description": "<p>厂家</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "unit",
            "description": "<p>单位</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "rationale",
            "description": "<p>物资理重</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "cateId",
            "description": "<p>物资分类id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "productCateName",
            "description": "<p>物资分类名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/WarehouseStockController.java",
    "groupTitle": "warehouseStock",
    "name": "GetWarehousestockSumbynocustomer"
  },
  {
    "type": "GET",
    "url": "/warehouseStockLog",
    "title": "仓库库存日志列表",
    "group": "warehouseStockLog",
    "version": "0.0.1",
    "description": "<p>仓库库存日志列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseSotckId",
            "description": "<p>库存Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseSiteId",
            "description": "<p>仓库id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseAreaId",
            "description": "<p>库区id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseId",
            "description": "<p>库位id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "productId",
            "description": "<p>物资id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "opOrderNo",
            "description": "<p>操作单号(支持模糊查询)</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "createTime,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=createTime,desc 表示在按createTime由高到低排列</li> <li>格式： sort=createTime,asc 表示在按createTime由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/warehouseStockLog",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "opOrderNo",
            "description": "<p>操作单号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "opOrderId",
            "description": "<p>操作单号Id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "opTypeTxt",
            "description": "<p>操作单类型名称</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "opType",
            "description": "<p>操作单类型</p> <ul> <li>TAKE_OVER(15010, &quot;收货&quot;),</li> <li>CARRY_GOOD(15020, &quot;提货&quot;),</li> <li>REMOVE_STOCK(15030, &quot;移库&quot;),</li> <li>TRANSFER_NAME(15040, &quot;过户&quot;),</li> <li>ADJUST_NAME(15050, &quot;调整&quot;),</li> <li>INVENTORY_NAME(15060, &quot;盘库&quot;);</li> </ul>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "opNum",
            "description": "<p>操作件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "opTonNum",
            "description": "<p>操作吨数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "blaNum",
            "description": "<p>结余件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "blaTonNum",
            "description": "<p>结余吨数</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseStock",
            "description": "<p>库存实体</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.id",
            "description": "<p>仓库Id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.num",
            "description": "<p>库存件数</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.tonNum",
            "description": "<p>库存吨数</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.createManId",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "warehouseStock.createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseStock.warehouseSiteData",
            "description": "<p>仓库实体</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.warehouseSiteData.id",
            "description": "<p>库区Id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.warehouseSiteData.name",
            "description": "<p>仓库名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseStock.warehouseAreaData",
            "description": "<p>库区实体</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.warehouseAreaData.id",
            "description": "<p>库区Id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.warehouseAreaData.name",
            "description": "<p>库区名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseStock.warehouseData",
            "description": "<p>库位实体</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.warehouseData.id",
            "description": "<p>库位Id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.warehouseData.name",
            "description": "<p>库位名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseStock.productData",
            "description": "<p>物资实体</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.productData.id",
            "description": "<p>物资Id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.productData.productName",
            "description": "<p>物资名称    *</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseStock.customerData",
            "description": "<p>客户实体</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.customerData.id",
            "description": "<p>客户Id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.customerData.name",
            "description": "<p>客户名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/WarehouseStockLogController.java",
    "groupTitle": "warehouseStockLog",
    "name": "GetWarehousestocklog"
  },
  {
    "type": "GET",
    "url": "/warehouseStockLog/{id}",
    "title": "仓库库存日志详细",
    "group": "warehouseStockLog",
    "version": "0.0.1",
    "description": "<p>仓库库存日志详细</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/warehouseStockLog/1",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "opOrderNo",
            "description": "<p>操作单号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "opOrderId",
            "description": "<p>操作单号Id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "opTypeTxt",
            "description": "<p>操作单类型名称</p>"
          },
          {
            "group": "200",
            "type": "Integer",
            "optional": false,
            "field": "opType",
            "description": "<p>操作单类型</p> <ul> <li>TAKE_OVER(15010, &quot;收货&quot;),</li> <li>CARRY_GOOD(15020, &quot;提货&quot;),</li> <li>REMOVE_STOCK(15030, &quot;移库&quot;),</li> <li>TRANSFER_NAME(15040, &quot;过户&quot;),</li> <li>ADJUST_NAME(15050, &quot;调整&quot;),</li> <li>INVENTORY_NAME(15060, &quot;盘库&quot;);</li> </ul>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "opNum",
            "description": "<p>操作件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "opTonNum",
            "description": "<p>操作吨数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "blaNum",
            "description": "<p>结余件数</p>"
          },
          {
            "group": "200",
            "type": "BigDecimal",
            "optional": false,
            "field": "blaTonNum",
            "description": "<p>结余吨数</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "createManId",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseStock",
            "description": "<p>库存实体</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.id",
            "description": "<p>仓库Id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.num",
            "description": "<p>库存件数</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.tonNum",
            "description": "<p>库存吨数</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.createMan",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.createManId",
            "description": "<p>操作人</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "warehouseStock.createTime",
            "description": "<p>创建时间</p> <ul> <li>格式：yyyy-MM-dd HH:mm:ss</li> </ul>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseStock.warehouseSiteData",
            "description": "<p>仓库实体</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.warehouseSiteData.id",
            "description": "<p>库区Id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.warehouseSiteData.name",
            "description": "<p>仓库名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseStock.warehouseAreaData",
            "description": "<p>库区实体</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.warehouseAreaData.id",
            "description": "<p>库区Id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.warehouseAreaData.name",
            "description": "<p>库区名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseStock.warehouseData",
            "description": "<p>库位实体</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.warehouseData.id",
            "description": "<p>库位Id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.warehouseData.name",
            "description": "<p>库位名称</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseStock.productData",
            "description": "<p>物资实体</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.productData.id",
            "description": "<p>物资Id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.productData.productName",
            "description": "<p>物资名称    *</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseStock.customerData",
            "description": "<p>客户实体</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.customerData.id",
            "description": "<p>客户Id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseStock.customerData.name",
            "description": "<p>客户名称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/WarehouseStockLogController.java",
    "groupTitle": "warehouseStockLog",
    "name": "GetWarehousestocklogId"
  },
  {
    "type": "GET",
    "url": "/worker",
    "title": "工作人员列表",
    "group": "worker",
    "version": "0.0.1",
    "description": "<p>工作人员列表</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "name",
            "description": "<p>名称(支持模糊查询)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "job",
            "description": "<p>岗位(支持模糊查询)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "warehouseSiteId",
            "description": "<p>仓库Id(第一级)</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "page",
            "defaultValue": "0",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": true,
            "field": "size",
            "defaultValue": "10",
            "description": "<p>每页数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "sort",
            "defaultValue": "id,asc",
            "description": "<p>排序</p> <ul> <li>格式： sort=id,desc 表示在按id由高到低排列</li> <li>格式： sort=id,asc 表示在按id由低到高排列</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/worker?name=1111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "job",
            "description": "<p>岗位</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseSiteData",
            "description": "<p>仓库对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseSiteData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseSiteData.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Boolean",
            "optional": false,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/WorkerController.java",
    "groupTitle": "worker",
    "name": "GetWorker"
  },
  {
    "type": "GET",
    "url": "/worker/{id}",
    "title": "单个工作人员",
    "group": "worker",
    "version": "0.0.1",
    "description": "<p>单个工作人员</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/worker/111",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>id主键</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "job",
            "description": "<p>岗位</p>"
          },
          {
            "group": "200",
            "type": "DateTime",
            "optional": false,
            "field": "createTime",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "warehouseSiteData",
            "description": "<p>仓库对象</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseSiteData.id",
            "description": "<p>id</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "warehouseSiteData.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "200",
            "type": "Boolean",
            "optional": false,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/WorkerController.java",
    "groupTitle": "worker",
    "name": "GetWorkerId"
  },
  {
    "type": "POST",
    "url": "/worker",
    "title": "工作人员添加",
    "group": "worker",
    "version": "0.0.1",
    "description": "<p>工作人员添加</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "warehouseSiteId",
            "description": "<p>仓库Id(第1级)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "job",
            "description": "<p>岗位</p> <ul> <li>调度员：负责货场调度</li> <li>理货员：负责现场安排卸货库位</li> <li>行车工：负责行车操作，吊装卸货物</li> <li>装卸工：负责配合行车工吊装货物时套钢索之类的工作</li> <li>叉车工：负责装卸货及整理库房时的移库工作及部分装卸车工作</li> <li>计量员：在磅房负责各个行吊的重量记录</li> <li>稽核员：负责进出库数据稽核（等同于仓库的库管）</li> <li>换单/结算员：负责办理出库单据及费用结算收费等</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "isUse",
            "defaultValue": "true",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/worker",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/WorkerController.java",
    "groupTitle": "worker",
    "name": "PostWorker"
  },
  {
    "type": "PUT",
    "url": "/worker/{id}",
    "title": "工作人员修改",
    "group": "worker",
    "version": "0.0.1",
    "description": "<p>工作人员修改</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>主键id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "warehouseSiteId",
            "description": "<p>仓库Id(第1级)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "job",
            "description": "<p>岗位</p> <ul> <li>调度员：负责货场调度</li> <li>理货员：负责现场安排卸货库位</li> <li>行车工：负责行车操作，吊装卸货物</li> <li>装卸工：负责配合行车工吊装货物时套钢索之类的工作</li> <li>叉车工：负责装卸货及整理库房时的移库工作及部分装卸车工作</li> <li>计量员：在磅房负责各个行吊的重量记录</li> <li>稽核员：负责进出库数据稽核（等同于仓库的库管）</li> <li>换单/结算员：负责办理出库单据及费用结算收费等</li> </ul>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "isUse",
            "description": "<p>是否启用</p> <ul> <li>true:启用</li> <li>false:停用</li> </ul>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求明文：",
          "content": "/worker/18",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功:",
          "content": "{\"message\":\"成功\"}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "500": [
          {
            "group": "500",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败:",
          "content": "{\"message\":\"失败\"}",
          "type": "json"
        }
      ]
    },
    "filename": "./src/main/java/com/warehouse/controller/WorkerController.java",
    "groupTitle": "worker",
    "name": "PutWorkerId"
  }
] });
