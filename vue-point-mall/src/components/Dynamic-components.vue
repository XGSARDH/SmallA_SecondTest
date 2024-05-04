<template>
  <div>
    <div class="sidebar">
      <!-- 侧边栏内容 -->
      <button @click="toggleComponent('ComponentA')">显示组件 A</button>
      <button @click="toggleComponent('ComponentB')">显示组件 B</button>
    </div>

    <!-- 动态挂载的组件 -->
    <component :is="currentComponent"></component>
  </div>
</template>

<script>
import ComponentA from './ComponentA.vue'; // 导入组件A
import ComponentB from './ComponentB.vue'; // 导入组件B

export default {
  name: 'DynamicComponentDemo',
  components: {
    ComponentA,
    ComponentB
  },
  data() {
    return {
      currentComponent: 'ComponentA' // 默认显示组件 A
    };
  },
  methods: {
    toggleComponent(componentName) {
      this.currentComponent = componentName;
    }
  }
};
</script>

<style>
.sidebar {
  /* 样式设置 */
}
</style>
