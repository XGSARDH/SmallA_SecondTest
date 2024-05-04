<template>
  <div>
    <el-container>
      <el-header>
        <div class="h-6" />
        <el-menu
            :default-active="activeIndex2"
            class="el-menu-demo"
            mode="horizontal"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b"
            @select="handleSelect"
        >
          <el-menu-item index="1" @click="toggleComponent(WelcomePage)">积分商城</el-menu-item>

          <el-sub-menu index="2">
            <template #title>游客服务</template>
            <el-menu-item index="2-1" @click="toggleComponent(Login)">登录</el-menu-item>
            <el-menu-item index="2-2" @click="toggleComponent(ComponentA)">注册</el-menu-item>
            <el-menu-item index="2-3" @click="toggleComponent(ComponentB)">查看商品</el-menu-item>
            <el-sub-menu index="2-4">
              <template #title>item four</template>
              <el-menu-item index="2-4-1">item one</el-menu-item>
              <el-menu-item index="2-4-2">item two</el-menu-item>
              <el-menu-item index="2-4-3">item three</el-menu-item>
            </el-sub-menu>
          </el-sub-menu>

          <el-sub-menu index="3" :disabled="disable3">
            <template #title>用户服务</template>
            <el-menu-item index="3-1" @click="toggleComponent(Test)">个人信息</el-menu-item>
            <el-menu-item index="3-2">个人地址</el-menu-item>
            <el-menu-item index="3-3">个人购物车</el-menu-item>
            <el-menu-item index="3-4">个人订单</el-menu-item>
            <el-menu-item index="3-5">个人资金流</el-menu-item>
            <el-menu-item index="3-6">个人申请积分入口</el-menu-item>
            <el-menu-item index="3-7">申请成为商家</el-menu-item>
          </el-sub-menu>

          <el-sub-menu index="4">
            <template #title>商户服务</template>
            <el-menu-item index="4-1">我的商户信息</el-menu-item>
            <el-menu-item index="4-2">我的商品管理</el-menu-item>
            <el-menu-item index="4-3">查看退货请求</el-menu-item>
          </el-sub-menu>

          <el-sub-menu index="5">
            <template #title>管理员服务</template>
            <el-menu-item index="5-1">管理客户端</el-menu-item>
            <el-menu-item index="5-2">管理商户端</el-menu-item>
            <el-menu-item index="5-3">管理商品</el-menu-item>
            <el-menu-item index="5-3">处理积分申请请求</el-menu-item>
          </el-sub-menu>
        </el-menu>
      </el-header>

      <el-main>
        <component :is="currentMain"></component>
      </el-main>
    </el-container>
  </div>
  <el-backtop :right="100" :bottom="100" />
</template>

<script>
  import ComponentB from "@/components/ComponentB.vue";
  import ComponentA from "@/components/ComponentA.vue";
  import WelcomePage from "@/point-mall/miscellaneous/Welcome_page.vue"
  import Login from "@/point-mall/client/Login.vue"
  import Test from "@/point-mall/client/Test.vue"
  export default {
    name:"App",
    // 主界面跳转所需, 必须先写入这里才能应用
    computed: {
      Login() {return Login},
      ComponentA() {return ComponentA},
      ComponentB() {return ComponentB},
      WelcomePage() {return WelcomePage},
      Test() {return Test}
    },
    // 第二步: 注入组件
    components: {
      ComponentB,
      ComponentA,
      WelcomePage,
      Login,
      Test
    },
    data() {
      return {
        disable3: false,
        currentMain: 'WelcomePage' // 默认显示组件 A
      };
    },
    methods: {
      toggleComponent(componentName) {
        this.currentMain = componentName;
      }
    }
  }
</script>

<style>

</style>