<template>

<!--  <div class="test">-->
<!--    <el-input v-model="formData.username" placeholder="请输入账号" style="width: 200px;margin-top: 10px"></el-input>-->
<!--    <el-input v-model="formData.password" placeholder="请输入密码" style="width: 200px;margin-top: 10px"></el-input>-->
<!--    <el-button type="primary" @click="login" style="width: 200px;margin-top: 10px" round>登录</el-button>-->
<!--  </div>-->
  <div class="login_container">
    <div class="login_box">
      <!-- 头像区 -->
      <div>
        <el-avatar :size="100" icon="el-icon-user-solid" class="avatar"></el-avatar>
      </div>
      <!-- 登陆表单区 -->
      <el-form ref="loginFormRef" label-width="0px" class="login_form" :model="formData" :rules="rules" style="width: 200px; ">
        <!-- 用户名 -->
        <el-form-item label="" prop="username">
          <el-input prefix-icon="iconfont icon-jurassic_user" v-model="formData.username"></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item label="" prop="password">
          <el-input type="password" prefix-icon="iconfont icon-mima" v-model="formData.password"></el-input>
        </el-form-item>
        <!-- 按钮区 -->
        <el-form-item label="" class="btns">
          <el-button type="primary" @click="login">登录</el-button>
          <el-button type="info" @click="reset">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>

</template>

<script>
export default {
  name: 'Login',
  data() {
    return {
      rules: {
        username: [
          {required: true, message: '账号不能为空', trigger: 'blur'},
          {min: 2, message: '账号至少2位', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '密码不能为空', trigger: 'blur'},
          {min: 3, message: '密码至少6位', trigger: 'blur'}
        ]
      },
      token: '',
      formData: {
        username: '',
        password: ''
      },
      storageData: []
    }
  },
  methods: {
    reset() {
      this.formData.username=''
      this.formData.password=''
    },
    login() {
      this.$axios.post('/login', this.formData)
        .then((res) => {
          const result = res.data
          if (result.msg.indexOf('登录失败') !== -1) {
            // 登陆失败
            this.$message({
              message: '登陆失败!',
              type: 'error'
            })
            return
          }
          // 登录成功，判断token与用户权限是否为空
          if (result.token !== '' && result.authorities !== '') {
            console.log(result.authorities)
            sessionStorage.setItem('user_name', this.formData.username)
            // 将逗号作为分隔符，将用户权限分割为字符串数组
            const array = result.authorities.split(',')
            let userType = ''
            for (let j = 0; j < array.length; j++) {
              if (array[j] !== '') {
                userType += this.getRoleName(array[j])
                userType += ' '
              }
            }
            // 将用户权限和token存入sessionStorage
            sessionStorage.setItem('user_type', userType)
            sessionStorage.setItem('token', result.token)
            this.token = result.token
            let path = ''
            if (userType.indexOf('管理员') !== -1) {
              path = '/Admin/Main'
            } else if (userType.indexOf('员工') !== -1) {
              this.$axios.get('http://localhost:8080/api/employee/getemployeeid/'+this.formData.username,{
                headers: {
                  'Authorization': result.token
                }
              }).then((res)=> {
                let result1=res.data
                if (result1.code===200){
                  sessionStorage.setItem('eid',result1.data)
                }
              })
              path = '/Employee/Main'
            }
            this.$router.push(path)
          }
        }).catch(function (error) {
        if (error.response) {
          // 请求成功发出且服务器也响应了状态码，但状态代码超出了 2xx 的范围
          console.log(error.response.data);
          console.log(error.response.status);
          console.log(error.response.headers);
          alert("无法连接到服务器，请重试！")
        } else if (error.request) {
          // 请求已经成功发起，但没有收到响应
          // `error.request` 在浏览器中是 XMLHttpRequest 的实例，
          // 而在node.js中是 http.ClientRequest 的实例
          console.log(error.request);
        } else {
          // 发送请求时出了点问题
          console.log('Error', error.message);
        }
        console.log(error.config);
      });
    },
    getRoleName(role) {
      if (role === 'ROLE_admin') {
        return '管理员'
      } else if (role === 'ROLE_employee') {
        return '员工'
      } else {
        return '未定义'
      }
    }
  }
}
</script>

<style scoped>
.login_container {
  background-color: #61daff;
  padding: 0;
  margin: 0;
  position: absolute;
  left: 0;
  top: 0;
  bottom: 0;
  right: 0;
}
.login_box {
  border-radius: 10px;
  background-color: #fff;
  width: 400px;
  height: 400px;
  margin: 0 auto;
  margin-top: 100px;
}
.avatar {
  margin-top: 50px;
  text-align: center;
}
.login_form {
  margin: 0 auto;
}
</style>
