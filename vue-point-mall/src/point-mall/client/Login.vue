<template>
  <form @submit.prevent="login">
    <div>
      <label for="username">用户名：</label>
      <input type="text" id="username" v-model="username" required>
    </div>
    <div>
      <label for="password">密码：</label>
      <input type="password" id="password" v-model="password" required>
    </div>
    <button type="submit">登录</button>
  </form>
</template>

<script>
export default {
  name: "LoginForm",
  data() {
    return {
      username: '',
      password: ''
    };
  },
  methods: {
    login() {
      // 这里可以添加登录逻辑，例如发送登录请求到服务器
      console.log('登录用户名：', this.username);
      console.log('登录密码：', this.password);
    }
  }
}
</script>

<style scoped>

form {
  text-align: center;
  background: white;
  padding: 2em;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  width: 300px;
  box-sizing: border-box;
}

div {
  margin-bottom: 1em;
}

label {
  display: block;
  margin-bottom: 0.5em;
  color: #333;
}

input[type="text"],
input[type="password"] {
  width: 100%;
  padding: 0.8em;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  width: 100%;
  padding: 1em;
  border: none;
  border-radius: 4px;
  background-color: #5c67f2;
  color: white;
  cursor: pointer;
  font-size: 1em;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #4a54e1;
}
</style>
