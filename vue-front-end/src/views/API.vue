<template>
  <div class="api" style="padding: 20px 200px">
    <div style="padding: 10px 0">
      <h1 tabindex="-1" style="color: dimgrey">Initialization</h1>
      <div style="padding: 10px 0">
        <el-button type="primary" @click="init">初始化</el-button>
      </div>
    </div>
    <div style="padding: 20px 0">
      <h1 tabindex="-1" style="color: dimgrey">Q2 - stockIn</h1>
      <div style="padding: 20px 0">
        <el-upload class="el-upload" action="http://localhost:8181/main/stockIn/">
          <el-button type="primary">上传</el-button>
        </el-upload>
      </div>
    </div>
    <div style="padding: 20px 0">
      <h1 tabindex="-1" style="color: dimgrey">Q3 - placeOrder</h1>
      <div style="padding: 20px 0">
        <el-upload class="el-upload" action="http://localhost:8181/main/placeOrder/">
          <el-button type="primary">上传</el-button>
        </el-upload>
      </div>
    </div>
    <div style="padding: 20px 0">
      <h1 tabindex="-1" style="color: dimgrey">Q4 - updateOrder</h1>
      <div style="padding: 20px 0">
        <el-upload class="el-upload" action="http://localhost:8181/main/updateOrder/">
          <el-button type="primary">上传</el-button>
        </el-upload>
      </div>
    </div>
    <div style="padding: 20px 0">
      <h1 tabindex="-1" style="color: dimgrey">Q5 - deleteOrder</h1>
      <div style="padding: 20px 0">
        <el-upload class="el-upload" action="http://localhost:8181/main/deleteOrder/">
          <el-button type="primary">上传</el-button>
        </el-upload>
      </div>
    </div>
    <div style="padding: 20px 0">
      <h1 tabindex="-1" style="color: dimgrey">Q6 - getAllStaffCount</h1>
      <el-table :data="tableData_staff" size="large" stripe border style="width: 362px">
        <el-table-column prop="type" label="Type" width="180"/>
        <el-table-column prop="count" label="Count" width="180"/>
      </el-table>
    </div>
    <div style="padding: 20px 0">
      <h1 tabindex="-1" style="color: dimgrey">Q7</h1>
      <el-table :data="tableData_contract" size="large" stripe border style="width: 182px">
        <el-table-column prop="count" label="Contract Count" width="180"/>
      </el-table>
    </div>
    <div style="padding: 20px 0">
      <h1 tabindex="-1" style="color: dimgrey">Q8</h1>
      <el-table :data="tableData_order" size="large" stripe border style="width: 182px">
        <el-table-column prop="count" label="Order Count" width="180"/>
      </el-table>
    </div>
    <div style="padding: 20px 0">
      <h1 tabindex="-1" style="color: dimgrey">Q9</h1>
      <el-table :data="tableData_NeverSoldProductCount" size="large" stripe border style="width: 212px">
        <el-table-column prop="count" label="Never Sold Product Count" width="210"/>
      </el-table>
    </div>
    <div style="padding: 20px 0">
      <h1 tabindex="-1" style="color: dimgrey">Q10</h1>
      <el-table :data="tableData_FavoriteProductModel" size="large" stripe border style="width: 392px">
        <el-table-column prop="modelName" label="Model Name" width="210"/>
        <el-table-column prop="sales" label="Sales" width="180"/>
      </el-table>
    </div>
    <div style="padding: 20px 0">
      <h1 tabindex="-1" style="color: dimgrey">Q11</h1>
      <el-table :data="tableData_getAvgStockByCenter" size="large" stripe border style="width: 392px">
        <el-table-column prop="centerName" label="centerName" width="210"/>
        <el-table-column prop="avg" label="avg" width="180"/>
      </el-table>
    </div>
    <div style="padding: 20px 0">
      <h1 tabindex="-1" style="color: dimgrey">Q12 - getProductByNumber</h1>
      <div style="margin: 10px 0">
        <el-input v-model="number" placeholder="请输model number" style="width: 20%" clearable></el-input>
        <el-button type="primary" style="margin: 5px" @click="loadGetProductByNumber">查询</el-button>
      </div>
      <el-table :data="tableData_getProductByNumber" size="large" stripe border style="width: 551px">
        <el-table-column prop="centerName" label="供应中心" width="200px"/>
        <el-table-column prop="modelName" label="model名称" width="200px"/>
        <el-table-column prop="quantity" label="数量" width="150px"/>
      </el-table>
    </div>
    <div style="padding: 20px 0">
      <h1 tabindex="-1" style="color: dimgrey">Q13 - getContractInfo</h1>
      <div style="margin: 10px 0">
        <el-input v-model="contract_number" placeholder="请输contract number" style="width: 20%" clearable></el-input>
        <el-button type="primary" style="margin: 5px" @click="loadGetContractInfo">查询</el-button>
      </div>
      <el-table :data="tableData_getContractInfo" size="large" stripe border style="width: 751px">
        <el-table-column prop="contract_number" label="合同编号" width="200px"/>
        <el-table-column prop="staffName" label="经理" width="200px"/>
        <el-table-column prop="enterpriseName" label="企业" width="150px"/>
        <el-table-column prop="centerName" label="供应中心" width="150px"/>
      </el-table>
      <el-table :data="tableData_getOrderInfo" size="large" stripe border style="width: 1001px">
        <el-table-column prop="modelName" label="产品型号" width="200px"/>
        <el-table-column prop="salesmanName" label="销售员" width="200px"/>
        <el-table-column prop="quantity" label="数量" width="150px"/>
        <el-table-column prop="unitPrice" label="单价" width="150px"/>
        <el-table-column prop="estimated_delivery_date" label="预定日期" width="150px"/>
        <el-table-column prop="lodgement_date" label="实际日期" width="150px"/>
      </el-table>
    </div>
  </div>
