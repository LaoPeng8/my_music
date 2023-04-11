<template>
  <div class="the-footer">
    <p v-for="(item, index) in footerList" :key="index">
      {{item}}
    </p>
  </div>
</template>

<script>
export default {
  name: 'the-footer',
  data () {
    return {
      footerList: [
        `关于 | 帮助 | 条款 | 反馈`,
        `Copyright © 2019 45e00ccc662a7cfcd8cee01c4956c47e`
      ]
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/the-footer.scss';
</style>
