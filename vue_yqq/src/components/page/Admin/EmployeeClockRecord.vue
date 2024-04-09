<template>
  <div class="clockrecord">
    <el-table :data="form" v-loading="loading" border>
      <el-table-column prop="eid" label="工号" width="150"></el-table-column>
      <el-table-column prop="ename" label="姓名" width="150"></el-table-column>
      <el-table-column prop="eclocktime" label="打卡时间" width="200"></el-table-column>
    </el-table>
  </div>

</template>

<script>
export default {
  name: 'EmployeeClockRecord',
  data() {
    return {
      form:[],
      loading:true
    }
  },
  methods: {
    getData() {
      this.$axios.get('http://localhost:8080/api/admin/clockrecord',{
        headers:{
          'Authorization':sessionStorage.token
        }
      }).then((res)=>{
        let result=res.data
        if(result.code===200){
          this.form=result.data
          setTimeout(() =>this.loading=false,Math.round(Math.random()*0.5+0.1))
        }
      })
    }
  },
  mounted() {
    this.getData()
  }
}
</script>

<style>
.clockrecord {
  margin: 0 auto;
}
</style>
