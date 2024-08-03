<script setup>
//定义user对象
import {ref} from "vue";
import axios from "axios";
import {doPost} from "../until/request.js";
import {API_URL} from "../until/constant.js";

let user = ref({
  loginAct: '',
  loginPwd: '',
  rememberMe: false,
})


const login = () => {
  console.log(user);
  let loginAct = user.value.loginAct;
  let loginPwd = user.value.loginPwd;
  let rememberMe = user.value.rememberMe;
  console.log(loginAct + " --", loginPwd+" --", rememberMe + " --");

  let formData = new FormData();
  formData.append("loginAct", loginAct);
  formData.append("loginPwd", loginPwd);
  formData.append("rememberMe", rememberMe);
  //把参数值传给后台接口进行登录(ajax)
  doPost(API_URL + "/api/login", formData).then(resp =>{
    console.log(resp);

    if (resp.code === 200) {
      //登录成功

    }
  })
}

</script>

<template>
    <el-container>
      <!--左侧-->
      <el-aside>
        <img class="login-bg"  src="../assets/loginBox.svg" alt="登录背景图片">
        <div class="login-box">
          欢迎使用动力云客系统
        </div>
      </el-aside>
      <!--右侧-->
      <el-main>
        <div>
          <h4>欢迎登录</h4>
        </div>
        <el-form :model="user" label-width="auto"  style="margin-left: 20%; margin-right: 20%;  ">
          <el-form-item label="账号：">
            <el-input type="text"  show-word-limit v-model="user.loginAct" />
          </el-form-item>
          <el-form-item label="密码：">
            <el-input type="password"  v-model="user.loginPwd" />
          </el-form-item>
          <el-form-item label=" ">
            <el-button  type="primary" @click="login">登 录</el-button>
          </el-form-item>
          <el-form-item label=" ">
            <el-checkbox v-model="user.rememberMe" label="记住我" size="large" />
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
</template>
<!--scoped只作用在当前组件内-->
<style scoped>
.el-aside {
  background-color: black;
  height: calc(100vh);
  width: 40%;
  text-align: center;
}
.login-bg{
  width: 70%;
}
.login-box{
  color: white;
  font-size: 26px;
}
.el-main{
  font-size: 24px;
  text-align: center;
  margin-top: 5%;
}
.el-button{
  width: 100%;
}
</style>