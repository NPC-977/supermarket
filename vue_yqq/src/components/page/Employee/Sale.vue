<template>
  <div>
    <el-table
      v-loading="loading"
      :data="this.storageData"
      border
      style="width: 911px">
      <el-table-column
        prop="pid"
        label="产品编号"
        width="130">
      </el-table-column>
      <el-table-column
        prop="pname"
        label="产品名称"
        width="170">
      </el-table-column>
      <el-table-column
        prop="pcount"
        label="产品数量"
        width="120">
      </el-table-column>
      <el-table-column
        prop="pprice"
        label="产品价格"
        width="120">
      </el-table-column>
      <el-table-column
        prop="psellprice"
        label="产品售价"
        width="120">
      </el-table-column>
      <el-table-column
        label="操作"
        width="150"
      >
        <template v-slot="scope">
          <el-button @click="sub(scope.row)" size="medium">-</el-button>
          <el-button @click="add(scope.row)" size="medium">+</el-button>
        </template>
      </el-table-column>
      <el-table-column
        label="已选数量"
        prop="count"
        width="100">
      </el-table-column>
    </el-table>
    <el-button @click="submit" type="primary" width="200">结算</el-button>

  </div>
</template>

<script>

export default {
  name: "Sale",
  data() {
    return {
      loading: true,
      storageData: [],
      dialogVisible: false
    }
  },
  mounted() {
    this.getData();
  },
  methods: {
    submit() {
      let flag=false
      for (let i in this.storageData) {
        if (this.storageData[i].count === 0) {
          continue
        }
        flag=true
        let form = {
          pId: this.storageData[i].pid,
          pCount: this.storageData[i].count
        }
        this.$axios.post('http://localhost:8080/api/employee/sale', form, {
          headers: {
            'Authorization': sessionStorage.token
          }
        }).then((res) => {
          let result = res.data
          if (result.code === 501) {
            this.$message({
              message: '认证过期，请重新登录！',
              type: 'error'
            })
          }else if (result.code===200){
            this.getData()
          }
        })
      }

      if (flag){
        this.$message({
          message: '出售成功',
          type: 'success'
        })
      }
    },
    add(row) {
      if (row.count < row.pcount) {
        row.count++
      }
      console.log(this.storageData)
    },
    sub(row) {
      if (row.count > 0) {
        row.count--
      }
    },
    addcountatt() {
      for (let temp in this.storageData) {
        this.$set(this.storageData[temp], "count", 0)
      }
      console.log(this.storageData)
    },
    showdialog(row) {
      this.dialogVisible = true
      this.form = JSON.parse(JSON.stringify(row))
    },
    getData() {
      this.$axios.get('http://localhost:8080/api/public/inventory', {
        headers: {
          'Authorization': sessionStorage.token
        }
      })
        .then((res) => {
          const result = res.data
          if (result.code === 200) {
            this.storageData = result.data

            setTimeout(() => this.loading = false, Math.round(Math.random() * 0.5 + 0.1))
            this.addcountatt();
            return
          } else if (result.code === 501) {
            this.$message({
              message: '认证过期，请重新登录!',
              type: 'error'
            })
          }
        })
    },
    modifyProduct() {
      this.$axios.post('http://localhost:8080/api/admin/modifyproduct', this.form, {
        headers: {
          'Authorization': sessionStorage.token
        }
      }).then((res) => {
        let result = res.data
        if (result.code === 200) {
          this.$message({
            message: '修改成功',
            type: 'success'
          })
        } else if (result.code === 501) {
          this.$message({
            message: '认证过期，请重新登录!',
            type: 'error'
          })
        }
      })
    }
  }

}
</script>

<style scoped>

</style>
