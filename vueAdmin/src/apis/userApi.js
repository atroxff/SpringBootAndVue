import Axios from 'axios'
import Qs from 'qs'

/**
 * 系统认证接口
 */
export default {
  /**
   * 系统登陆
   */
  loginIn: function ({username, password}) {
    const params = {
      username: username,
      password: password
    }
    return Axios.post('/vuedemo/api/vue/login', Qs.stringify(params), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
  },
  getAllUser: function ({currentPage, pageSize, status, userName}) {
    const params = {
      currentPage: currentPage,
      pageSize: pageSize,
      status: status,
      userName: userName
    }
    return Axios.post('/vuedemo/api/vue/searchUserByCondition', Qs.stringify(params), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
  },
  addUser: function (params) {
    return Axios.post('/vuedemo/api/vue/regist', params)
  },
  deleteUser: function ({userId}) {
    const params = {
      userId: userId
    }
    return Axios.post('/vuedemo/api/vue/deleteUser', Qs.stringify(params), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
  },
  updateUser: function (params) {
    return Axios.post('/vuedemo/api/vue/updateUser', params)
  }
}
