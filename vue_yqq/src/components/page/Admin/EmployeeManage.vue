<template>
  <div>
    <el-table
      :data="this.employeeData"
      border
      style="width: 100%"
      v-loading="loading">
      <el-table-column
        prop="eid"
        label="员工编号"
        width="150">
      </el-table-column>
      <el-table-column
        prop="ename"
        label="员工姓名"
        width="120">
      </el-table-column>
      <el-table-column
        prop="eidnum"
        label="员工身份证号"
        width="200">
      </el-table-column>
      <el-table-column
        prop="edate"
        label="入职时间"
        width="180">
      </el-table-column>
      <el-table-column
        prop="ewages"
        label="薪资"
        width="150">
      </el-table-column>
      <el-table-column
        label="操作"
        width="120"
      >
        <template v-slot="scope">
          <el-button @click="showdialog(scope.row)" type="text" >编辑</el-button>
          <el-button @click="deleteemployee(scope.row.eid)" type="text" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="修改员工信息" :visible.sync="dialogVisible">
      <el-form v-slot="scope" :model="form" :rules="rules">
        <el-form-item label="员工姓名" :label-width="'120px'" prop="ename">
          <el-input v-model="form.ename" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="员工身份证号" :label-width="'120px'" prop="eidnum">
          <el-input v-model.number="form.eidnum" type="number" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="员工工资" :label-width="'120px'" prop="ewages">
          <el-input v-model.number="form.ewages" type="number" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false;test()">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false;modifyEmployee()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "EmployeeManage",
  data() {
    return {
      rules: {
        ename: [
          {required: true, message: '姓名不能为空', trigger: 'blur'}
        ],
        eidnum: [
          {required: true, message: '身份证不能为空', trigger: 'blur'},
          {type: 'number', message: '身份证必须为数字值'}
        ],
        ewages: [
          {required: true, message: '工资不能为空', trigger: 'blur'},
          {type: 'number', message: '工资必须为数字值'}
        ]
      },
      loading:true,
      dialogVisible: false,
      employeeData: [],
      form: {
        eid: '',
        ename: '',
        eidnum: '',
        ewages: ''
      }
    }
  },
  methods: {
    test() {
      console.log(this.employeeData)
    },
    getData() {
      this.$axios.get('http://localhost:8080/api/admin/employee',{
        headers:{
          'Authorization': sessionStorage.token
        }
      }).then((res) => {
        let result=res.data
        if (result.code===200){
          this.employeeData=result.data
          setTimeout(() =>this.loading=false,Math.round(Math.random()*0.5+0.1))
        }else if(result.code===501){
          this.$message({
            message: '认证过期，请重新登录!',
            type: 'error'
          })
        }
      })
    },
    modifyEmployee() {
      this.$axios.post('http://localhost:8080/api/admin/modifyemployee',this.form,{
        headers:{
          'Authorization': sessionStorage.token
        }
      }).then((res) => {
        let result=res.data
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
    },
    showdialog(row) {
      this.form=JSON.parse(JSON.stringify(row))
      this.dialogVisible=true
    },
    deleteemployee(id) {
      this.$confirm('此操作将删除该员工, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.get('http://localhost:8080/api/admin/deleteemployee/'+id,{
          headers:{
            'Authorization': sessionStorage.token
          }
        }).then((res) => {
          let result=res.data
          if (result.code===200){
            this.getData()
            this.$message({
              message: '删除成功',
              type: 'success'
            })
          }else if(result.code===501){
            this.$message({
              message: '认证过期，请重新登录!',
              type: 'error'
            })
          }
        })
      })
.catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    }
  },
  mounted() {
    this.getData()
  }
}
</script>

<style scoped>
.el-drawer__container el-drawer__open{
  width: 1000px;
}
</style>
