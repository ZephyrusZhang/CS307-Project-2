<template>
  <div class="home" style="padding: 20px 180px">
    <div style="margin: 10px 0">
      <el-button type="primary" @click="insert">新增</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-input v-model="number" placeholder="请输入人员编号" style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin: 5px" @click="load">查询</el-button>
    </div>
    <el-table :data="tableData" stripe border style="width: 100%">
      <el-table-column prop="id" sortable label="id" width="50"/>
      <el-table-column prop="name" label="姓名" width="120"/>
      <el-table-column prop="age" label="年龄" width="50"/>
      <el-table-column prop="gender" label="性别" width="80"/>
      <el-table-column prop="number" label="编号" width="100"/>
      <el-table-column prop="mobileNumber" label="电话号码" width="300"/>
      <el-table-column prop="type" label="类型" width="100"/>
      <el-table-column prop="supplyCenterId" label="所属供应中心id" width="120"/>
      <el-table-column fixed="right" label="操作" width="150">
        <template #default="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="确认删除？" @confirm="handleDelete(scope.row.number)">
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
      <el-dialog v-model="dialogVisible" title="Tips" width="30%" :before-close="handleClose">
        <el-form model="form" label-width="120px">
          <el-form-item label="名字">
            <el-input v-model="form.name" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model="form.age" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio v-model="form.gender" label="1" size="large">Male</el-radio>
            <el-radio v-model="form.gender" label="2" size="large">Female</el-radio>
          </el-form-item>
          <el-form-item label="编号">
            <el-input v-model="form.number" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="电话号码">
            <el-input v-model="form.mobileNumber" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="类型">
            <el-radio v-model="form.type" label="1" size="large">Director</el-radio>
            <el-radio v-model="form.type" label="2" size="large">Salesman</el-radio>
            <el-radio v-model="form.type" label="3" size="large">Supply Staff</el-radio>
          </el-form-item>
          <el-form-item label="所属供应中心id">
            <el-input v-model="form.supplyCenterId" style="width: 80%"></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="save">确定</el-button>
          </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>

import request from "@/util/request";
import {Female, Male} from "@element-plus/icons";

export default {
  name: 'HomeView',
  components: {Female, Male},
  data() {
    return {
      number: '',
      pageNum: 1,
      form: {},
      pageSize: 10,
      total: 0,
      tableData: [],
      dialogVisible: false
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
      if (this.form.number) {
        request.put("/staff/updateStaff", this.form).then(response => {
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
        })
      } else {
        request.post("/staff/addOneStaff", this.form).then(response => {
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
      request.get("/staff/listStaffPage", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          number: this.number
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

    },
    handleDelete(number) {
      console.log("delete" + number)
      request.delete("staff/delete/" + number).then(response => {
        console.log(response)
        if (response === true) {
          this.$message({
            type: "success",
            message: "删除用户 " + number + " 成功"
          })
        } else {
          this.$message({
            type: "error",
            message: "删除用户 " + number + " 失败"
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
    }
  }
}
</script>
