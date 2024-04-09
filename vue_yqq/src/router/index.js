import Vue from 'vue'
import VueRouter from 'vue-router'
import Admin from "@/components/page/Admin/Admin.vue";
import AdminMain from "@/components/page/Admin/AdminMain.vue";
import EmployeeManage from "@/components/page/Admin/EmployeeManage.vue";
import Storage from "@/components/page/Admin/Storage.vue";
import Login from "@/components/page/Login.vue";
import Register from "@/components/Register.vue";
import AddProduct from '@/components/page/Admin/AddProduct.vue'
import InRecord from "@/components/page/Admin/InRecord.vue";
import SaleRecord from "@/components/page/Admin/SaleRecord.vue";
import AddEmployee from "@/components/page/Admin/AddEmployee.vue";
import BillManage from "@/components/page/Admin/BillManage.vue";
import Employee from "@/components/page/Employee/Employee.vue";
import EmployeeMain from "@/components/page/Employee/EmployeeMain.vue";
import Sale from "@/components/page/Employee/Sale.vue";
import Clock from "@/components/page/Employee/Clock.vue";
import EmployeeClockRecord from "@/components/page/Admin/EmployeeClockRecord.vue";


Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/',
    redirect: '/Login'
  },
  {
    path: '/Register',
    name: 'Register',
    component: Register
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/Admin',
    name: 'Admin',
    component: Admin,
    children: [
      {
        path: 'EmployeeClockRecord',
        name: 'EmployeeClockRecord',
        component: EmployeeClockRecord
      },
      {
        path: 'Main',
        name: 'AdminMain',
        component: AdminMain
      },
      {
        path: 'EmployeeManage',
        name: 'EmployeeManage',
        component: EmployeeManage
      },
      {
        path: 'Storage',
        name: 'Storage',
        component: Storage
      },
      {
        path: 'AddProduct',
        name: 'AddProduct',
        component: AddProduct
      },
      {
        path: 'InRecord',
        name: 'InRecord',
        component: InRecord
      },
      {
        path: 'SaleRecord',
        name: 'SaleRecord',
        component: SaleRecord
      },
      {
        path: 'AddEmployee',
        name: 'AddEmployee',
        component: AddEmployee
      },
      {
        path: 'BillManage',
        name: 'BillManage',
        component: BillManage
      }
    ]
  },
  {
    path: '/Employee',
    name: 'Employee',
    component: Employee,
    children: [
      {
        path: 'Main',
        name: 'EmployeeMain',
        component: EmployeeMain
      },
      {
        path: 'Sale',
        name: 'Sale',
        component: Sale
      },
      {
        path: 'Clock',
        name: 'Clock',
        component: Clock
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
