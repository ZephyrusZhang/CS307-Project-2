<template>
  <div class="staff" style="padding: 20px 260px">
    <div style="margin: 10px 0">
      <el-button type="primary" @click="insert">新增</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-input v-model="modelName" placeholder="请输model名称" style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin: 5px" @click="load">查询</el-button>
    </div>
    <el-table :data="tableData" stripe border style="width: 85%">
      <el-table-column prop="number" label="产品编号" width="150"/>
      <el-table-column prop="modelName" label="model名称" width="190"/>
      <el-table-column prop="productName" label="产品名称" width="180"/>
      <el-table-column prop="unitPrice" label="单价" width="80"/>
      <el-table-column prop="sales" label="销量" width="80"/>
      <el-table-column fixed="right" label="操作" width="150">
        <template #default="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="确认删除？" @confirm="handleDelete(scope.row.modelName)">
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
      <el-dialog v-model="dialogVisible" title="请输入人员信息" width="30%" :before-close="handleClose">
        <el-form model="form" label-width="120px">
          <el-form-item label="产品编号">
            <el-input v-model="form.number" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="model名称">
            <el-input v-model="form.modelName" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="产品名称">
            <el-input v-model="form.productName" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="单价">
            <el-input v-model="form.unitPrice" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="销量">
            <el-input v-model="form.sales" style="width: 80%"></el-input>
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

export default {
  name: "Model",
  data() {
    return {
      modelName: '',
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
    load() {
      request.get("/model/show", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          modelName: this.modelName
        }
      }).then(response => {
        ({records: this.tableData, total: this.total} = response.data);
        console.log(response)
      })
    },
    save() {
      if (this.editMode) {
        console.log(this.form.number)
        request.put("/model/updateModel", this.form).then(response => {
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
          this.editMode = false
        })
      } else {
        request.post("/model/addOneModel", this.form).then(response => {
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
    handleEdit(row) {
      console.log('handleEdit')
      console.log(row)
      this.form = JSON.parse(JSON.stringify(row))
      console.log(this.form)
      this.editMode = true
      this.dialogVisible = true
    },
    handleDelete(modelName) {
      console.log("delete" + modelName)
      request.delete("model/delete/" + modelName).then(response => {
        console.log(response)
        if (response) {
          this.$message({
            type: "success",
            message: "删除用户 " + modelName + " 成功"
          })
        } else {
          this.$message({
            type: "error",
            message: "删除用户 " + modelName + " 失败"
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
  }
}
</script>

<style scoped>
</style>