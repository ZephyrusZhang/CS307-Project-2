<template>

  <div class="order" style="padding: 20px 100px">
    <div style="margin: 10px 0">
      <el-input v-model="contract_number" placeholder="请输入合同编号" style="width: 15%" clearable></el-input>
      <el-input v-model="enterpriseName" placeholder="请输入企业" style="width: 15%" clearable></el-input>
      <el-input v-model="centerName" placeholder="供应中心" style="width: 15%" clearable></el-input>
      <el-input v-model="modelName" placeholder="请输入产品型号" style="width: 15%" clearable></el-input>
      <el-button type="primary" style="margin: 5px" @click="load">查询</el-button>
    </div>
    <el-table :data="tableData" stripe border style="width: 1000px">
      <el-table-column prop="contract_number" label="合同编号" width="200px"/>
      <el-table-column prop="staffName" label="经理" width="200px"/>
      <el-table-column prop="enterpriseName" label="企业" width="150px"/>
      <el-table-column prop="centerName" label="供应中心" width="150px"/>
      <el-table-column prop="modelName" label="产品型号" width="200px"/>
      <el-table-column prop="salesmanName" label="销售员" width="200px"/>
      <el-table-column prop="quantity" label="数量" width="150px"/>
      <el-table-column prop="unitPrice" label="单价" width="150px"/>
      <el-table-column prop="estimated_delivery_date" label="预定日期" width="150px"/>
      <el-table-column prop="lodgement_date" label="实际日期" width="150px"/>
    </el-table>

    <div style="margin: 20px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[5, 10, 15, 20, 50]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>

    </div>
  </div>
</template>

<script>
import request from "@/util/request";

export default {
  name: "Order",
  data(){
    return {
      number: '',
      pageNum: 1,
      form: {},
      pageSize: 100,
      total: 0,
      tableData: [],
      contract_number: '',
      enterpriseName: '',
      centerName: '',
      modelName: '',
      dialogVisible: false,
      editMode: false
    }
  },
  created() {
    this.load();
  },
  methods:{
    load(){
      request.get("/main/getOrder", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          contract_number: this.contract_number+'%',
          enterpriseName: this.enterpriseName+'%',
          centerName: this.centerName+'%',
          modelName: this.modelName+'%',
        }
      }).then(response => {
        console.log(response)
        this.tableData= response.data.records
      })
    },
    handleSizeChange(pageSize) {
      console.log('handleSizeChange')
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      console.log('handleCurrentChange')
      this.pageNum = pageNum
      this.load()
    }
  }
}
</script>

<style scoped>

</style>