<template>
  <div class="adminmain">
    <el-card class="box-card"  v-for="item in items"   style="cursor: pointer;"  shadow="hover">
      <div slot="header" class="clearfix"  @click="route(item.index)">
        <span>{{item.title}}</span>
      </div>
      <div  @click="route(item.index)">{{item.text}}</div>
    </el-card>
  </div>
</template>

<script>

export default {
  name: "EmployeeMain",
  data() {
    return {
      items: [
        {
          index: 'Sale',
          title: '商品销售',
          text: '商品销售。'
        },
        {
          index: 'Clock',
          title: '打卡',
          text: '打卡。'
        }
      ]
    }
  },
  methods: {
    route(index) {
      this.$router.push('/Employee/'+index)
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
