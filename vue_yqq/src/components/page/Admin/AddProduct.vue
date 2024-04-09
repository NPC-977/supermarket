<template>
<div>
  <el-form  :model="form" :rules="rules" v-loading="loading"
            element-loading-text="入库中..."
            element-loading-spinner="el-icon-loading">
    <el-form-item label="商品编号" :label-width="'120px'" prop="pId">
      <el-input v-model="form.pId" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="商品名称" :label-width="'120px'" prop="pName">
      <el-input v-model="form.pName" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="商品数量" :label-width="'120px'" prop="pCount">
      <el-input v-model.number="form.pCount" type='number' autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="商品价格" :label-width="'120px'" prop="pPrice">
      <el-input v-model.number="form.pPrice" type='number' autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="商品售价" :label-width="'120px'" prop="pSellprice">
      <el-input v-model.number="form.pSellprice" type='number' autocomplete="off"></el-input>
    </el-form-item>
  </el-form>
  <el-button @click="addProduct">入库</el-button>
</div>
</template>

<script>
export default {
  name: "AddProduct",
  data() {
    return {
      loading: false,
      form: {
        id: '',
        pId: '',
        pName: '',
        pCount: '',
        pPrice: '',
        pSellprice: ''
      },
      rules: {
        pId: [
          {required: true, message: '编号不能为空', trigger: 'blur'},
          {min: 6,max: 6, message: '编号为6位', trigger: 'blur'}
        ],
        pName: [
          {required: true, message: '名称不能为空', trigger: 'blur'},
          {min: 2, message: '名称至少2位', trigger: 'blur'}
        ],
        pCount: [
          {required: true, message: '数量不能为空', trigger: 'blur'},
          {type: 'number', message: '数量必须为数字值'}
        ],
        pPrice: [
          {required: true, message: '价格不能为空', trigger: 'blur'},
          {type: 'number', message: '价格必须为数字值'}
        ],
        pSellprice: [
          {required: true, message: '售价不能为空', trigger: 'blur'},
          {type: 'number', message: '售价必须为数字值'}
        ],
      }
    }
  },
  methods: {
    addProduct() {
      this.loading=true
      if (this.form.pId==='' || this.form.pSellprice==='' || this.form.pPrice==='' || this.form.pName==='' || this.form.pCount===''){
        this.$message({
          message: '请填写完整',
          type: 'error'
        })
        this.loading=false
      }else {
        this.$axios.post('http://localhost:8080/api/admin/inventoryadd',
          this.form,{
            headers: {
              'Authorization': sessionStorage.token
            }
          }
        ).then((res) => {
          let result=res.data
          if (result.code === 200){
            this.form.id=''
            this.form.pId=''
            this.form.pPrice=''
            this.form.pName=''
            this.form.pSellprice=''
            this.form.pCount=''

            setTimeout(() => {this.loading=false;this.$message({
              message: '入库成功',
              type: 'success'
            })}, 500)

          }else if(result.code===501){
            this.$message({
              message: '认证过期，请重新登录!',
              type: 'error'
            })
          }else {
            this.$message({
              message: '入库失败',
              type: 'error'
            })
          }
        })
      }

    }
  }
}
</script>

<style>

</style>
