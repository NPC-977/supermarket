<template>
  <div>
    <h2 class="time" >{{dateFormat(newDate)}}</h2>
    <el-button type="primary" style="width: 200px;font-size: 35px;height: 80px" @click="clock">打卡</el-button>
  </div>
</template>
<script>
export default {
  name: 'Clock',
  data() {
    return {
      newDate: new Date(),
      form: {
        eId: sessionStorage.eid
      }

    }
  },
  mounted () {
    let that = this
    this.timer = setInterval(function () {
      that.newDate = new Date().toLocaleString()
    })
  },
  beforeDestroy: function () {
    if (this.timer) {
      clearInterval(this.timer)
    }
  },
  methods: {
    clock() {
      this.$axios.post('http://localhost:8080/api/employee/clock',this.form,{
        headers: {
          'Authorization': sessionStorage.token
        }
      }).then((res)=>{
        let result=res.data
        if(result.code===200){
          this.$message({
            message: '打卡成功',
            type: 'success'
          })
        }
      })
    },
    // 时间格式化
    dateFormat() {
      let date = new Date()
      let year = date.getFullYear()
      let month = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1
      let day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
      let hours = date.getHours() < 10 ? '0' + date.getHours() : date.getHours()
      let minutes = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()
      let seconds = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds()
      let week = date.getDay() // 星期
      let weekArr = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']
// 拼接 时间格式处理
      return year + '年' + month + '月' + day + '日 ' + hours + ':' + minutes + ':' + seconds + ' ' + weekArr[week]
    }
  }
}
</script>
<style scoped>

</style>
