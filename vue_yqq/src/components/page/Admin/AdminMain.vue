<template>
  <div class="adminmain">
    <el-card class="box-card"  v-for="item in items"   style="cursor: pointer;"  shadow="hover">
      <div slot="header" class="clearfix" @click="route(item.index)">
        <span>{{item.title}}</span>
      </div>
      <div @click="route(item.index)">{{item.text}}</div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "AdminMain",
  data() {
    return {
      items: [
        {
          index: 'Storage',
          title: '库存管理',
          text: '查看商品库存，编辑商品信息。'
        },
        {
          index: 'AddProduct',
          title: '商品入库',
          text: '商品入库。'
        },
        {
          index: 'InRecord',
          title: '入库记录',
          text: '查看商品入库记录。'
        },
        {
          index: 'SaleRecord',
          title: '销售记录',
          text: '查看商品销售记录。'
        },
        {
          index: 'EmployeeManage',
          title: '员工管理',
          text: '查看员工信息，编辑员工信息，删除员工。'
        },
        {
          index: 'AddEmployee',
          title: '添加员工',
          text: '添加员工，员工账号。'
        },
        {
          index: 'EmployeeClockRecord',
          title: '打卡记录',
          text: '查看员工打卡记录。'
        }
        ,
        {
          index: 'BillManage',
          title: '流水管理',
          text: '查看每年/月收支详情。'
        }
      ]
    }
  },
  methods: {
    route(index) {
      this.$router.push('/Admin/'+index)
    }
  }
}
</script>

<style>
.box-card {
  float: left;
  width: 400px;
  margin-left: 100px;
  margin-top: 50px;
}
.adminmain {
  margin: 0 auto;
}
</style>
