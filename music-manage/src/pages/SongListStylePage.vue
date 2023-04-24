<template>
  <div class="table">
    {{id}}
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-tickets"></i> 歌单分类
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="handle-box">
        <el-button type="primary" size="mini" class="handle-del mr10" @click="batchDelVisible = true">批量删除</el-button>
        <el-input v-model="select_word" size="mini" placeholder="筛选关键词" class="handle-input mr10"></el-input>
        <el-button type="primary" size="mini" @click="centerDialogVisible = true">添加歌单分类</el-button>
      </div>
      <el-table
        :data="tableData"
        size="mini"
        border
        style="width: 100%"
        ref="multipleTable"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="50"></el-table-column>
        <el-table-column prop="name" label="歌单分类"></el-table-column>
        <el-table-column label="操作" width="85">
          <template slot-scope="scope">
            <el-button size="mini" @click="edit(scope.row)">编辑</el-button>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="85">
          <template slot-scope="scope">
            <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!--添加歌单-->
    <el-dialog title="添加歌单" :visible.sync="centerDialogVisible" width="400px" center>
      <el-form :model="registerForm" status-icon ref="registerForm" label-width="70px" class="demo-ruleForm">
          <el-form-item label="风格" prop="style" size="mini">
            <el-input v-model="registerForm.name" placeholder="风格"></el-input>
          </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button size="mini" @click="centerDialogVisible = false">取 消</el-button>
        <el-button type="primary" size="mini" @click="addsongListStyle">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 编辑弹出框 -->
    <el-dialog title="编辑" :visible.sync="editVisible" width="400px">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="歌单分类" size="mini">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button size="mini" @click="editVisible = false">取 消</el-button>
        <el-button type="primary" size="mini" @click="saveEdit">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 删除提示框 -->
    <el-dialog title="提示" :visible.sync="delVisible" width="300px" center>
      <div class="del-dialog-cnt" align="center">删除不可恢复，是否确定删除？</div>
      <span slot="footer" class="dialog-footer">
        <el-button size="mini" @click="delVisible = false">取 消</el-button>
        <el-button type="primary" size="mini" @click="deleteRow">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 批量删除提示框 -->
    <el-dialog title="提示" :visible.sync="batchDelVisible" width="300px" center>
      <div class="del-dialog-cnt" align="center">删除不可恢复，是否确定删除？</div>
      <span slot="footer" class="dialog-footer">
        <el-button size="mini" @click="batchDelVisible = false">取 消</el-button>
        <el-button type="primary" size="mini" @click="delAll">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
import { mixin } from '../mixins'
import { HttpManager } from '../api/index'

export default {
  name: 'song-list-style-page',
  mixins: [mixin],
  props: ['id'],
  data () {
    return {
      tableData: [], // 记录歌曲，用于显示
      tempDate: [], // 记录歌曲，用于搜索时能临时记录一份歌曲列表
      tempId: [], // 记录列表中歌曲的id
      multipleSelection: [], // 记录要删除的歌曲
      delVisible: false, // 显示删除框
      select_word: '', // 记录输入框输入的内容
      idx: -1, // 记录当前要删除的歌曲
      batchDelVisible: false, // 批量删除对话框
      editVisible: false,
      centerDialogVisible: false,
      registerForm: {
        name: ''
      },
      form: {
        id: '',
        name: ''
      }
    }
  },
  watch: {
    select_word: function () {
      if (this.select_word === '') {
        this.tableData = this.tempDate
      } else {
        this.tableData = []
        for (let item of this.tempDate) {
          if (item.name.includes(this.select_word)) {
            this.tableData.push(item)
          }
        }
      }
    }
  },
  mounted () {
    this.getData()
  },
  methods: {
    // 查询所有歌单分类
    getData () {
      HttpManager.getSongListAll()
        .then(res => {
          this.tableData = res
        })
        .catch(err => {
          console.log(err)
        })
    },
    // 通过歌曲ID获取歌曲
    getSongList (id) {
      HttpManager.getSongOfId(id)
        .then(res => {
          this.tableData.push(res[0])
          this.tempDate.push(res[0])
        })
        .catch(err => {
          console.log(err)
        })
    },
    // 删除一首歌曲
    deleteRow () {
      let params = new URLSearchParams()
      params.append('id', this.idx)
      HttpManager.deleteSongListStyle(params)
        .then(res => {
          if (res) {
            this.getData()
            this.notify('删除成功', 'success')
          } else {
            this.notify('删除失败', 'error')
          }
        })
        .catch(err => {
          console.log(err)
        })
      this.delVisible = false
    },

    // 编辑 歌单分类
    edit (row) {
      this.idx = row.id
      this.form = {
        id: row.id,
        name: row.name
      }
      this.editVisible = true
    },
    // 保存编辑
    saveEdit () {
      let params = new URLSearchParams()
      params.append('id', this.form.id)
      params.append('name', this.form.name)
      HttpManager.updateSongListStyle(params)
        .then(res => {
          if (res.code === 1) {
            this.notify('编辑成功', 'success')
            this.getData()
          } else {
            this.notify('编辑失败', 'error')
          }
        })
        .catch(err => {
          console.log(err)
        })
      this.editVisible = false
    },
    // 添加歌单
    addsongListStyle () {
      let params = new URLSearchParams()
      params.append('name', this.registerForm.name)
      HttpManager.insertSongListStyle(params).then(res => {
        if (res.code === 1) {
          this.getData()
          this.registerForm = {}
          this.notify('添加成功', 'success')
        } else {
          this.notify('添加失败', 'error')
        }
      }).catch(err => {
        console.log(err)
      })
      this.centerDialogVisible = false
    }
  }
}
</script>

<style scoped>
.handle-box {
  margin-bottom: 20px;
}

.handle-input {
  width: 300px;
  display: inline-block;
}
</style>
