<template>
  <div>
    <el-form  :model="form" :rules="rules" >
      <el-form-item label="账号" :label-width="'120px'" prop="username">
        <el-input v-model="form.username" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="密码" :label-width="'120px'" prop="password">
        <el-input v-model="form.password" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="员工编号" :label-width="'120px'" prop="eId">
        <el-input v-model="form.eId" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="员工姓名" :label-width="'120px'" prop="eName">
        <el-input v-model="form.eName" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="员工身份证" :label-width="'120px'" prop="eIdnum">
        <el-input v-model.number="form.eIdnum" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="员工工资" :label-width="'120px'" prop="eWages">
        <el-input v-model.number="form.eWages" type='number' autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <el-button @click="addEmployee">添加</el-button>
  </div>
</template>

<script>
export default {
  name: "AddEmployee",
  data() {
    return {
      form: {
        username:'',
        password:'',
        eId:'',
        eName:'',
        eIdnum:'',
        eWages:0
      },
      rules: {
        username: [
          {required: true, message: '账号不能为空', trigger: 'blur'},
          {min: 2, message: '账号至少2位', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '密码不能为空', trigger: 'blur'},
          {min: 3, message: '密码至少6位', trigger: 'blur'}
        ],
        eName: [
          {required: true, message: '姓名不能为空', trigger: 'blur'}
        ],
        eIdnum: [
          {required: true, message: '身份证不能为空', trigger: 'blur'},
          {type: 'number', message: '身份证必须为数字值'}
        ],
        eId: [
          {required: true, message: '员工编号不能为空', trigger: 'blur'},
          {min: 4, message: '员工编号至少4位', trigger: 'blur'}
        ],
        eWages: [
          {required: true, message: '工资不能为空', trigger: 'blur'},
          {type: 'number', message: '工资必须为数字值'}
        ]
      }
    }
  },
  methods: {
    addEmployee() {
      if (this.form.username==='' || this.form.password==='' || this.form.eName==='' || this.form.eIdnum==='' || this.form.eId==='' || this.form.eWages===''){
        this.$message({
          message: '请填写完整',
          type: 'error'
        })
      }else {
        this.$axios.post('http://localhost:8080/api/admin/addemployee',
          this.form,{
            headers: {
              'Authorization': sessionStorage.token
            }
          }
        ).then((res) => {
          let result=res.data
          if (result.code === 200){
            this.form.username=''
            this.form.password=''
            this.form.eName=''
            this.form.eIdnum=''
            this.form.eId=''
            this.form.eWages=''
            this.$message({
              message: '添加成功',
              type: 'success'
            })
          }else if(result.code===501){
            this.$message({
              message: '认证过期，请重新登录!',
              type: 'error'
            })
          }else if(result.data.indexOf('已存在') !== -1){
            this.$message({
              message: '该用户已存在！',
              type: 'error'
            })
          }else {
            this.$message({
              message: '添加失败',
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
