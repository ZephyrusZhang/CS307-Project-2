<template>
  <div>
    <el-menu
        style="width: 200px; min-height: calc(100vh - 50px)"
        default-active="staff"
        default-openeds="system"
        class="el-menu-vertical-demo">
      <el-submenu index="system">
        <template #title>系统管理</template>
        <el-menu-item index="staff" :route="{path: '/'}">供应中心成员管理</el-menu-item>
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