<template>
    <div class="container messageboard">
      <!-- 查询区----start -->
        <el-form :label-position="labelPosition" :label-width="labelWidth" :inline="true" ref="formSearch" :model="formSearch" class="demo-form-inline">
          <el-form-item label="昵称" prop="name">
            <el-input v-model="formSearch.userName" placeholder="昵称"></el-input>
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-select v-model="formSearch.status" placeholder="请选择状态">
              <el-option label="正常" value=1></el-option>
              <el-option label="已拉黑" value=0></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label=" " style="margin-left:50px;">
            <el-button type="primary" @click="onSearch">查询</el-button>
            <el-button type="warning" plain @click="onReset">重置</el-button>
          </el-form-item>
        </el-form>
      <!-- 查询区----end -->
      <!-- 操作区----start -->
      <el-row class="mgb15">
        <el-button size="small" round type="primary" @click="handleAdd">新增</el-button>
        <!--<el-button size="small" round type="danger" @click="deleteMany">批量删除</el-button>-->
      </el-row>
      <br/>
      <!-- 操作区----end -->
      <!-- 表格---start -->
      <el-table :data="tableData" v-loading="listLoading"  border stripe style="width: 100%" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="50">
        </el-table-column>
        <el-table-column prop="userName" label="昵称" width="200" align="center" sortable>
        </el-table-column>
        <el-table-column prop="account" label="账号" align="center" width="200">
        </el-table-column>
        <el-table-column prop="status" label="性别" align="center" width="200">
          <template slot-scope="scope">
            <span>{{ scope.row.sex==1?'男':'女' }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="status" label="状态" align="center" width="200">
          <template slot-scope="scope">
            <span>{{ scope.row.status==1?'正常':'已拉黑' }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建日期"  width="200" :formatter="this.$common.timestampToTime" sortable>
        </el-table-column>

        <el-table-column label="操作" fixed="right" min-width="230">
          <template slot-scope="scope">
            <el-button size="mini" plain type="primary" @click="handleDetail(scope.$index, scope.row)">详情</el-button>
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button size="mini" plain type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination background layout="total,sizes,prev, pager, next,jumper" :current-page="pageInfo.currentPage" :page-size="pageInfo.pageSize" :total="pageInfo.pageTotal" :page-sizes="[5, 10, 20, 50]" @size-change="handleSizeChange" @current-change="handleCurrentChange">
      </el-pagination>

      <!-- 编辑弹框---start -->
      <el-dialog  :title="formEditTitle" :visible.sync="dialogEdittVisible" width="700px" @close="closeDialog('formEdit')">
        <el-form :label-position="labelPosition" :label-width="labelWidth" :rules="rulesEdit" :disabled="formEditDisabled" :inline="true" ref="formEdit" :model="formEdit" class="demo-form-inline">
          <el-form-item label="姓名" prop="userName">
            <el-input v-model="formEdit.userName" placeholder="姓名" ></el-input>
          </el-form-item>
          <el-input v-model="formEdit.userId" placeholder="userId" disabled style='display : none'></el-input>
          <el-form-item label="性别" prop="sex">
            <el-select v-model="formEdit.sex" placeholder="性别" @change="sexChange">
              <el-option
                v-for="(item,i) in sexSelect"
                :key="i"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="账号" prop="account">
            <el-input v-model="formEdit.account" placeholder="账号"></el-input>
          </el-form-item>
          <el-form-item label="状态" prop="status" @change="statusChange">
            <el-select v-model="formEdit.status" placeholder="状态">
              <el-option
                v-for="(item,i) in statusSelect"
                :key="i"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input  v-model="formEdit.password" placeholder="请输入密码"></el-input>
          </el-form-item>
          <el-form-item label="手机号码" prop="phone">
            <el-input v-model="formEdit.phone" placeholder="手机号码"></el-input>
          </el-form-item>
        </el-form>

        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogEdittVisible = false">取 消</el-button>
          <el-button v-if="!formEditDisabled" type="primary" @click="handleSave">确 定</el-button>
        </div>
      </el-dialog>

      <!-- 编辑弹框---end -->
    </div>
</template>

<script>
  import apis from '../../apis/apis'

export default {

  name: 'userlist',
  data() {
    return {
      showClass :false,
      listLoading : false,//
      pageInfo: { //分页
        currentPage: 1,
        pageSize: 5,
        pageTotal: 0
      },
      formSearch: { //表单查询
        userName: '',
        status:''
      },
      formEdit: { //表单编辑
        userId:null,
        userName: '',
        password:'',
        account:'',
        sex:null,
        phone: '',
        status : null,
      },

      sexSelect: [{
        value: 1,
        label: '男'
      }, {
        value: 0,
        label: '女'
      }],
      statusSelect: [{
        value: 1,
        label: '正常'
      }, {
        value: 0,
        label: '已拉黑'
      }],

      rulesEdit:  {
        userName: [{ required: true, message: "请输入姓名", trigger: "blur" },],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
        account: [{ required: true, message: "请输入账号", trigger: "blur" }],
        phone: [{ required: true, message: "请输入手机号", trigger: "change" }],
        sex: [{ required: true, message: "请选择性别", trigger: "blur" }],
        status: [{ required: true, message: "请选择状态", trigger: "blur" }]
      },

      formEditTitle:'编辑',//新增，编辑和查看标题
      formEditDisabled:false,//编辑弹窗是否可编辑
      dialogEdittVisible: false,  //编辑弹框显示
      dialogType:'',//弹框类型：add,edit,show
      tableData: [],
      labelPosition: 'right', //lable对齐方式
      labelWidth: '80px', //lable宽度
      formLabelWidth: '120px',
      multipleSelection: []
    };
  },
  mounted(){
    this.onSearch();
  },
  methods:{
    onSearch(){
      //console.log("onSearch")
      var that = this;
      let _param = {
        currentPage: that.pageInfo.currentPage,
        pageSize: that.pageInfo.pageSize,
        status : that.formSearch.status,
        userName : that.formSearch.userName
      }
      const  param = Object.assign({},_param);
      apis.userApi.getAllUser(param)
        .then((data)=>{
          var dataRes = data.data;
          console.log(dataRes);
          if (dataRes.status == 200) {
            console.log(dataRes.data);
            that.pageInfo.pageTotal = dataRes.data.total;
            that.pageInfo.currentPage = dataRes.data.pageNum;
            that.tableData = dataRes.data.list;
          }

        })
        .catch((err)=>{
          this.listLoading=false;
          this.$message({message: '查询异常，请重试',type: "error"});
        });
    },

    onReset(){
      //console.log("onReset")
      this.formSearch= { //表单查询
        userName: '',
        status:''
      }
    },

    handleAdd(){
      //console.log("handleAdd")
      this.dialogEdittVisible = true;
      this.$nextTick(()=>{
        this.dialogType='add';
        this.formEditTitle='新增';
        this.formEditDisabled=false;
      });
    },

    handleSelectionChange(){
      console.log("handleSelectionChange")
    },

    handleDetail(index,rowData){
      //console.log("handleDetail")
      this.dialogEdittVisible = true;
      this.$nextTick(()=>{
        this.dialogType='show';
        this.formEditTitle='详情';
        this.formEditDisabled=true;
        this.formEdit= Object.assign({}, rowData) ;
        this.formEdit.gender+='';
      });
    },

    handleEdit(index,rowData){
      console.log("handleEdit");
      this.dialogEdittVisible = true;//等dom渲染完，读取data中初始值，然后再复制，这样重置的是data中初始值
      this.$nextTick(()=>{
        this.dialogType='edit';
        this.formEditTitle='编辑';
        this.formEditDisabled=false;
        console.log(rowData);
        this.formEdit= Object.assign({}, rowData);
        this.formEdit.gender+='';//必须转换成字符串才能回显
      });
    },

    handleDelete(index, rowData){
      //console.log("handleDelete")
      var that = this;
      var userId=rowData.userId;
      this.$confirm('此操作将永久删除该人员, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        apis.userApi.deleteUser({userId : userId})
          .then((data)=>{
            var dataRes = data.data;
            if(dataRes.status==200){
              this.$message({message: '执行成功',type: "success"});
              that.onSearch();
            }
          })
          .catch((err)=>{
            this.$message({message: err,type: "error"});
          });
      }).catch(() => {
        this.$message({type: 'info',message: '已取消删除'});
      });
    },

    handleSizeChange(val){
      //console.log("handleDelete")
      this.pageInfo.pageSize = val;
      this.onSearch();
    },

    handleCurrentChange(val){
      //console.log("handleDelete")
      this.pageInfo.currentPage = val;
      this.onSearch();
    },

    handleSave(){
      //console.log("handleSave")
      if(this.dialogType=='add'){
        this.save();
      }
      else if(this.dialogType=='edit'){
        this.update();
      }
      else{
        this.$message({message: '操作异常',type: "error"});
      }
    },

    save(){
      //console.log("save")
      this.$refs["formEdit"].validate(valid => {
        var that = this;
        if (valid) {
          let param = Object.assign({}, this.formEdit);
          apis.userApi.addUser(param)
            .then((data) => {
              var dataRes = data.data;
              if (dataRes.status == 200) {
                this.$message({message: '执行成功',type: "success"});
                this.dialogEdittVisible = false;
                that.onSearch();
                return;
              }else{
                this.$message({message: '执行失败，请重试', type: "error"});
              }
            })
            .catch((err) => {
              this.$message({message: '系统异常', type: "error"});
            });
        }
      });
    },

    update(){
      //console.log("update")
      this.$refs["formEdit"].validate(valid => {
        var that = this;
        if(valid){
          let param = Object.assign({}, this.formEdit);
          apis.userApi.updateUser(param)
            .then((data)=>{
              var dataRes = data.data;
              if (dataRes.status == 200) {
                  this.$message({message: '执行成功', type: "success"});
                  this.dialogEdittVisible = false;
                  that.onSearch();
                  return;
              }
              this.$message({message: '执行失败，请重试',type: "error"});
            })
            .catch((err)=>{
              this.$message({message: '系统异常',type: "error"});
            });
        }


      });
    },

    closeDialog(formName){
      this.$refs[formName].resetFields();
    },
    sexChange(vId){
      let obj = {};
      obj = this.sexSelect.find((item)=>{
        return item.value === vId;
      });
    },
    statusChange(vId){
      let obj = {};
      obj = this.statusSelect.find((item)=>{
        return item.value === vId;
      });
    },

  },
}
</script>

<style lang="scss">
  .name{
    font-size: 26px;
    color: #666666;
    display: block;
    margin-bottom: 10px;
    margin-top: 24px;
  }
  .code{
    background-color: #e5e8ec;
    padding: 0 4px;
    border: 1px solid #ced1dc;
    border-radius: 4px;
  }
  .text{
    font-size: 14px;
    color: #5e6d82;
    line-height: 1.5em;
    margin: 5px 0px;
  }
  .ele-icoo{
    max-width: 100%;
    li{
      float: left;
      width: 12.5%;
      text-align: center;
      height: 120px;
      line-height: 120px;
      color: #666;
      font-size: 13px;
      transition: color .15s linear;
      border-right: 1px solid #eee;
      border-bottom: 1px solid #eee;
      margin-right: -1px;
      margin-bottom: -1px;
      span{
        display: inline-block;
        line-height: normal;
        vertical-align: middle;
        font-family: Helvetica Neue,Helvetica,PingFang SC,Hiragino Sans GB,Microsoft YaHei,SimSun,sans-serif;
        color: #99a9bf;
        i{
          display: block;
          font-size: 32px;
          margin-bottom: 15px;
          color: #606266;
          code{
            display: inline-block;
            padding: 0 3px;
            height: 1em;
            color: #606266;
          }
        }
      }
    }
  }
</style>
