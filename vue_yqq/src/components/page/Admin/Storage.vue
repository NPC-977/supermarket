<template>
  <div>
    <el-table
      v-loading="loading"
      :data="this.storageData"
      border
      style="width: 801px">
      <el-table-column
        prop="pid"
        label="产品编号"
        width="150">
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
        width="120"
      ><template v-slot="scope">
          <el-button @click="showdialog(scope.row)" type="text" size="small">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="修改商品信息" :visible.sync="dialogVisible">
      <el-form v-slot="scope" :model="form" :rules="rules">
        <el-form-item label="商品名称" :label-width="'120px'" prop="pname">
          <el-input v-model="form.pname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="商品数量" :label-width="'120px'" prop="pcount">
          <el-input v-model.number="form.pcount" type='number' autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="商品价格" :label-width="'120px'" prop="pprice">
          <el-input v-model.number="form.pprice" type='number' autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="商品售价" :label-width="'120px'" prop="psellprice">
          <el-input v-model.number="form.psellprice" type='number' autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false;modifyProduct()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

export default {
  name: "Storage",
  data() {
    return {
      rules: {
        pname: [
          {required: true, message: '名称不能为空', trigger: 'blur'},
          {min: 2, message: '名称至少2位', trigger: 'blur'}
        ],
        pcount: [
          {required: true, message: '数量不能为空', trigger: 'blur'},
          {type: 'number', message: '数量必须为数字值'}
        ],
        pprice: [
          {required: true, message: '价格不能为空', trigger: 'blur'},
          {type: 'number', message: '价格必须为数字值'}
        ],
        psellprice: [
          {required: true, message: '售价不能为空', trigger: 'blur'},
          {type: 'number', message: '售价必须为数字值'}
        ],
      },
      loading: true,
      storageData: [],
      dialogVisible: false,
      form: {
        id: '',
        pid: '',
        pname: '',
        pcount: '',
        pprice: '',
        psellprice: ''
      }
    }
  },
  mounted () {
    this.getData();
  },
  methods: {
    showdialog(row){
      this.dialogVisible=true
      this.form=JSON.parse(JSON.stringify(row))
    },
    getData () {
      this.$axios.get('http://localhost:8080/api/public/inventory',{
        headers: {
          'Authorization': sessionStorage.token
        }
      })
        .then((res) => {
          const result = res.data
          if (result.code === 200){
            this.storageData=result.data
            setTimeout(() =>this.loading=false,Math.round(Math.random()*0.5+0.1))
            return
          }else if(result.code===501){
            this.$message({
              message: '认证过期，请重新登录!',
              type: 'error'
            })
          }
        })
    },
    modifyProduct(){
      this.$axios.post('http://localhost:8080/api/admin/modifyproduct',this.form,{
        headers: {
          'Authorization': sessionStorage.token
        }
      }).then((res) => {
        let result = res.data
        if (result.code===200){
          this.$message({
            message: '修改成功',
            type: 'success'
          })
          this.getData()
        }else if(result.code===501){
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
