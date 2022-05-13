<template>
  <div class="home" style="padding: 100px">
    <div style="margin: 10px 0">
      <el-button type="primary">插入</el-button>
      <el-button type="primary">删除</el-button>
      <el-button type="primary">修改</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入内容" style="width: 20%"></el-input>
      <el-button type="primary" style="margin: 5px">查询</el-button>
    </div>
    <el-table :data="tableData" stripe border style="width: 100%">
      <el-table-column prop="date" sortable label="Date" width="250"/>
      <el-table-column prop="name" label="Name" width="250"/>
      <el-table-column prop="address" label="Address" width="250"/>
      <el-table-column fixed="right" label="操作" width="220">
        <template #default>
          <el-button size="mini" @click="handleEdit">编辑</el-button>
          <el-popconfirm title="确认删除？">
            <template #reference>
              <el-button type="danger" size="mini" @click="handleDelete">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin: 20px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="10"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>

export default {
  name: 'HomeView',
  components: {
  },
  data() {
    return {
      search: '',
      currentPage: 1,
      total: 10,
      tableData: [
        {
          date: '2016-05-03',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-02',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-04',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-01',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
      ]
    }
  },
  methods: {
    handleEdit() {
      console.log('handleEdit')
    },
    handleDelete() {
      console.log('handleDelete')
    },
    handleSizeChange() {
      console.log('handleSizeChange')
    },
    handleCurrentChange() {
      console.log('handleCurrentChange')
    }
  }
}
</script>
