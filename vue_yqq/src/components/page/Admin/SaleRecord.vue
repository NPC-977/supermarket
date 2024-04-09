<template>
  <div>
    <el-table :data="form" border style="width: 731px;" v-loading="loading">
      <el-table-column
        prop="pid"
        label="商品编号"
        width="150">
      </el-table-column>
      <el-table-column
        prop="pname"
        label="商品名称"
        width="200">
      </el-table-column>
      <el-table-column
        prop="pcount"
        label="销售数量"
        width="100">
      </el-table-column>
      <el-table-column
        prop="pprice"
        label="销售价格"
        width="100">
      </el-table-column>
      <el-table-column
        prop="pdate"
        label="销售日期"
        width="180">
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
export default {
  name: 'SaleRecord',
  data() {
    return {
      loading:true,
      form: [{
        "pprice": 0,
        "pid": '',
        "pname": '',
        "pcount": 0,
        "pdate": ''
      }]
    }

  },
  methods: {
    getData() {
      this.$axios.get('http://localhost:8080/api/admin/salerecord',{
        headers:{
          'Authorization': sessionStorage.token
        }
      }).then((res) => {
        let result = res.data
        if (result.code===200){
          this.form=result.data
          setTimeout(() =>this.loading=false,Math.round(Math.random()*0.5+0.1))
        }else if(result.code===501){
          this.$message({
            message: '认证过期，请重新登录!',
            type: 'error'
          })
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

</style>
