<template>

  <div class="order" style="padding: 20px 100px">
    <div style="margin: 10px 0">
      <el-button type="primary" @click="insert">新增</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-input v-model="name" placeholder="请输入供应中心名称" style="width: 25%" clearable></el-input>
      <el-button type="primary" style="margin: 5px" @click="load">查询</el-button>
    </div>
    <el-table :data="tableData" stripe border style="width: 1000px">
      <el-table-column prop="contract_number" sortable label="contract_number" width="200"/>
      <el-table-column prop="enterprise" label="enterprise" width="120"/>
      <el-table-column prop="product_model" label="product_model" width="100"/>
      <el-table-column prop="quantity" label="quantity" width="100"/>
      <el-table-column prop="estimated_delivery_date" label="estimated_delivery_date" width="100"/>
      <el-table-column prop="lodgement_date" label="lodgement_date" width="100"/>
      <el-table-column fixed="right" label="操作" width="150">
        <template #default="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="确认删除？" @confirm="handleDelete(scope.row.name)">
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
      <el-dialog v-model="dialogVisible" title="请输入供应中心信息" width="30%" :before-close="handleClose">
        <el-form model="form" label-width="120px">
          <el-form-item label="供应中心名称">
            <el-input v-model="form.name" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="供应中心初始支出">
            <el-input v-model="form.expenditure" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="供应中心初始收入">
            <el-input v-model="form.revenue" style="width: 80%"></el-input>
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
export default {
  name: "Order"
}
</script>

<style scoped>

</style>