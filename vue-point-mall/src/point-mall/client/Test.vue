<template>
  <div>
    <button @click="fetchData">获取数据</button>
    <div v-if="isLoading">加载中...</div>
    <div v-else>
      <p v-if="error">{{ error }}</p>
      <ul v-else>
        <li v-for="item in data" :key="item.id">{{ item.name }}</li>
      </ul>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      data: [],
      isLoading: false,
      error: null
    };
  },
  methods: {
    fetchData() {
      this.isLoading = true;
      // 发送AJAX请求
      axios({
        method: 'post',
        url: 'http://localhost:8080/pointMall/TouristServlet',
        dataType: "json",
        data: {
          "method": "loginById",
          "loginCredentials": 2,
          "password": "Test_updateUserInformation"
        }
      })
          .then(function (response) {
        alert(response);
      })
    }
  }
};
</script>
