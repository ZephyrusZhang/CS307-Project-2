<template>
  <div class="budget" style="padding: 20px 100px">
    <div style="margin: 10px 0">
      <el-input v-model="contract_date" placeholder="请输入日期" style="width: 15%" clearable></el-input>
      <el-button type="primary" style="margin: 5px" @click="update">更新</el-button>
    </div>
  <el-table :data="tableData" stripe border style="width: 801px">
    <el-table-column prop="contract_number" sortable label="contract_number" width="200"/>
    <el-table-column prop="contract_date" label="contract_date" width="200"/>
    <el-table-column prop="contract_manager" label="contract_manager" width="200"/>
    <el-table-column prop="contract_type" label="contract_type" width="200"/>
  </el-table>
  </div>
</template>

<script>
import request from "@/util/request";

export default {
  name: "Budget",
  data() {
    return {
      name: '',
      contract_date: '',
      pageNum: 1,
      form: {},
      pageSize: 100,
      total: 0,
      tableData: [],
      dialogVisible: false,
      editMode: false
    }
  },
  created() {
    this.load();
    this.update();
  },
  methods: {
    load() {
      request.get("/main/getContract", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name
        }
      }).then(response => {
        ({records: this.tableData, total: this.total} = response.data);
        console.log(response)
      })
    },
    update(){
      request.get("/main/updateDate", {
        params: {
          contract_date: this.contract_date
        }
      }).then(response => {
        console.log(response)
      })
    }
  }
}
</script>

<style scoped>

</style>