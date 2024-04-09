<template>
  <div>
    <el-drawer
      title="我是标题"
      :visible.sync="drawer"
      :with-header="false"
      direction="ltr"
      size="15%"
      >
<!--      <el-aside width="200px">-->
        <div>
          <el-menu router>
            <template v-for="item in items" v-if="checkRole(item.role,role)">
              <template v-if="item.subs">
                <el-submenu :index="item.index" :key="item.index">
                  <template slot="title">
                    <i :class="item.icon"></i>{{ item.title }}
                  </template>
                  <el-menu-item v-for="(subItem, i) in item.subs" :key="i" :index="subItem.index">
                    {{ subItem.title }}
                  </el-menu-item>
                </el-submenu>
              </template>
              <template v-else>
                <el-menu-item :index="item.index" :key="item.index">
                  <i :class="item.icon"></i>{{ item.title }}
                </el-menu-item>
              </template>
            </template>
          </el-menu>
        </div>
<!--      </el-aside>-->
    </el-drawer>
    <div class="qwe" v-on:mouseover="drawer=true" >
    </div>
  </div>

</template>

<script>
export default {
  name: 'Sidebar',
  data () {
    return {
      drawer: false,
      role: '',
      items: [
        {
          icon: 'el-icon-message',
          index: 'readme',
          title: '系统功能介绍',
          role: 'all'
        },
        {
          icon: 'el-icon-menu',
          index: 'ProductManage',
          title: '商品管理',
          role: 'admin',
          subs: [
            {
              index: 'Storage',
              title: '库存管理'
            },
            {
              index: 'AddProduct',
              title: '商品入库'
            },
            {
              index: 'InRecord',
              title: '入库记录'
            },
            {
              index: 'SaleRecord',
              title: '销售记录'
            }
          ]
        },
        {
          icon: 'el-icon-menu',
          index: 'EmployeeManage',
          title: '员工管理',
          role: 'admin',
          subs: [
            {
              index: 'EmployeeManage',
              title: '员工管理'
            },
            {
              index: 'AddEmployee',
              title: '添加员工'
            },
            {
              index: 'EmployeeClockRecord',
              title: '打卡记录'
            }
          ]
        },
        {
          icon: 'el-icon-menu',
          index: 'BillManage',
          title: '流水管理',
          role: 'admin'
        },
        {
          icon: 'el-icon-menu',
          index: 'Sale',
          title: '商品出售',
          role: 'employee'
        },
        {
          icon: 'el-icon-menu',
          index: 'Clock',
          title: '打卡',
          role: 'employee'
        }
      ]
    }
  },
  mounted () {
    let userType = sessionStorage.getItem('user_type')
    console.log('用户类型' + userType)
    if (userType.indexOf('管理员') !== -1) {
      this.role = 'admin'
    } else if (userType.indexOf('员工') !== -1) {
      this.role = 'employee'
    }
  },
  methods: {
    checkRole (role1, role2) {
      console.log('checkRole---' + role1 + ',' + role2)
      if (role1 === 'all') {
        return true
      } else return role1 === role2;
    }
  }
}
</script>

<style scoped>
.sidebar {
  display: block;
  position: absolute;
  width: 250px;
  left: 0;
  top: 120px;
  bottom: 0;
  background: #2E363F;
}
.qwe {
  left: 0;
  bottom: 0;
  width: 30px;
  height: 1000px;
  position: absolute;
}
</style>
