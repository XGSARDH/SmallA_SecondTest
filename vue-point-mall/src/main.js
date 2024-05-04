import './assets/main.css'
import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import axios from 'axios';

axios.interceptors.request.use(
    config => {
        const token = sessionStorage.getItem('Token');
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    }
);

const app = createApp(App)

app.use(ElementPlus)
app.mount('#app')