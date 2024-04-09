<template>
  <div>
    <Header></Header>
    <el-container style="height: 100%; ">

      <Sidebar></Sidebar>
      <router-view class="myview"/>
    </el-container>
  </div>


</template>

<script>
import Header from '@/components/common/Header.vue'
import Sidebar from '@/components/common/Sidebar.vue'

export default {
  name: "Employee",
  components: { Sidebar, Header }
}
</script>

<style scoped>
.myview {
  margin: 0 auto;
}
</style>
