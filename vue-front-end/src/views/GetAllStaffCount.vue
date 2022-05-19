<template>
  <div class="getAllStaffCount" style="padding: 20px 350px">
    <section><h1>Q6</h1></section>
  <el-table :data="tableData_staff" stripe border style="width: 370px">
    <el-table-column prop="type" label="Type" width="180" />
    <el-table-column prop="count" label="Count" width="180" />
  </el-table>
    <section><h1>Q7</h1></section>
    <el-table :data="tableData_contract" stripe border style="width: 190px">
      <el-table-column prop="count" label="contractCount" width="180" />
    </el-table>
    <section><h1>Q8</h1></section>
    <el-table :data="tableData_order" stripe border style="width: 190px">
      <el-table-column prop="count" label="orderCount" width="180" />
    </el-table>
    <section><h1>Q9</h1></section>
    <el-table :data="tableData_NeverSoldProductCount" stripe border style="width: 190px">
      <el-table-column prop="count" label="NeverSoldProductCount" width="180" />
    </el-table>
    <section><h1>Q10</h1></section>
    <el-table :data="tableData_FavoriteProductModel" stripe border style="width: 390px">
      <el-table-column prop="modelName" label="modelName" width="180" />
      <el-table-column prop="sales" label="sales" width="180" />
    </el-table>
    <section><h1>Q11</h1></section>
    <el-table :data="tableData_getAvgStockByCenter" stripe border style="width: 390px">
      <el-table-column prop="centerName" label="centerName" width="180" />
      <el-table-column prop="avg" label="avg" width="180" />
    </el-table>
    <section><h1>Q12</h1></section>
    <el-table :data="tableData_getAvgStockByCenter" stripe border style="width: 390px">
    </el-table>
  </div>
</template>

<script>
import request from "@/util/request";

export default {
  name: "GetAllStaffCount",
  data() {
    return {
      type: '',
      pageNum: 1,
      form: {},
      pageSize: 10,
      total: 4,
      tableData_staff: [],
      tableDate_contract: [],
      tableData_order:[],
      tableData_NeverSoldProductCount:[],
      tableData_FavoriteProductModel:[],
      tableData_getAvgStockByCenter:[],
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
  },
  methods: {
    load_staff() {
      request.get("/staff/getAllStaffCount", {
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
      request.get("/contract/getContractCount", {
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
      request.get("/orders/getOrderCount", {
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
      request.get("/model/getNeverSoldProductCount", {
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
      request.get("/model/getFavoriteProductModel", {
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
      request.get("/inventory/getAvgStockByCenter", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          type: this.type
        }
      }).then(response => {
        ({records: this.tableData_getAvgStockByCenter, total: this.total} = response.data);
        console.log(response)
      })
    }
}
}
</script>

