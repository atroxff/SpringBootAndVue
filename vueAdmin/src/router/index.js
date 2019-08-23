import en from '../i18n/lang/en'
import Vue from 'vue'
import Router from 'vue-router'
import CommerViews from '@/views/commerViews'
import Login from '@/views/login/login'
import Layout from '@/views/layout/layout'
import HomeMain from '@/views/index/mainIndex'

// 不是必须加载的组件使用懒加载
const User = () => import('@/views/user/userlist')
const Task = () => import('@/views/task/tasklist')

const Icon = () => import('@/views/icon/index')
const Erji = () => import('@/views/duoji/erji')
const Erji2 = () => import('@/views/duoji/erji2')
const Sanji = () => import('@/views/duoji/sanji')
const Sanji2 = () => import('@/views/duoji/sanji2')
const Siji = () => import('@/views/duoji/siji')
const Wuji = () => import('@/views/duoji/wuji')
const Transfer = () => import('@/views/transfer/transfer')
const DataTable = () => import('@/views/table/dataTables')
const FilterTable = () => import('@/views/table/filterTable')
const DragTable = () => import('@/views/table/dragTabe')
const Upload = () => import('@/views/upload/upload')
const Markdown = () => import('@/views/editor/markdownView')
const WangeditorView = () => import('@/views/editor/wangeditorView')
const NotFound = () => import('@/page404')
const AddArticle = () => import('@/views/article/addArticle')
const AddArticleEditor = () => import('@/views/article/addArticleEditor')
const NavClassify = () => import('@/views/syssetting/navClassify')
const pagePermissions = () => import('@/views/permissions/pagePermissions')
const btnPermissions = () => import('@/views/permissions/btnPermissions')

Vue.use(Router)
let routeName = en.routeName
let defaultRouter = [
  { path: '/',
    redirect: '/index',
    hidden: true,
    children: []
  },
  {
    path: '/login',
    component: Login,
    name: '',
    hidden: true,
    children: []
  },
  {
    path: '/index',
    iconCls: 'fa fa-dashboard',
    name: routeName.home,
    component: Layout,
    alone: true,
    children: [
      {
        path: '/index',
        iconCls: 'fa fa-dashboard',
        name: '主页',
        component: HomeMain,
        children: []
      }
    ]
  },
  {
    path: '/404',
    component: NotFound,
    name: '404',
    hidden: true,
    children: []
  },
]

