<!--<template>-->
<!--  <div class="enterprise" style="padding: 20px 180px">-->
<!--    <div style="margin: 10px 0">-->
<!--      <el-button type="primary" @click="insert">新增</el-button>-->
<!--    </div>-->
<!--    <div style="margin: 10px 0">-->
<!--      <el-input v-model="name" placeholder="请输入公司名称" style="width: 20%" clearable></el-input>-->
<!--      <el-button type="primary" style="margin: 5px" @click="load">查询</el-button>-->
<!--    </div>-->
<!--    <el-table data="tableData" stripe border style="width: 100%">-->
<!--      <el-table-column prop="enterpriseName" sortable label="enterpriseName" width="100"/>-->
<!--      <el-table-column prop="country" label="country" width="80"/>-->
<!--      <el-table-column prop="city" label="city" width="100"/>-->
<!--      <el-table-column prop="centerName" label="centerName" width="180"/>-->
<!--      <el-table-column fixed="right" label="操作" width="150">-->
<!--        <template #default="scope">-->
<!--          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>-->
<!--          <el-popconfirm title="确认删除？" @confirm="handleDelete(scope.row.number)">-->
<!--            <template #reference>-->
<!--              <el-button type="danger" size="mini">删除</el-button>-->
<!--            </template>-->
<!--          </el-popconfirm>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--    </el-table>-->
<!--    <div style="margin: 20px 0">-->
<!--      <el-pagination-->
<!--          @size-change="handleSizeChange"-->
<!--          @current-change="handleCurrentChange"-->
<!--          :current-page="pageNum"-->
<!--          :page-sizes="[5, 10, 15, 20]"-->
<!--          :page-size="pageSize"-->
<!--          layout="total, sizes, prev, pager, next, jumper"-->
<!--          :total="total">-->
<!--      </el-pagination>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import request from "@/util/request";-->

