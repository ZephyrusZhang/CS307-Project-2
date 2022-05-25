<template>
  <div>
    <el-menu
        style="width: 200px; min-height: calc(100vh - 50px)"
        default-active="staff"
        router
        class="el-menu-vertical-demo">
      <el-submenu index="system">
        <template #title>
          <i class="el-icon-office-building"></i>
          <span>系统管理</span>
        </template>
        <el-menu-item index="staff">供应中心成员管理</el-menu-item>
        <el-menu-item index="enterprise">公司管理</el-menu-item>
        <el-menu-item index="center">供应中心管理</el-menu-item>
        <el-menu-item index="model">产品管理</el-menu-item>
      </el-submenu>
      <el-submenu index="api">
        <template #title>
          <i class="el-icon-s-finance"></i>
          <span>信息查询</span>
        </template>
        <el-menu-item index="api">API</el-menu-item>
        <el-menu-item index="order">订单</el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "Aside"
}
</script>

<style scoped>

</style>