</template>

<script>
import request from "@/util/request";

export default {
  name: "GetAllStaffCount",
  data() {
    return {
      type: '',
      number: '',
      contract_number: '',
      pageNum: 1,
      form: {},
      pageSize: 10,
      total: 4,
      tableData_staff: [],
      tableData_contract: [],
      tableData_order: [],
      tableData_NeverSoldProductCount: [],
      tableData_FavoriteProductModel: [],
      tableData_getAvgStockByCenter: [],
      tableData_getProductByNumber: [],
      tableData_getContractInfo: [],
      tableData_getOrderInfo: [],
      dialogVisible: false,
      editMode: false
    }
  },
  created() {
    this.load_staff();
    this.load_contract();
    this.load_order();
    this.load_NeverSoldProductCount();
    this.load_FavoriteProductModel();
    this.load_getAvgStockByCenter()
    this.loadGetProductByNumber();
    this.loadGetContractInfo();
  },
  methods: {
    init() {
      request.get("/main/init").then(response => {
        console.log(response)
        if (response) {
          this.$message({
            type: 'success',
            message: '初始化成功'
          })
        } else {
          this.$message({
            type: 'error',
            message: '初始化失败'
          })
        }
      })
    },
    load_staff() {
      request.get("/main/getAllStaffCount", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          type: this.type
        }
      }).then(response => {
        ({records: this.tableData_staff, total: this.total} = response.data);
        console.log(response)
      })
    },
    load_contract() {
      request.get("/main/getContractCount", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          type: this.type
        }
      }).then(response => {
        ({records: this.tableData_contract, total: this.total} = response.data);
        console.log(response)
      })
    },
    load_order() {
      request.get("/main/getOrderCount", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          type: this.type
        }
      }).then(response => {
        ({records: this.tableData_order, total: this.total} = response.data);
        console.log(response)
      })
    },
    load_NeverSoldProductCount() {
      request.get("/main/getNeverSoldProductCount", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          type: this.type
        }
      }).then(response => {
        ({records: this.tableData_NeverSoldProductCount, total: this.total} = response.data);
        console.log(response)
      })
    },
    load_FavoriteProductModel() {
      request.get("/main/getFavoriteProductModel", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          type: this.type
        }
      }).then(response => {
        ({records: this.tableData_FavoriteProductModel, total: this.total} = response.data);
        console.log(response)
      })
    },
    load_getAvgStockByCenter() {
      request.get("/main/getAvgStockByCenter", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          type: this.type
        }
      }).then(response => {
        ({records: this.tableData_getAvgStockByCenter, total: this.total} = response.data);
        console.log(response)
      })
    },
    loadGetProductByNumber() {
      request.get("/main/getProductByNumber", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          number: this.number
        }
      }).then(response => {
        console.log(response)
        this.tableData_getProductByNumber = response.data.records
      })
    },
    loadGetContractInfo() {
      request.get("/main/getContractInfo", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          contract_number: this.contract_number
        }
      }).then(response => {
        console.log(response)
        this.tableData_getContractInfo = response.data.records
      })
      request.get("/main/getOrderInfo", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          contract_number: this.contract_number
        }
      }).then(response => {
        console.log(response)
        this.tableData_getOrderInfo = response.data.records
      })
    }
  },
}
</script>