let addRouter = [
  { // 系统管理
    path: '/',
    iconCls: 'fa fa-user',
    name: routeName.system,
    component: Layout,
    children: [
      {
        path: '/user',
        iconCls: 'fa fa-user',
        name: routeName.userlist,
        component: User,
        children: []
      }
    ]
  },
  {// 任务管理
    path: '/',
    iconCls: 'el-icon-tickets',
    name: routeName.tasklist,
    component: Layout,
    children: [
      {
        path: '/task',
        iconCls: 'fa fa-life-ring',
        name: routeName.tasklist,
        component: Task,
        children: []
      }
    ]
  },
  {// 文章管理
    path: '/',
    iconCls: 'el-icon-tickets',
    name: routeName.article,
    component: Layout,
    children: [
      {
        path: '/addArticle',
        iconCls: 'el-icon-edit-outline',
        name: routeName.publishArticle,
        component: AddArticle,
        children: []
      },
      {
        path: '/addArticleEditor',
        iconCls: 'el-icon-edit-outline',
        name: routeName.publishArticleEditor,
        component: AddArticleEditor,
        children: []
      }
    ]
  },
  {// 图标
    path: '/',
    iconCls: 'fa fa-paw',
    name: routeName.icon,
    component: Layout,
    children: [
      {
        path: '/icon',
        iconCls: 'fa fa-life-ring',
        name: routeName.builtInIcon,
        component: Icon,
        children: []
      }
    ]
  },
  {
    path: '/',
    iconCls: 'fa fa-exchange',
    name: routeName.shuttleBox,
    component: Layout,
    children: [
      {
        path: '/transfer',
        iconCls: 'fa fa-sign-in',
        name: routeName.demoShuttle,
        component: Transfer,
        children: []
      }
    ]
  },
  {
    path: '/',
    iconCls: 'fa fa-universal-access',
    name: routeName.permissions,
    component: Layout,
    children: [
      {
        path: '/pagePermissions',
        iconCls: 'fa fa-expeditedssl',
        name: routeName.pageControl,
        component: pagePermissions,
        children: []
      },
      {
        path: '/btnPermissions',
        iconCls: 'fa fa-toggle-on',
        name: routeName.btnControl,
        component: btnPermissions,
        children: []
      }
    ]
  },
  {
    path: '/',
    iconCls: 'fa fa-newspaper-o',
    name: routeName.table,
    component: Layout,
    children: [
      {
        path: '/dataTable',
        iconCls: 'fa fa-sliders',
        name: routeName.multiDataTable,
        component: DataTable,
        children: []
      },
      {
        path: '/filterTable',
        iconCls: 'fa fa-sort-amount-asc',
        name: routeName.filterTable,
        component: FilterTable,
        children: []
      },
      {
        path: '/dragTabe',
        iconCls: 'fa fa-hand-stop-o',
        name: routeName.dragSort,
        component: DragTable,
        children: []
      }
    ]
  },

  {
    path: '/',
    iconCls: 'fa fa-server',
    name: routeName.multiDirectory,
    component: Layout,
    children: [
      {
        path: '/erji1',
        iconCls: 'fa fa-server',
        name: routeName['menu2-1'],
        component: Erji,
        children: []
      },
      {
        path: '/erji2',
        iconCls: 'fa fa-server',
        name: routeName['menu2-2'],
        component: Erji2,
        children: []
      },
      {
        path: '/erji3',
        iconCls: 'fa fa-server',
        name: routeName['menu2-3'],
        component: CommerViews, // 无限极菜单的容器
        children: [
          {
            path: '/sanji1',
            iconCls: 'fa fa-server',
            name: routeName['menu3-1'],
            component: Sanji,
            children: []
          },
          {
            path: '/sanji2',
            iconCls: 'fa fa-server',
            name: routeName['menu3-2'],
            component: Sanji2,
            children: []
          },
          {
            path: '/sanji3',
            iconCls: 'fa fa-server',
            name: routeName['menu3-3'],
            component: CommerViews,
            children: [
              {
                path: '/siji',
                iconCls: 'fa fa-server',
                name: routeName['menu4-1'],
                component: Siji,
                children: []
              },
              {
                path: '/siji1',
                iconCls: 'fa fa-server',
                name: routeName['menu4-2'],
                component: CommerViews,
                children: [
                  {
                    path: '/wuji',
                    iconCls: 'fa fa-server',
                    name: routeName['menu5-1'],
                    component: Wuji,
                    children: []
                  }
                ]
              }
            ]
          }
        ]
      }
    ]
  },
  {
    path: '/',
    iconCls: 'fa fa-cloud-upload',
    name: routeName.upload,
    component: Layout,
    meta: {role: ['superAdmin']},
    children: [
      {
        path: '/upload',
        iconCls: 'el-icon-upload2',
        name: routeName.fileUpload,
        component: Upload,
        children: []
      }
    ]
  },
  {
    path: '/',
    iconCls: 'el-icon-edit',
    name: routeName.editor,
    component: Layout,
    meta: {role: ['superAdmin', 'admin']},
    children: [
      {
        path: '/markdown',
        iconCls: 'fa fa-file-code-o',
        name: routeName.markdown,
        component: Markdown,
        children: []
      },
      {
        path: '/wangeditor',
        iconCls: 'fa fa-file-code-o',
        name: routeName.wangeditor,
        component: WangeditorView,
        children: []
      }
    ]
  },
  {
    path: '/',
    iconCls: 'el-icon-setting',
    name: routeName.systemSettings,
    component: Layout,
    meta: {role: ['superAdmin']},
    children: [
      {
        path: '/navClassifies',
        iconCls: 'el-icon-menu',
        name: routeName.navMenu,
        component: NavClassify,
        children: []
      }
    ]
  },
  { path: '*',
    redirect: '/404',
    hidden: true,
    children: []
  },

]
export default new Router({
  mode: 'history',
  routes: defaultRouter
})
export {defaultRouter, addRouter}
