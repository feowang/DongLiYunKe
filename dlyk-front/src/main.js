//import Vue from 'vue' 从Vue框架引入createApp方法
import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

//import App from './App.vue' 从App.vue文件引入App组件
import App from './App.vue'
import LoginView from './Views/LoginView.vue'

//调用createApp方法创建Vue应用实例，并挂载到#app元素上
let app = createApp(LoginView);
for (const [key,component] of Object.entries(ElementPlusIconsVue)){
    app.component(key,component)
}
app.use(ElementPlus)
app.mount('#app')
