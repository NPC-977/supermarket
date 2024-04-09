<template>
  <div class="header">
    <div class="logo"><div class="el-icon-s-home"></div>超市管理系统</div>
    <div class="user-info">
      <el-dropdown trigger="click" @command="handleCommand">

      <span class="el-dropdown-link">
        <div class="user-logo"><el-avatar > {{ username }} </el-avatar></div>
        <h3>{{ username }}&nbsp;{{ usertype }}</h3>
      </span>
        <el-dropdown-menu>
          <el-dropdown-item command="loginOut">退出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Header',
  data () {
    return {
      name: '未定义',
      type: '未定义',
      imgUrl: '../../assets/man.png'
    }
  },
  mounted () {
    if (sessionStorage.getItem('user_name') === null || sessionStorage.getItem('token') === null) {
      alert('您尚未登录，请先登录!')
      this.$router.push('/Login')
    }
  },
  // 利用计算属性获取用户名和用户类型
  computed: {
    // 从sessionStorage获取用户名
    username () {
      let username = sessionStorage.getItem('user_name')
      return username || this.name
    },
    usertype () {
      let usertype = sessionStorage.getItem('user_type')
      return usertype || this.type
    }
  },
  methods: {
    handleCommand (command) {
      if (command === 'loginOut') {
        sessionStorage.removeItem('user_name')
        sessionStorage.removeItem('user_type')
        sessionStorage.removeItem('token')
        sessionStorage.removeItem('avatar')
        sessionStorage.removeItem('eid')
        this.$router.push('/Login')
      } else if (command === 'userCenter') {
        this.$router.push('/UserCenter')
      }
    }
  }
}
</script>

<style scoped>
.header {
  width: 100%;
  height: 70px;
  font-size: 22px;
  line-height: 70px;
  background-color: #42b983;
}

.logo {
  float: left;
  width: 300px;
  height: 70px;
  text-align: center;
  font-weight: bold;
  font-size: 35px;
}

.user-info {
  float: right;
  width: 250px;
  height: 70px;
  padding-right: 10px;
  font-size: 16px;
  color: #fff;
}

.el-dropdown-link {
  position: relative;
  display: inline-block;
  padding-left: 50px;
  color: #fff;
  cursor: pointer;
  vertical-align: middle;
}
.user-logo {
  position: absolute;
  left: 0;
  top: 15px;
  width: 40px;
  height: 40px;
  border-radius: 50%;
}
</style>
