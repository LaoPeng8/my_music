<template>
  <div class="my-music">
    <div class="album-slide">
      <div class="album-img">
        <img :src=attachImageUrl(avator) alt="">
      </div>
      <ul class="album-info">
        <li>昵称： {{username}}</li>
        <li>性别： {{userSex}}</li>
        <li>生日： {{birth}}</li>
        <li>故乡： {{location}}</li>
      </ul>
    </div>
    <div class="album-content">
      <div class="album-title">个性签名: {{introduction}}</div>
      <div class="songs-body">
        <album-content :songList="collectList">
          <template slot="title">我的收藏</template>
        </album-content>
      </div>
    </div>
  </div>
</template>

<script>
import mixin from '../mixins'
import AlbumContent from '../components/AlbumContent'
import { mapGetters } from 'vuex'
import { HttpManager } from '../api/index'

export default {
  name: 'my-music',
  mixins: [mixin],
  components: {
    AlbumContent
  },
  data () {
    return {
      avator: '',
      username: '',
      userSex: '',
      birth: '',
      location: '',
      introduction: '',
      collection: [], // 存放收藏的歌曲ID
      collectList: [] // 收藏的歌曲
    }
  },
  computed: {
    ...mapGetters([
      'userId',
      'id',
      'listOfSongs' // 存放的音乐
    ])
  },
  mounted () {
    this.getMsg(this.userId)
    this.getCollection(this.userId)
  },
  methods: {
    getMsg (id) {
      HttpManager.getUserOfId(id)
        .then(res => {
          this.username = res.username
          this.getuserSex(res.sex)
          this.birth = this.attachBirth(res.birth)
          this.introduction = res.introduction
          this.location = res.location
          this.avator = res.avator
        })
        .catch(err => {
          console.log(err)
        })
    },
    getuserSex (sex) {
      if (sex === 0) {
        this.userSex = '女'
      } else if (sex === 1) {
        this.userSex = '男'
      }
    },
    // 收藏的歌曲ID
    getCollection (userId) {
      HttpManager.getCollectionOfUser(userId)
        .then(res => {
          this.collection = res
          // 通过歌曲ID获取歌曲信息
          for (let item of this.collection) {
            this.getCollectSongs(item)
          }
          this.$store.commit('setListOfSongs', this.collectList)
        })
        .catch(err => {
          console.log(err)
        })
    },
    // 获取收藏的歌曲
    getCollectSongs (id) {
      HttpManager.getSongOfId(id)
        .then(res => {
          this.collectList.push(res[0])
        })
        .catch(err => {
          console.log(err)
        })
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/my-music.scss';
</style>
