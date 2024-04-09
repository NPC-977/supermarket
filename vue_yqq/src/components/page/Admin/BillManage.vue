<template>
  <div>
    <div class="inrecord">
      <div style="width: 401px" class="title"><div class="el-icon-s-marketing"></div> 收 入</div>
      <el-date-picker
        v-model="formin.billYear"
        type="year"
        placeholder="选择年"
        style="width: 150px;">
      </el-date-picker>
      <el-select
        style="width: 100px;" placeholder="选择月" v-model="formin.billMonth" clearable>
        <el-option
          v-for="n in 12"
          :key="n"
          :label=String(n)
          :value="n">
        </el-option>
      </el-select>
      <el-button style="width: 150px;" type="primary" @click="getinrecord()">查询</el-button>
      <el-table
        :data="this.inform"
        border
        style="width: 401px"
        v-loading="loading">
        <el-table-column
          prop="billYear"
          label="年份"
          width="150">
        </el-table-column>
        <el-table-column
          prop="billMonth"
          label="月份"
          width="100">
        </el-table-column>
        <el-table-column
          prop="billMoney"
          label="金额"
          width="150">
        </el-table-column>
      </el-table>
    </div>
    <div class="outrecord">
      <div style="width: 401px" class="title"><div class="el-icon-s-marketing"></div> 支 出</div>
      <el-date-picker
        v-model="formout.billYear"
        type="year"
        placeholder="选择年"
        style="width: 150px;">
      </el-date-picker>
      <el-select
        style="width: 100px;" placeholder="选择月" v-model="formout.billMonth" clearable>
        <el-option
          v-for="n in 12"
          :key="n"
          :label=String(n)
          :value="n">
        </el-option>
      </el-select>
      <el-button style="width: 150px;" type="primary" @click="getoutrecord()">查询</el-button>
      <el-table
        :data="this.outform"
        border
        style="width: 401px"
        v-loading="loading">
        <el-table-column
          prop="billYear"
          label="年份"
          width="150">
        </el-table-column>
        <el-table-column
          prop="billMonth"
          label="月份"
          width="100">
        </el-table-column>
        <el-table-column
          prop="billMoney"
          label="金额"
          width="150">
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>
<script>
export default {
  name: 'BillManage',
  data() {
    return {
      loading:true,
      inform: [],
      outform: [],
      formin:{
        billYear: '',
        billMonth: ''
      },
      formout: {
        billYear: '',
        billMonth: ''
      }
    }
  },
  methods: {
    getinrecord() {
      if(this.formin.billYear!==''){
        let date=new Date(this.formin.billYear)
        this.formin.billYear=date.getFullYear()
      }
      this.$axios.post('http://localhost:8080/api/admin/inbillrecord',this.formin,{
        headers: {
          'Authorization': sessionStorage.token
        }
      }).then((res)=>{
        let result=res.data
        if (result.code===200){
          this.inform=result.data
          setTimeout(() =>this.loading=false,Math.round(Math.random()*0.5+0.1))
          this.formin.billYear=''
        }else if (result.code===501){
          this.$message({
            message: '认证过期，请重新登录!',
            type: 'error'
          })
        }
      })
    },
    getoutrecord() {
      if(this.formout.billYear!==''){
        let date=new Date(this.formout.billYear)
        this.formout.billYear=date.getFullYear()
      }
      this.$axios.post('http://localhost:8080/api/admin/outbillrecord',this.formout,{
        headers: {
          'Authorization': sessionStorage.token
        }
      }).then((res)=>{
        let result=res.data
        if (result.code===200){
          this.outform=result.data
          this.formout.billYear=''
          setTimeout(() =>this.loading=false,Math.round(Math.random()*0.5+0.1))
        }else if (result.code===501){
          this.$message({
            message: '认证过期，请重新登录!',
            type: 'error'
          })
        }
      })
    }
  },
  mounted() {
    this.getinrecord()
    this.getoutrecord()
  }
}
</script>
<style>
.inrecord {
  float: left;
  margin-right: 100px;
}
.outrecord {
  float: left;
}
.title {
  font-size: 30px;
  font-weight: bold;
}
.el-icon-s-marketing{
  font-size: 20px;
}
</style>
