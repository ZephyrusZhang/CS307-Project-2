<template>
  <div style="width: 100%; height: 100vh; background-color: deepskyblue; overflow: hidden">
    <div style="width: 400px; margin: 150px auto">
      <div style="color: azure; font-size: 30px; text-align: center; padding: 30px 0">欢迎登录</div>
      <el-form ref="form" :model="form" size="normal">
        <el-form-item>
          <el-input prefix-icon="el-icon-user-solid" v-model="form.username" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input prefix-icon="el-icon-lock" v-model="form.password" placeholder="密码" show-password></el-input>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%" type="primary" @click="login">登 录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      form: {}
    }
  },
  methods: {
    login() {

    }
  }
}
</script>

<style scoped>

</style>