<!--export default {-->
<!--  name: "Enterprise",-->
<!--  data() {-->
<!--    return {-->
<!--      name: '',-->
<!--      pageNum: 1,-->
<!--      form: {},-->
<!--      pageSize: 10,-->
<!--      total: 0,-->
<!--      tableData: [],-->
<!--      dialogVisible: false-->
<!--    }-->
<!--  },-->
<!--  created() {-->
<!--    this.load()-->
<!--  },-->
<!--  methods: {-->
<!--    insert() {-->

<!--    },-->
<!--    load() {-->
<!--      request.get("/enterprise/selectPage", {-->
<!--        params: {-->
<!--          pageNum: this.pageNum,-->
<!--          pageSize: this.pageSize,-->
<!--          name: this.name-->
<!--        }-->
<!--      }).then(response => {-->
<!--        this.tableData = response.data.records-->
<!--        this.total = response.data.total-->
<!--        console.log(response)-->
<!--      })-->
<!--    },-->
<!--    handleEdit() {-->

<!--    },-->
<!--    handleDelete() {-->

<!--    },-->
<!--    handleSizeChange(pageSize) {-->
<!--      console.log('handleSizeChange')-->
<!--      this.pageSize = pageSize-->
<!--      this.load()-->
<!--    },-->
<!--    handleCurrentChange(pageNum) {-->
<!--      console.log('handleCurrentChange')-->
<!--      this.pageNum = pageNum-->
<!--      this.load()-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->

<!--<style scoped>-->

<!--</style>-->
<template>
  <div class="staff" style="padding: 20px 350px">
    <div style="margin: 10px 0">
      <el-button type="primary" @click="insert">新增</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-input v-model="enterpriseName" placeholder="请输入公司名称" style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin: 5px" @click="load">查询</el-button>
    </div>
    <el-table :data="tableData" stripe border style="width: 700px">
      <el-table-column prop="enterpriseName" sortable label="enterpriseName" width="180"/>
      <el-table-column prop="country" label="country" width="120"/>
      <el-table-column prop="city" label="city" width="100"/>
      <el-table-column prop="centerName" label="centerName" width="120"/>
      <el-table-column fixed="right" label="操作" width="150">
        <template #default="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="确认删除？" @confirm="handleDelete(scope.row.enterpriseName)">
            <template #reference>
              <el-button type="danger" size="mini">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin: 20px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
      <el-dialog v-model="dialogVisible" title="请输入公司信息" width="30%" :before-close="handleClose">
        <el-form model="form" label-width="120px">
          <el-form-item label="公司名称">
            <el-input v-model="form.name" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="公司所在国家">
            <el-input v-model="form.country" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="公司所在城市">
            <el-input v-model="form.city" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="该公司的供应中心的id">
            <el-input v-model="form.supplyCenterId" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="公司的工厂">
            <el-input v-model="form.industry" style="width: 80%"></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="save">确定</el-button>
          </span>
        </template>
      </el-dialog>
      <!--      <el-dialog v-model="dialogVisible" title="Tips" width="30%" :before-close="handleClose">-->
      <!--        <el-form model="form" label-width="120px">-->
      <!--          <el-form-item label="名字">-->
      <!--            <el-input v-model="form.name" style="width: 80%"></el-input>-->
      <!--          </el-form-item>-->
      <!--          <el-form-item label="年龄">-->
      <!--            <el-input v-model="form.age" style="width: 80%"></el-input>-->
      <!--          </el-form-item>-->
      <!--          <el-form-item label="性别">-->
      <!--            <el-radio v-model="form.gender" label="1" size="large">Male</el-radio>-->
      <!--            <el-radio v-model="form.gender" label="2" size="large">Female</el-radio>-->
      <!--          </el-form-item>-->
      <!--          <el-form-item label="编号">-->
      <!--            <el-input v-model="form.number" style="width: 80%"></el-input>-->
      <!--          </el-form-item>-->
      <!--          <el-form-item label="电话号码">-->
      <!--            <el-input v-model="form.mobileNumber" style="width: 80%"></el-input>-->
      <!--          </el-form-item>-->
      <!--          <el-form-item label="类型">-->
      <!--            <el-radio v-model="form.type" label="1" size="large">Director</el-radio>-->
      <!--            <el-radio v-model="form.type" label="2" size="large">Salesman</el-radio>-->
      <!--            <el-radio v-model="form.type" label="3" size="large">Supply Staff</el-radio>-->
      <!--          </el-form-item>-->
      <!--          <el-form-item label="所属供应中心id">-->
      <!--            <el-input v-model="form.supplyCenterId" style="width: 80%"></el-input>-->
      <!--          </el-form-item>-->
      <!--        </el-form>-->
      <!--        <template #footer>-->
      <!--          <span class="dialog-footer">-->
      <!--            <el-button @click="dialogVisible = false">取消</el-button>-->
      <!--            <el-button type="primary" @click="save">确定</el-button>-->
      <!--          </span>-->
      <!--        </template>-->
      <!--      </el-dialog>-->
    </div>
  </div>
</template>

<script>

import request from "@/util/request";

export default {
  name: 'Enterprise',
  data() {
    return {
      enterpriseName: '',
      pageNum: 1,
      form: {},
      pageSize: 10,
      total: 0,
      tableData: [],
      dialogVisible: false,
      editMode: false
    }
  },
  created() {
    this.load()
  },
  methods: {
    insert() {
      this.form = {}
      this.dialogVisible = true
    },
    save() {
      if (this.editMode) {
        request.put("/enterprise/updateEnterprise", this.form).then(response => {
          console.log(response)
          this.dialogVisible = false
          if (response) {
            this.$message({
              type: "success",
              message: "编辑成功"
            })
          } else {
            this.$message({
              type: "error",
              message: "编辑失败"
            })
          }
          this.load()
          this.editMode = false;
        })
      } else {
        request.post("/enterprise/addOneEnterprise", this.form).then(response => {
          console.log(response)
          this.dialogVisible = false
          if (response) {
            this.$message({
              type: "success",
              message: "添加成功"
            })
          } else {
            this.$message({
              type: "error",
              message: "添加失败"
            })
          }
          this.load()
        })
      }
    },
    load() {
      request.get("/enterprise/show", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          enterpriseName: this.enterpriseName
        }
      }).then(response => {
        ({records: this.tableData, total: this.total} = response.data);
        console.log(response)
      })
    },
    handleEdit(row) {
      console.log('handleEdit')
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
      this.editMode = true
    },
    handleDelete(enterpriseName) {
      console.log("delete" + enterpriseName)
      request.delete("enterprise/delete/" + enterpriseName).then(response => {
        console.log(response)
        if (response === true) {
          this.$message({
            type: "success",
            message: "删除企业 " + enterpriseName + " 成功"
          })
        } else {
          this.$message({
            type: "error",
            message: "删除企业 " + enterpriseName + " 失败"
          })
        }
        this.load()
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
    },
    handleClose() {

    }
  }
}
</script>
